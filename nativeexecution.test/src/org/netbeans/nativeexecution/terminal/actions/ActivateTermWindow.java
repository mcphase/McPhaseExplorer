/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.nativeexecution.terminal.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.netbeans.terminal.example.topcomponent.TerminalTopComponent;

public final class ActivateTermWindow implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // TODO implement action body
        TerminalTopComponent ttc = TerminalTopComponent.findInstance();
        ttc.open();
        ttc.requestActive();
    }
}
