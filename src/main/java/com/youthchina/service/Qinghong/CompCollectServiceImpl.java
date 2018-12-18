package com.youthchina.service.Qinghong;

import com.youthchina.dao.Qinghong.StudentMapper;
import com.youthchina.domain.Qinghong.CompCollect;
import com.youthchina.domain.qingyang.Company_qingyang;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: youthchina
 * @description: 公司收藏的Service层具体实现
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:15
 **/
public class CompCollectServiceImpl implements CompCollectService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public Company_qingyang get(Integer id) throws NotFoundException {

        return null;
    }

    @Override
    public List<Company_qingyang> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Company_qingyang update(Company_qingyang company_qingyang) throws NotFoundException {
        return null;
    }

    @Override
    @Deprecated
    public Company_qingyang add(Company_qingyang entity) {
        return null;
    }
    /**
    * @Description: 通过学生id获得该id下所有收藏的公司的具体信息
    * @Param: [stu_id]
    * @return: java.util.List<com.youthchina.domain.qingyang.Company_qingyang>
    * @Author: Qinghong Wang
    * @Date: 2018/11/29
    */
    public List<Company_qingyang> getAllCompCollect(Integer stu_id){
        List<CompCollect> compCollects=studentMapper.getAllCompCollect(stu_id);
        List<Integer> key=new ArrayList<>();
        for(CompCollect item:compCollects){
            key.add(item.getCompany_id());
        }
        return studentMapper.getAllCompCollect(key);
    }

    /**
    * @Description: 收藏一个公司
    * @Param: [stu_id, comp_id]
    * @return: void
    * @Author: Qinghong Wang
    * @Date: 2018/11/29
    */
    public CompCollect addOneCompCollect(Integer stu_id,Integer comp_id){
        CompCollect compCollect=new CompCollect();
        if (studentMapper.getOneCompCollect(stu_id,comp_id)==null){
            compCollect.setCompany_id(comp_id);
            compCollect.setStu_id(stu_id);
            compCollect.setCompany_coll_time(new Date());
            studentMapper.addOneCompCollect(compCollect);
        }
        return  compCollect;

    }

}
