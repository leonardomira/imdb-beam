package br.com.mira.imdb.models;

import java.io.Serializable;
import java.util.Objects;

public class Principals implements Serializable {

    private String tconst;
    private int ordering;
    private String nconst;
    private String category;
    private String job;
    private String characters;

    public String getTconst() {
        return tconst;
    }

    public void setTconst(String tconst) {
        this.tconst = tconst;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getNconst() {
        return nconst;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCharacters() {
        return characters;
    }

    public void setCharacters(String characters) {
        this.characters = characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Principals that = (Principals) o;
        return ordering == that.ordering && Objects.equals(tconst, that.tconst) && Objects.equals(nconst, that.nconst) && Objects.equals(category, that.category) && Objects.equals(job, that.job) && Objects.equals(characters, that.characters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tconst, ordering, nconst, category, job, characters);
    }
}
