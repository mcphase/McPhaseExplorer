package org.mcphase.ini;

import javax.swing.ToolTipManager;
import org.mcphase.base.RegexVerifier;
import org.mcphase.base.VisualEditorBase;
import org.openide.text.DataEditorSupport;

/**
 *
 * @author Till Hoffmann
 */
public class iniEditor extends VisualEditorBase {

    /** Creates new form iniEditor */
    public iniEditor(DataEditorSupport support) {
        super(support, "ini Editor");
        initComponents();
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(5000);
       
        String[] ionNames = {
                  "0....T[K]",
                  "1....Ha[T]",
                  "2....Hb[T]",
                  "3....Hc[T]",
                  "4....Hi[T]",
                  "5....Hj[T]",
                  "6....Hk[T]",
                  "7....Ea[kV/mm]",
                  "8....Eb[kV/mm]",
                  "9....Ec[kV/mm]",
                  "10....Ei[kV/mm]",
                  "11....Ej[kV/mm]",
                  "12....Ek[kV/mm]",
                  "13....s1[GPa]",
                  "14....s2[GPa]",
                  "15....s3[GPa]",
                  "16....s4[GPa]",
                  "17....s5[GPa]",
                  "18....s6[GPa]",
                  "19....x",
                  "20....y",
                  "21....|H|[T]",
                  "22....|E|[T]",
                  "23...|Hext|[T]",
                  "24...|Eext|[T]",
                  "25...Hexta[T]",
                  "26...Hextb[T]",
                  "27...Hextc[T]",
                  "28...Hexti[T]",
                  "29...Hextj[T]",
                  "30...Hextk[T]",
                  "31...Eexta[kV/mm]",
                  "32...Eextb[kV/mm]",
                  "33...Eextc[kV/mm]",
                  "34...Eexti[kV/mm]",
                  "35...Eextj[kV/mm]",
                  "36...Eextk[kV/mm]"
                               };
         for (String s : ionNames) {
             jComboBoxout1.addItem(s);
             jComboBoxout2.addItem(s);
             jComboBoxout3.addItem(s);
             jComboBoxout4.addItem(s);
             jComboBoxout5.addItem(s);
             jComboBoxout6.addItem(s);
             jComboBoxout7.addItem(s);
        }
      
       
        // <editor-fold defaultstate="collapsed" desc="Component registration">
        registerComponent("xT", jTextFieldxT);
        registerComponent("xHa", jTextFieldxHa);
        registerComponent("xHb", jTextFieldxHb);
        registerComponent("xHc", jTextFieldxHc);
        registerComponent("xHi", jTextFieldxHi);
        registerComponent("xHj", jTextFieldxHj);
        registerComponent("xHk", jTextFieldxHk);
        registerComponent("xEa", jTextFieldxEa);
        registerComponent("xEb", jTextFieldxEb);
        registerComponent("xEc", jTextFieldxEc);
        registerComponent("xEi", jTextFieldxEi);
        registerComponent("xEj", jTextFieldxEj);
        registerComponent("xEk", jTextFieldxEk);
        registerComponent("xs1", jTextFieldxs1);
        registerComponent("xs2", jTextFieldxs2);
        registerComponent("xs3", jTextFieldxs3);
        registerComponent("xs4", jTextFieldxs4);
        registerComponent("xs5", jTextFieldxs5);
        registerComponent("xs6", jTextFieldxs6);
        registerComponent("xmin", jTextFieldxmin);
        registerComponent("xmax", jTextFieldxmax);
        registerComponent("xstep", jTextFieldxstep);

        registerComponent("yT", jTextFieldyT);
        registerComponent("yHa", jTextFieldyHa);
        registerComponent("yHb", jTextFieldyHb);
        registerComponent("yHc", jTextFieldyHc);
        registerComponent("yHi", jTextFieldyHi);
        registerComponent("yHj", jTextFieldyHj);
        registerComponent("yHk", jTextFieldyHk);
        registerComponent("yEa", jTextFieldyEa);
        registerComponent("yEb", jTextFieldyEb);
        registerComponent("yEc", jTextFieldyEc);
        registerComponent("yEi", jTextFieldyEi);
        registerComponent("yEj", jTextFieldyEj);
        registerComponent("yEk", jTextFieldyEk);
        registerComponent("ys1", jTextFieldys1);
        registerComponent("ys2", jTextFieldys2);
        registerComponent("ys3", jTextFieldys3);
        registerComponent("ys4", jTextFieldys4);
        registerComponent("ys5", jTextFieldys5);
        registerComponent("ys6", jTextFieldys6);
        registerComponent("ymin", jTextFieldymin);
        registerComponent("ymax", jTextFieldymax);
        registerComponent("ystep", jTextFieldystep);

        registerComponent("T0", jTextFieldT0);
        registerComponent("Ha0", jTextFieldHa0);
        registerComponent("Hb0", jTextFieldHb0);
        registerComponent("Hc0", jTextFieldHc0);
        registerComponent("Hi0", jTextFieldHi0);
        registerComponent("Hj0", jTextFieldHj0);
        registerComponent("Hk0", jTextFieldHk0);
        registerComponent("Ea0", jTextFieldEa0);
        registerComponent("Eb0", jTextFieldEb0);
        registerComponent("Ec0", jTextFieldEc0);
        registerComponent("Ei0", jTextFieldEi0);
        registerComponent("Ej0", jTextFieldEj0);
        registerComponent("Ek0", jTextFieldEk0);
        registerComponent("s10", jTextFields10);
        registerComponent("s20", jTextFields20);
        registerComponent("s30", jTextFields30);
        registerComponent("s40", jTextFields40);
        registerComponent("s50", jTextFields50);
        registerComponent("s60", jTextFields60);

        registerComponent("Nii", jTextFieldNii);
        registerComponent("Nij", jTextFieldsNij);
        registerComponent("Nik", jTextFieldsNik);
        registerComponent("Njk", jTextFieldsNjk);
        registerComponent("Njj", jTextFieldsNjj);
        registerComponent("Nkk", jTextFieldsNkk);

        registerComponent("hmin", jTextFieldhmin);
        registerComponent("hmax", jTextFieldhmax);
        registerComponent("deltah", jTextFielddeltah);

        registerComponent("kmin", jTextFieldkmin);
        registerComponent("kmax", jTextFieldkmax);
        registerComponent("deltak", jTextFielddeltak);

        registerComponent("lmin", jTextFieldlmin);
        registerComponent("lmax", jTextFieldlmax);
        registerComponent("deltal", jTextFielddeltal);

        registerComponent("maxqperiod", jTextFieldmaxqperiod);
        registerComponent("maxnoftestspincf", jTextFieldmaxnoftestspincf);
        registerComponent("maxnofspins", jTextFieldmaxnofspins);

        registerComponent("nofrndtries", jTextFieldnofrndtries);
        registerComponent("nofMCsteps", jTextFieldnofrndtries1);

        registerComponent("minnr1", jTextField1);
        registerComponent("minnr2", jTextField2);
        registerComponent("minnr3", jTextField3);

        registerComponent("maxstamf", jTextFieldmaxstamf);
        registerComponent("maxnofmfloops", jTextFieldmaxnofmfloops);
        registerComponent("bigstep", jTextFieldbigstep);
        registerComponent("repeat", jTextFieldrepeat);
        registerComponent("maxspinchange", jTextFieldmaxspinchange);

        registerComponent("nofspincorrs", jTextFieldnofspincorrs);

        registerComponent("maxnofhkls", jTextFieldmaxnofhkls);
        registerComponent("maxQ", jTextFieldmaxQ);

        registerComponent("exit", jCheckBoxexit);
        registerComponent("pause", jCheckBoxpause);
        registerComponent("displayall", jCheckBoxdisplayall);
        registerComponent("logfevsQ", jCheckBoxlogfevsQ);
        registerComponent("demag", jCheckBoxdemag);
        registerComponent("out1", jComboBoxout1);
        registerComponent("out2", jComboBoxout2);
        registerComponent("out3", jComboBoxout3);
        registerComponent("out4", jComboBoxout4);
        registerComponent("out5", jComboBoxout5);
        registerComponent("out6", jComboBoxout6);
        registerComponent("out7", jComboBoxout7);
        
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Verifiers">
        jTextFieldxT.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxHa.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxHb.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxHc.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxHi.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxHj.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxHk.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxEa.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxEb.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxEc.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxEi.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxEj.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxEk.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxs1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxs2.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxs3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxs4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxs5.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxs6.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldxstep.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldyT.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyHa.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyHb.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyHc.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyHi.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyHj.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyHk.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyEa.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyEb.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyEc.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyEi.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyEj.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldyEk.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldys1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldys2.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldys3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldys4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldys5.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldys6.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldymin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldymax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldystep.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldT0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldHa0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldHb0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldHc0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldHi0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldHj0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldHk0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldEa0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldEb0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldEc0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldEi0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldEj0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldEk0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFields10.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFields20.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFields30.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFields40.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFields50.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFields60.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldhmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldhmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltah.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldkmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldkmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltak.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldlmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldlmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltal.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldmaxqperiod.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldmaxnoftestspincf.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldmaxnofspins.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldnofrndtries.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldnofrndtries1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextField1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextField2.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextField3.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldmaxstamf.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldmaxnofmfloops.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldbigstep.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldrepeat.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldmaxspinchange.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldnofspincorrs.setInputVerifier(RegexVerifier.getScientificVerifier());

        jTextFieldmaxnofhkls.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldmaxQ.setInputVerifier(RegexVerifier.getScientificVerifier());
        //</editor-fold>
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelPropagationVectorRange = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldhmin = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldkmin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldlmin = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldhmax = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldkmax = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldlmax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFielddeltah = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFielddeltak = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFielddeltal = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextFieldmaxqperiod = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldmaxnofspins = new javax.swing.JTextField();
        jTextFieldmaxnoftestspincf = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jTextFieldnofrndtries1 = new javax.swing.JTextField();
        jTextFieldnofrndtries = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldmaxstamf = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextFieldmaxnofmfloops = new javax.swing.JTextField();
        jTextFieldmaxspinchange = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jTextFieldbigstep = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jTextFieldrepeat = new javax.swing.JTextField();
        jPanelMagneticScattering = new javax.swing.JPanel();
        jLabel48 = new javax.swing.JLabel();
        jTextFieldmaxnofhkls = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jTextFieldmaxQ = new javax.swing.JTextField();
        jTextFieldnofspincorrs = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanelXYPhasediagram = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldxT = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldyT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldT0 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldxHa = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldyHa = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldHa0 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextFieldxHb = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldyHb = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldHb0 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFieldxHc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextFieldyHc = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jTextFieldHc0 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldxmin = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jTextFieldymin = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jTextFieldxmax = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldymax = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldxstep = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldystep = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jTextFieldxHi = new javax.swing.JTextField();
        jTextFieldxHj = new javax.swing.JTextField();
        jTextFieldxHk = new javax.swing.JTextField();
        jTextFieldxEa = new javax.swing.JTextField();
        jTextFieldxEb = new javax.swing.JTextField();
        jTextFieldxEc = new javax.swing.JTextField();
        jTextFieldxEi = new javax.swing.JTextField();
        jTextFieldxEj = new javax.swing.JTextField();
        jTextFieldxEk = new javax.swing.JTextField();
        jTextFieldxs1 = new javax.swing.JTextField();
        jTextFieldxs2 = new javax.swing.JTextField();
        jTextFieldxs3 = new javax.swing.JTextField();
        jTextFieldxs4 = new javax.swing.JTextField();
        jTextFieldxs5 = new javax.swing.JTextField();
        jTextFieldxs6 = new javax.swing.JTextField();
        jTextFieldyHi = new javax.swing.JTextField();
        jTextFieldyHj = new javax.swing.JTextField();
        jTextFieldyHk = new javax.swing.JTextField();
        jTextFieldyEa = new javax.swing.JTextField();
        jTextFieldyEb = new javax.swing.JTextField();
        jTextFieldyEc = new javax.swing.JTextField();
        jTextFieldyEi = new javax.swing.JTextField();
        jTextFieldyEj = new javax.swing.JTextField();
        jTextFieldyEk = new javax.swing.JTextField();
        jTextFieldys1 = new javax.swing.JTextField();
        jTextFieldys2 = new javax.swing.JTextField();
        jTextFieldys3 = new javax.swing.JTextField();
        jTextFieldys4 = new javax.swing.JTextField();
        jTextFieldys5 = new javax.swing.JTextField();
        jTextFieldys6 = new javax.swing.JTextField();
        jTextFieldHi0 = new javax.swing.JTextField();
        jTextFieldHj0 = new javax.swing.JTextField();
        jTextFieldHk0 = new javax.swing.JTextField();
        jTextFieldEa0 = new javax.swing.JTextField();
        jTextFieldEb0 = new javax.swing.JTextField();
        jTextFieldEc0 = new javax.swing.JTextField();
        jTextFieldEi0 = new javax.swing.JTextField();
        jTextFieldEj0 = new javax.swing.JTextField();
        jTextFieldEk0 = new javax.swing.JTextField();
        jTextFields10 = new javax.swing.JTextField();
        jTextFields20 = new javax.swing.JTextField();
        jTextFields30 = new javax.swing.JTextField();
        jTextFields40 = new javax.swing.JTextField();
        jTextFields50 = new javax.swing.JTextField();
        jTextFields60 = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        jComboBoxout1 = new javax.swing.JComboBox<>();
        jComboBoxout2 = new javax.swing.JComboBox<>();
        jComboBoxout3 = new javax.swing.JComboBox<>();
        jComboBoxout4 = new javax.swing.JComboBox<>();
        jComboBoxout5 = new javax.swing.JComboBox<>();
        jComboBoxout6 = new javax.swing.JComboBox<>();
        jComboBoxout7 = new javax.swing.JComboBox<>();
        jLabel63 = new javax.swing.JLabel();
        jTextFieldNii = new javax.swing.JTextField();
        jTextFieldsNij = new javax.swing.JTextField();
        jTextFieldsNik = new javax.swing.JTextField();
        jTextFieldsNkk = new javax.swing.JTextField();
        jTextFieldsNjk = new javax.swing.JTextField();
        jTextFieldsNjj = new javax.swing.JTextField();
        jCheckBoxdemag = new javax.swing.JCheckBox();
        jPanelRuntimeControl = new javax.swing.JPanel();
        jCheckBoxexit = new javax.swing.JCheckBox();
        jCheckBoxpause = new javax.swing.JCheckBox();
        jCheckBoxdisplayall = new javax.swing.JCheckBox();
        jCheckBoxlogfevsQ = new javax.swing.JCheckBox();

        setLayout(new java.awt.BorderLayout());

        jScrollPane.setMinimumSize(new java.awt.Dimension(330, 23));
        jScrollPane.setPreferredSize(new java.awt.Dimension(330, 306));

        jPanel.setMinimumSize(new java.awt.Dimension(410, 539));
        jPanel.setLayout(null);

        jPanelPropagationVectorRange.setBackground(new java.awt.Color(255, 255, 102));
        jPanelPropagationVectorRange.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jPanelPropagationVectorRange.border.title"))); // NOI18N
        jPanelPropagationVectorRange.setMinimumSize(new java.awt.Dimension(330, 82));
        jPanelPropagationVectorRange.setPreferredSize(new java.awt.Dimension(330, 82));

        jLabel1.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel1.text")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel1.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldhmin.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.text")); // NOI18N
        jTextFieldhmin.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel2.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel2.text")); // NOI18N

        jTextFieldkmin.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldkmin.text")); // NOI18N
        jTextFieldkmin.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel3.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel3.text")); // NOI18N

        jTextFieldlmin.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldlmin.text")); // NOI18N
        jTextFieldlmin.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel4.text")); // NOI18N
        jLabel4.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel4.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldhmax.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmax.text")); // NOI18N
        jTextFieldhmax.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel5.text")); // NOI18N

        jTextFieldkmax.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldkmax.text")); // NOI18N
        jTextFieldkmax.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel6.text")); // NOI18N

        jTextFieldlmax.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldlmax.text")); // NOI18N
        jTextFieldlmax.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel7.text")); // NOI18N
        jLabel7.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(10, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(15, 14));

        jTextFielddeltah.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFielddeltah.text")); // NOI18N
        jTextFielddeltah.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel8.text")); // NOI18N

        jTextFielddeltak.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFielddeltak.text")); // NOI18N
        jTextFielddeltak.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel9.text")); // NOI18N

        jTextFielddeltal.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFielddeltal.text")); // NOI18N
        jTextFielddeltal.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel40.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel40.text")); // NOI18N
        jLabel40.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel40.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldmaxqperiod.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxqperiod.text")); // NOI18N
        jTextFieldmaxqperiod.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxqperiod.toolTipText")); // NOI18N

        jLabel41.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel41.text")); // NOI18N

        jTextFieldmaxnofspins.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnofspins.text")); // NOI18N
        jTextFieldmaxnofspins.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnofspins.toolTipText")); // NOI18N

        jTextFieldmaxnoftestspincf.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnoftestspincf.text_1")); // NOI18N
        jTextFieldmaxnoftestspincf.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnoftestspincf.toolTipText_1")); // NOI18N

        jLabel50.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel50.text")); // NOI18N

        jLabel53.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel53.text")); // NOI18N

        jTextField1.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextField1.text")); // NOI18N
        jTextField1.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextField1.toolTipText")); // NOI18N

        jLabel54.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel54.text")); // NOI18N

        jTextField2.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextField2.text")); // NOI18N
        jTextField2.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextField2.toolTipText")); // NOI18N

        jLabel55.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel55.text")); // NOI18N

        jTextField3.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextField3.text")); // NOI18N
        jTextField3.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextField3.toolTipText")); // NOI18N

        jPanel1.setBackground(new java.awt.Color(0, 255, 0));

        jLabel42.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel42.text")); // NOI18N

        jLabel52.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel52.text")); // NOI18N

        jTextFieldnofrndtries1.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldnofrndtries1.text")); // NOI18N
        jTextFieldnofrndtries1.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldnofrndtries1.toolTipText")); // NOI18N

        jTextFieldnofrndtries.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldnofrndtries.text")); // NOI18N
        jTextFieldnofrndtries.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldnofrndtries.toolTipText")); // NOI18N

        jLabel33.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel33.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel52))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldnofrndtries, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                    .addComponent(jTextFieldnofrndtries1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(37, 37, 37))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel33)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel52))
                    .addComponent(jTextFieldnofrndtries, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jTextFieldnofrndtries1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel52.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel52.AccessibleContext.accessibleName")); // NOI18N
        jTextFieldnofrndtries1.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldnofMCsteps.AccessibleContext.accessibleDescription")); // NOI18N

        jPanel2.setBackground(new java.awt.Color(154, 193, 255));

        jLabel43.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel43.text")); // NOI18N
        jLabel43.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel43.setMinimumSize(new java.awt.Dimension(10, 14));
        jLabel43.setPreferredSize(new java.awt.Dimension(15, 14));

        jLabel30.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel30.text")); // NOI18N

        jTextFieldmaxstamf.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxstamf.text")); // NOI18N
        jTextFieldmaxstamf.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxstamf.toolTipText")); // NOI18N

        jLabel44.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel44.text")); // NOI18N

        jTextFieldmaxnofmfloops.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnofmfloops.text")); // NOI18N
        jTextFieldmaxnofmfloops.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnofmfloops.toolTipText")); // NOI18N

        jTextFieldmaxspinchange.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxspinchange.text")); // NOI18N
        jTextFieldmaxspinchange.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxspinchange.toolTipText")); // NOI18N

        jLabel46.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel46.text")); // NOI18N

        jTextFieldbigstep.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldbigstep.text")); // NOI18N
        jTextFieldbigstep.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldbigstep.toolTipText")); // NOI18N

        jLabel45.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel45.text")); // NOI18N
        jLabel45.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel45.setMinimumSize(new java.awt.Dimension(10, 14));
        jLabel45.setPreferredSize(new java.awt.Dimension(15, 14));

        jLabel62.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel62.text")); // NOI18N
        jLabel62.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel62.setMinimumSize(new java.awt.Dimension(10, 14));
        jLabel62.setPreferredSize(new java.awt.Dimension(15, 14));

        jTextFieldrepeat.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldrepeat.text")); // NOI18N
        jTextFieldrepeat.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldrepeat.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldrepeat, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(24, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldmaxstamf, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldbigstep, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(4, 4, 4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(4, 4, 4)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldmaxnofmfloops, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldmaxspinchange, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jLabel30))
                    .addContainerGap()))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldrepeat, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(jLabel30)
                    .addGap(15, 15, 15)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jTextFieldmaxstamf, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextFieldbigstep, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(26, 26, 26)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(jTextFieldmaxnofmfloops, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(jTextFieldmaxspinchange, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(42, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanelPropagationVectorRangeLayout = new javax.swing.GroupLayout(jPanelPropagationVectorRange);
        jPanelPropagationVectorRange.setLayout(jPanelPropagationVectorRangeLayout);
        jPanelPropagationVectorRangeLayout.setHorizontalGroup(
            jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldkmin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jTextFieldhmin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldhmax, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFielddeltah, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                        .addComponent(jTextFieldkmax, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFielddeltak, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                        .addComponent(jTextFieldlmax, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFielddeltal, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))))
                        .addGap(67, 67, 67)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldmaxqperiod, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldlmin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldmaxnoftestspincf, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                                .addComponent(jTextFieldmaxnofspins, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))))
                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addComponent(jLabel41, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel55)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(63, 63, 63))
        );
        jPanelPropagationVectorRangeLayout.setVerticalGroup(
            jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPropagationVectorRangeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldhmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFielddeltah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldhmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldkmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelPropagationVectorRangeLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jTextFielddeltak, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldmaxqperiod, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 3, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jTextFieldkmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldlmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldlmax)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFielddeltal))
                .addGap(18, 18, 18)
                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldmaxnoftestspincf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldmaxnofspins, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelPropagationVectorRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        jTextField1.getAccessibleContext().setAccessibleName(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextField1.AccessibleContext.accessibleName")); // NOI18N

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jPanelPropagationVectorRange.TabConstraints.tabTitle"), jPanelPropagationVectorRange); // NOI18N

        jPanelMagneticScattering.setBackground(new java.awt.Color(204, 255, 255));
        jPanelMagneticScattering.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.border.title"))); // NOI18N
        jPanelMagneticScattering.setMaximumSize(new java.awt.Dimension(430, 52));
        jPanelMagneticScattering.setMinimumSize(new java.awt.Dimension(330, 42));
        jPanelMagneticScattering.setName(""); // NOI18N
        jPanelMagneticScattering.setPreferredSize(new java.awt.Dimension(330, 42));

        jLabel48.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel48.text")); // NOI18N
        jLabel48.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel48.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldmaxnofhkls.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnofhkls.text")); // NOI18N
        jTextFieldmaxnofhkls.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxnofhkls.toolTipText")); // NOI18N

        jLabel49.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel49.text")); // NOI18N
        jLabel49.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel49.setMinimumSize(new java.awt.Dimension(10, 14));
        jLabel49.setPreferredSize(new java.awt.Dimension(10, 14));

        jTextFieldmaxQ.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxQ.text")); // NOI18N
        jTextFieldmaxQ.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldmaxQ.toolTipText")); // NOI18N

        jTextFieldnofspincorrs.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldnofspincorrs.text")); // NOI18N
        jTextFieldnofspincorrs.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldnofspincorrs.toolTipText")); // NOI18N

        jLabel47.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel47.text")); // NOI18N

        jLabel51.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel51.text")); // NOI18N

        javax.swing.GroupLayout jPanelMagneticScatteringLayout = new javax.swing.GroupLayout(jPanelMagneticScattering);
        jPanelMagneticScattering.setLayout(jPanelMagneticScatteringLayout);
        jPanelMagneticScatteringLayout.setHorizontalGroup(
            jPanelMagneticScatteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMagneticScatteringLayout.createSequentialGroup()
                .addGroup(jPanelMagneticScatteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMagneticScatteringLayout.createSequentialGroup()
                        .addComponent(jLabel47)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldnofspincorrs, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMagneticScatteringLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel51))
                    .addGroup(jPanelMagneticScatteringLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldmaxnofhkls, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldmaxQ, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(377, Short.MAX_VALUE))
        );
        jPanelMagneticScatteringLayout.setVerticalGroup(
            jPanelMagneticScatteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMagneticScatteringLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMagneticScatteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldmaxnofhkls)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldmaxQ))
                .addGap(27, 27, 27)
                .addComponent(jLabel51)
                .addGap(18, 18, 18)
                .addGroup(jPanelMagneticScatteringLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(jTextFieldnofspincorrs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(129, 129, 129))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jPanelMagneticScattering.TabConstraints.tabTitle"), jPanelMagneticScattering); // NOI18N
        jPanelMagneticScattering.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jPanelMagneticScattering.AccessibleContext.accessibleDescription")); // NOI18N

        jPanelXYPhasediagram.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jPanelXYPhasediagram.border.title"))); // NOI18N
        jPanelXYPhasediagram.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.toolTipText")); // NOI18N
        jPanelXYPhasediagram.setMinimumSize(new java.awt.Dimension(330, 182));
        jPanelXYPhasediagram.setPreferredSize(new java.awt.Dimension(330, 182));

        jLabel10.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel10.text")); // NOI18N

        jLabel37.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel37.text")); // NOI18N

        jLabel11.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel11.text")); // NOI18N

        jLabel38.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel38.text")); // NOI18N

        jLabel12.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel12.text")); // NOI18N

        jLabel39.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel39.text")); // NOI18N

        jLabel13.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel13.text")); // NOI18N
        jLabel13.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldxT.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxT.text")); // NOI18N
        jTextFieldxT.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.toolTipText")); // NOI18N

        jLabel14.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel14.text")); // NOI18N

        jTextFieldyT.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyT.text")); // NOI18N
        jTextFieldyT.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyT.toolTipText")); // NOI18N

        jLabel15.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel15.text")); // NOI18N

        jTextFieldT0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldT0.text")); // NOI18N
        jTextFieldT0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldT0.toolTipText")); // NOI18N

        jLabel16.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel16.text")); // NOI18N
        jLabel16.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldxHa.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHa.text")); // NOI18N
        jTextFieldxHa.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.toolTipText")); // NOI18N

        jLabel17.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel17.text")); // NOI18N

        jTextFieldyHa.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHa.text")); // NOI18N
        jTextFieldyHa.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHa.toolTipText")); // NOI18N

        jLabel18.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel18.text")); // NOI18N

        jTextFieldHa0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHa0.text")); // NOI18N
        jTextFieldHa0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHa0.toolTipText")); // NOI18N

        jLabel19.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel19.text")); // NOI18N
        jLabel19.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldxHb.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHb.text")); // NOI18N
        jTextFieldxHb.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.toolTipText")); // NOI18N

        jLabel20.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel20.text")); // NOI18N

        jTextFieldyHb.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHb.text")); // NOI18N
        jTextFieldyHb.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHb.toolTipText")); // NOI18N

        jLabel21.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel21.text")); // NOI18N

        jTextFieldHb0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHb0.text")); // NOI18N
        jTextFieldHb0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHb0.toolTipText")); // NOI18N

        jLabel22.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel22.text")); // NOI18N
        jLabel22.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldxHc.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.text")); // NOI18N
        jTextFieldxHc.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.toolTipText_1")); // NOI18N

        jLabel23.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel23.text")); // NOI18N

        jTextFieldyHc.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHc.text")); // NOI18N
        jTextFieldyHc.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHc.toolTipText")); // NOI18N

        jLabel24.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel24.text")); // NOI18N

        jTextFieldHc0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHc0.text")); // NOI18N
        jTextFieldHc0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHc0.toolTipText")); // NOI18N

        jLabel25.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel25.text")); // NOI18N
        jLabel25.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldxmin.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxmin.text")); // NOI18N
        jTextFieldxmin.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.toolTipText")); // NOI18N

        jLabel26.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel26.text")); // NOI18N

        jTextFieldymin.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldymin.text")); // NOI18N
        jTextFieldymin.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldymin.toolTipText")); // NOI18N

        jLabel34.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel34.text")); // NOI18N

        jLabel28.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel28.text")); // NOI18N
        jLabel28.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldxmax.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxmax.text")); // NOI18N
        jTextFieldxmax.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.toolTipText")); // NOI18N

        jLabel29.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel29.text")); // NOI18N

        jTextFieldymax.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldymax.text")); // NOI18N
        jTextFieldymax.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldymin.toolTipText")); // NOI18N

        jLabel31.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel31.text")); // NOI18N
        jLabel31.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldxstep.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxstep.text")); // NOI18N
        jTextFieldxstep.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHc.toolTipText")); // NOI18N

        jLabel32.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel32.text")); // NOI18N

        jTextFieldystep.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldystep.text")); // NOI18N
        jTextFieldystep.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldymin.toolTipText")); // NOI18N

        jLabel56.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel56.text")); // NOI18N
        jLabel56.setMinimumSize(new java.awt.Dimension(10, 14));

        jLabel57.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel57.text")); // NOI18N

        jLabel58.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel58.text")); // NOI18N

        jLabel59.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel59.text")); // NOI18N

        jLabel60.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel60.text")); // NOI18N

        jLabel61.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel61.text")); // NOI18N

        jTextFieldxHi.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHi.text")); // NOI18N
        jTextFieldxHi.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHi.toolTipText")); // NOI18N

        jTextFieldxHj.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHj.text")); // NOI18N
        jTextFieldxHj.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHj.toolTipText")); // NOI18N

        jTextFieldxHk.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHk.text")); // NOI18N
        jTextFieldxHk.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxHk.toolTipText")); // NOI18N

        jTextFieldxEa.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEa.text")); // NOI18N
        jTextFieldxEa.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEa.toolTipText")); // NOI18N

        jTextFieldxEb.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEb.text")); // NOI18N
        jTextFieldxEb.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEb.toolTipText")); // NOI18N

        jTextFieldxEc.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEc.text")); // NOI18N
        jTextFieldxEc.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEc.toolTipText")); // NOI18N

        jTextFieldxEi.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEi.text")); // NOI18N
        jTextFieldxEi.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEi.toolTipText")); // NOI18N

        jTextFieldxEj.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEj.text")); // NOI18N
        jTextFieldxEj.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEj.toolTipText")); // NOI18N

        jTextFieldxEk.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEk.text")); // NOI18N
        jTextFieldxEk.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxEk.toolTipText")); // NOI18N

        jTextFieldxs1.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.text")); // NOI18N
        jTextFieldxs1.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.toolTipText")); // NOI18N

        jTextFieldxs2.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs2.text")); // NOI18N
        jTextFieldxs2.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.toolTipText")); // NOI18N

        jTextFieldxs3.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs3.text")); // NOI18N
        jTextFieldxs3.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.toolTipText")); // NOI18N

        jTextFieldxs4.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs4.text")); // NOI18N
        jTextFieldxs4.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.toolTipText")); // NOI18N

        jTextFieldxs5.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs5.text")); // NOI18N
        jTextFieldxs5.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.toolTipText")); // NOI18N

        jTextFieldxs6.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs6.text")); // NOI18N
        jTextFieldxs6.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldxs1.toolTipText")); // NOI18N

        jTextFieldyHi.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHi.text")); // NOI18N
        jTextFieldyHi.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHi.toolTipText")); // NOI18N

        jTextFieldyHj.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHj.text")); // NOI18N
        jTextFieldyHj.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHj.toolTipText")); // NOI18N

        jTextFieldyHk.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHk.text")); // NOI18N
        jTextFieldyHk.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyHk.toolTipText")); // NOI18N

        jTextFieldyEa.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEa.text")); // NOI18N
        jTextFieldyEa.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEa.toolTipText")); // NOI18N

        jTextFieldyEb.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEb.text")); // NOI18N
        jTextFieldyEb.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEb.toolTipText")); // NOI18N

        jTextFieldyEc.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEc.text")); // NOI18N
        jTextFieldyEc.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEc.toolTipText")); // NOI18N

        jTextFieldyEi.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEi.text")); // NOI18N
        jTextFieldyEi.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEi.toolTipText")); // NOI18N

        jTextFieldyEj.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEj.text")); // NOI18N
        jTextFieldyEj.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEj.toolTipText")); // NOI18N

        jTextFieldyEk.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEk.text")); // NOI18N
        jTextFieldyEk.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldyEk.toolTipText")); // NOI18N

        jTextFieldys1.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys1.text")); // NOI18N
        jTextFieldys1.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys1.toolTipText")); // NOI18N

        jTextFieldys2.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys2.text")); // NOI18N
        jTextFieldys2.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys1.toolTipText")); // NOI18N

        jTextFieldys3.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys3.text")); // NOI18N
        jTextFieldys3.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys1.toolTipText")); // NOI18N

        jTextFieldys4.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys4.text")); // NOI18N
        jTextFieldys4.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys1.toolTipText")); // NOI18N

        jTextFieldys5.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys5.text")); // NOI18N
        jTextFieldys5.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys1.toolTipText")); // NOI18N

        jTextFieldys6.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys6.text")); // NOI18N
        jTextFieldys6.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldys1.toolTipText")); // NOI18N

        jTextFieldHi0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHi0.text")); // NOI18N
        jTextFieldHi0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHi0.toolTipText")); // NOI18N

        jTextFieldHj0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHj0.text")); // NOI18N
        jTextFieldHj0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHj0.toolTipText")); // NOI18N

        jTextFieldHk0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHk0.text")); // NOI18N
        jTextFieldHk0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldHk0.toolTipText")); // NOI18N

        jTextFieldEa0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEa0.text")); // NOI18N
        jTextFieldEa0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEa0.toolTipText")); // NOI18N

        jTextFieldEb0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEb0.text")); // NOI18N
        jTextFieldEb0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEb0.toolTipText")); // NOI18N

        jTextFieldEc0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEc0.text")); // NOI18N
        jTextFieldEc0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEc0.toolTipText")); // NOI18N

        jTextFieldEi0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEi0.text")); // NOI18N
        jTextFieldEi0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEi0.toolTipText")); // NOI18N

        jTextFieldEj0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEj0.text")); // NOI18N
        jTextFieldEj0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEj0.toolTipText")); // NOI18N

        jTextFieldEk0.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEk0.text")); // NOI18N
        jTextFieldEk0.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldEk0.toolTipText")); // NOI18N

        jTextFields10.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields10.text")); // NOI18N
        jTextFields10.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields10.toolTipText")); // NOI18N

        jTextFields20.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields20.text")); // NOI18N
        jTextFields20.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields10.toolTipText")); // NOI18N

        jTextFields30.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields30.text")); // NOI18N
        jTextFields30.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields10.toolTipText")); // NOI18N

        jTextFields40.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields40.text")); // NOI18N
        jTextFields40.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields10.toolTipText")); // NOI18N

        jTextFields50.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields50.text")); // NOI18N
        jTextFields50.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields10.toolTipText")); // NOI18N

        jTextFields60.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields60.text")); // NOI18N
        jTextFields60.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFields10.toolTipText")); // NOI18N

        jLabel27.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel27.text")); // NOI18N

        jComboBoxout1.setEditable(true);
        jComboBoxout1.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jComboBoxout1.toolTipText")); // NOI18N

        jComboBoxout2.setEditable(true);
        jComboBoxout2.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jComboBoxout2.toolTipText")); // NOI18N

        jComboBoxout3.setEditable(true);
        jComboBoxout3.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jComboBoxout3.toolTipText")); // NOI18N

        jComboBoxout4.setEditable(true);
        jComboBoxout4.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jComboBoxout4.toolTipText")); // NOI18N

        jComboBoxout5.setEditable(true);
        jComboBoxout5.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jComboBoxout5.toolTipText")); // NOI18N

        jComboBoxout6.setEditable(true);
        jComboBoxout6.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jComboBoxout6.toolTipText")); // NOI18N

        jComboBoxout7.setEditable(true);
        jComboBoxout7.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jComboBoxout7.toolTipText")); // NOI18N

        jLabel63.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel63.text")); // NOI18N
        jLabel63.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jLabel63.toolTipText")); // NOI18N

        jTextFieldNii.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldNii.text")); // NOI18N
        jTextFieldNii.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldNii.toolTipText")); // NOI18N

        jTextFieldsNij.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNij.text")); // NOI18N
        jTextFieldsNij.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNij.toolTipText")); // NOI18N

        jTextFieldsNik.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNik.text")); // NOI18N
        jTextFieldsNik.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNik.toolTipText")); // NOI18N

        jTextFieldsNkk.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNkk.text")); // NOI18N
        jTextFieldsNkk.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNkk.toolTipText")); // NOI18N

        jTextFieldsNjk.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNjk.text")); // NOI18N
        jTextFieldsNjk.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNjk.toolTipText")); // NOI18N

        jTextFieldsNjj.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNjj.text")); // NOI18N
        jTextFieldsNjj.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jTextFieldsNjj.toolTipText")); // NOI18N

        jCheckBoxdemag.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxdemag.text")); // NOI18N
        jCheckBoxdemag.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxdemag.toolTipText")); // NOI18N
        jCheckBoxdemag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxdemagActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelXYPhasediagramLayout = new javax.swing.GroupLayout(jPanelXYPhasediagram);
        jPanelXYPhasediagram.setLayout(jPanelXYPhasediagramLayout);
        jPanelXYPhasediagramLayout.setHorizontalGroup(
            jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jTextFieldxs4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldxs5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldxs6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jTextFieldxs1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldxs2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldxs3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldxmax, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldxmin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldxstep, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelXYPhasediagramLayout.createSequentialGroup()
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTextFieldxHi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldxHj, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldxHk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelXYPhasediagramLayout.createSequentialGroup()
                                            .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                    .addComponent(jTextFieldxHa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldxHb, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldxHc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jTextFieldxT, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addComponent(jTextFieldxEa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldxEb, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldxEc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addComponent(jTextFieldxEi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldxEj, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldxEk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jTextFieldyT, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(67, 67, 67)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldymax, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldystep, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldymin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextFieldyHa, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldyHb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldyHc, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jTextFieldyHi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldyHj, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldyHk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel58)
                                    .addComponent(jLabel59))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jTextFieldys1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldys2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldys3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jTextFieldyEi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldyEj, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldyEk, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jTextFieldyEa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldyEb, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldyEc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jTextFieldys4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldys5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldys6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                            .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(6, 6, 6)
                                            .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                    .addComponent(jTextFieldHa0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldHb0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldHc0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                    .addComponent(jTextFieldHi0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldHj0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldHk0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(jTextFieldT0, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                            .addComponent(jLabel24)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldEa0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldEb0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jTextFieldEc0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addComponent(jTextFieldNii, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jTextFieldsNjj, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldsNij, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addGap(7, 7, 7)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jCheckBoxdemag)
                                                    .addComponent(jTextFieldsNik, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jTextFieldsNkk, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldsNjk, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelXYPhasediagramLayout.createSequentialGroup()
                                            .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                    .addComponent(jTextFieldEi0, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldEj0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFieldEk0, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                    .addComponent(jTextFields40, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFields50, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFields60, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                    .addComponent(jTextFields10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFields20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(jTextFields30, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelXYPhasediagramLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout7, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelXYPhasediagramLayout.setVerticalGroup(
            jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel14))
                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jTextFieldT0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldyT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(jTextFieldHa0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldHb0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldHc0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldyHa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldyHb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldyHc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(jTextFieldHi0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldHj0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldHk0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(jTextFieldEa0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEb0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEc0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(jTextFieldEi0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEj0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEk0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61)
                                    .addComponent(jTextFields10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFields20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFields30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFields40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFields50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFields60, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldxT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldxHa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldxHb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldxHc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel17)))
                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxHi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxHj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxHk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxEa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxEb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxEc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxEi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxEj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextFieldxEk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel57))
                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldxs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldxs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldxs3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldxs4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldxs5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldxs6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel20)
                                                    .addComponent(jTextFieldyHi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldyHj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldyHk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel23)
                                                    .addComponent(jTextFieldyEa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldyEb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldyEc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel58)
                                                    .addComponent(jTextFieldyEi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldyEj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldyEk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel59)
                                                    .addComponent(jTextFieldys1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldys2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldys3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldys4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldys5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldys6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextFieldymin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel63))
                                                    .addComponent(jCheckBoxdemag))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                        .addComponent(jTextFieldymax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jTextFieldystep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jTextFieldsNij, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextFieldsNik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextFieldNii, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                            .addComponent(jTextFieldsNjk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addComponent(jTextFieldsNjj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGroup(jPanelXYPhasediagramLayout.createSequentialGroup()
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldxmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jTextFieldxmax, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel29))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jTextFieldxstep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel32)))
                                            .addComponent(jLabel26))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldsNkk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanelXYPhasediagramLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel27)
                                                .addComponent(jComboBoxout1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBoxout2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBoxout3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBoxout4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBoxout5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBoxout6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBoxout7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))))
                .addGap(0, 73, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jPanelXYPhasediagram.TabConstraints.tabTitle"), jPanelXYPhasediagram); // NOI18N

        jPanel.add(jTabbedPane1);
        jTabbedPane1.setBounds(20, 10, 780, 480);

        jPanelRuntimeControl.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jPanelRuntimeControl.border.title"))); // NOI18N
        jPanelRuntimeControl.setMaximumSize(new java.awt.Dimension(750, 60));
        jPanelRuntimeControl.setMinimumSize(new java.awt.Dimension(660, 45));
        jPanelRuntimeControl.setPreferredSize(new java.awt.Dimension(660, 45));
        jPanelRuntimeControl.setLayout(new java.awt.GridLayout(1, 4));

        jCheckBoxexit.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxexit.text")); // NOI18N
        jCheckBoxexit.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxexit.toolTipText")); // NOI18N
        jPanelRuntimeControl.add(jCheckBoxexit);

        jCheckBoxpause.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxpause.text")); // NOI18N
        jCheckBoxpause.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxpause.toolTipText")); // NOI18N
        jPanelRuntimeControl.add(jCheckBoxpause);

        jCheckBoxdisplayall.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxdisplayall.text")); // NOI18N
        jCheckBoxdisplayall.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxdisplayall.toolTipText")); // NOI18N
        jPanelRuntimeControl.add(jCheckBoxdisplayall);

        jCheckBoxlogfevsQ.setText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxlogfevsQ.text")); // NOI18N
        jCheckBoxlogfevsQ.setToolTipText(org.openide.util.NbBundle.getMessage(iniEditor.class, "iniEditor.jCheckBoxlogfevsQ.toolTipText")); // NOI18N
        jPanelRuntimeControl.add(jCheckBoxlogfevsQ);

        jPanel.add(jPanelRuntimeControl);
        jPanelRuntimeControl.setBounds(20, 490, 670, 45);

        jScrollPane.setViewportView(jPanel);

        add(jScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxdemagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxdemagActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxdemagActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxdemag;
    private javax.swing.JCheckBox jCheckBoxdisplayall;
    private javax.swing.JCheckBox jCheckBoxexit;
    private javax.swing.JCheckBox jCheckBoxlogfevsQ;
    private javax.swing.JCheckBox jCheckBoxpause;
    private javax.swing.JComboBox<String> jComboBoxout1;
    private javax.swing.JComboBox<String> jComboBoxout2;
    private javax.swing.JComboBox<String> jComboBoxout3;
    private javax.swing.JComboBox<String> jComboBoxout4;
    private javax.swing.JComboBox<String> jComboBoxout5;
    private javax.swing.JComboBox<String> jComboBoxout6;
    private javax.swing.JComboBox<String> jComboBoxout7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelMagneticScattering;
    private javax.swing.JPanel jPanelPropagationVectorRange;
    private javax.swing.JPanel jPanelRuntimeControl;
    private javax.swing.JPanel jPanelXYPhasediagram;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextFieldEa0;
    private javax.swing.JTextField jTextFieldEb0;
    private javax.swing.JTextField jTextFieldEc0;
    private javax.swing.JTextField jTextFieldEi0;
    private javax.swing.JTextField jTextFieldEj0;
    private javax.swing.JTextField jTextFieldEk0;
    private javax.swing.JTextField jTextFieldHa0;
    private javax.swing.JTextField jTextFieldHb0;
    private javax.swing.JTextField jTextFieldHc0;
    private javax.swing.JTextField jTextFieldHi0;
    private javax.swing.JTextField jTextFieldHj0;
    private javax.swing.JTextField jTextFieldHk0;
    private javax.swing.JTextField jTextFieldNii;
    private javax.swing.JTextField jTextFieldT0;
    private javax.swing.JTextField jTextFieldbigstep;
    private javax.swing.JTextField jTextFielddeltah;
    private javax.swing.JTextField jTextFielddeltak;
    private javax.swing.JTextField jTextFielddeltal;
    private javax.swing.JTextField jTextFieldhmax;
    private javax.swing.JTextField jTextFieldhmin;
    private javax.swing.JTextField jTextFieldkmax;
    private javax.swing.JTextField jTextFieldkmin;
    private javax.swing.JTextField jTextFieldlmax;
    private javax.swing.JTextField jTextFieldlmin;
    private javax.swing.JTextField jTextFieldmaxQ;
    private javax.swing.JTextField jTextFieldmaxnofhkls;
    private javax.swing.JTextField jTextFieldmaxnofmfloops;
    private javax.swing.JTextField jTextFieldmaxnofspins;
    private javax.swing.JTextField jTextFieldmaxnoftestspincf;
    private javax.swing.JTextField jTextFieldmaxqperiod;
    private javax.swing.JTextField jTextFieldmaxspinchange;
    private javax.swing.JTextField jTextFieldmaxstamf;
    private javax.swing.JTextField jTextFieldnofrndtries;
    private javax.swing.JTextField jTextFieldnofrndtries1;
    private javax.swing.JTextField jTextFieldnofspincorrs;
    private javax.swing.JTextField jTextFieldrepeat;
    private javax.swing.JTextField jTextFields10;
    private javax.swing.JTextField jTextFields20;
    private javax.swing.JTextField jTextFields30;
    private javax.swing.JTextField jTextFields40;
    private javax.swing.JTextField jTextFields50;
    private javax.swing.JTextField jTextFields60;
    private javax.swing.JTextField jTextFieldsNij;
    private javax.swing.JTextField jTextFieldsNik;
    private javax.swing.JTextField jTextFieldsNjj;
    private javax.swing.JTextField jTextFieldsNjk;
    private javax.swing.JTextField jTextFieldsNkk;
    private javax.swing.JTextField jTextFieldxEa;
    private javax.swing.JTextField jTextFieldxEb;
    private javax.swing.JTextField jTextFieldxEc;
    private javax.swing.JTextField jTextFieldxEi;
    private javax.swing.JTextField jTextFieldxEj;
    private javax.swing.JTextField jTextFieldxEk;
    private javax.swing.JTextField jTextFieldxHa;
    private javax.swing.JTextField jTextFieldxHb;
    private javax.swing.JTextField jTextFieldxHc;
    private javax.swing.JTextField jTextFieldxHi;
    private javax.swing.JTextField jTextFieldxHj;
    private javax.swing.JTextField jTextFieldxHk;
    private javax.swing.JTextField jTextFieldxT;
    private javax.swing.JTextField jTextFieldxmax;
    private javax.swing.JTextField jTextFieldxmin;
    private javax.swing.JTextField jTextFieldxs1;
    private javax.swing.JTextField jTextFieldxs2;
    private javax.swing.JTextField jTextFieldxs3;
    private javax.swing.JTextField jTextFieldxs4;
    private javax.swing.JTextField jTextFieldxs5;
    private javax.swing.JTextField jTextFieldxs6;
    private javax.swing.JTextField jTextFieldxstep;
    private javax.swing.JTextField jTextFieldyEa;
    private javax.swing.JTextField jTextFieldyEb;
    private javax.swing.JTextField jTextFieldyEc;
    private javax.swing.JTextField jTextFieldyEi;
    private javax.swing.JTextField jTextFieldyEj;
    private javax.swing.JTextField jTextFieldyEk;
    private javax.swing.JTextField jTextFieldyHa;
    private javax.swing.JTextField jTextFieldyHb;
    private javax.swing.JTextField jTextFieldyHc;
    private javax.swing.JTextField jTextFieldyHi;
    private javax.swing.JTextField jTextFieldyHj;
    private javax.swing.JTextField jTextFieldyHk;
    private javax.swing.JTextField jTextFieldyT;
    private javax.swing.JTextField jTextFieldymax;
    private javax.swing.JTextField jTextFieldymin;
    private javax.swing.JTextField jTextFieldys1;
    private javax.swing.JTextField jTextFieldys2;
    private javax.swing.JTextField jTextFieldys3;
    private javax.swing.JTextField jTextFieldys4;
    private javax.swing.JTextField jTextFieldys5;
    private javax.swing.JTextField jTextFieldys6;
    private javax.swing.JTextField jTextFieldystep;
    // End of variables declaration//GEN-END:variables
}
