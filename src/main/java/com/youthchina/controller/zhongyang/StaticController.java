package com.youthchina.controller.zhongyang;

import com.youthchina.domain.Qinghong.Entry;
import com.youthchina.domain.zhongyang.User;
import com.youthchina.dto.EntryResponseDTO;
import com.youthchina.dto.Response;
import com.youthchina.dto.applicant.EducationResponseDTO;
import com.youthchina.exception.zhongyang.BaseException;
import com.youthchina.exception.zhongyang.NotFoundException;
import com.youthchina.service.Qinghong.DictionaryService;
import com.youthchina.service.tianjian.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 2/12/19.
 */
@RestController
@RequestMapping("${web.url.prefix}/static/**")
public class StaticController {
    private StaticFileService fileService;

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    public StaticController(StaticFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/")
    public ResponseEntity upload(@RequestPart MultipartFile file, @AuthenticationPrincipal User user) throws BaseException {
        Long id;
        try {
            id = fileService.saveFile(file.getResource(), user.getId());
        } catch (IOException e) {
            throw new BaseException(5000, 500, "Cannot upload file because server end error");
        }
        return ResponseEntity.ok(new Response(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUrl(HttpServletRequest request, @PathVariable String id) {
        String clientIp = request.getRemoteAddr();
        return ResponseEntity.ok(new Response(fileService.getFileUrl(id, "China")));
    }

    @GetMapping("/location")
    public ResponseEntity<?> getLocation(@RequestParam(value = "target") Integer target,@RequestParam(value = "id") Integer id) throws NotFoundException{
        List<Entry> locations=dictionaryService.getLocations(target,id);
        List<EntryResponseDTO> entryResponseDTOS=new ArrayList<>();
        for(Entry entry:locations){
            EntryResponseDTO entryResponseDTO=new EntryResponseDTO(entry);
            entryResponseDTOS.add(entryResponseDTO);
        }
        return ResponseEntity.ok(new Response(entryResponseDTOS));
    }
}
