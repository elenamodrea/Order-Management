package bll;

import dao.ClientDAO;
import model.Client;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * aceasta clasa este folosita pentru a face legatura intre view si model
 */
public class ClientBLL {
    private ClientDAO clientDAO;

    /**
     * este un constructor in care initializam clientDAO
     */
    public ClientBLL() {
        clientDAO=new ClientDAO();
    }

    /**
     * in aceasta metoda vom instantia metoda findById din ClientDAO
     * @param id
     * @return returneaza un obiect de tip Client
     */
    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * aceasta metoda instantiaza metoda findAll din ClientDAO
     * @return o lista de obiecte de tip Client
     */
    public List<Client> findClientsAll(){
        List<Client>clientList=clientDAO.findAll();
        if(clientList.isEmpty())
            System.out.println("nu s-au gasit clienti in tabela");
        return clientList;
    }

    /**
     * aceasta metoda instantiaza metoda insert din ClientDAO
     * @param t
     * @throws IllegalAccessException
     */
    public void insertCLient(Client t) throws IllegalAccessException {
        clientDAO.insert(t);

    }

    /**
     * aceasta metoda instantiaza metoda update din ClientDAO
     * @param t
     * @param id
     * @throws IllegalAccessException
     */
    public void updateClient(Client t, int id) throws IllegalAccessException {
       clientDAO.update(t, id);

    }

    /**
     * aceasta metoda instantiaza metoda delete din ClientDAO
     * @param id
     * @throws IllegalAccessException
     */
    public void deleteClient(int id) throws IllegalAccessException {
        clientDAO.delete(id);

    }

    /**
     * aceasta metoda instantiaza metoda tableModel din ClientDAO
     * @param list
     * @return
     * @throws IllegalAccessException
     */
    public DefaultTableModel tableModelClient (List<Client> list) throws IllegalAccessException {
       DefaultTableModel table= clientDAO.tableModel(list);
        return table;
    }
}
