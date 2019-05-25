package com.youthchina.util.permission;

import javax.annotation.Nullable;


/**
 * Created by zhongyangwu on 5/25/19.
 */
public interface HasOwnerMapper {
    @Nullable
    Integer getOwnerId(Integer entityId);
}
