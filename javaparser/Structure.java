package javaparser;

public class Structure
{
    
    protected String type;
    protected int id;
    private boolean open;
    
    public Structure()
    {
        this.open = true;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public void close()
    {
        this.open = false;
    }
    
    public boolean isOpen()
    {
        return this.open;
    }
    
    public String getCFGFormatGraphviz()
    {
        return "";
    }
    
    public String getNameNodeBegin()
    {
        return this.type + "Begin" + this.id;
    }
    
    public String getNameNodeEnd()
    {
        return this.type + "End" + this.id;
    }
    
    public void addStatement(Structure structure)
    {
        
    }
    
}
