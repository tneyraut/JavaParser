package javaparser;

public class AbstractVisitor implements JavaParser1_7Visitor
{
    public AbstractVisitor(){
    }

    protected void propagate(Node node, Object data){
        for(int i = 0; i < node.jjtGetNumChildren(); ++i){
            node.jjtGetChild(i).jjtAccept(this, data);
        }
    }

  public Object visit(SimpleNode node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Identifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(QualifiedIdentifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(QualifiedIdentifierList node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(CompilationUnit node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ImportDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Ellipsis node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(HasEllipsis node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(HasNotEllipsis node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(PossibleStaticModifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(HasStaticModifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(HasNotStaticModifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TypeDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EmptyInterfaceDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ClassOrInterfaceDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ModifierList node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ClassDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(NormalClassDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EnumDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(NormalInterfaceDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationTypeDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Type node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(BasicType node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ReferenceType node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TypeArguments node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TypeArgument node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(NonWildcardTypeArguments node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TypeList node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TypeArgumentsOrDiamond node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(NonWildcardTypeArgumentsOrDiamond node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TypeParameters node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TypeParameter node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Bound node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Modifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(KeywordModifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Annotations node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Annotation node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationElement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ElementValuePairs node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ElementValuePair node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ElementValue node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ElementValueArrayInitializer node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ElementValues node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ClassBody node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ClassBodyDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(StaticInitBlock node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(MemberDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VoidMethodDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ConstructorDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(MethodOrFieldDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(MethodOrFieldRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(FieldDeclaratorsRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(MethodDeclaratorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(MethodBody node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EmptyBody node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VoidMethodDeclaratorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ConstructorDeclaratorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(GenericMethodOrConstructorDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(GenericMethodOrConstructorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceBody node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceBodyDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(NonEmptyInterfaceDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceMemberDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceMethodOrFieldDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceMethodOrFieldRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ConstantDeclaratorsRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ConstantDeclaratorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ConstantDeclarator node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceMethodDeclaratorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VoidInterfaceMethodDeclaratorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InterfaceGenericMethodDecl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(FormalParameters node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(FormalParameterDecls node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VariableModifier node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(FormalParameterDeclsRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VariableDeclaratorId node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VariableDeclarators node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VariableDeclarator node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VariableDeclaratorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(VariableInitializer node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ArrayInitializer node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Block node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(BlockStatements node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(BlockStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(LocalVariableDeclarationStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Statement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EmptyStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(IdentifierStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(StatementExpression node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(IfStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ElseStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(NoElseStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AssertStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(SwitchStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(WhileStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(DoStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ForStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(BreakStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(LabeledBreak node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(UnlabeledBreak node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ContinueStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(LabeledContinue node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(UnlabeledContinue node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ReturnStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ThrowStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(SynchronizedStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(TryStatement node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Catches node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(CatchClause node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(CatchType node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Finally node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ResourceSpecification node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Resources node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Resource node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(SwitchBlockStatementGroups node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(SwitchBlockStatementGroup node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(SwitchLabels node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(SwitchLabel node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EnumConstantName node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ForControl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ForVarControl node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ForVarControlRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ForVariableDeclaratorsRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ForInit node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ForUpdate node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Expression node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ExpressionRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AssignmentOperator node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Expression1 node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Expression1Rest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Expression2 node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Expression2Rest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InfixOp node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Expression3 node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(PrefixOp node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(PostfixOp node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Primary node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Literal node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(IntegerLiteral node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(FloatingPointLiteral node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(CharacterLiteral node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(StringLiteral node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(BooleanLiteral node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(NullLiteral node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ParExpression node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Arguments node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(SuperSuffix node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ExplicitGenericInvocationSuffix node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Creator node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(CreatedName node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ClassCreatorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ArrayCreatorRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(IdentifierSuffix node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(ExplicitGenericInvocation node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(InnerCreator node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(Selector node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EnumBody node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EnumConstants node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EnumConstant node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(EnumBodyDeclarations node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationTypeBody node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationTypeElementDeclarations node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationTypeElementDeclaration node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationTypeElementRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationMethodOrConstantRest node, Object data){
		propagate(node, data);
		return data;
	}
  public Object visit(AnnotationMethodRest node, Object data){
		propagate(node, data);
		return data;
	}
}
