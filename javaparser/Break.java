package javaparser;

public class Break extends Structure
{
    
    private Structure boucle;
    
    public Break(int id, Structure boucle)
    {
        super();
        this.boucle = boucle;
        this.id = id;
        this.type = "Break";
    }
    
    public String getNameNodeBegin()
    {
        return "Break" + this.id;
    }
    
    public String getNameNodeEnd()
    {
        return "Break" + this.id;
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Break\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> " + this.boucle.getNameNodeEnd() + "\n";
        
        return resultat;
    }
    
    public String getDiagramDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Break\"\n\t]\n";
        
        return resultat;
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Break\"\n\t]\n";
        
        resultat = "\t" + this.boucle.getNameNodeEnd() + " -> " + this.getNameNodeBegin() + "\n";
        
        return resultat;
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        String label = "Break\nid = " + this.id + "\n" + this.getInString() + "\n" + this.getGenString() + "\n" + this.getKillString() + "\n" + this.getOutString();
        
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \""+ label + "\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> " + this.boucle.getNameNodeBegin() + "\n";
        
        return resultat;
    }
    
}
