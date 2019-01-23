package com.yangcy.openoffice;

import java.io.File;
import java.net.ConnectException;
//import com.artofsolving.jodconverter.DocumentConverter;
//import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
//import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

/**低版本的写法*/
public class OfficeConverter {

	public static void main(String[] args) {
		File inputFile = new File("C:/test/yy.doc");
		File outputFile = new File("C:/test/yy.html");
		
//		OpenOfficeConnection con = new SocketOpenOfficeConnection(8100);
//		try {
//			con.connect();
//		} catch (ConnectException e) {
//			System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
//			e.printStackTrace();
//		}
//		DocumentConverter converter = new OpenOfficeDocumentConverter(con);
//		converter.convert(inputFile, outputFile);
//		con.disconnect();
	}
}