package javaparser;

public class Try extends Structure
{
    
    public Try(int id)
    {
        super();
        this.id = id;
        this.type = "Try";
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\t" + this.getNameNodeBegin() + "[\n\t\tlabel = \"TryBegin\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeEnd() + "[\n\t\tlabel = \"TryEnd\"\n\t]\n";
        
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
            resultat += "\tStatement" + this.id + "[\n\t\tlabel = \"TryStatement\"\n\t]\n";
            resultat += "\t" + this.getNameNodeBegin() + " -> Statement" + this.id + "\n";
            resultat += "\tStatement" + this.id + " -> " + this.getNameNodeEnd() + "\n";
        }
        
        return resultat;
    }
    
    public String getDiagramDominateursFormatGraphViz()
    {
        String resultat = "\t" + this.getNameNodeBegin() + "[\n\t\tlabel = \"TryBegin\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeEnd() + "[\n\t\tlabel = \"TryEnd\"\n\t]\n";
        
        if (this.statement.size() > 0)
        {
            resultat += "\t" + this.getNameNodeBegin() + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
            
            boolean returnBreakContinuePresent = false;
            for (int i=0;i<this.statement.size();i++)
            {
                resultat += this.statement.get(i).getDiagramDominateursFormatGraphViz();
                if (i != 0)
                {
                    resultat += "\t" + this.statement.get(i-1).getNameNodeEnd() + " -> " + this.statement.get(i).getNameNodeBegin() + "\n";
                }
                if (this.statement.get(i).getType().equals("Return") || this.statement.get(i).getType().equals("Break") || this.statement.get(i).getType().equals("Continue"))
                {
                    returnBreakContinuePresent = true;
                    break;
                }
            }
            if (!returnBreakContinuePresent)
            {
                resultat += "\t" + this.statement.get(this.statement.size() - 1).getNameNodeEnd() + " -> " + this.getNameNodeEnd() + "\n";
            }
        }
        else
        {
            resultat += "\tStatement" + this.id + "[\n\t\tlabel = \"TryStatement\"\n\t]\n";
            resultat += "\t" + this.getNameNodeBegin() + " -> Statement" + this.id + "\n";
            resultat += "\tStatement" + this.id + " -> " + this.getNameNodeEnd() + "\n";
        }
        return resultat;
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \"TryBegin\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeEnd() + "[\n\t\tlabel = \"TryEnd\"\n\t]\n";
        
        resultat += "\t" + this.getNameNodeEnd() + " -> " + this.getNameNodeBegin() + "\n";
        
        int i = 0;
        for (i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getDiagramPostDominateursFormatGraphViz();
            if (this.statement.get(i).getType().equals("Return") || this.statement.get(i).getType().equals("Break") || this.statement.get(i).getType().equals("Continue"))
            {
                i++;
                break;
            }
        }
        
        if (this.statement.size() > 0)
        {
            if (!this.statement.get(i-1).getType().equals("Return") && !this.statement.get(i-1).getType().equals("Break") && !this.statement.get(i-1).getType().equals("Continue"))
            {
                resultat += "\t" + this.getNameNodeBegin() + " -> " + this.statement.get(i-1).getNameNodeEnd() + "\n";
            }
            for (int j=i-1;j>0;j--)
            {
                resultat += "\t" + this.statement.get(j).getNameNodeBegin() + " -> " + this.statement.get(j-1).getNameNodeEnd() + "\n";
            }
        }
        else
        {
            resultat += "\tStatement" + this.id + "[\n\t\tlabel = \"TryStatement\"\n\t]\n";
            resultat += "\t" + this.getNameNodeBegin() + " -> Statement" + this.id + "\n";
        }
        
        return resultat;
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        String label = "Try\nid = " + this.id + "\n" + this.getInString() + "\n" + this.getGenString() + "\n" + this.getKillString() + "\n" + this.getOutString();
        
        String resultat = "\t"+ this.getNameNodeBegin() + "[\n\t\tlabel = \""+ label + "\"\n\t]\n";
        
        if (this.statement.size() > 0)
        {
            resultat += "\t" + this.getNameNodeBegin() + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
        }
        
        boolean returnBreakContinuePresent = false;
        for (int i=0;i<this.statement.size()-1;i++)
        {
            resultat += this.statement.get(i).getGraphEntryOutFormatGraphViz();
            if (this.statement.get(i).getType().equals("Return") || this.statement.get(i).getType().equals("Break") || this.statement.get(i).getType().equals("Continue"))
            {
                returnBreakContinuePresent = true;
                break;
            }
            else
            {
                resultat += "\t" + this.statement.get(i).getNameNodeBegin() + " -> " + this.statement.get(i+1).getNameNodeBegin() + "\n";
            }
        }
        if (!returnBreakContinuePresent && this.statement.size() > 0)
        {
            resultat += this.statement.get(this.statement.size() - 1).getGraphEntryOutFormatGraphViz();
            
            resultat += "\t" + this.statement.get(this.statement.size() - 1).getNameNodeBegin() + " -> " + this.getNameNodeBegin() + "\n";
        }
        return resultat;
    }
    
}
