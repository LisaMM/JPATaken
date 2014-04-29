package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import be.vdab.valueobjects.Korting;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name = "Soort")
public abstract class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long artikelNr;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	@ElementCollection
	@CollectionTable(name = "kortingen", joinColumns = @JoinColumn(name = "artikelnr"))
	@OrderBy("vanafAantal")
	private Set<Korting> kortingen;

	protected Artikel() {
	}

	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
		kortingen = new LinkedHashSet<>();
	}

	@Override
	public String toString() {
		return String.format("%d:%s", artikelNr, naam);
	}

	public String getNaam() {
		return naam;
	}

	public long getArtikelNr() {
		return artikelNr;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public BigDecimal getAankoopprijs() {
		return aankoopprijs;
	}

	public void setAankoopprijs(BigDecimal aankoopprijs) {
		this.aankoopprijs = aankoopprijs;
	}

	public BigDecimal getVerkoopprijs() {
		return verkoopprijs;
	}

	public void setVerkoopprijs(BigDecimal verkoopprijs) {
		this.verkoopprijs = verkoopprijs;
	}

	public Set<Korting> getKortingen() {
		return Collections.unmodifiableSet(kortingen);
	}

	public void addKorting(Korting korting) {
		kortingen.add(korting);
	}

	public void removeKorting(Korting korting) {
		kortingen.remove(korting);
	}
}
