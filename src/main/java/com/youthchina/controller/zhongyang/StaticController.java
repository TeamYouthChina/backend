package com.youthchina.controller.zhongyang;

import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.Response;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.service.tianjian.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by zhongyangwu on 2/12/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/static/**")
public class StaticController {
    private StaticFileService fileService;

    @Autowired
    public StaticController(StaticFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/")
    public ResponseEntity upload(@RequestPart MultipartFile file, @AuthenticationPrincipal User user) throws BaseException {
        Long id;
        try {
            id = fileService.saveFile(file.getResource().getFile(), user.getId());
        } catch (IOException e) {
            throw new BaseException(5000, 500, "Cannot upload file because server end error");
        }
        return ResponseEntity.ok(new Response(id));
    }
}
