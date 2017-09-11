/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月24日 下午9:26:50
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月24日下午9:26:50		WGY			Create file
=========================================================================
 */

package org.net.plat4j.pdf.signature.utils;

/*
 Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
 All rights reserved.

 Author: WGY
 Version: 1.0
 Created Time: 2017年3月24日 下午8:35:10

 Revision History:
 Version          Date               Author			Comments
 1.0         	2017年3月24日下午8:35:10		WGY			Create file
 =========================================================================
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import org.net.plat4j.pdf.signature.model.TextPositionSequence;

/**
 * @author WGY 2017年3月24日
 *
 */
public class PdfSearchUtil {
	public static List<TextPositionSequence> getTextPosition(String filePath, String text) throws IOException {
		PDDocument document = PDDocument.load(new File(filePath));
		int allPages = document.getNumberOfPages();
		List<TextPositionSequence> findSubwords = new ArrayList<TextPositionSequence>();
		for (int i = 0; i <= allPages; i++) {
			findSubwords.addAll(findSubwords(document, i, text));
		}

		return findSubwords;
	}

	public static List<TextPositionSequence> findSubwords(PDDocument document,final int page,
			final String searchTerm)
			throws IOException {
		final List<TextPositionSequence> hits = new ArrayList<TextPositionSequence>();
		PDFTextStripper stripper = new PDFTextStripper() {
			@Override
			protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
				TextPositionSequence word = new TextPositionSequence(textPositions, page);
				String string = word.toString();
				int fromIndex = 0;
				int index;
				while ((index = string.indexOf(searchTerm, fromIndex)) > -1) {
					hits.add(word.subSequence(index, index + searchTerm.length()));
					fromIndex = index + 1;
				}
				super.writeString(text, textPositions);
			}
		};

		stripper.setSortByPosition(true);
		stripper.setStartPage(page);
		stripper.setEndPage(page);
		stripper.getText(document);
		return hits;
	}
}
