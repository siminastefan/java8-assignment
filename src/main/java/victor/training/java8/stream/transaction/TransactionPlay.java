package victor.training.java8.stream.transaction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TransactionPlay {

	private final Trader raoul = new Trader("Raoul", "Cambridge");
	private final Trader mario = new Trader("Mario","Milan");
	private final Trader alan = new Trader("Alan","Cambridge");
	private final Trader brian = new Trader("Brian","Cambridge");

	private final List<Transaction> transactions = Arrays.asList(
	    new Transaction(brian, 2011, 300),
	    new Transaction(raoul, 2012, 1000),
	    new Transaction(raoul, 2011, 400),
	    new Transaction(mario, 2012, 710),
	    new Transaction(mario, 2012, 700),
	    new Transaction(alan, 2012, 950)
	);

	@Test //1
	public void all_2011_transactions_sorted_by_value() {
		List<Transaction> expected = Arrays.asList(transactions.get(0), transactions.get(2));

		List<Transaction> list = new ArrayList<>();
		for(Transaction transaction : transactions) {
			if (transaction.getYear() == 2011)
				list.add(transaction);
		}
		list.sort(new SortTransactionByValue());
		assertEquals(expected, list); 									
	}
		
	@Test //2
	public void unique_cities_of_the_traders() {
		List<String> expected = Arrays.asList("Cambridge", "Milan");
		
		List<String> list = null; // TODO

		assertEquals(expected, list); 									
	}
	
	@Test //3
	public void traders_from_Cambridge_sorted_by_name() {
		List<Trader> expected = Arrays.asList(alan, brian, raoul);

		List<Trader> list = null; // TODO
		
		assertEquals(expected, list);
	}
	
	@Test //4
	public void names_of_all_traders_sorted_joined() {
		String expected = "Alan,Brian,Mario,Raoul";
		
		String joined = null; // TODO
		
		assertEquals(expected, joined);
	}
			
	@Test //5
	public void are_traders_in_Milano() {
		boolean areTradersInMilan = false; // TODO
		
		assertTrue(areTradersInMilan);
	}
	
	@Test //6 
	public void sum_of_values_of_transactions_from_Cambridge_traders() { 
		int sum = -1; // TODO
		
		assertEquals(2650, sum);
	}
	
	@Test //7
	public void max_transaction_value() {
		int max = -1; // TODO
		
		assertEquals(1000, max);
	}

	
	@Test
	public void transaction_with_smallest_value() {
		Transaction expected = transactions.get(0);
		Transaction min = null; // TODO
		assertEquals(expected, min);
	}

	@Test
	public void a_transaction_from_2012() {
		Transaction expected = transactions.get(1);
		Transaction tx2012 = null; // TODO
		
		assertEquals(expected, tx2012);
	}

	// You aren't suppose to be able to solve this one: :)
	@Test
	public void uniqueCharactersOfManyWords() {
		List<String> expected = Arrays.asList("a", "b", "c", "d", "f");
		List<String> wordsStream = Arrays.asList("abcd", "acdf");
		
		List<String> actual = null; // TODO
		assertEquals(expected, actual);
	}
	
	
}
