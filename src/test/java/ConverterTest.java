import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ConverterTest {

    String line = "payment weekly, Netflix/603603603, 2013-09-05 14:08:15 ";

    @Test
    public void transformToTransaction() {
        TransactionConverter converter = new TransactionConverter();
        String expected = "Netflix|null|payment weekly";
        Transaction transaction = converter.transformToTransaction(line);
        String actual = transaction.getPartner() + "|" + null + "|" + transaction.getTransactinOperation();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countItems() {
        TransactionConverter converter = new TransactionConverter();
        List<Transaction> transactionList = new ArrayList<>();

        String expected = "Netflix|1|payment weekly";

        Transaction transaction = converter.transformToTransaction(line);
        transactionList.add(transaction);

        List<Transaction> exc = converter.countItems(transactionList);
        Transaction expectedTransaction = exc.get(0);

        String actual = expectedTransaction.getPartner() + "|" + expectedTransaction.getCount() + "|" + expectedTransaction.getTransactinOperation();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFrequencyCountPartner() {
        TransactionConverter converter = new TransactionConverter();
        List<Transaction> transactionList = new ArrayList<>();
        String expected = "01";

        String testLine = "payment weekly, Netflix/603603603, 2013-09-05 14:08:1";

        IntStream.range(0, 11).forEach(index -> {
            Transaction transaction = null;
            transaction = converter.transformToTransaction(testLine + index);
            transactionList.add(transaction);
        });

        List<Transaction> exc = converter.countItems(transactionList);
        Transaction actual = exc.get(0);
        Assert.assertEquals(expected, actual.getCount());
    }

}