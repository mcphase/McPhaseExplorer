package org.mcphase.base;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ItemEvent;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import javax.swing.Action;
import javax.swing.InputVerifier;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.UndoRedo;
import org.openide.text.DataEditorSupport;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Mutex;
import org.openide.util.NotImplementedException;
import org.openide.windows.IOProvider;
import org.openide.windows.InputOutput;

/**
 * This class implements a basic visual editor, which can be embedded in a
 * multiple-editor environment.
 * @author Till Hoffmann
 */
public class VisualEditorBase extends JPanel implements MultiViewElement, Serializable {

    protected final DataEditorSupport support;
    private MultiViewElementCallback callback;
    private final String name;

    /**
     * Creates a new instance of VisualEditorBase.
     * @param support the editor support associated with the editor
     * @param name the name of the editor as displayed in a potential output window
     */
    public VisualEditorBase(DataEditorSupport support, String name) {
        this.support = support;
        this.name = name;
    }

    // <editor-fold defaultstate="collapsed" desc="InputOutput">
    /**
     * Returns an IO object to read and write to and from the output window.
     * @return an IO object
     */
    protected InputOutput getInputOutput() {
        return IOProvider.getDefault().getIO(name, false);
    }
    protected final Color outputGreen = Color.GREEN.darker();
    protected final Color outputOrange = Color.ORANGE.darker();
    protected final Color outputRed = Color.RED.darker();
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Interface implementation">

    /**
     *
     * @return
     */
    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }

    /**
     *
     * @return
     */
    @Override
    public JComponent getToolbarRepresentation() {
        return new JPanel();
    }

    /**
     *
     * @return
     */
    @Override
    public Action[] getActions() {
        return support.getDataObject().getNodeDelegate().getActions(false);
    }

    /**
     *
     * @return
     */
    @Override
    public Lookup getLookup() {
        return ((DataObjectBase) support.getDataObject()).getNodeDelegate().getLookup();
    }

    /**
     *
     * 
     */
    @Override
    public void componentOpened() {
    }

    /**
     *
     * 
     */
    @Override
    public void componentClosed() {
    }

    /**
     *
     * 
     */
    @Override
    public void componentShowing() {
        updateControls();
    }

    /**
     *
     * 
     */
    @Override
    public void componentHidden() {
    }

    /**
     *
     * 
     */
    @Override
    public void componentActivated() {
    }

    /**
     *
     * 
     */
    @Override
    public void componentDeactivated() {
    }

    /**
     *
     * @return
     */
    @Override
    public UndoRedo getUndoRedo() {
        return UndoRedo.NONE;
    }

    /**
     *
     * 
     * @param callback
     */
    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
        this.callback = callback;
     
    } 
    
   
   

    /**
     *
     * @return
     */
    @Override
    public CloseOperationState canCloseElement() {
         return CloseOperationState.STATE_OK;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Variables">
    /**
     * Returns the value of a key-value pair type variable or <code>null</code>,
     * if the variable can not be found.
     * @param name the variable name
     * @return the value of the key-value pair type variable or <code>null</code>,
     * if the variable can not be found.
     */
    // Convention
      // 1) normal variable "ki"
      // 2) set of variables in one line ":hmin:hmax:deltah" (deltah is the variable which is registered)
      // 3) set of variables in one line with common name,  "hklline:h1:k1:l1:hN:kN:lN:Nstp:k1"  (for k1)
      // ad 3) more than one such variable is possible in one mcdisp.par file
      
    public String getVariable(String name) {
        return VariableHelper.getVariable(name, getDocumentText());
    }

    /**
     * Sets the value of a key-value pair type variable.
     * @param variable the name of the variable
     * @param value the value to set
     */
    // Convention
      // 1) normal variable "ki"
      // 2) set of variables in one line ":hmin:hmax:deltah" (deltah is the variable which is registered)
      // 3) set of variables in one line with common name,  "hklline:h1:k1:l1:hN:kN:lN:Nstp:k1"  (for k1)
      // ad 3) more than one such variable is possible in one mcdisp.par file
      
    public void setVariable(String variable, String value) {
        //Get a matcher for the current text
        //System.out.printf("setVariable %s\n",variable);
                
        Matcher m = VariableHelper.VariableFind(variable, getDocumentText());
        Matcher cm = VariableHelper.getCommentedVariableMatcher(variable, getDocumentText());
         //Get the document as well
        StyledDocument d = support.getDocument();

        try {
            //If the value is null, remove the variable
            if (value == null || value.length() == 0) {
                if (m!=null) {
           // --> here we should comment the variable containing line with a # sign, i.e.
           // 1) if there is a #! at the beginning of line --> substitute it with #
           // 2) if there is nothing at the beginning of line --> put #
               //System.out.printf("removing %s because var %s zero\n", d.getText(m.start(), 2),variable);
                if(d.getText(m.start(), 2).equals("#!")){d.remove(m.start()+1,1); }
                else{
                d.insertString(m.start(),"#", null);
                }
           // start() Returns the start index of the previous match.
           // start(1) Returns the start index of the subsequence captured by the given group during the previous match operation.
           // m.end(int group)  Returns the offset after the last character of the subsequence captured by the given group during the previous match operation
                //    d.remove(m.start(1), m.end(1) - m.start(1));
                }
                //Nothing found, nothing to delete
                return;
            }
            //Otherwise set the variable
            //System.out.printf("setting %s\n",variable);
                
            if (m!=null) {
                //if possible - Replace the matched value
                d.remove(m.start(3), m.end(3) - m.start(3));
                //Insert the value
                d.insertString(m.start(3), value, null);
            } else {
                String v[]=variable.split(":");boolean p=false;
                String vs=v[0];if(vs.length()==0){vs=v[1];}
                 m = VariableHelper.getVariableMatcher(vs, getDocumentText());
                cm = VariableHelper.getCommentedVariableMatcher(vs, getDocumentText());
     
                //System.out.printf("-1)looking for comment line containing %s before any #!%s\n",vs,vs);
                if(cm.find())
                {if(m.find()){if(cm.start()<m.start()){
                    d.remove(cm.start(),1);
                if(d.getText(cm.start()-1, 1).equals("\n"))
                  {//System.out.printf("...inserting #!%s\n ",variable);
                  d.insertString(cm.start(), "#!", null);
                  }
                  }
                 } 
                }

                // here we should insert the variable into the document
                //0) here we should look if there is another variable
                //  present in some line where we could insert it (e.g. for ":var1:var2:var3:var2" case
                // System.out.printf("0)looking for other variable of set %s\n",variable);
                Integer ofs=0,i=1;
                if(v.length>2)
                {/*for(int i=1;i<v.length-1;++i)
                 {//System.out.printf(".. checking %b: %s\n",i,v[i]);
                  if(v[i].equals(v[v.length-1])){p=true;}
                  m = VariableHelper.getVariableMatcher(v[0]+":"+v[i], getDocumentText());  
                  if(m.find()) // found - insert variable before / after depending on p
                  {ofs=m.end(3);
                   if(v[i].equals(v[v.length-1])){d.remove(m.start(2), m.end(3) - m.start(2));ofs=m.start(2);}
                    if(p==true){d.insertString(m.start(2), String.format(" %s%c%s ", v[v.length-1], '=', value), null);}
                  else{++i;
                    */while(p==false&&i<v.length-1)
                      {//System.out.printf("found...checking %b: %s\n",i,v[i]);
                       m = VariableHelper.getVariableMatcher(v[0]+":"+v[i], getDocumentText());  
                        if(m.find())
                        {ofs=m.end(3);
                         if(v[i].equals(v[v.length-1]))
                         {d.remove(m.start(2), m.end(3) - m.start(2));ofs=m.start(2);}
                        }
                        if(v[i].equals(v[v.length-1])){p=true;}
                       ++i;
                      }
                      // ofs will be initialized unless 
                      if(ofs>0){d.insertString(ofs, String.format(" %s%c%s", v[v.length-1], '=', value), null);
                      return;}
                    }
                      //continue; break; // does this work ? we want to 
                      //}
                // }
                //}  
                
                // 1) look out for a comment line containing "variable=", if it exists
                //    replace # with #! and set variable
                 m = VariableHelper.getVariableMatcher(variable, getDocumentText());
                cm = VariableHelper.getCommentedVariableMatcher(variable, getDocumentText());
               //System.out.printf("1)looking for comment line containing %s\n",variable);
                
                 while(cm.find(0)&&!m.find(0))
                 {//System.out.printf("...found %s\n ",variable);
                  d.remove(cm.start(),1);
                 if(d.getText(cm.start()-1, 1).equals("\n"))
                  {//System.out.printf("...inserting #!%s\n ",variable);
                  d.insertString(cm.start(), "#!", null);
                  }
                 m = VariableHelper.getVariableMatcher(variable, d.getText(0, d.getLength()));
                 cm= VariableHelper.getCommentedVariableMatcher(variable, d.getText(0, d.getLength()));
                 } 
                  m = VariableHelper.getVariableMatcher(variable, d.getText(0, d.getLength()));
                ////System.out.printf("m.find=%b\n ",m.find());
                 //System.out.printf("text=%s\n ",d.getText(0, d.getLength()));
                 if(m.find())
                 {//System.out.printf("inserting value for %s\n ",variable);
                  //Replace the matched value
                  d.remove(m.start(3), m.end(3) - m.start(3));
                  //Insert the value
                  d.insertString(m.start(3), value, null);
                 }
                 else
                 {   // 2a) else look for a variable most similar to the variable and insert the 
                  //    variable=value into the line  before
                  //System.out.printf("2)looking for another variable most similar to %s\n",variable);
                  String vi=String.format("#!%s\n",v[0]+"= "+v[v.length-1]+"="+value);
                  String varp=v[0];
                  if(varp.length()==0||v.length==1){varp=v[v.length-1];
                                   vi=String.format("#!%s\n",v[v.length-1]+"="+value);
                  }
                  while(varp.length()>1&&!cm.find()) {varp=varp.substring(0,varp.length() -1);
                  cm=VariableHelper.getApproxVariableMatcher(varp, d.getText(0, d.getLength()));
                  }//System.out.printf("%s\n",varp);
                  if(cm.find())
                  {d.insertString(cm.start(),vi,null);
                  }
                  else
                  {String varm=v[0];
                  if(varm.length()==0){varm=v[v.length-1];
                  }
                  while(varm.length()>1&&!cm.find()) { varm=varm.substring(1,varm.length());
                  cm=VariableHelper.getApproxVariableMatcher(varm, d.getText(0, d.getLength()));
                  }
                  //System.out.printf("%s\n",varm);
                  if(cm.find())
                  {d.insertString(cm.start(),vi,null);
                  }
                  else{// 2b) else look for a word most similar to the variable and insert the 
                  //    variable=value into the line  befor
                  //System.out.printf("2)looking for word most similar to %s\n",variable);
                  varp=v[0]; if(varp.length()==0){varp=v[v.length-1];}
                  while(varp.length()>0&&!cm.find()) {varp=varp.substring(0,varp.length() -1);
                  cm=VariableHelper.getTextMatcher(varp, d.getText(0, d.getLength()));
                   }
                  if(cm.find())
                  {d.insertString(cm.start(),vi,null);
                  }
                  else
                  {varm=v[0]; if(varm.length()==0){varm=v[v.length-1];}
                   while(varm.length()>0&&!cm.find()) {varm=varm.substring(1,varm.length());
                  cm=VariableHelper.getTextMatcher(varm, d.getText(0, d.getLength()));
                   } 
                    if(cm.find())
                  {d.insertString(cm.start(),vi,null);
                  }
                  else  
                   {
                // 3) else put the variable after the last #! line
                    //System.out.printf("3)put %s before last #! line\n",variable);
                    cm=VariableHelper.getTextMatcher("#!", d.getText(0, d.getLength()));
                    if(cm.find()){d.insertString(cm.start(),vi,null);
                    }else
                    {cm=VariableHelper.getTextMatcher("#", d.getText(0, d.getLength()));
                // 4) else put the variable after the last # line
                    //System.out.printf("4)put %s before last # line\n",variable);
                     if(cm.find()){d.insertString(cm.start(),vi,null);
                     }else
                     {
                // 5) else put the variable into the 1st line of the document
                     //System.out.printf("5)put %s into 1st line line\n",variable);
                     d.insertString(1,vi,null);
                     }
                    }
                   }
                  }
                }
            }
              }
            }
        } catch (BadLocationException ex) {
        }
    }

    /**
     * Returns the text associated with the editor.
     * @return the text associated with the editor.
     */
    public String getDocumentText() {
        StyledDocument d = support.getDocument();
        if (d==null){return null;}
        try {d.getLength();
            return d.getText(0, d.getLength());
        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);
            return null;
        }
    }

    /**
     * Is called when the value of a key-value type variable changes.
     * @param variable the name of the variable
     * @param oldValue the old value of the variable
     * @param newValue the new value of the variable
     */
    protected void variableChanged(String variable, String oldValue, String newValue) {
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Components">
    private final BiHashtable<String, Component> variables = new BiHashtable<>();

    /**
     * Updates the content of all registered controls.
     */
    protected void updateControls() {
        //Iterate through all controls
        for (Entry<String, Component> e : variables.entrySet()) {
            //If the component is a text component, set the text from the file
            if (e.getValue() instanceof JCheckBox) {
                //Get the variable value and update component
                ((JCheckBox) e.getValue()).setSelected("1".equals(getVariable(e.getKey())));
            } else if (e.getValue() instanceof JComboBox) {
                ((JComboBox) e.getValue()).setSelectedItem(getVariable(e.getKey()));
            } else if (e.getValue() instanceof JTextComponent) {
                //Get the variable value and set it as the component's text
                ((JTextComponent) e.getValue()).setText(getVariable(e.getKey()));
            } 
            else {
                throw new NotImplementedException("updateControls is not implemented for controls of type " + e.getValue().getClass().toString());
            }
        }
    }

    /**
     * Reacts to a loss of focus of a registered component and updates the
     * associated variable in the text file.
     * @param component the component
     */
    protected void componentValidating(Component component) {
        //Get the associated variable variable
        String variable = variables.getKey(component);
        //Declare the new and old variable values
        String newValue = null;
        String oldValue = getVariable(variable);
        //If the originating component was a text component, get the text and set the variable
        if (component instanceof JTextComponent) {
            //Get an input verifier is possible
            InputVerifier verifier = ((JTextComponent) component).getInputVerifier();
            //If there is one, use it
            if (verifier != null) {
                //If verification fails, quit
                if (!verifier.verify((JComponent) component)) {
                    return;
                }
            }
            //Get the new variable value
            newValue = ((JTextComponent) component).getText();
        } else if (component instanceof JCheckBox) {
            //Get the new variable value
            newValue = ((JCheckBox) component).isSelected() ? "1" : "0";
        } else if (component instanceof JComboBox) {
            newValue = (String) ((JComboBox) component).getSelectedItem();
        } else {
//            throw new NotImplementedException("componentValidating is not implemented for controls of type " + component.getClass().toString());
            throw new NotImplementedException("componentValidating is not implemented for ...");
        }
        //If both values are null, null and an empty string or equal, don't do anything
        if ((newValue == null && oldValue == null) || 
            (newValue != null && oldValue != null && newValue.equals(oldValue)))
        {
            return;
        }
        if ((newValue == null && oldValue.length() == 0) || 
            (oldValue == null && newValue.length() == 0))
        {
            return;
        }
            
        //Set the value
        setVariable(variable, newValue);
        //Notify any potential listener
        variableChanged(variable, oldValue, newValue);
    }

    /**
     * Registers a control with a variable such that they are kept synchronized
     * and will be updated automatically.
     * @param variable the name of the variable
     * @param component the component to register
     */
    public void registerComponent(String variable, Component component) {
        //If the component is a check box
        if (component instanceof JCheckBox) {
            //Register a change listener
            ((JCheckBox) component).addActionListener((ActionEvent e) -> {
                componentValidating((Component) e.getSource());
            });
        } //If the component is an editable JComboBox, get the editor instead
        else if (component instanceof JComboBox) {
          if (((JComboBox) component).isEditable()) {
            //Get the editor component
            component = ((JComboBox) component).getEditor().getEditorComponent();
            //Register a focus lost event listener
            component.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    componentValidating(e.getComponent());
                }
            });
            component.addKeyListener(new KeyListener() {
                @Override
                public void keyReleased(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyTyped(KeyEvent e) {
                    componentValidating(e.getComponent()); }
            });
          }
          else {
            ((JComboBox) component).addItemListener((ItemEvent e) -> {
                // not used for IONTYPE
               
                componentValidating((Component) e.getSource());
            });
          }
        }
        //If the editor is a text component register a focus listener as well
        else if (component instanceof JTextComponent) {
            //Register a focus lost event listener
            component.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent e) {
                }

                @Override
                public void focusLost(FocusEvent e) {
                    componentValidating(e.getComponent());
                }  
            });
            component.addKeyListener(new KeyListener() {
                @Override
                public void keyReleased(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
                @Override
                public void keyTyped(KeyEvent e) {
                    componentValidating(e.getComponent());
                }
            });
        } else {
   //         throw new NotImplementedException("registerComponent not implemented for components of type " + component.getClass().toString());
            throw new NotImplementedException("registerComponent not implemented for components ...  ");
        }
        //Add the component and the associated variable to the hashtable
        variables.put(variable, component);
    }// </editor-fold>
    
    
    
    
    
}
