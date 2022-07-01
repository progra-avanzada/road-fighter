package road_fighter;

import java.util.List;

import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.objects.Background;
import road_fighter.objects.BarrelBuilder;
import road_fighter.objects.EnemyCar;
import road_fighter.objects.FpsInfo;
import road_fighter.objects.Player;
import road_fighter.utils.GameObjectBuilder;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class GameSceneHandler extends SceneHandler {

	private Background background;
	private Player player;
	private EnemyCar enemyCar;
	private BarrelBuilder barrelBuilder;
	private FpsInfo fpsInfo;

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
				case ESCAPE:
					System.exit(0);
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
				case A:
					player.getCar().setDirectionLeft(true);
					break;
				case W:
					player.getCar().accelerate(true);
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
				case D:
					player.getCar().setDirectionRight(false);
					break;
				case A:
					player.getCar().setDirectionLeft(false);
					break;
				case W:
					player.getCar().accelerate(false);
					break;
				default:
					break;
				}
			}
		});
	}

	public void load(boolean fullStart, int playerNumber, long seed) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);

		background = new Background();
		
		if (playerNumber == 1) {
			player = new Player(Config.barPlayer1, Config.posXRoadPlayer1, Config.Player1PosX);
			enemyCar = new EnemyCar(Config.Player2PosX, Config.playerHeight);		
		} else {
			player = new Player(Config.barPlayer1, Config.posXRoadPlayer1, Config.Player2PosX);
			enemyCar = new EnemyCar(Config.Player1PosX, Config.playerHeight);
		}

		barrelBuilder = new BarrelBuilder(player, seed);
		barrelBuilder.startBuilding(2 * NANOS_IN_SECOND);

		fpsInfo = new FpsInfo(fps);
		

		// Add to builder
		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(rootGroup);
		gameOB.add(background, fpsInfo, player.getCar(), player.getGps(), player.getRoad(), player.getScore(), player.getSpeedometer(), barrelBuilder, enemyCar);

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

	private void cleanData() {
		GameObjectBuilder.getInstance().removeAll();
		ended = false;
		started = false;
	}

	public void update(double delta) {
		super.update(delta);

		checkColliders();
		
		if (!g.conectado) {
			g.startMenu();
		}
		
		g.sendInput(player.getCar().isAccelerated(), player.getCar().isDirectionLeft(), player.getCar().isDirectionRight());
		
		enemyCar.setY(enemyCar.getY() + (player.getCar().getSpeed() - enemyCar.getSpeed()) * delta);
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

	public void receiveInput(boolean accelerate, boolean left, boolean right) {
		enemyCar.accelerate(accelerate);
		enemyCar.setDirectionLeft(left);
		enemyCar.setDirectionRight(right);
	}
}
