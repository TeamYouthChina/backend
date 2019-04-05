package com.youthchina.service.tianjian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;

@Service("localFileManage")
public class LocalFileManage {
    private File file;
    private String localfilePath;
    @Autowired
    FileNameGenerate fileNameGenerate;

//    public LocalFileManage(@Value("${localfilemanage.localfilepath}")String localfilePath){
//        this.localfilePath = localfilePath;
//        File file = new File(localfilePath);
//        if(!file.exists()){
//            file.mkdir();
//        }
//    }

    public String uploadFileToLocal(File inFile, String format) {
        String localFileName = fileNameGenerate.generateFileName();
        String path = localfilePath + localFileName + format;
        this.file = new File(path);
        try {
            FileInputStream input = new FileInputStream(inFile);
            byte[] inputByte = new byte[input.available()];
            input.read(inputByte);

            FileOutputStream outStream = new FileOutputStream(this.file);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(outStream);
            bufferedOutput.write(inputByte);

            outStream.close();
            input.close();
            bufferedOutput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public boolean deleteLocalFile(String localFilePath) {
        File file = new File(localFilePath);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("Delete File " + localFilePath + "Success！");
                return true;
            } else {
                System.out.println("Delete File" + localFilePath + "Fail！");
                return false;
            }
        } else {
            System.out.println("Delete File Fail:" + localFilePath + "is not exist");
            return false;
        }
    }
}
