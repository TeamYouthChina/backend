package com.youthchina.service.tianjian;


import java.io.File;
import java.net.URL;
import java.util.List;

public interface StaticFileSystemService {
    public long uploadFile(String fileName);

    public URL downloadFile(String fileName);

    public boolean verifyFile(String fileName);

    public void deleteFile(List<String> fileName);
}
