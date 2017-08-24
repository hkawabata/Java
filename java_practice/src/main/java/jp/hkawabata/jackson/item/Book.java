package jp.hkawabata.jackson.item;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by kawabatahiroto on 2017/08/25.
 */

@JsonTypeInfo(use= JsonTypeInfo.Id.CLASS)
public class Book {
    public String title;
    public int pages;

    public Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
    }
}
