package sk.upjs.registracia_konferencia;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.converter.NumberStringConverter;
import sk.upjs.registracia_konferencia.entities.Workshop;
import sk.upjs.registracia_konferencia.persistent.DaoFactory;
import sk.upjs.registracia_konferencia.persistent.WorkshopDao;

public class WorkshopEditController {
	
	private WorkshopDao workshopDao;
	private WorkshopFxModel workshopModel;

    @FXML
    private ChoiceBox<Workshop> workshopComboBox;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField priceFullLateTextField;

    @FXML
    private TextField priceFullTextField;

    @FXML
    private TextField priceStudentTextField;

    @FXML
    private TextField priceStudentLateTextField;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button saveButton;
    
    public WorkshopEditController() {
		workshopDao = DaoFactory.INSTANCE.getWorkshopDao();
		workshopModel = new WorkshopFxModel();
	}

    @FXML
    void initialize() {
    	List<Workshop> workshopy = workshopDao.getAll();
        workshopComboBox.setItems(FXCollections.observableList(workshopy));
        workshopComboBox.getSelectionModel().selectedItemProperty()
        .addListener(new ChangeListener<Workshop>() {

			@Override
			public void changed(ObservableValue<? extends Workshop> observable, Workshop oldValue, Workshop newValue) {
				if (newValue != null) {
					workshopModel.setWorkshop(newValue);
				}

				
			}
		});
        //if(!workshopComboBox.getItems().isEmpty()) {
        	//mame aspon 1 workshop tak ho vyberieme
        //	workshopComboBox.getSelectionModel().select(workshopComboBox.getItems().get(0));
        //}
        if(!workshopy.isEmpty()) {
        	//mame aspon 1 workshop tak ho vyberieme
        	workshopComboBox.getSelectionModel().select(workshopy.get(0));
        }
        nameTextField.textProperty().bindBidirectional(workshopModel.nameProperty());
        startDatePicker.valueProperty().bindBidirectional(workshopModel.startProperty());
        endDatePicker.valueProperty().bindBidirectional(workshopModel.endProperty());
        priceFullTextField.textProperty().bindBidirectional(workshopModel.priceFullProperty(), new NumberStringConverter());
        priceStudentTextField.textProperty().bindBidirectional(workshopModel.priceStudentProperty(), new NumberStringConverter());
        priceFullLateTextField.textProperty().bindBidirectional(workshopModel.priceFullLateProperty(), new NumberStringConverter());
        priceStudentLateTextField.textProperty().bindBidirectional(workshopModel.priceStudentLateProperty(), new NumberStringConverter());
    }
    
    @FXML
    void saveButtonClicked(ActionEvent event) {
    	Workshop workshop = workshopModel.getWorkshop();
    	workshopDao.save(workshop);
    	List<Workshop> workshopy = workshopDao.getAll();
    	workshopComboBox.setItems(FXCollections.observableList(workshopy));
    	for (Workshop w: workshopy) {
    		if(w.getId() == workshop.getId()) {
    			workshopComboBox.getSelectionModel().select(w);
    			workshopModel.setWorkshop(w);
    		}
    	}
    }
    
    @FXML
    void clearButtonClicked(ActionEvent event) {
    	Workshop newWorkshop = new Workshop();
    	newWorkshop.setId(workshopModel.getId());
    	workshopModel.setWorkshop(newWorkshop);
    }
    
    @FXML
    void saveAsNewWorkshopButtonClicked(ActionEvent event) {
    	workshopModel.setId(null);
    	saveButtonClicked(event);
    }
    
    @FXML
    void deleteButtonClicked(ActionEvent event) {
    	if(workshopModel.getId() == null) {
    		clearButtonClicked(event);
    	}else {
        	workshopDao.delete(workshopModel.getId());
        	List<Workshop> workshopy = workshopDao.getAll();
        	workshopComboBox.setItems(FXCollections.observableList(workshopDao.getAll()));
        	if(!workshopy.isEmpty()) {
            	//mame aspon 1 workshop tak ho vyberieme
            	workshopComboBox.getSelectionModel().select(workshopy.get(0));
            }
    	}
    }
}
