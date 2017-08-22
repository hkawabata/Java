package jp.hkawabata.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.hkawabata.jackson.item.Company;
import jp.hkawabata.jackson.item.Person;

import java.io.IOException;

/**
 * Created by kawabatahiroto on 2017/08/22.
 */
public class JacksonTrial {
    static ObjectMapper mapper = new ObjectMapper()
            // Java クラスのフィールドとして定義されていない JSON フィールドがあってもエラーを出さず無視する
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static final String personJson1 = "{\"name\": \"Taro\", \"age\": 17, \"sex\": \"male\", \"weight\": 60.0, \"height\": 170.5, \"birthday\": \"2000/01/01\"}";
    static final String personJson2 = "{\"name\": \"Hanako\", \"age\": 27, \"sex\": \"female\", \"weight\": 40.0, \"height\": 155.0, \"birthday\": \"1990/01/01\"}";
    static final String companyJson = String.format("{\"name\": \"HogeHoge Company\", \"members\": [%s, %s], \"place\": \"Tokyo\"}", personJson1, personJson2);

    public static void main(String[] args) {
        System.out.println("\nreadValue\n");
        readValue();
        System.out.println("\nreadTree\n");
        readTree();
    }

    static void readValue() {
        Company company = null;
        try {
            System.out.println(companyJson);
            company = mapper.readValue(companyJson, Company.class);
            System.out.println(company.members);
            Person person = company.members[0];
            System.out.println(person.name);
            System.out.println(person.age);
            System.out.println(person.weight);
            System.out.println(person.gender);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String json = mapper.writeValueAsString(company);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    static void readTree() {
        JsonNode tree = null;
        try {
            tree = mapper.readTree(companyJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(tree);
        System.out.println(tree != null ? tree.get("members") : null);
    }
}
