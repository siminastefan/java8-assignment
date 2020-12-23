package victor.training.java8.stream.transaction;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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

		List<Transaction> list = expected.stream()
				.filter(this::isYear2011)
				.sorted(Comparator.comparing(Transaction::getValue))
				.collect(toList());


		assertEquals(expected, list); 									
	}

	public boolean isYear2011(Transaction transaction) {
		return transaction.getYear() == 2011;
	}

	@Test //2
	public void unique_cities_of_the_traders() {
		List<String> expected = Arrays.asList("Cambridge", "Milan");

		List<Trader> traders = transactions.stream()
				.map(Transaction::getTrader)
				.collect(toList());

		List<String> list = traders.stream()
				.map(Trader::getCity)
				.distinct()
				.collect(toList());

		assertEquals(expected, list); 									
	}

	@Test //3
	public void traders_from_Cambridge_sorted_by_name() {
		List<Trader> expected = Arrays.asList(alan, brian, raoul);


		List<Trader> list = transactions.stream()
				.map(Transaction::getTrader)
				.filter(this::isCityCambridge)
				.distinct()
				.sorted(Comparator.comparing(Trader::getName))
				.collect(toList());
		
		assertEquals(expected, list);
	}

	private boolean isCityCambridge(Trader trader) {
		return trader.getCity().equals("Cambridge");
	}

	@Test //4
	public void names_of_all_traders_sorted_joined() {
		String expected = "Alan,Brian,Mario,Raoul";

		List<Trader> traders = transactions.stream()
				.map(Transaction::getTrader)
				.distinct()
				.sorted(Comparator.comparing(Trader::getName))
				.collect(toList());

		String joined = traders.stream()
				.map(Trader::getName)
				.collect(Collectors.joining(","));
		
		assertEquals(expected, joined);
	}
			
	@Test //5
	public void are_traders_in_Milano() {
		boolean areTradersInMilan = transactions.stream()
				.map(Transaction::getTrader)
				.anyMatch(this::isTraderInMilan);
		
		assertTrue(areTradersInMilan);
	}

	private boolean isTraderInMilan(Trader trader) {
		return trader.getCity().equals("Milan");
	}

	@Test //6 
	public void sum_of_values_of_transactions_from_Cambridge_traders() { 
		int sum = transactions.stream()
				.filter(this::isTransactionsTraderFromCambridge)
				.mapToInt(Transaction::getValue)
				.sum();
		
		assertEquals(2650, sum);
	}

	private boolean isTransactionsTraderFromCambridge(Transaction transaction) {
		return transaction.getTrader().getCity().equals("Cambridge");
	}

	@Test //7
	public void max_transaction_value() {
		int max = transactions.stream()
				.map(Transaction::getValue)
				.max(Integer::compare)
				.get();
		
		assertEquals(1000, max);
	}

	
	@Test
	public void transaction_with_smallest_value() {
		Transaction expected = transactions.get(0);
		Transaction min = transactions.stream()
				.min(Comparator.comparing(Transaction::getValue))
				.orElse(null);
		assertEquals(expected, min);
	}

	@Test
	public void a_transaction_from_2012() {
		Transaction expected = transactions.get(1);
		Transaction tx2012 = transactions.stream()
				.filter(this::isTransactionFrom2012)
				.findFirst()
				.orElse(null);
		
		assertEquals(expected, tx2012);
	}

	private boolean isTransactionFrom2012(Transaction transaction) {
		return transaction.getYear() == 2012;
	}

	// You aren't suppose to be able to solve this one: :)
	@Test
	public void uniqueCharactersOfManyWords() {
		List<String> expected = Arrays.asList("a", "b", "c", "d", "f");
		List<String> wordsStream = Arrays.asList("abcd", "acdf");
		
		List<String> actual = wordsStream.stream()
				.map(this::getListOfCharsInString)
				.flatMap(List::stream)
				.distinct()
				.collect(toList());

		assertEquals(expected, actual);
	}

	public List<String> getListOfCharsInString(String string) {
		return string.codePoints()
				.mapToObj(c -> String.valueOf((char) c))
				.distinct()
				.collect(toList());
	}


}
