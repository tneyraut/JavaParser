package javaparser;

import java.util.ArrayList;

public class ExampleVisitor extends AbstractVisitor
{
    
    private int numberIf = 0;
    private int numberElse = 0;
    private int numberElseIf = 0;
    private int numberSwitch = 0;
    private int numberCase = 0;
    private int numberWhile = 0;
    private int numberFor = 0;
    private int numberLocalVariable = 0;
    private int numberBreak = 0;
    private int numberContinue = 0;
    
    private int numberImport = 0;
    private int numberClass = 0;
    private int numberInterface = 0;
    private int numberEnum = 0;
    private int numberMethode = 0;
    
    private ArrayList<int[]> metriquesMethodArray = new ArrayList<int[]>();
    private ArrayList<String> classArray = new ArrayList<String>();
    private ArrayList<String> methodArray = new ArrayList<String>();
    private ArrayList<String> interfaceArray = new ArrayList<String>();
    
    private void initMetriquesMethod()
    {
        this.numberIf = 0;
        this.numberElse = 0;
        this.numberElseIf = 0;
        this.numberSwitch = 0;
        this.numberCase = 0;
        this.numberWhile = 0;
        this.numberFor = 0;
        this.numberLocalVariable = 0;
        this.numberBreak = 0;
        this.numberContinue = 0;
    }
    
    public void addMetriquesMethod()
    {
        int []tab = new int[12];
        tab[0] = this.numberIf;
        tab[1] = this.numberElse;
        tab[2] = this.numberElseIf;
        tab[3] = this.numberSwitch;
        tab[4] = this.numberCase;
        tab[5] = this.numberCase + this.numberIf + this.numberElse + this.numberElseIf;
        tab[6] = this.numberWhile;
        tab[7] = this.numberFor;
        tab[8] = this.numberFor + this.numberWhile;
        tab[9] = this.numberLocalVariable;
        tab[10] = this.numberBreak;
        tab[11] = this.numberContinue;
        
        this.metriquesMethodArray.add(tab);
    }
    
    public int getSizeInterfaceArray()
    {
        return this.interfaceArray.size();
    }
    
    public String getElementInterfaceArray(int index)
    {
        return this.interfaceArray.get(index);
    }
    
    public int getSizeMetriquesMethodArray()
    {
        return this.metriquesMethodArray.size();
    }
    
    public int[] getElementMetriquesMethodArray(int index)
    {
        return this.metriquesMethodArray.get(index);
    }
    
    public int getSizeClassArray()
    {
        return this.classArray.size();
    }
    
    public String getElementClassArray(int index)
    {
        return this.classArray.get(index);
    }
    
    public int getSizeMethodArray()
    {
        return this.methodArray.size();
    }
    
    public String getElementMethodArray(int index)
    {
        return this.methodArray.get(index);
    }
    
    public int getNumberMethode()
    {
        return this.numberMethode;
    }
    
    public int getNumberImport()
    {
        return this.numberImport;
    }
    
    public int getNumberClass()
    {
        return this.numberClass;
    }
    
    public int getNumberInterface()
    {
        return this.numberInterface;
    }
    
    public int getNumberEnum()
    {
        return this.numberEnum;
    }
    
    public Object visit(Identifier node, Object data)
    {
        //System.out.println(node.jjtGetFirstToken().image + " / " + node.jjtGetParent() + " / " + node.jjtGetLastToken());
        if (node.jjtGetParent().toString().equals("NormalClassDeclaration"))
        {
            this.classArray.add(node.jjtGetFirstToken().image.toString());
        }
        else if (node.jjtGetParent().toString().equals("ConstructorDecl") || node.jjtGetParent().toString().equals("MethodOrFieldDecl") || node.jjtGetParent().toString().equals("InterfaceMethodOrFieldDecl") || node.jjtGetParent().toString().equals("InterfaceMemberDecl") || node.jjtGetParent().toString().equals("VoidMethodDecl"))
        {
            this.methodArray.add(node.jjtGetFirstToken().image.toString());
        }
        else if (node.jjtGetParent().toString().equals("NormalInterfaceDeclaration"))
        {
            this.interfaceArray.add(node.jjtGetFirstToken().image.toString());
        }
        
		propagate(node, data);
		return data;
	}
    
    public Object visit(IfStatement node, Object data)
    {
        this.numberIf++;
        propagate(node, data);
        return data;
    }
    
    public Object visit(ElseStatement node, Object data)
    {
        if (node.jjtGetFirstToken().toString().equals("if"))
        {
            this.numberIf--;
            this.numberElseIf++;
        }
        else
        {
            this.numberElse++;
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(SwitchStatement node, Object data)
    {
        this.numberSwitch++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(WhileStatement node, Object data)
    {
        this.numberWhile++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(ForStatement node, Object data)
    {
        this.numberFor++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(VariableDeclarator node, Object data)
    {
        this.numberLocalVariable++;
        propagate(node, data);
        return data;
    }
    
    public Object visit(BreakStatement node, Object data)
    {
        this.numberBreak++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(ContinueStatement node, Object data)
    {
        this.numberContinue++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(ImportDeclaration node, Object data)
    {
        this.numberImport++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(ClassDeclaration node, Object data)
    {
        this.numberClass++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(ClassBodyDeclaration node, Object data)
    {
        if (this.numberMethode != 0)
        {
            this.addMetriquesMethod();
            this.initMetriquesMethod();
        }
        
        this.numberMethode++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(InterfaceDeclaration node, Object data)
    {
        this.numberInterface++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(InterfaceBodyDeclaration node, Object data)
    {
        if (this.numberMethode != 0)
        {
            this.addMetriquesMethod();
            this.initMetriquesMethod();
        }
        
        this.numberMethode++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(EnumDeclaration node, Object data)
    {
        this.numberEnum++;
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(SwitchBlockStatementGroup node, Object data)
    {
        this.numberCase++;
        
        propagate(node, data);
        return data;
    }

}
