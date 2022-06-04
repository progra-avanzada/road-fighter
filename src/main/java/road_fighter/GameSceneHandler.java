package road_fighter;

import java.util.List;

import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.objects.Background;
import road_fighter.objects.BarrelBuilder;
import road_fighter.objects.Car;
import road_fighter.objects.FpsInfo;
import road_fighter.objects.GPS;
//import road_fighter.objects.Ground;
//import road_fighter.objects.PipeBuilder;
//import road_fighter.objects.Radio;
import road_fighter.objects.Road;
import road_fighter.objects.Score;
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
	
	private Car player;
	private Car player1;
	private Background background;
	private Road road;
	private BarrelBuilder barrelBuilder;
	private Score score;
	private Speedometer speedometer;
	private GPS gps;
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

//	protected void defineEventHandlers() {
////		mouseEventHandler = new EventHandler<MouseEvent>() {
////			@Override
////			public void handle(MouseEvent event) {
////				if (event.getButton() == MouseButton.PRIMARY) {
////					makeAction();
////				}
////			}
////		};
//
//		keyEventHandler = new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent e) {
//				switch (e.getCode()) {
//
//				case W:
//				case UP:
//				case ENTER:
//				case SPACE:
//					makeActionStart();
//					break;
//					
//				case A:
//				case LEFT:
//					makeActionLeft();
//					break;
//					
//				case D:
//				case RIGHT:
//					makeActionRight();
//					break;
//					
//				case R:
//					restart();
//					break;
//					
//				case Q:
//				case ESCAPE:
//					g.startMenu();
//					break;
//					
//				default:
//					break;
//				}
//			}
//		};
//		
//		
//	}
	
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
				case RIGHT:
				case D:
					player.setDirectionRight(true);
					break;
				case LEFT:
				case A:
					player.setDirectionLeft(true);
					break;
				case W:
				case UP:
					player.accelerate(true);
					break;
				default:
					break;
				}
			}
		});

		scene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case RIGHT:
				case D:
					player.setDirectionRight(false);
					break;
				case LEFT:
				case A:
					player.setDirectionLeft(false);
					break;
				case W:
				case UP:
					player.accelerate(false);
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
		
		score = new Score();
		speedometer = new Speedometer();
		gps = new GPS();
		player = new Car(Config.playerCenter, Config.playerHeight, score, speedometer, gps , false);
//		player1 = new Car(Config.playerCenter + 128, Config.playerHeight, score, true);
		player.stopRotationAnimation();
		background = new Background();
		road = new Road();
		barrelBuilder = new BarrelBuilder();
		fpsInfo = new FpsInfo(fps);
//		radio = new Radio(Config.playerCenter, Config.baseHeight / 2, player);

		// Add to builder
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(background, road, score, speedometer, gps, fpsInfo, player, barrelBuilder);
		
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
		Config.baseSpeed = 0;
		Config.distanciaActual = 0;
	}

	private void makeActionStart() {
		if (!started) {
			started = true;
			barrelBuilder.startBuilding(2 * NANOS_IN_SECOND);
			player.stopRotationAnimation();
			Config.baseSpeed = 0;
			Config.distanciaActual = 0;
		}
	}
//	
//	private void makeActionRight() {
//		if(started)
//			player.pushRight();
//	}
//	
//	private void makeActionLeft() {
//		if(started)
//			player.pushLeft();
//	}

	public void update(double delta) {
		super.update(delta);

		checkColliders();

		if (!ended) {
			if (player.isDead()) {
				ended = true;
//				pipeBuilder.stopBuilding();
				barrelBuilder.stopBuilding();
				Config.baseSpeed = 0;
				Config.distanciaActual = 0;

				// Improve
				TranslateTransition tt = new TranslateTransition(Duration.millis(50), scene.getRoot());
				tt.setFromX(-20f);
				tt.setByX(20f);
				tt.setCycleCount(6);
				tt.setAutoReverse(true);
				tt.playFromStart();
				tt.setOnFinished(event -> {
					scene.getRoot().setTranslateX(0);
				});
			}
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
//				} else {
//					// Check contains
//					Bounds collideableBounds = collideable.getCollider().getBoundsInLocal();
//					Bounds collidatorBounds = collidator.getCollider().getBoundsInLocal();
//					if (collideableBounds.contains(collidatorBounds.getCenterX(), collidatorBounds.getCenterY())) {
//						collidator.collide(collideable);
//					}
//				}
			}
		}
	}
	
	public void unload() {
		cleanData();
		super.unload();
	}

}
