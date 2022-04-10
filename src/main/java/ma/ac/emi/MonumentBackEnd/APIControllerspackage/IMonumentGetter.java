package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import java.util.List;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;

public interface IMonumentGetter {

    public List<Monument> getMonuments(List<String> motClets);
    public Monument getMonument(String monumentId);
    
    
}
