package org.mcphase.base;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.netbeans.core.spi.multiview.MultiViewFactory;
import org.openide.cookies.EditCookie;
import org.openide.cookies.EditorCookie;
import org.openide.cookies.OpenCookie;
import org.openide.cookies.SaveCookie;
import org.openide.loaders.SaveAsCapable;
import org.openide.filesystems.FileLock;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObject;
import org.openide.loaders.MultiDataObject;
import org.openide.text.CloneableEditorSupport;
import org.openide.text.DataEditorSupport;

/**
 * This class implements a basic editor support to be used with multiple editors.
 * @author Till Hoffmann
 */
public class EditorSupportBase extends DataEditorSupport implements OpenCookie, EditorCookie, EditCookie, SaveAsCapable  {
    /**
     * This class extends the basic DataEditorSupport.Env environment by a save
     * option.
     */
    static class EnvironmentBase extends DataEditorSupport.Env implements SaveCookie {
        private EnvironmentBase(DataObject obj) {
            super(obj);
        }

        @Override
        protected FileObject getFile() {
            return super.getDataObject().getPrimaryFile();
        }

        @Override
        protected FileLock takeLock() throws IOException {
            return ((MultiDataObject) super.getDataObject()).getPrimaryEntry().takeLock();
        }

        @Override
       public void save() throws IOException {
            ((DataEditorSupport) this.findCloneableOpenSupport()).saveDocument();
        }

    }

    /**
     * Creates a new instance of EditorSupportBase.
     * @param dataObject the DataObject to use
     */
    public EditorSupportBase(DataObject dataObject) {
        super(dataObject, new EnvironmentBase(dataObject));
            }

    @Override
    protected boolean asynchronousOpen() {
         return false;
    }
    
    @Override
    protected boolean notifyModified() {
       boolean retValue = super.notifyModified();
        if (retValue) {
            //Add the save cookie to the data object
            ((DataObjectBase) getDataObject()).ic.add(env);
        }
        return retValue;
    }

    @Override
    protected void notifyUnmodified() {
        super.notifyUnmodified();
        //Remove the save cookie from the data object
        ((DataObjectBase) getDataObject()).ic.remove(env);
    }

    @Override
    protected CloneableEditorSupport.Pane createPane() {
        //Get all view descriptions as well as the default description
        if (viewDescriptions == null) {
            viewDescriptions = getViewDescriptions();
        }
        if (defaultViewDescription == null) {
            defaultViewDescription = getDefaultViewDescription();
        }
        //Return a new editor pane based on the
        return (CloneableEditorSupport.Pane) MultiViewFactory.createCloneableMultiView(viewDescriptions, defaultViewDescription);
    }

    private MultiViewDescription[] viewDescriptions;
    private MultiViewDescription defaultViewDescription;

    // <editor-fold defaultstate="collapsed" desc="Properties">
    /**
     * Returns the view descriptions.
     * @return the view descriptions.
     */
    public MultiViewDescription[] getViewDescriptions() {
        return viewDescriptions;
    }

    /**
     * Returns the view description selected by default.
     * @return the view description selected by default.
     */
    public MultiViewDescription getDefaultViewDescription() {
        return defaultViewDescription;
    }

    /**
     * Sets the view descriptions.
     * @param viewDescriptions the view descriptions
     */
    public void setViewDescriptions(MultiViewDescription[] viewDescriptions) {
        this.viewDescriptions = viewDescriptions;
    }

    /**
     * Sets the view description selected by default.
     * @param defaultViewDescription the view description selected by default.
     */
    public void setDefaultViewDescription(MultiViewDescription defaultViewDescription) {
        this.defaultViewDescription = defaultViewDescription;
    }// </editor-fold>
}
