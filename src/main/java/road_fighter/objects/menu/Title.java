package road_fighter.objects.menu;

import road_fighter.Config;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;

public class Title extends GameObject implements Renderable {
	private final int Y = Config.baseHeight / 3 - 35;
	
	private final TranslateTransition idleAnimation;
	private final Duration translateDuration = Duration.millis(1000);

	private Text text;
	private VBox render;

	public Title() {
		text = new Text("Road Fighter");

		render = new VBox(text);
		render.setAlignment(Pos.CENTER);
		render.setTranslateY(Y);
		render.setPrefWidth(Config.baseWidth);

		Font font = Font.loadFont(ClassLoader.getSystemResource("font/road-fighter.ttf").toString(), 70);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font);
		text.setFill(Color.BLACK);
		
		DropShadow ds = new DropShadow();
		ds.setColor(Color.WHITE);
		text.setEffect(ds);
		
		idleAnimation = initIdleAnimation();
	}

	@Override
	public Node getRender() {
		return render;
	}
	
	private TranslateTransition initIdleAnimation() {
		TranslateTransition translateTransition = new TranslateTransition(translateDuration, render);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setFromY(Y -10);
		translateTransition.setToY(Y + 10);
		translateTransition.setAutoReverse(true);
		translateTransition.play();
		return translateTransition;
	}

	@Override
	public void destroy() {
		idleAnimation.stop();
	}

}
