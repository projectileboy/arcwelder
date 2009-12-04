package com.bitbakery.arcwelder;

import com.intellij.lang.Commenter;

/**
 * Defines the support for "Comment with Line Comment" and "Comment with Block Comment" actions for Arc source files.
 */
public class ArcCommenter implements Commenter {

    public String getLineCommentPrefix() {
        return ";";
    }

    public String getBlockCommentPrefix() {
        return "#|";
    }

    public String getBlockCommentSuffix() {
        return "|#";
    }

    public String getCommentedBlockCommentPrefix() {
        return getBlockCommentPrefix();
    }

    public String getCommentedBlockCommentSuffix() {
        return getBlockCommentSuffix();
    }
}
