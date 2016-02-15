package javaparser;

import java.util.ArrayList;

public class Method
{
    
    private String name;
    private String typeReturn;
    private String accessibility;
    private ArrayList<Variable> parameters;
    
    public Method()
    {
        this.typeReturn = "";
        this.parameters = new ArrayList<Variable>();
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
    
}
