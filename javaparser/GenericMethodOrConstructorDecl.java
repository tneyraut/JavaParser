/* Generated By:JJTree: Do not edit this line. GenericMethodOrConstructorDecl.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package javaparser;

public
class GenericMethodOrConstructorDecl extends SimpleNode {
  public GenericMethodOrConstructorDecl(int id) {
    super(id);
  }

  public GenericMethodOrConstructorDecl(JavaParser1_7 p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JavaParser1_7Visitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=4794c7807662ba47a8c3cc382a76443a (do not edit this line) */