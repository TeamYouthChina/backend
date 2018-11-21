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

    /**
     * @param id list of id
     * @return target
     */
    List<T> get(List<K> id);

    /**
     * @param id id
     * @return
     * <p>delete entity with target id</p>
     */
    void delete(K id);

    /**
     * @param id id
     * @return
     * <p>update id</p>
     */
    T update(T t);

    /**
     * @param entity target
     * <p>add Entity</p>
     * */
    T add(T entity);

}
