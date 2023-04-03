package presentation;

import bll.Actions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the appropriate interface to add a product into a map using new data from the interface
 *
 * @author Szekely Maria-Robertha
 * @see JFrame
 */

public class AddProduct extends JFrame {

    private JTextField titleField;
    private JTextField ratingField;
    private JTextField caloriesField;
    private JTextField proteinField;
    private JTextField fatField;
    private JTextField sodiumField;
    private JTextField priceField;
    private JButton ok;

    public AddProduct() {

        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\newpr.jpg");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(500, 420));
        //setResizable(false);
        this.getContentPane().setBounds(0, 0, 500, 420);
        this.getContentPane().setLayout(null);
        setTitle("ADD PRODUCT");
        pack();


        JLabel idLabel = new JLabel("New infos");
        idLabel.setForeground(Color.BLACK);
        idLabel.setFont(new Font("Impact", Font.PLAIN, 25));
        idLabel.setBounds(40, 20, 200, 30);
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
                actions.addProduct(titleField.getText(), ratingField.getText(), caloriesField.getText(), proteinField.getText(), fatField.getText(), sodiumField.getText(), priceField.getText());
            }
        });
        ok.setBounds(300, 20, 200, 30);
        this.getContentPane().add(ok);

        setVisible(true);
    }
}
