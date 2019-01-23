package com.yangcy.itext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.lowagie.text.pdf.BaseFont;
 
/**
 * html转pdf
 * 
 * @author wqb
 *
 */
public class PdfDemoTest {
 
	public static void main(String[] args) throws Exception {
		String path = PdfDemoTest.class.getClassLoader().getResource("").getPath();
		//读取html的流
		InputStream inputStream = new FileInputStream(path + "周胜.html");
		
		//流转换成字符串
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = inputStream.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
 
		String html = out.toString();
		String pdffile = "周胜.pdf";
 
		OutputStream os = new FileOutputStream(pdffile);
		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(html);
		// writer.setPageEvent(header);
		ITextFontResolver fontResolver = renderer.getFontResolver();
		// 添加字体支持,路径可以自身项目的实际情况设置，我这里是本地项目，而且为了方便测试，就写成固定的了
		// 实际项目中，可以获取改字体所在真实的服务器的路径,这个方法是本地地址和网络地址都支持的
		// 这里面添加的是宋体
		fontResolver.addFont("SIMSUN.TTC",
				BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
 
		 
		renderer.layout();
		renderer.createPDF(os);
		os.flush();
		os.close();
 
	}
}
