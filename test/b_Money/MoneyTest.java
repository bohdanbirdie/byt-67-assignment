package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals(100.00, SEK100.getAmount(), 0);
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("100.0 SEK", SEK100.toString());
	}

	@Test
	public void testUniversalValue() {
		assertEquals(1500, SEK100.universalValue(), 0);
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(SEK100.equals(EUR10));
	}

	@Test
	public void testAdd() {
		assertTrue(new Money(2000, EUR).equals(EUR10.add(SEK100)));
	}

	@Test
	public void testSub() {
		assertTrue(new Money(1000, EUR).equals(EUR20.sub(EUR10)));
	}

	@Test
	public void testIsZero() {
		assertTrue(new Money(0, EUR).isZero());
	}

	@Test
	public void testNegate() {
		assertTrue(new Money(-10000, SEK).equals(SEK100.negate()));
	}

	@Test
	public void testCompareTo() {
		assertEquals(0, SEK100.compareTo(EUR10));
		assertEquals(-1, SEK100.compareTo(EUR20));
		assertEquals(1, SEK200.compareTo(EUR10));
	}
}
