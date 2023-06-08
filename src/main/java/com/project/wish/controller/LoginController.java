package com.project.wish.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    /**
     * 메인 페이지 요청 메서드
     *
     * @return 메인페이지로 리턴해줍니다.
     */
    @RequestMapping("/")
    public ResponseEntity<Void> mainView() {
        return ResponseEntity.ok(null);

    }
}
