package com.example.whoami.webparser.flavioparser;

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

@Log
@Service
@AllArgsConstructor
public class FlavioHeaderParser implements HeaderParser<FlavioHeader> {

    final private static String SIMPLE_REQUEST_HEADER_URL = "https://flaviocopes.com/http-request-headers/";
    final private static String SIMPLE_RESPONSE_HEADER_URL = "https://flaviocopes.com/http-response-headers/";

    final private WebClient webClient;

    private List<FlavioHeader> parseFlavioHeadersFromWeb(String simpleHeaderUrl, FlavioHeaderType headerType) {

        WebClient.ResponseSpec responseSpec =  webClient.get()
                .uri(simpleHeaderUrl)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .retrieve();

        String result = responseSpec.bodyToMono(String.class).block();

        Document doc =  Jsoup.parse(result);
        Elements elements =  doc.body().getElementsByTag("h3");

        // set simple headers
        List<FlavioHeader> simpleHeaders = new ArrayList<>();
        {
            Element exampleElement;
            Element simpleDefinitionElement;
            FlavioHeader.FlavioHeaderBuilder builder = FlavioHeader.builder();
            for (Element e: elements) {
                exampleElement = e.nextElementSibling();
                simpleDefinitionElement = exampleElement.nextElementSibling();
                builder.name(e.text());
                builder.example(exampleElement.text());
                builder.definition(simpleDefinitionElement.text());
                builder.headerType(headerType);
                simpleHeaders.add(builder.build());
            }
        }

        return simpleHeaders;
    }

    public List<FlavioHeader> parseHeaders() {

        List<FlavioHeader> allHeaders = new ArrayList<>();

        // parsing request headers
        List<FlavioHeader> simpleRequestHeaders = parseFlavioHeadersFromWeb(
                SIMPLE_REQUEST_HEADER_URL,
                FlavioHeaderType.REQUEST);

        // parsing response headers
        List<FlavioHeader> simpleResponseHeaders = parseFlavioHeadersFromWeb(
                SIMPLE_RESPONSE_HEADER_URL,
                FlavioHeaderType.RESPONSE);

        allHeaders.addAll(simpleRequestHeaders);
        allHeaders.addAll(simpleResponseHeaders);

        return allHeaders;
    }

}
