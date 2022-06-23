package road_fighter.objects;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public class Player extends GameObject implements Renderable, Updatable{
	
	private int bar;
	private int playerPositionX;
	private int playerPositionY = Config.playerHeight;
	private int roadPositionX;
	
	private VBox render;

	private int speed;
	
	
	private Score score;
	private GPS gps;
	private Road road;
	private Speedometer speedometer;
	private Car car;

	public Player(int barPlayer, int roadPosX, int playerPosX) {
		
		this.bar = barPlayer;
		this.playerPositionX = playerPosX;
		this.roadPositionX = roadPosX;
		
		score = new Score(bar);
		speedometer = new Speedometer(bar);
		gps = new GPS(bar);
		road = new Road(roadPositionX);
		car = new Car(playerPositionX, playerPositionY, road, score, speedometer, gps, false);
	}

	public int getBar() {
		return bar;
	}

	public int getPlayerPositionX() {
		return playerPositionX;
	}

	public int getRoadPositionX() {
		return roadPositionX;
	}
	
	public Car getCar() {
		return car;
	}
	
	public void setCar(Car car) {
		this.car = car;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node getRender() {
		// TODO Auto-generated method stub
		return render;
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public GPS getGps() {
		return gps;
	}

	public void setGps(GPS gps) {
		this.gps = gps;
	}

	public Road getRoad() {
		return road;
	}

	public void setRoad(Road road) {
		this.road = road;
	}

	public Speedometer getSpeedometer() {
		return speedometer;
	}

	public void setSpeedometer(Speedometer speedometer) {
		this.speedometer = speedometer;
	}
}
