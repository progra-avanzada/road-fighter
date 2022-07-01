package road_fighter;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class RoadFighterGame extends Application {
	private Stage stage;

	private MenuSceneHandler menuSceneHandler;

	private GameSceneHandler gameSceneHandler;
	
	private Cliente cliente;
	
	public boolean conectado;
	
	public MediaPlayer mediaPlayer;
	
	@Override
	public void start(Stage stage) {
		this.stage = stage;

		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);

		menuSceneHandler.load();
		
		cliente = new Cliente(Config.ServerURL, this);

		stage.getIcons().add(new Image("file:src/main/resources/ico/iconoRF.png"));
		stage.setTitle("Road Fighter FXGame | Programacion Avanzada");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

	public void startMenu() {
		gameSceneHandler.unload();
		menuSceneHandler = new MenuSceneHandler(this);
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		menuSceneHandler.load();
	}
	
	public void onConnect() {
		conectado = true;
		menuSceneHandler.changeMessage("Esperando a otro jugador");
	}
	
	public void onDisconnect() {
		conectado = false;
	}
	
	public void onFullGame() {
		menuSceneHandler.changeMessage("Partida llena");
	}
	
	public void onStartGame(int playerNumber, long seed) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				startGame(playerNumber, seed);				
			}
		});
	}
	
	private void startGame(int playerNumber, long seed) {
		menuSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this);
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load(true, playerNumber, seed);
		
		Media loop = new Media(new File("src/main/resources/musics/8-Bit-Mayhem.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(loop);
		mediaPlayer.setVolume(1);
		// mediaPlayer.seek(Duration.ZERO);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	
	public void sendInput(boolean accelerate, boolean left, boolean right) {
		cliente.sendInput(accelerate, left, right);
	}
	
	public void onReceiveInput(boolean accelerate, boolean left, boolean right) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				gameSceneHandler.receiveInput(accelerate, left, right);		
			}
		});		
	}
}
