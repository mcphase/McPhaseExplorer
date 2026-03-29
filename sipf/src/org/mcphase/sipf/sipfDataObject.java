package org.mcphase.sipf;

import java.io.IOException;
import org.mcphase.base.DataObjectBase;
import org.mcphase.base.EditorSupportBase;
import org.mcphase.base.SourceView;
import org.mcphase.base.ViewBase;
import org.netbeans.core.spi.multiview.MultiViewDescription;
import org.openide.filesystems.FileObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiFileLoader;
import org.openide.text.DataEditorSupport;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;

public class sipfDataObject extends DataObjectBase {

    public sipfDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        
    }

    @Override
    public  DataEditorSupport createEditorSupport() {
        //Create a new editor support
        EditorSupportBase support = new EditorSupportBase(this);
        //Create a custom view description
        ViewBase visual = new ViewBase();
        //Set its properties
        visual.setDisplayName(NbBundle.getMessage(ViewBase.class, "DISP_EDITOR"));
        visual.setIcon(ImageUtilities.loadImage("org/mcphase/base/resources/visual.png"));
        visual.setViewElement(new sipfEditor(support));
        //Create the view elements
//        MultiViewDescription[] desc = {visual,new SourceView(support)};
        ViewBase source = new SourceView(support);
        MultiViewDescription[] desc = {source,visual};

        //Add the view elements
        support.setViewDescriptions(desc);
        //Set the default elements
        support.setDefaultViewDescription(desc[0]);
        //return the editor support
        return support;
    }
}
