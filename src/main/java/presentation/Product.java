package presentation;

import bll.ProductBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * aceasta clasa reprezinta interfata grafica pentru Product
 */
public class Product extends JFrame{
    private JPanel product;
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton ADDButton;
    private JTextField textField3;
    private JButton DELETEButton;
    private JButton UPDATEButton;
    private JButton SHOWPRODUCTSButton;
    private JButton BACKButton;
    private JButton EXITButton;
    private JTextField textField4;
    private ProductBLL productBLL;

    /**
     * in constructor avem toata functionalitatea interfetei grafice
     */
    public Product(){
        productBLL=new ProductBLL();
        setContentPane(product);
        setTitle("Product");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        EXITButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        BACKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Management();

            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume=textField1.getText();
                String cantitateString=textField2.getText();
                String pretString=textField4.getText();
                int cantit=Integer.parseInt(cantitateString);
                int pret=Integer.parseInt(pretString);
                model.Product product=new model.Product(nume,cantit,pret);

                try {
                    productBLL.insertProduct(product);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume=textField1.getText();
                String cantitateString=textField2.getText();
                String pretString=textField4.getText();
                String numeF=new String();

                String idString=textField3.getText();
                int id=Integer.parseInt(idString);
                model.Product p=productBLL.findProductById(id);
                int cantitF,pretF;
                if(nume.isEmpty())
                    numeF=p.getName();
                else numeF=nume;
                if(cantitateString.isEmpty())
                    cantitF=p.getCantitate();
                else {int cantit=Integer.parseInt(cantitateString);
                    cantitF=cantit;}
                if(pretString.isEmpty())
                    pretF=p.getPret();
                else {int pret=Integer.parseInt(pretString);
                    pretF=pret;}


                model.Product product=new model.Product(numeF,cantitF,pretF);


                try {
                    productBLL.updateProduct(product,id);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idString=textField3.getText();
                int id=Integer.parseInt(idString);
                try {
                    productBLL.deleteProduct(id);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        SHOWPRODUCTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table1.setModel(productBLL.tableModelProduct(productBLL.findProductsAll()));
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }
}
