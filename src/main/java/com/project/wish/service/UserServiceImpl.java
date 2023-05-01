package com.project.wish.service;

import com.project.wish.domain.User;
import com.project.wish.dto.UserCreateRequestDto;
import com.project.wish.dto.UserResponseDto;
import com.project.wish.dto.UserResponseDtoByAdmin;
import com.project.wish.dto.UserUpdateRequestDto;
import com.project.wish.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // think 중복체크 시 user 반환과 boolean 반환중 뭘 쓸까

    //todo 트랜잭션
    private final UserRepository userRepository;

    @Override
    public void insertUser(UserCreateRequestDto dto) {
        User user = userCreateRequestDtoToUser(dto);
        userRepository.insertUser(user);
    }

    @Override
    public UserResponseDto findUserById(Integer id) {
        return userRepository.findUserById(id);
        //todo 찾았는데 없을 경우
    }

    @Override
    public UserResponseDtoByAdmin findUserByIdByAdmin(Integer id) {
        return userRepository.findUserByIdByAdmin(id);
        //todo 찾았는데 없을 경우
    }

    @Override
    public List<UserResponseDtoByAdmin> findUsers() {
        return userRepository.findUsers();
        //todo paging
    }

    @Override
    public void updateUser(Integer userId, UserUpdateRequestDto dto) {
        userRepository.updateUser(userId,dto);
    }

    @Override
    public void updateUserByAdmin(Integer id) {
        userRepository.updateUserByAdmin(id);
    }

    @Override
    public void deleteUserById(Integer id) {
        userRepository.deleteUser(id);
    }

    @Override
    public boolean isUserIdUnique(String userId) {
        User user = userRepository.findUserByUserId();
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
