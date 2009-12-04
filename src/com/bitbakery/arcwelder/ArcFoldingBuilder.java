package com.bitbakery.arcwelder;

import com.bitbakery.arcwelder.lexer.ArcTokenTypes;
import com.bitbakery.arcwelder.psi.ArcElementTypes;
import com.bitbakery.arcwelder.psi.Assignment;
import com.bitbakery.arcwelder.psi.Def;
import com.bitbakery.arcwelder.psi.Mac;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.List;

import static com.bitbakery.arcwelder.lexer.ArcTokenTypes.*;
import static com.bitbakery.arcwelder.psi.ArcElementTypes.*;

/**
 * Defines how code folding should behave for Arc files
 */
public class ArcFoldingBuilder implements FoldingBuilder {

    public String getPlaceholderText(ASTNode node) {
        if (node.getElementType() == FUNCTION_DEFINITION) {
            Def def = (Def) node.getPsi();
            return "(def " + def.getName() + " ...)";
        } else if (node.getElementType() == MACRO_DEFINITION) {
            Mac mac = (Mac) node.getPsi();
            return "(mac " + mac.getName() + " ...)";
        } else if (node.getElementType() == ASSIGNMENT) {
            Assignment eq = (Assignment) node.getPsi();
            return "(= " + eq.getName() + " ...)";
        } else if (node.getElementType() == BLOCK_COMMENT || node.getElementType() == MULTILINE_COMMENT) {
            String text = node.getText();
            return text.length() > 20 ? text.substring(0, 20) + "..." : text;
        }
        return null;
    }

    public boolean isCollapsedByDefault(ASTNode node) {
        return node.getElementType() == BLOCK_COMMENT;
    }

    public FoldingDescriptor[] buildFoldRegions(ASTNode node, Document document) {
        touchTree(node);
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    /**
     * We have to touch the PSI tree to get the folding to show up when we first open a file
     */
    private void touchTree(ASTNode node) {
        if (node.getElementType() == FILE) {
            final PsiElement firstChild = node.getPsi().getFirstChild();
        }
    }

    private void appendDescriptors(final ASTNode node, final List<FoldingDescriptor> descriptors) {
        if (isFoldableNode(node)) {
            descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
        }

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            appendDescriptors(child, descriptors);
            child = child.getTreeNext();
        }
    }

    private boolean isFoldableNode(ASTNode node) {
        return (node.getElementType() == FUNCTION_DEFINITION)
                || (node.getElementType() == MACRO_DEFINITION)
                || (node.getElementType() == ASSIGNMENT)
                || (node.getElementType() == MULTILINE_COMMENT)
                || (node.getElementType() == BLOCK_COMMENT);
    }
}
