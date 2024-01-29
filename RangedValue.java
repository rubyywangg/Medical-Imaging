package a2;


/**
 * A floating-point value that is guaranteed to lie within a fixed interval.
 * The interval of values that the value can take cannot be modified after
 * a {@code RangedValue} object is created, but the value can be modified.
 *
 */
public class RangedValue {

	/*
	 * Add your fields after this comment. One of your fields must be an
	 * Interval variable.
	 * 
	 * Hint: There should be 2 fields in total.
	 */
	Interval interval; //this interval object will create a range
	double value; //this is the floating point value guaranteed in a fixed interval
	
	

	/**
	 * Initializes this ranged value to the specified value in the specified interval.
	 * 
	 * @param min   the minimum value that this value can have
	 * @param max   the maximum value that this value can have
	 * @param value the value of this object
	 * @throws IllegalArgumentException if min or max is NaN
	 * @throws IllegalArgumentException if min is greater than max
	 * @throws IllegalArgumentException if val is less than min or greater than max
	 */
	public RangedValue(double min, double max, double value) {
		interval = new Interval(min, max);
		if (Double.isNaN(min)||Double.isNaN(max) || Double.isNaN(value)) {
			throw new IllegalArgumentException("inputted values must be numbers"); 
		}
		if (value < interval.min || value > interval.max) {
			throw new IllegalArgumentException("value can't be smaller than minimum or greater than maximum");
		}
		interval.min = min;
		//interval.min(min);
		this.interval.min = min;
		this.interval.max = max;
		this.value = value;
	}
	
	
	/**
	 * Initializes this ranged value by copying another ranged value so that both
	 * ranged values have equal values and intervals.
	 * 
	 * <p>
	 * After calling this constructor, the interval of this ranged value may be
	 * changed without affecting the interval of the copied ranged value, and vice versa.
	 * 
	 * @param other a ranged value to copy
	 */
	public RangedValue(RangedValue other) {
		interval = new Interval(other.min(), other.max());
		this.interval.min = other.interval.min;
		this.interval.max = other.interval.max;
		this.value = other.value;
	}

	/**
	 * Returns the minimum value that this value can have.
	 * 
	 * @return the minimum value that this value can have
	 */
	public double min() {
		return interval.min;
	}

	/**
	 * Returns the maximum value that this value can have.
	 * 
	 * @return the maximum value that this value can have
	 */
	public double max() {
		return interval.max;
	}

	/**
	 * Returns an {@code Interval} object representing the interval of values that this
	 * object can have. Changes in the returned {@code Interval} object are not reflected
	 * in the interval of this object.
	 * 
	 * @return the interval of values that this object can
	 *         have
	 */
	public Interval interval() {
		return interval;
	}

	/**
	 * Returns the value of this object.
	 * 
	 * @return the value of this object
	 */
	public double value() {
		return value;
	}

	/**
	 * Sets the value of this object to the specified value. The value must lie
	 * within the interval of this object.
	 * 
	 * @param value the value of this object
	 * @throws IllegalArgumentException if the specified value is outside of the
	 *                                  interval of this object
	 */
	public void value(double value) {
		if (value > interval.max || value < interval.min) {
			throw new IllegalArgumentException("value is outside of the interval");
		}
		this.value = value;
	}


	/**
	 * Returns a string representation of this value. The returned string has the
	 * form {@code [min : value : max]} where {@code min} is the minimum value of
	 * the interval, {@code max} is the maximum value of the interval, and {@code value}
	 * is the value of this object.
	 * 
	 * @return a string representation of this object
	 */
	@Override
	public String toString() {
		return "[" + interval.min + ": " + value + ": " + interval.max + "]";
	}

}
