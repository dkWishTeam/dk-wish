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
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WishController {

    @Value("${wish.image.local.path}")
    private String path;
    private final String DEFAULT_IMAGE_URL = "https://wish-image-s3.s3.ap-northeast-2.amazonaws.com/default.png";
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

    @PostMapping("/{userId}/wishes")
    public Long createWish(@RequestPart WishRequestDto wishRequestDto, @RequestPart Optional<MultipartFile> imageFile) {
        String s3FileUrl  = DEFAULT_IMAGE_URL;
        if (imageFile.isPresent()) {
            wishService.createLocalImageFolder(path);
            String localFilePath = s3FileUploader.uploadToLocal(imageFile.orElseThrow());
            s3FileUrl = s3FileUploader.getS3FileUrl(localFilePath);
        }
        wishRequestDto.setImage(s3FileUrl);
        return wishService.createWish(wishRequestDto);
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
