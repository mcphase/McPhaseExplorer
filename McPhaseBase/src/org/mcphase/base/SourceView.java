/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.mcphase.base;

import org.openide.text.DataEditorSupport;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

/**
 * This class implements a view description for a plain text editor.
 * @author Till Hoffmann
 */
public class SourceView extends ViewBase  {
    /**
     * Creates a new instance of SourceView. 
     * @param editorSupport the editor support associated with the view description
     */
    public SourceView(DataEditorSupport editorSupport) {
        this.setDisplayName(NbBundle.getMessage(ViewBase.class, "DISP_SOURCE"));
        this.setIcon(ImageUtilities.loadImage("org/mcphase/base/resources/source.png"));
        this.setViewElement(new SourceEditor(editorSupport));
    }

}
