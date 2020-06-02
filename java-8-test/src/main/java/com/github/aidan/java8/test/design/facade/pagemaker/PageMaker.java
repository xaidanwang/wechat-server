package com.github.aidan.java8.test.design.facade.pagemaker;

import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

/**
 * @author wang yi fei
 * @date 2019/5/27 9:49
 */
public class PageMaker {

	private PageMaker(){

	}

	public static void makeWelcomePage(String mailaddr,String filename){
		try {
			Properties mailprop = Database.getProperties("maildata");
			String username = mailprop.getProperty(mailaddr);
			HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
			writer.title("Welcome to "+ username + "`s page!");
			writer.paragraph(username + "欢迎来到"+ username + "的主页");
			writer.paragraph("等着你的邮件哦!  ");
			writer.mailto(mailaddr,username);
			writer.close();
			System.out.println(filename +" is created for "+ mailaddr + "(" + username +")");
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void makeLinkPage(String filename){
		try {

			HtmlWriter writer = new HtmlWriter(new FileWriter(filename));
			writer.title("Link page");

			Properties mailprop = Database.getProperties("maildata");
			Enumeration en = mailprop.propertyNames();
			while (en.hasMoreElements()){
				String mailaddr = (String) en.nextElement();
				String username = mailprop.getProperty(mailaddr,"(unknown)");
				writer.mailto(mailaddr,username);
			}

			writer.close();
			System.out.println(filename + "is created .");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
