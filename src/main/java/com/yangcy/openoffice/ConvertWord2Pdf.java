package com.yangcy.openoffice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class ConvertWord2Pdf {
	private static final String OPEN_OFFICE_HOME = "C:/Program Files (x86)/OpenOffice 4"; 
	private static final String LIBRE_OFFICE_HOME = "C:\\Program Files (x86)\\LibreOffice 5"; 
	
	
	
	public static void main(String[] args) {
		String path = "C:\\Users\\Administrator\\Desktop\\周胜.html";
		
		new ConvertWord2Pdf(path, LIBRE_OFFICE_HOME);
		
		
	}


    /**
     * 
     * @description:将Office格式的文档转换为PDF格式的文档
     * @author huang.j
     * @date 2017年5月24日 上午11:44:03
     * @param inputFilePath
     *
     */
    public ConvertWord2Pdf(String inputFilePath, String officeHome) {

        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();
        // OpenOffice/LibreOffice安装在本地环境的目录
        config.setOfficeHome(officeHome);

        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();
        
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        
        String outputFilePath = getReplaceFileAbsolutePath(inputFilePath, ".pdf");
        File inputFile = new File(inputFilePath);
        if (inputFile.exists()) {
            File outputFile = new File(outputFilePath);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
            // 进行PDF格式的转换
            converter.convert(inputFile, outputFile);
        }

        officeManager.stop();
    }
    /**
     * 
     * @description:更改文档后缀为指定的后缀名
     * @author huang.j
     * @date 2017年5月24日 上午11:44:20
     * @param inputFilePath 输入文档的绝对路径
     * @param replaceEndWith 指定的后缀名
     * @return 返回替换指定后缀名的文档的绝对路径
     *
     */
    private String getReplaceFileAbsolutePath(String inputFilePath, String replaceEndWith) {

        String replaceFilePath = null;
        Pattern pattern = Pattern.compile("(\\.[a-zA-Z]+)");
        Matcher matcher = pattern.matcher(inputFilePath);
        String endWith = null;
        if(matcher.find()) {
            endWith = matcher.group(1);
        }

        if(StringUtils.isEmpty(endWith)) {
            return null;
        }

        replaceFilePath = inputFilePath.replaceAll(endWith, replaceEndWith);
        return replaceFilePath;
    }
    /**
     * 
     * @description:预览PDF文件
     * @author huang.j
     * @date 2017年5月23日 下午4:51:59
     * @param attathFile
     * @param response
     *
     */
    public void previewPdf(File attathFile, HttpServletResponse response) {

        response.setContentType("application/pdf");
        try {
            if(attathFile.exists()) {
                DataOutputStream dataOutputStream = new DataOutputStream(response.getOutputStream());
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(attathFile));

                byte[] buffer = new byte[2048];
                int len = buffer.length;
                while((len = dataInputStream.read(buffer, 0, len)) != -1) {
                    dataOutputStream.write(buffer);
                    dataOutputStream.flush();
                }

                dataInputStream.close();
                dataOutputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
