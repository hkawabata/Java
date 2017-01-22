package jp.hkawabata.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Apache HttpClient のトライアル
 *
 * Created by kawabatahiroto on 2017/01/22.
 */
public class HttpClientTrial {
    public static void main(String[] args) {
        try {
            RequestConfig confDefault = RequestConfig.DEFAULT;
            RequestConfig confCustom = RequestConfig.custom()
                    .setRedirectsEnabled(true)
                    .build();
            List<Header> headers = new ArrayList<>();
            headers.add(new BasicHeader("Accept-Charset","utf-8"));
            headers.add(new BasicHeader("Accept-Language","ja, en;q=0.8"));

            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("www.google.com")
                    .setPath("/search")
                    .setParameter("q", "Apache HttpClient")
                    .build();
            HttpGet get = new HttpGet(uri);

            HttpClient httpClientDefault = HttpClientBuilder.create()
                    .setDefaultRequestConfig(confDefault)
                    .setDefaultHeaders(headers).build();
            HttpClient httpClientCustom = HttpClientBuilder.create()
                    .setDefaultRequestConfig(confCustom)
                    .setDefaultHeaders(headers).build();

            HttpResponse responseDefault = httpClientDefault.execute(get);
            HttpResponse responseCustom = httpClientCustom.execute(get);

            System.out.println("\n----- Default -----");
            func(responseDefault);
            System.out.println("\n----- Custom -----");
            func(responseCustom);

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void func(HttpResponse response) {
        try {
            Header[] headers = response.getAllHeaders();
            Locale locale = response.getLocale();
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));

            System.out.println("\n##### Headers\n");
            for (Header header : headers) {
                System.out.println(header);
            }
            System.out.println("\n##### Entity\n");
            System.out.println("entity.getContent\t:\t" + entity.getContent());
            System.out.println("entity.getContentEncoding\t:\t" + entity.getContentEncoding());
            System.out.println("entity.getContentLength\t:\t" + entity.getContentLength());
            System.out.println("entity.getContentType\t:\t" + entity.getContentType());

            /*
            System.out.println("\n##### Entity.Content\n");
            br.lines().forEach(System.out::println);
            */

            System.out.println("\n##### Locale\n");
            System.out.println("locale\t:\t" + locale);
            System.out.println("locale.getCountry\t:\t" + locale.getCountry());
            System.out.println("locale.getDisplayCountry\t:\t" + locale.getDisplayCountry());
            System.out.println("locale.getDisplayName\t:\t" + locale.getDisplayName());
            System.out.println("locale.getLanguage\t:\t" + locale.getLanguage());
            System.out.println("locale.getDisplayLanguage\t:\t" + locale.getDisplayLanguage());
            System.out.println("locale.getScript\t:\t" + locale.getScript());
            System.out.println("locale.getDisplayScript\t:\t" + locale.getDisplayScript());
            System.out.println("locale.getVariant\t:\t" + locale.getVariant());
            System.out.println("locale.getDisplayVariant\t:\t" + locale.getDisplayVariant());

            System.out.println("\n##### Status Line\n");
            System.out.println("statusLine\t:\t" + statusLine);
            System.out.println("statusLine.getReasonPhrase\t:\t" + statusLine.getReasonPhrase());
            System.out.println("statusLine.getProtocolVersion\t:\t" + statusLine.getProtocolVersion());
            System.out.println("statusLine.getStatusCode\t:\t" + statusLine.getStatusCode());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
