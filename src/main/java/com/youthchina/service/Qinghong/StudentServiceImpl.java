package com.youthchina.service.Qinghong;

import com.youthchina.domain.Qinghong.Student;
import com.youthchina.exception.zhongyang.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: youthchina
 * @description: 学生实体Service层实现
 * @author: Qinghong Wang
 * @create: 2018-11-29 17:11
 **/
@Service
public class StudentServiceImpl implements StudentService{
    @Override
    public Student get(Integer id) throws NotFoundException {
        return null;
    }

    @Override
    public List<Student> get(List<Integer> id) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(Integer id) throws NotFoundException {

    }

    @Override
    public Student update(Student student) throws NotFoundException {
        return null;
    }

    @Override
    public Student add(Student entity) {
        return null;
    }
}
