package com.project.wish.controller;

import com.project.wish.dto.WishDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.service.FileUploader;
import com.project.wish.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/{userId}")
    public String showWishMain(@PathVariable("userId") Long id, Model model) {
        List<WishDto> userWishDto = wishService.findWishListByUserID(id);
        System.out.println(userWishDto);
        model.addAttribute("userWishlist", userWishDto);
        return "wish/userWishMain";
    }


    /**
     * todo 뷰 파일 연결
     */
    @GetMapping("/{userId}/{wishId}")
    public String userWish(){
        return "";
    }

    @GetMapping("/create")
    public String createWishForm(Model model) {
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
                             RedirectAttributes redirectAttributes) throws ParseException, IOException {

        FileUploader fileUploader = new FileUploader();
        wishDto.setImage(fileUploader.getUploadFilePath(imageFile));

        wishService.createWish(wishDto, goalDateString, registerDatetimeString, modifyDatetimeString);
        redirectAttributes.addAttribute("isCreateSuccess", true);

        return "redirect:basic/userWishMain";
    }

    @GetMapping("/update")
    public String updateWishForm(WishDto wishDto, Model model) {
        model.addAttribute("wishDto", wishDto);
        return "wish/updateWishForm";
    }

    /**
     * todo : 뷰 파일 연결
     */
    @PostMapping("/update/{wishId}")
    public String updateWish(@PathVariable long wishId, WishUpdateDto wishUpdateDto) {
        wishService.updateWish(wishId, wishUpdateDto);
        return "";
    }

}
