package com.yangcy.openoffice;

import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.ExternalOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

public class StartWay {
	/**
	 * 服务未启动的情况
	 */
	public void startNoService() {
	    DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        configuration.setOfficeHome("");
        configuration.setPortNumbers(8100);
        configuration.setTaskExecutionTimeout(1000 * 60 * 5L);
        configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);

        OfficeManager officeManager = configuration.buildOfficeManager();
        officeManager.start();		
	}
	/**
	 * 服务已启动的情况
	 */
	public void startWithService() {
    	ExternalOfficeManagerConfiguration externalProcessOfficeManager = new ExternalOfficeManagerConfiguration();
    	externalProcessOfficeManager.setConnectOnStart(true);
    	externalProcessOfficeManager.setPortNumber(8100);
    	OfficeManager officeManager = externalProcessOfficeManager.buildOfficeManager();
        officeManager.start();
	}
	
	
	public boolean start() {
		try {
			System.out.println("准备启动服务....");
			try {
				System.out.println("尝试连接已启动的服务...");
				ExternalOfficeManagerConfiguration externalProcessOfficeManager = new ExternalOfficeManagerConfiguration();
				externalProcessOfficeManager.setConnectOnStart(true);
				externalProcessOfficeManager.setPortNumber(8100);
				OfficeManager officeManager = externalProcessOfficeManager.buildOfficeManager();
				officeManager.start();
				System.out.println("office转换服务启动成功!");
				return true;
			} catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("没有已启动的服务...");
			}
			System.out.println("创建并连接新服务...");
 
			DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
 
			configuration.setOfficeHome("");
			configuration.setPortNumbers(8100);
			configuration.setTaskExecutionTimeout(1000 * 60 * 5L);
			configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);
			OfficeManager officeManager = configuration.buildOfficeManager();
			officeManager.start();
			System.out.println("office转换服务启动成功!");
			return true;
		} catch (Exception ce) {
			ce.printStackTrace();
			System.out.println("office转换服务启动失败!详细信息:" + ce);
			return false;
		}	
	}
}
