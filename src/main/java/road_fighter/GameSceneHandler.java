package road_fighter;

import java.util.ArrayList;
import java.util.List;

import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.objects.Background;
import road_fighter.objects.Barrel;
import road_fighter.objects.BarrelBuilder;
import road_fighter.objects.Car;
import road_fighter.objects.FpsInfo;
import road_fighter.objects.GPS;
import road_fighter.objects.Player;
import road_fighter.objects.PlayerBuilder;
//import road_fighter.objects.Ground;
//import road_fighter.objects.PipeBuilder;
//import road_fighter.objects.Radio;
import road_fighter.objects.Road;
import road_fighter.objects.Score;
import road_fighter.objects.ScoreCollider;
import road_fighter.objects.Speedometer;
import road_fighter.utils.GameObjectBuilder;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
//import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class GameSceneHandler extends SceneHandler {

	private Background background;
	private Player player;
	private Player player2;
	private BarrelBuilder barrelBuilder;
	private PlayerBuilder playerBuilder;
	private ArrayList<Player> jugadores;
	private FpsInfo fpsInfo;

	// TODO pause
	// boolean paused = false;
	boolean started = false;
	boolean ended = false;

	public GameSceneHandler(RoadFighterGame g) {
		super(g);
	}

	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	protected void defineEventHandlers() {
		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {

				case W:
				case UP:
				case ENTER:
				case SPACE:
					makeActionStart();
					break;
				case R:
					restart();
					break;
				case Q:
				case ESCAPE:
					g.startMenu();
					break;

				default:
					break;
				}
			}
		};

		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case D:
					player.getCar().setDirectionRight(true);
					break;
				case RIGHT:
					player2.getCar().setDirectionRight(true);
					break;
				case A:
					player.getCar().setDirectionLeft(true);
					break;
				case LEFT:
					player2.getCar().setDirectionLeft(true);
					break;
				case W:
					player.getCar().accelerate(true);
					break;
				case UP:
					player2.getCar().accelerate(true);
				default:
					break;
				}
			}
		});

		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case D:
					player.getCar().setDirectionRight(false);
					break;
				case RIGHT:
					player2.getCar().setDirectionRight(false);
					break;
				case A:
					player.getCar().setDirectionLeft(false);
					break;
				case LEFT:
					player2.getCar().setDirectionLeft(false);
					break;
				case W:
					player.getCar().accelerate(false);
					break;
				case UP:
					player2.getCar().accelerate(false);
					break;
				default:
					break;
				}
			}
		});
	}

	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);

		background = new Background();
		player = new Player(Config.barPlayer1, Config.posXRoadPlayer1, Config.Player1PosX);

		player2 = new Player(Config.barPlayer2, Config.posXRoadPlayer2, Config.Player2PosX);

		jugadores = new ArrayList<Player>();
		jugadores.add(player);
		jugadores.add(player2);
		barrelBuilder = new BarrelBuilder(jugadores);
		playerBuilder = new PlayerBuilder(jugadores);

		fpsInfo = new FpsInfo(fps);

		// Add to builder
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(background, fpsInfo, player.getCar(), player.getGps(), player.getRoad(), player.getScore(),
				player.getSpeedometer(), player2.getCar(), player2.getGps(), player2.getRoad(), player2.getScore(),
				player2.getSpeedometer(), barrelBuilder, playerBuilder);

//		for(int i = 0; i < barrelBuilder.getObstaculos().size(); i++) {
//			gameOB.add(barrelBuilder.getObstaculos().get(i), barrelBuilder.getScoreColliders().get(i));
//		}

		/// PRUEBA BOT
//		gameOB.add(background, road, score, fpsInfo, player, player1, barrelBuilder);
//		player1.accelerate(true);
//		player1.setDirectionRight(true);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	public void restart() {
		cleanData();
		load(false);
	}

	private void cleanData() {
		GameObjectBuilder.getInstance().removeAll();
		ended = false;
		started = false;
	}

	private void makeActionStart() {
		if (!started) {
			started = true;
			barrelBuilder.startBuilding(2 * NANOS_IN_SECOND);
		}
	}

	public void update(double delta) {
		super.update(delta);

		checkColliders();

		if (!ended) {
//			if (player.getCar().isDead()) {
//				ended = true;
//				barrelBuilder.stopBuilding();
////				Config.speedPlayer1 = 0;
////				Config.distanciaActual1 = 0;
//
//				// Improve
//				TranslateTransition tt = new TranslateTransition(Duration.millis(50), scene.getRoot());
//				tt.setFromX(-20f);
//				tt.setByX(20f);
//				tt.setCycleCount(6);
//				tt.setAutoReverse(true);
////				tt.playFromStart();
//				tt.setOnFinished(event -> {
//					scene.getRoot().setTranslateX(0);
//				});
//			}
//			if (player2.getCar().isDead()) {
//				ended = true;
//				barrelBuilder.stopBuilding();
////				Config.speedPlayer2 = 0;
////				Config.distanciaActual2 = 0;
//
//				// Improve
//				TranslateTransition tt = new TranslateTransition(Duration.millis(50), scene.getRoot());
//				tt.setFromX(-20f);
//				tt.setByX(20f);
//				tt.setCycleCount(6);
//				tt.setAutoReverse(true);
////				tt.playFromStart();
//				tt.setOnFinished(event -> {
//					scene.getRoot().setTranslateX(0);
//				});
//			}
		}
	}

	private void checkColliders() {
		List<Collidator> collidators = GameObjectBuilder.getInstance().getCollidators();
		List<Collideable> collideables = GameObjectBuilder.getInstance().getCollideables();

		for (int i = 0; i < collidators.size(); i++) {
			Collidator collidator = collidators.get(i);
			for (int j = i + 1; j < collidators.size(); j++) {
				Collidator otherCollidator = collidators.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), otherCollidator.getCollider());
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(otherCollidator);
					otherCollidator.collide(collidator);
				}
			}

			for (int j = 0; j < collideables.size(); j++) {
				Collideable collideable = collideables.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), collideable.getCollider());

				// TODO test times
				// XXX Si el substract es distinto???
				// Check intersects
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(collideable);
				}
			}
		}
	}

	public void unload() {
		cleanData();
		super.unload();
	}

}
