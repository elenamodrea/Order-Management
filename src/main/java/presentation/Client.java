package presentation;

import bll.ClientBLL;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * aceasta clasa reprezinta interfata grafica pentru Client
 */

public class Client extends JFrame{
    private JTable table1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ADDButton;
    private JButton DELETEButton;
    private JButton SHOWCLIENTSButton;
    private JButton BACKButton;
    private JButton EXITButton;
    private JButton UPDATEButton;
    private JPanel Client;
    private ClientBLL clientBLL;

    /**
     * in constructor avem toata functionalitatea interfetei
     */
    public Client(){
        clientBLL=new ClientBLL();
        setContentPane(Client);
        setTitle("Client");
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
            String email=textField3.getText();
            String adresa=textField2.getText();
            model.Client client=new model.Client(nume,email,adresa);

               try {
                    clientBLL.insertCLient(client);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        UPDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nume=textField1.getText();
                String email=textField3.getText();
                String adresa=textField2.getText();
                String idString=textField4.getText();
                String numeF=new String();
                String emailF=new String();
                String adresaF=new String();
                int id=Integer.parseInt(idString);
                model.Client c=clientBLL.findClientById(id);
                if(nume.isEmpty())
                    numeF=c.getName();
                else numeF=nume;
                if(email.isEmpty())
                    emailF=c.getEmail();
                else emailF=email;
                if(adresa.isEmpty())
                    adresaF=c.getAddress();
                else adresaF=adresa;
                model.Client client=new model.Client(numeF,emailF,adresaF);
                try {
                    clientBLL.updateClient(client,id);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String idString=textField4.getText();
                int id=Integer.parseInt(idString);
                try {
                    clientBLL.deleteClient(id);
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }
            }
        });
        SHOWCLIENTSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table1.setModel(clientBLL.tableModelClient(clientBLL.findClientsAll()));
                } catch (IllegalAccessException ex) {
                    ex.printStackTrace();
                }

            }
        });

    }
}
