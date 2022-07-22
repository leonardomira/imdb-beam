package br.com.mira.imdb.models;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Names implements Serializable {

	private String nconst;
	private String primaryName;
	private int birthYear;
	private int deathYear;
	private String[] primaryProfession;
	private String[] knownForTitles;

	public String getNconst() {
		return nconst;
	}

	public void setNconst(String nconst) {
		this.nconst = nconst;
	}

	public String getPrimaryName() {
		return primaryName;
	}

	public void setPrimaryName(String primaryName) {
		this.primaryName = primaryName;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	public int getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(int deathYear) {
		this.deathYear = deathYear;
	}

	public String[] getPrimaryProfession() {
		return primaryProfession;
	}

	public void setPrimaryProfession(String[] primaryProfession) {
		this.primaryProfession = primaryProfession;
	}

	public String[] getKnownForTitles() {
		return knownForTitles;
	}

	public void setKnownForTitles(String[] knownForTitles) {
		this.knownForTitles = knownForTitles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Names names = (Names) o;
		return birthYear == names.birthYear && deathYear == names.deathYear && Objects.equals(nconst, names.nconst) && Objects.equals(primaryName, names.primaryName) && Arrays.equals(primaryProfession, names.primaryProfession) && Arrays.equals(knownForTitles, names.knownForTitles);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(nconst, primaryName, birthYear, deathYear);
		result = 31 * result + Arrays.hashCode(primaryProfession);
		result = 31 * result + Arrays.hashCode(knownForTitles);
		return result;
	}
}
