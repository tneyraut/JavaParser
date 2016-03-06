package javaparser;

import java.util.ArrayList;

public class CFGVisitor extends AbstractVisitor
{
    
    private ArrayList<Method> methodsArray;
    private Structure derniereBoucle;
    private For dernierFor;
    private ArrayList<Structure> structuresCourantes;
    private int id;
    
    private boolean elseFind;
    private boolean methodFind;
    private boolean ifClosed;
    
    public CFGVisitor()
    {
        super();
        this.methodsArray = new ArrayList<Method>();
        this.structuresCourantes = new ArrayList<Structure>();
        this.derniereBoucle = null;
        this.dernierFor = null;
        this.id = 1;
        this.elseFind = false;
        this.methodFind = false;
        this.ifClosed = false;
    }
    
    public int getSizeMethodsArray()
    {
        return this.methodsArray.size();
    }
    
    public Method getMethod(int index)
    {
        return this.methodsArray.get(index);
    }
    
    private void addStructure(Structure structure)
    {
        if (this.structuresCourantes.size() > 0)
        {
            this.structuresCourantes.get(this.structuresCourantes.size() - 1).addStatement(structure);
        }
        else if (this.methodsArray.size() > 0)
        {
            this.methodsArray.get(this.methodsArray.size() - 1).addStatement(structure);
        }
        this.structuresCourantes.add(structure);
    }
    
    private void removeLastStructuresCourantes()
    {
        this.structuresCourantes.remove(this.structuresCourantes.size() - 1);
    }
    
    public Object visit(Identifier node, Object data)
    {
        //System.out.println(node.jjtGetFirstToken().image + " / " + node.jjtGetParent() + " / " + node.jjtGetLastToken());
        
        if (this.methodFind && (node.jjtGetParent().toString().equals("MethodOrFieldDecl") || node.jjtGetParent().toString().equals("VoidMethodDecl")))
        {
            Method method = new Method(this.id, node.jjtGetFirstToken().image.toString());
            this.id++;
            this.methodFind = false;
            this.methodsArray.add(method);
            this.structuresCourantes.clear();
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(ClassBodyDeclaration node, Object data)
    {
        if (node.jjtGetLastToken().toString().equals("}"))
        {
            this.methodFind = true;
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(IfStatement node, Object data)
    {
        if (!this.elseFind)
        {
            If aIf = new If(this.id);
            this.id++;
            this.addStructure(aIf);
            this.ifClosed = false;
            
            propagate(node, data);
            if (!this.ifClosed)
            {
                this.removeLastStructuresCourantes();
                this.ifClosed = true;
            }
        }
        else
        {
            propagate(node, data);
        }
        return data;
    }
    
    public Object visit(ElseStatement node, Object data)
    {
        if (!this.ifClosed)
        {
            this.removeLastStructuresCourantes();
            this.ifClosed = true;
        }
        If aIf = new If(this.id);
        this.id++;
        this.elseFind = true;
        this.addStructure(aIf);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(Block node, Object data)
    {
        this.elseFind = false;
        propagate(node, data);
        return data;
    }
    
    public Object visit(SwitchStatement node, Object data)
    {
        Switch aSwitch = new Switch(this.id);
        this.id++;
        this.derniereBoucle = aSwitch;
        this.addStructure(aSwitch);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(WhileStatement node, Object data)
    {
        While aWhile = new While(this.id);
        this.id++;
        this.derniereBoucle = aWhile;
        this.addStructure(aWhile);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(DoStatement node, Object data)
    {
        Do aDo = new Do(this.id);
        this.id++;
        this.addStructure(aDo);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(ForStatement node, Object data)
    {
        For aFor = new For(this.id);
        this.id++;
        this.derniereBoucle = aFor;
        this.dernierFor = aFor;
        this.addStructure(aFor);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(BreakStatement node, Object data)
    {
        Break aBreak = new Break(this.id, this.derniereBoucle);
        this.id++;
        this.addStructure(aBreak);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(ReturnStatement node, Object data)
    {
        Return aReturn = new Return(this.id, this.methodsArray.get(this.methodsArray.size() - 1));
        this.id++;
        this.addStructure(aReturn);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(SwitchBlockStatementGroup node, Object data)
    {
        Case aCase = new Case(this.id);
        this.id++;
        this.addStructure(aCase);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(ContinueStatement node, Object data)
    {
        Continue aContinue = new Continue(this.id, this.dernierFor);
        this.id++;
        this.addStructure(aContinue);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(VariableDeclarators node, Object data)
    {
        if (node.jjtGetParent().toString().equals("LocalVariableDeclarationStatement"))
        {
            Variable variable = new Variable(this.id, node.jjtGetLastToken().toString(), "DéclarationVariable");
            this.id++;
            this.addStructure(variable);
            
            propagate(node, data);
            this.removeLastStructuresCourantes();
            return data;
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(Expression node, Object data)
    {
        if (node.jjtGetParent().toString().equals("StatementExpression"))
        {
            Variable variable = new Variable(this.id, node.jjtGetFirstToken().toString(), "AssignationVariable");
            this.id++;
            this.addStructure(variable);
            
            propagate(node, data);
            this.removeLastStructuresCourantes();
            return data;
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(Catches node, Object data)
    {
        Catch aCatch = new Catch(this.id);
        this.id++;
        this.addStructure(aCatch);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(Finally node, Object data)
    {
        FinallyStructure aFinally = new FinallyStructure(this.id);
        this.id++;
        this.addStructure(aFinally);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(TryStatement node, Object data)
    {
        Try aTry = new Try(this.id);
        this.id++;
        this.addStructure(aTry);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(ThrowStatement node, Object data)
    {
        Throw aThrow = new Throw(this.id);
        this.id++;
        this.addStructure(aThrow);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
}
