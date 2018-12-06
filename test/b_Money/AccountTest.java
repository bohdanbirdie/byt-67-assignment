package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("test", 10, 0, new Money(10000000, SEK), SweBank, "Alice");
		assertTrue(testAccount.timedPaymentExists("test"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("test", 10, 0, new Money(10000000, SEK), SweBank, "Alice");
		testAccount.tick();
		assertEquals(110000.0, SweBank.getBalance("Alice"), 0);
	}

	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(10000000, SEK));
		assertTrue(testAccount.getBalance().equals(new Money(0, SEK)));
	}
	
	@Test
	public void testGetBalance() {
		assertTrue(testAccount.getBalance().equals(new Money(10000000, SEK)));
	}
}
