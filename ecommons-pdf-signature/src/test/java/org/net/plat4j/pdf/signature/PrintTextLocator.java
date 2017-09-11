/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月24日 下午6:00:34
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月24日下午6:00:34		WGY			Create file
=========================================================================
 */

package org.net.plat4j.pdf.signature;

/**
 * @author WGY 2017年3月24日
 *
 */
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;

import org.net.plat4j.pdf.signature.model.PDFTextLocation;

public class PrintTextLocator extends PDFTextStripper {

	private final Set<PDFTextLocation> locations;

	public PrintTextLocator(PDDocument document, Set<PDFTextLocation> locations) throws IOException {
		super.setSortByPosition(true);
		this.document = document;
		this.locations = locations;
		this.output = new Writer() {
			@Override
			public void write(char[] cbuf, int off, int len) throws IOException {
			}

			@Override
			public void flush() throws IOException {
			}

			@Override
			public void close() throws IOException {
			}
		};
	}

	public Set<PDFTextLocation> doSearch() throws IOException {

//		processPage(document.getDocumentCatalog().getPages());
		return locations;
	}

	@Override
	protected void writeString(String text, List<TextPosition> textPositions) throws IOException {
		super.writeString(text);

		String searchText = text.toLowerCase();
		for (PDFTextLocation textLoc : locations) {
			int start = searchText.indexOf(textLoc.getText().toLowerCase());
			if (start != -1) {
				// found
				TextPosition pos = textPositions.get(start);
				textLoc.setFound(true);
				textLoc.setPage(getCurrentPageNo());
				textLoc.setX(pos.getXDirAdj());
				textLoc.setY(pos.getYDirAdj());
			}
		}

	}

}
