/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/actionListener.java to edit this template
 */
package org.mcphase.base;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.Action;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle.Messages;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Node;
import org.openide.util.ContextAwareAction;
import org.openide.util.Lookup;

@ActionID(
        category = "Tools",
        id = "org.mcphase.base.mchelp"
)
@ActionRegistration(
        iconBase = "org/mcphase/base/manual_icon.png",
        displayName = "#CTL_mchelp"
)
@ActionReferences({
    @ActionReference(path = "Toolbars/File", position = 300),
    @ActionReference(path = "Shortcuts", name = "M-M")
})
@Messages("CTL_mchelp=mchelp")
public final class mchelp implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        try {
             String mcphaseDir = System.getenv("MCPHASE_DIR");
             String HomeDir = System.getenv("HOME");
             String ds = System.getProperty("file.separator");
              String relDir = ds + "doc" + ds + "manual" + ds + "index.html";
            File file;
            if (mcphaseDir != null) { file = new File(mcphaseDir+relDir); }
           else { file = new File(HomeDir+ds+"mato"+ds+"mcphas"+ds+"doc"+ds+"manual"+ds+"index.html"); }
            FileObject man = FileUtil.toFileObject(file);
            DataObject newDo = DataObject.find(man);
            final Node node = newDo.getNodeDelegate();
        //     newDo.find(man).getLookup().lookup(FileObject.class).open();
    
             Action a = node.getActions(true)[1];
if (a instanceof ContextAwareAction) {
       a = ((ContextAwareAction) a).createContextAwareInstance(node.getLookup());
}
if (a != null) {
    a.actionPerformed(new ActionEvent(node, ActionEvent.ACTION_PERFORMED, "")); // NOI18N
}
        } catch (DataObjectNotFoundException ex) {
                    }
    }
}
