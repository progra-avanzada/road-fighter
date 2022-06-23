package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.AudioResources;
import road_fighter.utils.GameObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Score extends GameObject implements Renderable {
	private final int Y = 20;
	private int score = 0;

	private Text scoreText;
	private Text maxScoreText;
	private VBox render;

	private Animation zoomAnimation;
	private AudioClip pointAudio;

	public Score(int posX) {
		
		scoreText = new Text("" + score);
		maxScoreText = new Text("TOP: " + Config.maxScore);

		render = new VBox(maxScoreText, scoreText);
		render.setSpacing(5);
//		render.setAlignment(Pos.TOP_CENTER);
		render.setTranslateX(posX + 30);
		render.setTranslateY(Y);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		pointAudio = AudioResources.getCoinsAudio();

		Font font = Font.loadFont(ClassLoader.getSystemResource("font/flappy-bird-numbers.ttf").toString(), 50);
		scoreText.setTextAlignment(TextAlignment.CENTER);
		scoreText.setFont(font);
		scoreText.setFill(Color.WHITE);
		
//		maxScoreText.setFont(Font.font("MONOSPACE", 15));
		Font font1 = Font.loadFont(ClassLoader.getSystemResource("font/road-fighter.ttf").toString(), 15);
		maxScoreText.setTextAlignment(TextAlignment.CENTER);
		maxScoreText.setFont(font1);
		maxScoreText.setFill(Color.WHITE);
		

		DropShadow ds = new DropShadow();
		ds.setColor(Color.RED);
		scoreText.setEffect(ds);
	}

	@Override
	public Node getRender() {
		return render;
	}

	public void increase() {
		score++;
		scoreText.setText("" + score);
		this.updateHighScore();

		zoomAnimation = new Timeline(
				new KeyFrame(Duration.ZERO, new KeyValue(scoreText.scaleXProperty(), 1),
						new KeyValue(scoreText.scaleYProperty(), 1)),
				new KeyFrame(Duration.seconds(0.15),
						new KeyValue(scoreText.scaleXProperty(), Math.min(1.05 + score / 200.0, 2)),
						new KeyValue(scoreText.scaleYProperty(), Math.min(1.05 + score / 200.0, 2))),
				new KeyFrame(Duration.seconds(0.4), new KeyValue(scoreText.scaleXProperty(), 1),
						new KeyValue(scoreText.scaleYProperty(), 1)));
		zoomAnimation.play();

		pointAudio.play();
	}

	public int getScore() {
		return score;
	}
	
	public void updateHighScore() {
		if (this.score > Config.maxScore) {
			Config.maxScore = this.score;
		}
		maxScoreText.setText("TOP: " + Config.maxScore);
	}

	@Override
	public void destroy() {	}
	
}
