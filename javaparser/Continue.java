package javaparser;

public class Continue extends Structure
{
    
    private Structure boucle;
    
    public Continue(int id, Structure boucle)
    {
        super();
        this.boucle = boucle;
        this.id = id;
        this.type = "Continue";
    }
    
    public String getNameNodeBegin()
    {
        return "Continue" + this.id;
    }
    
    public String getNameNodeEnd()
    {
        return "Continue" + this.id;
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Continue\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> " + this.boucle.getNameNodeBegin() + "\n";
        
        return resultat;
    }
    
    public String getDiagramDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Continue\"\n\t]\n";
        
        return resultat;
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Continue\"\n\t]\n";
        
        resultat = "\t" + this.boucle.getNameNodeEnd() + " -> " + this.getNameNodeBegin() + "\n";
        
        return resultat;
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        String label = "Continue\nid = " + this.id + "\n" + this.getInString() + "\n" + this.getGenString() + "\n" + this.getKillString() + "\n" + this.getOutString();
        
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \""+ label + "\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> " + this.boucle.getNameNodeBegin() + "\n";
        
        return resultat;
    }
    
}
