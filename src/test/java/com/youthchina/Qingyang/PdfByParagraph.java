package com.youthchina.Qingyang;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PdfByParagraph {


    public String[] readParagraph(File file) throws IOException {
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setLineSeparator("");
        stripper.setParagraphStart("/t");
        stripper.setSortByPosition(true);
        String resume = stripper.getText(document);

        return resume.split(stripper.getParagraphStart());
    }

    @Test
    public void test() throws IOException {
        String path = "src/test/resources/Resume/夏锐思中文简历.pdf";
        File file = new File(path);
        String[] lines = readParagraph(file);
        if (lines == null || lines.length == 0) {
            Assert.fail();
        }
        for (int i = 0; i < lines.length; i++) {
            System.out.println("line" + i + ":" + lines[i]);
        }
    }
}
