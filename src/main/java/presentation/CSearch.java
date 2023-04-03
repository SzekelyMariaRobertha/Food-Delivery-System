package presentation;

import bll.Actions;
import bll.BaseProduct;
import bll.CompositeProduct;
import bll.MenuItem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This class creates the appropriate interface for an admin when he wants to search for a product in order to create a composite product
 * All products from the menu are showed into a JTable from where the admin can choose the menuitem, by selecting a chechbox, to order
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 * @see JFrame
 */

public class CSearch extends JFrame {

    private JTable table = new JTable();
    private JPanel contentPane;
    private JButton ok;

    public CSearch() {
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

        if (line != null) {
            table = new JTable(addRowToJTable());
            table.getColumnModel().getColumn(1).setPreferredWidth(600);
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

                for (int i = 0; i < table.getRowCount(); i++) {
                    boolean checked = Boolean.parseBoolean(table.getValueAt(i, 0).toString());
                    if (checked) {
                        if (!table.getValueAt(i, 1).toString().contains("COMPOSED OF")) {
                            BaseProduct baseProduct = new BaseProduct(table.getValueAt(i, 1).toString(), Float.parseFloat(table.getValueAt(i, 2).toString()), Float.parseFloat(table.getValueAt(i, 3).toString()), Float.parseFloat(table.getValueAt(i, 4).toString()), Float.parseFloat(table.getValueAt(i, 5).toString()), Float.parseFloat(table.getValueAt(i, 6).toString()), Float.parseFloat(table.getValueAt(i, 7).toString()));
                            CreateComposite.forCreate.add(baseProduct);
                        } else {
                            JOptionPane.showMessageDialog(null, "The new product cannot contains a composed product");
                            break;
                        }

                    }
                }
                dispose();
            }
        });
        ok.setBounds(50, 100, 200, 30);
        contentPane.add(ok);
        setVisible(true);
    }

    public DefaultTableModel addRowToJTable() {

        DefaultTableModel model = new DefaultTableModel() {

            public Class<?> getColumnClass(int column) {
                if (column == 0) {
                    return Boolean.class;
                }
                return String.class;
            }
        };

        table.setModel(model);
        model.addColumn("Select");
        model.addColumn("Title");
        model.addColumn("Rating");
        model.addColumn("Calories");
        model.addColumn("Proteins");
        model.addColumn("Fats");
        model.addColumn("Sodium");
        model.addColumn("Price");

        ArrayList<MenuItem> list = new ArrayList<>(Actions.elements);

        Object[] rowData = new Object[8];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof CompositeProduct) {
                StringBuilder s = new StringBuilder();
                s.append(list.get(i).getTitle()).append(" COMPOSED OF ");
                for (BaseProduct b : ((CompositeProduct) list.get(i)).getBaseProducts()) {
                    s.append(b.getTitle()).append(" ");
                }
                rowData[1] = s;

            } else {
                rowData[1] = list.get(i).getTitle();
            }
            rowData[2] = list.get(i).getRating();
            rowData[3] = list.get(i).getCalories();
            rowData[4] = list.get(i).getProteins();
            rowData[5] = list.get(i).getFats();
            rowData[6] = list.get(i).getSodium();
            rowData[7] = list.get(i).computePrice();

            model.addRow(rowData);
            model.setValueAt(false, i, 0);

        }
        return model;
    }
}