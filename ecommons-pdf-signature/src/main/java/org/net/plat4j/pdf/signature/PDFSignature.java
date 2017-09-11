/*
	Copyright (C) 2017 Shanghai Huizhao e-Bidding Services Co., Ltd.
	All rights reserved.

	Author: chenyj
	Version: 1.0
	Created Time: 2017年2月24日 上午10:10:54
	
	Revision History:
	Version          Date               Author			Comments
	1.0         	2017年2月24日上午10:10:54		chenyj			Create file
=========================================================================
*/
package org.net.plat4j.pdf.signature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.net.plat4j.pdf.signature.cert.ICertificate;
import org.net.plat4j.pdf.signature.model.TextPositionSequence;
import org.net.plat4j.pdf.signature.utils.PdfPositionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfSignatureAppearance.RenderingMode;
import com.itextpdf.text.pdf.PdfStamper;

/**
 * @author chenyj
 *
 */
public class PDFSignature {
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

	public PDFSignature() {
		this.signatureWidth = 100;
		this.signatureHeight = 100;
	}

	public PDFSignature(String pdfFilePath, String signatureFilePath, String keyword, String imagePath,
			ICertificate cert) {
		this();
		this.pdfFilePath = pdfFilePath;
		this.signatureFilePath = signatureFilePath;
		this.keyword = keyword;
		this.imagePath = imagePath;
		this.cert = cert;
	}

	/**
	 * 对pdf签章
	 * @param inFilePath 原pdf路径
	 * @param outFilePath 签章pdf路径
	 * @param position 签章位置
	 */
	private void signature(String inFilePath, String outFilePath, TextPositionSequence position) {
		PdfReader reader = null;
		FileOutputStream fout = null;
		PdfStamper stp = null;
		try {
	        reader = new PdfReader(inFilePath);   
	        fout = new FileOutputStream(outFilePath);  
	        //创建签章工具PdfStamper ，最后一个boolean参数 
	        //false的话，pdf文件只允许被签名一次，多次签名，最后一次有效
	        //true的话，pdf可以被追加签名，验签工具可以识别出每次签名之后文档是否被修改
	        stp = PdfStamper.createSignature(reader, fout, '\0', null, true);
	        // 获取数字签章属性对象，设定数字签章的属性
	        PdfSignatureAppearance sap = stp.getSignatureAppearance();  
	        sap.setCrypto(cert.getPrivateKey(), cert.getCertificate(), null,
	        		PdfSignatureAppearance.VERISIGN_SIGNED);  
	  
	        sap.setSignatureGraphic(Image.getInstance(imagePath));  
	        sap.setAcro6Layers(true);  
	        //设置图章的显示方式，如下选择的是只显示图章（还有其他的模式，可以图章和签名描述一同显示）
	        sap.setRenderingMode(RenderingMode.GRAPHIC);  
	        //设置签名的位置，页码，签名域名称，多次追加签名的时候，签名预名称不能一样
	        //签名的位置，是图章相对于pdf页面的位置坐标，原点为pdf页面左下角
	        //四个参数的分别是，图章左下角x，图章左下角y，图章右上角x，图章右上角y
	        sap.setVisibleSignature(
					new Rectangle(position.getX(), position.getY() - signatureHeight / 2,
							position.getX() + signatureWidth, position.getY() + signatureHeight / 2),
					position.getPage(), null);
	        stp.getWriter().setCompressionLevel(5);  
		} catch (Exception e) {
			logger.error("pdf签章失败", e);
			throw new PDFSignatureException("pdf签章失败", e);
		} finally {
			if (stp != null) {  
	            try {
					stp.close();
				} catch (DocumentException e) {
					logger.error("pdf签章失败", e);
					throw new PDFSignatureException("pdf签章失败", e);
				} catch (IOException e) {
					logger.error("pdf签章失败", e);
					throw new PDFSignatureException("pdf签章失败", e);
				}  
	        }  
			if (reader != null) {
				reader.close();
			}
			IOUtils.closeQuietly(fout);
		}
	}

	public void signature() {
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
			signature(sourcePdfFilePath, resultPdfPath, positions.get(i));
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
}
