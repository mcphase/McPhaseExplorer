/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/actionListener.java to edit this template
 */
package org.mcphase.base;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.Action;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.nodes.Node;
import org.openide.util.ContextAwareAction;
import org.openide.util.NbBundle.Messages;
import org.openide.util.actions.SystemAction;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;
import org.openide.windows.OutputWriter;

@ActionID(
        category = "Help",
        id = "org.mcphase.base.Manual_pdf"
)
@ActionRegistration(
        iconBase = "org/mcphase/base/manual_icon_pdf.png",
        displayName = "#CTL_Manual_pdf"
)
@ActionReferences({
    @ActionReference(path = "Menu/Help", position = 100),
    @ActionReference(path = "Toolbars/File", position = 200),
    @ActionReference(path = "Shortcuts", name = "M-P")
})
@Messages("CTL_Manual_pdf=McPhase Manual PDF")
public final class Manual_pdf implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
      
             String mcphaseDir = System.getenv("MCPHASE_DIR");
             String HomeDir = System.getenv("HOME");
             String ds = System.getProperty("file.separator");
              String relDir = ds + "doc" + ds + "manual.pdf";
            File file;
            if (mcphaseDir != null) { file = new File(mcphaseDir+relDir); }
           else { file = new File(HomeDir+ds+"mato"+ds+"mcphas"+ds+"doc"+ds+"manual.pdf"); }
            try {
      Desktop.getDesktop().open(file);
  } catch (Exception ex) {
    System.out.println(ex);}
    }
    
}
