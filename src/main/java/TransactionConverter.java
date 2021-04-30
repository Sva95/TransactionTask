import java.util.*;
import java.util.stream.IntStream;

class TransactionConverter {

    private int indexLine = 0;
    private Map<String, Integer> partners = new HashMap<>();

    // only to improve perfomance
    private Map<String, Long> partnersFrequency = new HashMap<>();

    public Transaction transformToTransaction(String file)  {
        String[] splitString = StringUtil.reverse(file).split("([,/])", 4);
        String time = StringUtil.cleanWithReverse(splitString[0]);
        String phone = StringUtil.cleanWithReverse(splitString[1]);
        String provider = StringUtil.cleanWithReverse(splitString[2]);
        String transactions = StringUtil.cleanWithReverse(splitString[3]);

        Transaction transaction = new Transaction();
        transaction.setId(indexLine);
        transaction.setTransactinOperation(transactions);
        transaction.setPartner(provider);
        transaction.setPhone(phone);
        transaction.setDate(StringUtil.convertTransactionTime(time));
        indexLine++;
        return transaction;
    }

    public List<Transaction> countItems(List<Transaction> transactionList) {
        transactionList.sort(Comparator.comparingLong(o -> o.getDate().getTime()));

        IntStream.range(0, transactionList.size())
                .forEach(index -> {
                    String partner = transactionList.get(index).getPartner();
                    addPartner(partner);

                    long partnerFrequency = getProviderFrequency(transactionList, partner);
                    int countPartner = partners.get(partner);
                    String count = StringUtil.countFrequency(countPartner, partnerFrequency);
                    transactionList.get(index).setCount(count);
                });

        transactionList.sort(Comparator.comparingInt(Transaction::getId));
        return transactionList;
    }

    // to avoid multiply Collections.frequency calls
    private int getProviderFrequency(List<Transaction> transactionList, String partner) {
        if (partnersFrequency.containsKey(partner)) {
            return Math.toIntExact(partnersFrequency.get(partner));
        } else {
            long frequency = transactionList.stream()
                    .filter(transaction -> transaction.getPartner().equals(partner))
                    .count();
            partnersFrequency.put(partner, frequency);
            return (int) frequency;
        }
    }

    private void addPartner(String partner) {
        if (partners.containsKey(partner)) {
            partners.put(partner, partners.get(partner) + 1);
        } else {
            partners.put(partner, 1);
        }
    }


}



