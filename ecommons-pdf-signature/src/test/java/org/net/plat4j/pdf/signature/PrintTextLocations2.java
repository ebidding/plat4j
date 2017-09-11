package org.net.plat4j.pdf.signature;
/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月24日 下午5:55:32
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月24日下午5:55:32		WGY			Create file
=========================================================================


package org.net.plat4j.pdf.signature;

*//**
 * @author WGY 2017年3月24日
 *
 *//*
import java.io.*;
import org.apache.pdfbox.exceptions.InvalidPasswordException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

import java.io.IOException;
import java.util.List;

public class PrintTextLocations2 extends PDFTextStripper {

    public PrintTextLocations2() throws IOException {
        super.setSortByPosition(true);
    }

    public static void main(String[] args) throws Exception {

        PDDocument document = null;
        try {
            File input = new File("/Users/rudy/keystore/testSignature.pdf");
            document = PDDocument.load(input);
            PrintTextLocations printer = new PrintTextLocations();
            List allPages = document.getDocumentCatalog().getAllPages();
            for (int i = 0; i < allPages.size(); i++) {
                PDPage page = (PDPage) allPages.get(i);
                System.out.println("Processing page: " + i);
                PDStream contents = page.getContents();
                if (contents != null) {
                    printer.processStream(page, page.findResources(), page.getContents().getStream());
                }
            }
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    *//**
     * @param text The text to be processed
     *//*
    @Override  this is questionable, not sure if needed... 
    protected void processTextPosition(TextPosition text) {
        System.out.println("String[" + text.getXDirAdj() + ","
                + text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale="
                + text.getXScale() + " height=" + text.getHeightDir() + " space="
                + text.getWidthOfSpace() + " width="
                + text.getWidthDirAdj() + "]" + text.getCharacter());
    }
}*/