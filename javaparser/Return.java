package javaparser;

public class Return extends Structure
{
    
    private Method method;
    
    public Return(int id, Method method)
    {
        this.id = id;
        this.type = "Return";
        this.method = method;
    }
    
    public String getNameNodeBegin()
    {
        return "Return" + this.id;
    }
    
    public String getNameNodeEnd()
    {
        return "Return" + this.id;
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Return\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> Exit" + this.method.getName() + this.method.getId() + "\n";
        
        return resultat;
    }
    
}
