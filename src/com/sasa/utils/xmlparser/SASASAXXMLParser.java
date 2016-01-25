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
		// 获取一个 SAXParserFactory 的实例
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			// 通过 factory 获取SAXParser 实例
			SAXParser parser = factory.newSAXParser();
			// 创建 SAXParserHandler 对象
			SAXParserHandler handler = new SAXParserHandler();
			parser.parse(fileName, handler);
			System.out.println("一共有：" + handler.getStudentList().size() + " 个学生");
			long endTime = System.currentTimeMillis();
			System.out.println("共消耗了：【" + (endTime - startTime) + "】毫秒");
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
