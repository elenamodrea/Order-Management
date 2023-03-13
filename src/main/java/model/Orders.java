package model;

/**
 * Aceasta clasa este echivalentul tabelei Orders din baza de date;
 * Atributele clasei sunt coloanele tabelei: id, idClient, idProduct, numClient, numeProdus,cantitate.
 */
public class Orders {
    private int id;
    private int idClient;
    private int idProduct;
    private String numClient;
    private String numeProdus;
    private int cantitate;

    /**
     * este un constructor gol
     */
    public Orders() {

    }

    /**
     * este un constructor care initializeaza toate atributele clasei
     * @param idClient
     * @param idProduct
     * @param numClient
     * @param numeProdus
     * @param cantitate
     */
    public Orders(int idClient, int idProduct, String numClient, String numeProdus, int cantitate) {
        this.idClient = idClient;
        this.idProduct = idProduct;
        this.numClient = numClient;
        this.numeProdus = numeProdus;
        this.cantitate = cantitate;
    }

    /**
     *
     * @return returneaza id ul
     */
    public int getId() {
        return id;
    }

    /**
     * seteaza id ul
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return returneaza idClient
     */
    public int getIdClient() {
        return idClient;
    }

    /**
     * seteaza idClient
     * @param idClient
     */
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    /**
     *
     * @return returneaza idProduct
     */
    public int getIdProduct() {
        return idProduct;
    }

    /**
     * seteaza idProduct
     * @param idProduct
     */
    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    /**
     *
     * @return returneaza NumClient
     */
    public String getNumClient() {
        return numClient;
    }

    /**
     * seteaza NumClient
     * @param numClient
     */
    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    /**
     *
     * @return returneaza NumeProdus
     */
    public String getNumeProdus() {
        return numeProdus;
    }

    /**
     * seteaza numeProdus
     * @param numeProdus
     */
    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    /**
     *
     * @return returneaza Cantitate
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * seteaza cantitate
     * @param cantitate
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     *
     * @return toString-ul acestei clase;
     */
    @Override
    public String toString() {
        return "order{" +
                "id=" + id +
                ", idClient=" + idClient +
                ", idProduct=" + idProduct +
                ", numClient='" + numClient + '\'' +
                ", numeProdus='" + numeProdus + '\'' +
                ", cantitate=" + cantitate +
                '}';
    }
}
