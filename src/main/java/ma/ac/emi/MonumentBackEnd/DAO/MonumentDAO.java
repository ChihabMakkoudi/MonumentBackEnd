package ma.ac.emi.MonumentBackEnd.DAO;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBMonumentAdder;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBMonumentDeletter;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBMonumentGetter;

@Component
public class MonumentDAO implements IDBMonumentGetter,IDBMonumentAdder,IDBMonumentDeletter {

    @Override
    public Monument getMonument(String id) {
        File monumentFile = new File("monuments/"+id+".xml");
        XmlMapper xmlMapper = new XmlMapper();
        String xml;
        try {
            xml = inputStreamToString(new FileInputStream(monumentFile));
            Monument monument = xmlMapper.readValue(xml, Monument.class);
            return monument;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Monument> getMonuments() {
        List<Monument> monuments =  new ArrayList<>();
        File dir = new File("monuments/");
        File[] files = dir.listFiles((dir1, name) -> {
            return name.endsWith(".xml");
        });
        String id;
        for (File file : files) {
            id = file.getName().substring(0, file.getName().length()-4);
            monuments.add(getMonument(id));
        }

        return monuments;
    }

    @Override
    public void deleteMonument(String idMonument) {
        File monumentFile = new File("monuments/"+idMonument+".xml");
        monumentFile.delete();
    }

    @Override
    public void addMonument(Monument monument) {
        File xmlFile = new File("monuments/"+ monument.getId()+".xml");
        xmlFile.getParentFile().mkdirs();
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writeValue(xmlFile, monument);
        } catch (Exception e) {
            e.printStackTrace();
        }

            
        try {
        Path fileName
            = Path.of(xmlFile.getAbsolutePath());
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
        str+= "<Monument xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"../src/main/resources/templates/Monument.xsd\">"; 

        str += Files.readString(fileName).substring(10);
        Files.writeString(fileName, str,
            StandardCharsets.UTF_8);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
       
        
    }



    public String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    
}