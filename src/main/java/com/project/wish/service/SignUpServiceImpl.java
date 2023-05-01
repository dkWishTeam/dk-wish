package com.project.wish.service;

import com.project.wish.domain.User;
import com.project.wish.dto.UserDto;
import com.project.wish.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;

    @Override
    public boolean signUp(UserDto signUpDto) {
        //todo return 값을 void?
        User user = new User(signUpDto);
        return userRepository.insertUser(user);
    }

    @Override
    public boolean isEmailUnique(String email) {
        User existingUser = userRepository.findByEmail(email);
        return existingUser == null;
    }

    @Override
    public boolean isNicknameUnique(String nickname) {
        User existingUser = userRepository.findByNickname(nickname);
        return existingUser == null;
    }

    @Override
    public boolean isPhoneUnique(String phone) {
        User existingUser = userRepository.findByPhone(phone);
        return existingUser == null;
    }
}
