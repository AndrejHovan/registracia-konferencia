package sk.upjs.registracia_konferencia.persistent;

import java.util.List;

import sk.upjs.registracia_konferencia.entities.Participant;

public interface ParticipantDao {

	void add(Participant participant);

	List<Participant> getAll();
	
	void save(Participant participant);
}