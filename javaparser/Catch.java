package javaparser;

import java.util.ArrayList;

public class Catch extends Structure
{
    
    private ArrayList<Structure> statement;
    
    public Catch(int id)
    {
        super();
        this.statement = new ArrayList<Structure>();
        this.id = id;
        this.type = "Catch";
    }
    
    public void addStatement(Structure structure)
    {
        this.statement.add(structure);
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t" + this.getNameNodeBegin() + "[\n\t\tlabel = \"CatchBegin\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeEnd() + "[\n\t\tlabel = \"CatchEnd\"\n\t]\n";
        
        if (this.statement.size() > 0)
        {
            resultat += "\t" + this.getNameNodeBegin() + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
            
            boolean returnOrBreakPresent = false;
            for (int i=0;i<this.statement.size()-1;i++)
            {
                resultat += this.statement.get(i).getCFGFormatGraphviz();
                if (this.statement.get(i).getType().equals("Return") || this.statement.get(i).getType().equals("Break") || this.statement.get(i).getType().equals("Continue"))
                {
                    resultat += "\t" + this.statement.get(i).getNameNodeEnd() + " -> " + this.getNameNodeEnd() + "\n";
                    returnOrBreakPresent = true;
                    break;
                }
                else
                {
                    resultat += "\t" + this.statement.get(i).getNameNodeEnd() + " -> " + this.statement.get(i+1).getNameNodeBegin() + "\n";
                }
            }
            if (!returnOrBreakPresent)
            {
                resultat += this.statement.get(this.statement.size() - 1).getCFGFormatGraphviz();
                
                resultat += "\t" + this.statement.get(this.statement.size() - 1).getNameNodeEnd() + " -> " + this.getNameNodeEnd() + "\n";
            }
        }
        else
        {
            resultat += "\tStatement" + this.id + "[\n\t\tlabel = \"CatchStatement\"\n\t]\n";
            resultat += "\t" + this.getNameNodeBegin() + " -> Statement" + this.id + "\n";
            resultat += "\tStatement" + this.id + " -> " + this.getNameNodeEnd() + "\n";
        }
        
        return resultat;
    }
    
}
