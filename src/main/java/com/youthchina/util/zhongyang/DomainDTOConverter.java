package com.youthchina.util.zhongyang;

import org.springframework.stereotype.Component;

/**
 * Created by zhong on 2018/12/30.
 */
public interface DomainDTOConverter<Domain, DTO> {
    DTO domain2Dto(Domain domain);

    Domain dto2Domain(DTO dto);
}
