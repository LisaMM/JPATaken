package be.vdab.entities;

import be.vdab.entities.Artikel;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FoodArtikel
 *
 */
@Entity
@DiscriminatorValue("F")
public class FoodArtikel extends Artikel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int houdbaarheid;

	protected FoodArtikel() {}
	
	public FoodArtikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs, 
			int houdbaarheid) {
		super(naam, aankoopprijs, verkoopprijs);
		setHoudbaarheid(houdbaarheid);
	}
	
	public int getHoudbaarheid() {
		return this.houdbaarheid;
	}

	public void setHoudbaarheid(int houdbaarheid) {
		this.houdbaarheid = houdbaarheid;
	}
   
}
