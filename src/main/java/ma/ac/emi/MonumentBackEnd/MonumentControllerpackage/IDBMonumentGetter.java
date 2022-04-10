package ma.ac.emi.MonumentBackEnd.MonumentControllerpackage;

import java.util.List;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;

public interface IDBMonumentGetter {

    public Monument getMonument(String id); 

    public List<Monument> getMonuments(); 
        
    
}
