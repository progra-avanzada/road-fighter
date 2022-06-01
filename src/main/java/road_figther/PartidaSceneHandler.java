package road_figther;

//import flappy_bird.objects.Background;
import road_figther.objects.BackgroundPartida;
//import flappy_bird.objects.FlappyBird;
import road_figther.objects.FpsInfo;
//import flappy_bird.objects.Ground;

import road_figther.objects.menu.TextoComenzarPartida;
//import flappy_bird.objects.menu.Title;
import road_figther.utils.GameObjectBuilder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class PartidaSceneHandler extends SceneHandler {

	private BackgroundPartida backgroundPartida;

	private FpsInfo fpsInfo;
	// private Title title;
	private TextoComenzarPartida textoComenzar;

	private Group rootGroup;

	public PartidaSceneHandler(RoadFigtherGame g) {
		super(g);
	}

	protected void prepareScene() {
		rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	protected void defineEventHandlers() {
		mouseEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					// g.startGame();
				}
			}
		};

		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case UP:
				case W:
				case SPACE:
				case ENTER:
					g.startGame();
					break;
				case Q:
					g.startMenu();
					break;
				case ESCAPE:
					System.exit(0);
					break;
				default:
					break;
				}
			}
		};
	}

	public void load() {
		boolean fullStart = true;
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);

		// player = new FlappyBird(Config.baseWidth - 75, Config.baseHeight / 3, null);

		backgroundPartida = new BackgroundPartida();
		// ground = new Ground();
		fpsInfo = new FpsInfo(fps);

		// title = new Title();
		textoComenzar = new TextoComenzarPartida();

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		// gameOB.add(background, player, ground, title, textoComenzar, fpsInfo);
//gameOB.add(background, player, title, textoComenzar, fpsInfo);
		// gameOB.add(background, title, textoComenzar, fpsInfo);
		// gameOB.add(ground, title, textoComenzar, fpsInfo);
		gameOB.add(backgroundPartida, textoComenzar, fpsInfo);

		// Scene currentScene = new Scene(baseGroup);

		/*
		 * Image fondo = new Image("file:src/main/resources/Fondo Sin auto.jpg", 720,
		 * 720, false, false); ImageView imageView = new ImageView(fondo);
		 * baseGroup.getChildren().add(imageView);
		 */

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		super.unload();
	}

}