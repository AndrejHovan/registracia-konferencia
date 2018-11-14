package sk.upjs.registracia_konferencia;

import javafx.collections.FXCollections;
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
        workshopComboBox.setItems(FXCollections.observableList(workshopDao.getAll()));
        nameTextField.textProperty().bindBidirectional(workshopModel.nameProperty());
        startDatePicker.valueProperty().bindBidirectional(workshopModel.propertyStart());
        endDatePicker.valueProperty().bindBidirectional(workshopModel.propertyEnd());
        priceFullTextField.textProperty().bindBidirectional(workshopModel.priceFullProperty(), new NumberStringConverter());
        priceStudentTextField.textProperty().bindBidirectional(workshopModel.priceStudentProperty(), new NumberStringConverter());
        priceFullLateTextField.textProperty().bindBidirectional(workshopModel.priceFullLateProperty(), new NumberStringConverter());
        priceStudentLateTextField.textProperty().bindBidirectional(workshopModel.priceStudentLateProperty(), new NumberStringConverter());
    }
}
