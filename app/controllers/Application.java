package controllers;

import play.*;

import play.mvc.*;

import java.util.*;

import models.*;
import net.zemberek.erisim.Zemberek;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import net.zemberek.yapi.Kelime;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.lang.*;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import play.data.validation.*;

public class Application extends Controller {

    public static void index() {

        render();
    }

    public static void kullanicidanAl(@Required String cumle) {
        if (validation.hasErrors()) {
            flash.error("Aloo cümleyi girmedinn :)");
            index();
        }
        render("Application/index.html", cumle);
    }

    /*
     * public static void heceler(String k) { String dizi[] = null; dizi =
     * k.split(" "); Zemberek z = new Zemberek(new TurkiyeTurkcesi()); int a =
     * 0; int m = dizi.length; String[] hecelenmis = null; String[][] gecis =
     * new String[dizi.length][]; while (a < m) { hecelenmis =
     * z.hecele(dizi[a]); gecis[a] = new String[hecelenmis.length]; for (int t =
     * 0; t < hecelenmis.length; t++) { gecis[a][t] = hecelenmis[t]; }
     * System.out.println(Arrays.toString(gecis[a]));
     * 
     * a++; }
     * 
     * render("Application/heceler.html", gecis);
     * 
     * }
     */
    public static void heceler(String k) {
        String dizi[] = k.split(" ");
        Zemberek z = new Zemberek(new TurkiyeTurkcesi());
        List<List<String>> gecis = new ArrayList<List<String>>();
        for (String kelime : dizi) {
            String[] hecelenmis = z.hecele(kelime);
            List<String> heceler = Arrays.asList(hecelenmis);
            gecis.add(heceler);
        }
        render("Application/heceler.html", gecis);
    }

    public static void kelimeCozumle(String k) {
        String dizi[] = null;
        dizi = k.split(" ");
        Zemberek z = new Zemberek(new TurkiyeTurkcesi());
        int d = 0;
        Kelime[] cozumler = null;
        String[][] gecis = new String[dizi.length][];
        while (d < dizi.length) {

            cozumler = z.kelimeCozumle(dizi[d]);
            gecis[d] = new String[cozumler.length];
            for (int t = 0; t < cozumler.length; t++) {
                // gecis[d][t] =cozumler[t];
            }

            d++;

        }

        render("Application/kelimeCozumle.html", cozumler);
    }

    public static void asciDonustur(String k) {

        Zemberek z = new Zemberek(new TurkiyeTurkcesi());

        String l = z.asciiyeDonustur(k);
        render(l);

    }

    public static void oneriler(String k) {
        String dizi[] = null;
        dizi = k.split(" ");
        Zemberek z = new Zemberek(new TurkiyeTurkcesi());
        int a = 0;
        int m = dizi.length;
        String[] oneriler = null;
        String[][] gecis = new String[dizi.length][];
        while (a < m) {
            oneriler = z.oner(dizi[a]);
            gecis[a] = new String[oneriler.length];
            for (int t = 0; t < oneriler.length; t++) {
                gecis[a][t] = oneriler[t];
            }

            a++;
        }

        render("Application/oneriler.html", gecis);
    }

    public static void kelimeDenetle(String k) {
        String dizi[] = null;
        dizi = k.split(" ");
        Zemberek z = new Zemberek(new TurkiyeTurkcesi());

        int a = 0;
        String l = "";
        String denetle[] = new String[dizi.length];

        while (a < dizi.length) {
            if (z.kelimeDenetle(dizi[a]))
                l = "Kelime doğru yazilmis";
            else
                l = "Kelime yanlış yazilmis";
            denetle[a] = l;
            a++;
        }

        render("Application/kelimeDenetle.html", denetle);

    }

    public static void kelimeAyristir(String k) {
        String dizi[] = null;
        dizi = k.split(" ");
        Zemberek z = new Zemberek(new TurkiyeTurkcesi());
        int h = 0;
        List<String[]> ayrisimlar = null;
        while (h < dizi.length) {

            ayrisimlar = z.kelimeAyristir((k));

        }

        render("Application/kelimeAyristir.html", ayrisimlar);

    }

    public static void secim(String sec) {

        render(sec);
    }
}
