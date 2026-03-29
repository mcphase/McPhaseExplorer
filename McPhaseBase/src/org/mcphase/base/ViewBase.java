package org.mcphase.base;

import java.io.Serializable;
import java.awt.Image;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewElement;
/*import org.openide.text.DataEditorSupport;*/
import org.openide.util.HelpCtx;
import org.openide.windows.TopComponent;

/**
 * This class implements a MultiViewDescription to streamline the development
 * process.
 * @author Till Hoffmann
 */
public class ViewBase implements MultiViewDescription, Serializable {

    private MultiViewElement viewElement = null;
    private String displayName;
    private Image icon;

    // <editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Sets the view element to be used.
     * @param viewElement the view element to be used
     */
    public void setViewElement(MultiViewElement viewElement) {
        this.viewElement = viewElement;
    }

    /**
     * Returns the view element being used.
     * @return the view element being used
     */
    public MultiViewElement getViewElement() {
        return viewElement;
    }

    /**
     * Sets the name displayed in the tab.
     * @param displayName the name to be displayed
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Returns the name displayed in the tab.
     * @return the name displayed in the tab.
     */
    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the icon displayed in the file's window while this view description
     * is selected.
     * @param icon the icon to be displayed
     */
    public void setIcon(Image icon) {
        this.icon = icon;
    }

    /**
     * Returns the icon displayed in the file's window while this view description
     * is selected.
     * @return the icon being displayed
     */
    @Override
    public Image getIcon() {
        return icon;
    }// </editor-fold>

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }

    @Override
    public HelpCtx getHelpCtx() {
        return HelpCtx.DEFAULT_HELP;
    }

    @Override
    public String preferredID() {
        return ViewBase.class.toString();
    }

    @Override
    public MultiViewElement createElement() {
        return viewElement;
    }
}