package com.youthchina.dto.community.comment;

import com.youthchina.domain.jinhao.Comment;
import com.youthchina.dto.ResponseDTO;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

public class CommentResponseDTO implements ResponseDTO<Comment> {
    private List<CommentDTO> comments;

    public CommentResponseDTO() {
        comments = new ArrayList<>();
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    @Override
    public void convertToDTO(Comment domain) {

    }
}
