package com.sasa.utils.createxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import com.sasa.entity.Student;

public class SASASAXCreateXML {
	public static void SAXCreateXML(List<Student> studentsList) {
		// ����һ�� TransformerFactory �Ķ���
		SAXTransformerFactory tff = (SAXTransformerFactory) SAXTransformerFactory
				.newInstance();
		try {
			TransformerHandler handler = tff.newTransformerHandler();
			Transformer tr = handler.getTransformer();

			// �� xml �ļ���������
			tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); // ����
			tr.setOutputProperty(OutputKeys.INDENT, "yes"); // ����

			File file = new File("SAXForStudents.xml");
			if (!file.exists()) {
				file.createNewFile();
			}

			Result result = new StreamResult(new FileOutputStream(file));
			handler.setResult(result);

			// �� document
			handler.startDocument();
			AttributesImpl attr = new AttributesImpl();

			handler.startElement("", "", "students", attr); // ����û���κ�����

			for (Student student : studentsList) {
				attr.clear(); // ���
				attr.addAttribute("", "", "id", "", "1");
				handler.startElement("", "", "student", attr);

				if (student.getName() != null && student.getName().trim().equals("")) {
					// �����ӽڵ��Լ�ֵ
					attr.clear();
					handler.startElement("", "", "name", attr);
					handler.characters(student.getName().toCharArray(), 0, student
							.getName().length());
					handler.endElement("", "", "name");
				}
				if (student.getAge() != null && student.getAge().trim().equals("")) {
					handler.startElement("", "", "age", attr);
					handler.characters(student.getAge().toCharArray(), 0, student
							.getAge().length());
					handler.endElement("", "", "age");
				}
				if (student.getSex() != null && student.getSex().trim().equals("")) {
					handler.startElement("", "", "sex", attr);
					handler.characters(student.getSex().toCharArray(), 0, student
							.getSex().length());
					handler.endElement("", "", "sex");
				}
				if (student.getHome() != null && student.getHome().trim().equals("")) {
					handler.startElement("", "", "home", attr);
					handler.characters(student.getHome().toCharArray(), 0, student
							.getHome().length());
					handler.endElement("", "", "home");
				}
				
				handler.endElement("", "", "student");
			}

			handler.endElement("", "", "students"); // �����ڵ�
			handler.endDocument(); // �ر� document
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}

	}
}
