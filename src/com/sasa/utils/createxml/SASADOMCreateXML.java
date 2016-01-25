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
			document.setXmlStandalone(true); // 把 Standalone 设置成 yes，就是没有 dtd 等说明文档
			
			Element students = document.createElement("students"); // 添加节点
			Element student = document.createElement("student");
			Element name = document.createElement("name"); // 子节点
			
			name.setTextContent("李凯伦");
			
			student.setAttribute("id", "1"); // 添加属性
			
			student.appendChild(name);
			students.appendChild(student); // 向 students 根节点中添加子节点 student
			document.appendChild(students); // 添加根节点
			
			// 创建 TransformerFactory 对象
			TransformerFactory tff = TransformerFactory.newInstance();
			// 创建 Transformer 对象
			Transformer tf = tff.newTransformer();
			tf.setOutputProperty(OutputKeys.INDENT, "yes"); // yes -> 换行
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
