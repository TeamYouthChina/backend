package com.youthchina.controller;

import com.youthchina.dto.Response;
import com.youthchina.exception.zhongyang.exception.NotFoundException;
import com.youthchina.service.DomainCRUDService;
import com.youthchina.util.HasId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by zhongyangwu on 11/21/18.
 */
public abstract class DomainCRUDController<T extends HasId<K>, K extends Serializable> {

    /**
     * @return DomainCRUDService to access the domain model
     */
    protected abstract DomainCRUDService<T, K> getService();


    /**
     * @param key key of domain model
     * @return 200 if found
     * @throws NotFoundException cannot find domain model based on the key
     */
    protected ResponseEntity<?> get(K key) throws NotFoundException {
        T t = getService().get(key);
        return ResponseEntity.ok(new Response(t));
    }

    /**
     * @param domain Data Transfer Model
     * @return 200 if updated
     * @throws NotFoundException cannot find domain model based on the key
     */
    protected ResponseEntity<?> update(T domain) throws NotFoundException {
        T updatedT = getService().update(domain);
        return ResponseEntity.ok(new Response(updatedT));
    }

    /**
     * @param key primary key of domain model
     * @return 200 if success
     * @throws NotFoundException cannot find domain model based on the key
     */
    protected ResponseEntity<?> delete(K key) throws NotFoundException {
        getService().delete(key);
        return ResponseEntity.ok(new Response());
    }

    /**
     * @param domain data transfer model
     * @return 201 if added
     */
    protected ResponseEntity<?> add(T domain) throws NotFoundException {
        T created = getService().add(domain);
        try {
            return ResponseEntity.created(getUriForNewInstance(created.getId())).body(new Response(created));
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * @param id primary key of domain model
     * @return URI which can be used to access the domain model with primary key equals to k
     */
    abstract protected URI getUriForNewInstance(K id) throws URISyntaxException;
}