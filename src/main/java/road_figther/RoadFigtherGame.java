package road_figther;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RoadFigtherGame extends Application {
	private Stage stage;

	private MenuSceneHandler menuSceneHandler;

	private GameSceneHandler gameSceneHandler;

	private PartidaSceneHandler partidaSceneHandler;

	@Override
	public void start(Stage stage) {
		this.stage = stage;

		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);

		menuSceneHandler.load();

		// XXX patron state para controlar paso de escenas?

		// Scale
		// TODO scale and fill to maintain proportion (also center)
		// scale = new Scale();
		// dinamico, cada vez que cambio el tamaÃ±o de ventana
		// scale.setX(scene.getWidth() / WIDTH);
		// scale.setY(scene.getHeight() / HEIGHT);
		// images.getTransforms().add(scale);

		stage.getIcons().add(new Image("file:src/main/resources/ico/iconoRF.png"));
		stage.setTitle("Road Figther FXGame | Progra Avanzada");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public void startGame() {
		partidaSceneHandler.unload();
		//menuSceneHandler.unload();
		
		gameSceneHandler = new GameSceneHandler(this);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true);
	}

	public void startMenu() {
		partidaSceneHandler.unload();
		//gameSceneHandler.unload();

		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		menuSceneHandler.load();
	}

	public void startPartida() {
		 menuSceneHandler.unload();
		//gameSceneHandler.unload();

		partidaSceneHandler = new PartidaSceneHandler(this);
		Scene scene = partidaSceneHandler.getScene();
		stage.setScene(scene);
		partidaSceneHandler.load();
	}
}
