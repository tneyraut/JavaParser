package javaparser;

public class Variable
{
    
    private String name;
    private String accessibility;
    private String type;
    
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
    
}
