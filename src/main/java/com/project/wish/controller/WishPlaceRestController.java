package com.project.wish.controller;

import com.project.wish.dto.WishPlaceDto;
import com.project.wish.service.WishPlaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class WishPlaceRestController {
    private final WishPlaceService wishPlaceService;

    @RequestMapping(value = "/place/{path}", produces = "application/json; charset=utf-8")
    @ResponseBody
    public List<WishPlaceDto> responseWishPlace(@PathVariable("path") String path) {
        return wishPlaceService.getWishPlace(path);
    }
}
