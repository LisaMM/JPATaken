package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.dao.ArtikelDAO;
import be.vdab.entities.Artikel;

public class ArtikelService {
	private final ArtikelDAO artikelDAO = new ArtikelDAO();

	public void create(Artikel artikel) {
		artikelDAO.beginTransaction();
		artikelDAO.create(artikel);
		artikelDAO.commit();
	}
	
	public Iterable<Artikel> findByWoord(String woord) {
		return artikelDAO.findByWoord(woord);
	}
	
	public void prijsVerhoging(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(
				percentage.divide(new BigDecimal(100)));
		artikelDAO.beginTransaction();
		artikelDAO.prijsVerhoging(factor);
		artikelDAO.commit();
	}
	
	public Iterable<Artikel> findAll() {
		return artikelDAO.findAll();
	}
	
	public Artikel read(long artikelNr) {
		return artikelDAO.read(artikelNr);
	}
}
