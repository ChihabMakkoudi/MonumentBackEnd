package ma.ac.emi.MonumentBackEnd.DAO;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;


import ma.ac.emi.MonumentBackEnd.Entities.Utilisateur;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserAdder;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserDeletter;
import ma.ac.emi.MonumentBackEnd.UserControllerpackage.IDBUserGetter;

public class UserDAO implements IDBUserAdder,IDBUserGetter,IDBUserDeletter{

    @Override
    public void deleteUtilisateur(String idUtilisateur) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Utilisateur getUtilisateur(String idUtilisateur) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Utilisateur getUtilisateur(String mail, String passeword) {
        // TODO Auto-generated method stub
        return null;
    }

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
        StreamResult result = new StreamResult(new File("/users/"+utilisateur.getMail()+".xml"));
        transformer.transform(source, result);
        
        // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
