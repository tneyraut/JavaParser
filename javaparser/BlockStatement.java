/* Generated By:JJTree: Do not edit this line. BlockStatement.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package javaparser;

public
class BlockStatement extends SimpleNode {
  public BlockStatement(int id) {
    super(id);
  }

  public BlockStatement(JavaParser1_7 p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(JavaParser1_7Visitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=8e0e72fd72269e1ff0ba9aee93cb5919 (do not edit this line) */
