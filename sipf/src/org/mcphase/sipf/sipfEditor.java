package org.mcphase.sipf;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.regex.Matcher;
import javax.swing.JTable;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import org.mcphase.base.RegexVerifier;
import org.mcphase.base.VariableHelper;
import org.mcphase.base.VisualEditorBase;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.text.DataEditorSupport;
import org.openide.util.Exceptions;
import org.openide.windows.IOColorLines;
import org.openide.windows.OutputWriter;

/**
 * This class represents a visual editor for a single ion parameter file (sipf).
 * @author Till Hoffmann
 */
public class sipfEditor extends VisualEditorBase {
  // <editor-fold defaultstate="collapsed" desc="Table models">
    class WybourneModel extends AbstractTableModel {

        final int rowCount = 13;
        final int columnCount = 5;

        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //If it is the first row, return the list of indices
            if (columnIndex == 0) {
                return String.format("m=%d", rowIndex - 6);
            }
            if (!isCellEditable(rowIndex, columnIndex)) {
                return null;
            }
            //Get the variable text
            return sipfEditor.this.getVariable(getVariableName(rowIndex, columnIndex));
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            //Get the variable text
            sipfEditor.this.setVariable(getVariableName(rowIndex, columnIndex), (String) aValue);
        }

        String getVariableName(int rowIndex, int columnIndex) {
            //Calculate the indices of the L[l,m] from the row and column indices
            int l = (columnIndex - 1) * 2;
            int m = rowIndex - 6;
            //Format
            return String.format("L%d%d%s", l, Math.abs(m), m < 0 ? "S" : "");
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            //The first column is not editable
            if (columnIndex == 0) {
                return false;
            }
            //Calculate the indices of the L[l,m] from the row and column indices
            int l = (columnIndex - 1) * 2;
            int m = rowIndex - 6;
            //The cell is editable if and only if
            return Math.abs(m) <= l;
        }

        @Override
        public String getColumnName(int column) {
            if (column == 0) {
                return "L[l,m]";
            }
            return String.format("l=%d", (column - 1) * 2);
        }
    }
    // <editor-fold defaultstate="collapsed" desc="Table models">
    class StevensModel extends AbstractTableModel {

        final int rowCount = 13;
        final int columnCount = 5;

        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //If it is the first row, return the list of indices
            if (columnIndex == 0) {
                return String.format("m=%d", rowIndex - 6);
            }
            if (!isCellEditable(rowIndex, columnIndex)) {
                return null;
            }
            //Get the variable text
            return sipfEditor.this.getVariable(getVariableName(rowIndex, columnIndex));
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            //Get the variable text
            sipfEditor.this.setVariable(getVariableName(rowIndex, columnIndex), (String) aValue);
        }

        String getVariableName(int rowIndex, int columnIndex) {
            //Calculate the indices of the B[l,m] from the row and column indices
            int l = (columnIndex - 1) * 2;
            int m = rowIndex - 6;
            //Format
            return String.format("B%d%d%s", l, Math.abs(m), m < 0 ? "S" : "");
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            //The first column is not editable
            if (columnIndex == 0) {
                return false;
            }
            //Calculate the indices of the B[l,m] from the row and column indices
            int l = (columnIndex - 1) * 2;
            int m = rowIndex - 6;
            //The cell is editable if and only if
            return Math.abs(m) <= l;
        }

        @Override
        public String getColumnName(int column) {
            if (column == 0) {
                return "B[l,m]";
            }
            return String.format("l=%d", (column - 1) * 2);
        }
    }

    class MagneticFormFactorModel extends AbstractTableModel {

        final int rowCount = 4;
        final int columnCount = 8;
        final char[] columnCharacter = {'A', 'a', 'B', 'b', 'C', 'c', 'D'};

        public ArrayList<String> getVariableNames() {
            //Create a new array
            ArrayList<String> list = new ArrayList<>();
            //Iterate through the rows and then columns
            for (int r = 0; r < rowCount; r++) {
                for (int c = 1; c < columnCount; c++) //Add a variable name
                {
                    list.add(getVariableName(r, c));
                }
            }
            //Return the list
            return list;
        }

        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //Get the index display name
            if (columnIndex == 0) {
                return String.format("i=%d", rowIndex * 2);
            }
            //Get the variable value
            return sipfEditor.this.getVariable(getVariableName(rowIndex, columnIndex));
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            //Set the value
            sipfEditor.this.setVariable(getVariableName(rowIndex, columnIndex), (String) aValue);
        }

        String getVariableName(int rowIndex, int columnIndex) {
            return String.format("FFj%d%c", rowIndex * 2, columnCharacter[columnIndex - 1]);
        }

        @Override
        public String getColumnName(int column) {
            return column == 0 ? "FFj[l,c]" : String.format("c=%c", columnCharacter[column - 1]);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex > 0;
        }
    }

    class ZCoefficientModel extends AbstractTableModel {

        final int rowCount = 4;
        final int columnCount = 5;

        @Override
        public int getRowCount() {
            return rowCount;
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        public ArrayList<String> getVariableNames() {
            //Create a list
            ArrayList<String> list = new ArrayList<>();
            //Iterate through the rows
            for (int r = 0; r < rowCount; r++) {
                for (int c = 1; c < columnCount; c++) //Add if editable
                {
                    if (isCellEditable(r, c)) {
                        list.add(getVariableName(r, c));
                    }
                }
            }
            //Return the list
            return list;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            //Return index
            if (columnIndex == 0) {
                return String.format("K'=%d", 1 + rowIndex * 2);
            }
            //Return nothing if not editable
            if (!isCellEditable(rowIndex, columnIndex)) {
                return null;
            }
            //Return the value
            return sipfEditor.this.getVariable(getVariableName(rowIndex, columnIndex));
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            //Set the value
            sipfEditor.this.setVariable(getVariableName(rowIndex, columnIndex), (String) aValue);
        }

        String getVariableName(int rowIndex, int columnIndex) {
            return String.format("Z%dc%d", 1 + rowIndex * 2, (columnIndex - 1) * 2);
        }

        @Override
        public String getColumnName(int column) {
            return column == 0 ? "Z[K']c[l]" : String.format("l=%d", (column - 1) * 2);
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            //First column is not editable
            if (columnIndex == 0) {
                return false;
            }
            //Convert to K' and l
            int K = 1 + rowIndex * 2;
            int l = (columnIndex - 1) * 2;
            //Return the condition
            return Math.abs(l - K) == 1;
        }
    }

    class RadialWaveFunctionModel extends DefaultTableModel {

        final String[] variables = {"N%d", "XI%d", "C%d"};
        final int columnCount = 4;
        final String[] columns = {"", "N[p]", "XI[p]", "C[p]"};

        public int getParameterCount() {
            //Get the document text
            String text = sipfEditor.this.getDocumentText();
            //Get a matcher for the pattern for the different variables
 //           Matcher m = VariableHelper.getMatcher("^(?:\\#!|[^\\#])*?(?:N|C|XI)(\\d+)\\s*=\\s*[^\\s]*", text);
            Matcher m = VariableHelper.getMatcher("^(?:\\#!|[^\\#])?(?:N|C|XI)(\\d++)\\s*+=\\s*+[^\\s]*+", text);
            //Get a running variable
            int count = 0;
            //Do all matches
            while (m.find()) {
                //Get the current index
                int index = Integer.valueOf(m.group(1));
                //Compare and set if necessary
                if (index > count) {
                    count = index;
                }
            }
            //Return the count
            return count;
        }

        public void updateRowCount() {
            this.setRowCount(getParameterCount());
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            //All cells are editable except for the first column
            return column != 0;
        }

        @Override
        public Object getValueAt(int row, int column) {
            //If it's the first column, return the running parameter
            if (column == 0) {
                return String.format("p=%d", row + 1);
            }
            //Otherwise return one of the variables
            return sipfEditor.this.getVariable(String.format(variables[column - 1], row + 1));
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            //Set the variable
            sipfEditor.this.setVariable(String.format(variables[column - 1], row + 1), aValue.toString());
        }

        @Override
        public int getColumnCount() {
            return columnCount;
        }

        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }
    // </editor-fold>

    /**
     * This class represents a custom cell renderer which shades non-editable
     * cells gray.
     */
    class CellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (!table.isCellEditable(row, column)) {
                c.setBackground(Color.lightGray);
                c.setForeground(Color.black);
            } else {
                if (table.isCellSelected(row, column)) {
                    c.setBackground(UIManager.getColor("Table.selectionBackground"));
                } else {
                    c.setBackground(UIManager.getColor("Table.background"));
                }
            }
            return c;
        }
    }
    private static Hashtable<String, FileObject> ionCache = null;

    private void outputColorPrint(String text, Color color) {
        try {
            IOColorLines.println(getInputOutput(), text, color);
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    /** Creates a new instance of sipfEditor. */
    public sipfEditor(DataEditorSupport support) {
        super(support, "sipf Editor");
        initComponents();

        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(5000);

        //Set the table model
        jTableWybourne.setModel(new WybourneModel());
        jTableStevens.setModel(new StevensModel());
        jTableMagneticFormFactors.setModel(new MagneticFormFactorModel());
        jTableZCoefficients.setModel(new ZCoefficientModel());
        //Description: R(r)=\sum_p C_p \cdot r^{N_p-1} \cdot \exp(-XI_p\cdot r) \cdot (2 XI_p)^{N_p+.5}/\sqrt{2 N_p!}
        jTableRadialWaveFunction.setModel(new RadialWaveFunctionModel());
        //Set the default renderer
        jTableWybourne.setDefaultRenderer(Object.class, new CellRenderer());
        jTableStevens.setDefaultRenderer(Object.class, new CellRenderer());
        jTableMagneticFormFactors.setDefaultRenderer(Object.class, new CellRenderer());
        jTableZCoefficients.setDefaultRenderer(Object.class, new CellRenderer());
        jTableRadialWaveFunction.setDefaultRenderer(Object.class, new CellRenderer());

        // <editor-fold defaultstate="collapsed" desc="Ion type">
        //Load all the ions from file
        if (ionCache == null) {
            ionCache = new Hashtable<>();
            String mcphaseDir = System.getenv("MCPHASE_DIR");
            String ds = System.getProperty("file.separator");
            String relDir = ds + "bin" + ds + "mcphaseexplorer" + ds + "ions";
            File file;
            if (mcphaseDir != null) { file = new File(mcphaseDir+relDir); }
            else { file = FileUtil.normalizeFile(new File("ions")); }
            FileObject ionfolder = FileUtil.toFileObject(file);
            if (ionfolder != null) {
                //Get all children
                FileObject[] ions = ionfolder.getChildren();
                //Iterate through all ions and add them to the list of possible ions
                for (FileObject f : ions) {
                    //Get the file object
                    //Check whether the extension is correct
                    if (!f.getExt().equalsIgnoreCase("sipf")) {
                        continue;
                    }
                    //Get the proper name and add it to the hash table
                    String ionName = f.getName().replaceAll("p$", "+");
                    ionCache.put(ionName, f);
                }
                //Notify
                getInputOutput().getOut().printf("Loaded %d ions from %s.\n\n", ionCache.size(),
                        file.toString());
            } else {
                outputColorPrint(String.format("Could not load ions from %s.\n",
                        file.toString()), outputRed);
            }
        }
        //Add the items in alphabetical order
        Object[] ionNames = ionCache.keySet().toArray();
        Arrays.sort(ionNames);
        for (Object s : ionNames) {
            jComboBoxFrom.addItem(s);
            jComboBoxTo.addItem(s);
            jComboBoxFrom1.addItem(s);
            jComboBoxTo1.addItem(s);
            jComboBoxIONTYPE.addItem(s);
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Component registration">
        //Register all the components
        registerComponent("SCATTERINGLENGTHREAL", jTextFieldSCATTERINGLENGTHREAL);
        registerComponent("SCATTERINGLENGTHIMAG", jTextFieldSCATTERINGLENGTHIMAG);

        registerComponent("ALPHA", jTextFieldALPHA);
        registerComponent("BETA", jTextFieldBETA);
        registerComponent("GAMMA", jTextFieldGAMMA);

        registerComponent("IONTYPE", jComboBoxIONTYPE);
        registerComponent("MODULE", jComboBoxMODULE);

        registerComponent("R2", jTextFieldR2);
        registerComponent("R4", jTextFieldR4);
        registerComponent("R6", jTextFieldR6);

        registerComponent("J", jTextFieldJ);

        registerComponent("A", jTextFieldA);
        registerComponent("B", jTextFieldB);
        registerComponent("C", jTextFieldC);

        registerComponent("DWF", jTextFieldDWF);

        registerComponent("GJ", jTextFieldGJ);

        registerComponent("CHARGE", jTextFieldCharge);
        registerComponent("MODPAR1", jTextFieldMass);
        registerComponent("nof_electrons", jTextFieldnof_electrons);
        registerComponent("conf", jTextFieldconf);
        
        registerComponent("F2", jTextFieldF2);
        registerComponent("F4", jTextFieldF4);
        registerComponent("F6", jTextFieldF6);
        registerComponent("zeta", jTextFieldSO);
        registerComponent("units", jComboBoxUnit);
        registerComponent("basis", jComboBoxBasis);
        
        //registerComponent("TEMP", jTextFieldTEMP);
        //registerComponent("Bx", jTextFieldBx);
        //registerComponent("By", jTextFieldBy);
        //registerComponent("Bz", jTextFieldBz);
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Verifiers">
        jTextFieldSCATTERINGLENGTHREAL.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldSCATTERINGLENGTHIMAG.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldALPHA.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldBETA.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldGAMMA.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldR2.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldR4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldR6.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldJ.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldA.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldB.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldC.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldDWF.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldGJ.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldCharge.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldMass.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldnof_electrons.setInputVerifier(RegexVerifier.getScientificVerifier());
        //jTextFieldTEMP.setInputVerifier(RegexVerifier.getScientificVerifier());
        //jTextFieldBx.setInputVerifier(RegexVerifier.getScientificVerifier());
        //jTextFieldBy.setInputVerifier(RegexVerifier.getScientificVerifier());
        //jTextFieldBz.setInputVerifier(RegexVerifier.getScientificVerifier());
        
        jTextFieldF2.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldF4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldF6.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldSO.setInputVerifier(RegexVerifier.getScientificVerifier());
   
        // </editor-fold>
    }

    @Override
    public void componentShowing() {
        //Update all the other controls
        super.componentShowing();
        //Get the number of rows in the radial wave function table
        ((RadialWaveFunctionModel) jTableRadialWaveFunction.getModel()).updateRowCount();
        //Update panel visibility
        updatePanelVisibility(getVariable("MODULE"));
    }

    @Override
    protected void variableChanged(String variable, String oldValue, String newValue) {
        if ("MODULE".equals(variable)) {
            updatePanelVisibility(newValue);
        } else if ("IONTYPE".equals(variable)) {
            //Set the combo boxes according to what has been selected
            jComboBoxFrom.setSelectedItem(oldValue);
            jComboBoxTo.setSelectedItem(newValue);
            jComboBoxFrom1.setSelectedItem(oldValue);
            jComboBoxTo1.setSelectedItem(newValue);
            jComboBoxIONTYPE.setSelectedItem(newValue);
            //Update the enabled status of the combobox
            jComboBoxConvertActionPerformed(null);
        }
    }

    /**
     * Updates the visibility of various panels grouping variables.
     * @param module the current module
     */
    private void updatePanelVisibility(String module) {
        //Hide everything
        jPanelJ.setVisible(false);
        jPanelABC.setVisible(false);
        jPanelConfiguration.setVisible(false);
        //jPanelTBxByBz.setVisible(false);
        jPanelWybourne.setVisible(false);
        jPanelStevens.setVisible(false);
        jPanelStevensFactors.setVisible(false);
        jPanelCoulomb.setVisible(false);
        jPanelSpinOrbit.setVisible(false);
        jPanelUnit.setVisible(false);
        jPanelBasis.setVisible(false);
        jPanelMass.setVisible(false);
        if (null == module) {
            //Show everything if an external module is selected
            jPanelJ.setVisible(true);
            jPanelABC.setVisible(true);
            jPanelConfiguration.setVisible(true);
            //jPanelTBxByBz.setVisible(true);
            jPanelWybourne.setVisible(true);
            jPanelStevens.setVisible(true);
            jPanelStevensFactors.setVisible(true);
            jPanelCoulomb.setVisible(true);
            jPanelSpinOrbit.setVisible(true);
            jPanelUnit.setVisible(true);
            jPanelBasis.setVisible(true);
        } else //Show for different modules
        switch (module) {
            case "brillouin":
                jPanelJ.setVisible(true);
                break;
            case "kramer":
                jPanelABC.setVisible(true);
                break;
            case "cfield":
            case "so1ion":
                jPanelWybourne.setVisible(true);
                jPanelStevens.setVisible(true);
                //jPanelTBxByBz.setVisible(true);
                jPanelWybourne.setBorder(javax.swing.BorderFactory.createTitledBorder("Wybourne normalised Crystal Field Pars. Llm [meV]"));
                jPanelStevens.setBorder(javax.swing.BorderFactory.createTitledBorder("Crystal Field Pars. Blm [meV] (Stevens)"));
                jPanelStevensFactors.setVisible(true);
                break;
            case "ic1ion":
            case "icf1ion":
                if ("ic1ion".equals(module)) jPanelCoulomb.setVisible(true);
                jPanelSpinOrbit.setVisible(true);
                jPanelConfiguration.setVisible(true);
                jPanelUnit.setVisible(true);
                jPanelBasis.setVisible(true);
                jPanelWybourne.setVisible(true);
                jPanelStevens.setVisible(true);
                jPanelStevensFactors.setVisible(true);
                jPanelWybourne.setBorder(javax.swing.BorderFactory.createTitledBorder("Wybourne normalised Crystal Field Pars. Llm [meV]"));
                jPanelStevens.setBorder(javax.swing.BorderFactory.createTitledBorder("Crystal Field Pars. Blm [meV] (Stevens)"));
                break;
            case "phonon":
                jLabelCharge1.setVisible(false);
                jTextFieldnof_electrons.setVisible(false);
                jPanelLandeFactor.setVisible(false);
                jPanelRadialMatrixElements.setVisible(false);
                jPanelRadialWaveFunction.setVisible(false);
                jPanelMagneticFormFactors.setVisible(false);
                jLabelCharge1.setVisible(false);
                jPanelZCoefficients.setVisible(false);
                jPanelMass.setVisible(true);
                break;
            default:
                //Show everything if an external module is selected
                jPanelJ.setVisible(true);
                jPanelABC.setVisible(true);
                jPanelConfiguration.setVisible(true);
                //jPanelTBxByBz.setVisible(true);
                jPanelWybourne.setVisible(true);
                jPanelStevens.setVisible(true);
                jPanelStevensFactors.setVisible(true);
                jPanelCoulomb.setVisible(true);
                jPanelSpinOrbit.setVisible(true);
                jPanelUnit.setVisible(true);
                jPanelBasis.setVisible(true);
                 jPanelMass.setVisible(true);
                break;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();
        jPanelModule = new javax.swing.JPanel();
        jComboBoxMODULE = new javax.swing.JComboBox<>();
        jPanelIonType = new javax.swing.JPanel();
        jComboBoxIONTYPE = new javax.swing.JComboBox<>();
        jButtonUpdateConstants = new javax.swing.JButton();
        jLabelCharge = new javax.swing.JLabel();
        jTextFieldCharge = new javax.swing.JTextField();
        jLabelCharge1 = new javax.swing.JLabel();
        jTextFieldnof_electrons = new javax.swing.JTextField();
        jPanelABC = new javax.swing.JPanel();
        jLabelA = new javax.swing.JLabel();
        jLabelB = new javax.swing.JLabel();
        jLabelC = new javax.swing.JLabel();
        jTextFieldA = new javax.swing.JTextField();
        jTextFieldB = new javax.swing.JTextField();
        jTextFieldC = new javax.swing.JTextField();
        jPanelJ = new javax.swing.JPanel();
        jTextFieldJ = new javax.swing.JTextField();
        jPanelLandeFactor = new javax.swing.JPanel();
        jTextFieldGJ = new javax.swing.JTextField();
        jPanelCoulomb = new javax.swing.JPanel();
        jLabelF2 = new javax.swing.JLabel();
        jTextFieldF2 = new javax.swing.JTextField();
        jLabelF4 = new javax.swing.JLabel();
        jTextFieldF4 = new javax.swing.JTextField();
        jLabelF6 = new javax.swing.JLabel();
        jTextFieldF6 = new javax.swing.JTextField();
        jPanelSpinOrbit = new javax.swing.JPanel();
        jTextFieldSO = new javax.swing.JTextField();
        jPanelUnit = new javax.swing.JPanel();
        jComboBoxUnit = new javax.swing.JComboBox<>();
        jPanelWybourne = new javax.swing.JPanel();
        jPanelConvert = new javax.swing.JPanel();
        jLabelConvertFrom = new javax.swing.JLabel();
        jComboBoxFrom = new javax.swing.JComboBox<>();
        jLabelTo = new javax.swing.JLabel();
        jComboBoxTo = new javax.swing.JComboBox<>();
        jButtonConvert = new javax.swing.JButton();
        jScrollPaneWybourne = new javax.swing.JScrollPane();
        jTableWybourne = new javax.swing.JTable();
        jPanelStevensFactors = new javax.swing.JPanel();
        jLabelAlpha = new javax.swing.JLabel();
        JLabelBeta = new javax.swing.JLabel();
        jLabelGamma = new javax.swing.JLabel();
        jTextFieldALPHA = new javax.swing.JTextField();
        jTextFieldBETA = new javax.swing.JTextField();
        jTextFieldGAMMA = new javax.swing.JTextField();
        jPanelRadialMatrixElements = new javax.swing.JPanel();
        jLabelR2 = new javax.swing.JLabel();
        jLabelR4 = new javax.swing.JLabel();
        jLabelR6 = new javax.swing.JLabel();
        jTextFieldR2 = new javax.swing.JTextField();
        jTextFieldR4 = new javax.swing.JTextField();
        jTextFieldR6 = new javax.swing.JTextField();
        jPanelRadialWaveFunction = new javax.swing.JPanel();
        jPanelAddRemove = new javax.swing.JPanel();
        jButtonAddRow = new javax.swing.JButton();
        jButtonRemoveRow = new javax.swing.JButton();
        jScrollPaneRadialWaveFunction = new javax.swing.JScrollPane();
        jTableRadialWaveFunction = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanelDebyeWallerFactor = new javax.swing.JPanel();
        jTextFieldDWF = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanelScatteringLength = new javax.swing.JPanel();
        jLabelScatteringLengthReal = new javax.swing.JLabel();
        jLabelScatteringLengthImag = new javax.swing.JLabel();
        jTextFieldSCATTERINGLENGTHREAL = new javax.swing.JTextField();
        jTextFieldSCATTERINGLENGTHIMAG = new javax.swing.JTextField();
        jPanelMagneticFormFactors = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPaneMagneticFormFactors = new javax.swing.JScrollPane();
        jTableMagneticFormFactors = new javax.swing.JTable();
        jPanelZCoefficients = new javax.swing.JPanel();
        jScrollPaneZCoefficients = new javax.swing.JScrollPane();
        jTableZCoefficients = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanelConfiguration = new javax.swing.JPanel();
        jTextFieldconf = new javax.swing.JTextField();
        jLabelCharge2 = new javax.swing.JLabel();
        jPanelBasis = new javax.swing.JPanel();
        jComboBoxBasis = new javax.swing.JComboBox<>();
        jPanelStevens = new javax.swing.JPanel();
        jPanelConvert1 = new javax.swing.JPanel();
        jLabelConvertFrom1 = new javax.swing.JLabel();
        jComboBoxFrom1 = new javax.swing.JComboBox<>();
        jLabelTo1 = new javax.swing.JLabel();
        jComboBoxTo1 = new javax.swing.JComboBox<>();
        jButtonConvert1 = new javax.swing.JButton();
        jScrollPaneStevens = new javax.swing.JScrollPane();
        jTableStevens = new javax.swing.JTable();
        jPanelMass = new javax.swing.JPanel();
        jTextFieldMass = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1034, 800));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane.setPreferredSize(new java.awt.Dimension(1034, 1012));

        jPanel.setMinimumSize(new java.awt.Dimension(1032, 1000));
        jPanel.setPreferredSize(new java.awt.Dimension(1032, 1110));

        jPanelModule.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelModule.border.title"))); // NOI18N
        jPanelModule.setAlignmentX(0.0F);

        jComboBoxMODULE.setEditable(true);
        jComboBoxMODULE.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "cfield", "so1ion", "brillouin", "kramer", "ic1ion", "icf1ion", "phonon" }));
        jComboBoxMODULE.setToolTipText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jComboBoxMODULE.toolTipText")); // NOI18N
        jComboBoxMODULE.setAlignmentX(0.0F);
        jComboBoxMODULE.setMaximumSize(new java.awt.Dimension(32767, 27));
        jComboBoxMODULE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMODULEActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanelModuleLayout = new org.jdesktop.layout.GroupLayout(jPanelModule);
        jPanelModule.setLayout(jPanelModuleLayout);
        jPanelModuleLayout.setHorizontalGroup(
            jPanelModuleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelModuleLayout.createSequentialGroup()
                .add(jComboBoxMODULE, 0, 86, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelModuleLayout.setVerticalGroup(
            jPanelModuleLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelModuleLayout.createSequentialGroup()
                .add(jComboBoxMODULE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jComboBoxMODULE.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jComboBoxMODULE.AccessibleContext.accessibleName")); // NOI18N

        jPanelIonType.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelIonType.border.title"))); // NOI18N
        jPanelIonType.setAlignmentX(0.0F);
        jPanelIonType.setMinimumSize(new java.awt.Dimension(822, 67));
        jPanelIonType.setPreferredSize(new java.awt.Dimension(822, 67));

        jComboBoxIONTYPE.setEditable(true);
        jComboBoxIONTYPE.setAlignmentX(0.0F);
        jComboBoxIONTYPE.setMaximumSize(new java.awt.Dimension(32767, 27));
        jComboBoxIONTYPE.setPreferredSize(new java.awt.Dimension(50, 22));
        jComboBoxIONTYPE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIONTYPEActionPerformed(evt);
            }
        });

        jButtonUpdateConstants.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jButtonUpdateConstants.text")); // NOI18N
        jButtonUpdateConstants.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateConstantsActionPerformed(evt);
            }
        });

        jLabelCharge.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelCharge.text")); // NOI18N

        jTextFieldCharge.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldCharge.text")); // NOI18N
        jTextFieldCharge.setToolTipText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldCharge.toolTipText")); // NOI18N
        jTextFieldCharge.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jTextFieldCharge.setMinimumSize(new java.awt.Dimension(60, 22));

        jLabelCharge1.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelCharge1.text")); // NOI18N

        jTextFieldnof_electrons.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldnof_electrons.text")); // NOI18N
        jTextFieldnof_electrons.setToolTipText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldnof_electrons.toolTipText")); // NOI18N
        jTextFieldnof_electrons.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldnof_electronsActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanelIonTypeLayout = new org.jdesktop.layout.GroupLayout(jPanelIonType);
        jPanelIonType.setLayout(jPanelIonTypeLayout);
        jPanelIonTypeLayout.setHorizontalGroup(
            jPanelIonTypeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelIonTypeLayout.createSequentialGroup()
                .add(jComboBoxIONTYPE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButtonUpdateConstants, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 142, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelCharge)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTextFieldCharge, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 55, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabelCharge1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTextFieldnof_electrons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(4, 4, 4))
        );
        jPanelIonTypeLayout.setVerticalGroup(
            jPanelIonTypeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelIonTypeLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jComboBoxIONTYPE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jButtonUpdateConstants)
                .add(jLabelCharge, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jTextFieldCharge, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jLabelCharge1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jTextFieldnof_electrons, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jComboBoxIONTYPE.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jComboBoxIONTYPE.AccessibleContext.accessibleName")); // NOI18N

        jPanelABC.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelABC.border.title"))); // NOI18N
        jPanelABC.setAlignmentX(0.0F);

        jLabelA.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelA.text")); // NOI18N

        jLabelB.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelB.text")); // NOI18N

        jLabelC.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelC.text")); // NOI18N

        jTextFieldA.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldA.text")); // NOI18N

        jTextFieldB.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldB.text")); // NOI18N

        jTextFieldC.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldC.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelABCLayout = new org.jdesktop.layout.GroupLayout(jPanelABC);
        jPanelABC.setLayout(jPanelABCLayout);
        jPanelABCLayout.setHorizontalGroup(
            jPanelABCLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelABCLayout.createSequentialGroup()
                .add(jPanelABCLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanelABCLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(jTextFieldB, 0, 1, Short.MAX_VALUE)
                    .add(jLabelB, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .add(27, 27, 27)
                .add(jPanelABCLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );
        jPanelABCLayout.setVerticalGroup(
            jPanelABCLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelABCLayout.createSequentialGroup()
                .add(jPanelABCLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelABCLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldA, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jTextFieldB, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelJ.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelJ.border.title"))); // NOI18N
        jPanelJ.setAlignmentX(0.0F);

        jTextFieldJ.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldJ.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelJLayout = new org.jdesktop.layout.GroupLayout(jPanelJ);
        jPanelJ.setLayout(jPanelJLayout);
        jPanelJLayout.setHorizontalGroup(
            jPanelJLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelJLayout.createSequentialGroup()
                .add(jTextFieldJ, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 49, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanelJLayout.setVerticalGroup(
            jPanelJLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTextFieldJ, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jPanelLandeFactor.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelLandeFactor.border.title"))); // NOI18N
        jPanelLandeFactor.setAlignmentX(0.0F);

        jTextFieldGJ.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldGJ.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelLandeFactorLayout = new org.jdesktop.layout.GroupLayout(jPanelLandeFactor);
        jPanelLandeFactor.setLayout(jPanelLandeFactorLayout);
        jPanelLandeFactorLayout.setHorizontalGroup(
            jPanelLandeFactorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelLandeFactorLayout.createSequentialGroup()
                .add(jTextFieldGJ, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 80, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanelLandeFactorLayout.setVerticalGroup(
            jPanelLandeFactorLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTextFieldGJ, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jPanelCoulomb.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelCoulomb.border.title"))); // NOI18N
        jPanelCoulomb.setAlignmentX(0.0F);

        jLabelF2.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelF2.text")); // NOI18N

        jTextFieldF2.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldF2.text")); // NOI18N

        jLabelF4.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelF4.text")); // NOI18N

        jTextFieldF4.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldF4.text")); // NOI18N

        jLabelF6.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelF6.text")); // NOI18N

        jTextFieldF6.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldF6.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelCoulombLayout = new org.jdesktop.layout.GroupLayout(jPanelCoulomb);
        jPanelCoulomb.setLayout(jPanelCoulombLayout);
        jPanelCoulombLayout.setHorizontalGroup(
            jPanelCoulombLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelCoulombLayout.createSequentialGroup()
                .add(jLabelF2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextFieldF2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 87, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jLabelF4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextFieldF4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 92, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelF6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextFieldF6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelCoulombLayout.setVerticalGroup(
            jPanelCoulombLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelCoulombLayout.createSequentialGroup()
                .add(jPanelCoulombLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelCoulombLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jTextFieldF2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabelF4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabelF2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanelCoulombLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jTextFieldF4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jTextFieldF6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabelF6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelSpinOrbit.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelSpinOrbit.border.title"))); // NOI18N
        jPanelSpinOrbit.setAlignmentX(0.0F);

        jTextFieldSO.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldSO.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelSpinOrbitLayout = new org.jdesktop.layout.GroupLayout(jPanelSpinOrbit);
        jPanelSpinOrbit.setLayout(jPanelSpinOrbitLayout);
        jPanelSpinOrbitLayout.setHorizontalGroup(
            jPanelSpinOrbitLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelSpinOrbitLayout.createSequentialGroup()
                .add(jTextFieldSO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanelSpinOrbitLayout.setVerticalGroup(
            jPanelSpinOrbitLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTextFieldSO, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jPanelUnit.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelUnit.border.title"))); // NOI18N
        jPanelUnit.setAlignmentX(0.0F);
        jPanelUnit.setLayout(new java.awt.BorderLayout());

        jComboBoxUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "meV", "K", "cm^-1" }));
        jComboBoxUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUnitActionPerformed(evt);
            }
        });
        jPanelUnit.add(jComboBoxUnit, java.awt.BorderLayout.PAGE_START);

        jPanelWybourne.setBackground(new java.awt.Color(153, 255, 153));
        jPanelWybourne.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelWybourne.border.title"))); // NOI18N
        jPanelWybourne.setAlignmentX(0.0F);
        jPanelWybourne.setMinimumSize(new java.awt.Dimension(35, 170));

        jPanelConvert.setBackground(new java.awt.Color(153, 255, 153));

        jLabelConvertFrom.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelConvertFrom.text")); // NOI18N

        jComboBoxFrom.setEditable(true);
        jComboBoxFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConvertActionPerformed(evt);
            }
        });

        jLabelTo.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelTo.text")); // NOI18N

        jComboBoxTo.setEditable(true);
        jComboBoxTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConvertActionPerformed(evt);
            }
        });

        jButtonConvert.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jButtonConvert.text")); // NOI18N
        jButtonConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertActionPerformed(evt);
            }
        });

        jScrollPaneWybourne.setPreferredSize(new java.awt.Dimension(454, 250));

        jTableWybourne.setGridColor(java.awt.Color.lightGray);
        jScrollPaneWybourne.setViewportView(jTableWybourne);

        org.jdesktop.layout.GroupLayout jPanelConvertLayout = new org.jdesktop.layout.GroupLayout(jPanelConvert);
        jPanelConvert.setLayout(jPanelConvertLayout);
        jPanelConvertLayout.setHorizontalGroup(
            jPanelConvertLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConvertLayout.createSequentialGroup()
                .add(jPanelConvertLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPaneWybourne, 0, 0, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanelConvertLayout.createSequentialGroup()
                        .add(jLabelConvertFrom)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBoxFrom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelTo)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBoxTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonConvert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelConvertLayout.setVerticalGroup(
            jPanelConvertLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConvertLayout.createSequentialGroup()
                .add(jPanelConvertLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelConvertLayout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(jLabelConvertFrom))
                    .add(jPanelConvertLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jComboBoxFrom, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabelTo)
                        .add(jComboBoxTo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButtonConvert)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPaneWybourne, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBoxFrom.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jComboBoxFrom.AccessibleContext.accessibleName")); // NOI18N
        jComboBoxFrom.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jComboBoxFrom.AccessibleContext.accessibleDescription")); // NOI18N
        jComboBoxTo.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jComboBoxTo.AccessibleContext.accessibleName")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelWybourneLayout = new org.jdesktop.layout.GroupLayout(jPanelWybourne);
        jPanelWybourne.setLayout(jPanelWybourneLayout);
        jPanelWybourneLayout.setHorizontalGroup(
            jPanelWybourneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelWybourneLayout.createSequentialGroup()
                .add(jPanelConvert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelWybourneLayout.setVerticalGroup(
            jPanelWybourneLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConvert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jPanelStevensFactors.setBackground(new java.awt.Color(153, 255, 153));
        jPanelStevensFactors.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelStevensFactors.border.title"))); // NOI18N
        jPanelStevensFactors.setAlignmentX(0.0F);
        jPanelStevensFactors.setLayout(new java.awt.GridLayout(2, 3));

        jLabelAlpha.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelAlpha.text")); // NOI18N
        jPanelStevensFactors.add(jLabelAlpha);

        JLabelBeta.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.JLabelBeta.text")); // NOI18N
        jPanelStevensFactors.add(JLabelBeta);

        jLabelGamma.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelGamma.text")); // NOI18N
        jPanelStevensFactors.add(jLabelGamma);
        jPanelStevensFactors.add(jTextFieldALPHA);
        jTextFieldALPHA.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldALPHA.AccessibleContext.accessibleName")); // NOI18N

        jPanelStevensFactors.add(jTextFieldBETA);
        jTextFieldBETA.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldBETA.AccessibleContext.accessibleName")); // NOI18N

        jPanelStevensFactors.add(jTextFieldGAMMA);
        jTextFieldGAMMA.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldGAMMA.AccessibleContext.accessibleName")); // NOI18N

        jPanelRadialMatrixElements.setBackground(new java.awt.Color(153, 255, 153));
        jPanelRadialMatrixElements.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelRadialMatrixElements.border.title"))); // NOI18N
        jPanelRadialMatrixElements.setAlignmentX(0.0F);
        jPanelRadialMatrixElements.setLayout(new java.awt.GridLayout(2, 3));

        jLabelR2.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelR2.text")); // NOI18N
        jPanelRadialMatrixElements.add(jLabelR2);

        jLabelR4.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelR4.text")); // NOI18N
        jPanelRadialMatrixElements.add(jLabelR4);

        jLabelR6.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelR6.text")); // NOI18N
        jPanelRadialMatrixElements.add(jLabelR6);

        jTextFieldR2.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldR2.text")); // NOI18N
        jPanelRadialMatrixElements.add(jTextFieldR2);

        jTextFieldR4.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldR4.text")); // NOI18N
        jPanelRadialMatrixElements.add(jTextFieldR4);

        jTextFieldR6.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldR6.text")); // NOI18N
        jPanelRadialMatrixElements.add(jTextFieldR6);

        jPanelRadialWaveFunction.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelRadialWaveFunction.border.title"))); // NOI18N
        jPanelRadialWaveFunction.setAlignmentX(0.0F);
        jPanelRadialWaveFunction.setLayout(new java.awt.BorderLayout());

        jPanelAddRemove.setLayout(new java.awt.GridLayout(1, 2));

        jButtonAddRow.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jButtonAddRow.text")); // NOI18N
        jButtonAddRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddRowActionPerformed(evt);
            }
        });
        jPanelAddRemove.add(jButtonAddRow);

        jButtonRemoveRow.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jButtonRemoveRow.text")); // NOI18N
        jButtonRemoveRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveRowActionPerformed(evt);
            }
        });
        jPanelAddRemove.add(jButtonRemoveRow);

        jPanelRadialWaveFunction.add(jPanelAddRemove, java.awt.BorderLayout.PAGE_END);

        jScrollPaneRadialWaveFunction.setPreferredSize(new java.awt.Dimension(454, 120));

        jTableRadialWaveFunction.setGridColor(new java.awt.Color(204, 204, 204));
        jScrollPaneRadialWaveFunction.setViewportView(jTableRadialWaveFunction);

        jPanelRadialWaveFunction.add(jScrollPaneRadialWaveFunction, java.awt.BorderLayout.CENTER);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/mcphase/sipf/RadialWaveFunctionParameters.gif"))); // NOI18N
        jLabel1.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabel1.text")); // NOI18N
        jLabel1.setAlignmentX(0.5F);
        jPanel1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jPanelRadialWaveFunction.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanelDebyeWallerFactor.setBackground(new java.awt.Color(255, 255, 153));
        jPanelDebyeWallerFactor.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelDebyeWallerFactor.border.title"))); // NOI18N
        jPanelDebyeWallerFactor.setAlignmentX(0.0F);
        jPanelDebyeWallerFactor.setLayout(new java.awt.BorderLayout());

        jTextFieldDWF.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldDWF.text")); // NOI18N
        jPanelDebyeWallerFactor.add(jTextFieldDWF, java.awt.BorderLayout.CENTER);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/mcphase/sipf/dwf.gif"))); // NOI18N
        jLabel4.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabel4.text")); // NOI18N
        jLabel4.setAlignmentX(0.5F);
        jPanel4.add(jLabel4, java.awt.BorderLayout.CENTER);

        jPanelDebyeWallerFactor.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanelScatteringLength.setBackground(new java.awt.Color(255, 255, 153));
        jPanelScatteringLength.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelScatteringLength.border.title"))); // NOI18N
        jPanelScatteringLength.setAlignmentX(0.0F);
        jPanelScatteringLength.setLayout(new java.awt.GridLayout(2, 2));

        jLabelScatteringLengthReal.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelScatteringLengthReal.text")); // NOI18N
        jPanelScatteringLength.add(jLabelScatteringLengthReal);

        jLabelScatteringLengthImag.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelScatteringLengthImag.text")); // NOI18N
        jPanelScatteringLength.add(jLabelScatteringLengthImag);
        jPanelScatteringLength.add(jTextFieldSCATTERINGLENGTHREAL);
        jTextFieldSCATTERINGLENGTHREAL.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldSCATTERINGLENGTHREAL.AccessibleContext.accessibleName")); // NOI18N

        jPanelScatteringLength.add(jTextFieldSCATTERINGLENGTHIMAG);
        jTextFieldSCATTERINGLENGTHIMAG.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldSCATTERINGLENGTHIMAG.AccessibleContext.accessibleName")); // NOI18N

        jPanelMagneticFormFactors.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelMagneticFormFactors.border.title"))); // NOI18N
        jPanelMagneticFormFactors.setAlignmentX(0.0F);
        jPanelMagneticFormFactors.setMinimumSize(new java.awt.Dimension(35, 120));
        jPanelMagneticFormFactors.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/mcphase/sipf/NeutronMagneticFormFactorCoeff.gif"))); // NOI18N
        jLabel2.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabel2.text")); // NOI18N
        jPanel2.add(jLabel2, java.awt.BorderLayout.CENTER);

        jScrollPaneMagneticFormFactors.setPreferredSize(new java.awt.Dimension(454, 100));

        jTableMagneticFormFactors.setGridColor(java.awt.Color.lightGray);
        jScrollPaneMagneticFormFactors.setViewportView(jTableMagneticFormFactors);

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(jScrollPaneMagneticFormFactors, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 469, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPaneMagneticFormFactors, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jPanelMagneticFormFactors.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanelZCoefficients.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelZCoefficients.border.title"))); // NOI18N
        jPanelZCoefficients.setAlignmentX(0.0F);
        jPanelZCoefficients.setMinimumSize(new java.awt.Dimension(35, 120));
        jPanelZCoefficients.setLayout(new java.awt.BorderLayout());

        jScrollPaneZCoefficients.setPreferredSize(new java.awt.Dimension(454, 100));

        jTableZCoefficients.setGridColor(java.awt.Color.lightGray);
        jScrollPaneZCoefficients.setViewportView(jTableZCoefficients);

        jPanelZCoefficients.add(jScrollPaneZCoefficients, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/mcphase/sipf/ZofK.gif"))); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabel3.text")); // NOI18N
        jPanel3.add(jLabel3, java.awt.BorderLayout.CENTER);

        jPanelZCoefficients.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jTextFieldconf.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldconf.text")); // NOI18N

        jLabelCharge2.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelCharge2.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelConfigurationLayout = new org.jdesktop.layout.GroupLayout(jPanelConfiguration);
        jPanelConfiguration.setLayout(jPanelConfigurationLayout);
        jPanelConfigurationLayout.setHorizontalGroup(
            jPanelConfigurationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelCharge2)
                .add(18, 18, 18)
                .add(jTextFieldconf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 58, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanelConfigurationLayout.setVerticalGroup(
            jPanelConfigurationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConfigurationLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                .add(jLabelCharge2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 22, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jTextFieldconf, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanelBasis.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelBasis.border.title"))); // NOI18N
        jPanelBasis.setAlignmentX(0.0F);
        jPanelBasis.setLayout(new java.awt.BorderLayout());

        jComboBoxBasis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "mSmL", "JmJ" }));
        jComboBoxBasis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBasisActionPerformed(evt);
            }
        });
        jPanelBasis.add(jComboBoxBasis, java.awt.BorderLayout.PAGE_START);

        jPanelStevens.setBackground(new java.awt.Color(153, 255, 153));
        jPanelStevens.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelStevens.border.title"))); // NOI18N
        jPanelStevens.setAlignmentX(0.0F);
        jPanelStevens.setMinimumSize(new java.awt.Dimension(35, 170));

        jPanelConvert1.setBackground(new java.awt.Color(153, 255, 153));

        jLabelConvertFrom1.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelConvertFrom1.text")); // NOI18N

        jComboBoxFrom1.setEditable(true);
        jComboBoxFrom1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFrom1jComboBoxConvertActionPerformed(evt);
            }
        });

        jLabelTo1.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabelTo1.text")); // NOI18N

        jComboBoxTo1.setEditable(true);
        jComboBoxTo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTo1jComboBoxConvertActionPerformed(evt);
            }
        });

        jButtonConvert1.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jButtonConvert1.text")); // NOI18N
        jButtonConvert1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvert1ActionPerformed(evt);
            }
        });

        jScrollPaneStevens.setPreferredSize(new java.awt.Dimension(454, 250));

        jTableStevens.setGridColor(java.awt.Color.lightGray);
        jScrollPaneStevens.setViewportView(jTableStevens);

        org.jdesktop.layout.GroupLayout jPanelConvert1Layout = new org.jdesktop.layout.GroupLayout(jPanelConvert1);
        jPanelConvert1.setLayout(jPanelConvert1Layout);
        jPanelConvert1Layout.setHorizontalGroup(
            jPanelConvert1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConvert1Layout.createSequentialGroup()
                .add(jPanelConvert1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPaneStevens, 0, 0, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanelConvert1Layout.createSequentialGroup()
                        .add(jLabelConvertFrom1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBoxFrom1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 69, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelTo1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jComboBoxTo1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 63, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonConvert1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 77, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelConvert1Layout.setVerticalGroup(
            jPanelConvert1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConvert1Layout.createSequentialGroup()
                .add(jPanelConvert1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelConvert1Layout.createSequentialGroup()
                        .add(4, 4, 4)
                        .add(jLabelConvertFrom1))
                    .add(jPanelConvert1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jComboBoxFrom1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabelTo1)
                        .add(jComboBoxTo1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jButtonConvert1)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPaneStevens, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 242, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPaneStevens.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jScrollPaneStevens.AccessibleContext.accessibleName")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelStevensLayout = new org.jdesktop.layout.GroupLayout(jPanelStevens);
        jPanelStevens.setLayout(jPanelStevensLayout);
        jPanelStevensLayout.setHorizontalGroup(
            jPanelStevensLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelStevensLayout.createSequentialGroup()
                .add(jPanelConvert1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelStevensLayout.setVerticalGroup(
            jPanelStevensLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelConvert1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        jTextFieldMass.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldMass.text")); // NOI18N
        jTextFieldMass.setToolTipText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jTextFieldMass.toolTipText")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jLabel5.text")); // NOI18N

        org.jdesktop.layout.GroupLayout jPanelMassLayout = new org.jdesktop.layout.GroupLayout(jPanelMass);
        jPanelMass.setLayout(jPanelMassLayout);
        jPanelMassLayout.setHorizontalGroup(
            jPanelMassLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelMassLayout.createSequentialGroup()
                .add(jLabel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jTextFieldMass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanelMassLayout.setVerticalGroup(
            jPanelMassLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelMassLayout.createSequentialGroup()
                .add(jPanelMassLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextFieldMass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel5))
                .add(0, 0, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout jPanelLayout = new org.jdesktop.layout.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelLayout.createSequentialGroup()
                .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jPanelWybourne, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanelStevens, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelRadialWaveFunction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 450, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanelRadialMatrixElements, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 376, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanelStevensFactors, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelLayout.createSequentialGroup()
                        .add(jPanelMagneticFormFactors, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .add(212, 212, 212))))
            .add(jPanelLayout.createSequentialGroup()
                .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jPanelZCoefficients, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 1032, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanelLayout.createSequentialGroup()
                        .add(jPanelModule, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanelJ, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanelConfiguration, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(18, 18, 18)
                        .add(jPanelABC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanelIonType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanelLayout.createSequentialGroup()
                        .add(jPanelLandeFactor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(1, 1, 1)
                        .add(jPanelCoulomb, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelSpinOrbit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jPanelUnit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(45, 45, 45)
                        .add(jPanelBasis, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanelLayout.createSequentialGroup()
                .add(jPanelDebyeWallerFactor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 308, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelScatteringLength, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 226, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelMass, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 0, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanelLayout.createSequentialGroup()
                .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelABC, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanelLayout.createSequentialGroup()
                        .add(jPanelJ, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelConfiguration, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanelModule, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelIonType, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 54, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelCoulomb, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanelLayout.createSequentialGroup()
                        .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanelLandeFactor, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanelUnit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(org.jdesktop.layout.GroupLayout.LEADING, jPanelSpinOrbit, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jPanelBasis, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .add(10, 10, 10)))
                .add(18, 18, 18)
                .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelLayout.createSequentialGroup()
                        .add(jPanelStevensFactors, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelRadialMatrixElements, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelRadialWaveFunction, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jPanelMagneticFormFactors, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 244, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanelLayout.createSequentialGroup()
                        .add(jPanelWybourne, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanelStevens, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanelLayout.createSequentialGroup()
                        .add(jPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(jPanelScatteringLength, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(jPanelMass, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(638, 638, 638)
                        .add(jPanelZCoefficients, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanelDebyeWallerFactor, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanelCoulomb.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelCoulomb.AccessibleContext.accessibleName_1")); // NOI18N
        jPanelSpinOrbit.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelSpinOrbit.AccessibleContext.accessibleName")); // NOI18N
        jPanelWybourne.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelWybourne.AccessibleContext.accessibleName")); // NOI18N
        jPanelWybourne.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelWybourne.AccessibleContext.accessibleDescription")); // NOI18N
        jPanelBasis.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelBasis.AccessibleContext.accessibleName")); // NOI18N
        jPanelStevens.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelStevens.AccessibleContext.accessibleName")); // NOI18N
        jPanelStevens.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(sipfEditor.class, "sipfEditor.jPanelStevens.AccessibleContext.accessibleDescription")); // NOI18N

        jScrollPane.setViewportView(jPanel);

        add(jScrollPane);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonUpdateConstantsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateConstantsActionPerformed
        //Get an input output provider
        OutputWriter io = getInputOutput().getOut();
        //Check
        if (!ionCache.containsKey(((JTextComponent) jComboBoxIONTYPE.getEditor().getEditorComponent()).getText())) {
            outputColorPrint("Please select an ion type.", outputRed);
            return;
        }
        //Print the current update
        io.printf("sipf Editor - Updating constants for ion '%s' @ %s\n\n",((JTextComponent) jComboBoxIONTYPE.getEditor().getEditorComponent()).getText(),
                new Date().toString());
        try {
            //Load everything associated with the ion from a file
            FileObject f = ionCache.get(((JTextComponent) jComboBoxIONTYPE.getEditor().getEditorComponent()).getText());
            //Get the file as text
            String text = f.asText();
            //Get all the variables to copy
            ArrayList<String> copy = new ArrayList<String>();
            copy.addAll(new MagneticFormFactorModel().getVariableNames());
            copy.addAll(new ZCoefficientModel().getVariableNames());
            copy.add("SCATTERINGLENGTHREAL");
            copy.add("SCATTERINGLENGTHIMAG");
            copy.add("ALPHA");
            copy.add("BETA");
            copy.add("GAMMA");
            copy.add("R2");
            copy.add("R4");
            copy.add("R6");
            copy.add("GJ");
            copy.add("CHARGE");
            copy.add("MODPAR1");
            copy.add("nof_electrons");
            copy.add("units"); 
            copy.add("conf");
            copy.add("F2");
            copy.add("F4");
            copy.add("F6");
            copy.add("zeta");
            copy.add("basis");

            //Delete all the current variables
            String Np = getVariable("N1");
            for (int p = 2; Np != null && Np.length() != 0; p++) {
                //remove the previous variables
                setVariable(String.format("C%d", p - 1), "tobedeleted");
                setVariable(String.format("XI%d", p - 1),"tobedeleted");
                setVariable(String.format("N%d", p - 1), "tobedeleted");
                //Get the new value
                Np = getVariable(String.format("N%d", p));
            }
            //Add the variables N_i XI_i C_i
            Np = VariableHelper.getVariable("N1", text);
            for (int p = 2; Np != null && Np.length() != 0; p++) {
                //Add the previous variables
                copy.add(String.format("N%d", p - 1));
                copy.add(String.format("C%d", p - 1));
                copy.add(String.format("XI%d", p - 1));
                //Get the new value
                Np = VariableHelper.getVariable(String.format("N%d", p), text);
            }

            //Run through the variables and copy them
            for (Object var : copy) {
                //Get the new value
                String value = VariableHelper.getVariable((String) var, text);
                //Check whether the value is valid
                if (value == null || value.length() == 0) {
                    outputColorPrint(String.format("'%s' could not be updated because it is not found in the ions database.", var), outputOrange);
                    Np = getVariable((String)var);
                    if (Np != null && Np.length() != 0) // if variable was there show that update is not possible
                    {setVariable((String) var, "no_value_in_database_err");}  // ( do not insert a new variable to the file)
                    continue;
                }
                //Set the value
                setVariable((String) var, value);
                //Print
                outputColorPrint(String.format("'%s' was updated.", var), outputGreen);
            }
            Np = getVariable("N1");
            for (int p = 2; Np != null && Np.length() != 0; p++) {
            if(Np.contentEquals("tobedeleted"))
            {   //remove the previous variables
                outputColorPrint(String.format("'N%d' 'C%d' 'XI%d' were removed.", p-1,p-1,p-1), outputOrange);
                setVariable(String.format("C%d", p - 1), null);
                setVariable(String.format("XI%d", p - 1),null);
                setVariable(String.format("N%d", p - 1), null);
            }   //Get the new value
                Np = getVariable(String.format("N%d", p));
            }
            //Completed
            io.print("\nUpdate completed.\n\n");
            //Update gui
            updateControls();
            //Get the number of rows in the radial wave function table
            ((RadialWaveFunctionModel) jTableRadialWaveFunction.getModel()).updateRowCount();
        } catch (IOException ex) {
            io.println(ex.getMessage());
        }
    }//GEN-LAST:event_jButtonUpdateConstantsActionPerformed

    private void jButtonConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertActionPerformed
        //Get an input output provider
        OutputWriter io = getInputOutput().getOut();
        //Check
        if (!ionCache.containsKey(((JTextComponent) jComboBoxFrom.getEditor().getEditorComponent()).getText()) || !ionCache.containsKey(((JTextComponent) jComboBoxTo.getEditor().getEditorComponent()).getText())) {
            outputColorPrint("Please select two ion types.", outputRed);
            return;
        }
        //Print the current update
        io.printf("sipf Editor - Scaling crystal field from ion '%s' to '%s' @ %s\n\n",
                jComboBoxFrom.getSelectedItem(), jComboBoxTo.getSelectedItem(),
                new Date().toString());

        try {
            //Get both files as text
            String[] text = {ionCache.get((String) jComboBoxFrom.getSelectedItem()).asText(),
                             ionCache.get((String)   jComboBoxTo.getSelectedItem()).asText()};
            //Get the new and old r^x values
            double[][] r = new double[2][3];
            //Get the values
            for (int j = 0; j < 3; j++) {
                //Variable holding a value whether the update needs to be canceled
                boolean cancel = false;
                for (int i = 0; i < 2; i++) {
                    //Get the variable name and value
                    String name = String.format("R%d", (j + 1) * 2);
                    String value = VariableHelper.getVariable(name, text[i]);
                    //Only errors printed here
                    //Check whether it is numerical
                    if (value == null || !RegexVerifier.getScientificVerifier(false).verify(value)) {
                        outputColorPrint(String.format("%s The variable '%s' from ion '%s' could not be read. Update cancelled.", name,
                                i == 0 ? jComboBoxFrom.getSelectedItem() : jComboBoxTo.getSelectedItem()), outputRed);
                        cancel = true;
                        continue;
                    }
                    //Read the variables
                    r[i][j] = Double.parseDouble(value);
                    //Scaling will be done
                    outputColorPrint(String.format("The variable '%s' from ion '%s' was read. Scaling...", name,
                            i == 0 ? jComboBoxFrom.getSelectedItem() : jComboBoxTo.getSelectedItem()), outputGreen);
                }
                //Go to the next l value if the values could not be read
                if (cancel) {
                    continue;
                }
                //Do the scaling
                for (int m = -(j + 1) * 2; m <= (j + 1) * 2; m++) {
                    //Get the variable name to multiply
                    String name = String.format("L%d%d%s", (j + 1) * 2, Math.abs(m), m < 0 ? "s" : "");
                    String value = getVariable(name);
                    //Check whether it is numerical
                    if (value == null || !RegexVerifier.getScientificVerifier(false).verify(value)) {
                        outputColorPrint(String.format("'%s' could not be scaled.", name), outputOrange);
                        continue;
                    }
                    //Get the value as a double
                    double dvalue = Double.valueOf(value);
                    //Multiply and divide with r^l
                    dvalue = dvalue * r[1][j] / r[0][j];
                    //Set the value
                    setVariable(name, Double.toString(dvalue));
                    //Ok
                    outputColorPrint(String.format("'%s' was scaled.", name), outputGreen);
                }
            }
            //Everything completed
            io.print("\nConversion completed.\n\n");
            //Redraw
            jTableWybourne.updateUI();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }//GEN-LAST:event_jButtonConvertActionPerformed

    private void jComboBoxConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConvertActionPerformed
        //Set whether the convert button is enabled
        jButtonConvert.setEnabled(jComboBoxFrom.getSelectedIndex() != -1 && 
                      jComboBoxTo.getSelectedIndex() != -1 &&
                        jComboBoxFrom.getSelectedIndex() != jComboBoxTo.getSelectedIndex());
    }//GEN-LAST:event_jComboBoxConvertActionPerformed

    private void jButtonAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddRowActionPerformed
        ((DefaultTableModel) jTableRadialWaveFunction.getModel()).addRow((Object[]) null);
    }//GEN-LAST:event_jButtonAddRowActionPerformed

    private void jButtonRemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveRowActionPerformed
        RadialWaveFunctionModel model = (RadialWaveFunctionModel) jTableRadialWaveFunction.getModel();
        for (String variable : model.variables) {
            setVariable(String.format(variable, model.getRowCount()), null);
        }
        //Remove the last row
        model.removeRow(model.getRowCount() - 1);
    }//GEN-LAST:event_jButtonRemoveRowActionPerformed

    private void jComboBoxUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUnitActionPerformed

    private void jComboBoxIONTYPEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIONTYPEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxIONTYPEActionPerformed

    private void jTextFieldnof_electronsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldnof_electronsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldnof_electronsActionPerformed

    private void jComboBoxBasisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBasisActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxBasisActionPerformed

    private void jComboBoxFrom1jComboBoxConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFrom1jComboBoxConvertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxFrom1jComboBoxConvertActionPerformed

    private void jComboBoxTo1jComboBoxConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTo1jComboBoxConvertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTo1jComboBoxConvertActionPerformed

    private void jButtonConvert1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvert1ActionPerformed
    //Get an input output provider
        OutputWriter io = getInputOutput().getOut();
        //Check
        if (!ionCache.containsKey(((JTextComponent) jComboBoxFrom1.getEditor().getEditorComponent()).getText()) || !ionCache.containsKey(((JTextComponent) jComboBoxTo1.getEditor().getEditorComponent()).getText())) {
            outputColorPrint("Please select two ion types.", outputRed);
            return;
        }
        //Print the current update
        io.printf("sipf Editor - Scaling crystal field from ion '%s' to '%s' @ %s\n\n",
                jComboBoxFrom1.getSelectedItem(), jComboBoxTo1.getSelectedItem(),
                new Date().toString());

        try {
            //Get both files as text
            String[] text = {ionCache.get((String) jComboBoxFrom1.getSelectedItem()).asText(),
                             ionCache.get((String)   jComboBoxTo1.getSelectedItem()).asText()};
            //Get the new and old r^x*theta_J values
            double[][] rthetaJ = new double[2][3];
             //Get the values
            for (int j = 0; j < 3; j++) {
                //Variable holding a value whether the update needs to be canceled
                boolean cancel = false;
                for (int i = 0; i < 2; i++) {
                    //Get the variable name and value
                    String name = String.format("R%d", (j + 1) * 2);
                    String value = VariableHelper.getVariable(name, text[i]);
                    //Only errors printed here
                    //Check whether it is numerical
                    if (value == null || !RegexVerifier.getScientificVerifier(false).verify(value)) {
                        outputColorPrint(String.format("The variable '%s' from ion '%s' could not be read. Update cancelled.", name,
                                i == 0 ? jComboBoxFrom1.getSelectedItem() : jComboBoxTo1.getSelectedItem()), outputRed);
                        cancel = true;
                        continue;
                                                                                                    }
                    //Read the variables
                    rthetaJ[i][j] = Double.parseDouble(value);
                    //Scaling will be done
                    outputColorPrint(String.format("The variable '%s' from ion '%s' was read. Scaling...", name,
                            i == 0 ? jComboBoxFrom1.getSelectedItem() : jComboBoxTo1.getSelectedItem()), outputGreen);

                    //Get the variable name and value
                    if (j==0) { name = String.format("ALPHA");}
                    if (j==1) { name = String.format("BETA");}
                    if (j==2) { name = String.format("GAMMA");}
                    value= VariableHelper.getVariable(name, text[i]);
                    //Only errors printed here
                    //Check whether it is numerical
                    if (value == null || !RegexVerifier.getScientificVerifier(false).verify(value)) {
                        outputColorPrint(String.format("The variable '%s' from ion '%s' could not be read. Update cancelled.", name,
                                i == 0 ? jComboBoxFrom1.getSelectedItem() : jComboBoxTo1.getSelectedItem()), outputRed);
                        cancel = true;
                        continue;
                                                                                                    }
                    //Read the variables
                    rthetaJ[i][j] *= Double.parseDouble(value);
                    //Scaling will be done
                    outputColorPrint(String.format("The variable '%s' from ion '%s' was read. Scaling...", name,
                            i == 0 ? jComboBoxFrom1.getSelectedItem() : jComboBoxTo1.getSelectedItem()), outputGreen);

                }
                //Go to the next l value if the values could not be read
                if (cancel) {
                    continue;
                }
                //Do the scaling
                for (int m = -(j + 1) * 2; m <= (j + 1) * 2; m++) {
                    //Get the variable name to multiply
                    String name = String.format("B%d%d%s", (j + 1) * 2, Math.abs(m), m < 0 ? "s" : "");
                    String value = getVariable(name);
                    //Check whether it is numerical
                    if (value == null || !RegexVerifier.getScientificVerifier(false).verify(value)) {
                        outputColorPrint(String.format("'%s' could not be scaled.", name), outputOrange);
                        continue;
                    }
                    //Get the value as a double
                    double dvalue = Double.valueOf(value);
                    //Multiply and divide with r^l theta_l
                    dvalue = dvalue * rthetaJ[1][j] / rthetaJ[0][j];
                    //Set the value
                    setVariable(name, Double.toString(dvalue));
                    //Ok
                    outputColorPrint(String.format("'%s' was scaled.", name), outputGreen);
                }
            }
            //Everything completed
            io.print("\nConversion completed.\n\n");
            //Redraw
            jTableStevens.updateUI();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }

    }//GEN-LAST:event_jButtonConvert1ActionPerformed

    private void jComboBoxMODULEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMODULEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxMODULEActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabelBeta;
    private javax.swing.JButton jButtonAddRow;
    private javax.swing.JButton jButtonConvert;
    private javax.swing.JButton jButtonConvert1;
    private javax.swing.JButton jButtonRemoveRow;
    private javax.swing.JButton jButtonUpdateConstants;
    private javax.swing.JComboBox<String> jComboBoxBasis;
    private javax.swing.JComboBox<Object> jComboBoxFrom;
    private javax.swing.JComboBox<Object> jComboBoxFrom1;
    private javax.swing.JComboBox<Object> jComboBoxIONTYPE;
    private javax.swing.JComboBox<String> jComboBoxMODULE;
    private javax.swing.JComboBox<Object> jComboBoxTo;
    private javax.swing.JComboBox<Object> jComboBoxTo1;
    private javax.swing.JComboBox<String> jComboBoxUnit;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelA;
    private javax.swing.JLabel jLabelAlpha;
    private javax.swing.JLabel jLabelB;
    private javax.swing.JLabel jLabelC;
    private javax.swing.JLabel jLabelCharge;
    private javax.swing.JLabel jLabelCharge1;
    private javax.swing.JLabel jLabelCharge2;
    private javax.swing.JLabel jLabelConvertFrom;
    private javax.swing.JLabel jLabelConvertFrom1;
    private javax.swing.JLabel jLabelF2;
    private javax.swing.JLabel jLabelF4;
    private javax.swing.JLabel jLabelF6;
    private javax.swing.JLabel jLabelGamma;
    private javax.swing.JLabel jLabelR2;
    private javax.swing.JLabel jLabelR4;
    private javax.swing.JLabel jLabelR6;
    private javax.swing.JLabel jLabelScatteringLengthImag;
    private javax.swing.JLabel jLabelScatteringLengthReal;
    private javax.swing.JLabel jLabelTo;
    private javax.swing.JLabel jLabelTo1;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanelABC;
    private javax.swing.JPanel jPanelAddRemove;
    private javax.swing.JPanel jPanelBasis;
    private javax.swing.JPanel jPanelConfiguration;
    private javax.swing.JPanel jPanelConvert;
    private javax.swing.JPanel jPanelConvert1;
    private javax.swing.JPanel jPanelCoulomb;
    private javax.swing.JPanel jPanelDebyeWallerFactor;
    private javax.swing.JPanel jPanelIonType;
    private javax.swing.JPanel jPanelJ;
    private javax.swing.JPanel jPanelLandeFactor;
    private javax.swing.JPanel jPanelMagneticFormFactors;
    private javax.swing.JPanel jPanelMass;
    private javax.swing.JPanel jPanelModule;
    private javax.swing.JPanel jPanelRadialMatrixElements;
    private javax.swing.JPanel jPanelRadialWaveFunction;
    private javax.swing.JPanel jPanelScatteringLength;
    private javax.swing.JPanel jPanelSpinOrbit;
    private javax.swing.JPanel jPanelStevens;
    private javax.swing.JPanel jPanelStevensFactors;
    private javax.swing.JPanel jPanelUnit;
    private javax.swing.JPanel jPanelWybourne;
    private javax.swing.JPanel jPanelZCoefficients;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JScrollPane jScrollPaneMagneticFormFactors;
    private javax.swing.JScrollPane jScrollPaneRadialWaveFunction;
    private javax.swing.JScrollPane jScrollPaneStevens;
    private javax.swing.JScrollPane jScrollPaneWybourne;
    private javax.swing.JScrollPane jScrollPaneZCoefficients;
    private javax.swing.JTable jTableMagneticFormFactors;
    private javax.swing.JTable jTableRadialWaveFunction;
    private javax.swing.JTable jTableStevens;
    private javax.swing.JTable jTableWybourne;
    private javax.swing.JTable jTableZCoefficients;
    private javax.swing.JTextField jTextFieldA;
    private javax.swing.JTextField jTextFieldALPHA;
    private javax.swing.JTextField jTextFieldB;
    private javax.swing.JTextField jTextFieldBETA;
    private javax.swing.JTextField jTextFieldC;
    private javax.swing.JTextField jTextFieldCharge;
    private javax.swing.JTextField jTextFieldDWF;
    private javax.swing.JTextField jTextFieldF2;
    private javax.swing.JTextField jTextFieldF4;
    private javax.swing.JTextField jTextFieldF6;
    private javax.swing.JTextField jTextFieldGAMMA;
    private javax.swing.JTextField jTextFieldGJ;
    private javax.swing.JTextField jTextFieldJ;
    private javax.swing.JTextField jTextFieldMass;
    private javax.swing.JTextField jTextFieldR2;
    private javax.swing.JTextField jTextFieldR4;
    private javax.swing.JTextField jTextFieldR6;
    private javax.swing.JTextField jTextFieldSCATTERINGLENGTHIMAG;
    private javax.swing.JTextField jTextFieldSCATTERINGLENGTHREAL;
    private javax.swing.JTextField jTextFieldSO;
    private javax.swing.JTextField jTextFieldconf;
    private javax.swing.JTextField jTextFieldnof_electrons;
    // End of variables declaration//GEN-END:variables
}
