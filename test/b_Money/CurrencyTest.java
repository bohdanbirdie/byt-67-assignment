package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {
		assertTrue(SEK.getRate().equals(0.15));
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.12);
		assertTrue(SEK.getRate().equals(0.12));
	}
	
	@Test
	public void testUniversalValue() {
		assertEquals(300, SEK.universalValue(20.0), 0);
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(100, SEK.valueInThisCurrency(10.0, EUR), 0);
	}

}
