package com.example.penktaspraktinisdarbas;

import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static List<String> parseXml(String xmlContent) {
        List<String> data = new ArrayList<>();

        Document doc = Jsoup.parse(xmlContent);
        Elements items = doc.select("item");

        for (Element item : items) {
            String title = item.select("title").text();
            String description = item.select("description").text();

            String displayText = title + " - " + description;
            data.add(displayText);
        }

        return data;
    }
}
