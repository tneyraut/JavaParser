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
    
    // CFG
    
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
    
    public String getCFGFormatGraphviz()
    {
        String resultat = "\tEntry" + this.name + this.id + " [\n\t\tlabel = \"Entry " + this.name +"\"\n\t]\n";
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getCFGFormatGraphviz();
        }
        
        resultat += "\tExit" + this.name + this.id + " [\n\t\tlabel = \"Exit " + this.name +"\"\n\t]\n";
        
        if (this.statement.size() > 0)
        {
            resultat += "\tEntry" + this.name + this.id + " -> " + this.statement.get(0).getNameNodeBegin() + "\n";
            
            boolean returnPresent = false;
            for (int i=0;i<this.statement.size()-1;i++)
            {
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
    
}
