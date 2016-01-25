package com.sasa.utils.xmlparser.handler;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sasa.entity.Student;

public class SAXParserHandler extends DefaultHandler {
	private int studentIndex = 0;
	private int nodeCounts = 0; // 子节点数量
	private String value = null;
	private Student student = null;
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
	
	public int getNodeCounts() {
		return nodeCounts;
	}

	/**
	 * 用来遍历 XML 文件的结束标签
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		// 判断是否针对一本书已经遍历结束
		if (qName.equals("student")) {
			studentList.add(student); // 添加到 studentList 中
			student = null; // 清空
			System.out.println("本生共有：" + this.getNodeCounts() + " 个有效子节点；");
			System.out.println("******* 下面结束遍历第 " + studentIndex
					+ " 个学生的信息 *******");
		} else if (qName.equals("name")) {
			student.setName(value);
		} else if (qName.equals("age")) {
			student.setAge(value);
		} else if (qName.equals("sex")) {
			student.setSex(value);
		} else if (qName.equals("home")) {
			student.setHome(value);
		}
	}

	/**
	 * 用来遍历 XML 文件的开始标签
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("student")) {
			// 创建一个 Student 对象
			student = new Student();
			++studentIndex;
			nodeCounts = 0;
			System.out.println("******* 下面开始遍历第 " + studentIndex
					+ " 个学生的信息 *******");
			// qName 是标签名称，这里只有 student 有属性，且已知属性是 id
//			String value = attributes.getValue("id");
//			System.out.println("student 的属性值 id = " + value);
			
			// 如果不知道 student 元素下属性的名称以及个数，如何获取属性值呢
			int num = attributes.getLength(); // 属性个数
			System.out.println("本生共有：" + num + " 个属性；");
			for (int i = 0; i < num; ++i) {
				System.out.println("student 元素的第 " + (i + 1) + " 个属性名：" + attributes.getQName(i) + "，值是：" + attributes.getValue(i));
				if (attributes.getQName(i).equals("id")) {
//					student.setId(attributes.getValue(i));
				}
			}
		} else if (!qName.equals("student") && !qName.equals("students")) {
			System.out.print("节点名称：" + qName + "，");
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		value = new String(ch, start, length);
		// 去掉多余的空格和换行
		if (!value.trim().equals("")) {
			++nodeCounts;
			System.out.println("值为：" + value + "；");
		}
	}

	/**
	 * 用来标识解析结束
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
//		System.out.println("解析结束！");
	}

	/**
	 * 用来标识解析开始
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
//		System.out.println("解析开始！");
	}
}
