package com.sasa.utils.createxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class SASADOM4JCreateXML {
	public static void DOM4JCreateXML() {
		// 创建 document 对象，代表整个 xml 文档
		Document document = DocumentHelper.createDocument();
		// 创建根节点 rss
		Element rss = document.addElement("rss");
		// 向 rss 节点中添加属性 version 属性
		rss.addAttribute("version", "2.0");
		
		// 生成子节点以及节点内容
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("国内最新新闻");
		// 设置生成 xml 的格式
		OutputFormat format = OutputFormat.createPrettyPrint();
//		format.setEncoding("GBK"); // 设置编码
		
		// 生成文件
		File file = new File("DOM4JForStudents.xml");
		XMLWriter writer;
		
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.setEscapeText(false); // 不进行转义字符转义，默认 true
			writer.write(document);
			writer.close();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
