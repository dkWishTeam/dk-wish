package com.project.wish.service;

import com.project.wish.domain.RoleType;
import com.project.wish.domain.User;
import com.project.wish.dto.*;
import com.project.wish.repository.RoleRepository;
import com.project.wish.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // think 중복체크 시 user 반환과 boolean 반환중 뭘 쓸까

    //todo 트랜잭션
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public String isLogin(LoginDto loginUser, HttpSession session) {
        Optional<User> user = userRepository.findByUserId(loginUser.getUserId());
        if (user.isEmpty() || !loginUser.getPassword().equals(user.get().getPassword())) {
            return "아이디 혹은 비밀번호가 다릅니다.";
        }

        if(user.get().isBlock()) {
            return "블락된 회원입니다. 관리자에게 문의하세요.";
        }

        // 세션에 유저 정보를 필요한 만큼 넣음
        session.setAttribute("id", user.get().getId());
        session.setAttribute("nickname", user.get().getNickname());
        session.setAttribute("email", user.get().getEmail());
        if (user.get().getRole().getRoleType() == RoleType.ADMIN) {
            session.setAttribute("role", RoleType.ADMIN.toString());
        } else {
            session.setAttribute("role", RoleType.USER.toString());
        }

        return "loginSuccess";
    }

    @Override
    public boolean isLogined(HttpSession session) {
        if(session.getAttribute("id") != null)
            return true;
        return false;
    }

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }

    @Transactional
    @Override
    public void insertUser(UserCreateRequestDto dto) {
        User user = userCreateRequestDtoToUser(dto);
        user.setRole(roleRepository.findByRoleType(RoleType.USER).orElseThrow());
        userRepository.save(user);
    }

    @Override
    public UserResponseDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return userToUserResponseDto(user);
        //todo 찾았는데 없을 경우
    }

    @Override
    public UserResponseDtoByAdmin findUserByIdByAdmin(Long id) {
        //todo 현재 세션에 있는 아이디가 해당 유저 또는 admin 인지
        //todo 미구현
        User user = userRepository.findById(id).orElseThrow();
        return userToUserResponseDtoByAdmin(user);
    }

    @Override
    public List<UserResponseDtoByAdmin> findUsers() {
        // todo 관리자인지 체크
        List<User> users = userRepository.findAll();
        return users.stream().map(this::userToUserResponseDtoByAdmin).collect(Collectors.toList());
        //todo paging
    }

    @Transactional
    @Override
    public void updateUser(UserUpdateRequestDto dto) {
        User user = userRepository.findById(dto.getId()).orElseThrow();
        userUpdateByDto(user, dto);
    }


    @Transactional
    @Override
    public boolean updateUserBlockByAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        if(user.getRole().getRoleType() == RoleType.ADMIN){
            return false;
        }
        boolean beforeBlockMethod = user.isBlock();
        user.setBlock(!user.isBlock());
        boolean afterBlockMethod = user.isBlock();
        return beforeBlockMethod != afterBlockMethod;
    }

    @Override
    public boolean isUserBlocked(Long id) {
        return userRepository.findById(id).orElseThrow().isBlock();
    }


    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean isUserIdUnique(String userId) {
        return !userRepository.existsByUserId(userId);
    }

    @Override
    public boolean isEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }

    @Override
    public boolean isNicknameUnique(String nickname) {
        return !userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isPhoneUnique(String phone) {
        return !userRepository.existsByPhone(phone);
    }

    @Override
    public boolean isUserAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return user.getRole().getRoleType() == RoleType.ADMIN;
    }
}
