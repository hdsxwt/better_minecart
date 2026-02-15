package hdsxwt.better_minecart;

public class Colors {
	public static final Colors BLACK = new Colors(0xFF000000); // black
	public static final Colors WHITE = new Colors(0xFFFFFFFF); // white
	public static final Colors GREEN = new Colors(0xFF00FF00); // green
	public static final Colors RED = new Colors(0xFFFF0000); // red
	public static final Colors BLUE = new Colors(0xFF0000FF); // blue
	public static final Colors YELLOW = new Colors(0xFFFFFF00); // yellow
	public static final Colors CYAN = new Colors(0xFF00FFFF); // cyan
	public static final Colors PURPLE = new Colors(0xFFFF00FF); // purple
	public static final Colors ORANGE = new Colors(0xFFFFA500); // orange
	public static final Colors GRAY = new Colors(0xFF808080); // gray
	public static final Colors LIGHT_GRAY = new Colors(0xFFD3D3D3); // light gray
	public static final Colors DARK_GRAY = new Colors(0xFF404040); // dark gray


	public int a, r, g, b;
	/**
	 * Creates a Colors object from individual ARGB components.
	 * @param a alpha(0-255)
	 * @param r red(0-255)
	 * @param g green(0-255)
	 * @param b blue(0-255)
	 */
	public Colors(int a, int r, int g, int b) {
		this.a = a;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	/**
	 * Creates a Colors object from a single ARGB integer.
	 * @param color ARGB color as an integer (0xAARRGGBB)
	 */
	public Colors (int color) {
		this.a = (color >> 24) & 0xFF;
		this.r = (color >> 16) & 0xFF;
		this.g = (color >> 8) & 0xFF;
		this.b = color & 0xFF;
	}

	/**
	 * Converts this Colors object back to a single ARGB integer.
	 * @return ARGB color as an integer (0xAARRGGBB)
	 */
	public int toInt() {
		return (a << 24) | (r << 16) | (g << 8) | b;
	}
}