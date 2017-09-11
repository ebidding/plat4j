/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: WGY
	Version: 1.0
	Created Time: 2017年5月18日 下午1:45:39
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年5月18日下午1:45:39		WGY			Create file
=========================================================================
*/

package org.net.plat4j.pdf.signature;
import java.io.*;  
import java.security.*;  
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.net.plat4j.pdf.signature.cert.ICertificate;
import org.net.plat4j.pdf.signature.cert.impl.P12Certificate;
import org.net.plat4j.pdf.signature.model.TextPositionSequence;
import org.net.plat4j.pdf.signature.utils.PdfPositionUtil;

import com.itextpdf.text.*; 
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfSignatureAppearance.RenderingMode;
import com.itextpdf.text.pdf.PdfStamper;
/**
 * @author WGY 2017年5月18日
 *
 */
public class signPDF {
	/**
	 * 原文件路径
	 */
	private String pdfFilePath;
	/**
	 * 签章之后文件路径
	 */
	private String signatureFilePath;
	/**
	 * 签章关键字
	 */
	private String keyword;
	/**
	 * 签章图片
	 */
	private String imagePath;
	/**
	 * 签章宽度
	 */
	private int signatureWidth;
	/**
	 * 签章高度
	 */
	private int signatureHeight;
	/**
	 * 证书信息
	 */
	private ICertificate cert;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public signPDF() {
		this.signatureWidth = 100;
		this.signatureHeight = 100;
	}

	public signPDF(String pdfFilePath, String signatureFilePath, String keyword, String imagePath,
			ICertificate cert) {
		this();
		this.pdfFilePath = pdfFilePath;
		this.signatureFilePath = signatureFilePath;
		this.keyword = keyword;
		this.imagePath = imagePath;
		this.cert = cert;
	}
	
	  public  void sign(String inFilePath, String outFilePath, TextPositionSequence position) throws Exception {  
	        PdfReader reader = new PdfReader(inFilePath);   
	        FileOutputStream fout = new FileOutputStream(outFilePath);  
	        PdfStamper stp = PdfStamper.createSignature(reader, fout, '\0', null, true);
	        PdfSignatureAppearance sap = stp.getSignatureAppearance();  
	        sap.setCrypto(cert.getPrivateKey(), cert.getCertificate(), null, PdfSignatureAppearance.VERISIGN_SIGNED);  
	  
	        sap.setSignatureGraphic(Image.getInstance(imagePath));  
	        sap.setAcro6Layers(true);  
	        sap.setRenderingMode(RenderingMode.GRAPHIC);  
	        sap.setVisibleSignature(
					new Rectangle(position.getX(), position.getY() - signatureHeight / 2,
							position.getX() + signatureWidth, position.getY() + signatureHeight / 2),
					position.getPage(), null);
	        stp.getWriter().setCompressionLevel(5);  
	        if (stp != null) {  
	            stp.close();  
	        }  
	        if (fout != null) {  
	            fout.close();  
	        }  
	        if (reader != null) {  
	            reader.close();  
	        }  
	    }  
	  	
	  public void signature() throws Exception {
			File pdfFile = new File(pdfFilePath);
			if (!pdfFile.exists()) {
				throw new PDFSignatureException("pdf文件不存在，文件路径：" + pdfFilePath);
			}
			File signatureFileDir = new File(signatureFilePath).getParentFile();
			if (!signatureFileDir.exists()) {
				signatureFileDir.mkdirs();
			}
			List<TextPositionSequence> positions = PdfPositionUtil.getTextPosition(pdfFilePath, keyword);
			if (positions == null || positions.isEmpty()) { //未找到签章位置，直接复制pdf文件
				logger.info("未找到签章位置，签章文件路径：{}，关键字：{}", pdfFilePath, keyword);
				InputStream input = null;
				OutputStream output = null;
				try {
					input = new FileInputStream(pdfFilePath);
					output = new FileOutputStream(signatureFilePath);
					IOUtils.copy(input, output);
				} catch (IOException e) {
					String message = "复制文件出错，原文件路径：" + pdfFilePath + "，目标文件路径：" + signatureFilePath;
					logger.error(message, e);
					throw new PDFSignatureException(message, e);
				} finally {
					IOUtils.closeQuietly(input);
					IOUtils.closeQuietly(output);
				}
				return;
			}
			String sourcePdfFilePath = pdfFilePath;
			String resultPdfPath = null;
			List<String> tempFiles = new ArrayList<String>();
			for (int i = 0; i < positions.size(); i++) {//循环签章
				//临时文件路径
				resultPdfPath = signatureFileDir.getAbsolutePath() + File.separator + "temp" + i + ".pdf";
				if (i == positions.size() - 1) { // 最后一次
					resultPdfPath = signatureFilePath;
				} else {
					tempFiles.add(resultPdfPath);
				}
				sign(sourcePdfFilePath, resultPdfPath, positions.get(i));
				sourcePdfFilePath = resultPdfPath;
			}
			for (String tempFile : tempFiles) {
				FileUtils.deleteQuietly(new File(tempFile));
			}
		}
		public String getKeyword() {
			return keyword;
		}

		public String getPdfFilePath() {
			return pdfFilePath;
		}

		public void setPdfFilePath(String pdfFilePath) {
			this.pdfFilePath = pdfFilePath;
		}

		public String getSignatureFilePath() {
			return signatureFilePath;
		}

		public void setSignatureFilePath(String signatureFilePath) {
			this.signatureFilePath = signatureFilePath;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

		public String getImagePath() {
			return imagePath;
		}

		public void setImagePath(String imagePath) {
			this.imagePath = imagePath;
		}

		public int getSignatureWidth() {
			return signatureWidth;
		}

		public void setSignatureWidth(int signatureWidth) {
			this.signatureWidth = signatureWidth;
		}

		public int getSignatureHeight() {
			return signatureHeight;
		}

		public void setSignatureHeight(int signatureHeight) {
			this.signatureHeight = signatureHeight;
		}

		public ICertificate getCert() {
			return cert;
		}

		public void setCert(ICertificate cert) {
			this.cert = cert;
		}
	    public static void main(String[] args) {  
	        try {
	        	String pdfFilePath = "/Users/rudy/keystore/testSignature.pdf";
	    		String signatureFilePath = "/Users/rudy/keystore/testSignature2.pdf";
	    		String keyword = "易招标签章位置";
	    		String imagePath = "/Users/rudy/keystore/default.jpg";
	    		ICertificate cert = new P12Certificate("/Users/rudy/keystore/pdfsignature.p12", "123456");
	    		signPDF signature = new signPDF(pdfFilePath, signatureFilePath, keyword, imagePath, cert);
	    		signature.signature();
	            System.out.println("done!!");  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	    }  
}
