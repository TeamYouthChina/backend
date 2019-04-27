package com.youthchina.service;

import com.youthchina.exception.zhongyang.exception.NotFoundException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
public interface DomainCRUDService<T, K extends Serializable> {
    /**
     * @param id id of domain
     * @return target
     */
    T get(K id) throws NotFoundException;

    /**
     * @param id list of id
     * @return target
     */
    default List<T> get(List<K> id) {
        List<T> res = new ArrayList<>();
        for (K i : id) {
            try {
                res.add(this.get(i));
            } catch (NotFoundException ignore) {
            }
        }
        return res;
    }

    /**
     * @param id id
     * @return <p>delete entity with target id</p>
     */
    void delete(K id) throws NotFoundException;

    /**
     * @param
     * @return <p>update id</p>
     */
    T update(T t) throws NotFoundException;

    /**
     * @param entity target
     *               <p>add Entity</p>
     */
    T add(T entity) throws NotFoundException;

    default boolean exist(K id) {
        try {
            this.get(id);
            return true;
        } catch (NotFoundException e) {
            return false;
        }
    }

}
