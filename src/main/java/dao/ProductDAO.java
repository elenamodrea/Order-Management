package dao;

import model.Product;

/**
 * Aceasta clasa este folosit pentru a extinde abstractDAO ce va avea ca tipul obiectului Product
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * aceasta metoda verifica cantitatea ramasa dupa cumpararea unui numar de produse
     * @param id
     * @param cantit
     * @return cantitatea ramasa
     */
    public int verifcareCantitate(int id,int cantit){
        Product product=findById(id);
        int cantitInitiala=product.getCantitate();
        int cantitFinala=cantitInitiala-cantit;
        return cantitFinala;
        }

    /**
     * aceasta metoda actualizeaza cantitatea in baza de date numai daca este pozitiva
     *
     * @param cantit
     * @param id
     * @param productInit
     * @throws IllegalAccessException
     */
        public void actualizeazaCantit(int cantit, int id, Product productInit) throws IllegalAccessException {
        if(cantit>=0)
        {Product product=new Product(productInit.getName(), cantit, productInit.getPret());
        update(product,id);}
        }
}
