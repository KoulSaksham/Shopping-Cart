import java.util.Scanner;

public class ShoppingCartHome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI staff = new UI();

        System.out.println();  // for skipping the first line of console
        System.out.print("\t\t\t\t\t\tEnter Your Name: ");
        String user = scanner.nextLine().toUpperCase();
        System.out.println();
        System.out.println("\t\t\t\t\t\tWelcome " + user + " to our Shopping Cart Project!");
        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        System.out.println("\t\t\t\t\t\tOur Inventory : ");
        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        staff.displayOurList();
        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");

        /// addition of items starts from here

        boolean inputIsValid = false;
        while (!inputIsValid) {
            System.out.print("\t\t\t\t\t\tPlease choose any ITEM name and specify Quantity to add in your cart: ");
            String answer = scanner.nextLine();
            String[] values = answer.split(" ");
            try {
                if (values.length != 2) {  //check for the input from user
                    throw new ArrayIndexOutOfBoundsException();
                }
                int quantity = Integer.parseInt(values[1]);
                String value = values[0];
                if (staff.checkItemName(value)) {
                    staff.addItemToUserList(quantity, value);
                    inputIsValid = true;
                } else
                    System.out.println("\t\t\t\t\t\t❌ Item Name entered is not correct !");
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("\t\t\t\t\t\t❌ Invalid input for quantity.");
            }
        }
        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        System.out.println("\t\t\t\t\t\tItem added successfully !!");
        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        System.out.print("\t\t\t\t\t\tDo you want to add more items in your cart? (Y or N): ");
        char UserReply = scanner.next().charAt(0);
        staff.addItemsRecursively(UserReply);
        ///  end of additions

        ///  deletion starts from here
        System.out.println("\t\t\t\t\t\tDo you wish to delete any items from your list? (Y or N): ");
        UserReply = scanner.next().charAt(0);
        if (UserReply == 'y' || UserReply == 'Y') {
            System.out.println("\t\t\t\t\t\tEnter item name:-");
            String deleteItem = scanner.next();
            System.out.println("\t\t\t\t\t\tEnter quantity of the item you want to delete:-");
            int deleteQuantity = scanner.nextInt();
            switch (deleteQuantity) {
                case 0 -> System.out.println("\t\t\t\t\t\t❌ Wrong quantity..");
                default -> staff.removeItemFromUserList(staff.customerOrder, deleteQuantity, deleteItem);
            }
        } else {
            System.out.println("\t\t\t\t\t\tOkay Printing the bill now.");
            System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        }  ///   deletion ends here

        ///    Billing starts from here
        CheckingOut obj = new CheckingOut();
        System.out.print("\t\t\t\t\t\tTotal bill of your items = $");
        System.out.printf("%.3f%n", obj.addTotal(staff.customerOrder));
        System.out.print("\t\t\t\t\t\tApplying the discounts...");
        System.out.println();
        System.out.print("\t\t\t\t\t\tAmount after discount = $");
        System.out.printf("%.3f%n", obj.discountCheck(staff.customerOrder));
        System.out.print("\t\t\t\t\t\tTotal Bill after taxes = $");
        System.out.printf("%.3f%n", obj.finalBill());
        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        ///    end of billing

        ///   Payment starts from here

        inputIsValid = false;
        while (!inputIsValid) {
            System.out.print("\t\t\t\t\t\tHow would you like to pay? (Card or Paypal): ");
            String payment = scanner.next();
            scanner.nextLine();
            if (payment.equalsIgnoreCase("card")) {
                System.out.print("\t\t\t\t\t\tEnter Card Number Without Spaces: ");
                String card = scanner.next();
                CreditCardPayment c1 = new CreditCardPayment(card);
                c1.pay(obj.finalBill());
                inputIsValid = true;
            } else if (payment.equalsIgnoreCase("paypal")) {
                System.out.print("\t\t\t\t\t\tEnter Email id: ");
                String email = scanner.next();
                PayPalPayment p1 = new PayPalPayment(email);
                p1.pay(obj.finalBill());
                inputIsValid = true;
            } else {
                System.out.println("\t\t\t\t\t\t❌ Wrong Choice. Try again !");
            }
        }

        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        System.out.println("\t\t\t\t\t\tThank you " + user + " for shopping with us !");
        System.out.println("\t\t\t\t\t\tHave a Great Day.");
        System.out.println("\t\t\t\t\t\t══════════════════════════════════════════════════");
        //// end of Payment
        //// end of Program
        scanner.close();
    }
}