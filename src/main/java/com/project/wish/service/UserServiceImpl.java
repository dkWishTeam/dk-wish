package com.project.wish.service;

import com.project.wish.dto.LoginDto;
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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // think 중복체크 시 user 반환과 boolean 반환중 뭘 쓸까

    //todo 트랜잭션
    private final UserRepository userRepository;


    @Override
    public boolean loginCheck (LoginDto user, HttpSession session) {
        LoginDto loginUser = userRepository.findLoginUser(user);
        if(loginUser == null) {
            return false;
        }
        if(!loginUser.getPassword().equals(user.getPassword())) {
            return false;
        }

        LoginDto loginUserInfo = getLoginUserInfo(user);
        session.setAttribute("id", loginUserInfo.getId());
        session.setAttribute("nickname", loginUserInfo.getNickname());

        return true;
    }

    @Override
    public void logout(HttpSession session) {
        session.removeAttribute("id");
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
    public void updateUserBlockByAdmin(Long id) {
        userRepository.updateUserBlockByAdmin(id);
    }

    @Override
    public void updateUserUnBlockByAdmin(Long id) {
        userRepository.updateUserUnBlockByAdmin(id);
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
}
