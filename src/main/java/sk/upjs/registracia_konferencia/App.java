package sk.upjs.registracia_konferencia;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
	@Override
	public void start(Stage primaryStage) throws Exception {
		ParticipantsListController mainController = new ParticipantsListController();
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ParticipantsList.fxml"));
		 fxmlLoader.setController(mainController);
		 Parent rootPane = fxmlLoader.load();
		 
		 Scene scene = new Scene(rootPane);
		 primaryStage.setTitle("Organizácia konferencie");
		 primaryStage.setScene(scene);
		 primaryStage.show();
	}
	
    public static void main( String[] args )
    {
        launch(args);
    }    
    
}
