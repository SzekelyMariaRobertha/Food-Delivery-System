package presentation;

import bll.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the appropriate interface when an admin was logged into the app
 *
 * @author Szekely Maria-Robertha
 * @see JFrame
 */

public class AdministratorGUI extends JFrame {

    public AdministratorGUI() {

        Image img = Toolkit.getDefaultToolkit().getImage("src\\main\\resources\\mann.jpg");
        this.setContentPane(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, null);
            }
        });

        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(750, 500));
        this.getContentPane().setBounds(0, 0, 300, 300);
        this.getContentPane().setLayout(null);
        setTitle(String.valueOf(who.ADMINISTRATOR));
        pack();
        setVisible(true);


        JButton imp = new JButton("Import products");
        imp.setFont(new Font("Impact", Font.PLAIN, 18));
        imp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Actions actions = new Actions();
                actions.importProducts();
            }
        });
        imp.setBounds(275, 70, 210, 20);
        this.getContentPane().add(imp);

        JButton addP = new JButton("Add product");
        addP.setFont(new Font("Impact", Font.PLAIN, 18));
        addP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddProduct();
            }
        });
        addP.setBounds(275, 95, 210, 20);
        this.getContentPane().add(addP);


        JButton delete = new JButton("Delete product");
        delete.setFont(new Font("Impact", Font.PLAIN, 18));
        delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DeleteProduct();
            }
        });
        delete.setBounds(275, 120, 210, 20);
        this.getContentPane().add(delete);

        JButton modify = new JButton("Modify product");
        modify.setFont(new Font("Impact", Font.PLAIN, 18));
        modify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ModifyProduct();
            }
        });
        modify.setBounds(275, 145, 210, 20);
        this.getContentPane().add(modify);

        JButton create = new JButton("Create comp. product");
        create.setFont(new Font("Impact", Font.PLAIN, 18));
        create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CreateComposite();
            }
        });
        create.setBounds(275, 170, 210, 20);
        this.getContentPane().add(create);

        JButton generate = new JButton("Generate reports");
        generate.setFont(new Font("Impact", Font.PLAIN, 18));
        generate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Reports();
            }
        });
        generate.setBounds(510, 95, 210, 20);
        this.getContentPane().add(generate);
    }
}
