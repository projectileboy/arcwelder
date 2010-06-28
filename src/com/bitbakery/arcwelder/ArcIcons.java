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

package com.bitbakery.arcwelder;

import javax.swing.*;

import static com.intellij.openapi.util.IconLoader.findIcon;

/**
 * Contains paths to the various icons
 */
public class ArcIcons {
    private static final String BASE = "/icons/";

    public static final Icon REPL = findIcon(BASE + "arc16.png");
    public static final Icon FILE = findIcon(BASE + "arc16.png");

/*
    final Icon ARC_LARGE_ICON = IconLoader.findIcon(BASE + "arc128.png");
    final Icon ARC_MODULE_TYPE_ICON = IconLoader.findIcon(BASE + "arc24.png");

    final Icon ARC_CONFIG_ICON = IconLoader.findIcon(BASE + "arc32.png");
*/

    public static final Icon GROUPING = findIcon(BASE + "arcStructGrouping.png");
    public static final Icon DEF = findIcon(BASE + "arcStructViewDef.png");
    public static final Icon MAC = findIcon(BASE + "arcStructViewMac.png");
    public static final Icon EQ = findIcon(BASE + "arcStructViewEq.png");

}
