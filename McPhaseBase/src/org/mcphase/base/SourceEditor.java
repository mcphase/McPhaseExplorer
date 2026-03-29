package org.mcphase.base;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledDocument;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.netbeans.core.spi.multiview.MultiViewFactory;
import org.openide.awt.UndoRedo;
import org.openide.loaders.DataObject;
import org.openide.nodes.Node;
import org.openide.text.CloneableEditor;
import org.openide.text.DataEditorSupport;
import org.openide.text.NbDocument;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Mutex;
import org.openide.util.NbBundle;
import org.openide.util.NotImplementedException;
import org.openide.util.lookup.ProxyLookup;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.TopComponent;

/**
 * This class implements an editor providing access to a file using a plain text
 * interface. Please see 'Rich Client Programming: Plugging into the NetBeans
 * Platform' for a full description of the implementation.
 * @author Till Hoffmann
 */


public class SourceEditor extends CloneableEditor implements MultiViewElement, Runnable, Serializable {
    private final DataEditorSupport editorSupport;
    private transient MultiViewElementCallback callback;
    private transient JComponent toolbar;
    private final String name;
    
    /**
     * Creates a new instance of SourceEditor.
     * @param editorSupport the editor support associated with the editor
     */
    public SourceEditor(DataEditorSupport editorSupport) {
        super(editorSupport);
        this.editorSupport = editorSupport;
        this.name = "SourceEditor";
    }
    
        protected InputOutput getInputOutput() {
        return IOProvider.getDefault().getIO(name, false);
    }
    protected final Color outputGreen = Color.GREEN.darker();
    protected final Color outputOrange = Color.ORANGE.darker();
    protected final Color outputRed = Color.RED.darker();

    
    public SourceEditor() {
        super();
        this.editorSupport =(DataEditorSupport)cloneableEditorSupport();
        this.name="SourceEditor";
    }
    
    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }

    @Override
   public JComponent getToolbarRepresentation() {
        JEditorPane editorPane = getEditorPane();
        if (editorPane != null) {
            Document doc = editorPane.getDocument();
            if (doc instanceof NbDocument.CustomToolbar) {
                if (toolbar == null) {
                    toolbar = ((NbDocument.CustomToolbar) doc).createToolbar(getEditorPane());
                }
                return toolbar;
            }
        }
        return null;
    }
/*    @Override
    public JComponent getToolbarRepresentation() {
        return new JPanel();
    }*/
     @Override
    public Action[] getActions() {
        return editorSupport.getDataObject().getNodeDelegate().getActions(false);
    }

   /**
     * Returns the text associated with the editor.
     * @return the text associated with the editor.
     */
    public String getDocumentText() {
        StyledDocument d = editorSupport.getDocument();
        if (d==null){return null;}
        try {d.getLength();
            return d.getText(0, d.getLength());
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }


@Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
        updateName();
    }

    @Override
    public CloseOperationState canCloseElement() {
if(!editorSupport.isModified())
     { return CloseOperationState.STATE_OK;}
// return a state which will save/discard changes and is called by close handler
    AbstractAction save = new AbstractAction(){
                public void actionPerformed(ActionEvent arg0) {
                    //save changes
                    try {
                        getEditorSupport().saveDocument();
                    } catch (IOException ex) {
                    }
                }

            };
    save.putValue(Action.LONG_DESCRIPTION, NbBundle.getMessage(DataObject.class,
                        "MSG_SaveFile", // NOI18N
                        getEditorSupport().getDataObject().getPrimaryFile().getNameExt()));     
    return MultiViewFactory.createUnsafeCloseState(
            "ID_JAXWS_CLOSING", // NOI18N
            save,
            MultiViewFactory.NOOP_CLOSE_ACTION); 
     
    }

    @Override 
    public void updateName() {
        Mutex.EVENT.readAccess(this);
        
    }

    @Override
    public void run() {
        MultiViewElementCallback c = callback;
        if (c == null) {
            return;
        }
        TopComponent tc = c.getTopComponent();
        if (tc == null) {
            return;
        }
        super.updateName();
        tc.setName(this.getName());
        tc.setDisplayName(this.getDisplayName());
        tc.setHtmlDisplayName(this.getHtmlDisplayName());
           
    }

      @Override
    public Lookup getLookup() {
        return ((DataObjectBase) editorSupport.getDataObject()).getNodeDelegate().getLookup();
    }

/*    @Override
    public Lookup getLookup() {
     return new ProxyLookup(super.getLookup(),getEditorSupport().getDataObject().getNodeDelegate().getLookup());
    }
  */  
    @Override
    public void componentActivated() {
        //super.componentActivated();
    }
    
    @Override
    public void componentDeactivated() {
        super.componentDeactivated();
    }
    
    @Override
    public void componentOpened() {
//        super.componentOpened();
    }

    @Override
    public void componentClosed() {
  //      super.componentClosed();
    }
    
    @Override
    protected boolean closeLast() {
        //if(MultiViewSupport.getNumberOfClones(callback.getTopComponent()) == 0) {
            // this is the last editor component so call super.closeLast
            return super.closeLast();
       // }
       // return true;
    }
    

    @Override
    public void componentShowing() {
        super.componentShowing();
        DataObject dobj = getEditorSupport().getDataObject();
        if (dobj == null || !dobj.isValid()) {
            setActivatedNodes(new Node[] {});
        } else {
            setActivatedNodes(new Node[] {getEditorSupport().getDataObject().getNodeDelegate()});
        }
    }
    
 private DataEditorSupport getEditorSupport() {
        return (DataEditorSupport) cloneableEditorSupport();
    }
    //@Override
    //public void componentHidden() {
    //    super.componentHidden();
    //}

   @Override
    public void componentHidden() {
        //super.componentHidden();
        //setActivatedNodes(new Node[] {});
    }
    
    @Override
    public void open() {
        if (callback != null) {
            callback.requestVisible();
        } else {
            super.open();
        }
        
    }
    @Override
    public UndoRedo getUndoRedo() {
        //return super.getUndoRedo();
        return UndoRedo.NONE;
    }
    
    protected CloneableEditor createCloneableEditor () {
        return new SourceEditor(editorSupport);
    }
    @Override
    public void requestVisible() {
        if (callback != null)
            callback.requestVisible();
        else
            super.requestVisible();
    }
    
    @Override
    public void requestActive() {
        if (callback != null)
            callback.requestActive();
        else
            super.requestActive();
    }
}
