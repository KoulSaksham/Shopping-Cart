import java.util.HashMap;
import java.util.Map;

public class CheckingOut implements CheckOut {
    private double billTotal;
    private double afterDiscount;

    @Override
    public double addTotal(HashMap<String, String> userList) {
        if (!userList.isEmpty()) {
            for (Map.Entry<String, String> mapElement :
                    userList.entrySet()) {
                String key = mapElement.getKey();
                String value = mapElement.getValue();

                String[] parts = key.split("_");
                int quantity = Integer.parseInt(parts[1]);

                if (value.equalsIgnoreCase("Watches"))
                    billTotal += 105.20 * quantity;
                else if (value.equalsIgnoreCase("Googles"))
                    billTotal += 75.43 * quantity;
                else if (value.equalsIgnoreCase("T-shirt"))
                    billTotal += 71.33 * quantity;
                else if (value.equalsIgnoreCase("Iphone"))
                    billTotal += 300.99 * quantity;
                else if (value.equalsIgnoreCase("Jeans"))
                    billTotal += 94.49 * quantity;
                else if (value.equalsIgnoreCase("Pants"))
                    billTotal += 77.99 * quantity;
                else if (value.equalsIgnoreCase("Shorts"))
                    billTotal += 45.12 * quantity;
                else if (value.equalsIgnoreCase("Shoes"))
                    billTotal += 31.17 * quantity;
                else
                    billTotal += 12.07 * quantity;
            }
        } else {
            System.out.println("User list was found EMPTY.");
            billTotal = 0.0;
        }
        return billTotal;
    }

    @Override
    public double discountCheck(HashMap<String, String> userList) {
        if (!userList.isEmpty()) {
            for (Map.Entry<String, String> mapElement :
                    userList.entrySet()) {
                String key = mapElement.getKey();
                String value = mapElement.getValue();

                String[] parts = key.split("_");
                int quantity = Integer.parseInt(parts[1]);

                if (value.equalsIgnoreCase("Watches"))
                    afterDiscount = billTotal - (billTotal * 0.10);
                else if (value.equalsIgnoreCase("Googles"))
                    afterDiscount = billTotal - (billTotal * 0.15);
                else if (value.equalsIgnoreCase("Iphone"))
                    afterDiscount = billTotal - (billTotal * 0.15);
                else if (value.equalsIgnoreCase("Formal Shirt"))
                    afterDiscount = billTotal - (billTotal * 0.4);
                else if (value.equalsIgnoreCase("Jeans"))
                    afterDiscount = billTotal - (billTotal * 0.25);
                else if (value.equalsIgnoreCase("Pants"))
                    afterDiscount = billTotal - (billTotal * 0.15);
                else if (value.equalsIgnoreCase("Shorts"))
                    afterDiscount = billTotal - (billTotal * 0.20);
                else if (value.equalsIgnoreCase("Shoes"))
                    afterDiscount = billTotal - (billTotal * 0.35);
                else  //for socks
                    afterDiscount = billTotal - (billTotal * 0.31);
            }
        } else {
            System.out.println("User list was found EMPTY.");
            afterDiscount = 0.0;
        }
        return afterDiscount;
    }

    @Override
    public double finalBill() {
        double tax = afterDiscount * 0.30;
        return afterDiscount + tax;
    }
}

