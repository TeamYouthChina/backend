package com.youthchina.Qingyang;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PdfTestByArea {

    @Test
    public void testPageSize() throws IOException {
        File file = new File("src/test/resources/Resume/夏锐思中文简历.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String s = stripper.getText(document);
        //System.out.println(s);

        Splitter splitter = new Splitter();
        List<PDDocument> pages = splitter.split(document);
        System.out.println(pages.size());
        Assert.assertEquals(1, pages.size());
    }
}
