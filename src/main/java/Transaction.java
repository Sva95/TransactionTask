import java.util.Date;

public class Transaction {

    private String transactinOperation, partner, phone, count;
    private int id;
    private Date date;

    public Transaction(){}


    public String getTransactinOperation() {
        return transactinOperation;
    }

    public void setTransactinOperation(String transactinOperation) {
        this.transactinOperation = transactinOperation;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCount() { return count; }

    public void setCount(String count) { this.count = count; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }


}
