/* Generated By:JJTree: Do not edit this line. ConstantDeclarator.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package javaparser;

public
class ConstantDeclarator extends SimpleNode {
  public ConstantDeclarator(int id) {
    super(id);
  }

  public ConstantDeclarator(JavaParser1_7 p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JavaParser1_7Visitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=7e8410fe4203b8bc6805a44f6c9b5117 (do not edit this line) */