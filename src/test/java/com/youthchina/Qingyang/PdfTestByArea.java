package com.youthchina.Qingyang;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfTestByArea {

    public static void main(String[] args) throws IOException {
        File file = new File("src/test/resources/Resume/夏锐思中文简历.pdf");
        PDDocument document = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String s = stripper.getText(document);
        //System.out.println(s);

        Splitter splitter = new Splitter();
        List<PDDocument> pages = splitter.split(document);
        System.out.println(pages.size());
    }
}
