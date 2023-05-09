package com.project.wish.controller;

import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.service.FileUploader;
import com.project.wish.service.UserService;
import com.project.wish.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * todo : delete 메소드 추가
 */
@Controller
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;
    private final UserService userService;

    public WishController(WishService wishService, UserService userService) {
        this.wishService = wishService;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public String showWishMain(@PathVariable("userId") Long id, Model model, HttpSession session) {
        List<WishDto> userWishDto = wishService.findWishListByUserID(id);
        userService.loginMaintain(session);
        model.addAttribute("userWishlist", userWishDto);
        return "wish/userWishMain";
    }


    /**
     * todo 뷰 파일 연결
     */
    @GetMapping("/{userId}/{wishId}")
    public String userWish(@PathVariable Long wishId, HttpSession session){
        userService.loginMaintain(session);
        return "redirect:wishHistory/" + wishId;
    }

    @GetMapping("/create")
    public String createWishForm(Model model, HttpSession session) {
        userService.loginMaintain(session);
        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("wishDto", WishDto.builder());
        return "wish/wishCreateForm";
    }

    /**
     * todo : redirectAttributes 를 toastr 에  넘겨주기
     */
    @PostMapping("/create")
    public String createWish(WishDto wishDto,
                             @RequestParam MultipartFile imageFile,
                             @RequestParam String goalDateString,
                             @RequestParam String registerDatetimeString,
                             @RequestParam String modifyDatetimeString,
                             RedirectAttributes redirectAttributes,
                             HttpSession session) throws ParseException, IOException {
        userService.loginMaintain(session);
        FileUploader fileUploader = new FileUploader();
        wishDto.setImage(fileUploader.getUploadFilePath(imageFile));
        Long createdWishId = wishService.createWish(wishDto, goalDateString, registerDatetimeString, modifyDatetimeString);

        redirectAttributes.addAttribute("isCreateSuccess", true);
        return "redirect:/wishHistory/" + createdWishId;
    }

    @GetMapping("/update")
    public String updateWishForm(WishDto wishDto, Model model, HttpSession session) {
        userService.loginMaintain(session);
        model.addAttribute("wishDto", wishDto);
        return "wish/updateWishForm";
    }

    /**
     * todo : 뷰 파일 연결
     */
    @PostMapping("/update/{wishId}")
    public String updateWish(@PathVariable long wishId, WishUpdateDto wishUpdateDto, HttpSession session) {
        userService.loginMaintain(session);
        wishService.updateWish(wishId, wishUpdateDto);
        return "";
    }

}
