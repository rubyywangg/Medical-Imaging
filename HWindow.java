package a2;

/**
 * A class that represents a windowed view of Hounsfield units. A Hounsfield
 * window is defined by two values: (1) the window level, and (2) the window
 * width. The window level is the Hounsfield unit value that the window is
 * centered on. The window width is the range of Hounsfield unit values that the
 * window is focused on.
 * 
 * <p>
 * A window has a lower and upper bound. The lower bound is defined as the
 * window level minus half the window width:
 * 
 * <p>
 * lo = level - (width / 2)
 * 
 * <p>
 * The upper bound is defined as the window level plus half the window width:
 * 
 * <p>
 * hi = level + (width / 2)
 * 
 * <p>
 * Hounsfield units are mapped by the window to a real number in the range of
 * {@code 0} to {@code 1}. A Hounsfield unit with a value less than lo is mapped
 * to the value {@code 0}. A Hounsfield unit with a value greater than hi is
 * mapped to the value {@code 1}. A Hounsfield unit with a value v between lo
 * and hi is mapped to the value:
 * 
 * <p>
 * (v - lo) / width
 * 
 *
 */
public class HWindow {

	private HUnit winLevel;
	
	private Interval window;

	/**
	 * Initializes this window to have a level of {@code 0} and a width of
	 * {@code 400}.
	 */
	public HWindow() {
		this(0, 400);
	}

	/**
	 * Initializes this window to have the specified level and width.
	 * 
	 * @param level the level of this window
	 * @param width the width of this window
	 * @throws IllegalArgumentException if level is less than HUnit.MIN_VALUE or if
	 *                                  level is greater than HUnit.MAX_VALUE
	 * @throws IllegalArgumentException if width is less than 1
	 */
	public HWindow(int level, int width) {
		if (width < 1) {
			throw new IllegalArgumentException("width is less than 1");
		}
		this.winLevel = new HUnit(level);
		this.window = new Interval(this.winLevel.value() - 0.5 * width, this.winLevel.value() + 0.5 * width);
	}

	/**
	 * Returns the level of this window.
	 * 
	 * @return the level of this window
	 */
	public int level() {
		return (int) this.winLevel.value();
	}

	/**
	 * Returns the width of this window.
	 * 
	 * @return the width of this window
	 */
	public int width() {
		return (int) this.window.width();
	}

	/**
	 * Sets the level of this window to the specified value.
	 * 
	 * @param level the level of this window
	 * @throws IllegalArgumentException if level is less than HUnit.MIN_VALUE or if
	 *                                  level is greater than HUnit.MAX_VALUE
	 */
	public void level(int level) {
		int old = this.level();
		this.winLevel.value(level);
		double delta = level - old;
		this.window.moveBy(delta);
	}

	/**
	 * Sets the width of this window to the specified value.
	 * 
	 * @param width the width of this window
	 * @throws IllegalArgumentException if width is less than 1
	 */
	public void width(int width) {
		if (width < 1) {
			throw new IllegalArgumentException("width is less than 1");
		}
		this.window.min(this.winLevel.value() - 0.5 * width);
		this.window.max(this.winLevel.value() + 0.5 * width);
	}

	/**
	 * Maps the value of the specified Hounsfield unit to a value between zero and
	 * one.
	 * 
	 * Hounsfield units are mapped by the window to a real number in the range of
	 * {@code 0} to {@code 1}. A Hounsfield unit with a value less than lo is mapped
	 * to the value {@code 0}. A Hounsfield unit with a value greater than hi is
	 * mapped to the value {@code 1}. A Hounsfield unit with a value v between lo
	 * and hi is mapped to the value:
	 * 
	 * <p>
	 * (v - lo) / width
	 * 
	 * @param h a Hounsfield unit
	 * @return a value between 0 and 1
	 */
	public double mapLinear(HUnit h) {
		double result = 0.0;
		int val = h.value();
		if (val < this.window.min()) {
			result = 0.0;
		} else if (val > this.window.max()) {
			result = 1.0;
		} else {
			result = (val - this.window.min()) / this.width();
		}
		return result;
	}

}
