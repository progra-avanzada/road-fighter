package road_figther.objects;

import road_figther.Config;
import road_figther.interfaces.Renderable;
import road_figther.interfaces.Updatable;
import road_figther.utils.GameObject;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class BackgroundMapa extends GameObject implements Updatable, Renderable {
	private VBox render;
	private double posX = 0;

	private final int cityWidth = 1024;
	private final int cityHeight = 1024;
	private final int grassHeight = 1000;

	public BackgroundMapa() {
		Image backgroundImage = new Image("file:src/main/resources/img/FondoMapa.jpg", cityWidth, cityHeight, false, false);

		ImagePattern image_pattern = new ImagePattern(backgroundImage, cityWidth, cityHeight, cityWidth, cityHeight,
				false);
		
		//ImagePattern image_pattern = new ImagePattern(backgroundImage, 1920, 1920, 1920, 1920,
				//false);

		Rectangle sky = new Rectangle(Config.baseWidth + cityWidth, Config.baseHeight - cityHeight - grassHeight);
		//Rectangle city = new Rectangle(Config.baseWidth + cityWidth, cityHeight);
		Rectangle grass = new Rectangle(Config.baseWidth + cityWidth, grassHeight);
		
		Rectangle all = new Rectangle(1024,1024);

		sky.setFill(Color.rgb(84, 192, 201));
		//city.setFill(image_pattern);
		grass.setFill(Color.rgb(100, 224, 117));
		
		all.setFill(image_pattern);

		render = new VBox(all);
		// TODO zIndex list
		render.setViewOrder(10);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		//posX += -Config.baseSpeed * deltaTime * 0.01 * 0;
		posX += 0;
		render.setTranslateX(posX % cityWidth);
	}

	@Override
	public void destroy() { }

} 


