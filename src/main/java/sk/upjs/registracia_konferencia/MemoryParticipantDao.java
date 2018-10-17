package sk.upjs.registracia_konferencia;

import java.util.ArrayList;
import java.util.List;

public class MemoryParticipantDao implements ParticipantDao {
	
	private List<Participant> participants = new ArrayList<>();
	private long lastId = 0;
	
	public MemoryParticipantDao() {
		//TODO pre testovacie ucely only
		Participant p = new Participant();
		p.setName("Andrej");
		p.setSurname("Hovan");
		p.setEmail("nemam@email.sk");
		this.add(p);
		Participant p2 = new Participant();
		p2.setName("Miloš");
		p2.setSurname("Jakubčin");
		p2.setEmail("neviem@kodit.com");
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
