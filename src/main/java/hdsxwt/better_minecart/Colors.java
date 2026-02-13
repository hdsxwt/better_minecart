package hdsxwt.better_minecart;

public class Colors {
	public static final int WHITE = 0xFFFFFFFF; // white
	public static final int GREEN = 0xFF00FF00; // green
	public static final int RED = 0xFFFF0000; // red

	public static int color(int transparent, int r, int g, int b) {
		return (transparent << 24) | (r << 16) | (g << 8) | b;
	}
}