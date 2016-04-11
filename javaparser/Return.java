package javaparser;

public class Return extends Structure
{
    
    private Method method;
    
    public Return(int id, Method method)
    {
        super();
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
    
    public String getDiagramDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Return\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> Exit" + this.method.getName() + this.method.getId() + "\n";
        
        return resultat;
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"Return\"\n\t]\n";
        
        resultat += "\tExit" + this.method.getName() + this.method.getId() + " -> " + this.getNameNodeBegin();
        
        return resultat;
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        String label = "Return\nid = " + this.id + "\n" + this.getInString() + "\n" + this.getGenString() + "\n" + this.getKillString() + "\n" + this.getOutString();
        
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \""+ label + "\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> Exit" + this.method.getName() + this.method.getId() + "\n";
        
        return resultat;
    }
    
}
