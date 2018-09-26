package sk.upjs.registracia_konferencia;

import java.time.LocalDateTime;
import java.util.List;

public class Participant {
	
	private String name;
	private String surname;
	private String email;
	private String organization;
	private String address;
	private Long ico;
	private String dic;
	private boolean early;
	private Tshirt tshirt;
	private boolean isStudent;
	private boolean singleRoom;
	private LocalDateTime start;
	private LocalDateTime end;
	private List<Companion> companions;
	private Workshop workshop;
	private boolean cash;
	
	
}
