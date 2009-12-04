package com.bitbakery.arcwelder.lexer;

import com.intellij.lexer.FlexAdapter;

import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: kurtc
 * Date: Nov 14, 2009
 * Time: 2:31:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArcLexer extends FlexAdapter {
    public ArcLexer() {
        super(new _ArcLexer((Reader) null));
    }
}
