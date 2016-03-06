package javaparser;

import java.util.ArrayList;

public class Switch extends Structure
{
    
    private ArrayList<Structure> casesArray;
    
    public Switch(int id)
    {
        super();
        this.casesArray = new ArrayList<Structure>();
        this.id = id;
        this.type = "Switch";
    }
    
    public void addStatement(Structure structure)
    {
        this.casesArray.add(structure);
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"SwitchBegin\"\n\t]\n";
        
        for (int i=0;i<this.casesArray.size();i++)
        {
            resultat += this.casesArray.get(i).getCFGFormatGraphviz();
        }
        
        resultat += "\t" + this.getNameNodeEnd() + "[\n\t\tlabel = \"SwitchEnd\"\n\t]\n";
        
        if (this.casesArray.size() > 0)
        {
            resultat += "\t" + this.getNameNodeBegin() + " -> " + this.casesArray.get(0).getNameNodeBegin() + "\n";
            resultat += "\t" + this.casesArray.get(this.casesArray.size() - 1).getNameNodeEnd() + " -> " + this.getNameNodeEnd() + "\n";
        }
        else
        {
            resultat += "\tStatement" + this.id + "[\n\t\tlabel = \"SwitchStatement\"\n\t]\n";
            resultat += "\t" + this.getNameNodeBegin() + " -> Statement" + this.id + "\n";
            resultat += "\tStatement" + this.id + " ->" + this.getNameNodeEnd() + "\n";
        }
        
        for (int i=0;i<this.casesArray.size()-1;i++)
        {
            resultat += "\t" + this.casesArray.get(i).getNameNodeEnd() + " -> " + this.casesArray.get(i+1).getNameNodeBegin() + "\n";
        }
        
        return resultat;
    }
    
}
