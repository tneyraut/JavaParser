package javaparser;

public class Variable extends Structure
{
    
    private String name;
    private String accessibility;
    private String type;
    private int id;
    
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
    
    // CFG
    
    public Variable(int id, String name, String type)
    {
        this.id = id;
        this.name = name;
        this.type = type;
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
    
}
