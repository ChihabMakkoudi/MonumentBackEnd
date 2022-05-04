package ma.ac.emi.MonumentBackEnd.DAO;

import java.io.File;
import java.util.List;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBMonumentAdder;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBMonumentDeletter;
import ma.ac.emi.MonumentBackEnd.MonumentControllerpackage.IDBMonumentGetter;

public class MonumentDAO implements IDBMonumentGetter,IDBMonumentAdder,IDBMonumentDeletter {

    @Override
    public Monument getMonument(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Monument> getMonuments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteMonument(String idMonument) {
        File monumentFile = new File("monuments/"+idMonument+".xml");
        monumentFile.delete();
    }

    @Override
    public void addMonument(Monument monument) {
        // TODO Auto-generated method stub
        
    }

    
}