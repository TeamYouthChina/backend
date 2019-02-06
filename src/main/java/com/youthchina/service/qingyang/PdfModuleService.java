package com.youthchina.service.qingyang;

import java.io.File;
import java.io.IOException;

public interface PdfModuleService {
    public String[] getParagraphOfPdf(File file) throws IOException;
}
