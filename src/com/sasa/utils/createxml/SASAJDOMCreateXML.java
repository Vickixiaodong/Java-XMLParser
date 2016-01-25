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
		// ����һ�����ڵ�
		Element rss = new Element("rss");
		// Ϊ�ڵ��������
		rss.setAttribute("version", "2.0");
		// ����һ�� document ����
		Document document = new Document(rss);
		
		Element channel = new Element("channel");
		Element title = new Element("title");
		title.setText("������������");
		channel.addContent(title);
		rss.addContent(channel);
		
		// xml ��ʽ����
		Format format = Format.getCompactFormat();
		format.setIndent(""); // ���û���
		format.setEncoding("UTF-8"); // ���룬Ĭ�� UTF-8
		
		// ���� XMLOutputter �Ķ���
		XMLOutputter outputer = new XMLOutputter(format);
		// �� outputer �� document ����ת���� xml �ĵ�
		try {
			outputer.output(document, new FileOutputStream(new File("JDOMForStudents.xml")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
