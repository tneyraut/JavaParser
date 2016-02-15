package javaparser;

import java.util.ArrayList;

public class UmlVisitor extends AbstractVisitor
{
    
    private Classe classe;
    private Method method;
    private Variable variable;
    private ArrayList<Classe> classes;
    private String packageName = "NoPackage";
    
    private String mode = ""; // "" => Aucune considération / "Class" / "Method" / "Variable"
    
    public UmlVisitor()
    {
        super();
        this.classes = new ArrayList<Classe>();
    }
    
    public Classe getClasse(int index)
    {
        return this.classes.get(index);
    }
    
    public int getSizeClasses()
    {
        return this.classes.size();
    }
    
    public Object visit(Identifier node, Object data)
    {
        //System.out.println(node.jjtGetFirstToken().image + " / " + node.jjtGetParent() + " / " + node.jjtGetLastToken());
        
        if (node.jjtGetParent().toString().equals("NormalClassDeclaration") || node.jjtGetParent().toString().equals("NormalInterfaceDeclaration"))
        {
            this.classe.setName(node.jjtGetFirstToken().image.toString());
        }
        else if (node.jjtGetParent().toString().equals("ConstructorDecl") || node.jjtGetParent().toString().equals("MethodOrFieldDecl") || node.jjtGetParent().toString().equals("InterfaceMethodOrFieldDecl") || node.jjtGetParent().toString().equals("InterfaceMemberDecl") || node.jjtGetParent().toString().equals("VoidMethodDecl"))
        {
            if (this.mode.equals("Method"))
            {
                this.method.setName(node.jjtGetFirstToken().image.toString());
                this.mode = "";
            }
            else if (mode.equals("Variable"))
            {
                this.mode = "";
                this.variable.setName(node.jjtGetFirstToken().image.toString());
            }
        }
		propagate(node, data);
		return data;
	}
    
    public Object visit(ClassBodyDeclaration node, Object data)
    {
        // récupération de l'accessibilité des variables de classe et des méthodes
        if (node.jjtGetLastToken().toString().equals(";"))
        {
            this.mode = "Variable";
            this.variable = new Variable();
            this.classe.addVariable(this.variable);
            if (node.jjtGetFirstToken().image.toString().equals("public") || node.jjtGetFirstToken().image.toString().equals("private"))
                this.variable.setAccessibility(node.jjtGetFirstToken().image.toString());
            else
                this.variable.setAccessibility("protected");
        }
        else if (node.jjtGetLastToken().toString().equals("}"))
        {
            this.mode = "Method";
            this.method = new Method();
            this.classe.addMethod(this.method);
            if (node.jjtGetFirstToken().image.toString().equals("@"))
            {
                this.method.setAccessibility("public");
            }
            else if (!node.jjtGetFirstToken().image.toString().equals("public") || !node.jjtGetFirstToken().image.toString().equals("private"))
            {
                this.method.setAccessibility("protected");
            }
            else
            {
                this.method.setAccessibility(node.jjtGetFirstToken().image.toString());
            }
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(InterfaceBodyDeclaration node, Object data)
    {
        // récupération de l'accessibilité des méthodes
        this.classe.setIsInterface(true);
        this.mode = "Method";
        this.method = new Method();
        this.classe.addMethod(this.method);
        if (node.jjtGetFirstToken().toString().equals("@"))
        {
            this.method.setAccessibility("public");
        }
        else if (!node.jjtGetFirstToken().image.toString().equals("public") || !node.jjtGetFirstToken().image.toString().equals("private"))
        {
            this.method.setAccessibility("protected");
        }
        else
        {
            this.method.setAccessibility(node.jjtGetFirstToken().image.toString());
        }
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(TypeDeclaration node, Object data)
    {
        // récupération de l'accessibilité des classes
        this.mode = "Class";
        this.classe = new Classe();
        this.classes.add(this.classe);
        this.classe.setAccessibility(node.jjtGetFirstToken().image.toString());
        this.classe.setPackageName(this.packageName);
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(Type node, Object data)
    {
        // permet de récupérer les types
        // FormalParameterDecls => pour les parametres
        // MethodOrFieldDecl => pour les types de return
        if (node.jjtGetParent().toString().equals("FormalParameterDecls"))
        {
            this.mode = "Variable";
            this.variable = new Variable();
            this.method.addParameter(this.variable);
            this.variable.setType(node.jjtGetFirstToken().image.toString());
        }
        else if (node.jjtGetParent().toString().equals("InterfaceMethodOrFieldDecl"))
        {
            this.method.setTypeReturn(node.jjtGetFirstToken().image.toString());
        }
        else if (node.jjtGetParent().toString().equals("NormalClassDeclaration"))
        {
            this.classe.setSuperClassName(node.jjtGetFirstToken().image.toString());
        }
        
        propagate(node, data);
        return data;
    }
    
    public Object visit(MethodOrFieldDecl node, Object data)
    {
        // permet de récupérer le type de retour
        if (node.jjtGetLastToken().toString().equals("}"))
        {
            this.method.setTypeReturn(node.jjtGetFirstToken().image.toString());
        }
        else if (node.jjtGetLastToken().toString().equals(";"))
        {
            this.variable.setType(node.jjtGetFirstToken().image.toString());
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(VariableDeclaratorId node, Object data)
    {
        // permet de récupérer le nom des paramètres de méthodes
        if (node.jjtGetParent().toString().equals("FormalParameterDeclsRest"))
        {
            this.variable.setName(node.jjtGetFirstToken().image.toString());
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(QualifiedIdentifier node, Object data)
    {
        if (node.jjtGetParent().toString().equals("CompilationUnit"))
        {
            this.packageName = node.jjtGetLastToken().image.toString();
        }
        propagate(node, data);
        return data;
    }
    
    public Object visit(ReferenceType node, Object data)
    {
        if (node.jjtGetParent().toString().equals("TypeList"))
        {
            this.classe.addImplement(node.jjtGetFirstToken().image.toString());
        }
        propagate(node, data);
        return data;
    }

}
