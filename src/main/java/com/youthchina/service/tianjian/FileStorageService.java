package com.youthchina.service.tianjian;


import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.List;

@Service
public interface FileStorageService {

    public URL downloadFile(String fileName);

    public boolean verifyFile(String fileName);

    public void deleteFile(List<String> fileName);

    void uploadFile(Resource file, Long id);
}
