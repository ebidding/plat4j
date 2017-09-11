/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月24日 下午8:33:05
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月24日下午8:33:05		WGY			Create file
=========================================================================
 */

package org.net.plat4j.pdf.signature.model;

import java.util.List;

import org.apache.pdfbox.text.TextPosition;

/**
 * @author WGY 2017年3月24日
 *
 */
public class TextPositionSequence implements CharSequence {

	private List<TextPosition> textPositions;
	private int start, end, page;

	public TextPositionSequence(List<TextPosition> textPositions, int page) {
		this(textPositions, 0, textPositions.size(), page);
	}

	public TextPositionSequence(List<TextPosition> textPositions, int start, int end, int page) {
		this.textPositions = textPositions;
		this.start = start;
		this.end = end;
		this.page = page;
	}

	@Override
	public int length() {
		return end - start;
	}

	@Override
	public char charAt(int index) {
		TextPosition textPosition = textPositionAt(index);
		String text = textPosition.getUnicode();
		return text.charAt(0);
	}

	@Override
	public TextPositionSequence subSequence(int start, int end) {
		return new TextPositionSequence(textPositions, this.start + start, this.start + end, page);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(length());
		for (int i = 0; i < length(); i++) {
			builder.append(charAt(i));
		}
		return builder.toString();
	}

	public TextPosition textPositionAt(int index) {
		return textPositions.get(start + index);
	}

	public float getX() {
		return textPositions.get(start).getEndX();
	}

	public float getY() {
		return textPositions.get(start).getEndY();
	}

	public int getPage() {
		return this.page;
	}

}