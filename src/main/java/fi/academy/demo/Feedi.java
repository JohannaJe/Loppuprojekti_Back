package fi.academy.demo;

public class Feedi {
    private String otsikko;
    private String linkki;
    private String aikaleima;

    public Feedi(String otsikko, String linkki, String aikaleima) {
        this.otsikko = otsikko;
        this.linkki = linkki;
        this.aikaleima = aikaleima;
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

    @Override
    public String toString() {
        return "Feedi{" +
                "otsikko='" + otsikko + '\'' +
                ", linkki='" + linkki + '\'' +
                ", aikaleima='" + aikaleima + '\'' +
                '}';
    }
}
