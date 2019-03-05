package com.youthchina.dto.community;

import java.util.ArrayList;
import java.util.List;

public class ResponseCommentDTO {
    private List<CommentDTO> comments;

    public ResponseCommentDTO(){
        comments = new ArrayList<>();
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
