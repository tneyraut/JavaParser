package javaparser;

import java.io.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MetricFileGenerator
{
    
    private FileWriter fileWriter;
    private String fileFormat;
    private int id;
    
    public MetricFileGenerator(String fileFormat)
    {
        this.id = 1;
        this.fileFormat = fileFormat;
        this.fileWriter = null;
        try {
            File directory = new File("MetricFile");
            this.fileWriter = new FileWriter("MetricFile/resultats" + fileFormat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void write(ExampleVisitor vis, String fileName)
    {
        if (this.fileFormat.equals(".json"))
        {
            this.setResultatsJSON(vis, fileName);
        }
        else
        {
            this.setResultatsCSV(vis, fileName);
        }
    }
    
    private void setResultatsJSON(ExampleVisitor vis, String fileName)
    {
        vis.addMetriquesMethod();
        
        JSONObject obj = new JSONObject();
        
        obj.put("id", this.id);
        obj.put("fileName", fileName);
        
        JSONArray listClass = new JSONArray();
        for (int i=0;i<vis.getSizeClassArray();i++)
        {
            listClass.add(vis.getElementClassArray(i));
        }
        obj.put("classNames", listClass);
        
        obj.put("numberImport", vis.getNumberImport());
        obj.put("numberClass", vis.getNumberClass());
        obj.put("numberInterface", vis.getNumberInterface());
        obj.put("numberEnum", vis.getNumberEnum());
        obj.put("numberMethode", vis.getNumberMethode());
        
        JSONArray listInterface = new JSONArray();
        for (int i=0;i<vis.getSizeInterfaceArray();i++)
        {
            listClass.add(vis.getElementInterfaceArray(i));
        }
        obj.put("interfacesNames", listInterface);
        
        String[] tab = {"numberIf", "numberElse", "numberSwitch", "numberCase", "valeurCyclomatique", "numberWhile", "numberFor", "nombreTotalBoucle", "numberLocalVariable", "numberBreak", "numberContinue"};
        
        JSONArray listMethod = new JSONArray();
        for (int i=0;i<vis.getSizeMethodArray();i++)
        {
            JSONObject method = new JSONObject();
            
            method.put("methodName", vis.getElementMethodArray(i));
            
            for (int j=0;j<tab.length;j++)
            {
                method.put(tab[j], vis.getElementMetriquesMethodArray(i)[j]);
            }
            
            listMethod.add(method);
        }
        obj.put("methods", listMethod);
        
        try {
            this.fileWriter.write(obj.toJSONString() + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.id++;
    }
    
    private void setResultatsCSV(ExampleVisitor vis, String fileName)
    {
        vis.addMetriquesMethod();
        
        String text = "";
        
        if (id == 1)
        {
            text = "id;FileName;ClassName;MethodName;NumberOfIf;NumberOfElse;NumberOfSwitch;NumberCase;ValeurCyclomatique;NumberOfWhile;NumberOfFor;NombreTotalDeBoucles;NumberOfLocalVariable;NumberOfBreak;NumberOfContinue\n";
        }
        
        for (int i=0;i<vis.getSizeMethodArray();i++)
        {
            String className = "NoName";
            if (vis.getSizeClassArray() > 0)
            {
                className = vis.getElementClassArray(0);
            }
            else
            {
                className = fileName.replace(".java","");
            }
            text += id + ";" + fileName + ";" + className + ";" + vis.getElementMethodArray(i);
            id++;
            for (int j=0;j<11;j++)
            {
                text += ";" + vis.getElementMetriquesMethodArray(i)[j];
            }
            text += "\n";
        }
        
        try {
            this.fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void endWriter()
    {
        try {
            this.fileWriter.flush();
            this.fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
