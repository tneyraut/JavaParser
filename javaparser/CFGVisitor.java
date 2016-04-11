package javaparser;

import java.util.ArrayList;

public class CFGVisitor extends AbstractVisitor
{
    
    private ArrayList<Method> methodsArray;
    private Structure derniereBoucle;
    private ArrayList<Structure> structuresCourantes;
    private int id;
    
    private boolean elseFind;
    private boolean methodFind;
    private boolean ifClosed;
    private boolean isVariable;
    
    private boolean assignationVariable;
    
    public CFGVisitor()
    {
        super();
        this.methodsArray = new ArrayList<Method>();
        this.structuresCourantes = new ArrayList<Structure>();
        this.derniereBoucle = null;
        this.id = 1;
        this.elseFind = false;
        this.methodFind = false;
        this.ifClosed = false;
        this.isVariable = false;
        this.assignationVariable = false;
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
        if (this.methodsArray.size() > 0 && this.methodsArray.get(this.methodsArray.size() - 1).getStatementSize() > 0)
        {
            Method method = this.methodsArray.get(this.methodsArray.size() - 1);
            if (this.isVariable)
            {
                for (int i=0;i<method.getStatementSize();i++)
                {
                    if (structure.getName().equals(method.getStatement(i).getName()))
                    {
                        structure.kill.add(method.getStatement(i));
                        method.getStatement(i).kill.add(structure);
                    }
                    else if (method.getStatement(i).statement.size() > 0)
                    {
                        for (int j=0;j<method.getStatement(i).statement.size();j++)
                        {
                            Structure parentStructure = method.getStatement(i).statement.get(j);
                            if (structure.getName().equals(parentStructure.getName()))
                            {
                                structure.kill.add(parentStructure);
                                parentStructure.kill.add(structure);
                            }
                            else if (parentStructure.statement.size() > 0)
                            {
                                this.calculKillForStructure(structure,parentStructure);
                            }
                        }
                    }
                }
            }
            if (this.structuresCourantes.size() > 0)
            {
                if (this.structuresCourantes.get(this.structuresCourantes.size() - 1).statement.size() > 0)
                {
                    this.copyArrayList(structure.in, this.structuresCourantes.get(this.structuresCourantes.size() - 1).statement.get(this.structuresCourantes.get(this.structuresCourantes.size() - 1).statement.size() - 1).out);
                }
                else
                {
                    this.copyArrayList(structure.in, this.structuresCourantes.get(this.structuresCourantes.size() - 1).out);
                }
            }
            else
            {
                this.copyArrayList(structure.in, method.getStatement(method.getStatementSize() - 1).out);
            }
        }
        
        this.calculOutForStructure(structure);
        
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
    
    private void copyArrayList(ArrayList<Structure> array1, ArrayList<Structure> array2)
    {
        for (int i=0;i<array2.size();i++)
        {
            if (!array1.contains(array2.get(i)))
            {
                array1.add(array2.get(i));
            }
        }
    }
    
    private void calculOutForStructure(Structure structure)
    {
        boolean modifDone = false;
        for (int i=0;i<structure.in.size();i++)
        {
            boolean ok = true;
            for (int j=0;j<structure.kill.size();j++)
            {
                if (structure.in.get(i).id == structure.kill.get(j).id)
                {
                    ok = false;
                    break;
                }
            }
            if (ok && !structure.out.contains(structure.in.get(i)))
            {
                structure.out.add(structure.in.get(i));
                modifDone = true;
            }
        }
        if (modifDone && structure.statement.size() > 0)
        {
            this.calculInForStructure(structure.statement.get(0),structure);
            for (int i=1;i<structure.statement.size();i++)
            {
                this.calculInForStructure(structure.statement.get(i),structure.statement.get(i-1));
            }
        }
    }
    
    private void calculKillForStructure(Structure structure, Structure parentStructure)
    {
        for (int i=0;i<parentStructure.statement.size();i++)
        {
            if (structure.getName().equals(parentStructure.statement.get(i).getName()))
            {
                structure.kill.add(parentStructure.statement.get(i));
                parentStructure.statement.get(i).kill.add(structure);
            }
            else if (parentStructure.statement.get(i).statement.size() > 0)
            {
                this.calculKillForStructure(structure,parentStructure.statement.get(i));
            }
        }
    }
    
    private void calculInForStructureForOrWhile(Structure boucle)
    {
        if ((boucle.getType().equals("For") || boucle.getType().equals("While")) && boucle.statement.size() > 0)
        {
            Structure structure = boucle.statement.get(boucle.statement.size() - 1);
            for (int i=0;i<structure.out.size();i++)
            {
                if (!boucle.in.contains(structure.out.get(i)))
                {
                    boucle.in.add(structure.out.get(i));
                }
            }
            this.calculOutForStructure(boucle);
            
            this.copyArrayList(boucle.statement.get(0).in, boucle.out);
            this.calculOutForStructure(boucle.statement.get(0));
            for (int i=1;i<boucle.statement.size();i++)
            {
                Structure childStructure = boucle.statement.get(i);
                this.copyArrayList(childStructure.in, boucle.statement.get(i-1).out);
                this.calculOutForStructure(childStructure);
            }
        }
    }
    
    private void calculInForStructure(Structure structure, Structure parentStructure)
    {
        this.copyArrayList(structure.in,parentStructure.out);
        this.calculOutForStructure(structure);
        if (structure.statement.size() > 0)
        {
            this.calculInForStructure(structure.statement.get(0),structure);
            for (int j=1;j<structure.statement.size();j++)
            {
                this.calculInForStructure(structure.statement.get(j),structure.statement.get(j-1));
            }
        }
    }
    
    private void removeLastStructuresCourantes()
    {
        this.structuresCourantes.remove(this.structuresCourantes.size() - 1);
    }
    
    public boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
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
            If aIf = new If(this.id, "If");
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
        String type = "Else";
        if (node.jjtGetFirstToken().toString().equals("if"))
        {
            type = "ElseIf";
        }
        If aIf = new If(this.id, type);
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
        
        this.calculInForStructureForOrWhile(aWhile);
        
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
        this.addStructure(aFor);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        
        this.calculInForStructureForOrWhile(aFor);
        
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
        Continue aContinue = new Continue(this.id, this.derniereBoucle);
        this.id++;
        this.addStructure(aContinue);
        
        propagate(node, data);
        this.removeLastStructuresCourantes();
        return data;
    }
    
    public Object visit(VariableDeclarator node, Object data)
    {
        if (node.jjtGetParent().toString().equals("VariableDeclarators"))
        {
            this.isVariable = true;
            Variable variable = new Variable(this.id, node.jjtGetFirstToken().toString(), "DéclarationVariable");
            
            variable.gen = variable;
            variable.kill.add(variable);
            variable.out.add(variable);
            
            this.id++;
            this.addStructure(variable);
            
            this.removeLastStructuresCourantes();
            
            if (!node.jjtGetLastToken().toString().equals(node.jjtGetFirstToken().toString()))
            {
                Variable aVariable = new Variable(this.id, node.jjtGetFirstToken().toString(), "AssignationVariable");
                
                aVariable.gen = aVariable;
                aVariable.kill.add(aVariable);
                aVariable.out.add(aVariable);
                
                this.id++;
                this.addStructure(aVariable);
                propagate(node, data);
                this.removeLastStructuresCourantes();
                this.isVariable = false;
                return data;
            }
        }
        this.isVariable = false;
        propagate(node, data);
        return data;
    }
    
    public Object visit(Expression node, Object data)
    {
        //System.out.println(node.jjtGetFirstToken().image + " / " + node.jjtGetParent() + " / " + node.jjtGetLastToken());
        if (node.jjtGetParent().toString().equals("StatementExpression"))
        {
            boolean test = (node.jjtGetLastToken().toString().equals("++") || node.jjtGetLastToken().toString().equals("--"));
            /*if (!test)
            {
                propagate(node, data);
            }*/
            
            this.isVariable = true;
            Variable variable = new Variable(this.id, node.jjtGetFirstToken().toString(), "AssignationVariable");
            
            variable.gen = variable;
            variable.kill.add(variable);
            variable.out.add(variable);
            
            if (node.jjtGetLastToken().toString().equals("++") || node.jjtGetLastToken().toString().equals("--"))
            {
                variable.variablesIntervenants.add(node.jjtGetFirstToken().toString());
            }
            else if (node.jjtGetFirstToken().toString().equals("++") || node.jjtGetFirstToken().toString().equals("--"))
            {
                variable.variablesIntervenants.add(node.jjtGetLastToken().toString());
            }
            
            this.assignationVariable = true;
            this.id++;
            this.addStructure(variable);
            
            this.isVariable = false;
            
            //if (test)
            //{
                propagate(node,data);
            //}
            this.removeLastStructuresCourantes();
            
            return data;
        }
        else if (node.jjtGetParent().toString().equals("ExpressionRest") && (node.jjtGetFirstToken().toString().equals("++") || node.jjtGetFirstToken().toString().equals("--") || node.jjtGetLastToken().toString().equals("++") || node.jjtGetLastToken().toString().equals("--")))
        {
            String variableName = node.jjtGetFirstToken().toString();
            if (node.jjtGetFirstToken().toString().equals("++") || node.jjtGetFirstToken().toString().equals("--"))
            {
                variableName = node.jjtGetLastToken().toString();
            }
            this.isVariable = true;
            Variable variable = new Variable(this.id, variableName, "AssignationVariable");
            this.id++;
            
            variable.gen = variable;
            variable.kill.add(variable);
            variable.out.add(variable);
            
            variable.variablesIntervenants.add(variableName);
            
            this.addStructure(variable);
            propagate(node, data);
            
            this.isVariable = false;
            this.removeLastStructuresCourantes();
            return data;
        }
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(Expression3 node, Object data)
    {
        //System.out.println(node.jjtGetFirstToken().image + " / " + node.jjtGetParent() + " / " + node.jjtGetLastToken());
        if (this.structuresCourantes.size() > 0 && !this.isNumeric(node.jjtGetFirstToken().image.toString()) && !this.structuresCourantes.get(this.structuresCourantes.size() - 1).variablesIntervenants.contains(node.jjtGetFirstToken().image.toString()) && !node.jjtGetFirstToken().image.toString().equals("(") && !node.jjtGetFirstToken().image.toString().equals(")") && !node.jjtGetFirstToken().image.toString().equals("/") && !node.jjtGetFirstToken().image.toString().equals("*") && !node.jjtGetFirstToken().image.toString().equals("+") && !node.jjtGetFirstToken().image.toString().equals("-") && !this.assignationVariable)
        {
            this.structuresCourantes.get(this.structuresCourantes.size() - 1).variablesIntervenants.add(node.jjtGetFirstToken().image.toString());
        }
        
        this.assignationVariable = false;
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
