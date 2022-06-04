package road_fighter;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import road_fighter.objects.Background;
import road_fighter.objects.Car;
import road_fighter.objects.FpsInfo;
import road_fighter.objects.GPS;
import road_fighter.objects.Road;
import road_fighter.objects.Speedometer;
//import road_fighter.objects.Ground;
import road_fighter.objects.menu.TextoComenzar;
import road_fighter.objects.menu.Title;
import road_fighter.utils.GameObjectBuilder;

public class MenuSceneHandler extends SceneHandler {

	private Car player;
	private Background background;
	private Road road;
	private FpsInfo fpsInfo;
	private Title title;
	private TextoComenzar textoComenzar;

	private Group rootGroup;

	public MenuSceneHandler(RoadFighterGame g) {
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
					g.startGame();
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
		
		Speedometer speedometer = new Speedometer();
		GPS gps = new GPS();
		player = new Car(Config.baseWidth/2 + 220, Config.baseHeight / 3, null, speedometer, gps, false);
		
		background = new Background();
		road = new Road();
		fpsInfo = new FpsInfo(fps);

		title = new Title();
		textoComenzar = new TextoComenzar();

		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		gameOB.add(background, player, road, title, textoComenzar, fpsInfo);

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
