package fi.academy.demo;

import java.io.*;
import java.net.*;
import java.util.*;

public class Lukija {

    List otsikot = new ArrayList();
   // List linkit = new ArrayList();
    List ajat = new ArrayList();


    public List haeLinkki(String url) {
        List linkit = new ArrayList();
        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String linkki = "";
            String rivi;
            while ((rivi = luettava.readLine()) != null) {
                if (rivi.contains("<link>")) {
                    int eka = rivi.indexOf("<link>");
                    String jotain = rivi.substring(eka);
                    jotain = jotain.replace("<link>", "");
                    int vika = jotain.indexOf("</link>");
                    jotain = jotain.substring(0, vika);
                    linkki += jotain+"\n";
                    linkit.add(linkki);

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




    public String haeOtsikko(String url) {

        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String otsikko = "";
            String rivi;

            while ((rivi = luettava.readLine()) != null) {
                if (rivi.contains("<title>")) {
                    int eka = rivi.indexOf("<title>");
                    String jotain = rivi.substring(eka);
                    jotain = jotain.replace("<title>", "");
                    if (jotain.contains ("<![CDATA[")) {
                        jotain= jotain.replace("<![CDATA[", "");
                    }
                    int vika = jotain.indexOf("</title>");  // toimiva ]]></title>
                    if (jotain.contains("]]></title>")) {
                        vika= jotain.indexOf("]]></title>");
                    }
                    jotain = jotain.substring(0, vika);
                    otsikko += jotain+"\n";
                    //  otsikot.add(otsikko);


                }
            }

            luettava.close();
            return otsikko;
        } catch (MalformedURLException urle) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Eipä tainnut onnistua");
        }
        return null;
    }

    public String haeAika(String url) {
//        DateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
//        Date date = formatter.parse("Sat, 24 Apr 2010 14:01:00 GMT");

        try {
            URL rssURL = new URL(url);
            BufferedReader luettava = new BufferedReader(new InputStreamReader(rssURL.openStream()));
            String aika = "";
            String rivi;
            while ((rivi = luettava.readLine()) != null) {
                if (rivi.contains("<pubDate>")) {
                    int eka = rivi.indexOf("<pubDate>");
                    String jotain = rivi.substring(eka);
                    jotain = jotain.replace("<pubDate>", "");
                    int vika = jotain.indexOf("</pubDate>");
                    jotain = jotain.substring(0, vika);
                    aika += jotain+"\n";
                    //  ajat.add(aika);
                    // aika Stringin muunto Date-olioksi??


                }
            }
            luettava.close();
            return aika;
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



}
