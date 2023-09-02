package ru.netology.parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.netology.Employee;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlJsonParser implements Parser {
    private final String fileName;

    public XmlJsonParser(String fileName) {
        this.fileName = fileName;
    }
    @Override
    public void parsing() {
        toJSON(fromXml(), "src/main/resources/data2.json");
    }

    private List<Employee> fromXml() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            Node node = doc.getDocumentElement();
            NodeList emps = node.getChildNodes();
            List<Employee> employees = new ArrayList<>();
            for (int i = 0; i < emps.getLength(); i++) {
                Node emp = emps.item(i);

                if (emp.getNodeType() != Node.TEXT_NODE) {
                    NodeList empProps = emp.getChildNodes();
                    List<String> emplsString = new ArrayList<>();
                    for (int j = 0; j < empProps.getLength(); j++) {
                        Node empProp = empProps.item(j);

                        if (empProp.getNodeType() != Node.TEXT_NODE) {
                            emplsString.add(empProp.getChildNodes().item(0).getTextContent());
                        }
                    }
                    employees.add(new Employee(Long.parseLong(emplsString.get(0)), emplsString.get(1),
                            emplsString.get(2), emplsString.get(3), Integer.parseInt(emplsString.get(4))));
                }
            }
            return employees;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }


}
