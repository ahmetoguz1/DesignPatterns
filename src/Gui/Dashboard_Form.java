package Gui;

import DBObjects.Doctor;
import DBObjects.Hospital;
import Gui.GuiManager.GuiManager;
import Patterns.Command.CommandMaker;
import Patterns.Command.GetHospitalsCommand;
import Patterns.Command.GetSelectedClinicDoktorsCommand;
import Patterns.Decorator.AllergyTest;
import Patterns.Decorator.BloodValuesTest;
import Patterns.Decorator.GeneralTest;
import Patterns.Decorator.GeneticDisorder;
import Patterns.Decorator.Test;
import Patterns.Decorator.UrineTest;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmet
 */
public final class Dashboard_Form extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard_Form
     */
    // default border for the menu items
    Border default_border = BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(46, 49, 49));

    // yellow border for the menu items
    Border yellow_border = BorderFactory.createMatteBorder(1, 0, 1, 0, Color.WHITE);

    // create an array of jlabels
    JLabel[] menuLabels = new JLabel[4];

    // create an array of jpanels
    JPanel[] panels = new JPanel[4];

    JCheckBox[] tests = new JCheckBox[4];
    JComboBox[] cbList = new JComboBox[5];

    ArrayList<Hospital> hospitals;

    public Dashboard_Form() {
        initComponents();
        //setExtendedState(JFrame.MAXIMIZED_BOTH); 
        // center this form
        this.setLocationRelativeTo(null);

        // set icons
        jLabel_appLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("../IMAGES/saglik_bakanligi_logo_kucuk.png")));
        jLabel_close.setIcon(new javax.swing.ImageIcon(getClass().getResource("../IMAGES/x.png")));

        // set borders
        // panel logo border
        Border panelBorder = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.lightGray);
        jPanel_logoANDname.setBorder(panelBorder);
        // panel container border
        Border containerBorder = BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(46, 49, 49));
        jPanel_container.setBorder(containerBorder);

        // populate the menuLabels array
        // you can use a for loop to do that
        //menuLabels[0] = jLabel_menuItem1;
        menuLabels[0] = jLabelZiyaretlerim;
        menuLabels[1] = jLabelTahlillerim;
        menuLabels[2] = jLabelCovid;
        menuLabels[3] = jLabelRandevuAl;

        // populate the panels array
        //panels[0] = jPanel_profil;
        panels[0] = jPanelZiyaretlerim;
        panels[1] = jPanelCheckUp;
        panels[2] = jPanelCovid;
        panels[3] = jPanelRandevuAl;
        //panels[5] = jPanelPrice;

        addActionToMenuLabels();

        for (JPanel pnl : panels) {
            pnl.setVisible(false);
        }
        fillJTableZiyaretlerim();
        jPanelZiyaretlerim.setVisible(true);
        jLabelZiyaretlerim.setBackground(Color.white);
        jLabelZiyaretlerim.setForeground(Color.BLACK);
        fillPatientInformation();

        tests[0] = jCheckBoxAlergy;
        tests[1] = jCheckBoxBloodValues;
        tests[2] = jCheckBoxGeneticDisorder;
        tests[3] = jCheckBoxUrine;
        //jPanelPrice.setVisible(false);

        cbList[0] = jComboBoxAppointmentCity;
        cbList[1] = jComboBoxAppointmentIlce;
        cbList[2] = jComboBoxAppointmentHospital;
        cbList[3] = jComboBoxAppointmentClinic;
        cbList[4] = jComboBoxAppointmentDoctorNameSurname;

        actionComboBox();
    }

    public void fillJTableZiyaretlerim() {
        DefaultTableModel model = (DefaultTableModel) jTableMyVisits.getModel();

//        setBackground(new Color(0, 0, 0, 0));
        jTableMyVisits.getTableHeader().setFont(new Font("Seogo UI", Font.BOLD, 15));
        jTableMyVisits.getTableHeader().setOpaque(false);
        jTableMyVisits.getTableHeader().setBackground(new Color(245, 245, 245));
        jTableMyVisits.getTableHeader().setForeground(new Color(0, 0, 0));
//        jTableMyVisits.setBackground(Color.yellow);

        model.setRowCount(0);
        Object[] row = new Object[5];
        for (int i = 0; i < GuiManager.vt.size(); i++) {
            row[0] = GuiManager.vt.get(i).getHospitalName();
            row[1] = GuiManager.vt.get(i).getClinicName();
            row[2] = GuiManager.vt.get(i).getDoctorName();
            row[3] = GuiManager.vt.get(i).getDoctorLastName();
            row[4] = GuiManager.vt.get(i).getDate();
            model.addRow(row);
        }
    }

    public void fillPatientInformation() {
        lblHastaTC.setText(Integer.toString(GuiManager.pt.getPatientTC()));
        lblHastaAdi.setText(GuiManager.pt.getPatientName());
        lblHastaSoyadi.setText(GuiManager.pt.getPatientLastName());
        lblHastaDogumYeri.setText(GuiManager.pt.getPatientBorn());
        lblHastaDogumTarihi.setText(GuiManager.pt.getPatientBirthDate());
        lblHastaAdresi.setText(GuiManager.pt.getPatientAddress());
        lblHastaTelefon.setText(GuiManager.pt.getPatientTel());
        lblHastaEmail.setText(GuiManager.pt.getPatientEmail());
    }

    public void clearAllComboBox(int id) {
        for (int i = id; i < 5; i++) {
            cbList[i].setSelectedIndex(0);
        }
    }

    // create a function to set the label background color
    public void setLabelBackround(JLabel label) {
        // reset labels to their default design
        for (JLabel menuItem : menuLabels) {
            // change the jlabel background color to white
            menuItem.setBackground(new Color(255, 236, 238));
            // change the jlabel Foreground color to blue
            menuItem.setForeground(new Color(87, 89, 98));
        }

        // change the jlabel background color to white
        label.setBackground(new Color(204, 102, 110));
        // change the jlabel Foreground color to blue
        label.setForeground(Color.BLACK);
    }

    // create a function to show the selected panel
    public void showPanel(JPanel panel) {
        // hide panels
        for (JPanel pnl : panels) {
            pnl.setVisible(false);
        }

        // and show only this panel
        panel.setVisible(true);
    }

    public void addActionToMenuLabels() {
        // get labels in the jpanel menu
        Component[] components = jPanel_menu.getComponents();

        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;

                label.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        // change the jlabel background and Foreground
                        setLabelBackround(label);

                        // display the selected panel
                        switch (label.getText().trim()) {
                            /*case "Profil":
                                showPanel(jPanel_profil);
                                break;*/
                            case "Ziyaretlerim":
                                showPanel(jPanelZiyaretlerim);
                                fillJTableZiyaretlerim();
                                // jPanel_users.setBackground(Color.red);
                                break;
                            /*case "Reçetelerim":
                                showPanel(jPanelRecetelerim);
                                // jPanel_products.setBackground(Color.BLUE);
                                break;*/
                            case "Checkup İşlemleri":
                                showPanel(jPanelCheckUp);
                                lblSelectedTests.setText("");
                                lblPrice.setText("");
                                jCheckBoxAlergy.setSelected(false);
                                jCheckBoxBloodValues.setSelected(false);
                                jCheckBoxGeneticDisorder.setSelected(false);
                                jCheckBoxUrine.setSelected(false);
                                // jPanel_settings.setBackground(Color.GRAY);
                                break;
                            case "Covid":
                                showPanel(jPanelCovid);

                                showCovidInformation();
                                // jPanel_contact.setBackground(Color.GREEN);
                                break;
                            case "Randevu Al":
                                showPanel(jPanelRandevuAl);
                                // jPanel_calendar.setBackground(Color.yellow);
                                break;
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                        // set the border to yellow
                        label.setBorder(yellow_border);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        // reset to the default border
                        label.setBorder(default_border);

                    }
                });

            }
        }
    }

    public void showCovidInformation() {
        if (GuiManager.covidStatus) {
            lblCovidInformation.setText("COVID-19 pandemik aşılaması için belirlenen öncelikli gruplar içerisinde yer almaktasınız. Randevu Al sekmesinden randevunuzu alabilirsiniz.");

            lblCovidInformation.setForeground(Color.WHITE);
            lblCovidIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/succes.png")));

            Color c = new Color(76, 175, 80);
            jPanel1.setBackground(c);
        } else {
            lblCovidIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/warning.png"))); // NOI18N
            lblCovidInformation.setText("COVID-19 pandemik aşılaması için belirlenen öncelikli grupta değilsiniz.");
            Color c = new Color(250, 234, 169);
            jPanel1.setBackground(c);
        }
    }

    public ArrayList<Hospital> getHospitals(String il, String status) {
        CommandMaker cm = new CommandMaker();
        //String query = "Select * from Hastaneler where il = " + "'" + il + "'" + "AND ilce = " + "'" + ilce + "'";
        String query = "";
        if (status.equals("hospitals")) {
            query = "Select * from Hastaneler where il = " + "'" + il + "'";
        } else {
            query = "Select * from Hastaneler where il = " + "'" + il + "' Group By(ilce)";
        }
        //System.out.println(query);
        ArrayList<Hospital> hospitals = null;
        hospitals = (ArrayList<Hospital>) cm.makeCommand(new GetHospitalsCommand(), query);
        return hospitals;
    }

    public ArrayList<Doctor> getDoctors(int hospitalID, int clinicID) {

        CommandMaker cm = new CommandMaker();
        //select * from Doktorlar where hastaneID = 1 AND klinikID = 1;
        String query = "Select * from Doktorlar where hastaneID = " + hospitalID + " AND klinikID = " + clinicID;
        ArrayList<Doctor> doctors = null;
        doctors = (ArrayList<Doctor>) cm.makeCommand(new GetSelectedClinicDoktorsCommand(), query);
        return doctors;
    }

    public void actionComboBox() {

        jComboBoxAppointmentCity.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                //jComboBoxAppointmentIlce.setEnabled(true);

                ArrayList<Hospital> hospitals = getHospitals(jComboBoxAppointmentCity.getSelectedItem().toString(), "hospital ilce");

                jComboBoxAppointmentIlce.removeAllItems();
                jComboBoxAppointmentIlce.addItem("-");
                hospitals.forEach((hospital) -> {
                    jComboBoxAppointmentIlce.addItem(hospital.getIlce());
                });
                clearAllComboBox(1);
                if (!jComboBoxAppointmentIlce.isEnabled()) {
                    jComboBoxAppointmentIlce.setEnabled(true);
                }

            }
        });
        jComboBoxAppointmentIlce.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String il = jComboBoxAppointmentCity.getSelectedItem().toString();
                String ilce = jComboBoxAppointmentIlce.getSelectedItem().toString();

                hospitals = getHospitals(il, "hospitals");
                jComboBoxAppointmentHospital.removeAllItems();
                jComboBoxAppointmentHospital.addItem("-");
                hospitals.forEach((hospital) -> {
                    if (hospital.getIlce().equals(ilce)) {
                        jComboBoxAppointmentHospital.addItem(hospital.getHospitalName());
                    }
                });
                clearAllComboBox(2);
                if (!jComboBoxAppointmentHospital.isEnabled()) {
                    jComboBoxAppointmentHospital.setEnabled(true);
                }

            }
        });

        jComboBoxAppointmentHospital.addItemListener((ItemEvent e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!jComboBoxAppointmentClinic.isEnabled()) {
                    jComboBoxAppointmentClinic.setEnabled(true);
                }

                clearAllComboBox(3);
            }
        });

        jComboBoxAppointmentClinic.addItemListener((ItemEvent e) -> {
            jComboBoxAppointmentDoctorNameSurname.removeAllItems();
            jComboBoxAppointmentDoctorNameSurname.addItem("-");
            if (e.getStateChange() == ItemEvent.SELECTED) {

                String hospitalName = jComboBoxAppointmentHospital.getSelectedItem().toString();

                int clinicID = jComboBoxAppointmentClinic.getSelectedIndex();
                int hospitalID = 1;

                for (int i = 0; i < hospitals.size(); i++) {
                    if (hospitals.get(i).getHospitalName().equals(hospitalName)) {
                        hospitalID = hospitals.get(i).getHospitalID();
                    }
                }
                ArrayList<Doctor> doctors = getDoctors(hospitalID, clinicID);

                //System.out.println(doctors.get(0).getDoctorName());
                doctors.forEach((doctor) -> {
                    jComboBoxAppointmentDoctorNameSurname.addItem(doctor.getDoctorName() + " " + doctor.getDoctorLastName());
                });
                clearAllComboBox(4);
                if (!jComboBoxAppointmentDoctorNameSurname.isEnabled()) {
                    jComboBoxAppointmentDoctorNameSurname.setEnabled(true);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3_member = new javax.swing.JPanel();
        jPanelPatientInformation = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        lblHastaAdi = new javax.swing.JLabel();
        lblHastaSoyadi = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblHastaTC = new javax.swing.JLabel();
        jLabelDogumYeri = new javax.swing.JLabel();
        jLabelDogumTarihi = new javax.swing.JLabel();
        lblHastaDogumYeri2 = new javax.swing.JLabel();
        lblHastaDogumYeri3 = new javax.swing.JLabel();
        lblHastaDogumYeri4 = new javax.swing.JLabel();
        lblHastaDogumYeri = new javax.swing.JLabel();
        lblHastaDogumTarihi = new javax.swing.JLabel();
        lblHastaAdresi = new javax.swing.JLabel();
        lblHastaTelefon = new javax.swing.JLabel();
        lblHastaEmail = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_close = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel_menu = new javax.swing.JPanel();
        jPanel_logoANDname = new javax.swing.JPanel();
        jLabel_appLogo = new javax.swing.JLabel();
        jLabelTahlillerim = new javax.swing.JLabel();
        jLabelZiyaretlerim = new javax.swing.JLabel();
        jLabelCovid = new javax.swing.JLabel();
        jLabelRandevuAl = new javax.swing.JLabel();
        jPanel_container = new javax.swing.JPanel();
        jPanelCheckUp = new javax.swing.JPanel();
        jPanelOperations1 = new javax.swing.JPanel();
        jPanelPrice = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblSelectedTests = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblPrice = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCheckBoxAlergy = new javax.swing.JCheckBox();
        jCheckBoxBloodValues = new javax.swing.JCheckBox();
        jCheckBoxUrine = new javax.swing.JCheckBox();
        jCheckBoxGeneticDisorder = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanelCovid = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCovidInformation = new javax.swing.JLabel();
        lblCovidIcon = new javax.swing.JLabel();
        jPanelRandevuAl = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxAppointmentCity = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxAppointmentIlce = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxAppointmentClinic = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jComboBoxAppointmentDoctorNameSurname = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxAppointmentHospital = new javax.swing.JComboBox<>();
        rSButton3 = new rojeru_san.RSButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        jPanelZiyaretlerim = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMyVisits = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel3_member.setBackground(new java.awt.Color(71, 120, 197));
        jPanel3_member.setPreferredSize(new java.awt.Dimension(420, 1020));

        jPanelPatientInformation.setBackground(new java.awt.Color(120, 168, 252));
        jPanelPatientInformation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/user-male.png"))); // NOI18N
        jPanelPatientInformation.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 100, 80));

        lblHastaAdi.setText("ADI:");
        jPanelPatientInformation.add(lblHastaAdi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        lblHastaSoyadi.setText("SOYADI");
        jPanelPatientInformation.add(lblHastaSoyadi, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, -1, -1));

        jLabel1.setText("TC NO:");
        jPanelPatientInformation.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));
        jPanelPatientInformation.add(lblHastaTC, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, -1, -1));

        jLabelDogumYeri.setText("DOGUMYERİ          : ");

        jLabelDogumTarihi.setText("DOĞUM TARİHİ      : ");

        lblHastaDogumYeri2.setText("ADRES                     : ");

        lblHastaDogumYeri3.setText("TELEFON NO         : ");

        lblHastaDogumYeri4.setText("EMAİL ADRESİ        :");

        javax.swing.GroupLayout jPanel3_memberLayout = new javax.swing.GroupLayout(jPanel3_member);
        jPanel3_member.setLayout(jPanel3_memberLayout);
        jPanel3_memberLayout.setHorizontalGroup(
            jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPatientInformation, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
            .addGroup(jPanel3_memberLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3_memberLayout.createSequentialGroup()
                        .addComponent(jLabelDogumYeri)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHastaDogumYeri))
                    .addGroup(jPanel3_memberLayout.createSequentialGroup()
                        .addComponent(jLabelDogumTarihi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHastaDogumTarihi))
                    .addGroup(jPanel3_memberLayout.createSequentialGroup()
                        .addComponent(lblHastaDogumYeri2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHastaAdresi))
                    .addGroup(jPanel3_memberLayout.createSequentialGroup()
                        .addComponent(lblHastaDogumYeri3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHastaTelefon))
                    .addGroup(jPanel3_memberLayout.createSequentialGroup()
                        .addComponent(lblHastaDogumYeri4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHastaEmail)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3_memberLayout.setVerticalGroup(
            jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3_memberLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPatientInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDogumYeri)
                    .addComponent(lblHastaDogumYeri))
                .addGap(18, 18, 18)
                .addGroup(jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDogumTarihi)
                    .addComponent(lblHastaDogumTarihi))
                .addGap(18, 18, 18)
                .addGroup(jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHastaDogumYeri2)
                    .addComponent(lblHastaAdresi))
                .addGap(18, 18, 18)
                .addGroup(jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHastaDogumYeri3)
                    .addComponent(lblHastaTelefon))
                .addGap(18, 18, 18)
                .addGroup(jPanel3_memberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHastaDogumYeri4)
                    .addComponent(lblHastaEmail))
                .addGap(0, 639, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(46, 49, 49));
        jPanel2.setAlignmentX(0.0F);
        jPanel2.setAlignmentY(0.0F);
        jPanel2.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel_close.setBackground(new java.awt.Color(46, 49, 49));
        jLabel_close.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel_close.setOpaque(true);
        jLabel_close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_closeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(1878, Short.MAX_VALUE)
                .addComponent(jLabel_close, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_close, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jSplitPane1.setDividerSize(1);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(400, 400));
        jSplitPane1.setPreferredSize(new java.awt.Dimension(400, 400));

        jPanel_menu.setBackground(new java.awt.Color(251, 251, 254));
        jPanel_menu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel_menu.setToolTipText("");

        jPanel_logoANDname.setBackground(new java.awt.Color(46, 49, 49));

        jLabel_appLogo.setBackground(new java.awt.Color(255, 255, 255));
        jLabel_appLogo.setOpaque(true);

        javax.swing.GroupLayout jPanel_logoANDnameLayout = new javax.swing.GroupLayout(jPanel_logoANDname);
        jPanel_logoANDname.setLayout(jPanel_logoANDnameLayout);
        jPanel_logoANDnameLayout.setHorizontalGroup(
            jPanel_logoANDnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_appLogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_logoANDnameLayout.setVerticalGroup(
            jPanel_logoANDnameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_appLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
        );

        jLabelTahlillerim.setBackground(new java.awt.Color(255, 236, 238));
        jLabelTahlillerim.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabelTahlillerim.setForeground(new java.awt.Color(87, 89, 98));
        jLabelTahlillerim.setText("    Checkup İşlemleri");
        jLabelTahlillerim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelTahlillerim.setOpaque(true);

        jLabelZiyaretlerim.setBackground(new java.awt.Color(204, 102, 110));
        jLabelZiyaretlerim.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabelZiyaretlerim.setForeground(new java.awt.Color(255, 255, 255));
        jLabelZiyaretlerim.setText("    Ziyaretlerim");
        jLabelZiyaretlerim.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelZiyaretlerim.setOpaque(true);

        jLabelCovid.setBackground(new java.awt.Color(255, 236, 238));
        jLabelCovid.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabelCovid.setForeground(new java.awt.Color(87, 89, 98));
        jLabelCovid.setText("    Covid");
        jLabelCovid.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelCovid.setOpaque(true);

        jLabelRandevuAl.setBackground(new java.awt.Color(255, 236, 238));
        jLabelRandevuAl.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jLabelRandevuAl.setForeground(new java.awt.Color(87, 89, 98));
        jLabelRandevuAl.setText("    Randevu Al");
        jLabelRandevuAl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelRandevuAl.setOpaque(true);

        javax.swing.GroupLayout jPanel_menuLayout = new javax.swing.GroupLayout(jPanel_menu);
        jPanel_menu.setLayout(jPanel_menuLayout);
        jPanel_menuLayout.setHorizontalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_logoANDname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel_menuLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menuLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelZiyaretlerim, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelCovid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelRandevuAl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_menuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelTahlillerim, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel_menuLayout.setVerticalGroup(
            jPanel_menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_menuLayout.createSequentialGroup()
                .addComponent(jPanel_logoANDname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelZiyaretlerim, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelTahlillerim, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelCovid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelRandevuAl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel_menu);

        jPanel_container.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_container.setLayout(new java.awt.CardLayout());

        jPanelCheckUp.setBackground(new java.awt.Color(247, 248, 251));

        jPanelOperations1.setBackground(new java.awt.Color(247, 248, 251));

        javax.swing.GroupLayout jPanelOperations1Layout = new javax.swing.GroupLayout(jPanelOperations1);
        jPanelOperations1.setLayout(jPanelOperations1Layout);
        jPanelOperations1Layout.setHorizontalGroup(
            jPanelOperations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        jPanelOperations1Layout.setVerticalGroup(
            jPanelOperations1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        jPanelPrice.setBackground(new java.awt.Color(247, 248, 251));
        jPanelPrice.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel9.setText("Seçtiğiniz Testler:");
        jPanelPrice.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));
        jPanelPrice.add(lblSelectedTests, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 328, 100));

        jLabel10.setFont(new java.awt.Font("Ubuntu", 1, 15)); // NOI18N
        jLabel10.setText("Toplam Fiyat: ");
        jPanelPrice.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));
        jPanelPrice.add(lblPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 100, 20));

        rSButton2.setBackground(new java.awt.Color(255, 255, 255));
        rSButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        rSButton2.setText("Seçili Test Ücretlerini Hesapla");
        rSButton2.setColorHover(new java.awt.Color(51, 204, 255));
        rSButton2.setColorText(new java.awt.Color(51, 204, 255));
        rSButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Genel" }));

        jCheckBoxAlergy.setText("Alerji Testi");

        jCheckBoxBloodValues.setText("Kan Değerleri Testi");

        jCheckBoxUrine.setText("İdrar Testi");

        jCheckBoxGeneticDisorder.setText("Genetik Bozulma Testi");

        jLabel2.setText("Yapmak İstediğiniz Test Tipini Seçiniz (Sadece Genel Test hizmeti aktiftir)");

        jLabel3.setText("Genel teste eklemek istediğiniz testleri seçiniz.");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel7.setText("Chekup İşlemleri");

        javax.swing.GroupLayout jPanelCheckUpLayout = new javax.swing.GroupLayout(jPanelCheckUp);
        jPanelCheckUp.setLayout(jPanelCheckUpLayout);
        jPanelCheckUpLayout.setHorizontalGroup(
            jPanelCheckUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckUpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCheckUpLayout.createSequentialGroup()
                        .addGroup(jPanelCheckUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelCheckUpLayout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelCheckUpLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanelCheckUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jCheckBoxAlergy)))
                            .addComponent(jCheckBoxGeneticDisorder)
                            .addComponent(jCheckBoxBloodValues)
                            .addComponent(jCheckBoxUrine))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelOperations1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanelPrice, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanelCheckUpLayout.setVerticalGroup(
            jPanelCheckUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCheckUpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCheckUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelOperations1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCheckUpLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(9, 9, 9)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBoxAlergy)
                        .addGap(24, 24, 24)
                        .addComponent(jCheckBoxBloodValues)
                        .addGap(12, 12, 12)
                        .addComponent(jCheckBoxGeneticDisorder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBoxUrine)
                        .addGap(28, 28, 28)
                        .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addComponent(jPanelPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(390, Short.MAX_VALUE))
        );

        jPanel_container.add(jPanelCheckUp, "card6");

        jPanelCovid.setBackground(new java.awt.Color(247, 248, 251));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel4.setText("Covid");

        jPanel1.setBackground(new java.awt.Color(250, 234, 169));
        jPanel1.setForeground(new java.awt.Color(95, 77, 9));

        lblCovidInformation.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblCovidInformation.setForeground(new java.awt.Color(95, 77, 9));
        lblCovidInformation.setText("COVID-19 pandemik aşılaması için belirlenen öncelikli grupta değilsiniz. ");

        lblCovidIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMAGES/warning.png"))); // NOI18N
        lblCovidIcon.setPreferredSize(new java.awt.Dimension(52, 50));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblCovidIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCovidInformation)
                .addContainerGap(538, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblCovidIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblCovidInformation)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lblCovidInformation.getAccessibleContext().setAccessibleName("");
        lblCovidInformation.getAccessibleContext().setAccessibleDescription("COVID-19 pandemik aşılaması için belirlenen öncelikli grupta değilsiniz. ");

        javax.swing.GroupLayout jPanelCovidLayout = new javax.swing.GroupLayout(jPanelCovid);
        jPanelCovid.setLayout(jPanelCovidLayout);
        jPanelCovidLayout.setHorizontalGroup(
            jPanelCovidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCovidLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCovidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanelCovidLayout.setVerticalGroup(
            jPanelCovidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCovidLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(886, Short.MAX_VALUE))
        );

        jPanel_container.add(jPanelCovid, "card7");

        jPanelRandevuAl.setBackground(new java.awt.Color(247, 248, 251));
        jPanelRandevuAl.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setText("Randevu Al");
        jPanelRandevuAl.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        jComboBoxAppointmentCity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Ankara" }));
        jPanelRandevuAl.add(jComboBoxAppointmentCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 68, 570, -1));

        jLabel11.setText("Şehir:");
        jPanelRandevuAl.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 73, -1, -1));

        jComboBoxAppointmentIlce.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBoxAppointmentIlce.setEnabled(false);
        jComboBoxAppointmentIlce.setMinimumSize(new java.awt.Dimension(570, 26));
        jComboBoxAppointmentIlce.setPreferredSize(new java.awt.Dimension(570, 26));
        jPanelRandevuAl.add(jComboBoxAppointmentIlce, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 112, 570, -1));

        jLabel12.setText("İlçe:");
        jPanelRandevuAl.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 117, -1, -1));

        jLabel13.setText("Klinik: ");
        jPanelRandevuAl.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 211, -1, -1));

        jComboBoxAppointmentClinic.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Ortopedi ve Travmatoloji", "Dahiliye", "Göz", "Cildiye", "Fizyoterapi" }));
        jComboBoxAppointmentClinic.setEnabled(false);
        jPanelRandevuAl.add(jComboBoxAppointmentClinic, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 206, 570, -1));

        jLabel14.setText("Doktor:");
        jPanelRandevuAl.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 255, -1, -1));

        jComboBoxAppointmentDoctorNameSurname.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBoxAppointmentDoctorNameSurname.setEnabled(false);
        jPanelRandevuAl.add(jComboBoxAppointmentDoctorNameSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 250, 570, -1));

        jLabel15.setText("Hastane:");
        jPanelRandevuAl.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 167, -1, -1));

        jComboBoxAppointmentHospital.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));
        jComboBoxAppointmentHospital.setEnabled(false);
        jPanelRandevuAl.add(jComboBoxAppointmentHospital, new org.netbeans.lib.awtextra.AbsoluteConstraints(74, 162, 570, -1));

        rSButton3.setBackground(new java.awt.Color(255, 255, 255));
        rSButton3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 204, 255)));
        rSButton3.setText("Randevu AL");
        rSButton3.setColorHover(new java.awt.Color(51, 204, 255));
        rSButton3.setColorText(new java.awt.Color(51, 204, 255));
        rSButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton3ActionPerformed(evt);
            }
        });
        jPanelRandevuAl.add(rSButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(181, 392, 270, 30));
        jPanelRandevuAl.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(73, 294, 570, -1));

        jLabel16.setText("Tarih");
        jPanelRandevuAl.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 294, -1, -1));

        jPanel_container.add(jPanelRandevuAl, "card8");

        jPanelZiyaretlerim.setBackground(new java.awt.Color(247, 248, 251));

        jTableMyVisits.setBorder(null);
        jTableMyVisits.setForeground(new java.awt.Color(56, 56, 56));
        jTableMyVisits.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hastane ADI", "Klinik ADI", "Doktor ADI", "Doktor SOYADI", "Tarih"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableMyVisits.setGridColor(new java.awt.Color(254, 254, 254));
        jTableMyVisits.setRowHeight(50);
        jTableMyVisits.setRowMargin(5);
        jTableMyVisits.setSelectionBackground(new java.awt.Color(255, 236, 238));
        jTableMyVisits.setSelectionForeground(new java.awt.Color(87, 89, 98));
        jScrollPane1.setViewportView(jTableMyVisits);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel6.setText("Ziyaretlerim");

        javax.swing.GroupLayout jPanelZiyaretlerimLayout = new javax.swing.GroupLayout(jPanelZiyaretlerim);
        jPanelZiyaretlerim.setLayout(jPanelZiyaretlerimLayout);
        jPanelZiyaretlerimLayout.setHorizontalGroup(
            jPanelZiyaretlerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelZiyaretlerimLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelZiyaretlerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelZiyaretlerimLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1267, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelZiyaretlerimLayout.setVerticalGroup(
            jPanelZiyaretlerimLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelZiyaretlerimLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(411, Short.MAX_VALUE))
        );

        jPanel_container.add(jPanelZiyaretlerim, "card4");

        jSplitPane1.setRightComponent(jPanel_container);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3_member, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3_member, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1020, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel_close1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_close1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel_close1MouseClicked

    private void jLabel_closeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_closeMouseClicked
        // close this form
        this.dispose();
    }//GEN-LAST:event_jLabel_closeMouseClicked

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        // TODO add your handling code here:
        Test t = new GeneralTest();
        if (jCheckBoxAlergy.isSelected()) {
            t = new AllergyTest(t);
        }
        if (jCheckBoxBloodValues.isSelected()) {
            t = new BloodValuesTest(t);
        }
        if (jCheckBoxGeneticDisorder.isSelected()) {
            t = new GeneticDisorder(t);
        }
        if (jCheckBoxUrine.isSelected()) {
            t = new UrineTest(t);
        }
        double totalPrice = t.getCost();
        String selectedTests = t.getDescription();

        //System.out.println(selectedTests + " " + totalPrice + " TL");
        String[] tests = selectedTests.split(",");
        StringBuilder sb = new StringBuilder("<html>");
        for (String a : tests) {
            sb.append(a);
            sb.append("<br>");
        }
        sb.append("</html>");
        lblSelectedTests.setText(sb.toString());
        lblPrice.setText(Double.toString(totalPrice) + "TL");
        jPanelPrice.setVisible(true);
        //jPanelPrice.setBo
        /*jPanelPrice.setBounds(20, 500, 334, 175);
        jPanelOperations.setBounds(20, 0, 450, 315);*/

    }//GEN-LAST:event_rSButton2ActionPerformed

    private void rSButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton3ActionPerformed
        // TODO add your handling code here:
        String city = jComboBoxAppointmentCity.getSelectedItem().toString();
        String ilce = jComboBoxAppointmentIlce.getSelectedItem().toString();
        String hospital = jComboBoxAppointmentHospital.getSelectedItem().toString();
        String clinic = jComboBoxAppointmentClinic.getSelectedItem().toString();
        String doctor = jComboBoxAppointmentDoctorNameSurname.getSelectedItem().toString();

        Date dt = jDateChooser1.getDate();
        String newDate = String.format("%1$td-%1$tm-%1$ty", dt);

        String message = "<html>Randevunuz oluşturulmuştur.<br>" + "Şehir: " + city + "<br>İlçe: " + ilce + "<br>Hastane: " + hospital + "<br>Klinik: " + clinic + "<br>Doktor: " + doctor + "<br>Tarih: " + newDate + "</html>";

        JOptionPane.showMessageDialog(null, message, "BAŞARILI", JOptionPane.PLAIN_MESSAGE);

    }//GEN-LAST:event_rSButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard_Form().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBoxAlergy;
    private javax.swing.JCheckBox jCheckBoxBloodValues;
    private javax.swing.JCheckBox jCheckBoxGeneticDisorder;
    private javax.swing.JCheckBox jCheckBoxUrine;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxAppointmentCity;
    private javax.swing.JComboBox<String> jComboBoxAppointmentClinic;
    private javax.swing.JComboBox<String> jComboBoxAppointmentDoctorNameSurname;
    private javax.swing.JComboBox<String> jComboBoxAppointmentHospital;
    private javax.swing.JComboBox<String> jComboBoxAppointmentIlce;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCovid;
    private javax.swing.JLabel jLabelDogumTarihi;
    private javax.swing.JLabel jLabelDogumYeri;
    private javax.swing.JLabel jLabelRandevuAl;
    private javax.swing.JLabel jLabelTahlillerim;
    private javax.swing.JLabel jLabelZiyaretlerim;
    private javax.swing.JLabel jLabel_appLogo;
    private javax.swing.JLabel jLabel_close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3_member;
    private javax.swing.JPanel jPanelCheckUp;
    private javax.swing.JPanel jPanelCovid;
    private javax.swing.JPanel jPanelOperations1;
    private javax.swing.JPanel jPanelPatientInformation;
    private javax.swing.JPanel jPanelPrice;
    private javax.swing.JPanel jPanelRandevuAl;
    private javax.swing.JPanel jPanelZiyaretlerim;
    private javax.swing.JPanel jPanel_container;
    private javax.swing.JPanel jPanel_logoANDname;
    private javax.swing.JPanel jPanel_menu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTableMyVisits;
    private javax.swing.JLabel lblCovidIcon;
    private javax.swing.JLabel lblCovidInformation;
    private javax.swing.JLabel lblHastaAdi;
    private javax.swing.JLabel lblHastaAdresi;
    private javax.swing.JLabel lblHastaDogumTarihi;
    private javax.swing.JLabel lblHastaDogumYeri;
    private javax.swing.JLabel lblHastaDogumYeri2;
    private javax.swing.JLabel lblHastaDogumYeri3;
    private javax.swing.JLabel lblHastaDogumYeri4;
    private javax.swing.JLabel lblHastaEmail;
    private javax.swing.JLabel lblHastaSoyadi;
    private javax.swing.JLabel lblHastaTC;
    private javax.swing.JLabel lblHastaTelefon;
    private javax.swing.JLabel lblPrice;
    private javax.swing.JLabel lblSelectedTests;
    private rojeru_san.RSButton rSButton2;
    private rojeru_san.RSButton rSButton3;
    // End of variables declaration//GEN-END:variables
}
