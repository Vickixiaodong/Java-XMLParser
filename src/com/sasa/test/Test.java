package com.sasa.test;

import java.util.List;

import com.sasa.entity.Student;
import com.sasa.utils.xmlparser.SASADOMXMLParser;
import com.sasa.utils.xmlparser.SASAJDOMXMLParser;
import com.sasa.utils.xmlparser.SASASAXXMLParser;

public class Test {
	public static void main(String[] args) {
		// 测试 DOM 方法
		System.out.println("============== DOM Start ==============");
		List<Student> studentsList = SASADOMXMLParser.DOMXMLParser();
		for (Student student : studentsList) {
			System.out.println(student);
		}
		System.out.println("============== DOM End ==============");
		// 测试 DOM 方法
		System.out.println("============== SAX Start ==============");
		SASASAXXMLParser.SAXXMLParser();
		System.out.println("============== SAX End ==============");
		// 测试 JDOM 方法
		System.out.println("============== JDOM Start ==============");
		studentsList = SASAJDOMXMLParser.JDOMXMLParser();
		for (Student student : studentsList) {
			System.out.println(student);
		}
		System.out.println("============== JDOM End ==============");
		// 测试 DOM 方法
		System.out.println("============== DOM4J Start ==============");
		studentsList = SASADOMXMLParser.DOMXMLParser();
		for (Student student : studentsList) {
			System.out.println(student);
		}
		System.out.println("============== DOM4J End ==============");
	}
}
