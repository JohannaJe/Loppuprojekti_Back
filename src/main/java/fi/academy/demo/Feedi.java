package fi.academy.demo;

public class Feedi {
    private String otsikko;
    private String linkki;
    private String aikaleima;
    private String kuva;


    public Feedi(String otsikko, String linkki, String aikaleima, String kuva) {
        this.otsikko = otsikko;
        this.linkki = linkki;
        this.aikaleima = aikaleima;
        this.kuva = kuva;
    }

    public Feedi() {
    }

    public String getOtsikko() {
        return otsikko;
    }

    public void setOtsikko(String otsikko) {
        this.otsikko = otsikko;
    }

    public String getLinkki() {
        return linkki;
    }

    public void setLinkki(String linkki) {
        this.linkki = linkki;
    }

    public String getAikaleima() {
        return aikaleima;
    }

    public void setAikaleima(String aikaleima) {
        this.aikaleima = aikaleima;
    }

    public String getKuva() {
        return kuva;
    }

    public void setKuva(String kuva) {
        this.kuva = kuva;
    }

    @Override
    public String toString() {
        return "Feedi{" +
                "otsikko='" + otsikko + '\'' +
                ", linkki='" + linkki + '\'' +
                ", aikaleima='" + aikaleima + '\'' +
                '}';
    }
}
