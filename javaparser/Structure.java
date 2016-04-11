package javaparser;

import java.util.ArrayList;

public class Structure
{
    
    protected String type;
    protected int id;
    protected ArrayList<Structure> statement;
    
    protected Structure dominateur;
    
    protected Structure postDominateur;
    
    protected Variable gen;
    protected ArrayList<Structure> kill;
    protected ArrayList<Structure> in;
    protected ArrayList<Structure> out;
    
    protected ArrayList<String> variablesIntervenants;
    
    protected ArrayList<Structure> dependancesDonnees;
    
    protected ArrayList<Structure> dependanceControle;
    
    protected ArrayList<ArrayList<Structure>> backwardSlicingArray;
    private boolean allreadyPrint;
    
    public Structure()
    {
        this.statement = new ArrayList<Structure>();
        
        this.dominateur = null;
        
        this.postDominateur = null;
        
        this.gen = null;
        this.kill = new ArrayList<Structure>();
        this.in = new ArrayList<Structure>();
        this.out = new ArrayList<Structure>();
        
        this.variablesIntervenants = new ArrayList<String>();
        
        this.dependancesDonnees = new ArrayList<Structure>();
        
        this.dependanceControle = new ArrayList<Structure>();
        
        this.backwardSlicingArray = new ArrayList<ArrayList<Structure>>();
        this.allreadyPrint = false;
    }
    
    public String getGenString()
    {
        String resultat = "Gen = ( ";
        if (this.gen != null)
        {
            resultat += this.gen.id;
        }
        resultat += " )";
        return resultat;
    }
    
    public String getKillString()
    {
        String resultat = "Kill = ( ";
        if (this.kill.size() > 0)
        {
            resultat += this.kill.get(0).id;
            for (int i=1;i<this.kill.size();i++)
            {
                resultat += " ; " + this.kill.get(i).id;
            }
        }
        resultat += " )";
        return resultat;
    }
    
    public String getInString()
    {
        String resultat = "In = ( ";
        if (this.in.size() > 0)
        {
            resultat += this.in.get(0).id;
            for (int i=1;i<this.in.size();i++)
            {
                resultat += " ; " + this.in.get(i).id;
            }
        }
        resultat += " )";
        return resultat;
    }
    
    public String getOutString()
    {
        String resultat = "Out = ( ";
        if (this.out.size() > 0)
        {
            resultat += this.out.get(0).id;
            for (int i=1;i<this.out.size();i++)
            {
                resultat += " ; " + this.out.get(i).id;
            }
        }
        resultat += " )";
        return resultat;
    }
    
    public boolean structureCanContainStatement()
    {
        return (this instanceof Variable || this instanceof Break || this instanceof Continue || this instanceof Return || this instanceof Throw);
    }
    
    public boolean structureIsForIfWhile()
    {
        return (this instanceof For || this instanceof If || this instanceof While || this instanceof Case);
    }
    
    public void setDominateur(Structure structure)
    {
        this.dominateur = structure;
    }
    
    public void setPostDominateur(Structure structure)
    {
        this.postDominateur = structure;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public String getCFGFormatGraphviz()
    {
        return "";
    }
    
    public String getDiagramDominateursFormatGraphViz()
    {
        return "";
    }
    
    public String getDiagramPostDominateursFormatGraphViz()
    {
        return "";
    }
    
    public String getGraphEntryOutFormatGraphViz()
    {
        return "";
    }
    
    public String getName()
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
        this.statement.add(structure);
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
        String resultat = "";
        if (this.dependancesDonnees.size() > 0)
        {
            resultat += "\t" + this.getNameNodeBegin() + " [\n\t\tlabel = \"" + this.getType() + " " + this.getName() + "\nid = " + this.id +"\"\n\t]\n";
        }
        for (int i=0;i<this.dependancesDonnees.size();i++)
        {
            Structure structure = this.dependancesDonnees.get(i);
            resultat += "\t" + structure.getNameNodeBegin() + " [\n\t\tlabel = \"" + structure.getType() + " " + structure.getName() + "\nid = " + structure.id +"\"\n\t]\n";
            resultat += "\t" + structure.getNameNodeBegin() + " -> " + this.getNameNodeBegin() + "\n";
        }
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getDependancesDonneesGraphViz();
        }
        
        return resultat;
    }
    
    public void calculPostDominateurs()
    {
        if (this.statement.size() > 0)
        {
            this.statement.get(this.statement.size() - 1).setPostDominateur(this);
        }
        int i=0;
        for (i=0;i<this.statement.size();i++)
        {
            if (this.statement.get(i).getType().equals("Return") || this.statement.get(i).getType().equals("Break") || this.statement.get(i).getType().equals("Continue"))
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
    
    public boolean postDominateurIs(Structure structure)
    {
        if (this.postDominateur == structure)
        {
            return true;
        }
        if (this.postDominateur == null)
        {
            return false;
        }
        return this.postDominateur.postDominateurIs(structure);
    }
    
    public void calculDependancesControle()
    {
        for (int i=0;i<this.statement.size();i++)
        {
            Structure structure = this.statement.get(i);
            if (structure.getType().equals("Else") || structure.getType().equals("ElseIf") || structure.getType().equals("Catch"))
            {
                structure.dependanceControle.add(this.statement.get(i-1));
            }
            else
            {
                if (structure.getType().equals("For") || structure.getType().equals("While"))
                {
                    structure.dependanceControle.add(structure);
                }
                structure.dependanceControle.add(this);
            }
            structure.calculDependancesControle();
        }
    }
    
    public String getDependancesControleGraphViz(String methodNodeName)
    {
        String resultat = "\t" + this.getNameNodeBegin() + " [\n\t\tlabel = \"" + this.getType() + " " + this.getName() + "\nid = " + this.id +"\"\n\t]\n";
        
        if (this.dependanceControle.size() == 0 || (this.dependanceControle.size() == 1 && this.dependanceControle.contains(this)))
        {
            resultat += "\t" + methodNodeName + " -> " + this.getNameNodeBegin() + "\n";
        }
        for (int i=0;i<this.dependanceControle.size();i++)
        {
                resultat += "\t" + this.dependanceControle.get(i).getNameNodeBegin() + " -> " + this.getNameNodeBegin() + "\n";
        }
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getDependancesControleGraphViz(methodNodeName);
        }
        
        return resultat;
    }
    
    public void initBackwardSlicingArray()
    {
        this.backwardSlicingArray = new ArrayList<ArrayList<Structure>>();
        for (int i=0;i<this.variablesIntervenants.size();i++)
        {
            this.backwardSlicingArray.add(new ArrayList<Structure>());
        }
    }
    
    public void backwardSlicing()
    {
        this.initBackwardSlicingArray();
        
        for (int i=0;i<this.variablesIntervenants.size();i++)
        {
            for (int j=0;j<this.in.size();j++)
            {
                if (this.in.get(j).gen != null && this.in.get(j).gen.getName().equals(this.variablesIntervenants.get(i)) && !this.backwardSlicingArray.get(i).contains(this.in.get(j)))
                {
                    this.backwardSlicingArray.get(i).add(this.in.get(j));
                }
            }
        }
        
        for (int i=0;i<this.statement.size();i++)
        {
            this.statement.get(i).backwardSlicing();
        }
    }
    
    public String getBackwardSlicingGraphViz(String methodNodeName)
    {
        String resultat = "";
        
        for (int i=0;i<this.backwardSlicingArray.size();i++)
        {
            ArrayList<Structure> array = this.backwardSlicingArray.get(i);
            if (array.size() > 0)
            {
                resultat += "\tVariable" + this.variablesIntervenants.get(i) + i + this.getNameNodeBegin() + this.id + " [\n\t\tlabel = \"Variable " + this.variablesIntervenants.get(i) + "\n" + this.getNameNodeBegin() + "\nid = " + this.id +"\"\n\t]\n";
                
                resultat += this.getDependancesControleEtDonneesForBackwardSlicingGraphViz(methodNodeName);
            }
            for (int j=0;j<array.size();j++)
            {
                resultat += "\t" + array.get(j).getNameNodeBegin() + " [\n\t\tlabel = \"" + array.get(j).getType() + " " + array.get(j).getName() + "\nid = " + array.get(j).id +"\"\n\t]\n";
                resultat += "\t" + array.get(j).getNameNodeBegin() + " -> Variable" + this.variablesIntervenants.get(i) + i + this.getNameNodeBegin() + this.id + "\n";
                
                resultat += array.get(j).getDependancesControleEtDonneesForBackwardSlicingGraphViz(methodNodeName);
            }
        }
        
        for (int i=0;i<this.statement.size();i++)
        {
            resultat += this.statement.get(i).getBackwardSlicingGraphViz(methodNodeName);
        }
        
        return resultat;
    }
    
    public String getDependancesControleEtDonneesForBackwardSlicingGraphViz(String methodNodeName)
    {
        String resultat = "";
        if (!this.allreadyPrint)
        {
            this.allreadyPrint = true;
            resultat = "\t" + this.getNameNodeBegin() + " [\n\t\tlabel = \"" + this.getType() + " " + this.getName() + "\nid = " + this.id +"\"\n\t]\n";
            
            if (this.dependanceControle.size() == 0)
            {
                resultat += "\t" + methodNodeName + " -> " + this.getNameNodeBegin() + "\n";
            }
            for (int i=0;i<this.dependanceControle.size();i++)
            {
                resultat += this.dependanceControle.get(i).getDependancesControleEtDonneesForBackwardSlicingGraphViz(methodNodeName);
                resultat += "\t" + this.dependanceControle.get(i).getNameNodeBegin() + " -> " + this.getNameNodeBegin() + "\n";
            }
            
            for (int i=0;i<this.dependancesDonnees.size();i++)
            {
                Structure structure = this.dependancesDonnees.get(i);
                resultat += structure.getDependancesControleEtDonneesForBackwardSlicingGraphViz(methodNodeName);
                resultat += "\t" + structure.getNameNodeBegin() + " [\n\t\tlabel = \"" + structure.getType() + " " + structure.getName() + "\nid = " + structure.id +"\"\n\t]\n";
                resultat += "\t" + structure.getNameNodeBegin() + " -> " + this.getNameNodeBegin() + "\n";
            }
        }
        return resultat;
    }
    
    public void forwardSlicing()
    {
        for (int i=0;i<this.in.size();i++)
        {
            if (this.in.get(i).gen != null && this.variablesIntervenants.contains(this.in.get(i).gen.getName()))
            {
                this.in.get(i).gen.addElementInForwardSlicingArray(this);
            }
        }
        
        for (int i=0;i<this.statement.size();i++)
        {
            this.statement.get(i).forwardSlicing();
        }
    }
    
    public String getForwardSlicingGraphViz()
    {
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
