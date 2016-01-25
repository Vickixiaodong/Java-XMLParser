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
 * �̶����� xml �ļ� �г�������õ������� �������ʱ�� �����ݱ��浽ʵ�岢���
 * 
 * @author xiexiaodong
 * 
 */
public class SASADOMXMLParser {
	public static List<Student> DOMXMLParser() {
		int nodeCounts = 0; // ��Ч�ӽڵ�
		long startTime = System.currentTimeMillis();
		List<Student> studentsList = new ArrayList<Student>();
		String fileName = "src/res/students.xml";
		// ����һ�� DocumentBuilderFactory �Ķ���
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			// ͨ�� DocumentBuilderFactory ���󴴽�һ�� DocumentBuilder �Ķ���
			DocumentBuilder documentBuilder = documentBuilderFactory
					.newDocumentBuilder();
			// ͨ�� Document ����� parser() �������� XML �ļ�����ǰ��Ŀ��
			Document document = documentBuilder.parse(fileName);
			// ��ȡ���� student �ڵ�ļ���
			NodeList studentList = document.getElementsByTagName("student");
			System.out.println("һ���У�" + studentList.getLength() + " ��ѧ��");
			// ����ÿһ�� student
			for (int i = 0; i < studentList.getLength(); ++i) {
				nodeCounts = 0;
				Student studentEntity = new Student();
				System.out.println("******* ���濪ʼ������ " + (i + 1)
						+ " ��ѧ������Ϣ *******");
				// ����������ȡ student ��Ϣ
				Node student = studentList.item(i);
				// ��ȡ student �ڵ���������Լ���
				NamedNodeMap attrs = student.getAttributes();
				System.out.println("�������У�" + attrs.getLength() + " �����ԣ�");
				// ��������
				for (int j = 0; j < attrs.getLength(); ++j) {
					Node attr = attrs.item(j);
					// ���������������ֵ
					System.out.println("��������" + attr.getNodeName() + "������ֵ��"
							+ attr.getNodeValue() + "��");
				}
				// ��ʼ����ÿ���ڵ�(student)����Ϣ
				NodeList childNodes = student.getChildNodes(); // �ո�ͻ���Ҳ����һ���ӽڵ�
				System.out.println("�������У�" + childNodes.getLength() + " ���ӽڵ㣻");
				for (int k = 0; k < childNodes.getLength(); ++k) {
					// ���ֳ� text ���͵� node �Լ� element ���͵� node
					if (childNodes.item(k).getNodeType() == Node.ELEMENT_NODE) {
						++nodeCounts;
						// ��ȡ�� element ���ͽڵ�Ľڵ���
						Node childNode = childNodes.item(k);
						String childNodeName = childNode.getNodeName();
						// ��ȡ�� element ���ͽڵ�Ľڵ�ֵ�� childNode.getTextContext() Ҳ����
						String childNodeValue = childNode.getFirstChild()
								.getNodeValue();
						System.out
								.println("�� " + (k + 1) + " ���ӽڵ����ƣ�"
										+ childNodeName + "��ֵΪ��"
										+ childNodeValue + "��");
						// ��ʵ�帳ֵ
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
				System.out.println("������Ч�ӽڵ㹲��" + nodeCounts + " ����");
				System.out.println("******* ������������� " + (i + 1)
						+ " ��ѧ������Ϣ *******");
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
		System.out.println("�������ˣ���" + (endTime - startTime) + "������");
		return studentsList;
	}
}
