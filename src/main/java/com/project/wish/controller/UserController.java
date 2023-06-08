package com.project.wish.controller;

import com.project.wish.auth.CustomUserDetails;
import com.project.wish.dto.BlockUserResponse;
import com.project.wish.dto.UserCreateRequestDto;
import com.project.wish.dto.UserResponseDto;
import com.project.wish.dto.UserResponseDtoByAdmin;
import com.project.wish.dto.UserUpdateRequestDto;
import com.project.wish.exception.UnAuthorizedAccessException;
import com.project.wish.service.UserService;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 시에 사용되는 메서드입니다.
     *
     * @param dto user 등록 시 쓰이는 dto 입니다.
     */
    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateRequestDto dto) {
        userService.insertUser(dto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * 회원정보 조회시에 사용되는 메서드입니다.
     *
     * @param id      회원의 고유번호
     * @return 회원의 myPage 를 반환합니다.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(
        @AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("id") Long id)
        throws UnAuthorizedAccessException {
        unAuthorizedAccessCheck(userDetails,id);
        return ResponseEntity.ok(userService.findUserById(id));
    }

    /**
     * 사이트 이용자(관리자)가 회원의 리스트를 보는 메서드입니다.
     *
     * @return 회원 리스트 페이지를 반환합니다.
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDtoByAdmin>> findUsersByAdmin() {
        return ResponseEntity.ok(userService.findUsers());
    }

    /**
     * 회원이 자신의 정보를 수정할 때 쓰는 메서드입니다.
     *
     * @param dto     UserUpdateRequestDto(회원 정보수정사항을 담는 객체) 객체입니다.
     * @return 마이페이지로 이동합니다.
     * @throws UnAuthorizedAccessException 회원 정보 페이지에 대한 권한이 없을 때 발생하는 오류입니다.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@AuthenticationPrincipal CustomUserDetails userDetails,@RequestBody UserUpdateRequestDto dto)
        throws UnAuthorizedAccessException {
        unAuthorizedAccessCheck(userDetails,dto.getId());
        userService.updateUser(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * 관리자가 회원을 block 할 때 쓰이는 메서드입니다.
     *
     * @param id      회원의 고유번호
     * @param session session 에 있는 회원의 고유번호를 얻기 위한 객체
     * @return 마이페이지로 이동합니다.
     * @throws UnAuthorizedAccessException 회원 정보 페이지에 대한 권한이 없을 때 발생하는 오류입니다.
     */
    @PutMapping("/{id}/block")
    public ResponseEntity<BlockUserResponse> updateUserBlockByAdmin(@PathVariable("id") Long id, HttpSession session)
        throws UnAuthorizedAccessException {
        return ResponseEntity.ok(
            new BlockUserResponse(userService.updateUserBlockByAdmin(id), userService.isUserBlocked(id)));
    }

    /**
     * 회원이 회원 탈퇴할 때 쓰이는 메서드입니다.
     *
     * @param id      회원의 고유번호
     * @return 회원탈퇴(회원 본인만 가능)를 한 후 main 페이지로 이동합니다.
     * @throws UnAuthorizedAccessException 회원 정보 페이지에 대한 권한이 없을 때 발생하는 오류입니다.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal CustomUserDetails userDetails, @PathVariable("id") Long id)
        throws UnAuthorizedAccessException {
        unAuthorizedAccessCheck(userDetails, id);
        userService.deleteUserById(id);
        return ResponseEntity.ok(null);
    }

    /**
     * userId(회원 아이디) 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param userId 사용가능한지(중복이 아닌지) 확인하려는 userId(로그인시 이용하는 id) 입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @GetMapping("/userid-duplicate-check")
    public ResponseEntity<Boolean> isUserIdUnique(@RequestParam("userId") String userId) {
        return ResponseEntity.ok(userService.isUserIdUnique(userId));
    }


    /**
     * 이메일 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param email 사용가능한지(중복이 아닌지) 확인하려는 email 입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @GetMapping("/email-duplicate-check")
    public ResponseEntity<Boolean> isEmailUnique(@RequestParam("email") String email) {
        return ResponseEntity.ok(userService.isEmailUnique(email));
    }

    /**
     * 닉네임 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param nickname 사용가능한지(중복이 아닌지) 확인하려는 nickname 입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @GetMapping("/nickname-duplicate-check")
    public ResponseEntity<Boolean> isNicknameUnique(@RequestParam("nickname") String nickname) {
        return ResponseEntity.ok(userService.isNicknameUnique(nickname));
    }

    /**
     * phone(핸드폰 번호) 중복체크를 할 때 쓰이는 메서드입니다.
     *
     * @param phone 핸드폰 번호 사용가능한지(중복이 아닌지) 확인하려는 phone(핸드폰 번호)입니다.
     * @return 사용가능하면(중복이 아니면) true 를 반환합니다.
     */
    @GetMapping("/phone-duplicate-check")
    public ResponseEntity<Boolean> isPhoneUnique(@RequestParam("phone") String phone) {
        return ResponseEntity.ok(userService.isPhoneUnique(phone));
    }

    @GetMapping("/user-admin-check")
    public ResponseEntity<Boolean> isUserAdmin(@RequestParam("id") Long id) {
        return ResponseEntity.ok(userService.isUserAdmin(id));
    }

    private void unAuthorizedAccessCheck(CustomUserDetails userDetails, Long id) {
        Long userId = userDetails.getUser().getId();
        if (!userId.equals(id)) {
            throw new UnAuthorizedAccessException(); // 본인이 아닌 경우 예외 처리
        }
    }
}
