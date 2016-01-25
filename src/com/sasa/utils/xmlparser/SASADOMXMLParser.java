package com.sasa.utils.xmlparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sasa.entity.Student;

/**
 * 固定解析 xml 文件 列出解析后得到的数据 算出消耗时间 将数据保存到实体并输出
 * 
 * @author xiexiaodong
 * 
 */
public class SASADOMXMLParser {
	public static List<Student> DOMXMLParser() {
		int nodeCounts = 0; // 有效子节点
		long startTime = System.currentTimeMillis();
		List<Student> studentsList = new ArrayList<Student>();
		String fileName = "src/res/students.xml";
		// 创建一个 DocumentBuilderFactory 的对象
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			// 通过 DocumentBuilderFactory 对象创建一个 DocumentBuilder 的对象
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			// 通过 Document 对象的 parser() 方法加载 XML 文件到当前项目下
			Document document = documentBuilder.parse(fileName);
			// 获取所有 student 节点的集合
			NodeList studentList = document.getElementsByTagName("student");
			System.out.println("一共有：" + studentList.getLength() + " 个学生");
			// 遍历每一个 student
			for (int i = 0; i < studentList.getLength(); ++i) {
				nodeCounts = 0;
				Student studentEntity = new Student();
				System.out.println("******* 下面开始遍历第 " + (i + 1)
						+ " 个学生的信息 *******");
				// 根据索引获取 student 信息
				Node student = studentList.item(i);
				// 获取 student 节点的所有属性集合
				NamedNodeMap attrs = student.getAttributes();
				System.out.println("本生共有：" + attrs.getLength() + " 个属性；");
				// 遍历属性
				for (int j = 0; j < attrs.getLength(); ++j) {
					Node attr = attrs.item(j);
					// 输出属性名和属性值
					System.out.println("属性名：" + attr.getNodeName() + "，属性值："
							+ attr.getNodeValue() + "；");
				}
				// 开始解析每个节点(student)的信息
				NodeList childNodes = student.getChildNodes(); // 空格和换行也当成一个子节点
				System.out.println("本生共有：" + childNodes.getLength() + " 个子节点；");
				for (int k = 0; k < childNodes.getLength(); ++k) {
					// 区分出 text 类型的 node 以及 element 类型的 node
					if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						++nodeCounts;
						// 获取了 element 类型节点的节点名
						Node childNode = childNodes.item(k);
						String childNodeName = childNode.getNodeName();
						// 获取了 element 类型节点的节点值， childNode.getTextContext() 也可以
						String childNodeValue = childNode.getFirstChild()
								.getNodeValue();
						System.out
								.println("第 " + (k + 1) + " 个子节点名称："
										+ childNodeName + "，值为："
										+ childNodeValue + "；");
						// 给实体赋值
						if (childNodeName.equals("name")) {
							studentEntity.setName(childNodeValue);
						} else if (childNodeName.equals("age")) {
							studentEntity.setAge(childNodeValue);
						} else if (childNodeName.equals("sex")) {
							studentEntity.setSex(childNodeValue);
						} else if (childNodeName.equals("home")) {
							studentEntity.setHome(childNodeValue);
						}
					}
				}
				System.out.println("其中有效子节点共：" + nodeCounts + " 个；");
				System.out.println("******* 下面结束遍历第 " + (i + 1)
						+ " 个学生的信息 *******");
				studentsList.add(studentEntity);
				studentEntity = null;
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("共消耗了：【" + (endTime - startTime) + "】毫秒");
		return studentsList;
	}
}
