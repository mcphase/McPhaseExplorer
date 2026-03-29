package org.mcphase.par;

import java.awt.Component;
import java.awt.event.ItemEvent;
import javax.swing.JComboBox;
import javax.swing.ToolTipManager;
import org.mcphase.base.RegexVerifier;
import org.mcphase.base.VisualEditorBase;
import org.openide.text.DataEditorSupport;

/**
 *
 * @author Till Hoffmann
 */
public class parEditor extends VisualEditorBase {
  String hklline ; Integer nofhkllines=0;
    /** Creates new form parEditor */
    public parEditor(DataEditorSupport support) {
        super(support, "par Editor");
        initComponents();
        
        ToolTipManager.sharedInstance().setInitialDelay(0);
        ToolTipManager.sharedInstance().setDismissDelay(5000);
       
        String[] outputCols = {
                  "0....Qinc[1/A]", 
                  "1....Qx[1/A]",   
                  "2....Qy[1/A]",   
                  "3....Qz[1/A]",   
                  "4....T[K]",      
                  "5....Ha[T]",     
                  "6....Hb[T]",     
                  "7....Hc[T]",     
                  "8....|Q|[1/A]",  
                  "9....hprim" , 
                  "10....kprim",  
                  "11....lprim",  
                  "12....h", 
                  "13....k", 
                  "14....l", 
                  "15....Hi[T]",   
                  "16....Hj[T]",   
                  "17....Hk[T]",   
                  "18....Ea[kV/mm]", 
                  "19....Eb[kV/mm]", 
                  "20....Ec[kV/mm]", 
                  "21....Ei[kV/mm]", 
                  "22....Ej[kV/mm]", 
                  "23....Ek[kV/mm]", 
                  "24....s1[GPa]", 
                  "25....s2[GPa]", 
                  "26....s3[GPa]", 
                  "27....s4[GPa]", 
                  "28....s5[GPa]", 
                  "29....s6[GPa]",
                  "30....Qindex"};
        
         for (String s : outputCols) {
             jComboBoxout1.addItem(s);
             jComboBoxout2.addItem(s);
             jComboBoxout3.addItem(s);
             jComboBoxout4.addItem(s);
             jComboBoxout5.addItem(s);
             jComboBoxout6.addItem(s);
             jComboBoxout7.addItem(s);
             jComboBoxout8.addItem(s);
        }
        String[] Souts = {
                  "0:no_output_of_Sab",
                  "1:Sperpab(Q,omega)_in_dipole_approximation,_with_a,b=x,y,z",
                  "2:Sperpab(Q,omega)_going_beyond_dipole_approximation_(if_possible),_with_a,b=x,y,z",
                  "3:Sperpab(Q,omega)_in_dipole_approximation,_with_a,b=u,v,w",
                  "4:Sperpab(Q,omega)_going_beyond_dipole_approximation_(if_possible),_with_a,b=u,v,w",
                  "5:Sab(Q,omega)_in_dipole_approximation,_with_a,b=x,y,z_(no_output_of_dip_intensity)",
                  "6:Sab(Q,omega)_in_dipole_approximation,_with_a,b=u,v,w_(no_output_of_dip_intensity)"
                    };
                
 for (String s : Souts) {jComboBoxoutS.addItem(s);}
        
      // Convention
      // 1) normal variable "ki"
      // 2) set of variables in one line ":hmin:hmax:deltah" (deltah is the variable which is registered)
      // 3) set of variables in one line with common name,  "hklline:h1:k1:l1:hN:kN:lN:Nstp:k1"  (for k1)
      // ad 3) more than one such variable is possible in one mcdisp.par file
      
        // <editor-fold defaultstate="collapsed" desc="Component registration">
        registerComponent("ki", jTextFieldki);
        registerComponent("kf", jTextFieldkf);
        registerComponent("emin", jTextFieldemin);
        registerComponent("emax", jTextFieldemax);
        
        registerComponent(":hmin:hmax:deltah:hmin", jTextFieldhmin);
        registerComponent(":hmin:hmax:deltah:hmax", jTextFieldhmax);
        registerComponent(":hmin:hmax:deltah:deltah", jTextFielddeltah);

        registerComponent(":kmin:kmax:deltak:kmin", jTextFieldkmin);
        registerComponent(":kmin:kmax:deltak:kmax", jTextFieldkmax);
        registerComponent(":kmin:kmax:deltak:deltak", jTextFielddeltak);

        registerComponent(":lmin:lmax:deltal:lmin", jTextFieldlmin);
        registerComponent(":lmin:lmax:deltal:lmax", jTextFieldlmax);
        registerComponent(":lmin:lmax:deltal:deltal", jTextFielddeltal);

        registerComponent(":Qxmin:Qxmax:deltaQx:Qxmin", jTextFieldQxmin);
        registerComponent(":Qxmin:Qxmax:deltaQx:Qxmax", jTextFieldQxmax);
        registerComponent(":Qxmin:Qxmax:deltaQx:deltaQx", jTextFielddeltaQx);

        registerComponent(":Qymin:Qymax:deltaQy:Qymin", jTextFieldQymin);
        registerComponent(":Qymin:Qymax:deltaQy:Qymax", jTextFieldQymax);
        registerComponent(":Qymin:Qymax:deltaQy:deltaQy", jTextFielddeltaQy);

        registerComponent(":Qzmin:Qzmax:deltaQz:Qzmin", jTextFieldQzmin);
        registerComponent(":Qzmin:Qzmax:deltaQz:Qzmax", jTextFieldQzmax);
        registerComponent(":Qzmin:Qzmax:deltaQz:deltaQz", jTextFielddeltaQz);
        
        registerComponent("hklfile", jTextFieldhklfile);
        registerComponent("QxQyQzfile", jTextFieldQxQyQzfile);
        
        registerComponent("hklline:h1:k1:l1:hN:kN:lN:Nstp:h1", jTextFieldh1);
        registerComponent("hklline:h1:k1:l1:hN:kN:lN:Nstp:k1", jTextFieldk1);
        registerComponent("hklline:h1:k1:l1:hN:kN:lN:Nstp:l1", jTextFieldl1);
        registerComponent("hklline:h1:k1:l1:hN:kN:lN:Nstp:hN", jTextFieldhN);
        registerComponent("hklline:h1:k1:l1:hN:kN:lN:Nstp:kN", jTextFieldkN);
        registerComponent("hklline:h1:k1:l1:hN:kN:lN:Nstp:lN", jTextFieldlN);
        registerComponent("hklline:h1:k1:l1:hN:kN:lN:Nstp:Nstp", jTextFieldNstp);

        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:h0", jTextFieldh0);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:k0", jTextFieldk0);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:l0", jTextFieldl0);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:hN", jTextFieldh3);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:kN", jTextFieldk3);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:lN", jTextFieldl3);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:hM", jTextFieldh4);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:kM", jTextFieldk4);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:lM", jTextFieldl4);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:Nstp", jTextFieldNstp3);
        registerComponent("hklplane:h0:k0:l0:hN:kN:lN:Nstp:hM:kM:lM:Mstp:Mstp", jTextFieldNstp4);

        
        registerComponent("QxQyQzline:Qx1:Qy1:Qz1:QxN:QyN:QzN:Nstp:Qx1", jTextFieldQx1);
        registerComponent("QxQyQzline:Qx1:Qy1:Qz1:QxN:QyN:QzN:Nstp:Qy1", jTextFieldQy1);
        registerComponent("QxQyQzline:Qx1:Qy1:Qz1:QxN:QyN:QzN:Nstp:Qz1", jTextFieldQz1);
        registerComponent("QxQyQzline:Qx1:Qy1:Qz1:QxN:QyN:QzN:Nstp:QxN", jTextFieldQxN);
        registerComponent("QxQyQzline:Qx1:Qy1:Qz1:QxN:QyN:QzN:Nstp:QyN", jTextFieldQyN);
        registerComponent("QxQyQzline:Qx1:Qy1:Qz1:QxN:QyN:QzN:Nstp:QzN", jTextFieldQzN);
        registerComponent("QxQyQzline:Qx1:Qy1:Qz1:QxN:QyN:QzN:Nstp:Nstp", jTextFieldQNstp);

        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:Qx0", jTextFieldQx0);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:Qy0", jTextFieldQy0);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:Qz0", jTextFieldQz0);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:QxN", jTextFieldQx3);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:QyN", jTextFieldQy3);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:QzN", jTextFieldQz3);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:QxM", jTextFieldQx4);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:QyM", jTextFieldQy4);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:QzM", jTextFieldQz4);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:Nstp", jTextFieldQNstp3);
        registerComponent("QxQyQzplane:Qx0:Qy0:Qz0:QxN:QyN:QzN:Nstp:QxM:QyM:QzM:Mstp:Mstp", jTextFieldQNstp4);
        
        
        registerComponent("calculate_magmoment_oscillation", jCheckBoxcalculate_magmoment_oscillation);
        registerComponent("calculate_spinmoment_oscillation", jCheckBoxcalculate_spinmoment_oscillation);
        registerComponent("calculate_orbmoment_oscillation", jCheckBoxcalculate_orbmoment_oscillation);
        registerComponent("calculate_chargedensity_oscillation", jCheckBoxcalculate_chargedensity_oscillation);
        registerComponent("calculate_spindensity_oscillation", jCheckBoxcalculate_spindensity_oscillation);
        registerComponent("calculate_orbmomdensity_oscillation", jCheckBoxcalculate_orbmomdensity_oscillation);
        registerComponent("calculate_phonon_oscillation", jCheckBoxcalculate_phonon_oscillation);
        registerComponent("calculate_pel_oscillation", jCheckBoxcalculate_pel_oscillation);

        registerComponent("out1", jComboBoxout1);
        registerComponent("out2", jComboBoxout2);
        registerComponent("out3", jComboBoxout3);
        registerComponent("out4", jComboBoxout4);
        registerComponent("out5", jComboBoxout5);
        registerComponent("out6", jComboBoxout6);
        registerComponent("out7", jComboBoxout7);
        registerComponent("out8", jComboBoxout8);
        
        registerComponent("outS", jComboBoxoutS);
        
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Verifiers">
        jTextFieldki.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldkf.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldemin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldemax.setInputVerifier(RegexVerifier.getScientificVerifier());
        
        jTextFieldhmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldhmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltah.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldkmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldkmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltak.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldlmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldlmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltal.setInputVerifier(RegexVerifier.getScientificVerifier());
        
        jTextFieldQxmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQxmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltaQx.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQymin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQymax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltaQy.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQzmin.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQzmax.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFielddeltaQz.setInputVerifier(RegexVerifier.getScientificVerifier());
        
        jComboBoxout1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jComboBoxout2.setInputVerifier(RegexVerifier.getScientificVerifier());
        jComboBoxout3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jComboBoxout4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jComboBoxout5.setInputVerifier(RegexVerifier.getScientificVerifier());
        jComboBoxout6.setInputVerifier(RegexVerifier.getScientificVerifier());
        jComboBoxout7.setInputVerifier(RegexVerifier.getScientificVerifier());
        jComboBoxoutS.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQx0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQy0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQz0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQx1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQy1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQz1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQx3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQy3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQz3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQx4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQy4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQz4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQxN.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQyN.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQzN.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQNstp3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQNstp4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldQNstp.setInputVerifier(RegexVerifier.getScientificVerifier());
        
        jTextFieldh0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldk0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldl0.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldh1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldk1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldl1.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldh3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldk3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldl3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldh4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldk4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldl4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldhN.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldkN.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldlN.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldNstp3.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldNstp4.setInputVerifier(RegexVerifier.getScientificVerifier());
        jTextFieldNstp.setInputVerifier(RegexVerifier.getScientificVerifier());
        
        //</editor-fold>
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane = new javax.swing.JScrollPane();
        jPanel = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jCheckBoxcalculate_magmoment_oscillation = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jCheckBoxcalculate_spinmoment_oscillation = new javax.swing.JCheckBox();
        jCheckBoxcalculate_orbmoment_oscillation = new javax.swing.JCheckBox();
        jCheckBoxcalculate_chargedensity_oscillation = new javax.swing.JCheckBox();
        jCheckBoxcalculate_spindensity_oscillation = new javax.swing.JCheckBox();
        jCheckBoxcalculate_orbmomdensity_oscillation = new javax.swing.JCheckBox();
        jCheckBoxcalculate_phonon_oscillation = new javax.swing.JCheckBox();
        jCheckBoxcalculate_pel_oscillation = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxoutS = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxout1 = new javax.swing.JComboBox<>();
        jComboBoxout2 = new javax.swing.JComboBox<>();
        jComboBoxout3 = new javax.swing.JComboBox<>();
        jComboBoxout4 = new javax.swing.JComboBox<>();
        jComboBoxout5 = new javax.swing.JComboBox<>();
        jComboBoxout6 = new javax.swing.JComboBox<>();
        jComboBoxout7 = new javax.swing.JComboBox<>();
        jComboBoxout8 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabelki = new javax.swing.JLabel();
        jTextFieldki = new javax.swing.JTextField();
        jLabelkf = new javax.swing.JLabel();
        jTextFieldkf = new javax.swing.JTextField();
        jLabelkiemin = new javax.swing.JLabel();
        jTextFieldemin = new javax.swing.JTextField();
        jTextFieldemax = new javax.swing.JTextField();
        jLabelkfemax = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldhmin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldhmax = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFielddeltah = new javax.swing.JTextField();
        jTextFielddeltak = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldkmax = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldkmin = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldlmin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldlmax = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextFielddeltal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldQxmin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextFieldQxmax = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jTextFielddeltaQx = new javax.swing.JTextField();
        jTextFielddeltaQy = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextFieldQymax = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldQymin = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextFieldQzmin = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jTextFieldQzmax = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextFielddeltaQz = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextFieldhklfile = new javax.swing.JTextField();
        jTextFieldQxQyQzfile = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jTextFieldh1 = new javax.swing.JTextField();
        jTextFieldk1 = new javax.swing.JTextField();
        jTextFieldl1 = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jTextFieldhN = new javax.swing.JTextField();
        jTextFieldkN = new javax.swing.JTextField();
        jTextFieldlN = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jTextFieldNstp = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jTextFieldQx1 = new javax.swing.JTextField();
        jTextFieldQy1 = new javax.swing.JTextField();
        jTextFieldQz1 = new javax.swing.JTextField();
        jTextFieldQxN = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextFieldQyN = new javax.swing.JTextField();
        jTextFieldQzN = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jTextFieldQNstp = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jTextFieldh0 = new javax.swing.JTextField();
        jTextFieldk0 = new javax.swing.JTextField();
        jTextFieldl0 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jTextFieldh3 = new javax.swing.JTextField();
        jTextFieldk3 = new javax.swing.JTextField();
        jTextFieldl3 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        jTextFieldNstp3 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jTextFieldh4 = new javax.swing.JTextField();
        jTextFieldk4 = new javax.swing.JTextField();
        jTextFieldl4 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jTextFieldNstp4 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jTextFieldQx0 = new javax.swing.JTextField();
        jTextFieldQy0 = new javax.swing.JTextField();
        jTextFieldQz0 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jTextFieldQx3 = new javax.swing.JTextField();
        jTextFieldQy3 = new javax.swing.JTextField();
        jTextFieldQz3 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jTextFieldQNstp3 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jTextFieldQx4 = new javax.swing.JTextField();
        jTextFieldQy4 = new javax.swing.JTextField();
        jTextFieldQz4 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jTextFieldQNstp4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jScrollPane.setMinimumSize(new java.awt.Dimension(330, 23));
        jScrollPane.setPreferredSize(new java.awt.Dimension(330, 306));

        jPanel.setBackground(new java.awt.Color(51, 51, 255));
        jPanel.setMinimumSize(new java.awt.Dimension(410, 539));
        jPanel.setLayout(null);

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jCheckBoxcalculate_magmoment_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_magmoment_oscillation.text")); // NOI18N

        jLabel1.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel1.text")); // NOI18N

        jCheckBoxcalculate_spinmoment_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_spinmoment_oscillation.text")); // NOI18N

        jCheckBoxcalculate_orbmoment_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_orbmoment_oscillation.text")); // NOI18N

        jCheckBoxcalculate_chargedensity_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_chargedensity_oscillation.text")); // NOI18N

        jCheckBoxcalculate_spindensity_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_spindensity_oscillation.text")); // NOI18N

        jCheckBoxcalculate_orbmomdensity_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_orbmomdensity_oscillation.text")); // NOI18N

        jCheckBoxcalculate_phonon_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_phonon_oscillation.text")); // NOI18N

        jCheckBoxcalculate_pel_oscillation.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jCheckBoxcalculate_pel_oscillation.text")); // NOI18N

        jLabel4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel4.text")); // NOI18N

        jComboBoxoutS.setToolTipText("<html>xyz coordinate refer to y||b, z||(a x b) and x normal to y and z <br>\n uvw coordinates refer to u||Q=k-k', w perpendicular to the scattering plane <br>\n     (as determined by the cross product of subsequent vectors in the input<br>\n     q-vector list) and v perpendicular to u and w, such that uvw form a righthanded system<br>\n");

        jLabel2.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBoxcalculate_pel_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxcalculate_phonon_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxcalculate_orbmomdensity_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxcalculate_spindensity_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxcalculate_chargedensity_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxcalculate_orbmoment_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxcalculate_spinmoment_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel1))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(27, 27, 27)
                                    .addComponent(jCheckBoxcalculate_magmoment_oscillation, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jComboBoxout1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxout2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxout3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxout4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxout5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxout6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxout7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxout8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxoutS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))))
                .addContainerGap(264, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxcalculate_magmoment_oscillation)
                        .addGap(8, 8, 8)
                        .addComponent(jCheckBoxcalculate_spinmoment_oscillation)
                        .addGap(14, 14, 14)
                        .addComponent(jCheckBoxcalculate_orbmoment_oscillation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxcalculate_chargedensity_oscillation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxcalculate_spindensity_oscillation)
                        .addGap(8, 8, 8)
                        .addComponent(jCheckBoxcalculate_orbmomdensity_oscillation)
                        .addGap(8, 8, 8)
                        .addComponent(jCheckBoxcalculate_phonon_oscillation))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxout1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxout2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addComponent(jComboBoxout3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxout4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxout6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jComboBoxout7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxcalculate_pel_oscillation))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jComboBoxout8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxoutS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(232, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel1.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabelki.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabelki.text")); // NOI18N

        jTextFieldki.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldki.text")); // NOI18N
        jTextFieldki.setToolTipText("<html> depending on what is kept constant it follows either kf or ki (1/A) <br>\nfor neutrons: E=(hbar k)^2/2m_n=81.8meV/lambda(A)^2=2.072meV (k(1/A))^2 ...  k(1/A)=sqrt(0.483*E(meV))<br>\n for X-rays:   E=c hbar k   =1.24e06 meV/lambda(A)=1973202 meV k(1/A)    ...  k(1/A)=5.0679e-7*E(meV)\n");

        jLabelkf.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabelkf.text")); // NOI18N

        jTextFieldkf.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldkf.text")); // NOI18N
        jTextFieldkf.setToolTipText("<html> depending on what is kept constant it follows either kf or ki (1/A) <br>\nfor neutrons: E=(hbar k)^2/2m_n=81.8meV/lambda(A)^2=2.072meV (k(1/A))^2 ...  k(1/A)=sqrt(0.483*E(meV))<br>\n for X-rays:   E=c hbar k   =1.24e06 meV/lambda(A)=1973202 meV k(1/A)    ...  k(1/A)=5.0679e-7*E(meV)<br>\n");

        jLabelkiemin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabelkiemin.text")); // NOI18N

        jTextFieldemin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldemin.text")); // NOI18N
        jTextFieldemin.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldemin.toolTipText")); // NOI18N

        jTextFieldemax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldemax.text")); // NOI18N
        jTextFieldemax.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldemax.toolTipText")); // NOI18N

        jLabelkfemax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabelkfemax.text")); // NOI18N

        jLabel5.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel5.text")); // NOI18N
        jLabel5.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel5.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldhmin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.text")); // NOI18N
        jTextFieldhmin.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel6.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel6.text")); // NOI18N
        jLabel6.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel6.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldhmax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmax.text")); // NOI18N
        jTextFieldhmax.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel7.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel7.text")); // NOI18N
        jLabel7.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel7.setMinimumSize(new java.awt.Dimension(10, 14));
        jLabel7.setPreferredSize(new java.awt.Dimension(15, 14));

        jTextFielddeltah.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFielddeltah.text")); // NOI18N
        jTextFielddeltah.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jTextFielddeltak.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFielddeltak.text")); // NOI18N
        jTextFielddeltak.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel8.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel8.text")); // NOI18N

        jTextFieldkmax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldkmax.text")); // NOI18N
        jTextFieldkmax.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel9.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel9.text")); // NOI18N

        jTextFieldkmin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldkmin.text")); // NOI18N
        jTextFieldkmin.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel10.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel10.text")); // NOI18N

        jLabel11.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel11.text")); // NOI18N

        jTextFieldlmin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldlmin.text")); // NOI18N
        jTextFieldlmin.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel12.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel12.text")); // NOI18N

        jTextFieldlmax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldlmax.text")); // NOI18N
        jTextFieldlmax.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel13.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel13.text")); // NOI18N

        jTextFielddeltal.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFielddeltal.text")); // NOI18N
        jTextFielddeltal.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhmin.toolTipText")); // NOI18N

        jLabel14.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel14.text")); // NOI18N
        jLabel14.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel14.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldQxmin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.text")); // NOI18N
        jTextFieldQxmin.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel15.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel15.text")); // NOI18N
        jLabel15.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel15.setMinimumSize(new java.awt.Dimension(10, 14));

        jTextFieldQxmax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmax.text")); // NOI18N
        jTextFieldQxmax.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel16.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel16.text")); // NOI18N
        jLabel16.setMaximumSize(new java.awt.Dimension(15, 14));
        jLabel16.setMinimumSize(new java.awt.Dimension(10, 14));
        jLabel16.setPreferredSize(new java.awt.Dimension(15, 14));

        jTextFielddeltaQx.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFielddeltaQx.text")); // NOI18N
        jTextFielddeltaQx.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jTextFielddeltaQy.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFielddeltaQy.text")); // NOI18N
        jTextFielddeltaQy.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel17.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel17.text")); // NOI18N

        jTextFieldQymax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQymax.text")); // NOI18N
        jTextFieldQymax.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel18.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel18.text")); // NOI18N

        jTextFieldQymin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQymin.text")); // NOI18N
        jTextFieldQymin.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel19.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel19.text")); // NOI18N

        jLabel20.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel20.text")); // NOI18N

        jTextFieldQzmin.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQzmin.text")); // NOI18N
        jTextFieldQzmin.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel21.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel21.text")); // NOI18N

        jTextFieldQzmax.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQzmax.text")); // NOI18N
        jTextFieldQzmax.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel22.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel22.text")); // NOI18N

        jTextFielddeltaQz.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFielddeltaQz.text")); // NOI18N
        jTextFielddeltaQz.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxmin.toolTipText")); // NOI18N

        jLabel23.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel23.text")); // NOI18N

        jLabel24.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel24.text")); // NOI18N

        jLabel25.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel25.text")); // NOI18N

        jTextFieldhklfile.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhklfile.text")); // NOI18N
        jTextFieldhklfile.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhklfile.toolTipText")); // NOI18N

        jTextFieldQxQyQzfile.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxQyQzfile.text")); // NOI18N
        jTextFieldQxQyQzfile.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxQyQzfile.toolTipText")); // NOI18N

        jLabel26.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel26.text")); // NOI18N

        jLabel27.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel27.text")); // NOI18N

        jLabel28.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel28.text")); // NOI18N

        jLabel29.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel29.text")); // NOI18N

        jTextFieldh1.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.text")); // NOI18N
        jTextFieldh1.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jTextFieldk1.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldk1.text")); // NOI18N
        jTextFieldk1.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jTextFieldl1.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldl1.text")); // NOI18N
        jTextFieldl1.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jLabel30.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel30.text")); // NOI18N
        jLabel30.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jTextFieldhN.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldhN.text")); // NOI18N
        jTextFieldhN.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jTextFieldkN.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldkN.text")); // NOI18N
        jTextFieldkN.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jTextFieldlN.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldlN.text")); // NOI18N
        jTextFieldlN.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jLabel31.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel31.text")); // NOI18N
        jLabel31.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jTextFieldNstp.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldNstp.text")); // NOI18N
        jTextFieldNstp.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh1.toolTipText")); // NOI18N

        jLabel32.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel32.text")); // NOI18N

        jTextFieldQx1.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.text")); // NOI18N
        jTextFieldQx1.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jTextFieldQy1.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy1.text")); // NOI18N
        jTextFieldQy1.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jTextFieldQz1.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQz1.text")); // NOI18N
        jTextFieldQz1.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jTextFieldQxN.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQxN.text")); // NOI18N
        jTextFieldQxN.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jLabel33.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel33.text")); // NOI18N
        jLabel33.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jTextFieldQyN.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQyN.text")); // NOI18N
        jTextFieldQyN.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jTextFieldQzN.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQzN.text")); // NOI18N
        jTextFieldQzN.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jLabel34.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel34.text")); // NOI18N
        jLabel34.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jTextFieldQNstp.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQNstp.text")); // NOI18N
        jTextFieldQNstp.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx1.toolTipText")); // NOI18N

        jLabel36.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel36.text")); // NOI18N

        jTextFieldh0.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh0.text")); // NOI18N
        jTextFieldh0.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh0.toolTipText")); // NOI18N

        jTextFieldk0.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldk0.text")); // NOI18N
        jTextFieldk0.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldk0.toolTipText")); // NOI18N

        jTextFieldl0.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldl0.text")); // NOI18N
        jTextFieldl0.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldl0.toolTipText")); // NOI18N

        jLabel37.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel37.text")); // NOI18N
        jLabel37.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel37.toolTipText")); // NOI18N

        jTextFieldh3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh3.text")); // NOI18N
        jTextFieldh3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh3.toolTipText")); // NOI18N

        jTextFieldk3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldk3.text")); // NOI18N
        jTextFieldk3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldk3.toolTipText")); // NOI18N

        jTextFieldl3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldl3.text")); // NOI18N
        jTextFieldl3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldl3.toolTipText")); // NOI18N

        jLabel38.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel38.text")); // NOI18N
        jLabel38.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel38.toolTipText")); // NOI18N

        jTextFieldNstp3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldNstp3.text")); // NOI18N
        jTextFieldNstp3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldNstp3.toolTipText")); // NOI18N

        jLabel39.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel39.text")); // NOI18N
        jLabel39.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel39.toolTipText")); // NOI18N

        jTextFieldh4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh4.text")); // NOI18N
        jTextFieldh4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldh4.toolTipText")); // NOI18N

        jTextFieldk4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldk4.text")); // NOI18N
        jTextFieldk4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldk4.toolTipText")); // NOI18N

        jTextFieldl4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldl4.text")); // NOI18N
        jTextFieldl4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldl4.toolTipText")); // NOI18N

        jLabel40.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel40.text")); // NOI18N
        jLabel40.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel40.toolTipText")); // NOI18N

        jTextFieldNstp4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldNstp4.text")); // NOI18N
        jTextFieldNstp4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldNstp4.toolTipText")); // NOI18N

        jLabel41.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel41.text")); // NOI18N
        jLabel41.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQx0.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx0.text")); // NOI18N
        jTextFieldQx0.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQy0.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy0.text")); // NOI18N
        jTextFieldQy0.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQz0.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQz0.text")); // NOI18N
        jTextFieldQz0.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jLabel42.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel42.text")); // NOI18N
        jLabel42.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQx3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx3.text")); // NOI18N
        jTextFieldQx3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQy3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.text")); // NOI18N
        jTextFieldQy3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQz3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQz3.text")); // NOI18N
        jTextFieldQz3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jLabel43.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel43.text")); // NOI18N
        jLabel43.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQNstp3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQNstp3.text")); // NOI18N
        jTextFieldQNstp3.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jLabel44.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel44.text")); // NOI18N
        jLabel44.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQx4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQx4.text")); // NOI18N
        jTextFieldQx4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQy4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy4.text")); // NOI18N
        jTextFieldQy4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQz4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQz4.text")); // NOI18N
        jTextFieldQz4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jLabel45.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel45.text")); // NOI18N
        jLabel45.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        jTextFieldQNstp4.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQNstp4.text")); // NOI18N
        jTextFieldQNstp4.setToolTipText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jTextFieldQy3.toolTipText")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabelki)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldki, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelkf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldkf, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelkiemin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldemin, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelkfemax)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldemax, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldhmin, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldkmin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldlmin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldhmax, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldkmax, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldlmax, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFielddeltak, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFielddeltah, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFielddeltal, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQzmin, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQzmax, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFielddeltaQz, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldQxmin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldQxmax, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFielddeltaQx, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldQymin, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldQymax, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFielddeltaQy, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldhklfile, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldQxQyQzfile, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel32)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQx1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQy1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQz1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel33)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQxN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQyN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQzN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel34)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQNstp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel28)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldh1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldk1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldl1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldhN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldkN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldlN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel31)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldNstp, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel39)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldh4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldk4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldl4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel40)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldNstp4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel36)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldh0, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldk0, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldl0, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel37)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldh3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldk3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldl3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel38)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldNstp3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel44)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQx4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQy4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQz4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel45)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQNstp4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel41)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQx0, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQy0, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQz0, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel42)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQx3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQy3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQz3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel43)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldQNstp3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(293, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelki)
                    .addComponent(jTextFieldki, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelkf)
                    .addComponent(jTextFieldkf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelkiemin)
                    .addComponent(jTextFieldemin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelkfemax)
                    .addComponent(jTextFieldemax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldhmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldkmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextFieldlmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldhmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFielddeltah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldkmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFielddeltak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldlmax)
                            .addComponent(jLabel13)
                            .addComponent(jTextFielddeltal))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQxmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQxmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFielddeltaQx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextFieldQymin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jTextFieldQymax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jTextFielddeltaQy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFielddeltaQz)
                            .addComponent(jLabel22)
                            .addComponent(jTextFieldQzmax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21)
                            .addComponent(jTextFieldQzmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12))
                    .addComponent(jLabel20))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(jTextFieldhklfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQxQyQzfile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jTextFieldh1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldk1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30)
                    .addComponent(jTextFieldhN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldkN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldlN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jTextFieldNstp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(jTextFieldQx1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQy1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQz1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33)
                    .addComponent(jTextFieldQxN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQyN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQzN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jTextFieldQNstp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextFieldh0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldk0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldl0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jTextFieldh3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldk3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jTextFieldNstp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jTextFieldh4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldk4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(jTextFieldNstp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jTextFieldQx0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQy0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQz0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel42)
                    .addComponent(jTextFieldQx3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQy3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQz3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(jTextFieldQNstp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(jTextFieldQx4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQy4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQz4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(jTextFieldQNstp4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jPanel1.TabConstraints.tabTitle"), jPanel1); // NOI18N

        jPanel.add(jTabbedPane1);
        jTabbedPane1.setBounds(50, 10, 980, 600);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/mcphase/par/kuku.jpg"))); // NOI18N
        jLabel3.setText(org.openide.util.NbBundle.getMessage(parEditor.class, "parEditor.jLabel3.text")); // NOI18N
        jPanel.add(jLabel3);
        jLabel3.setBounds(0, 470, 50, 70);

        jScrollPane.setViewportView(jPanel);

        add(jScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxcalculate_chargedensity_oscillation;
    private javax.swing.JCheckBox jCheckBoxcalculate_magmoment_oscillation;
    private javax.swing.JCheckBox jCheckBoxcalculate_orbmomdensity_oscillation;
    private javax.swing.JCheckBox jCheckBoxcalculate_orbmoment_oscillation;
    private javax.swing.JCheckBox jCheckBoxcalculate_pel_oscillation;
    private javax.swing.JCheckBox jCheckBoxcalculate_phonon_oscillation;
    private javax.swing.JCheckBox jCheckBoxcalculate_spindensity_oscillation;
    private javax.swing.JCheckBox jCheckBoxcalculate_spinmoment_oscillation;
    private javax.swing.JComboBox<String> jComboBoxout1;
    private javax.swing.JComboBox<String> jComboBoxout2;
    private javax.swing.JComboBox<String> jComboBoxout3;
    private javax.swing.JComboBox<String> jComboBoxout4;
    private javax.swing.JComboBox<String> jComboBoxout5;
    private javax.swing.JComboBox<String> jComboBoxout6;
    private javax.swing.JComboBox<String> jComboBoxout7;
    private javax.swing.JComboBox<String> jComboBoxout8;
    private javax.swing.JComboBox<String> jComboBoxoutS;
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
    private javax.swing.JLabel jLabel36;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelkf;
    private javax.swing.JLabel jLabelkfemax;
    private javax.swing.JLabel jLabelki;
    private javax.swing.JLabel jLabelkiemin;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldNstp;
    private javax.swing.JTextField jTextFieldNstp3;
    private javax.swing.JTextField jTextFieldNstp4;
    private javax.swing.JTextField jTextFieldQNstp;
    private javax.swing.JTextField jTextFieldQNstp3;
    private javax.swing.JTextField jTextFieldQNstp4;
    private javax.swing.JTextField jTextFieldQx0;
    private javax.swing.JTextField jTextFieldQx1;
    private javax.swing.JTextField jTextFieldQx3;
    private javax.swing.JTextField jTextFieldQx4;
    private javax.swing.JTextField jTextFieldQxN;
    private javax.swing.JTextField jTextFieldQxQyQzfile;
    private javax.swing.JTextField jTextFieldQxmax;
    private javax.swing.JTextField jTextFieldQxmin;
    private javax.swing.JTextField jTextFieldQy0;
    private javax.swing.JTextField jTextFieldQy1;
    private javax.swing.JTextField jTextFieldQy3;
    private javax.swing.JTextField jTextFieldQy4;
    private javax.swing.JTextField jTextFieldQyN;
    private javax.swing.JTextField jTextFieldQymax;
    private javax.swing.JTextField jTextFieldQymin;
    private javax.swing.JTextField jTextFieldQz0;
    private javax.swing.JTextField jTextFieldQz1;
    private javax.swing.JTextField jTextFieldQz3;
    private javax.swing.JTextField jTextFieldQz4;
    private javax.swing.JTextField jTextFieldQzN;
    private javax.swing.JTextField jTextFieldQzmax;
    private javax.swing.JTextField jTextFieldQzmin;
    private javax.swing.JTextField jTextFielddeltaQx;
    private javax.swing.JTextField jTextFielddeltaQy;
    private javax.swing.JTextField jTextFielddeltaQz;
    private javax.swing.JTextField jTextFielddeltah;
    private javax.swing.JTextField jTextFielddeltak;
    private javax.swing.JTextField jTextFielddeltal;
    private javax.swing.JTextField jTextFieldemax;
    private javax.swing.JTextField jTextFieldemin;
    private javax.swing.JTextField jTextFieldh0;
    private javax.swing.JTextField jTextFieldh1;
    private javax.swing.JTextField jTextFieldh3;
    private javax.swing.JTextField jTextFieldh4;
    private javax.swing.JTextField jTextFieldhN;
    private javax.swing.JTextField jTextFieldhklfile;
    private javax.swing.JTextField jTextFieldhmax;
    private javax.swing.JTextField jTextFieldhmin;
    private javax.swing.JTextField jTextFieldk0;
    private javax.swing.JTextField jTextFieldk1;
    private javax.swing.JTextField jTextFieldk3;
    private javax.swing.JTextField jTextFieldk4;
    private javax.swing.JTextField jTextFieldkN;
    private javax.swing.JTextField jTextFieldkf;
    private javax.swing.JTextField jTextFieldki;
    private javax.swing.JTextField jTextFieldkmax;
    private javax.swing.JTextField jTextFieldkmin;
    private javax.swing.JTextField jTextFieldl0;
    private javax.swing.JTextField jTextFieldl1;
    private javax.swing.JTextField jTextFieldl3;
    private javax.swing.JTextField jTextFieldl4;
    private javax.swing.JTextField jTextFieldlN;
    private javax.swing.JTextField jTextFieldlmax;
    private javax.swing.JTextField jTextFieldlmin;
    // End of variables declaration//GEN-END:variables
}
