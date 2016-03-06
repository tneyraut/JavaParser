package javaparser;

import java.util.ArrayList;

public class For extends Structure
{
    
    private ArrayList<Structure> statement;
    
    public For(int id)
    {
        super();
        this.statement = new ArrayList<Structure>();
        this.id = id;
        this.type = "For";
    }
    
    public void addStatement(Structure structure)
    {
        this.statement.add(structure);
    }
    
    public String getNameNodeIteration()
    {
        return "Iteration" + this.id;
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"ForBegin\"\n\t]\n";
        
        resultat += "\tInitialisation" + this.id + "[\n\t\tlabel = \"Initialisation\"\n\t]\n";
        
        resultat += "\tCondition" + this.id + "[\n\t\tlabel = \"Condition\"\n\t]\n";
        
        resultat += "\tIteration" + this.id + "[\n\t\tlabel = \"Iteration\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeEnd() + "[\n\t\tlabel = \"ForEnd\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeBegin() + " -> Initialisation" + this.id + "\n";
        
        resultat += "\tInitialisation" + this.id + " -> Condition" + this.id + "\n";
        
        if (this.statement.size() > 0)
        {
            resultat += "\tCondition" + this.id + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
            
            boolean returnOrBreakPresent = false;
            for (int i=0;i<this.statement.size()-1;i++)
            {
                resultat += this.statement.get(i).getCFGFormatGraphviz();
                if (this.statement.get(i).getType().equals("Break") || this.statement.get(i).getType().equals("Continue"))
                {
                    returnOrBreakPresent = true;
                    break;
                }
                else if (this.statement.get(i).getType().equals("Return"))
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
                
                resultat += "\t" + this.statement.get(this.statement.size() - 1).getNameNodeEnd() + " -> Iteration" + this.id + "\n";
                resultat += "\tIteration" + this.id + " -> Condition" + this.id + "\n";
                resultat += "\tCondition" + this.id + " -> " + this.getNameNodeEnd() + "\n";
            }
        }
        else
        {
            resultat += "\tStatement" + this.id + "[\n\t\tlabel = \"ForStatement\"\n\t]\n";
            resultat += "\tCondition" + this.id + " -> Statement" + this.id + "\n";
            resultat += "\tStatement" + this.id + " -> Iteration" + this.id + "\n";
            resultat += "\tIteration" + this.id + " -> Condition" + this.id + "\n";
            resultat += "\tCondition" + this.id + " -> " + this.getNameNodeEnd() + "\n";
        }
        
        return resultat;
    }
    
}
