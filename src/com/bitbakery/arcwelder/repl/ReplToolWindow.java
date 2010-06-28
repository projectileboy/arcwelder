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

import static com.bitbakery.arcwelder.ArcStrings.*;

import com.bitbakery.arcwelder.ArcIcons;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.actionSystem.ActionGroup;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPopupMenu;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The REPL tool window for the Arcwelder plugin. Encapsulates the UI as well as the interaction with the underlying Arc process
 */
public class ReplToolWindow implements ProjectComponent {

    private JTabbedPane tabbedPane;
    private ToolWindow toolWindow;
    private ActionPopupMenu popup;

    public void projectOpened() {
        try {
            initToolWindow();
        } catch (Exception e) {
            // TODO - Handle me for real...
            e.printStackTrace();  // TODO - Some sort of real error handling
        }

    }


    private void initToolWindow() throws ExecutionException, IOException {
        // TODO _ Blech... fix me!
        Project[] projects = ProjectManager.getInstance().getOpenProjects();
        Project myProject = projects[0];
        if (myProject != null) {
            tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);

            JPanel panel = new JPanel(new BorderLayout());
            panel.add(tabbedPane, BorderLayout.CENTER);

/*
            ActionManager am = ActionManager.getInstance();
            ActionGroup group = (ActionGroup) am.getAction("ArcReplActionGroup");
            ActionToolbar toolbar = am.createActionToolbar(str("repl.title"), group, false);
            panel.add(toolbar.getComponent(), BorderLayout.WEST);
*/

            toolWindow = ToolWindowManager.getInstance(myProject).registerToolWindow(str("repl.title"), false, ToolWindowAnchor.BOTTOM);
            ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
            Content content = contentFactory.createContent(panel, null, true);
            toolWindow.getContentManager().addContent(content);
            toolWindow.setIcon(ArcIcons.REPL);
            // toolWindow.setToHideOnEmptyContent(true);

/*
            popup = am.createActionPopupMenu(str("repl.title"), group);
            panel.setComponentPopupMenu(popup.getComponent());
            toolWindow.getComponent().setComponentPopupMenu(popup.getComponent());
            toolbar.getComponent().setComponentPopupMenu(popup.getComponent());
*/

            //createRepl();
        }
    }


    public void projectClosed() {
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
