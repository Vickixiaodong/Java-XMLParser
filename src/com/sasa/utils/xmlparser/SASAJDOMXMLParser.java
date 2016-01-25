package com.sasa.utils.xmlparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.sasa.entity.Student;

public class SASAJDOMXMLParser {
	public static List<Student> JDOMXMLParser() {
		long startTime = System.currentTimeMillis();
		String fileName = "src/res/students.xml";
		List<Student> studentsList = new ArrayList<Student>();
		// 创建一个 SAXBuilder 的对象
		SAXBuilder saxBuilder = new SAXBuilder();
		InputStream in = null;
		try {
			// 创建一个输入流，将 xml 文件加载到输入流中
			in = new FileInputStream(fileName);
			// 处理乱码
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			// 通过 SAXBuilder 的 build 方法，将输入流加载到 saxBuilder 中
			Document document = saxBuilder.build(isr);
			// 通过 document 对象获取 xml 文件的根节点
			Element rootElement = document.getRootElement();
			// 获取根节点下的子节点的 List 集合
			List<Element> studentList = rootElement.getChildren();
			System.out.println("一共有：" + studentList.size() + " 个学生");
			// 继续进行解析，可以通过 studentList.size() 获取它的数量
			for (Element student : studentList) {
				Student studentEntity = new Student();
				System.out.println("******* 下面开始遍历第 "
						+ (studentList.indexOf(student) + 1)
						+ " 个学生的信息 *******");
				// 解析 student 的属性
				List<Attribute> attrList = student.getAttributes(); // 不知道属性名，个数
				System.out.println("本生共有：" + attrList.size() + " 个属性；");
				// student.getAttribute("id"); // 知道属性名
				// student.getAttributeValue("id"); // 知道属性名
				// 遍历属性集合
				for (Attribute attr : attrList) {
					// 获取属性名
					String attrName = attr.getName();
					// 获取属性值
					String attrValue = attr.getValue();
					System.out.println("属性名：" + attrName + "，属性值：" + attrValue);
					if (attrName.equals("id")) {
						// studentEntity.setId(attrValue);
					}
				}
				// 对 book 节点的子节点的节点名以及节点值的遍历
				List<Element> studentChildren = student.getChildren();
				System.out.println("本生共有：" + studentChildren.size() + " 个子节点；");
				for (Element child : studentChildren) {
					System.out.println("第 " + (studentChildren.indexOf(child) + 1) + " 个子节点名称："
							+ child.getName() + "，值为："
							+ child.getValue() + "；");
					if (child.getName().equals("name")) {
						studentEntity.setName(child.getValue());
					} else if (child.getName().equals("age")) {
						studentEntity.setAge(child.getValue());
					} else if (child.getName().equals("sex")) {
						studentEntity.setSex(child.getValue());
					} else if (child.getName().equals("home")) {
						studentEntity.setHome(child.getValue());
					}
				}
				System.out.println("******* 下面结束遍历第 "
						+ (studentList.indexOf(student) + 1)
						+ " 个学生的信息 *******");
				studentsList.add(studentEntity);
				studentEntity = null; // 垃圾回收
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("共消耗了：【" + (endTime - startTime) + "】毫秒");
		return studentsList;
	}
}
