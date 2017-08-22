package jp.hkawabata.jackson.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kawabatahiroto on 2017/08/22.
 */
public class Person {
    public String name;
    public int age;
    @JsonProperty("sex")
    public String gender;
    public double weight;
    @JsonIgnore
    public double height;
}
