import java.util.*;

class UI implements UserCart {
    private final List<String> OurList;
    protected HashMap<String, String> customerOrder = new HashMap<>();

    UI() {
        OurList = new ArrayList<>();
        OurList.add("Watches");
        OurList.add("Goggles");
        OurList.add("T-shirt");
        OurList.add("Iphone");
        OurList.add("Jeans");
        OurList.add("Pants");
        OurList.add("Shorts");
        OurList.add("Shoes");
        OurList.add("Socks");
    }

    @Override
    public void displayOurList() {
        int blackStartCode = 0x2605; // Unicode code point for black star point
        for (String item : OurList) {
            String bullet = new String(Character.toChars(blackStartCode));
            System.out.println("\t\t\t\t\t\t" + bullet + " " + item);
            try {
                Thread.sleep(500); // 1000 milliseconds = 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addItemToUserList(int quantity, String item) {
        String key = item + "_" + quantity;
        customerOrder.put(key, item);
    }

    @Override
    public void removeItemFromUserList(HashMap<String, String> customerOrder, int inputQuantity, String itemName) {
        if (!customerOrder.isEmpty()) {
            for (Map.Entry<String, String> mapElement : customerOrder.entrySet()) {
                String key = mapElement.getKey();
                String value = mapElement.getValue();
                String[] parts = key.split("_");
                int quantity = Integer.parseInt(parts[1]);
                if (value.equalsIgnoreCase(itemName)) {
                    if (inputQuantity < quantity) {
                        int newQuantity = quantity - inputQuantity;
                        customerOrder.remove(key);
                        customerOrder.put(itemName + "_" + newQuantity, itemName);
                        System.out.println("\t\t\t\t\t\tDeletion successful.");
                        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
                        System.out.println("\t\t\t\t\t\tUpdated cart elements are:-");
                        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
                        displayUserList();
                        return; // Exit after successful deletion
                    } else if (inputQuantity == quantity) {
                        customerOrder.remove(key);
                        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
                        System.out.println("\t\t\t\t\t\tDeletion successful.");
                        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
                        System.out.println("\t\t\t\t\t\tUpdated cart elements are:-");
                        displayUserList();
                        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
                        return; // Exit after successful deletion
                    } else {
                        System.out.println("\t\t\t\t\t\tQuantity entered is greater than stored quantity.");
                        System.out.println("\t\t\t\t\t\tCannot delete the item!!");
                        return; // Exit , no need to check other entries
                    }
                }
            }
            System.out.println("\t\t\t\t\t\tSorry ! Item not found in the cart.");
            System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        } else
            System.out.println("\t\t\t\t\t\tUser list is empty. Cannot delete");
    }

    @Override
    public void displayUserList() {
        if (!customerOrder.isEmpty()) {
            for (Map.Entry<String, String> mapElement : customerOrder.entrySet()) {
                String key = mapElement.getKey();
                String value = mapElement.getValue();
                String[] parts = key.split("_");
                int quantity = Integer.parseInt(parts[1]);
                System.out.println("\t\t\t\t\t\t" + quantity + " : " + value);
                try {
                    Thread.sleep(500); // 1000 milliseconds = 1 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else
            System.out.println("\t\t\t\t\t\tUser List is Empty. Cannot display the data.");
    }

    @Override
    public boolean checkItemName(String item) {
        boolean isValid = false;
        for (String element : OurList) {
            if (item.equalsIgnoreCase(element)) {
                return true;
            }
        }
        return isValid;
    }

    public void addItemsRecursively(char reply) {
        if (reply == 'y' || reply == 'Y') {
            Scanner sc = new Scanner(System.in);
            HashMap<String, String> tempOrder = new HashMap<>(); //can be moved on top as private
            /* create a temporary HM to avoid the
               key-value pairs to
               accumulate with each recursive call, thus leading to overlapping entries */
            System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
            displayOurList();
            System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
            System.out.println("\t\t\t\t\t\tEnter item name followed by the quantity :-");
            String answer = sc.nextLine();
            String[] items = answer.split(" ");
            try {
                if (items.length != 2) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                int quantity = Integer.parseInt(items[1]);
                String value = items[0];
                tempOrder.putAll(customerOrder); // Copy existing entries to the new LinkedHashMap
                tempOrder.put(value + "_" + quantity, value); // Add the new entry
                customerOrder = tempOrder; // Update the customerOrder reference
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("\t\t\t\t\t\t❌ Invalid input .");
                return;
            }
            System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
            System.out.println("\t\t\t\t\t\tItem added successfully !!");
            System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
            System.out.println("\t\t\t\t\t\tDo you want to add more items in your cart? ( Y or N )");
            reply = sc.next().charAt(0);
            addItemsRecursively(reply);
        } else if (reply == 'N' || reply == 'n') {
            System.out.println("\t\t\t\t\t\tOk. Printing your cart now.");
            displayUserList();
            System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        } else {
            System.out.println("\t\t\t\t\t\tBad Choice !!");
        }
    }
}