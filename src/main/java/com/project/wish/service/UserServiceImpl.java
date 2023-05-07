package com.project.wish.service;

import com.project.wish.domain.Role;
import com.project.wish.dto.LoginDto;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.project.wish.domain.User;
import com.project.wish.dto.UserCreateRequestDto;
import com.project.wish.dto.UserResponseDto;
import com.project.wish.dto.UserResponseDtoByAdmin;
import com.project.wish.dto.UserUpdateRequestDto;
import com.project.wish.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // think 중복체크 시 user 반환과 boolean 반환중 뭘 쓸까

    //todo 트랜잭션
    private final UserRepository userRepository;


    @Override
    public boolean loginCheck(LoginDto user, HttpSession session, Model model,
        boolean remember, HttpServletResponse response) {
        LoginDto loginUser = userRepository.findLoginUser(user);
        if (loginUser == null || !loginUser.getPassword().equals(user.getPassword())) {
            model.addAttribute("msg", "아이디 혹은 비밀번호가 다릅니다.");
            return false;
        }

        LoginDto loginUserInfo = getLoginUserInfo(user);
        if(loginUserInfo.getIsBlock() == 1) {
            model.addAttribute("msg", "블락된 회원입니다. 관리자에게 문의하세요.");
            return false;
        }
        if(loginUserInfo.getIsQuit() == 1) {
            model.addAttribute("msg", "탈퇴하였습니다. 다시 회원가입하세요.");
            return false;
        }

        // 아이디 기억 : 쿠키에 아이디를 저장
        Cookie rememberCookie = new Cookie("rememberUserId", loginUserInfo.getUserId());
        rememberCookie.setPath("/");
        if(remember == true) {
            rememberCookie.setMaxAge(60*60*24*3); // 3일 동안 쿠키에 저장
        } else {
            rememberCookie.setMaxAge(0);
        }
        response.addCookie(rememberCookie);

        // 세션에 유저 정보를 필요한 만큼 넣음
        session.setAttribute("id", loginUserInfo.getId());
        session.setAttribute("nickname", loginUserInfo.getNickname());
        session.setAttribute("email", loginUserInfo.getEmail());
        if (loginUserInfo.getRoleId() == 1) {
            session.setAttribute("role", Role.ADMIN.toString());
        } else {
            session.setAttribute("role", Role.USER.toString());
        }

        return true;
    }

    @Override
    public boolean loginMaintain(HttpSession session) {
        if(session.getAttribute("id") != null)
            return true;

        return false;
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("id");
        session.removeAttribute("nickname");
        session.removeAttribute("email");
        session.removeAttribute("role");
    }

    @Override
    public LoginDto getLoginUserInfo(LoginDto user) {
        LoginDto userInfo = userRepository.findLoginUserInfo(user);
        return userInfo;
    }

    @Override
    public void insertUser(UserCreateRequestDto dto) {
        User user = userCreateRequestDtoToUser(dto);
        userRepository.insertUser(user);
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findUserById(id);
        return userToUserResponseDto(user);
        //todo 찾았는데 없을 경우
    }

    @Override
    public UserResponseDtoByAdmin findUserByIdByAdmin(Long id) {
        User user = userRepository.findUserByIdByAdmin(id);
        return userToUserResponseDtoByAdmin(user);
        //todo 찾았는데 없을 경우
    }

    @Override
    public List<UserResponseDtoByAdmin> findUsers() {
        List<User> users = userRepository.findUsers();
        return users.stream().map(this::userToUserResponseDtoByAdmin).collect(Collectors.toList());
        //todo paging
    }

    @Override
    public void updateUser(Long id, UserUpdateRequestDto dto) {
        User user = userRepository.findUserById(id);
        User updatedUser = userUpdateRequestDtoToUser(user, dto);
        userRepository.updateUser(updatedUser);
    }


    @Override
    public boolean updateUserBlockByAdmin(Long id) {
        return userRepository.updateUserBlockByAdmin(id);
    }

    @Override
    public boolean isUserBlocked(Long id) {
        return userRepository.isUserBlocked(id);
    }


    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteUser(id);
    }

    @Override
    public boolean isUserIdUnique(String userId) {
        User user = userRepository.findUserByUserId(userId);
        return user == null;
    }

    @Override
    public boolean isEmailUnique(String email) {
        User user = userRepository.findUserByEmail(email);
        return user == null;
    }

    @Override
    public boolean isNicknameUnique(String nickname) {
        User user = userRepository.findUserByNickname(nickname);
        return user == null;
    }

    @Override
    public boolean isPhoneUnique(String phone) {
        User user = userRepository.findUserByPhone(phone);
        return user == null;
    }

    @Override
    public boolean isUserAdmin(Long id) {
        User user = userRepository.findUserById(id);
        return user.getRoleId() == 1;
    }
}
