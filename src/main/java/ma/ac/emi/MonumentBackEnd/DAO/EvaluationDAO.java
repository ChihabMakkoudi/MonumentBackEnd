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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

import ma.ac.emi.MonumentBackEnd.Entities.Editeur;
import ma.ac.emi.MonumentBackEnd.Entities.Evaluation;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationAdder;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationDeletter;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBEvaluationGetter;

public class EvaluationDAO implements IDBEvaluationGetter,IDBEvaluationAdder,IDBEvaluationDeletter{

    @Override
    public void deleteEvaluation(String idEvaluation) {
        File dir = new File("evaluations/");
        File[] files = dir.listFiles((dir1, name) -> name.equals(idEvaluation+".xml"));
        if (files.length>0)
            files[0].delete();
    }

    @Override
    public void addEvaluation(Evaluation evaluation) {
        
        try {
        DocumentBuilderFactory dbFactory =
        DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();
        
        // root element
        Element rootElement = doc.createElement("Evaluation");
        doc.appendChild(rootElement);
        
        // setting id attribute to evaluation
        Attr attr = doc.createAttribute("id");
        attr.setValue(evaluation.getId());
        rootElement.setAttributeNode(attr);

        // note element
        Element note = doc.createElement("note");
        note.appendChild(doc.createTextNode(Double.toString(evaluation.getNote())));
        rootElement.appendChild(note);

        // comment element
        Element comment = doc.createElement("comment");
        comment.appendChild(doc.createTextNode(evaluation.getCommentaire()));
        rootElement.appendChild(comment);

        // editeur
        Element editor = doc.createElement("editeur");
        // setting id attribute to the editor
        Attr editorid = doc.createAttribute("id");
        editorid.setValue(evaluation.getEditeur().getId());
        editor.setAttributeNode(editorid);
        editor.appendChild(doc.createTextNode(evaluation.getEditeur().getNomComplet()));
        rootElement.appendChild(editor);



        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        File xmlFile = new File("evaluations/"+evaluation.getId()+".xml");
        xmlFile.getParentFile().mkdirs();
        xmlFile.createNewFile();
        StreamResult result = new StreamResult(xmlFile);
        transformer.transform(source, result);
        
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public Evaluation getEvaluation(String idEvaluation) {

        File inputFile = new File("evaluations/"+idEvaluation+".xml");
        try {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();

        String id = doc.getDocumentElement().getAttribute("id");
        double note = Double.parseDouble(doc.getDocumentElement().getElementsByTagName("note").item(0).getTextContent());
        String commentaire = doc.getDocumentElement().getElementsByTagName("comment").item(0).getTextContent();
        
        // editor
        Node editeurNode = doc.getDocumentElement().getElementsByTagName("editeur").item(0);
        String editorId = editeurNode.getAttributes().getNamedItem("id").getTextContent();
        String nomComplet = editeurNode.getTextContent();
        Editeur editeur = new Editeur(editorId, nomComplet);
        
        
        return new Evaluation(id, note, commentaire, editeur);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    
}
