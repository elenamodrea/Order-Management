package bll;


import dao.OrdersDAO;
import model.Orders;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * aceasta clasa este folosita pnetru a face legatura intre view si model
 */
public class OrdersBLL {
    private OrdersDAO orderDAO;

    /**
     * un constructor ce initializeaza orderDAO
     */
    public OrdersBLL() {
        orderDAO=new OrdersDAO();
    }

    /**
     * aceasta metoda instantiaza metoda findById din OrdersDAO
     * @param id
     * @return un obiect de tip Orders
     */
    public Orders findOrderById(int id) {
        Orders st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * aceasta metoda instantiaza metoda findAll din OrdersDAO
     * @return o lista de obiecte de tip Orders
     */
    public List<Orders> findOrdersAll(){
        List<Orders>orderList=orderDAO.findAll();
       /* if(orderList.isEmpty())
            System.out.println("nu s-au gasit clienti in tabela");*/
        return orderList;
    }

    /**
     * aceasta metoda instantiaza metoda insert din OrdersDAO
     * @param t
     * @throws IllegalAccessException
     */
    public void insertOrder(Orders t) throws IllegalAccessException {
        orderDAO.insert(t);

    }

    /**
     * aceasta metoda instantiaza metoda update din OrdersDAO
     * @param t
     * @param id
     * @throws IllegalAccessException
     */
    public void updateOrder(Orders t, int id) throws IllegalAccessException {
        orderDAO.update(t, id);

    }

    /**
     * aceasta metoda instantiaza metoda delete din OrdersDAO
     * @param id
     * @throws IllegalAccessException
     */
    public void deleteOrder(int id) throws IllegalAccessException {
       orderDAO.delete(id);

    }

    /**
     * aceasta metoda instantiaza metoda tableModel din OrdersDAO
     * @param list
     * @return returneaza un tabel
     * @throws IllegalAccessException
     */
    public DefaultTableModel tableModelOrder (List<Orders> list) throws IllegalAccessException {
        DefaultTableModel table= orderDAO.tableModel(list);
        return table;
    }
}
