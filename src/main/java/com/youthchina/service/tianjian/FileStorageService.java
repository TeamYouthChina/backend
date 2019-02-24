package com.youthchina.service.tianjian;


import com.youthchina.dao.tianjian.StaticFileSystemMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.List;

@Service
public interface FileStorageService {

    public URL downloadFile(String fileName);

    public boolean verifyFile(String fileName);

    public void deleteFile(List<String> fileName);

    void uploadFile(File file,Long id);
}
