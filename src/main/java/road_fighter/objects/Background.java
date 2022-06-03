package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Background extends GameObject implements Updatable, Renderable {
	private VBox render;
//	private double posX = 0;
	private double posY = 0;

	private final int cityWidth = 800;
	private final int cityHeight = 600;
//	private final int grassHeight = 100;

	public Background() {
		Image backgroundImage = new Image(
				"file:src/main/resources/img/background1.jpg", cityWidth, cityHeight, false, false);

		ImagePattern image_pattern = new ImagePattern(
				backgroundImage, 0, 0, cityWidth, cityHeight,
				false);

//		Rectangle sky = new Rectangle(Config.baseWidth + cityWidth, Config.baseHeight - cityHeight - grassHeight);
		Rectangle city = new Rectangle(cityWidth, Config.baseWidth + cityHeight);
//		Rectangle grass = new Rectangle(Config.baseWidth + cityWidth, grassHeight);
//
//		sky.setFill(Color.rgb(84, 192, 201));
		city.setFill(image_pattern);
//		grass.setFill(Color.rgb(100, 224, 117));

//		render = new VBox(sky, city, grass);
		render = new VBox(city);
		// TODO zIndex list
		render.setViewOrder(10);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
//		posX += -Config.baseSpeed * deltaTime * 0.01;
//		render.setTranslateX(posX % cityWidth);
		posY += -Config.baseSpeed * deltaTime * 0.01;
		render.setTranslateY(posY % cityWidth);
	}

	@Override
	public void destroy() { }

}
