package ma.ac.emi.MonumentBackEnd.APIControllerspackage;

import ma.ac.emi.MonumentBackEnd.Entities.Monument;

public interface IMonumentManager {

    public void addMonument(Monument monument);
    public void editMonument(String monumentId, Monument monument);
    
}
