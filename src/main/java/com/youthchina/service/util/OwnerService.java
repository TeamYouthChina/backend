package com.youthchina.service.util;

import com.youthchina.dao.tianjian.CommunityMapper;
import com.youthchina.util.dictionary.HasOwnerEntityType;
import com.youthchina.util.permission.HasOwner;
import com.youthchina.util.permission.HasOwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongyangwu on 5/25/19.
 */
@Service
public class OwnerService {

    private final Map<String, HasOwnerMapper> mappers;

    @Autowired
    public OwnerService(CommunityMapper communityMapper) {
        HashMap<String, HasOwnerMapper> mappers = new HashMap<>();
        mappers.put(HasOwnerEntityType.ARTICLE, communityMapper);
        this.mappers = Collections.unmodifiableMap(mappers); // finalize the content of the mapper
    }

    @Nullable
    public HasOwner getEntityByOwnerId(@Nonnull Integer id, String entityType) {
        if (!this.mappers.containsKey(entityType)) {
            return null;
        } else {
            return () -> this.mappers.get(entityType).getOwnerId(id);
        }

    }
}
