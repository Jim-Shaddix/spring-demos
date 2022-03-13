package com.example.whoami.webparser.rfcparser;

import com.example.whoami.webparser.HeaderParser;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
@AllArgsConstructor
public class Rfc2616HeaderParser implements HeaderParser<Rfc2616Header> {

    final private static String RFC_HEADER_URL = "https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html";

    final private WebClient webClient;

    private List<Rfc2616Header> parseRfcHeadersFromWeb(String simpleHeaderUrl) {

        WebClient.ResponseSpec responseSpec = webClient.get()
                .uri(simpleHeaderUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .retrieve();

        String result = responseSpec.bodyToMono(String.class).block();

        Document doc =  Jsoup.parse(result);
        Elements elements =  doc.body().getElementsByTag("h3");

        List<Rfc2616Header> rfcHeaders = new ArrayList<>();

        for (Element headerElement: elements) {

            String headerName = headerElement.text().trim().split(" ")[1];

            List<Element> headerDefinitionElements = new ArrayList<>();

            Element subElement = headerElement.nextElementSibling();
            while (subElement != null && !subElement.tagName().equals("h3")) {
                headerDefinitionElements.add(subElement);
                subElement = subElement.nextElementSibling();
            }

            String definition = headerDefinitionElements.stream()
                    .map(Element::outerHtml)
                    .collect(Collectors.joining("\n"));

            Rfc2616Header rfcHeader = new Rfc2616Header(headerName, definition);
            rfcHeaders.add(rfcHeader);

        }

        return rfcHeaders;
    }

    public List<Rfc2616Header> parseHeaders() {
        return parseRfcHeadersFromWeb(RFC_HEADER_URL);
    }

}
