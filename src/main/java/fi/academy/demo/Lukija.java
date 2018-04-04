package fi.academy.demo;

import java.io.*;
import java.net.*;
import java.util.*;

public class Lukija {

    Feedi eka= new Feedi();
    List<String> linkit = new ArrayList<>();
    List<String> otsikot = new ArrayList<>();
    List<String> ajat = new ArrayList<>();
    List<String> kuvat = new ArrayList<>();
    ArrayList mega = new ArrayList();




    public List haeLinkki(String url) {

        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String linkki = "";
            String rivi;
            int x = 0;
            while ((rivi = luettava.readLine()) != null) {
                if (rivi.contains("<item>")) {
                    x = 1;
                }
                if (x == 1) {


                if (rivi.contains("<link>")) {
                    int eka = rivi.indexOf("<link>");
                    String jotain = rivi.substring(eka);
                    jotain = jotain.replace("<link>", "");
                    int vika = jotain.indexOf("</link>");
                    jotain = jotain.substring(0, vika);
                    linkki += jotain;
                    linkit.add(linkki);
                    linkki="";
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
            int x = 0;

            while ((rivi = luettava.readLine()) != null) {

                if (rivi.contains("<item>")) {
                    x = 1;
                }
                if (x == 1) {
                    if (rivi.contains("media:content url=\"")) {
                        int eka = rivi.indexOf("media:content url=\"");
                        String jotain = rivi.substring(eka);
                        jotain = jotain.replace("media:content url=\"", "");
                        int vika = jotain.indexOf("\" type=\"image/jpeg\" width=\"468\"/>");  // toimiva ]]></title>
                        jotain = jotain.substring(0, vika);
                        otsikko += jotain;
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
            int x = 0;

            while ((rivi = luettava.readLine()) != null) {

                if (rivi.contains("<item>")) {
                    x = 1;
                }
                if (x == 1) {
                    if (rivi.contains("<title>")) {
                        int eka = rivi.indexOf("<title>");
                        String jotain = rivi.substring(eka);
                        jotain = jotain.replace("<title>", "");
                        if (jotain.contains("<![CDATA[")) {
                            jotain = jotain.replace("<![CDATA[", "");
                        }
                        int vika = jotain.indexOf("</title>");  // toimiva ]]></title>
                        if (jotain.contains("]]></title>")) {
                            vika = jotain.indexOf("]]></title>");
                        }
                        jotain = jotain.substring(0, vika);
                        otsikko += jotain;
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
            int x = 0;
            while ((rivi = luettava.readLine()) != null) {


                if (rivi.contains("<item>")) {
                    x = 1;
                }
                if (x == 1) {
                    if (rivi.contains("<pubDate>")) {
                        int eka = rivi.indexOf("<pubDate>");
                        String jotain = rivi.substring(eka);
                        jotain = jotain.replace("<pubDate>", "");
                        int vika = jotain.indexOf("</pubDate>");
                        jotain = jotain.substring(0, vika);
                        aika += jotain;
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


// toimiva otsikkohaku "roskilla"
//while ((rivi = luettava.readLine()) != null) {
//        if (rivi.contains("<title><![CDATA[")) {
//            int eka = rivi.indexOf("<title><![CDATA[");
//            String jotain = rivi.substring(eka);
//            jotain = jotain.replace("<title><![CDATA[", "");
//            int vika = jotain.indexOf("]]></title>");
//            jotain = jotain.substring(0, vika);
//            otsikko += jotain+"\n";
//            //  otsikot.add(otsikko);


    public List koostaLista(String url) {
        haeOtsikko(url);
        haeLinkki(url);
        haeAika(url);
        haeKuva(url);
        for (int i = 0; i < kuvat.size(); i++) {
            mega.add(new Feedi(otsikot.get(i), linkit.get(i), ajat.get(i), kuvat.get(i)));


        }
    return mega;
    }
}
