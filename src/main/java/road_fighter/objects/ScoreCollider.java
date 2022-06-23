package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ScoreCollider extends GameObject implements Collideable {
	private Rectangle collider;
	
	private double posX;
	private double posY = -Config.barrelSize / 2;
	private int tamCollider = 10; 
	
	public ScoreCollider(int posX) {
		this.posX = posX;
		
		collider = new Rectangle(this.posX + Config.platformWidth, - tamCollider,
				Config.roadWidth - 2 * Config.platformWidth, tamCollider);
		collider.setFill(null);
		collider.setStroke(Color.WHITE);
		collider.setStrokeWidth(2);
	}

	public void update(double speed, double deltaTime) {
		setPosY(posY + speed * deltaTime);
	}
	
	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
		collider.setY(posY);
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	public void remove() {
		GameObjectBuilder.getInstance().remove(this);
	}

	@Override
	public void destroy() {
	}

}
