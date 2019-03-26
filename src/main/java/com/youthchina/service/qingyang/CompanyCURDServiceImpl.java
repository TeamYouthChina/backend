package com.youthchina.service.qingyang;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.Company;
import com.youthchina.domain.qingyang.CompanyPhoto;
import com.youthchina.domain.qingyang.Industry;
import com.youthchina.domain.qingyang.Logo;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Resource
    JobMapper jobMapper;


    @Autowired
    LocationServiceImpl locationServiceImpl;

    /**
     * 公司搜索
     *
     * @param id 公司Id
     * @return 公司类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Company get(Integer id) throws NotFoundException {
        Company company = companyMapper.selectCompany(id);
        setCompanyLocation(company);
        return company;
    }

    /**
     * 查询Company的Location (因为是字典表, 所以没加事务)
     *
     * @param company
     */
    private void setCompanyLocation(Company company) {
        Location location = company.getLocation();
        if (location != null) {
            company.setLocation(locationServiceImpl.getLocation(location.getRegionNum()));
        }
    }

    /**
     * 公司搜索
     *
     * @param id 公司Id List
     * @return 公司类 List
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public List<Company> get(List<Integer> id) throws NotFoundException {
        List<Company> companyList = companyMapper.selectCompanyByIdList(id);
        for (Company company : companyList) {
            setCompanyLocation(company);
        }
        return companyList;
    }

    /**
     * 删除公司
     *
     * @param comId 公司Id
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public void delete(Integer comId) throws NotFoundException {
        jobMapper.deleteJobByComId(comId);
        //hrMapper.deleteHrByComId(comId); // No Hr anymore
        companyMapper.deleteCompanyVerificationByComId(comId);
        companyMapper.deleteCompanyEmployee(comId);
        companyMapper.deleteCompanyEvaluate(comId);
        companyMapper.deleteCompanyPhoto(comId);
        companyMapper.deleteStudentComCollection(comId);
        companyMapper.deleteCompanyInd(comId);
        companyMapper.deleteCompany(comId);
    }

    /**
     * 更新公司
     *
     * @param company 公司类
     * @return 更新后的公司类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Company update(Company company) throws NotFoundException {
        Integer result = companyMapper.updateCompany(company);
        companyMapper.deleteCompanyInd(company.getCompanyId());
        companyMapper.deleteCompanyLogo(company.getCompanyId());
        List<Industry> industryList = company.getIndList();
        if (industryList != null && industryList.size() > 0) {
            companyMapper.insertCompanyInd(company.getId(), industryList);
        }
        List<Logo> logoList = company.getLogoList();
        if(logoList != null && logoList.size() > 0){
            companyMapper.insertCompanyLogo(company.getId(), logoList);
        }
        List<CompanyPhoto> photoList = company.getPhotoList();
        if(photoList != null && photoList.size() > 0){
            companyMapper.insertCompanyPhoto(company.getId(), photoList);
        }
        Company companyResult = companyMapper.selectCompany(company.getCompanyId());
        setCompanyLocation(companyResult);
        return companyResult;
    }

    /**
     * 添加公司
     *
     * @param entity 公司类
     * @return 添加后的公司类
     */
    @Override
    @Transactional
    public Company add(Company entity) {
        Integer result = companyMapper.insertCompany(entity);
        List<Industry> industryList = entity.getIndList();
        if (industryList != null && industryList.size() > 0) {
            companyMapper.insertCompanyInd(entity.getId(), industryList);
        }
        List<Logo> logoList = entity.getLogoList();
        if(logoList != null && logoList.size() > 0){
            companyMapper.insertCompanyLogo(entity.getId(), logoList);
        }
        List<CompanyPhoto> photoList = entity.getPhotoList();
        if(photoList != null && photoList.size() > 0){
            companyMapper.insertCompanyPhoto(entity.getId(), photoList);
        }
        Company companyResult = companyMapper.selectCompany(entity.getCompanyId());
        setCompanyLocation(companyResult);
        return companyResult;
    }


    /**
     * 添加和更新公司时, 添加行业的关联
     *
     * @param company
     */
    @Transactional
    public void addInd(Company company) {
        companyMapper.insertCompanyInd(company.getId(), company.getIndList());
    }

    /**
     * 更新和删除公司时, 删除行业的关联
     *
     * @param company
     * @throws NotFoundException
     */
    @Transactional
    public void deleteInd(Company company) throws NotFoundException {
        companyMapper.deleteCompanyInd(company.getCompanyId());
    }

    /**
     * 公司搜索 : 模糊搜索
     *
     * @param comName 公司名称
     * @return 公司List
     */
    @Override
    @Transactional
    public List<Company> getByName(String comName) {
        List<Company> companyList = companyMapper.selectCompanyByName(comName);
        for (Company company : companyList) {
            setCompanyLocation(company);
        }
        return companyList;
    }
}
