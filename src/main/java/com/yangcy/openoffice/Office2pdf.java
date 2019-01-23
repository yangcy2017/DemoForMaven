package com.yangcy.openoffice;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;


public class Office2pdf {

	private static OfficeManager officeManager;
    private static String OPEN_OFFICE_HOME = "C:/Program Files (x86)/OpenOffice 4";
    private static int OPEN_OFFICE_PORT[] = {8100};
    
    public static void main(String[] args) throws java.io.IOException {
    	args = new String[2]; 
    	args[0] = "C:\\Users\\Administrator\\Desktop\\周胜.html";
    	args[1] = "C:\\Users\\Administrator\\Desktop\\周胜.pdf";
        System.out.println("\n\n\n");
        
        if (args.length < 2) {
            System.out.println("输入参数错误,必须指定至少2个参数.\n  office2pdf \"office路径\" \"pdf保存路径\" -env; \n -env 显示当前java运行环境;\n 目前你输入的参数个数是" + args.length);
            return;
        }
        
        if ( (args.length > 2) && args[2].equalsIgnoreCase("-env")) {//查看java运行环境参数
            System.getProperties().storeToXML(System.out, "java 运行环境", "UTF-8");//输出内容是utf8,所以,本java源代码也必须是utf8来保存
            System.out.println("\n\n\n");
        }
        
        String inputFile = args[0];
        String pdfFile = args[1];
        
        if (inputFile.isEmpty()) {
            System.out.println("待转换office路径不允许留空");
            return;
        }
        
        System.out.println("进行文档转换转换:" + inputFile + " --> " + pdfFile);
        long startTime = System.currentTimeMillis();
        startService();        
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        converter.convert(new File(inputFile),new File(pdfFile));
        System.out.println("转换完成.耗时" +( (System.currentTimeMillis() - startTime) / 60.0)+ "秒");
        stopService();
        System.out.println("运行结束");        
    }
    
    public static void stopService(){
        System.out.println("关闭office转换服务....");
        
        if (officeManager != null) {
            officeManager.stop();
        }
        
        System.out.println("关闭office转换成功!");
    }
    
    public static void startService(){
    	DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        
        try {
            System.out.println("准备启动安装在" + OPEN_OFFICE_HOME + "目录下的openoffice服务....");
            configuration.setOfficeHome(OPEN_OFFICE_HOME);//设置OpenOffice.org安装目录
            configuration.setPortNumbers(OPEN_OFFICE_PORT); //设置转换端口，默认为8100
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);//设置任务执行超时为5分钟
            configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);//设置任务队列超时为24小时
         
            officeManager = configuration.buildOfficeManager();
            officeManager.start();    //启动服务
            System.out.println("office转换服务启动成功!");
        } catch (Exception ce) {
            System.out.println("office转换服务启动失败!详细信息:" + ce);
        }
    }
}

