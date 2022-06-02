package roadfighter.objects;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import roadfighter.Config;

public class StaticBackground extends Background{
    private final int cityWidth = 136;
    private final int cityHeight = 152;

    public StaticBackground(String imagePath) {
        Image backgroundImage = new Image(imagePath, cityWidth, cityHeight, false, false);

        // pattern
        ImagePattern image_pattern = new ImagePattern(backgroundImage, cityWidth, cityHeight, cityWidth, cityHeight,
                false);

        Rectangle city = new Rectangle(Config.baseWidth + cityWidth, cityHeight);

        city.setFill(image_pattern);

        render = new VBox(city);
        // TODO zIndex list
        render.setViewOrder(10);
    }

    @Override
    public void update(double deltaTime) {

    }
}
