package victor.training.java8.stream.transaction;

import java.util.Comparator;

public class SortTransactionByValue implements Comparator<Transaction> {
    @Override
    public int compare(Transaction o1, Transaction o2) {
        if (o1.getValue() > o2.getValue())
            return 1;
        return 0;
    }
}
