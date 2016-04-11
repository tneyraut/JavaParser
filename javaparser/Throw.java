package javaparser;

public class Throw extends Structure
{
    
    public Throw(int id)
    {
        super();
        this.id = id;
        this.type = "Throw";
    }
    
    public String getNameNodeBegin()
    {
        return "Throw" + this.id;
    }
    
    public String getNameNodeEnd()
    {
        return "Throw" + this.id;
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Throw\"\n\t]\n";
        
        return resultat;
    }
    
    public String getDiagramDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Throw\"\n\t]\n";
        
        return resultat;
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Throw\"\n\t]\n";
        
        return resultat;
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        String label = "Throw\nid = " + this.id + "\n" + this.getInString() + "\n" + this.getGenString() + "\n" + this.getKillString() + "\n" + this.getOutString();
        
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \""+ label + "\"\n\t]\n";
        
        return resultat;
    }
    
}
