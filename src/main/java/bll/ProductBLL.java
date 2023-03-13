package bll;



import dao.ProductDAO;

import model.Product;

import javax.sound.sampled.Port;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * aceasta clasa e folosita pentru a face legatura intre model si view
 */
public class ProductBLL {
    private ProductDAO productDAO;

    /**
     * este un constructor in care initilizam productDAO
     */
    public ProductBLL() {
        productDAO=new ProductDAO();
    }

    /**
     * aceasta metoda instantiaza metoda findById din ProductDAO
     * @param id
     * @return un obiect de tip Product
     */
    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return st;
    }

    /**
     * aceasta metoda instantiaza metoda findAll din ProductDAO
     * @return  retuneaza lista de obiecte de tip product
     */
    public List<Product> findProductsAll(){
        List<Product>productList=productDAO.findAll();
        if(productList.isEmpty())
            System.out.println("nu s-au gasit clienti in tabela");
        return productList;
    }

    /**
     * aceasta metoda instantiaza metoda insert din ProductDAO
     * @param t
     * @throws IllegalAccessException
     */
    public void insertProduct(Product t) throws IllegalAccessException {
        productDAO.insert(t);

    }

    /**
     * aceasta metoda instantiaza metoda update din ProductDAO
     * @param t
     * @param id
     * @throws IllegalAccessException
     */
    public void updateProduct(Product t, int id) throws IllegalAccessException {
        productDAO.update(t, id);

    }

    /**
     * aceasta metoda instantiaza metoda delete din ProductDAO
     * @param id
     * @throws IllegalAccessException
     */
    public void deleteProduct(int id) throws IllegalAccessException {
        productDAO.delete(id);

    }

    /**
     * aceasta metoda instantiaza metoda tableModel din ProductDAO
     * @param list
     * @return un tabel
     * @throws IllegalAccessException
     */
    public DefaultTableModel tableModelProduct (List<Product> list) throws IllegalAccessException {
        DefaultTableModel table= productDAO.tableModel(list);
        return table;
    }

    public ProductDAO getProductDAO() {
        return productDAO;
    }
}
