package road_fighter.objects;

import java.util.ArrayList;
import java.util.Random;

import road_fighter.Config;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class BarrelBuilder extends GameObject implements Updatable {
//	private final long NANOS_IN_SECOND = 1_000_000_000;

	private int[][] obstaculos;

	private ArrayList<Barrel> barrels;
	private ArrayList<ScoreCollider> scoreColliders;
	private Player jugador;

	private int cantObstaculos = 0;

	private boolean running = false;

	public BarrelBuilder(Player player, long seed) {
//		this.posX = posX;
		jugador = player;

		obstaculos = new int[1000][2];
		barrels = new ArrayList<Barrel>();
		scoreColliders = new ArrayList<ScoreCollider>();

		Random generator = new Random(seed);
		for (int i = Config.startObstacles; i < Config.distanciaMax; i += Config.distancePerBarrel) {
			int posX = (int) (generator.nextDouble() * (Config.roadWidth - Config.platformWidth - Config.barrelSize));
			obstaculos[cantObstaculos][0] = posX;
			obstaculos[cantObstaculos][1] = i;
			cantObstaculos++;
		}

	}

	@Override
	public void update(double deltaTime) {
		if (running) {
			double distanciaJugador = jugador.getCar().getDistanciaRecorrida();

			for (int i = 0; i < cantObstaculos; i++) {

				if ((obstaculos[i][1] - distanciaJugador) < 540 && barrels.size() < i) {

					createObstacles(obstaculos[i][0] + jugador.getRoadPositionX() + Config.platformWidth,
							obstaculos[i][1]);
				}
			}

			for (int i = 0; i < barrels.size(); i++) {
				barrels.get(i).updateBarrel(jugador.getCar().getSpeed(), deltaTime);
				scoreColliders.get(i).update(jugador.getCar().getSpeed(), deltaTime);
			}
		}
	}

	public void startBuilding(long delayInNano) {
		running = true;
	}

	public void stopBuilding() {
		running = false;
	}

	public void createObstacles(int x, int distancia) {
		Barrel barrel = new Barrel(x, distancia);
		ScoreCollider scoreCollider = new ScoreCollider(jugador.getRoadPositionX());
		GameObjectBuilder.getInstance().add(barrel, scoreCollider);
		barrels.add(barrel);
		scoreColliders.add(scoreCollider);
	}

	@Override
	public void destroy() {
	}

}
