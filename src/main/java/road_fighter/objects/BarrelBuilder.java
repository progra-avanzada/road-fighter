package road_fighter.objects;

import java.util.ArrayList;

import road_fighter.Config;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class BarrelBuilder extends GameObject implements Updatable {
//	private final long NANOS_IN_SECOND = 1_000_000_000;

	private int[][] obstaculos;

	private ArrayList<ArrayList<Barrel>> barrelsPerPlayer;
	private ArrayList<ArrayList<ScoreCollider>> scoreCollidersPerPlayer;
	private ArrayList<Player> jugadores;

	private int cantObstaculos = 0;

	private boolean running = false;

	public BarrelBuilder(ArrayList<Player> listPlayers) {
//		this.posX = posX;
		jugadores = listPlayers;

		obstaculos = new int[1000][2];
		barrelsPerPlayer = new ArrayList<ArrayList<Barrel>>();
		scoreCollidersPerPlayer = new ArrayList<ArrayList<ScoreCollider>>();

		for (int i = 0; i < jugadores.size(); i++) {
			barrelsPerPlayer.add(new ArrayList<Barrel>());
			scoreCollidersPerPlayer.add(new ArrayList<ScoreCollider>());
		}

		for (int i = Config.startObstacles; i < Config.distanciaMax; i += Config.distancePerBarrel) {
			double random = Math.random();

			int posX = (int) (random * (Config.roadWidth - Config.platformWidth - Config.barrelSize));
			obstaculos[cantObstaculos][0] = posX;
			obstaculos[cantObstaculos][1] = i;
			cantObstaculos++;
		}

	}

	@Override
	public void update(double deltaTime) {

		if (running) {

			for (int j = 0; j < jugadores.size(); j++) {

				double distanciaJugador = jugadores.get(j).getCar().getDistanciaRecorrida();

				for (int i = 0; i < cantObstaculos; i++) {

					if ((obstaculos[i][1] - distanciaJugador) < 540 && barrelsPerPlayer.get(j).size() < i) {

						createObstacles(obstaculos[i][0] + jugadores.get(j).getRoadPositionX() + Config.platformWidth,
								obstaculos[i][1], j);
					}
				}

				for (int i = 0; i < barrelsPerPlayer.get(j).size(); i++) {
					barrelsPerPlayer.get(j).get(i).updateBarrel(jugadores.get(j).getCar().getSpeed(), deltaTime);
					scoreCollidersPerPlayer.get(j).get(i).update(jugadores.get(j).getCar().getSpeed(), deltaTime);
				}
			}
		}
	}

	public void startBuilding(long delayInNano) {
		running = true;
	}

	public void stopBuilding() {
		running = false;
	}

	public void createObstacles(int x, int distancia, int perteneceAJugador) {

		Barrel barrel = new Barrel(x, distancia);
		ScoreCollider scoreCollider = new ScoreCollider(jugadores.get(perteneceAJugador).getRoadPositionX());
		GameObjectBuilder.getInstance().add(barrel, scoreCollider);
		barrelsPerPlayer.get(perteneceAJugador).add(barrel);
		scoreCollidersPerPlayer.get(perteneceAJugador).add(scoreCollider);

	}

	@Override
	public void destroy() {
	}

}
