package be.vdab.entities;

import be.vdab.entities.Artikel;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: NonFoodArtikel
 * 
 */
@Entity
@DiscriminatorValue("NF")
public class NonFoodArtikel extends Artikel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int garantie;

	public NonFoodArtikel() {
	}

	public NonFoodArtikel(String naam, BigDecimal aankoopprijs,
			BigDecimal verkoopprijs, int garantie) {
		super(naam, aankoopprijs, verkoopprijs);
		setGarantie(garantie);
	}

	public int getGarantie() {
		return this.garantie;
	}

	public void setGarantie(int garantie) {
		this.garantie = garantie;
	}
}
