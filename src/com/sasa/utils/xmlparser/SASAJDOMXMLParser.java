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
		// ����һ�� SAXBuilder �Ķ���
		SAXBuilder saxBuilder = new SAXBuilder();
		InputStream in = null;
		try {
			// ����һ������������ xml �ļ����ص���������
			in = new FileInputStream(fileName);
			// ��������
			InputStreamReader isr = new InputStreamReader(in, "UTF-8");
			// ͨ�� SAXBuilder �� build �����������������ص� saxBuilder ��
			Document document = saxBuilder.build(isr);
			// ͨ�� document �����ȡ xml �ļ��ĸ��ڵ�
			Element rootElement = document.getRootElement();
			// ��ȡ���ڵ��µ��ӽڵ�� List ����
			List<Element> studentList = rootElement.getChildren();
			System.out.println("һ���У�" + studentList.size() + " ��ѧ��");
			// �������н���������ͨ�� studentList.size() ��ȡ��������
			for (Element student : studentList) {
				Student studentEntity = new Student();
				System.out.println("******* ���濪ʼ������ "
						+ (studentList.indexOf(student) + 1)
						+ " ��ѧ������Ϣ *******");
				// ���� student ������
				List<Attribute> attrList = student.getAttributes(); // ��֪��������������
				System.out.println("�������У�" + attrList.size() + " �����ԣ�");
				// student.getAttribute("id"); // ֪��������
				// student.getAttributeValue("id"); // ֪��������
				// �������Լ���
				for (Attribute attr : attrList) {
					// ��ȡ������
					String attrName = attr.getName();
					// ��ȡ����ֵ
					String attrValue = attr.getValue();
					System.out.println("��������" + attrName + "������ֵ��" + attrValue);
					if (attrName.equals("id")) {
						// studentEntity.setId(attrValue);
					}
				}
				// �� book �ڵ���ӽڵ�Ľڵ����Լ��ڵ�ֵ�ı���
				List<Element> studentChildren = student.getChildren();
				System.out.println("�������У�" + studentChildren.size() + " ���ӽڵ㣻");
				for (Element child : studentChildren) {
					System.out.println("�� " + (studentChildren.indexOf(child) + 1) + " ���ӽڵ����ƣ�"
							+ child.getName() + "��ֵΪ��"
							+ child.getValue() + "��");
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
				System.out.println("******* ������������� "
						+ (studentList.indexOf(student) + 1)
						+ " ��ѧ������Ϣ *******");
				studentsList.add(studentEntity);
				studentEntity = null; // ��������
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("�������ˣ���" + (endTime - startTime) + "������");
		return studentsList;
	}
}
