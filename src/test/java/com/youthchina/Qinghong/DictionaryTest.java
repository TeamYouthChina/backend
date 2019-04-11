package com.youthchina.Qinghong;

import com.youthchina.dao.Qinghong.DictionaryMapper;
import com.youthchina.domain.Qinghong.Entry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: youthchina
 * @description: 字典表测试
 * @author: Qinghong Wang
 * @create: 2019-04-10 14:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@WebAppConfiguration
public class DictionaryTest {
    @Autowired
    private DictionaryMapper dictionaryMapper;

    @Test
    public void getLocation(){
        List<Entry> locations=dictionaryMapper.getLocation(2,4400000);
        System.out.print(locations);
    }

    @Test
    public void getMajorAbbre(){
        List<Entry> entries=dictionaryMapper.getMajorByAbbre();
        System.out.print(entries);

    }

    @Test
    public void getMajorFull(){
        List<Entry> entries=dictionaryMapper.getMajorByFull();
        System.out.print(entries);
    }

    @Test
    public void getDegree(){
        List<Entry> entries=dictionaryMapper.getDegree();
        System.out.print(entries);

    }

    @Test
    public void getDiploma(){
        List<Entry> entries=dictionaryMapper.getDiploma();
        System.out.print(entries);
    }

    @Test
    public void getSkill(){
        List<Entry> entries=dictionaryMapper.getAdvantageSkill();
        System.out.print(entries);
    }

    @Test
    public void getIndustry(){
        List<Entry> entries=dictionaryMapper.getIndustry();
        System.out.print(entries);
    }

    @Test
    public void getJob(){
        List<Entry> entries=dictionaryMapper.getJob();
        System.out.print(entries);
    }

    @Test
    public void getCompanyScale(){
        List<Entry> entries=dictionaryMapper.getCompanyScale();
        System.out.print(entries);
    }

    @Test
    public void getCompanyNature(){
        List<Entry> entries=dictionaryMapper.getCompanyNature();
        System.out.print(entries);
    }

    @Test
    public void getCountry(){
        List<Entry> entries=dictionaryMapper.getCountry();
        System.out.print(entries);
    }

    @Test
    public void getChinaRegionAbbre(){
        List<Entry> entries=dictionaryMapper.getChinaRegionByAbbre();
        System.out.print(entries);
    }

    @Test
    public void getChinaRegionFull(){
        List<Entry> entries=dictionaryMapper.getChinaRegionByFull();
        System.out.print(entries);
    }

    @Test
    public void getUSASTATEAbbre(){
        List<Entry> entries=dictionaryMapper.getUSAStateByAbbre();
        System.out.print(entries);
    }

    @Test
    public void getUSASTATEFull(){
        List<Entry> entries=dictionaryMapper.getUSAStateByFull();
        System.out.print(entries);
    }

    @Test
    public void getUSARegion(){
        List<Entry> entries=dictionaryMapper.getUSARegion();
        System.out.print(entries);
    }

    @Test
    public void getCHNUniversity(){
        List<Entry> entries=dictionaryMapper.getCHNUniversity();
        System.out.print(entries);
    }

    @Test
    public void getUSAUniversity(){
        List<Entry> entries=dictionaryMapper.getUSAUniversity();
        System.out.print(entries);
    }

    @Test
    public void getGBRUniversity(){
        List<Entry> entries=dictionaryMapper.getGBRUniversity();
        System.out.print(entries);
    }

    @Test
    public void getCANUniversity(){
        List<Entry> entries=dictionaryMapper.getCANUniversity();
        System.out.print(entries);
    }

    @Test
    public void getTimesRank(){
        List<Entry> entries=dictionaryMapper.getTIMESRank();
        System.out.print(entries);
    }

    @Test
    public void getUSNEWSRank(){
        List<Entry> entries=dictionaryMapper.getUSNEWSRank();
        System.out.print(entries);
    }

    @Test
    public void getQSrank(){
        List<Entry> entries=dictionaryMapper.getQSRank();
        System.out.print(entries);
    }

    @Test
    public void getTopCompany(){
        List<Entry> entries=dictionaryMapper.getTopCompany();
        System.out.print(entries);
    }
    @Test
    public void getUniversity(){
        List<Entry> entries=dictionaryMapper.getUniversity();
        System.out.print(entries);
    }

}
