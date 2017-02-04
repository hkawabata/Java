package jp.hkawabata.api.twitter;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.*;

/**
 * twitter の API を試す
 *
 * Created by kawabatahiroto on 2017/01/30.
 */
public class TwitterAPI {

    public static void main(String[] args) throws Exception {
        String apiKey = args[0];
        String apiSecret = args[1];
        String requestToken = getRequestToken(apiKey, apiSecret);
        System.out.println(requestToken);
    }

    public static String getRequestToken(String apiKey, String apiSecret) throws Exception {
        String oauthToken = "";
        String oauthTokenSecret = "";
        String method = "POST";
        String urlStr = "https://api.twitter.com/oauth/request_token";

        // oauth に使うパラメータ
        SortedMap<String, String> oauthParams = new TreeMap<String, String>();
        String unixTime = String.valueOf((int) (System.currentTimeMillis() / 1000L));
        oauthParams.put("oauth_consumer_key", apiKey);
        oauthParams.put("oauth_signature_method", "HMAC-SHA1");
        oauthParams.put("oauth_timestamp", String.valueOf(unixTime));
        oauthParams.put("oauth_nonce", String.valueOf(Math.random()));
        oauthParams.put("oauth_version", "1.0");
        setOauthSignature(urlStr, method, oauthParams, apiSecret, oauthTokenSecret);

        // Authorizationヘッダの作成
        String paramStr = "";
        for (Map.Entry<String, String> param : oauthParams.entrySet()) {
            paramStr += ", " + param.getKey() + "=\""
                    + urlEncode(param.getValue()) + "\"";
        }
        paramStr = paramStr.substring(2);
        String authorizationHeader = "OAuth " + paramStr;

        // APIにアクセス
        RequestConfig confDefault = RequestConfig.DEFAULT;
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("Authorization", authorizationHeader));
        HttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(confDefault)
                .setDefaultHeaders(headers).build();
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost("api.twitter.com")
                .setPath("/oauth/request_token")
                .build();
        HttpPost post = new HttpPost(uri);
        HttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        BufferedReader br = new BufferedReader(new InputStreamReader(entity.getContent()));
        return br.lines().iterator().next();
    }

    private static void setOauthSignature(String urlStr, String method, Map<String, String> oauthParams, String apiSecret, String oauthTokenSecret) throws Exception {
        String paramStr = "";
        for (Map.Entry<String, String> param : oauthParams.entrySet()) {
            paramStr += "&" + param.getKey() + "=" + param.getValue();
        }
        paramStr = paramStr.substring(1);

        // 署名対象テキスト（signature base string）の作成
        String text = method + "&" + urlEncode(urlStr) + "&"
                + urlEncode(paramStr);

        // 署名キーの作成
        String key = urlEncode(apiSecret) + "&"
                + urlEncode(oauthTokenSecret);

        // HMAC-SHA1で署名を生成
        SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(),
                "HmacSHA1");
        Mac mac = Mac.getInstance(signingKey.getAlgorithm());
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(text.getBytes());
        String signature = new BASE64Encoder().encode(rawHmac);

        // 署名をパラメータに追加
        oauthParams.put("oauth_signature", signature);
    }

    private static String urlEncode(String string) {
        try {
            return URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
