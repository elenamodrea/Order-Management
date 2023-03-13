package model;

/**
 * Aceasta clasa este echvalentul tabelei Product din baza de date;
 * Atributele ei sunt coloanele tabelei: id, name, cantitate, pret
 */
public class Product {
    private int id;
    private String name;
    private int cantitate;
    private int pret;

    /**
     * este un constructor gol
     */
    public Product() {
    }

    /**
     * este un constructor ce initializeaza toate atributele clasei
     * @param name
     * @param cantitate
     * @param pret
     */
    public Product( String name, int cantitate, int pret) {

        this.name = name;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    /**
     *
     * @return returneaza id ul
     */
    public int getId() {
        return id;
    }

    /**
     * setaza id ul
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return returneaza numele
     */
    public String getName() {
        return name;
    }

    /**
     * seteaza numele
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returneaza cantitatea
     */
    public int getCantitate() {
        return cantitate;
    }

    /**
     * seteaza cantitatea
     * @param cantitate
     */
    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    /**
     *
     * @return returneaza pret
     */
    public int getPret() {
        return pret;
    }

    /**
     * seteaza pret
     * @param pret
     */
    public void setPret(int pret) {
        this.pret = pret;
    }

    /**
     *
     * @return returneaza toString-ul acestei clase
     */
    @Override
    public String toString() {
        return "product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cantitate=" + cantitate +
                ", pret=" + pret +
                '}';
    }
}
