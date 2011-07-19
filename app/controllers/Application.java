package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;

import net.zemberek.erisim.Zemberek;
import net.zemberek.tr.yapi.TurkiyeTurkcesi;
import net.zemberek.yapi.Kelime;
import play.Logger;
import play.data.validation.Required;
import play.mvc.Controller;

public class Application extends Controller {
    public static final Zemberek z = new Zemberek(new TurkiyeTurkcesi());

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

    public static void heceler(String k) {
        String dizi[] = k.split(" ");
        List<List<String>> gecis = new ArrayList<List<String>>();
        for (String kelime : dizi) {
            String[] hecelenmis = z.hecele(kelime);
            List<String> heceler = Arrays.asList(hecelenmis);
            gecis.add(heceler);
        }
        render("Application/heceler.html", gecis);
    }

    public static void kelimeCozumle(String k) {
        String dizi[] = k.split(" ");
        List<List<String>> gecis = new ArrayList<List<String>>();

        for (String kel : dizi) {
            Kelime[] cozumler = z.kelimeCozumle(kel);
            List<Kelime> cozum = Arrays.asList(cozumler);
            gecis.addAll((Collection<? extends List<String>>) cozum);
        }

        render("Application/kelimeCozumle.html", gecis);
    }

    public static void asciDonustur(String k) {
        String l = z.asciiyeDonustur(k);
        render(l);
    }

    public static void oneriler(String k) {
        String dizi[] = null;
        dizi = k.split(" ");
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
        String[] dizi = k.split(" ");
        List<List<String[]>> ayrisimlar = new ArrayList<List<String[]>>();
        for (String kelime : dizi) {
            ayrisimlar.add(z.kelimeAyristir(kelime));
        }
        Logger.debug("Kelimeler ayrıştırıldı");
        render("Application/kelimeAyristir.html", ayrisimlar);
    }

}
