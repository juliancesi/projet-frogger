package fr.cesi.application;

import java.net.URL;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import fr.cesi.util.Utils;

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
		
		URL url = Utils.loadResource("resources/sounds/themeFrogger.mp3");
		String stu = url.toString();
		
		Media media = new Media(stu);
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
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
