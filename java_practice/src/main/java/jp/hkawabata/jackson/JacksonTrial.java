package jp.hkawabata.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.hkawabata.jackson.item.Person;

import java.io.IOException;

/**
 * Created by kawabatahiroto on 2017/08/22.
 */
public class JacksonTrial {
    static ObjectMapper mapper = new ObjectMapper()
            // Java クラスのフィールドとして定義されていない JSON フィールドがあってもエラーを出さず無視する
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static final String personJson = "{\"name\": \"Taro\", \"age\": 18, \"weight\": 60.0, \"height\": 170.5}";

    public static void main(String[] args) {
        parsePerson();
    }

    static void parsePerson() {
        try {
            Person person = mapper.readValue(personJson, Person.class);
            System.out.println(person.name);
            System.out.println(person.age);
            System.out.println(person.weight);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
