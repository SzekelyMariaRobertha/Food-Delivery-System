package presentation;

import bll.Actions;
import bll.BaseProduct;
import bll.CompositeProduct;
import bll.MenuItem;
import start.Start;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * This class creates the appropriate interface for an admin when he wants to create a composite product
 * All base products that could be used to create a composite product are showed into a JTable from where the admin can choose the elements by selecting a chechbox
 *
 * @author Szekely Maria-Robertha
 * @see JFrame
 */

public class CreateComposite extends JFrame {

    public static JTextField filter;
    private JTable table = new JTable();
    public static List<BaseProduct> forCreate = new ArrayList();
    private JPanel contentPane;
    private JButton ok;
    private JTextField title = new JTextField();
    private int s = 0;

    public CreateComposite() {
        setSize(1040, 580);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255, 204, 204));
        setContentPane(contentPane);
        setLayout(null);
        setVisible(true);

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
                actions.searchProduct(filter.getText(), 0);
                s = 1;
            }
        });
        search.setBounds(970, 50, 30, 30);
        getContentPane().add(search);
        setVisible(true);

        JLabel menu = new JLabel("Create composite product");
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.WHITE);
        menu.setFont(new Font("Freestyle Script", Font.BOLD, 60));
        menu.setBounds(100, 40, 500, 50);
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

        JButton finish = new JButton("Create");
        finish.setFont(new Font("Impact", Font.PLAIN, 18));
        finish.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (s == 0) {
                    forCreate = new ArrayList<>();
                }

                for (int i = 0; i < table.getRowCount(); i++) {
                    boolean checked = Boolean.parseBoolean(table.getValueAt(i, 0).toString());
                    if (checked) {
                        if (!table.getValueAt(i, 1).toString().contains("COMPOSED OF")) {
                            BaseProduct baseProduct = new BaseProduct(table.getValueAt(i, 1).toString(), Float.parseFloat(table.getValueAt(i, 2).toString()), Float.parseFloat(table.getValueAt(i, 3).toString()), Float.parseFloat(table.getValueAt(i, 4).toString()), Float.parseFloat(table.getValueAt(i, 5).toString()), Float.parseFloat(table.getValueAt(i, 6).toString()), Float.parseFloat(table.getValueAt(i, 7).toString()));
                            forCreate.add(baseProduct);
                        } else {
                            JOptionPane.showMessageDialog(null, "The products cannot contains a composed product");
                            forCreate = new ArrayList<>();
                            title.setEditable(false);
                            break;
                        }

                    }
                }

                if (forCreate.size() > 0) {
                    JFrame frame = new JFrame();
                    frame.setLocationRelativeTo(null);
                    frame.setSize(new Dimension(300, 200));
                    frame.setLayout(null);
                    frame.setVisible(true);

                    JLabel priceLabel = new JLabel("Choose a title");
                    priceLabel.setForeground(Color.BLACK);
                    priceLabel.setFont(new Font("Impact", Font.PLAIN, 18));
                    priceLabel.setBounds(50, 20, 200, 30);
                    frame.add(priceLabel);

                    title = new JTextField();
                    title.setEditable(true);
                    title.setForeground(Color.BLACK);
                    title.setFont(new Font("Impact", Font.PLAIN, 18));
                    title.setBounds(50, 60, 200, 30);
                    frame.add(title);

                    ok = new JButton("Finish");
                    ok.setOpaque(false);
                    ok.setContentAreaFilled(false);
                    ok.setBorderPainted(false);
                    ok.setForeground(Color.BLACK);
                    ok.setFont(new Font("Impact", Font.PLAIN, 20));
                    ok.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            if (!title.getText().equals("")) {
                                if (Start.menuitemslist.containsKey(title.getText() + " ")) {
                                    JOptionPane.showMessageDialog(null, "This name already exists");
                                } else {
                                    CompositeProduct compositeProduct = new CompositeProduct(forCreate, title.getText() + " ");
                                    Actions actions = new Actions();
                                    actions.createComposite(compositeProduct);
                                    frame.dispose();

                                    forCreate = new ArrayList<>();
                                    s = 0;
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Please insert a title");
                            }
                        }
                    });
                    ok.setBounds(50, 100, 200, 30);
                    frame.getContentPane().add(ok);
                } else {
                    JOptionPane.showMessageDialog(null, "Nothing to create");
                }
            }
        });

        finish.setBounds(900, 470, 100, 30);
        getContentPane().add(finish);

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