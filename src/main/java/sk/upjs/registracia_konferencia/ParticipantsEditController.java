package sk.upjs.registracia_konferencia;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ParticipantsEditController {
	
	private ParticipantDao participantDao = ParticipantDaoFactory.INSTANCE.getParticipantDao();
	private Participant participant;
	private ParticipantFxModel participantModel;
	
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField surnameTextField;

    @FXML
    private Button saveButton;
    
    public ParticipantsEditController(Participant participant) {
    	this.participant = participant;
    	this.participantModel = new ParticipantFxModel(participant);
    }

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(participantModel.nameProperty());
        surnameTextField.textProperty().bindBidirectional(participantModel.surnameProperty());
        
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				participantDao.save(participantModel.getParticipant());
				saveButton.getScene().getWindow().hide();
			}
		});
    }
    
    
}

