package kore.botssdk.models;

public class TransactionDetailModel {

   // private int image;

    private String transaction;

    private String description;

    private String amount;

    public TransactionDetailModel(int image, String transaction, String description, String amount) {
        //this.image = image;
        this.transaction = transaction;
        this.description = description;
        this.amount = amount;
    }

    //public int getImage() {
       // return image;
   // }

    //public void setImage(int image) {
        //this.image = image;
    //}

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
