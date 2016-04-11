package javaparser;

import java.util.ArrayList;

public class Method
{
    
    private String name;
    private String typeReturn;
    private String accessibility;
    private ArrayList<Variable> parameters;
    
    private int id;
    private ArrayList<Structure> statement;
    
    public Method()
    {
        this.typeReturn = "";
        this.parameters = new ArrayList<Variable>();
        this.statement = new ArrayList<Structure>();
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void addParameter(Variable parameter)
    {
        this.parameters.add(parameter);
    }
    
    public Variable getElementParameters(int index)
    {
        return this.parameters.get(index);
    }
    
    public int getSizeParameters()
    {
        return this.parameters.size();
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setTypeReturn(String typeReturn)
    {
        this.typeReturn = typeReturn;
    }
    
    public String getTypeReturn()
    {
        return this.typeReturn;
    }
    
    public void setAccessibility(String accessibility)
    {
        this.accessibility = accessibility;
    }
    
    public String getAccessibility()
    {
        return this.accessibility;
    }
    
    public String toString()
    {
        String resultat = this.accessibility + " " + this.typeReturn + " " + this.name + "(\n";
        
        for (int i=0;i<this.parameters.size();i++)
        {
            resultat += this.parameters.get(i).toString();
        }
        resultat += "){}\n";
        
        return resultat;
    }
    
    public Method(int id, String name)
    {
        this.statement = new ArrayList<Structure>();
        this.id = id;
        this.name = name;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public void addStatement(Structure structure)
    {
        this.statement.add(structure);
    }
    
    public int getStatementSize()
    {
        return this.statement.size();
    }
    
    public Structure getStatement(int index)
    {
        return this.statement.get(index);
    }
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\tEntry" + this.name + this.id + " [\n\t\tlabel = \"Entry " + this.name +"\"\n\t]\n";
        
        resultat += "\tExit" + this.name + this.id + " [\n\t\tlabel = \"Exit " + this.name +"\"\n\t]\n";
        
        if (this.statement.size() > 0)
        {
            resultat += "\tEntry" + this.name + this.id + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
            
            boolean returnPresent = false;
            for (int i=0;i<this.statement.size()-1;i++)
            {
                resultat += this.statement.get(i).getCFGFormatGraphviz();
                if (this.statement.get(i).getType().equals("Return"))
                {
                    returnPresent = true;
                    break;
                }
                else
                {
                    resultat += "\t" + this.statement.get(i).getNameNodeEnd() + " -> " + this.statement.get(i+1).getNameNodeBegin() + "\n";
                }
            }
            if (!returnPresent)
            {
                resultat += this.statement.get(this.statement.size() -1).getCFGFormatGraphviz();
                if (!this.statement.get(this.statement.size() -1).getType().equals("Return"))
                {
                    resultat += "\t" + this.statement.get(this.statement.size() - 1).getNameNodeEnd() + " -> Exit" + this.name + this.id + "\n";
                }
            }
        }
        else
        {
            resultat += "\tEntry" + this.name + this.id + " -> Exit" + this.name + this.id + "\n";
        }
        
        return resultat;
    }
    
    public String getDiagramDominateursFormatGraphViz()
    {
        String resultat = "\tEntry" + this.name + this.id + " [\n\t\tlabel = \"Entry " + this.name +"\"\n\t]\n";
        
        resultat += "\tExit" + this.name + this.id + " [\n\t\tlabel = \"Exit " + this.name +"\"\n\t]\n";
        
        if (this.statement.size() > 0)
        {
            resultat += this.statement.get(0).getDiagramDominateursFormatGraphViz();
            resultat += "\tEntry" + this.name + this.id + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
            
            boolean returnPresent = false;
            for (int i=1;i<this.statement.size();i++)
            {
                resultat += this.statement.get(i).getDiagramDominateursFormatGraphViz();
                resultat += "\t" + this.statement.get(i-1).getNameNodeEnd() + " -> " + this.statement.get(i).getNameNodeBegin() + "\n";
                if (this.statement.get(i).getType().equals("Return"))
                {
                    returnPresent = true;
                    break;
                }
            }
            if (!returnPresent && !this.statement.get(this.statement.size() -1).getType().equals("Return"))
            {
                resultat += "\t" + this.statement.get(this.statement.size() - 1).getNameNodeEnd() + " -> Exit" + this.name + this.id + "\n";
            }
        }
        else
        {
            resultat += "\tEntry" + this.name + this.id + " -> Exit" + this.name + this.id + "\n";
        }
        return resultat;
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        String resultat = "\tEntry" + this.name + this.id + " [\n\t\tlabel = \"Entry " + this.name +"\"\n\t]\n";
        
        resultat += "\tExit" + this.name + this.id + " [\n\t\tlabel = \"Exit " + this.name +"\"\n\t]\n";
        
        int i = 0;
        for (i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getDiagramPostDominateursFormatGraphViz();
            if (this.statement.get(i).getType().equals("Return"))
            {
                i++;
                break;
            }
        }
        
        if (this.statement.size() > 0)
        {
            if (!this.statement.get(i-1).getType().equals("Return"))
            {
                resultat += "\tExit" + this.name + this.id + " -> " + this.statement.get(i-1).getNameNodeEnd() + "\n";
            }
            resultat += "\t" + this.statement.get(0).getNameNodeBegin() + " -> Entry" + this.name + this.id + "\n";
            for (int j=i-1;j>0;j--)
            {
                resultat += "\t" + this.statement.get(j).getNameNodeBegin() + " -> " + this.statement.get(j-1).getNameNodeEnd() + "\n";
            }
        }
        else
        {
            resultat += "\tExit" + this.name + this.id + " -> Entry" + this.name + this.id + "\n";
        }
        
        return resultat;
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        String resultat = "\tEntry" + this.name + this.id + " [\n\t\tlabel = \"Entry " + this.name +"\"\n\t]\n";
        
        resultat += "\tExit" + this.name + this.id + " [\n\t\tlabel = \"Exit " + this.name +"\"\n\t]\n";
        
        if (this.statement.size() > 0)
        {
            resultat += "\tEntry" + this.name + this.id + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
            
            boolean returnPresent = false;
            for (int i=0;i<this.statement.size()-1;i++)
            {
                resultat += this.statement.get(i).getGraphEntryOutFormatGraphViz();
                if (this.statement.get(i).getType().equals("Return"))
                {
                    returnPresent = true;
                    break;
                }
                else
                {
                    resultat += "\t" + this.statement.get(i).getNameNodeBegin() + " -> " + this.statement.get(i+1).getNameNodeBegin() + "\n";
                }
            }
            if (!returnPresent)
            {
                resultat += this.statement.get(this.statement.size() - 1).getGraphEntryOutFormatGraphViz();
                if (!this.statement.get(this.statement.size() - 1).getType().equals("Return"))
                {
                    resultat += "\t" + this.statement.get(this.statement.size() - 1).getNameNodeBegin() + " -> Exit" + this.name + this.id + "\n";
                }
            }
        }
        else
        {
            resultat += "\tEntry" + this.name + this.id + " -> Exit" + this.name + this.id + "\n";
        }
        
        return resultat;
    }
    
    public void calculDependancesDonnees()
    {
        for (int i=0;i<this.statement.size();i++)
        {
            Structure structure = this.statement.get(i);
            for (int j=0;j<structure.variablesIntervenants.size();j++)
            {
                String variableName = structure.variablesIntervenants.get(j);
                for (int k=0;k<structure.in.size();k++)
                {
                    Structure in = structure.in.get(k);
                    if (in instanceof Variable && ((Variable)in).getName().equals(variableName))
                    {
                        structure.dependancesDonnees.add(in);
                    }
                }
            }
            if (structure.statement.size() > 0)
            {
                structure.calculDependancesDonnees();
            }
        }
    }
    
    public String getDependancesDonneesGraphViz()
    {
        this.calculDependancesDonnees();
        
        String resultat = "";
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getDependancesDonneesGraphViz();
        }
        
        return resultat;
    }
    
    public void calculPostDominateurs()
    {
        int i=0;
        for (i=0;i<this.statement.size();i++)
        {
            if (this.statement.get(i).getType().equals("Return"))
            {
                i++;
                break;
            }
        }
        i--;
        for (int j=i;j>0;j--)
        {
            this.statement.get(j-1).setPostDominateur(this.statement.get(j));
            if (this.statement.get(j).statement.size() > 0)
            {
                this.statement.get(j).calculPostDominateurs();
            }
        }
        if (this.statement.size() > 0 && this.statement.get(0).statement.size() > 0)
        {
            this.statement.get(0).calculPostDominateurs();
        }
    }
    
    public void calculDependancesControle()
    {
        for (int i=0;i<this.statement.size();i++)
        {
            Structure structure = this.statement.get(i);
            if (structure.getType().equals("For") || structure.getType().equals("While"))
            {
                structure.dependanceControle.add(structure);
            }
            else if (structure.getType().equals("Else") || structure.getType().equals("ElseIf") || structure.getType().equals("Catch"))
            {
                structure.dependanceControle.add(this.statement.get(i-1));
            }
            structure.calculDependancesControle();
        }
    }
    
    public String getDependancesControleGraphViz()
    {
        this.calculDependancesControle();
        
        String methodNodeName = "Entry" + this.name + this.id;
        
        String resultat = "\tEntry" + this.name + this.id + " [\n\t\tlabel = \"Entry " + this.name +"\"\n\t]\n";
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getDependancesControleGraphViz(methodNodeName);
        }
        
        return resultat;
    }
    
    public void backwardSlicing()
    {
        this.calculDependancesDonnees();
        this.calculDependancesControle();
        for (int i=0;i<this.statement.size();i++)
        {
            this.statement.get(i).backwardSlicing();
        }
    }
    
    public String getBackwardSlicingGraphViz()
    {
        this.backwardSlicing();
        
        String methodNodeName = "Entry" + this.name + this.id;
        
        String resultat = "";
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getBackwardSlicingGraphViz(methodNodeName);
        }
        
        return resultat;
    }
    
    public void forwardSlicing()
    {
        for (int i=0;i<this.statement.size();i++)
        {
            this.statement.get(i).forwardSlicing();
        }
    }
    
    public String getForwardSlicingGraphViz()
    {
        this.forwardSlicing();
        
        String resultat = "";
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getForwardSlicingGraphViz();
        }
        
        return resultat;
    }
    
    public String getForwardSlicingCSV()
    {
        String resultat = "";
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getForwardSlicingCSV();
        }
        return resultat;
    }
    
}
