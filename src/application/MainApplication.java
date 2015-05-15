package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		GraphicsEngine graphicsEngine = new GraphicsEngine(primaryStage);
		graphicsEngine.launch();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
