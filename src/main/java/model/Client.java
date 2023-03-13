package model;

/**
 * Aceasta clasa este echivalentul tabelei Client din baza de date;
 * atributele acesteia sunt coloanele din tabela: id, name, email, address.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;

    /**
     *  este un constructor gol
     */
    public Client(){

    }

    /**
     * este un constructor pentru a putea initializa toate atributele clasei
     * @param name
     * @param email
     * @param address
     */
    public Client(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
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
     * @return returneaza atributul name
     */
    public String getName() {
        return name;
    }

    /**
     * seteaza atributul nume
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returneaza emailul
     */
    public String getEmail() {
        return email;
    }

    /**
     * seteaza atributul email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return returneaza adresa
     */
    public String getAddress() {
        return address;
    }

    /**
     * seteaza adresa
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return returneaza toString ul acestei clase
     */
    @Override
    public String toString() {
        return "client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
