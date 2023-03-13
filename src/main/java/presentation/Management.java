package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * aceasta clasa reprezinta interfata grafica pentru Management
 */
public class Management extends JFrame {
    private JButton clientButton;
    private JButton productButton;
    private JButton orderButton;
    private JButton EXITButton;
    private JPanel management;

    /**
     * in constructor avem toata functionalitatea interfetei
     */
    public Management(){
        setContentPane(management);
        setTitle("management");
        setSize(900,600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        clientButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
                new Client();}
        });
        productButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
                new Product();}
        });
        orderButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                dispose();
                new Order();}
        });
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
    }
}

