package ticket;

public class Ticket {

    private int id;
    private double preis;
    private String konzert;

    public Ticket(double preis, String konzert) {
        this.preis = preis;
        this.konzert = konzert;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getKonzert() {
        return konzert;
    }

    public void setKonzert(String konzert) {
        this.konzert = konzert;
    }
}
