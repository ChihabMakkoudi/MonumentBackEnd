package ma.ac.emi.MonumentBackEnd.DAO;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;


import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserAdder;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserDeletter;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserGetter;

@Repository
public class UserDAO implements IDBUserAdder,IDBUserGetter,IDBUserDeletter{

    @Override
    public void deleteUtilisateur(String idUtilisateur) {
        File dir = new File("users/");
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(idUtilisateur + "_"));
        if (files.length>0)
            files[0].delete();

    }

    // return the file but return null if doesnt exist
    @Override
    public Utilisateur getUtilisateur(String idUtilisateur) {
        File dir = new File("users/");
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(idUtilisateur+"_"));
        if (files.length<1) return null;
        File inputFile = files[0];
        try {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        String id = idUtilisateur;
        String nom = doc.getDocumentElement().getElementsByTagName("nom").item(0).getTextContent();
        String prenom = doc.getDocumentElement().getElementsByTagName("prenom").item(0).getTextContent();
        String mail = doc.getDocumentElement().getElementsByTagName("email").item(0).getTextContent();
        String password = doc.getDocumentElement().getElementsByTagName("password").item(0).getTextContent();
        
        
        return new Utilisateur(id, nom, prenom, mail, password);


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    // get user if mail and password are good 
    // return null otherwise
    @Override
    public Utilisateur getUtilisateur(String mail, String passeword) {
        File dir = new File("users/");
        File[] files = dir.listFiles((dir1, name) -> name.endsWith("_"+mail+".xml"));
        if (files.length<1) return null;
        File inputFile = files[0];
        try {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        String id = doc.getDocumentElement().getAttribute("id");
        String nom = doc.getDocumentElement().getElementsByTagName("nom").item(0).getTextContent();
        String prenom = doc.getDocumentElement().getElementsByTagName("prenom").item(0).getTextContent();
        String result_password = doc.getDocumentElement().getElementsByTagName("password").item(0).getTextContent();
        if (!passeword.equals(result_password)) return null;
        
        return new Utilisateur(id, nom, prenom, mail, passeword);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return null;
    }

    // Create file using the id and the email as the name of the file
    @Override
    public void addUtilisateur(Utilisateur utilisateur) {

        try {
        DocumentBuilderFactory dbFactory =
        DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        
        // root element
        Element rootElement = doc.createElement("Account");
        doc.appendChild(rootElement);
        
        // setting id attribute to account
        Attr attr = doc.createAttribute("id");
        attr.setValue(utilisateur.getId());
        rootElement.setAttributeNode(attr);

        // nom element
        Element nom = doc.createElement("nom");
        nom.appendChild(doc.createTextNode(utilisateur.getNom()));
        rootElement.appendChild(nom);

        // nom element
        Element prenom = doc.createElement("prenom");
        prenom.appendChild(doc.createTextNode(utilisateur.getPrenom()));
        rootElement.appendChild(prenom);

        // email element
        Element email = doc.createElement("email");
        email.appendChild(doc.createTextNode(utilisateur.getMail()));
        rootElement.appendChild(email);

        // password element
        Element password = doc.createElement("password");
        password.appendChild(doc.createTextNode(utilisateur.getPasseword()));
        rootElement.appendChild(password);

        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File xmlFile = new File("users/"+utilisateur.getId()+"_"+utilisateur.getMail()+".xml");
        xmlFile.getParentFile().mkdirs();
        xmlFile.createNewFile();
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
