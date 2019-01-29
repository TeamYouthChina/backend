package com.youthchina.service.tianjian;


import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.List;

@Service("StaticFileSystemService")
public interface StaticFileSystemService {
    public long uploadFile(String fileName, File file,String format);

    public URL downloadFile(String fileName);

    public boolean verifyFile(String fileName);

    public void deleteFile(List<String> fileName);
}
