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
		// ����SAXReader �Ķ��� reader
		SAXReader reader = new SAXReader();
		// ͨ�� reader ����� read �������� xml �ļ�
		try {
			Document document = reader.read(new File(fileName));
			// ͨ�� document �����ȡ���ڵ� students
			Element students = document.getRootElement();
			// ͨ�� element ����� elementIterator ������ȡ������
			Iterator it = students.elementIterator();
			// ��������������ȡ���ڵ��е���Ϣ��ѧ����
			while (it.hasNext()) {
				Student studentEntity = new Student();
				++studentsCount;
				System.out.println("******* ���濪ʼ������ " + studentsCount
						+ " ��ѧ������Ϣ *******");
				Element student = (Element) it.next();
				// ��ȡ book ���������Լ�����ֵ
				List<Attribute> studentAttrs = student.attributes();
				System.out.println("�������У�" + studentAttrs.size() + " �����ԣ�");
				for (Attribute attr : studentAttrs) {
					System.out.println("��������" + attr.getName() + "������ֵ��"
							+ attr.getValue());
				}
				Iterator itt = student.elementIterator();
				int ittCounts = 0;
				while (itt.hasNext()) {
					++ittCounts;
					Element studentChild = (Element) itt.next();
					System.out
					.println("�� " + ittCounts + " ���ӽڵ����ƣ�"
							+ studentChild.getName() + "��ֵΪ��"
							+ studentChild.getStringValue() + "��");
					// ��ʵ�帳ֵ
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
				System.out.println("�������У�" + ittCounts + " ����Ч�ӽڵ㣻");
				System.out.println("******* ������������� " + studentsCount
						+ " ��ѧ������Ϣ *******");
				studentsList.add(studentEntity);
				studentEntity = null;
			}
			System.out.println("һ���У�" + studentsCount + " ��ѧ��");
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("�������ˣ���" + (endTime - startTime) + "������");
		return studentsList;
	}
}
