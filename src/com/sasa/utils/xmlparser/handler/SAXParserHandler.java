package com.sasa.utils.xmlparser.handler;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sasa.entity.Student;

public class SAXParserHandler extends DefaultHandler {
	private int studentIndex = 0;
	private int nodeCounts = 0; // �ӽڵ�����
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
	 * �������� XML �ļ��Ľ�����ǩ
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		// �ж��Ƿ����һ�����Ѿ���������
		if (qName.equals("student")) {
			studentList.add(student); // ��ӵ� studentList ��
			student = null; // ���
			System.out.println("�������У�" + this.getNodeCounts() + " ����Ч�ӽڵ㣻");
			System.out.println("******* ������������� " + studentIndex
					+ " ��ѧ������Ϣ *******");
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
	 * �������� XML �ļ��Ŀ�ʼ��ǩ
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		if (qName.equals("student")) {
			// ����һ�� Student ����
			student = new Student();
			++studentIndex;
			nodeCounts = 0;
			System.out.println("******* ���濪ʼ������ " + studentIndex
					+ " ��ѧ������Ϣ *******");
			// qName �Ǳ�ǩ���ƣ�����ֻ�� student �����ԣ�����֪������ id
//			String value = attributes.getValue("id");
//			System.out.println("student ������ֵ id = " + value);
			
			// �����֪�� student Ԫ�������Ե������Լ���������λ�ȡ����ֵ��
			int num = attributes.getLength(); // ���Ը���
			System.out.println("�������У�" + num + " �����ԣ�");
			for (int i = 0; i < num; ++i) {
				System.out.println("student Ԫ�صĵ� " + (i + 1) + " ����������" + attributes.getQName(i) + "��ֵ�ǣ�" + attributes.getValue(i));
				if (attributes.getQName(i).equals("id")) {
//					student.setId(attributes.getValue(i));
				}
			}
		} else if (!qName.equals("student") && !qName.equals("students")) {
			System.out.print("�ڵ����ƣ�" + qName + "��");
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		super.characters(ch, start, length);
		value = new String(ch, start, length);
		// ȥ������Ŀո�ͻ���
		if (!value.trim().equals("")) {
			++nodeCounts;
			System.out.println("ֵΪ��" + value + "��");
		}
	}

	/**
	 * ������ʶ��������
	 */
	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
//		System.out.println("����������");
	}

	/**
	 * ������ʶ������ʼ
	 */
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
//		System.out.println("������ʼ��");
	}
}
