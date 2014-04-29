package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "artikels")
@DiscriminatorColumn(name = "Soort")
public abstract class Artikel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int artikelNr;
	private String naam;
	private BigDecimal aankoopprijs;
	private BigDecimal verkoopprijs;
	

	protected Artikel() {}
	
	public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
		setNaam(naam);
		setAankoopprijs(aankoopprijs);
		setVerkoopprijs(verkoopprijs);
	}
	
	@Override
	public String toString() {
		return String.format("%d:%s", artikelNr, naam);
	}

	public String getNaam() {
		return naam;
	}
	
	public int getArtikelNr() {
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

}
