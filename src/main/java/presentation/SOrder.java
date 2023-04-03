package presentation;

import bll.*;
import bll.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This class creates the appropriate interface for an employee when he wants to check the orders
 * All orders are showed into a JTable which contains details about the orders
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 * @see JFrame
 */

public class SOrder extends JFrame {

    private JTable table = new JTable();
    private JPanel contentPane;
    private JButton ok;

    public SOrder() {
        setSize(1040, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255, 204, 204));
        setContentPane(contentPane);
        setLayout(null);

        String line = "";
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/main/resources/products.txt"));
            line = bufferedReader.readLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (EmployeeGUI.map != null) {
            table = new JTable(addRowToJTable());
            table.getColumnModel().getColumn(4).setPreferredWidth(600);
            JScrollPane jScrollPane = new JScrollPane();
            jScrollPane.setBounds(100, 150, 900, 300);
            getContentPane().add(jScrollPane);
            jScrollPane.setViewportView(table);
        } else {
            JLabel label = new JLabel("Nothing to show");
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Freestyle Script", Font.BOLD, 90));
            label.setBounds(500, 200, 500, 80);
            this.getContentPane().add(label);
        }

        ok = new JButton("OK");
        ok.setOpaque(false);
        ok.setContentAreaFilled(false);
        ok.setBorderPainted(false);
        ok.setForeground(Color.BLACK);
        ok.setFont(new Font("Impact", Font.PLAIN, 20));
        ok.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        ok.setBounds(50, 100, 200, 30);
        contentPane.add(ok);
        setVisible(true);
    }

    public DefaultTableModel addRowToJTable() {

        DefaultTableModel model = new DefaultTableModel();

        table.setModel(model);
        model.addColumn("Hashcode");
        model.addColumn("Date");
        model.addColumn("Time");
        model.addColumn("Name");
        model.addColumn("Products");
        model.addColumn("Price");

        HashMap<Order, List<MenuItem>> map = EmployeeGUI.map;

        Object[] rowData = new Object[6];
        for (Map.Entry<Order, List<MenuItem>> entry : map.entrySet()) {
            rowData[0] = entry.getKey().hashCode();
            rowData[1] = entry.getKey().getOrderData();
            rowData[2] = entry.getKey().getOrderTime();
            rowData[3] = entry.getKey().getClientUsername();
            rowData[4] = entry.getValue();
            rowData[5] = entry.getKey().getTotalPrice();
            model.addRow(rowData);
        }

        return model;
    }
}