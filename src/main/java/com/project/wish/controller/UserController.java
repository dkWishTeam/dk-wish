package com.project.wish.controller;

import com.project.wish.domain.Role;
import com.project.wish.dto.UserCreateRequestDto;
import com.project.wish.dto.UserUpdateRequestDto;
import com.project.wish.exception.UnAuthorizedAccessException;
import com.project.wish.service.UserService;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 시에 사용되는 메서드입니다.
     *
     * @param dto user 등록 시 쓰이는 dto 입니다.
     */
    @PostMapping
    public String createUser(UserCreateRequestDto dto) {
        userService.insertUser(dto);
        // think 입력 시점에 중복체크 싹다 한 번 다시? 아니면 unique 제약 조건을 걸어주나?
        // todo 회원 가입 시 회원가입이 안되면 띄워주는 에러페이지
        return "redirect:/";
    }

    /**
     * 회원정보 조회시에 사용되는 메서드입니다.
     *
     * @param session myPage 에 대한 권한이 있는지 얻기 위한 session 객체입니다.
     * @param model   데이터를 담는 model 객체입니다.
     * @param id      회원의 고유번호
     * @return 회원의 myPage 를 반환합니다.
     */
    @GetMapping("/{id}")
    public String findUserById(HttpSession session, Model model, @PathVariable("id") Long id)
        throws UnAuthorizedAccessException {
        isUserAuthorized(id, session);
        model.addAttribute("user", userService.findUserById(id));
        return "user/userInfo";
    }

    /**
     * 관리자의 회원정보 조회시에 사용되는 메서드입니다.
     *
     * @param session myPage 에 대한 권한이 있는지 얻기 위한 session 객체입니다.
     * @param model   데이터를 담는 model 객체입니다.
     * @param id      회원의 고유번호
     * @return 회원의 myPage 를 반환합니다.
     */
    @GetMapping("/{id}/admin")
    public String findUserByIdByAdmin(HttpSession session, Model model, @PathVariable("id") Long id)
        throws UnAuthorizedAccessException {
        isRoleEqualsAdmin(session);
        model.addAttribute("user", userService.findUserByIdByAdmin(id));
        return "";
        //todo Hierarchy check
    }


    /**
     * 사이트 이용자(관리자)가 회원의 리스트를 보는 메서드입니다.
     *
     * @param model 데이터를 담는 model 객체입니다.
     * @return 회원 리스트 페이지를 반환합니다.
     */
    @GetMapping
    public String findUsersByAdmin(HttpSession session, Model model) {
        isRoleEqualsAdmin(session);
        model.addAttribute("users", userService.findUsers());
        return "user/userList";
        //todo paging
    }

    /**
     * 회원이 자신의 정보를 수정할 때 쓰는 메서드입니다.
     *
     * @param id      회원의 고유번호
     * @param dto     UserUpdateRequestDto(회원 정보수정사항을 담는 객체) 객체입니다.
     * @param session session 에 있는 회원의 고유번호를 얻기 위한 객체
     * @throws UnAuthorizedAccessException 회원 정보 페이지에 대한 권한이 없을 때 발생하는 오류입니다.
     * @return 마이페이지로 이동합니다.
     */
    @PutMapping("/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto dto, HttpSession session)
        throws UnAuthorizedAccessException {
        isUserAuthorized(id, session);
        userService.updateUser(id, dto);
        return "redirect:/users/" + id;
    }

    /**
     * 관리자가 회원을 block 할 때 쓰이는 메서드입니다.
     *
     * @param id      회원의 고유번호
     * @param session session 에 있는 회원의 고유번호를 얻기 위한 객체
     * @throws UnAuthorizedAccessException 회원 정보 페이지에 대한 권한이 없을 때 발생하는 오류입니다.
     * @return 마이페이지로 이동합니다.
     */
    @PostMapping("/{id}/block")
    public String updateUserBlockByAdmin(@PathVariable("id") Long id, HttpSession session)
        throws UnAuthorizedAccessException {
        isRoleEqualsAdmin(session);
        userService.updateUserBlockByAdmin(id);
        return "redirect:/users";
    }

    /**
     * 관리자가 회원을 unBlock 할 때 쓰이는 메서드입니다.
     *
     * @param id      회원의 고유번호
     * @param session session 에 있는 회원의 고유번호를 얻기 위한 객체
     * @throws UnAuthorizedAccessException 회원 정보 페이지에 대한 권한이 없을 때 발생하는 오류입니다.
     */
    @PostMapping("/{id}/unblock")
    public String updateUserUnBlockByAdmin(@PathVariable("id") Long id, HttpSession session)
        throws UnAuthorizedAccessException {
        isRoleEqualsAdmin(session);
        userService.updateUserUnBlockByAdmin(id);
        return "redirect:/users";
    }

    /**
     * 회원이 회원 탈퇴할 때 쓰이는 메서드입니다.
     *
     * @param id      회원의 고유번호
     * @param session session 에 있는 회원의 고유번호를 얻기 위한 객체
     * @return 회원탈퇴(회원 본인만 가능)를 한 후 main 페이지로 이동합니다.
     * @throws UnAuthorizedAccessException 회원 정보 페이지에 대한 권한이 없을 때 발생하는 오류입니다.
     */
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id, HttpSession session)
        throws UnAuthorizedAccessException {
        isUserAuthorized(id, session);
        userService.deleteUserById(id);
        session.removeAttribute("userId");
        return "redirect:/main";
    }

    /**
     * 회원가입 form 을 얻을 때 쓰는 메서드입니다.
     *
     * @return 회원가입 form 을 반환합니다.
     */
    @GetMapping("/sign-up")
    public String getSignUpForm() {
        return "user/signUpForm";
        //todo 해당 경로의 Path hierarchy 구조 변경
    }

    /**
     * userId(회원 아이디) 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param userId 사용가능한지(중복이 아닌지) 확인하려는 userId(로그인시 이용하는 id) 입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @PostMapping("/userid-duplicate-check")
    @ResponseBody
    public boolean isUserIdUnique(@RequestParam("userId") String userId) {
        System.out.println("wd");
        boolean isEmailDuplicate = userService.isUserIdUnique(userId);
        return !isEmailDuplicate;
    }


    /**
     * 이메일 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param email 사용가능한지(중복이 아닌지) 확인하려는 email 입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @PostMapping("/email-duplicate-check")
    @ResponseBody
    public boolean isEmailUnique(@RequestParam("email") String email) {
        boolean isEmailDuplicate = userService.isEmailUnique(email);
        return !isEmailDuplicate;
    }

    /**
     * 닉네임 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param nickname 사용가능한지(중복이 아닌지) 확인하려는 nickname 입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @PostMapping("/nickname-duplicate-check")
    @ResponseBody
    public boolean isNicknameUnique(@RequestParam("nickname") String nickname) {
        boolean isNicknameDuplicate = userService.isNicknameUnique(nickname);
        return !isNicknameDuplicate;
    }

    /**
     * phone(핸드폰 번호) 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param phone 핸드폰 번호 사용가능한지(중복이 아닌지) 확인하려는 phone(핸드폰 번호)입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @PostMapping("/phone-duplicate-check")
    @ResponseBody
    public boolean isPhoneUnique(@RequestParam("phone") String phone) {
        boolean isPhoneDuplicate = userService.isPhoneUnique(phone);
        return !isPhoneDuplicate;
    }

    @PostMapping("/user-admin-check")
    @ResponseBody
    public boolean isUserAdmin(@RequestParam("id") Long id) {
        return userService.isUserAdmin(id);
    }



    /**
     * uri Path 와 session 의 id 값의 동일여부를(권한이 있는지) 체크하는 메서드입니다.
     *
     * @param id  회원의 고유번호
     * @param session 로그인 시 session 에 저장되는 id 값을 얻기 위해 사용되는 session 객체
     * @throws UnAuthorizedAccessException
     */
    private void isUserAuthorized(Long id, HttpSession session) throws UnAuthorizedAccessException {
        Long loggedUserId = (Long)session.getAttribute("id");
        if (loggedUserId == null || !Objects.equals(loggedUserId, id)) {
            throw new UnAuthorizedAccessException();
            //todo 권한이 없는 페이지 오류 처리
        }
    }

    /**
     * session 의 role 값을 확인하여 관리자인지 확인할 때 쓰이는 메서드입니다.
     *
     * @param session role value 를 얻기 위한 session 객체
     * @throws UnAuthorizedAccessException
     */
    private void isRoleEqualsAdmin(HttpSession session) throws UnAuthorizedAccessException {

        String role = (String) session.getAttribute("role");
        //todo 로그인 시 session.add(role,"ADMIN") 추가
        if (role == null || !Objects.equals(role, Role.ADMIN.toString())) {
            throw new UnAuthorizedAccessException();
        }
    }
}
