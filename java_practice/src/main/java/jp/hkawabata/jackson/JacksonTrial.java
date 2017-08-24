package jp.hkawabata.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jp.hkawabata.jackson.item.Book;
import jp.hkawabata.jackson.item.Company;
import jp.hkawabata.jackson.item.Person;

import java.io.IOException;
import java.util.List;

/**
 * Created by kawabatahiroto on 2017/08/22.
 */
public class JacksonTrial {
    static ObjectMapper mapper = new ObjectMapper()
            // Java クラスのフィールドとして定義されていない JSON フィールドがあってもエラーを出さず無視する
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static final String personJson1 = "{\"name\": \"Taro\", \"age\": 17, \"sex\": \"male\", \"weight\": 60.0, \"height\": 170.5, \"birthday\": \"2000/01/01\", \"hobbies\": [\"reading books\", \"soccer\"]}";
    static final String personJson2 = "{\"name\": \"Jiro\", \"age\": 15, \"sex\": \"male\", \"weight\": 55.0, \"height\": 160.2, \"birthday\": \"2002/01/01\", \"hobbies\": []}";
    static final String personJson3 = "{\"name\": \"Hanako\", \"age\": 27, \"sex\": \"female\", \"weight\": 40.0, \"height\": 155.0, \"birthday\": \"1990/01/01\"}";
    static final String membersJson = String.format("[%s, %s, %s]", personJson1, personJson2, personJson3);
    static final String companyJson = String.format("{\"name\": \"HogeHoge Company\", \"members\": %s, \"place\": \"Tokyo\"}", membersJson);

    public static void main(String[] args) {
        System.out.println("\nreadValue\n");
        readValue();
        System.out.println("\nreadTree\n");
        readTree();
        System.out.println("\nwriteValueWithClassInfo\n");
        writeValueWithClassInfo();
    }

    static void readValue() {
        List<Person> membersList = null;
        Person[] membersArray = null;
        Company company = null;
        try {
            System.out.println(companyJson);
            membersList = mapper.readValue(membersJson, new TypeReference<List<Person>>(){});
            membersArray = mapper.readValue(membersJson, Person[].class);
            company = mapper.readValue(companyJson, Company.class);
            System.out.println(company.members);
            Person person = company.members[0];
            System.out.println("company.name: " + company.name);
            System.out.println("person.name: " + person.name);
            System.out.println("person.age: " + person.age);
            System.out.println("person.weight: " + person.weight);
            System.out.println("person.gender: " + person.gender);
            System.out.println("membersList.get(0).name: " + membersList.get(0).name);
            System.out.println("membersArray[0].name: " + membersArray[0].name);
            System.out.println("membersArray[0].hobbies: " + membersArray[0].hobbies);
            System.out.println("membersArray[1].hobbies: " + membersArray[1].hobbies);
            System.out.println("membersArray[2].hobbies: " + membersArray[2].hobbies);
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

    static void writeValueWithClassInfo() {
        Book book = new Book("Les Miserables", 123);
        try {
            String json = mapper.writeValueAsString(book);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
