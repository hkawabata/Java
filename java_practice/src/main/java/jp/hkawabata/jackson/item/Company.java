package jp.hkawabata.jackson.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kawabatahiroto on 2017/08/22.
 */

public class Company {
    public String name;
    public Person[] members;
    public String place;
    public Person president;

    @JsonCreator
    Company(@JsonProperty("name") String name, @JsonProperty("members") Person[] members, @JsonProperty("place") String place) {
        this.name = name;
        this.members = members;
        this.place = place;
        this.president = members[0];
    }
}
