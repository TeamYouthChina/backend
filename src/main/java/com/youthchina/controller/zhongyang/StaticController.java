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

    @GetMapping("/dictionary")
    public ResponseEntity<?> getDictionary(@RequestParam(value = "type") String type, @RequestParam(value = "length",required = false) String length) throws NotFoundException {
        List<EntryResponseDTO> entryResponseDTOS = new ArrayList<>();
        switch (type) {
            case "major": {
                if(length==null){
                    throw new NotFoundException(404,404,"cannot find this length");
                }
                if (length .equals("full")) {
                    List<Entry> entries = dictionaryService.getMajorByFull();
                    for (Entry entry : entries) {
                        EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                        entryResponseDTOS.add(entryResponseDTO);
                    }
                } else {
                    List<Entry> entries = dictionaryService.getMajorByAbbre();
                    for (Entry entry : entries) {
                        EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                        entryResponseDTOS.add(entryResponseDTO);
                    }
                }
                break;
            }
            case "degree": {
                List<Entry> entries = dictionaryService.getDegree();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }
            case "diploma": {
                List<Entry> entries = dictionaryService.getDiploma();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;
            }
            case "advantageSkill": {
                List<Entry> entries = dictionaryService.getAdvantageSkill();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;
            }
            case "industry": {
                List<Entry> entries = dictionaryService.getIndustry();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;
            }

            case "job": {
                List<Entry> entries = dictionaryService.getJob();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "companyScale": {
                List<Entry> entries = dictionaryService.getCompanyScale();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "companyNature": {
                List<Entry> entries = dictionaryService.getCompanyNature();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }
            case "country": {
                List<Entry> entries = dictionaryService.getCountry();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "chinaRegion": {
                if(length==null){
                    throw new NotFoundException(404,404,"cannot find this length");
                }
                if (length .equals("full")) {
                    List<Entry> entries = dictionaryService.getChinaRegionByFull();
                    for (Entry entry : entries) {
                        EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                        entryResponseDTOS.add(entryResponseDTO);
                    }
                } else {
                    List<Entry> entries = dictionaryService.getChinaRegionByAbbre();
                    for (Entry entry : entries) {
                        EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                        entryResponseDTOS.add(entryResponseDTO);
                    }
                }
                break;

            }
            case "USAState": {
                if(length==null){
                    throw new NotFoundException(404,404,"cannot find this length");
                }
                if (length.equals("full")) {
                    List<Entry> entries = dictionaryService.getUSAStateByFull();
                    for (Entry entry : entries) {
                        EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                        entryResponseDTOS.add(entryResponseDTO);
                    }
                } else {
                    List<Entry> entries = dictionaryService.getUSAStateByAbbre();
                    for (Entry entry : entries) {
                        EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                        entryResponseDTOS.add(entryResponseDTO);
                    }
                }
                break;

            }
            case "USARegion": {
                List<Entry> entries = dictionaryService.getUSARegion();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "CHNUniversity": {
                List<Entry> entries = dictionaryService.getCANUniversity();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }
            case "USAUniversity": {
                List<Entry> entries = dictionaryService.getUSAUniversity();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "GBRUniversity": {
                List<Entry> entries = dictionaryService.getGBRUniversity();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }
            case "CANUniversity": {
                List<Entry> entries = dictionaryService.getCANUniversity();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }
            case "TimesRank": {
                List<Entry> entries = dictionaryService.getTIMESRank();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "USNEWSRank": {
                List<Entry> entries = dictionaryService.getUSNEWSRank();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "QSRank": {
                List<Entry> entries = dictionaryService.getQSRank();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            case "topCompany": {
                List<Entry> entries = dictionaryService.getTopCompany();
                for (Entry entry : entries) {
                    EntryResponseDTO entryResponseDTO = new EntryResponseDTO(entry);
                    entryResponseDTOS.add(entryResponseDTO);
                }
                break;

            }

            default:
                throw new NotFoundException(404, 404, "do not have this type");


        }
        return ResponseEntity.ok(new Response(entryResponseDTOS));
    }
}
