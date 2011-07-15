package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		String dizi[] = null;
		dizi = k.split(" ");
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

	public static void secim(String sec) {

		render(sec);
	}
}
