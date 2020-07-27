package BankServer.src;

public class Receipt {
    private String type;
    private int money;
    private String sourceID;
    private String destID;
    private String details;
    private static int receiptIds = 0;
    private int id;
    private int paid;

    public Receipt(String type, int money, String sourceID, String destID, String details) {
        this.type = type;
        this.money = money;
        this.sourceID = sourceID;
        this.destID = destID;
        this.details = details;
        this.id = ++receiptIds;
        this.paid = 0;
        BankServer.receipts.add(this);
    }

    public String getType() {
        return type;
    }

    public int getMoney() {
        return money;
    }

    public String getSourceID() {
        return sourceID;
    }

    public String getDestID() {
        return destID;
    }

    public String getDetails() {
        return details;
    }

    public int getId() {
        return id;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "{\"receiptType\":\"" + type +
                "\",\"money\":\"" + money +
                "\",\"sourceAccountID\":\"" + sourceID +
                "\",\"destAccountID\":\"" + destID +
                "\",\"description\":\"" + details +
                "\",\"id\":\"" + id +
                "\",\"paid\":\"" + paid +
                "\"}";
    }
}
