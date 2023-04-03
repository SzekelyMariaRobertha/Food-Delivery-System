package presentation;

import bll.Actions;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the appropriate interface for an admin when he wants to generate reports
 * The admin must introduce some value into certain text-fields depending on what report he wants to generate
 *
 * @author Szekely Maria-Robertha
 * @see Actions
 * @see JFrame
 */

public class Reports extends JFrame {

    private JTextField start;
    private JTextField end;

    private JTextField productNumber;

    private JTextField clientNumber;
    private JTextField value;

    private JTextField day;

    private JButton ok;
    private JPanel contentPane;
    private JComboBox<String> option;

    public Reports() {

        setSize(300, 560);
        setLocationRelativeTo(null);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(255, 204, 204));
        setContentPane(contentPane);
        setLayout(null);

        String[] product = {"Start-End", "Pr. ordered more than", "Cl. ordered more than", "Day"};
        option = new JComboBox<>(product);
        option.setBounds(40, 0, 200, 20);
        contentPane.add(option);

        JLabel startLabel = new JLabel("Start hour");
        startLabel.setForeground(Color.BLACK);
        startLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        startLabel.setBounds(100, 20, 200, 30);
        contentPane.add(startLabel);

        JLabel endLabel = new JLabel("End hour");
        endLabel.setForeground(Color.BLACK);
        endLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        endLabel.setBounds(100, 100, 200, 30);
        contentPane.add(endLabel);

        JLabel pLabel = new JLabel("Pr. times");
        pLabel.setForeground(Color.BLACK);
        pLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        pLabel.setBounds(100, 180, 200, 30);
        contentPane.add(pLabel);

        JLabel caloriesLabel = new JLabel("Cl. times");
        caloriesLabel.setForeground(Color.BLACK);
        caloriesLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        caloriesLabel.setBounds(100, 260, 200, 30);
        contentPane.add(caloriesLabel);

        JLabel valuelLabel = new JLabel("Value");
        valuelLabel.setForeground(Color.BLACK);
        valuelLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        valuelLabel.setBounds(100, 340, 200, 30);
        contentPane.add(valuelLabel);

        JLabel dayLabel = new JLabel("Day");
        dayLabel.setForeground(Color.BLACK);
        dayLabel.setFont(new Font("Impact", Font.PLAIN, 20));
        dayLabel.setBounds(100, 420, 200, 30);
        contentPane.add(dayLabel);

        start = new JTextField();
        start.setForeground(Color.BLACK);
        start.setFont(new Font("Impact", Font.PLAIN, 18));
        start.setBounds(90, 60, 100, 30);
        contentPane.add(start);

        end = new JTextField();
        end.setForeground(Color.BLACK);
        end.setFont(new Font("Impact", Font.PLAIN, 18));
        end.setBounds(90, 140, 100, 30);
        contentPane.add(end);

        productNumber = new JTextField();
        productNumber.setForeground(Color.BLACK);
        productNumber.setFont(new Font("Impact", Font.PLAIN, 18));
        productNumber.setBounds(90, 220, 100, 30);
        contentPane.add(productNumber);

        clientNumber = new JTextField();
        clientNumber.setForeground(Color.BLACK);
        clientNumber.setFont(new Font("Impact", Font.PLAIN, 18));
        clientNumber.setBounds(90, 300, 100, 30);
        contentPane.add(clientNumber);

        value = new JTextField();
        value.setForeground(Color.BLACK);
        value.setFont(new Font("Impact", Font.PLAIN, 18));
        value.setBounds(90, 380, 100, 30);
        contentPane.add(value);

        day = new JTextField();
        day.setForeground(Color.BLACK);
        day.setFont(new Font("Impact", Font.PLAIN, 18));
        day.setBounds(90, 460, 100, 30);
        contentPane.add(day);

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
                String opt = option.getSelectedItem().toString();
                switch (opt) {
                    case "Start-End":
                        if (!start.getText().equals("") && !end.getText().equals("")) {
                            actions.btwSEHour(start.getText(), end.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Incomplete data (start-end)");
                        }
                        break;
                    case "Pr. ordered more than":
                        if (!productNumber.getText().equals("")) {
                            actions.productsOrderedMoreThan(productNumber.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Incomplete data (pr. number)");
                        }
                        break;
                    case "Cl. ordered more than":
                        if (!clientNumber.getText().equals("") && !value.getText().equals("")) {
                            actions.clientsOrderedMoreThan(clientNumber.getText(), value.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Incomplete data (cl. number-value)");
                        }
                        break;
                    case "Day":
                        if (!day.getText().equals("")) {
                            actions.specifiedDay(day.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Incomplete data (day)");
                        }
                        break;
                }
            }
        });
        ok.setBounds(100, 500, 100, 30);
        contentPane.add(ok);

        setVisible(true);
    }
}
