/* Generated By:JJTree: Do not edit this line. InterfaceBodyDeclaration.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package javaparser;

public
class InterfaceBodyDeclaration extends SimpleNode {
  public InterfaceBodyDeclaration(int id) {
    super(id);
  }

  public InterfaceBodyDeclaration(JavaParser1_7 p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JavaParser1_7Visitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=3e84b9ff094be6953c245acb7721653e (do not edit this line) */
