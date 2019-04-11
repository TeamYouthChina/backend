package com.youthchina.dto;

import com.youthchina.domain.Qinghong.Entry;

/**
 * @program: youthchina
 * @description: 字典表DTO
 * @author: Qinghong Wang
 * @create: 2019-04-10 14:59
 **/
public class EntryResponseDTO {
    private String id;
    private String name;

    public EntryResponseDTO() {

    }

    public EntryResponseDTO(Entry entry) {
        this.id=entry.getId();
        this.name=entry.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
