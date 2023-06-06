package com.project.wish.service;

import com.project.wish.auth.CustomUserDetails;
import com.project.wish.domain.User;
import com.project.wish.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    // todo 5: 아까 AuthenticationProvider 에 꽂아 준 UserDetailService bean이다.
    // todo 5: 이 클래스는 사용자가 로그인 시에 이용한 아이디를 통해 DB 를 조회해서 내가 입력한 이이디에 맞는 user 엔티티 객체를 가져온다.

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username)
            .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        // todo 7: UserDetails 를 상속받는 객체를 반환한다.
        return new CustomUserDetails(user);
    }
}
