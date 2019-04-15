package com.youthchina.service.tianjian;

import com.youthchina.dao.tianjian.LogoUploadMapper;
import com.youthchina.domain.qingyang.Logo;
import com.youthchina.exception.zhongyang.NotFoundException;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

public class LogoUploadServiceImpl implements LogoUploadService {

    @Resource
    LogoUploadMapper logoUploadMapper;

    @Override
    public Logo get(Integer id) throws NotFoundException {
        Logo logo= logoUploadMapper.get(id);
        if(logo == null){
            throw new NotFoundException(404,404,"this logo does not exist");
        }
        return logoUploadMapper.get(id);
    }

    @Override
    public List<Logo> get(List<Integer> id) {
        List<Logo> logos = new LinkedList<>();
        for(Integer one : id){
            Logo logo = logoUploadMapper.get(one);
            logos.add(logo);
        }
        return logos;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        Logo logo= logoUploadMapper.get(id);
        if(logo == null){
            throw new NotFoundException(404,404,"this logo does not exist");
        }
         logoUploadMapper.delete(id);
    }

    @Override
    public Logo update(Logo logo) throws NotFoundException {
        Logo logo1= logoUploadMapper.get(logo.getLogoId());
        if(logo1 == null){
            throw new NotFoundException(404,404,"this logo does not exist");
        }
        logoUploadMapper.update(logo);
        return logo;
    }

    @Override
    public Logo add(Logo entity) {
        logoUploadMapper.add(entity);
        return entity;
    }


}
