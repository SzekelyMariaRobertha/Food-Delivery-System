package presentation;

import bll.Actions;
import bll.MenuItem;
import start.Start;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class creates the appropriate interface for an admin when he wants to modify an existing product from the menu
 * All products that can be deleted are showed into a JComboBox
 * The admin must choose the item from the JComboBox then complete the textfields with new information, then press OK
 *
 * @author Szekely Maria-Robertha
 * @see JFrame
 */

public class ModifyProduct extends JFrame {

    private JTextField titleField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField proteinField;
    private JTextField fatField;
    private JTextField sodiumField;
    private JTextField priceField;
    private JPanel contentPane;
    private JButton ok;
    private JComboBox<String> products = new JComboBox<>();

    public ModifyProduct() {

        setSize(500, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255, 204, 204));
        setContentPane(contentPane);
        setLayout(null);
        setVisible(true);

        JLabel idLabel = new JLabel("Choose product");
        idLabel.setForeground(Color.BLACK);
        idLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        idLabel.setBounds(10, 20, 200, 20);
        this.getContentPane().add(idLabel);

        JLabel titleLabel = new JLabel("TITLE");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        titleLabel.setBounds(40, 60, 200, 30);
        this.getContentPane().add(titleLabel);

        JLabel ratingLabel = new JLabel("RATING");
        ratingLabel.setForeground(Color.BLACK);
        ratingLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        ratingLabel.setBounds(40, 100, 200, 30);
        this.getContentPane().add(ratingLabel);

        JLabel caloriesLabel = new JLabel("CALORIES");
        caloriesLabel.setForeground(Color.BLACK);
        caloriesLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        caloriesLabel.setBounds(40, 140, 200, 30);
        this.getContentPane().add(caloriesLabel);

        JLabel proteinslLabel = new JLabel("PROTEINS");
        proteinslLabel.setForeground(Color.BLACK);
        proteinslLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        proteinslLabel.setBounds(40, 180, 200, 30);
        this.getContentPane().add(proteinslLabel);

        JLabel fatLabel = new JLabel("FAT");
        fatLabel.setForeground(Color.BLACK);
        fatLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        fatLabel.setBounds(40, 220, 200, 30);
        this.getContentPane().add(fatLabel);

        JLabel sodiumLabel = new JLabel("SODIUM");
        sodiumLabel.setForeground(Color.BLACK);
        sodiumLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        sodiumLabel.setBounds(40, 260, 200, 30);
        this.getContentPane().add(sodiumLabel);

        JLabel priceLabel = new JLabel("PRICE");
        priceLabel.setForeground(Color.BLACK);
        priceLabel.setFont(new Font("Impact", Font.PLAIN, 18));
        priceLabel.setBounds(40, 300, 200, 30);
        this.getContentPane().add(priceLabel);

        titleField = new JTextField();
        titleField.setForeground(Color.BLACK);
        titleField.setFont(new Font("Impact", Font.PLAIN, 18));
        titleField.setBounds(200, 60, 200, 30);
        this.getContentPane().add(titleField);

        ratingField = new JTextField();
        ratingField.setForeground(Color.BLACK);
        ratingField.setFont(new Font("Impact", Font.PLAIN, 18));
        ratingField.setBounds(200, 100, 200, 30);
        this.getContentPane().add(ratingField);

        caloriesField = new JTextField();
        caloriesField.setForeground(Color.BLACK);
        caloriesField.setFont(new Font("Impact", Font.PLAIN, 18));
        caloriesField.setBounds(200, 140, 200, 30);
        this.getContentPane().add(caloriesField);

        proteinField = new JTextField();
        proteinField.setForeground(Color.BLACK);
        proteinField.setFont(new Font("Impact", Font.PLAIN, 18));
        proteinField.setBounds(200, 180, 200, 30);
        this.getContentPane().add(proteinField);

        fatField = new JTextField();
        fatField.setForeground(Color.BLACK);
        fatField.setFont(new Font("Impact", Font.PLAIN, 18));
        fatField.setBounds(200, 220, 200, 30);
        this.getContentPane().add(fatField);

        sodiumField = new JTextField();
        sodiumField.setForeground(Color.BLACK);
        sodiumField.setFont(new Font("Impact", Font.PLAIN, 18));
        sodiumField.setBounds(200, 260, 200, 30);
        this.getContentPane().add(sodiumField);

        priceField = new JTextField();
        priceField.setForeground(Color.BLACK);
        priceField.setFont(new Font("Impact", Font.PLAIN, 18));
        priceField.setBounds(200, 300, 200, 30);
        this.getContentPane().add(priceField);

        ok = new JButton("OK");
        ok.setOpaque(false);
        ok.setContentAreaFilled(false);
        ok.setBorderPainted(false);
        ok.setForeground(Color.BLACK);
        ok.setFont(new Font("Impact", Font.PLAIN, 20));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Actions actions = new Actions();
                actions.modifyProduct(products, titleField.getText(), ratingField.getText(), caloriesField.getText(), proteinField.getText(), fatField.getText(), sodiumField.getText(), priceField.getText());
            }
        });
        ok.setBounds(200, 350, 200, 30);
        contentPane.add(ok);

        ArrayList<bll.MenuItem> list = new ArrayList<>(Start.menuitemslist.values());
        String[] smt = {};
        products = new JComboBox<>(smt);
        for (MenuItem menuItem : list) {
            products.addItem(menuItem.getTitle());
        }
        products.setBounds(150, 20, 330, 20);
        this.getContentPane().add(products);
    }
}
