package com.youthchina.dto.solr;

import org.apache.solr.client.solrj.beans.Field;

public class SolrDTO {

    @Field(value="id")
    private Integer id;

    public void setId(Integer id){this.id =id;}

    public Integer getId(){ return this.id; }
}
