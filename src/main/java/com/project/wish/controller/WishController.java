package com.project.wish.controller;

import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.service.FileUploader;
import com.project.wish.service.UserService;
import com.project.wish.service.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * todo : delete 메소드 추가
 */
@Controller
@RequestMapping("/users")
@Slf4j
public class WishController {

    private final WishService wishService;
    private final UserService userService;

    public WishController(WishService wishService, UserService userService) {
        this.wishService = wishService;
        this.userService = userService;
    }

    @GetMapping("/{userId}/wishes")
    public String showWishMain(@PathVariable("userId") Long id, Model model, HttpSession session) {
        userService.isLogin(session);
        List<WishDto> userWishDto = wishService.findWishListByUserID(id);
        model.addAttribute("userWishlist", userWishDto);
        return "wish/userWishMain";
    }

    @GetMapping("/{userId}/wishes/{wishId}")
    public String userWish(@PathVariable Long wishId, HttpSession session){
        userService.isLogin(session);
        return "redirect:wishHistory/" + wishId;
    }

    @GetMapping("/{userId}/wishes/createForm")
    public String createWishForm(Model model, HttpSession session) {
        userService.isLogin(session);
        return "wish/wishCreateForm";
    }

    /**
     * todo : redirectAttributes 를 toastr 에  넘겨주기
     */
    @PostMapping("/{userId}/wishes/")
    public String createWish(WishDto wishDto,
                             @RequestParam MultipartFile imageFile,
                             RedirectAttributes redirectAttributes,
                             HttpSession session) throws ParseException, IOException {
        System.out.println("1 : " + session.getAttribute("id"));
        FileUploader fileUploader = new FileUploader();
        System.out.println("2 : " + session.getAttribute("id"));
        wishDto.setImage(fileUploader.getUploadFilePath(imageFile));
        System.out.println("3 : " + session.getAttribute("id"));
        wishService.createWish(wishDto);
        System.out.println("4 : " + session.getAttribute("id"));
        redirectAttributes.addAttribute("isCreateSuccess", true);
        System.out.println("5 : " + session.getAttribute("id"));
        return "redirect:/users/" + session.getAttribute("id") + "/wishes";
    }

    @GetMapping("/{userId}/wishes/updateForm/{wishId}")
    public String updateWishForm(@PathVariable Long wishId, Model model, HttpSession session) {
        userService.isLogin(session);
        model.addAttribute("wishDto", wishService.findWishById(wishId));
        return "wish/updateWishForm";
    }

    /**
     * todo : 뷰 파일 연결
     */
    @PutMapping("/{userId}/wishes/{wishId}")
    public String updateWish( WishUpdateDto wishUpdateDto, HttpSession session) {
        wishService.updateWish(wishUpdateDto);
        return "redirect:/users/" + session.getAttribute("id");
    }

    @DeleteMapping("/{userId}/wishes/{wishId}")
    public String deleteWish(@PathVariable long wishId , HttpSession session) {
        wishService.deleteWish(wishId);
        return "redirect:/users/" + session.getAttribute("id") + "/wishes";
    }

}
