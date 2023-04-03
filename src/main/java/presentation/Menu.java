package presentation;

import bll.*;
import bll.MenuItem;
import start.Start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * This class creates the appropriate interface for a client when he wants to see the menu
 * All products from the menu are showed into a JTable from where the client can choose the menuitem, by selecting a chechbox, to order
 * Also, the client can search for a certain product by introducing keywords into the JTextfield present on the interface (the method searchProduct is called)
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 * @see JFrame
 */

public class Menu extends JFrame {

    public static List<MenuItem> toOrder = new ArrayList<>();
    public static JTextField filter;
    private JTable table = new JTable();
    private List<BaseProduct> forCommand = new ArrayList();
    private Actions actions = new Actions();


    public Menu(String name) {

        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\mm.jpg");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(1040, 580));
        this.getContentPane().setBounds(0, 0, 1040, 600);
        this.getContentPane().setLayout(null);
        pack();

        filter = new JTextField();
        filter.setFont(new Font("Impact", Font.PLAIN, 18));
        filter.setBounds(850, 50, 100, 30);
        getContentPane().add(filter);

        ImageIcon icon = new ImageIcon("src\\main\\resources\\s.png");
        JButton search = new JButton(icon);
        search.setFont(new Font("Impact", Font.PLAIN, 18));
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Actions actions = new Actions();
                actions.searchProduct(filter.getText(), 1);
            }
        });
        search.setBounds(970, 50, 30, 30);
        getContentPane().add(search);
        setVisible(true);

        JLabel menu = new JLabel("MENU");
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Freestyle Script", Font.BOLD, 90));
        menu.setBounds(100, 40, 500, 80);
        this.getContentPane().add(menu);

        String line = "";
        BufferedReader bufferedReader = null;
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

        JButton finish = new JButton("Order");
        finish.setFont(new Font("Impact", Font.PLAIN, 18));
        finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                for (int i = 0; i < table.getRowCount(); i++) {
                    boolean checked = Boolean.parseBoolean(table.getValueAt(i, 0).toString());
                    if (checked) {
                        MenuItem menuItem = new BaseProduct(table.getValueAt(i, 1).toString(), Float.parseFloat(table.getValueAt(i, 2).toString()), Float.parseFloat(table.getValueAt(i, 3).toString()), Float.parseFloat(table.getValueAt(i, 4).toString()), Float.parseFloat(table.getValueAt(i, 5).toString()), Float.parseFloat(table.getValueAt(i, 6).toString()), Float.parseFloat(table.getValueAt(i, 7).toString()));
                        toOrder.add(menuItem);
                    }
                }
                actions.createOrder(name, toOrder);
                if (Menu.toOrder.size() > 0) {
                    Menu.toOrder = new ArrayList<>();
                }
            }
        });
        finish.setBounds(900, 470, 100, 30);
        getContentPane().add(finish);
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

        ArrayList<MenuItem> list = new ArrayList<>(Start.menuitemslist.values());

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