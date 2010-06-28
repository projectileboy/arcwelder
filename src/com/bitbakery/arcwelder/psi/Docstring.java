/*
 * Copyright (c) Kurt Christensen, 2010.
 *
 * Licensed under the Artistic License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 *
 * Unless required by applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.bitbakery.arcwelder.psi;

import com.intellij.lang.ASTNode;

/**
 * PSI element for a docstring, which (by convention) is the string literal following
 * the parameter list in a function or macro definition which contains more than
 * one expression in the body. Proper syntax highlighting is applied by ArcAnnotator.
 */
public class Docstring extends ArcElement {
    public Docstring(ASTNode node) {
        super(node);
    }
}