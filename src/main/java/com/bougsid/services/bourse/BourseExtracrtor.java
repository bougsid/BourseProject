package com.bougsid.services.bourse;

import com.bougsid.entities.Achat;
import com.bougsid.entities.Ordre;
import com.bougsid.entities.Societe;
import com.bougsid.entities.Vente;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by ayoub on 11/28/2016.
 */
@Service
public class BourseExtracrtor {

    private static final String URL = "https://www.wafabourse.com/marches/actions/r";

    public static List<Societe> extractData() throws IOException {
        int i = 0;
        List<Societe> societes = new ArrayList<>();
        Document doc = Jsoup.connect(URL).get();
        Element table = doc.select("table[class=t-data-grid]").first();
        Iterator<Element> trs = table.select("tr").iterator();
        Element tr;
        trs.next();
        while (trs.hasNext()) {
            if (i == 1) break;
            i++;
            tr = trs.next();
            societes.add(extractSocieteAndOrdres(tr));
        }
        return societes;
    }

    private static Societe extractSocieteAndOrdres(Element tr) throws IOException {
        Element tdName = tr.select("td[class=longNameQS]").first();
        String societeURL = tdName.select("a").first().attr("abs:href");
        System.out.println(societeURL);
        Document societeDoc = Jsoup.connect(societeURL).get();
        Societe societe = extractSociete(societeDoc);
        //Get Ordres
        Element divOrdres = societeDoc.select("div[class=orderBookDiv]").first();
        Element tableOrdreAchat = divOrdres.select("table").stream().filter((e) -> e.hasAttr("bid_qty")).findFirst().get().select("tbody").first();
        Element tableOrdreVente = divOrdres.select("table").stream().filter((e) -> e.hasAttr("ask_px")).findFirst().get().select("tbody").first();
        extractAndAddOrdres(tableOrdreAchat, tableOrdreVente, societe);
        return societe;
    }

    private static Societe extractSociete(Document societeDoc) {
        Element titre = societeDoc.select("div[class=titre]").first();
        return new Societe(titre.select("div[class=markettitleisin]").text(), titre.select("h1").text());
    }

    private static void extractAndAddOrdres(Element tableOrdreAchat, Element tableOrdreVente, Societe societe) {
        extractOrdres(tableOrdreAchat, Achat::new, () -> "bid", societe);//Achat
        extractOrdres(tableOrdreVente, Vente::new, () -> "ask", societe);//Vente
    }

    private static void extractOrdres(Element tableOrdre, Supplier<Ordre> ordreSupplier, Supplier<String> tdClassSupplier, Societe societe) {
        Iterator<Element> achatRows = tableOrdre.select("tr")
                .stream()
                .filter(e -> !e.select("td").first().text().equals("-"))
                .iterator();
        Element achatRow = null;
        while (achatRows.hasNext()) {
            achatRow = achatRows.next();
            int actionsCount = Integer.parseInt(achatRow.select("td[class=" + tdClassSupplier.get() + "Volume]").text().replaceAll(" ", ""));
            double actionPrice = Double.parseDouble(achatRow.select("td[class=" + tdClassSupplier.get() + "Price]").text().replace(",", ".").replaceAll(" ", ""));
            Ordre ordre = ordreSupplier.get();
            ordre.setOrdreDate(LocalDate.now());
            ordre.setActionsCount(actionsCount);
            ordre.setActionPrice(actionPrice);
            ordre.setSociete(societe);
            societe.addOrdre(ordre);
            System.out.println(ordre);
        }
    }
}
