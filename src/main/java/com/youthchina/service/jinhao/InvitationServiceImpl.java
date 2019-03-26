package com.youthchina.service.jinhao;

import com.youthchina.domain.jinhao.Invitation;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    @Override
    public Invitation get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Invitation> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Invitation update(Invitation invitation) throws NotFoundException {
        return null;
    }

    @Override
    public Invitation add(Invitation entity) throws NotFoundException {
        return null;
    }
}
