package com.sasa.utils.createxml;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SASADOMCreateXML {
	public static void DOMCreateXML() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.newDocument();
			document.setXmlStandalone(true); // �� Standalone ���ó� yes������û�� dtd ��˵���ĵ�
			
			Element students = document.createElement("students"); // ��ӽڵ�
			Element student = document.createElement("student");
			Element name = document.createElement("name"); // �ӽڵ�
			
			name.setTextContent("���");
			
			student.setAttribute("id", "1"); // �������
			
			student.appendChild(name);
			students.appendChild(student); // �� students ���ڵ�������ӽڵ� student
			document.appendChild(students); // ��Ӹ��ڵ�
			
			// ���� TransformerFactory ����
			TransformerFactory tff = TransformerFactory.newInstance();
			// ���� Transformer ����
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes"); // yes -> ����
			tf.transform(new DOMSource(document), new StreamResult(new File("DOMForStudents.xml")));
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
}
