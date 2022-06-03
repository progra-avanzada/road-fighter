package road_fighter;

public class Config {
	public final static int baseHeight = 600;
	public final static int baseWidth = 800;
//	public final static int groundHeight = 80;
	public final static int groundHeight = 128;
	
	public static int distanciaActual = 0;
	public final static int distanciaMax = 10000;
	
	public final static int imageWidth = 128;
	public final static int platformWidth = 20;
	public final static int roadLeft = (baseWidth - 3 * imageWidth) / 2;
	public final static int roadRight = roadLeft + 3 * imageWidth;
	

	public final static double gravity = 1300;
	public final static double jumpForce = 500;
	public static double baseSpeed = 500;
	

	public final static double emptySpace = 0.25;

	public final static double pipesPerSecond = 1.3;
	public final static double barrelsPerSecond = 0.7;
	public final static int playerCenter = baseWidth / 2;
	public final static int playerHeight = baseHeight / 4 * 3;
	
	public static int maxScore = 0;

	private Config() {
	}
}
