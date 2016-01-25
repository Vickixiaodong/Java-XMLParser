package com.sasa.utils.createxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class SASAJDOMCreateXML {
	public static void JDOMCreateXML() {
		// 生成一个根节点
		Element rss = new Element("rss");
		// 为节点添加属性
		rss.setAttribute("version", "2.0");
		// 生成一个 document 对象
		Document document = new Document(rss);
		
		Element channel = new Element("channel");
		Element title = new Element("title");
		title.setText("国内最新新闻");
		channel.addContent(title);
		rss.addContent(channel);
		
		// xml 格式设置
		Format format = Format.getCompactFormat();
		format.setIndent(""); // 设置换行
		format.setEncoding("UTF-8"); // 编码，默认 UTF-8
		
		// 创建 XMLOutputter 的对象
		XMLOutputter outputer = new XMLOutputter(format);
		// 用 outputer 将 document 对象转换成 xml 文档
		try {
			outputer.output(document, new FileOutputStream(new File("JDOMForStudents.xml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
