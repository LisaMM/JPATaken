package be.vdab.services;

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
}
