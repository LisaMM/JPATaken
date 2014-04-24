package be.vdab.dao;

import java.math.BigDecimal;
import javax.persistence.*;

import be.vdab.entities.Artikel;

public class ArtikelDAO extends AbstractDAO {
	
	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}
	
	public Iterable<Artikel> findByWoord(String woord) {
		TypedQuery<Artikel> query = getEntityManager().createNamedQuery(
				"Artikel.findByWoord", Artikel.class);
		query.setParameter("woord", String.format("%%%s%%", woord));
		return query.getResultList();
	}
	
	public void prijsVerhoging(BigDecimal factor) {
		Query query = getEntityManager().createNamedQuery(
				"Artikel.prijsVerhoging");
		query.setParameter("factor", factor);
		query.executeUpdate();
	}
}
