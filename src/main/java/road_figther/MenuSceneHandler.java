package road_figther;
import road_figther.objects.Background;
//import flappy_bird.objects.FlappyBird;
import road_figther.objects.FpsInfo;
//import flappy_bird.objects.Radio;
//import flappy_bird.objects.RadioMenu;
//import flappy_bird.objects.Ground;
import road_figther.objects.menu.TextoComenzar;
import road_figther.objects.menu.TextoSalir;
//import flappy_bird.objects.menu.Title;
import road_figther.utils.GameObjectBuilder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class MenuSceneHandler extends SceneHandler {

	// private FlappyBird player;
	private Background background;
	// private Ground ground;
	private FpsInfo fpsInfo;
	// private Title title;
	private TextoComenzar textoComenzar;

	private TextoSalir textoSalir;
	//private RadioMenu radioMenu;

	private Group rootGroup;

	public MenuSceneHandler(RoadFigtherGame g) {
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
					//g.startGame();
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
					g.startPartida();
					break;
				case ENTER:
					//g.startGame();
					g.startPartida();
					break;
				case Q:
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

		background = new Background();
		// ground = new Ground();
		fpsInfo = new FpsInfo(fps);

		// title = new Title();
		textoComenzar = new TextoComenzar();

		textoSalir = new TextoSalir();
		
		//radioMenu = new RadioMenu(Config.playerCenter, Config.baseHeight / 2);
		
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		// gameOB.add(background, player, ground, title, textoComenzar, fpsInfo);
//gameOB.add(background, player, title, textoComenzar, fpsInfo);
		// gameOB.add(background, title, textoComenzar, fpsInfo);
		// gameOB.add(ground, title, textoComenzar, fpsInfo);
		// gameOB.add(background, textoComenzar, fpsInfo);
		// Scene currentScene = new Scene(baseGroup);
		gameOB.add(background, textoComenzar, textoSalir, fpsInfo);
		//gameOB.add(background, textoComenzar, textoSalir, fpsInfo,radioMenu);
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

