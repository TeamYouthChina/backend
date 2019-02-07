package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Company CURD
 */
@Service
public class CompanyCURDServiceImpl implements CompanyCURDService {
    @Resource
    CompanyMapper companyMapper;

    /**
     * 公司搜索
     * @param id 公司Id
     * @return 公司类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Company get(Integer id) throws NotFoundException {
        return companyMapper.selectCompany(id);
    }

    /**
     * 公司搜索
     * @param id 公司Id List
     * @return 公司类 List
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public List<Company> get(List<Integer> id) throws NotFoundException {
        return companyMapper.selectCompanyByIdList(id);
    }

    /**
     * 删除公司 TODO:级联删除
     * @param id 公司Id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public void delete(Integer id) throws NotFoundException {
        companyMapper.deleteCompany(id);
        companyMapper.deleteCompanyInd(id); // CompanyID
    }

    /**
     * 更新公司 TODO:级联更新
     * @param company 公司类
     * @return 更新后的公司类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Company update(Company company) throws NotFoundException {
        Integer result = companyMapper.updateCompany(company);
        return companyMapper.selectCompany(company.getCompanyId());
    }

    /**
     * 添加公司
     * @param entity 公司类
     * @return 添加后的公司类
     */
    @Override
    @Transactional
    public Company add(Company entity) {
        Integer result = companyMapper.insertCompany(entity);
        return companyMapper.selectCompany(entity.getCompanyId());
    }


    /**
     * 添加和更新公司时, 添加行业的关联
     * @param company
     */
    @Transactional
    public void addInd(Company company) {
        companyMapper.insertCompanyInd(company.getIndList());
    }

    /**
     * 更新和删除公司时, 删除行业的关联
     * @param company
     * @throws NotFoundException
     */
    @Transactional
    public void deleteInd(Company company) throws NotFoundException {
        companyMapper.deleteCompanyInd(company.getCompanyId());
    }

    /**
     * 公司搜索 : 模糊搜索
     * @param comName 公司名称
     * @return 公司List
     */
    @Override
    @Transactional
    public List<Company> getByName(String comName) {
        return companyMapper.selectCompanyByName(comName);
    }
}
