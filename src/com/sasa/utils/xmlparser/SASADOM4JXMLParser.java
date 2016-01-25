package com.sasa.utils.xmlparser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sasa.entity.Student;

public class SASADOM4JXMLParser {
	public static List<Student> DOM4JXMLParser() {
		long startTime = System.currentTimeMillis();
		List<Student> studentsList = new ArrayList<Student>();
		int studentsCount = 0;
		String fileName = "src/res/students.xml";
		// 创建SAXReader 的对象 reader
		SAXReader reader = new SAXReader();
		// 通过 reader 对象的 read 方法加载 xml 文件
		try {
			Document document = reader.read(new File(fileName));
			// 通过 document 对象获取根节点 students
			Element students = document.getRootElement();
			// 通过 element 对象的 elementIterator 方法获取迭代器
			Iterator it = students.elementIterator();
			// 遍历迭代器，获取根节点中的信息（学生）
			while (it.hasNext()) {
				Student studentEntity = new Student();
				++studentsCount;
				System.out.println("******* 下面开始遍历第 " + studentsCount
						+ " 个学生的信息 *******");
				Element student = (Element) it.next();
				// 获取 book 的属性名以及属性值
				List<Attribute> studentAttrs = student.attributes();
				System.out.println("本生共有：" + studentAttrs.size() + " 个属性；");
				for (Attribute attr : studentAttrs) {
					System.out.println("属性名：" + attr.getName() + "，属性值："
							+ attr.getValue());
				}
				Iterator itt = student.elementIterator();
				int ittCounts = 0;
				while (itt.hasNext()) {
					++ittCounts;
					Element studentChild = (Element) itt.next();
					System.out
					.println("第 " + ittCounts + " 个子节点名称："
							+ studentChild.getName() + "，值为："
							+ studentChild.getStringValue() + "；");
					// 给实体赋值
					if (studentChild.getName().equals("name")) {
						studentEntity.setName(studentChild.getStringValue());
					} else if (studentChild.getName().equals("age")) {
						studentEntity.setAge(studentChild.getStringValue());
					} else if (studentChild.getName().equals("sex")) {
						studentEntity.setSex(studentChild.getStringValue());
					} else if (studentChild.getName().equals("home")) {
						studentEntity.setHome(studentChild.getStringValue());
					}
				}
				System.out.println("本生共有：" + ittCounts + " 个有效子节点；");
				System.out.println("******* 下面结束遍历第 " + studentsCount
						+ " 个学生的信息 *******");
				studentsList.add(studentEntity);
				studentEntity = null;
			}
			System.out.println("一共有：" + studentsCount + " 个学生");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("共消耗了：【" + (endTime - startTime) + "】毫秒");
		return studentsList;
	}
}
