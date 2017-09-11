/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年3月24日 下午6:02:50
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年3月24日下午6:02:50		WGY			Create file
=========================================================================
*/

package org.net.plat4j.pdf.signature.model;

/**
 * @author WGY 2017年3月24日
 *
 */
public class PDFTextLocation {
	private boolean found;
	private float x;
	private float y;
	private int page;
	private String text;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isFound() {
		return found;
	}
	public void setFound(boolean found) {
		this.found = found;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
}
