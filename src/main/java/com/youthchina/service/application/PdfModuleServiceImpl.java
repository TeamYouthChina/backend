package com.youthchina.service.application;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class PdfModuleServiceImpl implements PdfModuleService {
    /**
     * 按段落读取Pdf, 返回String数组
     *
     * @param file
     * @return
     * @throws IOException
     */
    @Override
    public String[] getParagraphOfPdf(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            AccessPermission ap = document.getCurrentAccessPermission();
            if (!ap.canExtractContent()) {
                throw new IOException("No permission to extract text");
            }
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setLineSeparator("");
            stripper.setParagraphStart("/t");
            stripper.setSortByPosition(true);
            String resume = stripper.getText(document);

            return resume.split(stripper.getParagraphStart());
        }
    }
}
