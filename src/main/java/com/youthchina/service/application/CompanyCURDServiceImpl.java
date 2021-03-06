package com.youthchina.service.application;

import com.youthchina.dao.qingyang.CompanyMapper;
import com.youthchina.dao.qingyang.JobMapper;
import com.youthchina.domain.Qinghong.Location;
import com.youthchina.domain.qingyang.*;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.util.StaticFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ArrayList;
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

    @Autowired
    StaticFileService staticFileServiceImpl;

    /**
     * 通过公司Id获取公司
     *
     * @param id 公司Id
     * @return 公司类
     * @throws NotFoundException
     */
    @Override
    @Transactional
    public Company get(Integer id) throws NotFoundException {
        Company company = companyMapper.selectCompany(id);
        if (company == null) throw new NotFoundException(4040, 404, "No such company");
        setCompanyLocation(company);
        setCompanyLogo(company);
        setCompanyPhoto(company);
        company.setJobCount(countJobByComId(id));
        return company;
    }

    /**
     * 设置该公司的的Logo (DocId)
     * @param company
     */
    private void setCompanyLogo(Company company) {
        List<Logo> logoList = company.getLogoList();
        if (logoList != null && logoList.size() > 0) {
            for (Logo logo : logoList) {//TODO : 中美服务器?
//               String aliId = staticFileSystemMapper.getFileInfo(logo.getDocuLocalId()).getDocu_server_ali_id();
                URL url = (staticFileServiceImpl.getFileUrl(logo.getDocuLocalId(), "China"));
                logo.setUrl(url != null ? url.toString() : null);
            }
        }
    }

    /**
     * 插入该公司的 PhotoList (DocId)
     * @param company
     */
    private void setCompanyPhoto(Company company) {
        List<CompanyPhoto> photoList = company.getPhotoList();

        if (photoList != null && photoList.size() > 0) {
            for (CompanyPhoto photo : photoList) {//TODO : 中美服务器?
                URL url = staticFileServiceImpl.getFileUrl(photo.getDocuLocalId(), "China");
                photo.setUrl(url != null ? url.toString() : null);
            }
        }
    }

    /**
     * 查询Company的Location (因为是字典表, 所以没加事务)
     *
     * @param company
     */
    private void setCompanyLocation(Company company) {
        Location location = company.getLocation();
        if (location != null) {
            company.setLocation(locationServiceImpl.getLocation(location.getRegionId()));
        }
    }

    /**
     * 通过Id List, 返回公司 List
     *
     * @param id 公司Id List
     * @return 公司类 List
     */
    @Override
    @Transactional
    public List<Company> get(List<Integer> id){
        List<Company> companyList = new ArrayList<>();
        for (Integer i : id) {
            try {
                companyList.add(this.get(i));
            } catch (NotFoundException ignore) {

            }
        }
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
        companyMapper.deleteCompanyLogo(comId);
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
        companyMapper.deleteCompanyPhoto(company.getCompanyId());
        addRelatedInfo(company);
        return this.get(company.getCompanyId());
    }

    /**
     * 在添加&更新公司的基本信息后, 插入关联表相关信息
     * @param company
     * @throws NotFoundException
     */
    private void addRelatedInfo(Company company) throws NotFoundException {
        List<Industry> industryList = company.getIndList();
        if (industryList != null && industryList.size() > 0) {
            companyMapper.insertCompanyInd(company.getId(), industryList);
        }
        List<Logo> logoList = company.getLogoList();
        if (logoList != null && logoList.size() > 0) {
            companyMapper.insertCompanyLogo(company.getId(), logoList);
        }
        List<CompanyPhoto> photoList = company.getPhotoList();
        if (photoList != null && photoList.size() > 0) {
            companyMapper.insertCompanyPhoto(company.getId(), photoList);
        }

    }

    /**
     * 添加公司
     *
     * @param entity 公司类
     * @return 添加后的公司类
     */
    @Override
    @Transactional
    public Company add(Company entity) throws NotFoundException {
        Integer result = companyMapper.insertCompany(entity);
        addRelatedInfo(entity);
        return this.get(entity.getCompanyId());
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
     * 公司搜索 : 通过公司名称 模糊搜索
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

    /**
     * 获取数据库所有公司, (本来暂时为推荐提供数据)
     * @return
     */
    @Transactional
    public List<Company> getAll() {
        List<Company> companyList = companyMapper.selectAllCompany();
        for (Company company : companyList) {
            setCompanyLocation(company);
        }
        return companyList;
    }

    /**
     * 统计该公司下有多少职位 (未对DeadLine 进行判断)
     * @param companyId
     * @return
     */
    @Transactional
    public Integer countJobByComId(Integer companyId) {
        Integer jobCount = jobMapper.countJobByComId(companyId);
        return jobCount;
    }

    /**
     * 判断该User 是否收藏了该公司
     * @param companyId
     * @param userId
     * @return
     */
    @Override
    public Boolean isCollected(Integer companyId, Integer userId) {
        Integer result = companyMapper.isCollected(companyId, userId);
        return result != null;
    }

    /**
     * 通过公司Id获取 公司, 并判断是否被该User收藏
     * @param companyId
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @Override
    public Company getCompanyWithCollected(Integer companyId, Integer userId) throws NotFoundException {
        Company company = this.get(companyId);
        company.setCollected(isCollected(companyId, userId));
        return company;
    }

    /**
     * 通过公司Id获取下属职位
     * @param companyId
     * @param userId
     * @return
     * @throws NotFoundException
     */
    @Override
    public List<Job> getJobsByCompanyId(Integer companyId, Integer userId) throws NotFoundException{
        List<Job> jobList = jobMapper.selectJobByComId(companyId);
        for(Job job : jobList){
           Integer result = jobMapper.isCollect(job.getJobId(), userId);
           job.setCollected(result != null);
        }
        return jobList;
    }

}
