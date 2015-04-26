package application;
	
import static graphic.FxmlOverview.GAME;
import static graphic.FxmlOverview.IHM;
import static graphic.FxmlOverview.getFxmlController;
import static graphic.FxmlOverview.loadFxmlView;
import graphic.GameLoop;
import graphic.fxmlcontroller.AbstractController;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GraphicsEngine {
	private Stage primaryStage;
	private GameLoop gameLoop;
	
	private static GraphicsEngine instance;
	
	public static GraphicsEngine getInstance() {
		return instance;
	}
	
	public GraphicsEngine(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Mon Application");
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);

		if(instance == null) {
			instance = this;
		}
		
		loadMenu(); // load the menu fxml
	}
	
	public void setScene(Pane newLayout) {
		Scene scene = new Scene(newLayout);
		primaryStage.setScene(scene);
		scene.getRoot().requestFocus();
	}
	
	public void loadMenu() throws IOException {
		setScene(loadFxmlView(IHM));
	}

	public void loadGame() throws IOException {
		setScene(loadFxmlView(GAME));
		
		gameLoop = new GameLoop(getFxmlController());
		gameLoop.startGame();
	}
	
	public void launch() {
		primaryStage.show();
	}
}
