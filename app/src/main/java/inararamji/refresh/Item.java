package inararamji.refresh;

import java.util.Date;

/**
 * Created by inara on 26/02/2018.
 */

public class Item {

    public String name;
    public int quantity;
    public String expiryDate;
    public String additionalInfo;

    public Item() {

    }

    public Item(String name, int quantity, String expiryDate, String additionalInfo) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
        this.additionalInfo = additionalInfo;
    }
}
