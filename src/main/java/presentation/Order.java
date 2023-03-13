package presentation;

import bll.ClientBLL;
import bll.OrdersBLL;
import bll.ProductBLL;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.List;

/**
 * aceasta clasa repezinta interfata grafica pentru Order
 */
public class Order extends JFrame{
    private JPanel order;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton EXITButton;
    private JButton BACKButton;
    private JButton ADDButton;
    private JTextField textField5;
    private JButton BILLButton;
    private JButton VIZUALIZAREButton;
    private OrdersBLL orderBLL;
    private ProductBLL productBLL;
    private ClientBLL clientBLL;

    /**
     * in constructor avem toata functionalitatea interfetei
     */
    public Order(){
        orderBLL=new OrdersBLL();
        productBLL=new ProductBLL();
        clientBLL=new ClientBLL();
        setContentPane(order);
        setTitle("Order");
        setSize(900, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        List<Client> clientList=clientBLL.findClientsAll();
        List<Product> productList=productBLL.findProductsAll();
        for (Client t:clientList) {
            comboBox1.addItem(t.getId());
        }
        for (Product t: productList) {
            comboBox2.addItem(t.getId());
        }

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
        VIZUALIZAREButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               int comb1= (int) comboBox1.getSelectedItem();
               int comb2=(int) comboBox2.getSelectedItem();
               Client client1=clientBLL.findClientById(comb1);
               Product product1=productBLL.findProductById(comb2);
               textField1.setText(client1.getName());
               textField2.setText(product1.getName());
               String cantit=Integer.toString(product1.getCantitate());
               textField3.setText(cantit);


            }
        });
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int comb1= (int) comboBox1.getSelectedItem();
                int comb2=(int) comboBox2.getSelectedItem();
                Client client1=clientBLL.findClientById(comb1);
                System.out.println(client1.toString());

                Product product1=productBLL.findProductById(comb2);
                System.out.println(product1.toString());
                String cantitateIntrodusaString=textField4.getText();
                int cantitIntrodusa=Integer.parseInt(cantitateIntrodusaString);
                int cantitRamasa=productBLL.getProductDAO().verifcareCantitate(comb2,cantitIntrodusa);
                if(cantitRamasa<0)
                     textField5.setText("Stoc insuficient!");
                else {
                    Orders order = new Orders(comb1, comb2, client1.getName(), product1.getName(),cantitIntrodusa);
                    try {
                        orderBLL.insertOrder(order);
                        productBLL.getProductDAO().actualizeazaCantit(cantitRamasa,comb2,product1);

                    } catch (IllegalAccessException ex) {
                        ex.printStackTrace();
                    }

                    textField5.setText("Tranzactie reusita!");
                }


            }
        });
        BILLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter myWriter = new FileWriter("Bil.txt");

                    String o="id idClient idProduct numClient numeProdus cantitate \n";
                     List<Orders> list=orderBLL.findOrdersAll();
                       Orders t=list.get(list.size()-1);
                        o+=t.getId()+" "+t.getIdClient()+" "+t.getIdProduct()+" "+t.getNumClient()+" "+t.getNumeProdus()+" "+t.getCantitate()+"\n";

                    myWriter.write(o);
                    myWriter.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }


            }
        });
    }
}
