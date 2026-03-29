/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.nativeexecution.terminal.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.logging.Logger;
//import org.openide.util.NbBundle;
//import org.openide.windows.TopComponent;
//import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
//import org.netbeans.api.settings.ConvertAsProperties;

//mport org.netbeans.modules.terminal.api.TerminalContainer;
//import org.openide.windows.IOContainer;
//import org.netbeans.lib.richexecution.program.Command;
//import org.netbeans.lib.richexecution.program.Program;
//import org.netbeans.lib.richexecution.OS;

import org.netbeans.modules.nativeexecution.api.ExecutionEnvironment;
import org.netbeans.modules.nativeexecution.api.ExecutionEnvironmentFactory;
import org.netbeans.modules.nativeexecution.api.NativeProcess;
import org.netbeans.modules.nativeexecution.api.NativeProcessBuilder;
import org.netbeans.modules.nativeexecution.api.pty.PtySupport;
//import org.netbeans.nativeexecution.terminal.ui.TargetSelector;
import org.netbeans.nativeexecution.terminal.util.EnvSupport;
import org.netbeans.modules.terminal.api.IOEmulation;

import java.io.IOException;
import org.openide.util.Exceptions;
import org.openide.windows.InputOutput;
import org.openide.windows.IOProvider;  /*
import org.openide.windows.IOContainer;

import org.netbeans.terminal.example.Config;
import org.netbeans.terminal.example.TerminalIOProviderSupport; */
import org.netbeans.terminal.example.OS;
import org.netbeans.terminal.example.topcomponent.TerminalTopComponent;

public final class termBut implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        TerminalTopComponent ttc = TerminalTopComponent.findInstance();
        ttc.open();
        ttc.requestActive();
        //super.componentActivated();
        //tc.componentActivated();

        final String cmd;
        final OS os = OS.get();
        switch (os) {
            case WINDOWS:
		cmd = "cmd";
                break;
	    default:
                cmd = "/bin/bash";
		break;
        }

/*      //Config config = Config.getCmdConfig(cmd);
        Config config = new Config(cmd,
	                  Config.Provider.TERM,
	                  Config.Provider.TERM,
	                  Config.AllowClose.ALWAYS,
	                  null,         // DispatchThread
	                  Config.Execution.NATIVE,
	                  Config.IOShuttling.INTERNAL,
			  Config.ContainerStyle.TABBED,
	                  true,		// restartable
	                  true,		// hupOnClose
			  false		// keep
			  );

        final TerminalIOProviderSupport support = new TerminalIOProviderSupport(config);

	IOContainer container = TerminalIOProviderSupport.getIOContainer(config);
	//container = null;	// work with default IO container

	IOProvider iop = TerminalIOProviderSupport.getIOProvider();
	support.executeNativeCommand(iop, container);


        //TerminalProvider terminalProvider = TerminalProvider.getDefault();
        //Terminal terminal = terminalProvider.createTerminal("command: " + cmd);
        //Program program = new Command(cmd);
        //terminal.startProgram(program, true);
*/
        final ExecutionEnvironment env = ExecutionEnvironmentFactory.getLocal();
        EnvSupport.ensureConnected(env);
        NativeProcessBuilder npb = NativeProcessBuilder.newProcessBuilder(env);

        int spidx = cmd.indexOf(' ');
        String exec;
        String[] argsArray;

        if (spidx < 0) {
            exec = cmd;
            argsArray = new String[0];
        } else {
            exec = cmd.substring(0, spidx);
            argsArray = cmd.substring(spidx + 1).split(" +"); // NOI18N
        }

        npb.setExecutable(exec).setArguments(argsArray);
        npb.setUsePty(true);

        //  IOProvider iop = getIOProvider();

        //  InputOutput io = iop.getIO(cmd, true);
        //  io.select();

          //IOProvider iop = TerminalIOProvider.getDefault();
            IOProvider iop = IOProvider.get("Terminal");
          //InputOutput io = iop.getIO(cmd, true);
            InputOutput io = iop.getIO(cmd, null, ttc.ioContainer());
            io.select();
            if (IOEmulation.isSupported(io)) {
                npb.getEnvironment().put("TERM", IOEmulation.getEmulation(io)); // NOI18N
            } else {
                npb.getEnvironment().put("TERM", "dumb"); // NOI18N
            }

            try {
                final NativeProcess nativeProcess = npb.call();
                PtySupport.connect(io, nativeProcess);
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    @Override
                    public void run() {
                       nativeProcess.destroy();
                    }
                });
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }

    }
}
