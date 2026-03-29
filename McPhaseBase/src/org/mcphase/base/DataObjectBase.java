package org.mcphase.base;

import org.openide.filesystems.FileObject;
import org.openide.loaders.DataNode;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.loaders.SaveAsCapable;
import org.openide.nodes.Children;
import org.openide.nodes.Node;
import org.openide.text.DataEditorSupport;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
//import org.openide.util.lookup.ProxyLookup;
import org.openide.util.lookup.InstanceContent;

/**
 * This class represents a file object and provides support for multiple editors.
 * @author Till Hoffmann
 */
public abstract class DataObjectBase extends MultiDataObject implements Lookup.Provider {

    final InstanceContent ic;
    private final AbstractLookup lookup;
  //  private final Lookup lookup;
  //  private final InstanceContent lookupContents = new InstanceContent();
    private DataEditorSupport support;

    /**
     * Creates a new instance of DataObjectBase
     * @param pf primary file object for this data object
     * @param loader loader that created the data object
     * @throws DataObjectExistsException if there is already a data object for this primary file
     */
    public DataObjectBase(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException {
        super(pf, loader);

        // We use InstanceContent because we need a modifiable
        // Lookup; see step 5 for the reason behind our
        // modifications. (Rich Client Programming)
        ic = new InstanceContent();
        lookup = new AbstractLookup(ic); // replace by MR by following line 23.1.22
        //lookup = new ProxyLookup(getCookieSet().getLookup(), new AbstractLookup(ic));
        //Add our editor support and the data object itself
        ic.add(getEditorSupport());
        getCookieSet().assign(SaveAsCapable.class, (SaveAsCapable) (FileObject folder, String fileName) -> {
            getEditorSupport().saveAs( folder, fileName );
        });
        ic.add((this));
    }

    /**
     * When overridden in a derived class, creates a new editor support.
     * @return a new editor support
     */
    protected abstract DataEditorSupport createEditorSupport();

    /**
     * Returns the current editor support or creates a new one if necessary.
     * @return the current editor support or a newly created one if necessary
     */
    public final DataEditorSupport getEditorSupport() {
        //Check whether an editor support exists
        if (support == null) {
            //Build a new editor support
            support = createEditorSupport();
        }
        return support;
    }

    @Override
    protected Node createNodeDelegate() {
        //Create a standard DataNode to represent the DataObject
        return new DataNode(this, Children.LEAF); //replaced MR by following line 23.1.22
      // return new DataNode(this, Children.LEAF,getLookup());
    }

    @Override
    public Lookup getLookup() {
        //Return the modifiable lookup
        return lookup;
    }

    @Override
    public <T extends Node.Cookie> T getCookie(Class<T> type) {
        //Check whether the lookup contains a cookie of the specified class
        //e.g. open cookie/save cookie etc.
        return lookup.lookup(type);
    }
}
