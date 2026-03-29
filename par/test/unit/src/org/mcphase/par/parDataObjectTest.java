package org.mcphase.par;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package org.mcphase.par;

import org.mcphase.par.parDataObject;
import junit.framework.TestCase;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.Repository;
import org.openide.loaders.DataObject;

public class parDataObjectTest extends TestCase {

    public parDataObjectTest(String testName) {
        super(testName);
    }

    public void testDataObject() throws Exception {
        FileObject root = Repository.getDefault().getDefaultFileSystem().getRoot();
        FileObject template = root.getFileObject("Templates/Other/parTemplate.par");
        assertNotNull("Template file shall be found", template);

        DataObject obj = DataObject.find(template);
        assertEquals("It is our data object", parDataObject.class, obj.getClass());
    }
}
*/