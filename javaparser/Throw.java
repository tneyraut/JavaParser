package javaparser;

public class Throw extends Structure
{
    
    public Throw(int id)
    {
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
    
}
