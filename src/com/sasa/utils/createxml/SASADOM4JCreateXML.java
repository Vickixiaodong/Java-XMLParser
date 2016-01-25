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
		// ���� document ���󣬴������� xml �ĵ�
		Document document = DocumentHelper.createDocument();
		// �������ڵ� rss
		Element rss = document.addElement("rss");
		// �� rss �ڵ���������� version ����
		rss.addAttribute("version", "2.0");
		
		// �����ӽڵ��Լ��ڵ�����
		Element channel = rss.addElement("channel");
		Element title = channel.addElement("title");
		title.setText("������������");
		// �������� xml �ĸ�ʽ
		OutputFormat format = OutputFormat.createPrettyPrint();
//		format.setEncoding("GBK"); // ���ñ���
		
		// �����ļ�
		File file = new File("DOM4JForStudents.xml");
		XMLWriter writer;
		
		try {
			writer = new XMLWriter(new FileOutputStream(file), format);
			writer.setEscapeText(false); // ������ת���ַ�ת�壬Ĭ�� true
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
