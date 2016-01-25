package com.sasa.utils.xmlparser;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.sasa.entity.Student;
import com.sasa.utils.xmlparser.handler.SAXParserHandler;

public class SASASAXXMLParser {
	public static void SAXXMLParser() {
		long startTime = System.currentTimeMillis();
		String fileName = "src/res/students.xml";
		// ��ȡһ�� SAXParserFactory ��ʵ��
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			// ͨ�� factory ��ȡSAXParser ʵ��
			SAXParser parser = factory.newSAXParser();
			// ���� SAXParserHandler ����
			SAXParserHandler handler = new SAXParserHandler();
			parser.parse(fileName, handler);
			System.out.println("һ���У�" + handler.getStudentList().size() + " ��ѧ��");
			long endTime = System.currentTimeMillis();
			System.out.println("�������ˣ���" + (endTime - startTime) + "������");
			for (Student student : handler.getStudentList()) {
				System.out.println(student);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
