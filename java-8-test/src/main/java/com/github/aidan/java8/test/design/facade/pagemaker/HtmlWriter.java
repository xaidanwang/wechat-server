package com.github.aidan.java8.test.design.facade.pagemaker;

import java.io.IOException;
import java.io.Writer;

/**
 * @author wang yi fei
 * @date 2019/5/25 18:04
 */
public class HtmlWriter {

	private Writer writer;

	HtmlWriter(Writer writer) {
		this.writer = writer;
	}
	public void title(String title) throws IOException {
		writer.write("<html>");
		writer.write("<head>");
		writer.write("<title>" +title + "</title>");
		writer.write("</head>");
		writer.write("<body>\n");
		writer.write("<h1>"+ title + "</h1>\n");
	}
	public void paragraph(String msg)throws IOException{  // 输出段落
		writer.write("<p>" + msg + "</p>\n");
	}

	public void link(String href,String caption)throws IOException{  //  输出超链接
		writer.write("<a href =\">" + href +"\"" + caption + "</a>");
	}

	public void mailto(String mailaddr,String username) throws IOException{  // 输出邮件地址
		link("mailto:" + mailaddr,username);
	}
	public void close() throws IOException{  // 结束输出HTML
		writer.write("</body>");
		writer.write("</html>\n");
		writer.close();
	}
}
