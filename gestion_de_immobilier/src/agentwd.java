import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.border.LineBorder;

import javax.swing.table.DefaultTableModel;


import java.awt.Color;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.SwingConstants;

import java.awt.Font;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.Statement;


import javax.swing.JTextField;

import javax.swing.JButton;

import javax.swing.JTable;

import javax.swing.JSpinner;

import javax.swing.JScrollPane;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import javax.swing.ScrollPaneConstants;

import javax.swing.border.EmptyBorder;


public class agentwd {


    private JFrame frame;

    private JTextField PRIXtxt;

    private JTextField ADtxt;

    private JTextField TYPEtxt;

    private JTable tabletxt;

    private JTextField IDtxt;


    public static void NewScreen() {

        EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {

                    agentwd window = new agentwd();

                    window.frame.setVisible(true);

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

    }


    Connection con;

    PreparedStatement pst;

    ResultSet rs;


    public agentwd() {

        initialize();

        table();

    }


    public void connect() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/agence","root","");

            System.out.println("connecté avec succès");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    private void table() {

        try {

            connect();

            String [] entete = { "id","prix","adresse","type","nb"};

            String [] ligne = new String[5];


            DefaultTableModel model = new DefaultTableModel(null, entete);


            String sql = "select * from tabletxt";

            Statement st = con.createStatement();

            rs = st.executeQuery(sql);

            while(rs.next()) {

                ligne[0] = rs.getString("id");

                ligne[1] = rs.getString("prix");

                ligne[2] = rs.getString("adresse");

                ligne[3] = rs.getString("type");

                ligne[4] = rs.getString("nb");

                model.addRow(ligne);

            }


            tabletxt.setModel(model);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    private void initialize() {

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(128, 128, 128));

        frame.setBounds(100, 100, 434, 305);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().setLayout(null);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192));

        panel.setBorder(new LineBorder(new Color(0, 0, 0), 0));

        panel.setBounds(0, 0, 418, 143);

        frame.getContentPane().add(panel);

        panel.setLayout(null);


        JLabel lblNewLabel_1 = new JLabel(" PRIX(DA)");

        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

        lblNewLabel_1.setBounds(15, 59, 68, 24);

        panel.add(lblNewLabel_1);


        JLabel lblNewLabel_2 = new JLabel("ADRESSE");

        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);

        lblNewLabel_2.setBounds(10, 94, 73, 24);

        panel.add(lblNewLabel_2);


        JLabel lblNewLabel_3 = new JLabel("TYPE");

        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));

        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);

        lblNewLabel_3.setBounds(241, 14, 44, 24);

        panel.add(lblNewLabel_3);


        JLabel lblNewLabel_4 = new JLabel("Nb de pièces");

        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);

        lblNewLabel_4.setBounds(235, 60, 73, 24);

        panel.add(lblNewLabel_4);


        PRIXtxt = new JTextField();

        PRIXtxt.setBounds(93, 59, 126, 23);

        panel.add(PRIXtxt);

        PRIXtxt.setColumns(10);


        ADtxt = new JTextField();

        ADtxt.setBounds(89, 97, 164, 24);

        panel.add(ADtxt);

        ADtxt.setColumns(10);


        TYPEtxt = new JTextField();

        TYPEtxt.setBounds(295, 20, 113, 26);

        panel.add(TYPEtxt);

        TYPEtxt.setColumns(10);


        JSpinner NBtxt = new JSpinner();

        NBtxt.setBounds(318, 62, 59, 20);

        panel.add(NBtxt);


        IDtxt = new JTextField();

        IDtxt.setBounds(93, 25, 126, 23);

        panel.add(IDtxt);

        IDtxt.setColumns(10);


        JButton btnNewButton = new JButton("Enregistrer");

        btnNewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                String PRIX = PRIXtxt.getText();

                String AD = ADtxt.getText();

                String TYPE = TYPEtxt.getText();

                String ID = IDtxt.getText();

                String NB = NBtxt.getValue().toString();


                String sql = "insert into tabletxt values(?,?,?,?,?)";

                try {

                    pst = con.prepareStatement(sql);

                    pst.setString(1, ID);

                    pst.setString(2, PRIX);

                    pst.setString(3, AD);

                    pst.setString(4, TYPE);

                    pst.setString(5, NB);

                    pst.execute();

                    JOptionPane.showMessageDialog(null, "ajouté avec succès");

                } catch (Exception e) {

                    JOptionPane.showMessageDialog(null, e);

                }

            }

        });

        btnNewButton.setBounds(321, 115, 89, 25);

        panel.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("ID");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(15, 25, 55, 23);
        panel.add(lblNewLabel);


        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));

        scrollPane.setToolTipText("");

        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBounds(10, 154, 398, 101);

        frame.getContentPane().add(scrollPane);


        tabletxt = new JTable();

        tabletxt.setBorder(new LineBorder(new Color(0, 0, 0), 2));

        tabletxt.setBackground(new Color(255, 255, 255));

        scrollPane.setViewportView(tabletxt);

        tabletxt.setFont(new Font("Tahoma", Font.PLAIN, 12));

    }
}