package com.objectiva.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.objectiva.model.Employee;

public class XMLUtils {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

	private XMLUtils() {
		// prevent construction
	}

	@SuppressWarnings("unchecked")
	public static Map<String, List<Employee>> parseXML() {
		Map<String, List<Employee>> resultMap = new HashMap<String, List<Employee>>();
		String xmlPath = FileUtils.getXMLPath();
		SAXReader reader = new SAXReader();
		try {
			Document doc = reader.read(new File(xmlPath));
			List<Node> nodeList = doc.selectNodes("//month/employee");
			for (Iterator<Node> iter = nodeList.iterator(); iter.hasNext();) {
				Node emp = iter.next();
				Element element = emp.getParent();
				String month = element.attributeValue("value");
				String empName = emp.valueOf("@name");
				String empType = emp.valueOf("@type");
				String empBirthday = emp.valueOf("@birthday");
				String empWorkingHours = emp.valueOf("@workingHours");
				String empamount = emp.valueOf("@amount");
				Employee employee = new Employee();
				employee.setMonth(month);
				employee.setName(empName);
				employee.setType(empType);
				employee.setBirthday(empBirthday);
				employee.setWorkingHours(empWorkingHours);
				employee.setAmount(empamount);

				List<Employee> empList = resultMap.get(month);
				if (CollectionUtils.isEmpty(empList)) {
					empList = new ArrayList<Employee>();
					resultMap.put(month, empList);
				}
				empList.add(employee);
			}
		} catch (DocumentException e) {
			LOGGER.warn("Parsing the emplyee''s profile from XML error.");
		}
		return resultMap;
	}

}
