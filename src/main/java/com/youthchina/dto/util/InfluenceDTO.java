package com.youthchina.dto.util;

public class InfluenceDTO {

    public Integer getInfluence_score() {
        return influence_score;
    }

    public void setInfluence_score(Integer influence_score) {
        this.influence_score = influence_score;
    }

    private Integer influence_score;

    public InfluenceDTO(Integer score){
        influence_score = score;
    }
}
