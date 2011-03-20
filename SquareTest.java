import static org.junit.Assert.*;
import org.junit.*;

/**
 * A class collecting tests for the class of squares
 *
 * @author Roald Frederickx
 */
public class SquareTest {
	/**
	 * Instance variable referencing bank accounts that may
	 * change during individual tests.
	 */
	private Square squareTemp100;
	private Square squareTempNeg5;
	private Square squareTempNeg14p99;
	private Square squareTempNeg15;
	private Square squareTempNeg15p01;
	private Square squareTempNeg100;
	private Square squareTemp35;
	private Square squareTemp40;
	private Square squareTemp49p99;
	private Square squareTemp50;

	/**
	 * Set up a mutable test fixture.
	 */
	@Before
	public void setUpMutableFixture() {
		squareTemp100      = new Square(new Temperature(100), 5000);

		squareTempNeg5     = new Square(new Temperature(-5), 5000);
		squareTempNeg14p99 = new Square(new Temperature(-14.99), 5000);
		squareTempNeg15    = new Square(new Temperature(-15), 5000);
		squareTempNeg15p01 = new Square(new Temperature(-15.01), 5000);
		squareTempNeg100   = new Square(new Temperature(-100), 5000);


		squareTemp35    = new Square(new Temperature(35), 5000);
		squareTemp40    = new Square(new Temperature(40), 5000);
		squareTemp49p99 = new Square(new Temperature(49.99), 5000);
		squareTemp50    = new Square(new Temperature(50), 5000);
	}

	//TODO: test constructor?


	@Test
	public void setTemperature_LegalCase() {
		squareTemp100.setTemperature(new Temperature(200));
		assertEquals(200, squareTemp100.getTemperature().temperature(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setTemperature_TooHigh() throws Exception {
		if (!squareTemp100.canHaveAsTemperature(new Temperature(Double.MAX_VALUE)))
			squareTemp100.setTemperature(new Temperature(Double.MAX_VALUE));
		else
			throw new IllegalArgumentException();
	}

	@Test(expected = IllegalArgumentException.class)
	public void setTemperature_TooLow() throws Exception {
		if (!squareTemp100.canHaveAsTemperature(new Temperature(Double.MIN_VALUE)))
			squareTemp100.setTemperature(new Temperature(Double.MIN_VALUE));
		else
			throw new IllegalArgumentException();
	}

	@Test
	public void setMaxTemperature_LegalCase() {
		squareTemp100.setMaxTemperature(new Temperature(1000));
		assertEquals( 1000, squareTemp100.getMaxTemperature().temperature(), 0);
	}

	@Test
	public void setMinTemperature_LegalCase() {
		squareTemp100.setMinTemperature(new Temperature(-1000));
		assertEquals(-1000, squareTemp100.getMinTemperature().temperature(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxTemperature_BelowTemp() {
		squareTemp100.setMaxTemperature(new Temperature(99));
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMinTemperature_AboveTemp() {
		squareTemp100.setMinTemperature(new Temperature(101));
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMaxTemperature_BelowMin() {
		Temperature lower = new Temperature(
				squareTemp100.getMinTemperature().temperature() - 1);
		squareTemp100.setMaxTemperature(lower);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setMinTemperature_AboveMax() {
		Temperature higher = new Temperature(
				squareTemp100.getMaxTemperature().temperature() + 1);
		squareTemp100.setMinTemperature(higher);
	}

	@Test
	public void coldDamage(){
		assertEquals(0, squareTemp100.coldDamage());
		assertEquals(1, squareTempNeg5.coldDamage());
		assertEquals(1, squareTempNeg14p99.coldDamage());
		assertEquals(2, squareTempNeg15.coldDamage());
		assertEquals(2, squareTempNeg15p01.coldDamage());
		assertEquals(10,squareTempNeg100.coldDamage());
	}


	@Test
	public void heatDamage(){
		assertEquals(0, squareTempNeg5.heatDamage());
		assertEquals(1, squareTemp35.heatDamage());
		assertEquals(1, squareTemp40.heatDamage());
		assertEquals(1, squareTemp49p99.heatDamage());
		assertEquals(2, squareTemp50.heatDamage());
	}


	public void setHeatDamageThreshold_LegalCase() {
		Square.setHeatDamageThreshold(new Temperature(100));
		assertEquals(100, Square.getHeatDamageThreshold().temperature(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHeatDamageThreshold_Null() {
		Square.setHeatDamageThreshold(null);
	}

	public void setHeatDamageStep_LegalCase() {
		Square.setHeatDamageThreshold(new Temperature(100));
		assertEquals(100, Square.getHeatDamageStep(), 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHeatDamageStep_Zero() {
		Square.setHeatDamageStep(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void setHeatDamageStep_Negative() {
		Square.setHeatDamageStep(-1);
	}
}

