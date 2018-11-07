package sk.upjs.registracia_konferencia.persistent;

import java.util.List;

import sk.upjs.registracia_konferencia.entities.Workshop;

public interface WorkshopDao {

	List<Workshop> getAll();
	
	Workshop save(Workshop workshop);
	
	void delete(long id);
}