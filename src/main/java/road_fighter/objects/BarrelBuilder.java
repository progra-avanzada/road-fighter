package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;

public class BarrelBuilder extends GameObject implements Updatable {
	private final long NANOS_IN_SECOND = 1_000_000_000;

	private boolean running = false;
	private long barrelTime;

	public BarrelBuilder() {

	}

	@Override
	public void update(double deltaTime) {
		if (running) {
			long currentNano = System.nanoTime();

//			if(distancia == Obstaculos)
//				createBarrel();
			
			if (currentNano - barrelTime > 0 && Config.baseSpeed > 0) {
				barrelTime = currentNano + (long) (Config.barrelsPerSecond * NANOS_IN_SECOND);
				createBarrel();
			}
		}
	}

	public void startBuilding(long delayInNano) {
		running = true;
		this.barrelTime = System.nanoTime() + delayInNano;
	}

	public void stopBuilding() {
		running = false;
	}

	public void createBarrel() {
		double random = Math.random();

//		int bordeIzquierdo = 572;
//		int bordeIzquierdo = Config.roadLeft;
//		int bordeDerecho = Config.roadRight;
		
//		int posBarrel = (int) (random * (bordeDerecho * 2 + 1 - bordeIzquierdo) + bordeIzquierdo);
		int posBarrel = (int) (480 + random * (Config.baseWidth - 220));
		
//		int totalHeight = Config.baseHeight - Config.groundHeight;
//		int emptySpace = (int) (totalHeight * Config.emptySpace);
//		int topPipeHeight = (int) (50 + random * (Config.baseHeight - 400));
//		int x = (int) (Config.baseWidth * 1.2);

		Barrel barrel = new Barrel(posBarrel);
//		Pipe bottomPipe = new Pipe(x, topPipeHeight, false);
		ScoreCollider scoreCollider = new ScoreCollider(-10);
//		GameObjectBuilder.getInstance().add(topPipe, bottomPipe, scoreCollider);
		GameObjectBuilder.getInstance().add(barrel, scoreCollider);
	}
	
	@Override
	public void destroy() {	}
	
}
