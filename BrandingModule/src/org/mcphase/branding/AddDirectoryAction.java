/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mcphase.branding;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class AddDirectoryAction implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        //Action can be registered by uncommenting the xml in layer.xml
        org.netbeans.modules.favorites.Actions.add().actionPerformed(e);
        }
}
