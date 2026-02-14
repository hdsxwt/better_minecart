package hdsxwt.better_minecart;

public class Colors {
	public static final int BLACK = 0xFF000000; // black
	public static final int WHITE = 0xFFFFFFFF; // white
	public static final int GREEN = 0xFF00FF00; // green
	public static final int RED = 0xFFFF0000; // red
	public static final int BLUE = 0xFF0000FF; // blue
	public static final int YELLOW = 0xFFFFFF00; // yellow
	public static final int CYAN = 0xFF00FFFF; // cyan
	public static final int PURPLE = 0xFFFF00FF; // purple
	public static final int ORANGE = 0xFFFFA500; // orange
	public static final int GRAY = 0xFF808080; // gray
	public static final int LIGHT_GRAY = 0xFFD3D3D3; // light gray
	public static final int DARK_GRAY = 0xFF404040; // dark gray


	public int a, r, g, b;

	public Colors(int a, int r, int g, int b) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Colors (int color) {
		this.a = (color >> 24) & 0xFF;
		this.r = (color >> 16) & 0xFF;
		this.g = (color >> 8) & 0xFF;
		this.b = color & 0xFF;
	}

	public int toInt() {
		return (a << 24) | (r << 16) | (g << 8) | b;
	}

	public static int color(int a, int r, int g, int b) {
		return (a << 24) | (r << 16) | (g << 8) | b;
	}
}