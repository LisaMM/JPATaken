package be.vdab.dao;

import javax.persistence.TypedQuery;

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
}
