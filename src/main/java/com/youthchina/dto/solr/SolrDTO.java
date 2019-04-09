package com.youthchina.dto.solr;

public class SolrDTO {

    private Object obejct;
    private String type;

    public SolrDTO(String type, Object obejct){
        this.obejct = obejct;
        this.type = type;
    }

    public void setObejct(Object obejct){this.obejct =obejct;}

    public Object getObejct(){ return this.obejct; }

    public void setType(String type){this.type =type;}

    public String getType(){ return this.type; }
}
