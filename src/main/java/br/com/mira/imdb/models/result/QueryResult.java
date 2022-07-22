package br.com.mira.imdb.models.result;

import java.io.Serializable;
import java.util.List;

public class QueryResult implements Serializable {

    private String name;
    private List<String> titles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }
}
