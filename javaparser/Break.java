package javaparser;

public class Break extends Structure
{
    
    private Structure boucle;
    
    public Break(int id, Structure boucle)
    {
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
    
}
