package sk.upjs.registracia_konferencia.persistent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import sk.upjs.registracia_konferencia.entities.Participant;
import sk.upjs.registracia_konferencia.entities.Tshirt;

public class MemoryParticipantDao implements ParticipantDao {
	
	private List<Participant> participants = new ArrayList<>();
	private long lastId = 0;
	
	public MemoryParticipantDao() {
		//TODO pre testovacie ucely only
		Participant p = new Participant();
		p.setName("Andrej");
		p.setSurname("Hovan");
		p.setEmail("nemam@email.sk");
		p.setTshirt(Tshirt.XL);
		p.setStart(LocalDateTime.now());
		this.add(p);
		Participant p2 = new Participant();
		p2.setName("Miloš");
		p2.setSurname("Jakubčin");
		p2.setEmail("neviem@kodit.com");
		p2.setTshirt(Tshirt.XL);
		this.add(p2);
	}
	
	@Override
	public void add(Participant participant) {
		participant.setId(++lastId);
		participants.add(participant);
	}
	
	@Override
	public List<Participant> getAll(){
		return participants;
	}

	@Override
	public void save(Participant participant) {
		if(participant != null) {
			if(participant.getId() == null) {
				add(participant);
			}else { //najdeme participanta a nahradime ho
				for (int i = 0; i<participants.size(); i++) {
					if(participants.get(i).getId().equals(participant.getId())){
						participants.set(i,participant);
						break;
					}
				}
			}
		}
	}
	
}
