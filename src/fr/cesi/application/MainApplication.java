package fr.cesi.application;

import javafx.application.Application;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class MainApplication.
 */
public class MainApplication extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		SceneLoader graphicsEngine = new SceneLoader(primaryStage);
		graphicsEngine.launch();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
