package javaparser;

import java.util.ArrayList;

public class Variable extends Structure
{
    
    private String name;
    private String accessibility;
    private String type;
    
    private ArrayList<Structure> forwardSlicingArray;
    
    public Variable()
    {
        this.accessibility = "";
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setAccessibility(String accessibility)
    {
        this.accessibility = accessibility;
    }
    
    public String getAccessibility()
    {
        return this.accessibility;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public String toString()
    {
        return this.accessibility + " " + this.type + " " + this.name + ";\n";
    }
    
    public Variable(int id, String name, String type)
    {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
        
        this.forwardSlicingArray = new ArrayList<Structure>();
    }
    
    public String getNameNodeBegin()
    {
        return this.type + this.id;
    }
    
    public String getNameNodeEnd()
    {
        return this.type + this.id;
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t" + this.type + this.id + " [\n\t\tlabel = \"" + this.type + " " + this.name +"\"\n\t]\n";
        
        return resultat;
    }
    
    public String getDiagramDominateursFormatGraphViz()
    {
        String resultat = "\t" + this.type + this.id + " [\n\t\tlabel = \"" + this.type + " " + this.name +"\"\n\t]\n";
        
        return resultat;
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        String resultat = "\t" + this.type + this.id + " [\n\t\tlabel = \"" + this.type + " " + this.name +"\"\n\t]\n";
        
        return resultat;
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        String label = this.type + " de " + this.name + "\nid = " + this.id + "\n" + this.getInString() + "\n" + this.getGenString() + "\n" + this.getKillString() + "\n" + this.getOutString();
        
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \""+ label + "\"\n\t]\n";
        
        return resultat;
    }
    
    public void addElementInForwardSlicingArray(Structure structure)
    {
        this.forwardSlicingArray.add(structure);
    }
    
    public String getForwardSlicingGraphViz()
    {
        String resultat = "";
        
        if (this.forwardSlicingArray.size() > 0)
        {
            resultat += "\t" + this.getNameNodeBegin() + " [\n\t\tlabel = \"" + this.type + " " + this.name + "\nid = " + this.id +"\"\n\t]\n";
        }
        
        for (int i=0;i<this.forwardSlicingArray.size();i++)
        {
            resultat += "\t" + this.forwardSlicingArray.get(i).getNameNodeBegin() + " [\n\t\tlabel = \"" + this.forwardSlicingArray.get(i).getType() + " " + this.forwardSlicingArray.get(i).getName() + "\nid = " + this.forwardSlicingArray.get(i).id +"\"\n\t]\n";
            resultat += "\t" + this.getNameNodeBegin() + " -> " + this.forwardSlicingArray.get(i).getNameNodeBegin() + "\n";
        }
        
        return resultat;
    }
    
    public String getForwardSlicingCSV()
    {
        String resultat = "";
        
        String text = this.type + ";" + this.name + ";" + this.id + ";;";
        
        for (int i=0;i<this.forwardSlicingArray.size();i++)
        {
            resultat += text + this.forwardSlicingArray.get(i).getType() + ";" + this.forwardSlicingArray.get(i).getName() + ";" + this.forwardSlicingArray.get(i).id + "\n";
        }
        
        return resultat;
    }
}
