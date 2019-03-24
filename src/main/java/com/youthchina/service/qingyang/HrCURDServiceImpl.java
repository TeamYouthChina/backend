package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.HrMapper;
import com.youthchina.domain.qingyang.Hr;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HrCURDServiceImpl implements HrCURDService {
    @Resource
    HrMapper hrMapper;

    /**
     * 搜索Hr
     *
     * @param id Hr Id
     * @return Hr类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Hr get(Integer id) throws NotFoundException {
        return hrMapper.selectHrById(id);
    }

    /**
     * 搜索Hr
     *
     * @param id Hr ID List
     * @return Hr类 List
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public List<Hr> get(List<Integer> id) throws NotFoundException {
        return hrMapper.selectHrByIdList(id);
    }

    /**
     * 删除Hr
     *
     * @param id id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        hrMapper.deleteHr(id);
    }

    /**
     * 更新Hr
     *
     * @param hr Hr类
     * @return 更新后的Hr类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Hr update(Hr hr) throws NotFoundException {
        hrMapper.updateHr(hr);
        return hrMapper.selectHrById(hr.getHrId());
    }

    /**
     * 添加Hr
     *
     * @param entity Hr类
     * @return 添加后的Hr类
     */
    @Override
    @Transactional
    public Hr add(Hr entity) {
        hrMapper.insertHr(entity);
        return hrMapper.selectHrById(entity.getHrId());
    }
}
