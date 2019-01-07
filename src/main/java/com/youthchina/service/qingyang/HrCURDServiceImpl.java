package com.youthchina.service.qingyang;

import com.youthchina.domain.qingyang.Hr;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HrCURDServiceImpl implements HrCURDService{
    //TODO!!!!

    @Override
    public Hr get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Hr> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Hr update(Hr hr_) throws NotFoundException {
        return null;
    }

    @Override
    public Hr add(Hr entity) {
        return null;
    }
}
