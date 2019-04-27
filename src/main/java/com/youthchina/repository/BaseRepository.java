package com.youthchina.repository;

import java.io.Serializable;

/**
 * Created by zhongyangwu on 4/14/19.
 */
public interface BaseRepository<I extends Serializable, E> {
    default void delete(I id) {
        this.delete(this.get(id));
    }

    E get(I id);

    void delete(E entity);
}
