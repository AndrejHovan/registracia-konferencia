package sk.upjs.registracia_konferencia;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import sk.upjs.registracia_konferencia.entities.Participant;
import sk.upjs.registracia_konferencia.entities.Tshirt;
import sk.upjs.registracia_konferencia.persistent.ParticipantDao;
import sk.upjs.registracia_konferencia.persistent.DaoFactory;

public class ParticipantsListController {

	private ParticipantDao participantDao = DaoFactory.INSTANCE.getParticipantDao();
	private ObservableList<Participant> participantsModel;
	private Map<String, BooleanProperty> columnsVisibility = new LinkedHashMap<>();
	private ObjectProperty<Participant> selectedParticipant = new SimpleObjectProperty<>();

	@FXML
	private TableView<Participant> participantsTableView;

	@FXML
	private Button editParticipantButton;

	@FXML
	private Button editWorkshopsButton;

	@FXML
	void initialize() {

		participantsModel = FXCollections.observableArrayList(participantDao.getAll());

		editWorkshopsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				WorkshopEditController editController = new WorkshopEditController();
				showModalWindow(editController, "WorkshopEdit.fxml");
			}
		});

		// listener na editParticipantButton
		editParticipantButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ParticipantsEditController editController = new ParticipantsEditController(selectedParticipant.get());
				showModalWindow(editController, "ParticipantsEdit.fxml");

				participantsModel.setAll(participantDao.getAll());

			}
		});

		// id stlpec
		TableColumn<Participant, Long> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
		participantsTableView.getColumns().add(idCol);
		columnsVisibility.put("ID", idCol.visibleProperty());

		// name stlpec
		TableColumn<Participant, String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		participantsTableView.getColumns().add(nameCol);
		columnsVisibility.put("Name", nameCol.visibleProperty());

		// surname stlpec
		TableColumn<Participant, String> surnameCol = new TableColumn<>("Surname");
		surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
		participantsTableView.getColumns().add(surnameCol);
		columnsVisibility.put("Surname", surnameCol.visibleProperty());

		// email stlpec
		TableColumn<Participant, String> emailCol = new TableColumn<>("E-mail");
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
		emailCol.setEditable(true);
		emailCol.setVisible(false);
		participantsTableView.getColumns().add(emailCol);
		columnsVisibility.put("E-mail", emailCol.visibleProperty());

		//tricka
		TableColumn<Participant, Tshirt> tshirtCol = new TableColumn<>("Tričko");
		tshirtCol.setCellValueFactory(new PropertyValueFactory<>("tshirt"));
		tshirtCol.setCellFactory(new Callback<TableColumn<Participant,Tshirt>, TableCell<Participant,Tshirt>>() {
			
			@Override
			public TableCell<Participant, Tshirt> call(TableColumn<Participant, Tshirt> param) {
				return new ComboBoxTableCell<>(new StringConverter<Tshirt>() {

					@Override
					public String toString(Tshirt object) {
						return object == null ? "" : object.name();
					}

					@Override
					public Tshirt fromString(String string) {
						return string == null? null : Tshirt.valueOf(string);
					}
				});
			}
		});
		//tshirtCol.setEditable(true);
		//tshirtCol.setVisible(false);
		participantsTableView.getColumns().add(tshirtCol);
		columnsVisibility.put("Tričko", tshirtCol.visibleProperty());
		
		TableColumn<Participant, LocalDateTime> startCol = new TableColumn<>("Začiatok");
		//cezLambdu
		/*startCol.setCellFactory((TableColumn<Participant,LocalDateTime> param) -> { 
			return new TableCell<Participant,LocalDateTime>(){
			
			@Override
			protected void updateItem(LocalDateTime item, boolean empty) {
				super.updateItem(item, empty);
				if(empty || item == null) {
					setText("");
				}else {
					setText(item.toString());
				}
			}
		});*/
		
		startCol.setCellFactory(new Callback<TableColumn<Participant,LocalDateTime>, TableCell<Participant,LocalDateTime>>() {
			
			@Override
			public TableCell<Participant, LocalDateTime> call(TableColumn<Participant, LocalDateTime> param) {
				return new TableCell<Participant,LocalDateTime>(){
				
					private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy H:m");
					
					@Override
					protected void updateItem(LocalDateTime item, boolean empty) {
						super.updateItem(item, empty);
						if(empty || item == null) {
							setText("");
						}else {
							setText(formatter.format(item));
						}
					}
				};
			}
		});
		
		/*startCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Participant,LocalDateTime>, ObservableValue<LocalDateTime>>() {
			
			@Override
			public ObservableValue<LocalDateTime> call(CellDataFeatures<Participant, LocalDateTime> param) {
				return new SimpleObjectProperty<>(param.getValue().getStart());
			}
		});*/
		
		//lambda
		startCol.setCellValueFactory( param -> {
			return new SimpleObjectProperty<>(param.getValue().getStart());
		});
		
		participantsTableView.getColumns().add(startCol);
		columnsVisibility.put("Začiatok", startCol.visibleProperty());
		
		
		participantsTableView.setItems(participantsModel);
		participantsTableView.setEditable(true);

		ContextMenu contextMenu = new ContextMenu();
		for (Entry<String, BooleanProperty> entry : columnsVisibility.entrySet()) {
			CheckMenuItem menuItem = new CheckMenuItem(entry.getKey());
			menuItem.selectedProperty().bindBidirectional(entry.getValue());
			contextMenu.getItems().add(menuItem);
		}
		participantsTableView.setContextMenu(contextMenu);

		// chceme zobrat oznaceneho participanta v tableview
		participantsTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Participant>() {

			@Override
			public void changed(ObservableValue<? extends Participant> observable, Participant oldValue,
					Participant newValue) {
				if (newValue == null) {
					editParticipantButton.setDisable(true);
				} else {
					editParticipantButton.setDisable(false);
				}
				selectedParticipant.set(newValue);
			}
		});
	}

	private void showModalWindow(Object controller, String fxml){
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
			fxmlLoader.setController(controller);
			Parent rootPane = fxmlLoader.load();
			Scene scene = new Scene(rootPane);

			Stage dialog = new Stage();
			dialog.setScene(scene);
			dialog.initModality(Modality.APPLICATION_MODAL);
			dialog.showAndWait();
			// co sa nachadza za tymto prikazom sa spusti az po zatvoreni toho okna
			// participantsModel =
			// FXCollections.observableArrayList(participantDao.getAll());
			// participantsTableView.setItems(participantsModel);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
