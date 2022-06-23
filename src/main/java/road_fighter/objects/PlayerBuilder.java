package road_fighter.objects;

import java.util.ArrayList;

import road_fighter.Config;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class PlayerBuilder extends GameObject implements Updatable {

	private boolean[][] autoEnPantalla;

	private ArrayList<ArrayList<Car>> playersPerPlayer;
	private ArrayList<Player> jugadores;

//	private boolean running = false;

	public PlayerBuilder(ArrayList<Player> listPlayers) {
//		this.posX = posX;
		jugadores = listPlayers;

		autoEnPantalla = new boolean[jugadores.size()][jugadores.size()];
		playersPerPlayer = new ArrayList<ArrayList<Car>>();

		for (int i = 0; i < jugadores.size(); i++) {
			playersPerPlayer.add(new ArrayList<Car>());
			for (int j = 0; j < jugadores.size(); j++) {

				if (i != j)
					playersPerPlayer.get(i)
							.add(new Car(
									(int) jugadores.get(j).getCar().getX() - jugadores.get(j).getRoadPositionX()
											+ jugadores.get(i).getRoadPositionX(),
									Config.playerHeight + (int) jugadores.get(i).getCar().getDistanciaRecorrida()
											- (int) jugadores.get(j).getCar().getDistanciaRecorrida(),
									null, null, null, null, false));
				else
					playersPerPlayer.get(i).add(null);
			}
		}

//		for (int i = Config.inicioObstaculos; i < Config.distanciaMax; i += Config.distancePerBarrel) {
//			double random = Math.random();
//
//			int posX = (int) (random * (Config.roadWidth - Config.platformWidth - Config.barrelSize));
////			obstaculos[cantObstaculos][0] = posX;
////			obstaculos[cantObstaculos][1] = i;
//			cantObstaculos++;
//		}

	}

	@Override
	public void update(double deltaTime) {

//		if (running) {

		for (int i = 0; i < jugadores.size(); i++) {

			double distanciaJugador = jugadores.get(i).getCar().getDistanciaRecorrida();

			for (int j = 0; j < jugadores.size(); j++) {

				double distanciaOtroJugador = jugadores.get(j).getCar().getDistanciaRecorrida();
				double posicionRelativa = jugadores.get(j).getCar().getX() - jugadores.get(j).getRoadPositionX();

				if (i != j && (distanciaOtroJugador - distanciaJugador) <= 540
						&& (distanciaOtroJugador - distanciaJugador) >= -180 && !autoEnPantalla[i][j]) {

					createCar(posicionRelativa, distanciaOtroJugador - distanciaJugador, i, j);
					autoEnPantalla[i][j] = true;
				}

				if (i != j && autoEnPantalla[i][j]) {


					if (jugadores.get(j).getCar().isAccelerated()) {
						playersPerPlayer.get(i).get(j).accelerate(true);
					} else {
						playersPerPlayer.get(i).get(j).accelerate(false);
					}

					playersPerPlayer.get(i).get(j)
							.setY(playersPerPlayer.get(i).get(j).getY()
									+ (+jugadores.get(i).getCar().getSpeed() - jugadores.get(j).getCar().getSpeed())
											* deltaTime);

					if (jugadores.get(j).getCar().isDirectionLeft()) {
						playersPerPlayer.get(i).get(j).setDirectionLeft(true);
					} else {
						playersPerPlayer.get(i).get(j).setDirectionLeft(false);
					}
					if (jugadores.get(j).getCar().isDirectionRight()) {
						playersPerPlayer.get(i).get(j).setDirectionRight(true);
					} else {
						playersPerPlayer.get(i).get(j).setDirectionRight(false);
					}

					playersPerPlayer.get(i).get(j).setDeadTime(jugadores.get(j).getCar().getDeadTime());
					playersPerPlayer.get(i).get(j)
							.setInvulnerabilityTime(jugadores.get(j).getCar().getInvulnerabilityTime());

					playersPerPlayer.get(i).get(j).setX(posicionRelativa + jugadores.get(i).getRoadPositionX());
				}

				if (i != j && playersPerPlayer.get(i).get(j).isOffScreen()) {
					autoEnPantalla[i][j] = false;
					GameObjectBuilder.getInstance().remove(playersPerPlayer.get(i).get(j));
				}
			}
		}
//		}
	}

//	public void startBuilding(long delayInNano) {
//		running = true;
//	}
//
//	public void stopBuilding() {
//		running = false;
//	}

	public void createCar(double x, double distancia, int perteneceAJugador, int esJugador) {
		playersPerPlayer.get(perteneceAJugador).get(esJugador)
				.setDeadTime(jugadores.get(esJugador).getCar().getDeadTime());
		playersPerPlayer.get(perteneceAJugador).get(esJugador)
				.setInvulnerabilityTime(jugadores.get(esJugador).getCar().getInvulnerabilityTime());
		GameObjectBuilder.getInstance().add(playersPerPlayer.get(perteneceAJugador).get(esJugador));
	}

	@Override
	public void destroy() {
	}

}
