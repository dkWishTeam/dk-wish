package com.project.wish.controller;

import com.project.wish.dto.WishRequestDto;
import com.project.wish.dto.WishResponseDto;
import com.project.wish.dto.WishUpdateDto;
import com.project.wish.service.FileUploader;
import com.project.wish.service.S3FileUploader;
import com.project.wish.service.WishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * todo : delete 메소드 추가
 */
@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishController {

    @Value("${wish.image.local.path}")
    private String path;
    private final WishService wishService;
    private final S3FileUploader s3FileUploader;

    public WishController(WishService wishService, S3FileUploader s3FileUploader) {
        this.wishService = wishService;
        this.s3FileUploader = s3FileUploader;
    }

    @ResponseBody
    @GetMapping("/{userId}/wishes")
    public List<WishResponseDto> showWishMainJson(@PathVariable("userId") Long id) {
        return wishService.findWishListByUserID(id);
    }

    @GetMapping("/{userId}/wishes/{wishId}")
    public String userWish(@PathVariable Long wishId) {
        return "redirect:/wishes/" + wishId + "/wishHistories";
    }

    @GetMapping("/{userId}/wishes/createForm")
    public String createWishForm() {
        return "wish/wishCreateForm";
    }

    /**
     * todo : redirectAttributes 를 toastr 에  넘겨주기
     */
    @PostMapping("/{userId}/wishes")
    public String createWish(@RequestPart WishRequestDto wishRequestDto, @RequestPart MultipartFile imageFile) {
        wishService.createLocalImageFolder(path);
        String localFilePath = s3FileUploader.uploadToLocal(imageFile);
        String s3FileUrl = s3FileUploader.getS3FileUrl(localFilePath);
        log.info("s3 image url : " + s3FileUrl);
        wishRequestDto.setImage(s3FileUrl);
        wishService.createWish(wishRequestDto);

        return "success";
    }

    @GetMapping("/{userId}/wishes/{wishId}/updateForm")
    public String updateWishForm(@PathVariable Long wishId, Model model) {
        model.addAttribute("wishId");
        model.addAttribute("wishResponseDto", wishService.findWishById(wishId));
        return "wish/updateWishForm";
    }


    @PostMapping("/{userId}/wishes/{wishId}")
    public String updateWish(@PathVariable Long userId, WishUpdateDto wishUpdateDto, @RequestParam MultipartFile imageFile) {
        FileUploader fileUploader = new FileUploader();
        String fileName = fileUploader.getUploadFilePath(imageFile);
        wishUpdateDto.setImage(fileName);
        wishService.updateWish(wishUpdateDto);
        return "redirect:/users/" + userId + "/wishes";
    }

    @DeleteMapping("/{userId}/wishes/{wishId}")
    public String deleteWish(@PathVariable long wishId, HttpSession session) {
        wishService.deleteWish(wishId);
        return "redirect:/users/" + session.getAttribute("id") + "/wishes";
    }

}
