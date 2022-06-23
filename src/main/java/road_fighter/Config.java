package road_fighter;

//import javax.sound.midi.Synthesizer;

public class Config {
	public final static int baseHeight = 720;
	public final static int baseWidth = 1280;
	
	public final static int distanciaMax = 10000;
	
	public final static int sizeRoadImage = 128;
	public final static int roadWidth = sizeRoadImage * 3;
	public final static int platformWidth = 20;
//	public final static int roadLeft = (baseWidth - roadWidth) / 2;
//	public final static int roadRight = roadLeft + roadWidth;
	
	public final static double gravity = 1300;
	public final static double jumpForce = 500;
	
	
	/// OBSTACLES
	public final static int startObstacles = 500;
	
	/// BARRELS
	public final static int distancePerBarrel = 100;
	public final static int barrelSize = 56;
	
	/// BOOST
	public final static int distancePerBoost = 100;
	public final static int boostSize = 56;
	
	
	public static int maxScore = 0;
	
	/// ALL PLAYERS
	public final static int emptySpace = 100;
	public final static int sizeBar = 156;
	public final static int playerHeight = baseHeight / 4 * 3;
	
	
	/// SINGLE PLAYER
	public final static int SinglePlayerPosX = baseWidth / 2;
	public final static int barSinglePlayer = 292;
	
	/// PLAYER 1
//	public static int distanciaActual1 = 0;
//	public static double speedPlayer1 = 500;
	public final static int Player1PosX = sizeBar + sizeRoadImage;
	public final static int barPlayer1 = 0;
	public final static int posXRoadPlayer1 = 156;
	
	/// PLAYER 2
//	public static int distanciaActual2 = 0;
//	public static double speedPlayer2 = 500;
	public final static int Player2PosX = baseWidth / 2 + Player1PosX + sizeRoadImage;
	public final static int barPlayer2 = baseWidth / 2;
	public final static int posXRoadPlayer2 = baseWidth / 2 + 156;

	private Config() {
	}
}
