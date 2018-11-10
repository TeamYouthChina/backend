package com.youthchina.service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhongyangwu on 11/10/18.
 */
public interface DomainCRUDService<T, K extends Serializable> {
    /**
     * @param id id of domain
     * @return target
     */
    T get(K id);

    List<T> get(List<K> id);

    void delete(K id);

    T update(T t);

    T add(T t);

}
