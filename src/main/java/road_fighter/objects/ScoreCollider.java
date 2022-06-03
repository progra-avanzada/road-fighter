package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ScoreCollider extends GameObject implements Updatable, Collideable {
	private Rectangle collider;

//	private double posX;
	private double posY;

//	public ScoreCollider(int x) {
//		posX = x;
//		collider = new Rectangle(x, 0, Config.baseWidth / 10, Config.baseHeight - Config.groundHeight);
//		collider.setFill(null);
//		collider.setStroke(Color.WHITE);
//		collider.setStrokeWidth(2);
//	}

	public ScoreCollider(int y) {
//		posX = x;
		posY = y;
//		collider = new Rectangle(x, 0, Config.baseWidth / 10, Config.baseHeight - Config.groundHeight);
		collider = new Rectangle(Config.roadLeft + Config.platformWidth, y,
				Config.roadRight - Config.roadLeft - 2 * Config.platformWidth, 10);
		collider.setFill(null);
		collider.setStroke(Color.WHITE);
		collider.setStrokeWidth(2);
	}

	@Override
	public void update(double deltaTime) {
//		posX -= Config.baseSpeed * deltaTime;
		posY += Config.baseSpeed * deltaTime;
//		collider.setX(posX);
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
