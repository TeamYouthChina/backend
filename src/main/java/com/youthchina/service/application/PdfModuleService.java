package com.youthchina.service.application;

import java.io.File;
import java.io.IOException;

public interface PdfModuleService {
    public String[] getParagraphOfPdf(File file) throws IOException;
}
