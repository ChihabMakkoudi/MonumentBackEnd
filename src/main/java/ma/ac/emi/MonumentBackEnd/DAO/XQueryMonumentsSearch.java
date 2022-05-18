package ma.ac.emi.MonumentBackEnd.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBMonumentFilter;

@Component
public class XQueryMonumentsSearch implements IDBMonumentFilter{
    
    @Autowired
    private MonumentDAO monumentDAO;

    public List<Monument> getMonumentsByMotCle(List<String> motcles) {
        File dir = new File("monuments/");
        File[] files = dir.listFiles((dir1, name) -> {
            return name.endsWith(".xml");
        });
        String id = "";
        ArrayList<Monument> monuments = new ArrayList<>();
        for (File file: files) {
            for (String motcle: motcles) {
                if (containsMotCle(file, motcle)) {
                    id = file.getName().substring(0, file.getName().length()-4);
                    monuments.add(monumentDAO.getMonument(id));
                    break;
                }
            }
        }
        return monuments;
    }

    public Boolean containsMotCle(File monumentFile, String motCle) {

        String query = 
            "count( //nom[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"+motCle+"')] | //description[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'"+motCle+"')])";

        try {
        FileInputStream fileIS = new FileInputStream(monumentFile);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(fileIS);
        XPath xPath = XPathFactory.newInstance().newXPath();

        //return (Boolean) xPath.compile(query).evaluate(xmlDocument, XPathConstants.BOOLEAN);
        return  ((Double)xPath.compile(query).evaluate(xmlDocument, XPathConstants.NUMBER)).intValue() !=0;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("xquery not working");
        }
        return false;

    }

}
