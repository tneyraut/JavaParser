package javaparser;

public class Continue extends Structure
{
    
    private For boucleFor;
    
    public Continue(int id, For boucle)
    {
        this.boucleFor = boucle;
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
        
        resultat += "\t" + this.getNameNodeBegin() + " -> " + this.boucleFor.getNameNodeIteration() + "\n";
        
        return resultat;
    }
    
}
