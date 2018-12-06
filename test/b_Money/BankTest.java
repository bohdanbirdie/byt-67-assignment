package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		try {
			SweBank.openAccount("Bob");
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(true);
		}
		
		SweBank.openAccount("Bob1");
		assertEquals(0.0, SweBank.getBalance("Bob1"), 0);
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		assertEquals(10.0, SweBank.getBalance("Ulrika"), 0);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		SweBank.withdraw("Ulrika", new Money(1000, SEK));
		assertEquals(0.0, SweBank.getBalance("Ulrika"), 0);
		
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(1000, SEK));
		assertEquals(10.0, SweBank.getBalance("Ulrika"), 0);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		Nordea.deposit("Bob", new Money(1000, SEK));
		Nordea.transfer("Bob", SweBank, "Bob", new Money(1000, SEK));
		assertEquals(10.0, SweBank.getBalance("Bob"), 0);
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.addTimedPayment("Bob", "1", 1, 0, new Money(1000, SEK), Nordea, "Bob");
		SweBank.tick();
		assertEquals(10.0, Nordea.getBalance("Bob"), 0);
	}
}
