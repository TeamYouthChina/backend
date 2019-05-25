package com.youthchina.service.util;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.util.dictionary.HasOwnerEntityType;
import com.youthchina.util.permission.HasOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by zhongyangwu on 5/25/19.
 */
@Service
public class OwnerService {


    private final CommunityMapper communityMapper;

    @Autowired
    public OwnerService(CommunityMapper communityMapper) {
        this.communityMapper = communityMapper;
    }

    @Nullable
    public HasOwner getEntityByOwnerId(@Nonnull Integer id, String entityType) {
        Integer ownerId = null;
        if (entityType.equals(HasOwnerEntityType.ARTICLE)) {
            ownerId = this.communityMapper.getOwnerId(id);
        }
        //default
        if (ownerId == null) {
            return null;
        } else {
            final Integer returnId = ownerId; //must be final
            return () -> returnId;
        }

    }
}
