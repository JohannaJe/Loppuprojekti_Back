package fi.academy.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Feedi {
    private String otsikko;
    private String linkki;
    private LocalDateTime aikaleima;
    private String kuva;
    private String aikaleimaString;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    public LocalDateTime getAikaleima() {
        return aikaleima;
    }

    public Feedi(LocalDateTime aikaleima) {
        this.aikaleima = aikaleima;
    }

    public void setAikaleima(LocalDateTime aikaleima) {
        this.aikaleima = aikaleima;
    }

    public Feedi(String otsikko, String linkki, LocalDateTime aikaleima, String kuva) {
        this.otsikko = otsikko;
        this.linkki = linkki;
        this.aikaleima = aikaleima;
        this.kuva = kuva;
        this.aikaleimaString=aikaleima.format(formatter);

    }

    public Feedi(String otsikko, String linkki, LocalDateTime aikaleima) {
        this.otsikko = otsikko;
        this.linkki = linkki;
        this.aikaleima = aikaleima;
    }

    public String getAikaleimaString() {
        return aikaleimaString;
    }

    public void setAikaleimaString(String aikaleimaString) {
        this.aikaleimaString = aikaleimaString;
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
