package sk.upjs.registracia_konferencia;

import java.time.LocalDateTime;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ParticipantFxModel {
	
	private Participant participant;
	private StringProperty name = new SimpleStringProperty();
	private StringProperty surname = new SimpleStringProperty();
	private StringProperty email = new SimpleStringProperty();
	
	
	public ParticipantFxModel(Participant participant) {
		this.participant = participant;
		setEmail(participant.getEmail());
		setName(participant.getName());
		setSurname(participant.getSurname());
	}
	
	public Participant getParticipant() {
		this.participant.setName(getName());
		this.participant.setSurname(getSurname());
		this.participant.setEmail(getEmail());
		return participant;
	}
	
	//nameProperty
	public String getName() {
		return name.get();
	}
	public void setName(String name) {
		this.name.set(name);
	}
	public StringProperty nameProperty() {
		return name;
	}
	
	//surnameProperty
	public String getSurname() {
		return surname.get();
	}
	public void setSurname(String surname) {
		this.surname.set(surname);
	}
	public StringProperty surnameProperty() {
		return surname;
	}
	
	//emailProperty
	public String getEmail() {
		return email.get();
	}
	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty emailProperty() {
		return email;
	}
	

	
	
}
