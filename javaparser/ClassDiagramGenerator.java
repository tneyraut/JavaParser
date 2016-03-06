package javaparser;

import java.io.*;
import java.util.*;

public class ClassDiagramGenerator
{
    
    private ArrayList<ArrayList<String>> extendsLinksArray;
    private ArrayList<ArrayList<String>> implementsArrayList;
    private ArrayList<String> packageArray;
    private ArrayList<FileWriter> fileWriterArray;
    private ArrayList<ArrayList<Classe>> classArrayList;
    private ArrayList<Integer> compteurs;
    
    private int id;
    
    public ClassDiagramGenerator()
    {
        File directory = new File("ClassDiagram");
        directory.mkdirs();
        File[] files = directory.listFiles();
        for (int i=0;i<files.length;i++)
        {
            files[i].delete();
        }
        this.packageArray = new ArrayList<String>();
        this.fileWriterArray = new ArrayList<FileWriter>();
        this.extendsLinksArray = new ArrayList<ArrayList<String>>();
        this.implementsArrayList = new ArrayList<ArrayList<String>>();
        this.classArrayList = new ArrayList<ArrayList<Classe>>();
        this.compteurs = new ArrayList<Integer>();
        this.id = 0;
    }
    
    public void writeEndAndCloseAllFile()
    {
        for (int i=0;i<this.fileWriterArray.size();i++)
        {
            this.writeEndFileAndClose(this.fileWriterArray.get(i), i);
        }
    }
    
    private void writeEndFileAndClose(FileWriter fileWriter, int index)
    {
        String text = "\t edge [\n";
        text += "\t\t arrowhead = \"empty\"\n";
        text += "\t ]\n\n";
        
        ArrayList<String> extendsLinks = this.extendsLinksArray.get(index);
        for (int i=0;i<extendsLinks.size();i+=2)
        {
            text += "\t " + extendsLinks.get(i) + " -> " + extendsLinks.get(i+1) + "\n";
        }
        
        text += "\t edge [\n";
        text += "\t\t arrowhead = \"diamond\"\n";
        text += "\t ]\n\n";
        
        ArrayList<Classe> classArray = this.classArrayList.get(index);
        for (int i=0;i<classArray.size();i++)
        {
            Classe classe = classArray.get(i);
            for (int j=0;j<classe.getSizeVariables();j++)
            {
                boolean ok = true;
                Variable variable = classe.getVariable(j);
                for (int a=0;a<j;a++)
                {
                    if (variable.getType().equals(classe.getVariable(a).getType()))
                    {
                        ok = false;
                        break;
                    }
                }
                if (ok)
                {
                    ok = false;
                    for (int k=0;k<this.classArrayList.size();k++)
                    {
                        ArrayList<Classe> array = this.classArrayList.get(k);
                        for (int l=0;l<array.size();l++)
                        {
                            Classe aClass = array.get(l);
                            if (variable.getType().equals(aClass.getName()))
                            {
                                text += "\t " + aClass.getName() + "->" + classe.getName() + "\n";
                                ok = true;
                                break;
                            }
                        }
                        if (ok)
                        {
                            break;
                        }
                    }
                }
            }
        }
        
        text += "\t edge [\n";
        text += "\t\t arrowhead = \"vee\"\n";
        text += "\t\t label = \"implements\"";
        text += "\t ]\n\n";
        
        ArrayList<String> implementsArray = this.implementsArrayList.get(index);
        for (int i=0;i<implementsArray.size();i+=2)
        {
            text += "\t " + implementsArray.get(i) + "->" + implementsArray.get(i+1) + "\n";
        }
        
        text += "}";
        
        try {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void writeBeginFile(String packageName)
    {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("ClassDiagram/classDiagram_" + this.id + "_"+ packageName + ".dot");
            this.id++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String text = "digraph ClassDiagram {\n";
        text += "\t fontname = \"Bitstream Vera Sans\"\n";
        text += "\t fontsize = 8\n\n";
        text += "\t node [\n";
        text += "\t\t fontname = \"Bistream Vera Sans\"\n";
        text += "\t\t fontsize = 8\n";
        text += "\t\t shape = \"record\"\n";
        text += "\t ]\n\n";
        text += "\t edge [\n";
        text += "\t\t fontname = \"Bistream Vera Sans\"\n";
        text += "\t\t fontsize = 8\n";
        text += "\t ]\n\n";
        
        try {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        this.fileWriterArray.add(fileWriter);
        this.packageArray.add(packageName);
        this.compteurs.add(0);
        
        ArrayList<String> extendsLinks = new ArrayList<String>();
        this.extendsLinksArray.add(extendsLinks);
        
        ArrayList<String> implementsArray = new ArrayList<String>();
        this.implementsArrayList.add(implementsArray);
        
        ArrayList<Classe> classArray = new ArrayList<Classe>();
        this.classArrayList.add(classArray);
    }
    
    public void addClassToDiagram(Classe classe)
    {
        String packageName = classe.getPackageName();
        if (!this.packageArray.contains(packageName))
        {
            this.writeBeginFile(packageName);
        }
        int index = this.packageArray.indexOf(packageName);
        FileWriter fileWriter = this.fileWriterArray.get(index);
        
        //MODIF
        if (this.compteurs.get(index) == 20)
        {
            this.compteurs.set(index, 0);
            this.writeEndFileAndClose(fileWriter, index);
            this.writeBeginFile(packageName);
            
            this.extendsLinksArray.set(index, this.extendsLinksArray.get(this.extendsLinksArray.size() - 1));
            this.extendsLinksArray.remove(this.extendsLinksArray.get(this.extendsLinksArray.size() - 1));
            
            this.implementsArrayList.set(index, this.implementsArrayList.get(this.implementsArrayList.size() - 1));
            this.implementsArrayList.remove(this.implementsArrayList.get(this.implementsArrayList.size() - 1));
            
            this.classArrayList.set(index, this.classArrayList.get(this.classArrayList.size() - 1));
            this.classArrayList.remove(this.classArrayList.get(this.classArrayList.size() - 1));
            
            this.fileWriterArray.set(index, this.fileWriterArray.get(this.fileWriterArray.size() - 1));
            this.fileWriterArray.remove(this.fileWriterArray.get(this.fileWriterArray.size() - 1));
            
            this.packageArray.remove(this.packageArray.get(this.packageArray.size() - 1));
            
            this.compteurs.remove(this.compteurs.get(this.compteurs.size() - 1));
            
            fileWriter = this.fileWriterArray.get(index);
        }
        // MODIF
        
        this.compteurs.set(index, this.compteurs.get(index) + 1);
        
        ArrayList<String> extendsLinks = this.extendsLinksArray.get(index);
        ArrayList<String> implementsArray = this.implementsArrayList.get(index);
        ArrayList<Classe> classArray = this.classArrayList.get(index);
        
        if (!classe.getSuperClassName().equals(""))
        {
            extendsLinks.add(classe.getName());
            extendsLinks.add(classe.getSuperClassName());
        }
        
        for (int i=0;i<classe.getSizeImplementsArray();i++)
        {
            implementsArray.add(classe.getName());
            implementsArray.add(classe.getImplement(i));
        }
        
        classArray.add(classe);
        
        String text = "\t " + classe.getName() + " [\n";
        text += "\t\t label = \"{" + classe.getName() + "|";
        
        for (int i=0;i<classe.getSizeVariables();i++)
        {
            Variable variable = classe.getVariable(i);
            if (variable.getAccessibility().equals("public"))
            {
                text += "+ ";
            }
            else if (variable.getAccessibility().equals("private"))
            {
                text += "- ";
            }
            else if (variable.getAccessibility().equals("protected"))
            {
                text += "# ";
            }
            text += variable.getName() + " : ";
            text += variable.getType() + "\\l";
        }
        text += "|";
        
        for (int i=0;i<classe.getSizeMethods();i++)
        {
            Method method = classe.getMethod(i);
            if (method.getAccessibility().equals("public"))
            {
                text += "+ ";
            }
            else if (method.getAccessibility().equals("private"))
            {
                text += "- ";
            }
            else if (method.getAccessibility().equals("protected"))
            {
                text += "# ";
            }
            text += method.getName() + "(";
            
            for (int j=0;j<method.getSizeParameters();j++)
            {
                if (j > 0)
                {
                    text += ", ";
                }
                Variable variable = method.getElementParameters(j);
                text += variable.getType();
            }
            text += ") : " + method.getTypeReturn() + "\\l";
        }
        text += "}\"\n";
        text += "\t ]\n\n";
        
        try {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
