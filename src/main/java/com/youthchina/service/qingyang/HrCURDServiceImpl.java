package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Hr_qingyang;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrCURDServiceImpl implements HrCURDService{
    //TODO!!!!

    @Override
    public Hr_qingyang get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Hr_qingyang> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Hr_qingyang update(Hr_qingyang hr_qingyang) throws NotFoundException {
        return null;
    }

    @Override
    public Hr_qingyang add(Hr_qingyang entity) {
        return null;
    }
}
