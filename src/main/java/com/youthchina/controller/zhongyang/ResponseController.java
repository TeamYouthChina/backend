package com.youthchina.controller.zhongyang;

import com.youthchina.dto.Response;
import com.youthchina.dto.ResponseDTO;
import com.youthchina.dto.StatusDTO;
import com.youthchina.util.zhongyang.HasId;

import java.io.Serializable;

/**
 * Created by zhongyangwu on 1/29/19.
 */
abstract class ResponseController<DTO, T extends HasId<K>, K extends Serializable> {
    /**
     * @param dto       dto object that set to content field
     * @param statusDTO status object that set to status field
     * @return response with single object
     * @see StatusDTO
     * @see DomainCRUDController
     */
    protected ResponseDTO DtoToResponse(DTO dto, StatusDTO statusDTO) {

        return new Response(dto, statusDTO);
    }

    /**
     * @param dto     dto object that set to content field
     * @param status  custom status code
     * @param message reason for status code
     * @return response with single object
     * @see StatusDTO
     */
    protected ResponseDTO DtoToResponse(DTO dto, int status, String message) {
        return new Response(dto, status, message);
    }

    /**
     * @param dto dto object that set to content field
     * @return response with single object
     */
    protected ResponseDTO DtoToResponse(DTO dto) {
        return new Response(dto);
    }

    protected ResponseDTO DtoToReponse(StatusDTO statusDTO) {
        return new Response(null, statusDTO);
    }

    protected ResponseDTO DtoToResponse(int status, String message) {
        return new Response(null, status, message);
    }

    protected ResponseDTO DtoToResponse() {
        return new Response();
    }
}
