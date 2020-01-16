/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.java.ast;

import net.sourceforge.pmd.lang.java.ast.InternalInterfaces.QualifierOwner;

/**
 * A field access expression.
 *
 * <pre class="grammar">
 *
 * FieldAccess ::= {@link ASTExpression Expression} "." &lt;IDENTIFIER&gt;
 *
 * </pre>
 */
public final class ASTFieldAccess extends AbstractJavaExpr implements ASTAssignableExpr, QualifierOwner, LeftRecursiveNode {
    ASTFieldAccess(int id) {
        super(id);
    }


    ASTFieldAccess(JavaParser p, int id) {
        super(p, id);
    }


    /**
     * Promotes an ambiguous name to the LHS of this node.
     */
    ASTFieldAccess(ASTAmbiguousName lhs, String fieldName) {
        super(JavaParserTreeConstants.JJTFIELDACCESS);
        this.jjtAddChild(lhs, 0);
        this.setImage(fieldName);
    }


    /** Returns the name of the field. */
    public String getFieldName() {
        return getImage();
    }


    @Override
    public Object jjtAccept(JavaParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }


    @Override
    public <T> void jjtAccept(SideEffectingVisitor<T> visitor, T data) {
        visitor.visit(this, data);
    }
}