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

package com.bitbakery.arcwelder.repl;

import com.intellij.openapi.module.ModuleComponent;
import org.jetbrains.annotations.NotNull;

/**
 * The REPL tool window for the Arcwelder plugin. Encapsulates the UI as well as the interaction with the underlying Arc process
 */
public class ReplToolWindow implements ModuleComponent {

    public void projectOpened() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void projectClosed() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void moduleAdded() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public String getComponentName() {
        return "arc.repl.toolWindow";
    }

    public void initComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void disposeComponent() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
