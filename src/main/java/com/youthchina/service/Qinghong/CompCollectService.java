package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.qingyang.Company_qingyang;
import com.youthchina.service.DomainCRUDService;

import java.util.List;

public interface CompCollectService extends DomainCRUDService<Company_qingyang,Integer> {
    public List<Company_qingyang> getAllCompCollect(Integer stu_id);
    public CompCollect addOneCompCollect(Integer stu_id, Integer comp_id);
}
