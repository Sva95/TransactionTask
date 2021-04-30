import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Solution {

    static void solution(String location) {
        if (File.separatorChar == '\\') {
            transformFileToList(location.replace('/', File.separatorChar));
        } else {
            transformFileToList(location.replace('\\', File.separatorChar));
        }
    }

    private static void transformFileToList(String location) {
        List<Transaction> transactions;
        TransactionConverter mapper = new TransactionConverter();

        try (Stream<String> lines = Files.lines(Paths.get(location))) {
            transactions = lines
                    .filter(line -> !line.isEmpty())
                    .map(mapper::transformToTransaction)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            HandleException.handleException(e);
            return;
        }

        List<Transaction> transactionList = mapper.countItems(transactions);
        printTransaction(transactionList);
    }

    private static void printTransaction(List<Transaction> transactionList) {
        transactionList.forEach(transaction -> {
            System.out.println(transaction.getPartner() + "|" + transaction.getCount() + "|" + transaction.getTransactinOperation());
        });
    }
}







