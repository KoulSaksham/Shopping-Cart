import java.util.*;

class UI implements UserCart {
    private final List<String> OurList;
    protected HashMap<String, String> customerOrder = new HashMap<>();

    UI() {
        OurList = new ArrayList<>();
        OurList.add("1. Watches");
        OurList.add("2. Goggles");
        OurList.add("3. T-shirts");
        OurList.add("4. Formal Shirt"); //formal shirt 3
        OurList.add("5. Jeans");
        OurList.add("6. Pants");
        OurList.add("7. Shorts");
        OurList.add("8. Shoes");
        OurList.add("9. Socks");
    }

    @Override
    public void displayOurList() {
        for (String item : OurList)
            System.out.println("\t\t\t\t\t\t"+item);
    }
            //shorts 2
            //pants 2
    @Override
    public void addItemToUserList(int quantity, String item) {
        String key = item + "_" + quantity;
        customerOrder.put(key, item);
    }

    @Override
    public void removeItemFromUserList(String item) {
        if (!customerOrder.isEmpty())
            customerOrder.remove(item);
        else
            System.out.println("\t\t\t\t\t\tUser list is empty.");
    }

    @Override
    public void displayUserList() {
        if (!customerOrder.isEmpty()) {
            for (Map.Entry<String, String> mapElement : customerOrder.entrySet()) {
                String key = mapElement.getKey();
                String value = mapElement.getValue();
                String[] parts = key.split("_");
                int quantity = Integer.parseInt(parts[1]);
                System.out.println("\t\t\t\t\t\t"+quantity + " : " + value);
            }
           // System.out.println("storage in hashmap-> "+customerOrder);
        } else
            System.out.println("\t\t\t\t\t\tUser List is Empty. Cannot display the data.");
    }

    public void addItemsRecursively(char reply) {
        if (reply == 'y' || reply == 'Y') {
            Scanner sc = new Scanner(System.in);
            HashMap<String, String> tempOrder = new HashMap<>();  //create a temporary HM to avoid the key-value pairs
            // accumulate with each recursive call, leading to overlapping entries
            displayOurList();
            System.out.println("\t\t\t\t\t\tEnter item name followed by the quantity :-");
            String answer = sc.nextLine();
            String[] items = answer.split(" ");
            int quantity = Integer.parseInt(items[1]);
            String value = items[0];
            tempOrder.putAll(customerOrder); // Copy existing entries to the new HashMap
            tempOrder.put(value + "_" + quantity, value); // Add the new entry
            customerOrder = tempOrder; // Update the customerOrder reference
            System.out.println("\t\t\t\t\t\tYour cart elements are as follows:-");
            displayUserList();
            System.out.println("\t\t\t\t\t\tDo you want to add more items in your cart? ( Y or N )");
            reply = sc.next().charAt(0);
            addItemsRecursively(reply);
        } else if (reply == 'N' || reply == 'n') {
            System.out.println("\t\t\t\t\t\tOk. Printing your cart now.");
            displayUserList();
        } else {
            System.out.println("\t\t\t\t\t\tBad Choice !!");
        }
    }
}
    /*public void addAgain(char reply2) {
        if (reply2 == 'y' || reply2 == 'Y') {
            Scanner sc = new Scanner(System.in);
            //sc.nextLine();
            displayOurList();
            System.out.println("Enter item name followed by the quantity :-");
            String answer2 = sc.nextLine();
            String[] values2 = answer2.split(" ");
            int quantity2 = Integer.parseInt(values2[1]);
            String value2 = values2[0];
            this.addItemToUserList(quantity2, value2);
            System.out.println("Your cart elements are as follows:-");
            this.displayUserList();
            System.out.println("Do you want to add more items in your cart? ( Y or N )");
            char reply3 = sc.next().charAt(0);

            // Consume the newline character left in the buffer
            sc.nextLine();

            if (reply3 == 'Y'  || reply3 == 'y' ) {
                displayOurList();
                System.out.println("Enter item name followed by the quantity :-"); // shoes 2
                String answer3 = sc.nextLine(); //sc.next
                String[] values3 = answer3.split(" ");
                int quantity3 = Integer.parseInt(values3[1]);
                String items = values3[0];
                this.addItemToUserList(quantity2, items);
                System.out.println("Limit Reached !!");
                System.out.println("Items in your cart are:-");
                this.displayUserList();

            } else if (reply3 == 'N' || reply3 == 'n') {
                System.out.println("Ok. Printing your cart now.");
                this.displayUserList();
            } else {
                System.out.println("Bad Choice !!");
            }
            sc.close();
        } else {
            System.out.println("Invalid Choice BYE !!");
        }
    } */




