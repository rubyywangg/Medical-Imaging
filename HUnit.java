package a2;

/**
 * A class that represents a Hounsfield unit. Hounsfield units are the units of
 * measurement used in computed tomography (CT or CAT) scanning.
 * 
 * <p>
 * The Hounsfield scale is defined by specifying the radiodensity of air as
 * {@code -1000} Hounsfield units and the radiodensity of distilled water as
 * {@code 0} Hounsfield units. Adjacent tissues in the human body can be
 * distinguished from one another if their radiodensities differ; see
 * <a href="https://en.wikipedia.org/wiki/Hounsfield_scale">the Wikipedia
 * page</a> for a table of typical Hounsfield values for tissues of the
 * human body.
 * 
 * <p>
 * CT scanners for medical purposes typically restrict the value of reported
 * Hounsfield units to integers in the interval {@code -1024} to {@code 3071} so
 * that a Hounsfield unit can be encoded as a 12-bit value. This class uses
 * the values {@code -1024} and {@code 3071} to represent the minimum and
 * maximum, respectively, allowable Hounsfield unit values.
 *
 */
public class HUnit {

	/*
	 * Add your fields, including the constants provided by this class, after this comment.
	 * 
	 * You are required to use a RangedValue object when implementing this class.
	 * 
	 * Hint: There should be 3 fields in total (2 of which are constants).
	 */
	RangedValue rv; 
	static int MIN_VALUE = -1024;
	static int MAX_VALUE = 3071;
	
	
	
	/**
	 * Initializes this Hounsfield unit to have a value of zero.
	 */
	public HUnit() {
		rv.value = 0;
		//value.value(0);
	}

	/**
	 * Initializes this Hounsfield unit to have the specified value.
	 * 
	 * @param value
	 *            the value of this Hounsfield unit
	 * @throws IllegalArgumentException
	 *             if {@code value} is less than the minimum Hounsfield unit
	 *             reported by medical CT scanners or greater than the maximum
	 *             Hounsfield unit reported by medical CT scanners
	 */
	public HUnit(int value) {
		if (value < MIN_VALUE || value > MAX_VALUE) {
			throw new IllegalArgumentException("value is outside the minimum and maximum Hounsfield unit");
		}
		//this.rv.value = value;
		rv = new RangedValue(MIN_VALUE, MAX_VALUE, value);
	}

	/**
	 * Initializes this Hounsfield unit by copying the value from the specified
	 * other Hounsfield unit.
	 * 
	 * @param other
	 *            the Hounsfield unit to copy the value from
	 */
	public HUnit(HUnit other) {
		rv = new RangedValue(MAX_VALUE, MIN_VALUE, other.value());
		this.rv.value = other.rv.value;
	}

	/**
	 * Returns the value of this Hounsfield unit.
	 * 
	 * @return the value of this Hounsfield unit
	 */
	public int value() {
		return (int)rv.value;
	}

	/**
	 * Sets the value of this Hounsfield unit to the specified value returning
	 * the value that was overwritten.
	 * 
	 * @param value
	 *            the value to set this Hounsfield unit to
	 * @return the overwritten value of this Hounsfield unit
	 * @throws IllegalArgumentException
	 *             if the specified value is less than the minimum Hounsfield
	 *             unit reported by medical CT scanners or greater than the
	 *             maximum Hounsfield unit reported by medical CT scanners.
	 */
	public int value(int value) {
		if (value < MIN_VALUE || value > MAX_VALUE) {
			throw new IllegalArgumentException("value is less than maximum or minimum Hounsfield units");
		}
		int overwritten = (int)rv.value;
		this.rv.value = value;
		return overwritten;
	}

	/**
	 * Returns a string representation of this Hounsfield unit. The returned
	 * string is the numeric value of this Hounsfield unit (formatted as an
	 * integer) inside of a matched pair of braces {}.
	 */
	@Override
	public String toString() {
		return "{" + value() + "}";
	}
}
