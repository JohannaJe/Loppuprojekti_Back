package fi.academy.demo;

import java.io.*;
import java.net.*;
import java.util.*;

public class Lukija {


    List<String> linkit = new ArrayList<>();
    List<String> otsikot = new ArrayList<>();

    List<String> ajat = new ArrayList<>();
    List<String> kuvat = new ArrayList<>();
    ArrayList feediLista = new ArrayList();


    public List haeLinkki(String url) {

        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String linkki = "";
            String rivi;
            int aloitaHaku = 0;
            while ((rivi = luettava.readLine()) != null) {
                if (rivi.contains("<item>")) {
                    aloitaHaku = 1;
                }
                if (aloitaHaku == 1) {


                    if (rivi.contains("<link>")) {
                        int eka = rivi.indexOf("<link>");
                        String muokattuRivi = rivi.substring(eka);
                        muokattuRivi = muokattuRivi.replace("<link>", "");
                        int vika = muokattuRivi.indexOf("</link>");
                        muokattuRivi = muokattuRivi.substring(0, vika);
                        linkki += muokattuRivi;
                        linkit.add(linkki);
                        linkki = "";
                    }

                }
            }

            luettava.close();
            return linkit;
        } catch (MalformedURLException urle) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Eipä tainnut onnistua");
        }
        return null;
    }


    public List haeKuva(String url) {

        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String otsikko = "";
            String rivi;
            int aloitaHaku = 0;

            while ((rivi = luettava.readLine()) != null) {

                if (rivi.contains("<item>")) {
                    aloitaHaku = 1;
                }
                if (aloitaHaku == 1) {
                    if (rivi.contains("media:content url=\"")) {
                        int eka = rivi.indexOf("media:content url=\"");
                        String muokattuRivi = rivi.substring(eka);
                        muokattuRivi = muokattuRivi.replace("media:content url=\"", "");
                        int vika = muokattuRivi.indexOf("\" type=\"image/jpeg\" width=\"468\"/>");  // toimiva ]]></title>
                        muokattuRivi = muokattuRivi.substring(0, vika);
                        otsikko += muokattuRivi;
                        kuvat.add(otsikko);
                        otsikko = "";


                    }
                }
            }

            luettava.close();
            return kuvat;
        } catch (MalformedURLException urle) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Eipä tainnut onnistua");
        }
        return null;
    }


    public List haeOtsikko(String url) {

        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String otsikko = "";
            String rivi;
            int aloitaHaku = 0;

            while ((rivi = luettava.readLine()) != null) {

                if (rivi.contains("<item>")) {
                    aloitaHaku = 1;
                }
                if (aloitaHaku == 1) {
                    if (rivi.contains("<title>")) {
                        int eka = rivi.indexOf("<title>");
                        String muokattuRivi = rivi.substring(eka);
                        muokattuRivi = muokattuRivi.replace("<title>", "");
                        if (muokattuRivi.contains("<![CDATA[")) {
                            muokattuRivi = muokattuRivi.replace("<![CDATA[", "");
                        }
                        int vika = muokattuRivi.indexOf("</title>");  // toimiva ]]></title>
                        if (muokattuRivi.contains("]]></title>")) {
                            vika = muokattuRivi.indexOf("]]></title>");
                        }
                        muokattuRivi = muokattuRivi.substring(0, vika);
                        otsikko += muokattuRivi;
                        otsikot.add(otsikko);
                        otsikko = "";


                    }
                }
            }

            luettava.close();
            return otsikot;
        } catch (MalformedURLException urle) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Eipä tainnut onnistua");
        }
        return null;
    }

    public List haeAika(String url) {
//        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
//        Date date = formatter.parse("Sat, 24 Apr 2010 14:01:00 GMT");

        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String aika = "";
            String rivi;
            int aloitaHaku = 0;
            while ((rivi = luettava.readLine()) != null) {


                if (rivi.contains("<item>")) {
                    aloitaHaku = 1;
                }
                if (aloitaHaku == 1) {
                    if (rivi.contains("<pubDate>")) {
                        int eka = rivi.indexOf("<pubDate>");
                        String muokattuRivi = rivi.substring(eka);
                        muokattuRivi = muokattuRivi.replace("<pubDate>", "");
                        int vika = muokattuRivi.indexOf("</pubDate>");
                        muokattuRivi = muokattuRivi.substring(0, vika);
                        aika += muokattuRivi;
                        ajat.add(aika);
                        aika = "";
                        // aika Stringin muunto Date-olioksi??


                    }
                }
            }
            luettava.close();
            return ajat;
        } catch (MalformedURLException urle) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Eipä tainnut onnistua");
        }
        return null;

    }


    public List koostaLista(String url) {
        haeOtsikko(url);
        haeLinkki(url);
        haeAika(url);
        haeKuva(url);
        for (int i = 0; i < kuvat.size(); i++) {
            feediLista.add(new Feedi(otsikot.get(i), linkit.get(i), ajat.get(i), kuvat.get(i)));


        }
        return feediLista;
    }
}
