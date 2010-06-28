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

import com.bitbakery.arcwelder.file.ArcFileType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;

/**
 * Created by IntelliJ IDEA.
 * User: kurtc
 * Date: Nov 16, 2009
 * Time: 2:01:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ArcElementType extends IElementType {

    public ArcElementType(@NonNls String debugName) {
        super(debugName, ArcFileType.ARC_LANGUAGE);
    }

    @SuppressWarnings({"HardCodedStringLiteral"})
    public String toString() {
        return "Arc:" + super.toString();
    }
}

