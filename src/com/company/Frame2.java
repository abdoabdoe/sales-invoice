package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Frame2 extends JFrame implements ActionListener {


    private JMenuBar Menubar;
    private JMenu filemenu;
    private JMenuItem Openmenu;
    private JMenuItem Savemenu;
    private JMenuItem Loadmenu;
    private JMenuItem Exitmenu;


    private JTable tableO;


    private String[] col = {"No", "Item name", "Item price", "Count", "Item Total"};
    private String[][] dataa = {
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},
            {"", "", "", "", ""},

    };
    private JTable table;
    private String[] cols = {"No", "Data", "Customer", "Total"};
    private String[][] Data = {
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
            {"", "", "", ""},
    };


    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JTextField text1;
    private JTextField text2;
    private JTextArea ta;
    private JTextArea tb;



    public Frame2() {


        super("Sales invoice generator");
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Menubar = new JMenuBar();
        filemenu = new JMenu("file");
        Openmenu = new JMenuItem("Open");
        Savemenu = new JMenuItem("Save");
        Loadmenu = new JMenuItem("Load");
        Exitmenu = new JMenuItem("Exit");

        filemenu.add(Openmenu);
        filemenu.add(Savemenu);
        filemenu.add(Loadmenu);
        filemenu.addSeparator();
        filemenu.add(Exitmenu);
        Menubar.add(filemenu);

        setJMenuBar(Menubar);
        Menubar = new JMenuBar();
        filemenu = new JMenu("file");

        Openmenu = new JMenuItem("Open");
        Openmenu.addActionListener(this);
        Openmenu.setActionCommand("Open");

        Savemenu = new JMenuItem("Save");
        Savemenu.addActionListener(this);
        Savemenu.setActionCommand("Save");

        /*Loadmenu = new JMenuItem("Load");
        Loadmenu.addActionListener(this);
        Loadmenu.setActionCommand("Load");*/

        Exitmenu = new JMenuItem("Exit");
        Exitmenu.addActionListener(this);
        Exitmenu.setActionCommand("Exit");

        filemenu.add(Openmenu);
        filemenu.add(Savemenu);
        filemenu.addSeparator();
        filemenu.add(Exitmenu);
        Menubar.add(filemenu);

        setJMenuBar(Menubar);


        tableO = new JTable(dataa, col);
        add(new JScrollPane(tableO));

        ta = new JTextArea();
        add(new JScrollPane(ta));


        btn1 = new JButton("Save content");
        add(btn1);
        btn1.addActionListener(this);
        btn1.setActionCommand("S");


        btn2 = new JButton("Cancel");
        add(btn2);
        btn2.addActionListener(this);
        btn2.setActionCommand("C");

        JLabel label1 = new JLabel("invoice date");
        text1 = new JTextField(10);
        add(label1);
        add(text1);


        JLabel label2 = new JLabel("Customer Name");
        text2 = new JTextField(10);
        add(label2);
        add(text2);

        table = new JTable();

        table = new JTable(Data, cols);
        add(new JScrollPane(table));


        tb = new JTextArea();
        add(new JScrollPane(tb));


        btn3 = new JButton("Create new invoice ");
        add(btn3);
        btn3.addActionListener(this);
        btn3.setActionCommand("Create new invoice");

        btn4 = new JButton("Delete invoice");
        add(btn4);
        btn4.addActionListener(this);
        btn4.setActionCommand("D");

        setSize(600, 600);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new Frame2().setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Open":
                openFile();
                break;

            case "Create new invoice":
                invoice();
                break;

            case "Save":

            case "S":
                saveFile();
                break;

            case "Load":
                openFile();
                break;

            case "Exit":
                System.exit(0);

                break;

            case "C":
                deletetext();
                break;
            case "D":
                deletetext();
                break;

        }
    }

    private void openFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(path);
                int size = fis.available();
                byte[] b = new byte[size];

                fis.read(b);
                ta.setText(new String(b));
                fis.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }

    private void invoice() {
        JFileChooser fg = new JFileChooser();
        int result = fg.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fg.getSelectedFile().getPath();
            FileInputStream fes = null;
            try {
                fes = new FileInputStream(path);
                int size = fes.available();
                byte[] b = new byte[size];

                fes.read(b);
                tb.setText(new String(b));
                fes.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fes.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }


        }
    }

    private void saveFile() {
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            String path = fc.getSelectedFile().getPath();
            FileWriter fos = null;
            try {
                fos = new FileWriter(path);
                BufferedWriter bw = new BufferedWriter(fos);

                for (int i = 0; i < tableO.getRowCount(); i++) {
                    for (int j = 0; j < tableO.getColumnCount(); j++) {
                        bw.write(tableO.getValueAt(i, j).toString());
                    }
                }

                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        bw.write(table.getValueAt(i, j).toString());
                    }
                }


                bw.close();
                fos.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

        private void deletetext() {

            ta.setText(null);
            tb.setText(null);
        }


        }


