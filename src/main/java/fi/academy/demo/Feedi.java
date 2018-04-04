package fi.academy.demo;

import java.time.LocalDateTime;

public class Feedi {
    private String otsikko;
    private String linkki;
    private LocalDateTime aikaleima;
    private String kuva;

    public LocalDateTime getAikaleima() {
        return aikaleima;
    }

    public void setAikaleima(LocalDateTime aikaleima) {
        this.aikaleima = aikaleima;
    }

    public Feedi(String otsikko, String linkki, LocalDateTime aikaleima, String kuva) {
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
