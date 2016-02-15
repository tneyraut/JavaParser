package javaparser;

import java.util.ArrayList;

public class Classe
{
    
    private String packageName;
    private String name;
    private String accessibility;
    private ArrayList<Method> methods;
    private ArrayList<Variable> variables;
    private ArrayList<String> implementsArray;
    private String superClassName;
    private boolean isInterface;
    
    public Classe()
    {
        this.isInterface = false;
        this.superClassName = "";
        this.methods = new ArrayList<Method>();
        this.variables = new ArrayList<Variable>();
        this.implementsArray = new ArrayList<String>();
    }
    
    public void addImplement(String implementName)
    {
        this.implementsArray.add(implementName);
    }
    
    public int getSizeImplementsArray()
    {
        return this.implementsArray.size();
    }
    
    public String getImplement(int index)
    {
        return this.implementsArray.get(index);
    }
    
    public void setIsInterface(boolean isInterface)
    {
        this.isInterface = isInterface;
    }
    
    public void setPackageName(String packageName)
    {
        this.packageName = packageName;
    }
    
    public String getPackageName()
    {
        return this.packageName;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void addMethod(Method method)
    {
        this.methods.add(method);
    }
    
    public Method getMethod(int index)
    {
        return this.methods.get(index);
    }
    
    public int getSizeMethods()
    {
        return this.methods.size();
    }
    
    public void addVariable(Variable variable)
    {
        this.variables.add(variable);
    }
    
    public Variable getVariable(int index)
    {
        return this.variables.get(index);
    }
    
    public int getSizeVariables()
    {
        return this.variables.size();
    }
    
    public String getAccessibility()
    {
        return this.accessibility;
    }
    
    public void setAccessibility(String accessibility)
    {
        this.accessibility = accessibility;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setSuperClassName(String superClassName)
    {
        this.superClassName = superClassName;
    }
    
    public String getSuperClassName()
    {
        return this.superClassName;
    }
    
    public String toString()
    {
        String resultat;
        if (this.isInterface)
        {
            resultat = "Interface ";
        }
        else
        {
            resultat = "Class ";
        }
        resultat += this.accessibility + " " + this.name;
        if (!this.superClassName.equals(""))
        {
            resultat += " extends " + this.superClassName;
        }
        resultat += " {\n";
        
        for (int i=0;i<this.variables.size();i++)
        {
            resultat += this.variables.get(i).toString();
        }
        
        for (int i=0;i<this.methods.size();i++)
        {
            resultat += this.methods.get(i).toString();
        }
        
        resultat += "}\n";
        
        return resultat;
    }
    
}
