package com.bitbakery.arcwelder.psi;

import com.bitbakery.arcwelder.file.ArcFileType;
import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.util.Key;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.psi.stubs.StubElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Base class for all Arc PSI elements
 */
public abstract class ArcElement<T extends StubElement> extends StubBasedPsiElementBase<T> {
    public ArcElement(@NotNull final ASTNode node) {
        super(node);
    }

    @NotNull
    public Language getLanguage() {
        return ArcFileType.ARC_LANGUAGE;
    }

    @NotNull
    public SearchScope getUseScope() {
        // TODO - This is true as long as we have no inter-file references
        return new LocalSearchScope(getContainingFile());
    }

    public <T> T getUserData(Key<T> key) {
        return null;  // TODO - What the hell is this guy?? Poke around the Scala plugin, maybe...?
    }

    public <T> void putUserData(Key<T> key, T value) {
        // TODO - What the hell is this guy?? Poke around the Scala plugin, maybe...?
    }

    public Icon getIcon(int flags) {
        return null;
    }

    protected boolean isEmpty(ASTNode[] children) {
        return children == null || children.length < 1;
    }
}
