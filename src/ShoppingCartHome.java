import java.util.Scanner;
public class ShoppingCartHome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UI customer = new UI();

        System.out.println();
        System.out.print("\t\t\t\t\t\tEnter Your Name: ");
        String user = scanner.nextLine().toUpperCase();
        System.out.println("\t\t\t\t\t\tWelcome " + user + " to our Shopping Cart Project!");

        System.out.println("\t\t\t\t\t\tWe have these items in our inventory:");
        customer.displayOurList();

        boolean inputIsValid = false;

        while (!inputIsValid) {
            System.out.print("\t\t\t\t\t\tPlease choose any ITEM name and specify Quantity to add in your cart: ");
            String answer = scanner.nextLine();
            String[] values = answer.split(" ");
            try {
                if (values.length != 2) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                int quantity = Integer.parseInt(values[1]);
                String value = values[0];
                customer.addItemToUserList(quantity, value);
                inputIsValid = true; // Valid input, exit the loop
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("\t\t\t\t\t\tInvalid input for quantity. Please enter a valid quantity.");
            }
        }

        System.out.println("\t\t\t\t\t\tYour cart elements are as follows:");
        customer.displayUserList();

        System.out.print("\t\t\t\t\t\tDo you want to add more items in your cart? (Y or N): ");
        char UserReply = scanner.next().charAt(0);
        customer.addItemsRecursively(UserReply);

        //Billing starts from here
        CheckingOut obj = new CheckingOut();

        System.out.print("\t\t\t\t\t\tTotal bill of your items = $");
        System.out.printf("%.3f%n", obj.addTotal(customer.customerOrder));
        System.out.print("\t\t\t\t\t\tApplying the discounts...");
        System.out.println();
        System.out.print("\t\t\t\t\t\tAmount after discount = $");
        System.out.printf("%.3f%n",obj.discountCheck(customer.customerOrder));
        System.out.print("\t\t\t\t\t\tTotal Bill after taxes = $");
        System.out.printf("%.3f%n", obj.finalBill());
        //end of billing

        //Payment starts from here
        System.out.print("\t\t\t\t\t\tHow would you like to pay? (Card or Paypal): ");
        String payment = scanner.next();
        scanner.nextLine();
        if (payment.equalsIgnoreCase("card")) {
            System.out.print("\t\t\t\t\t\tEnter Card Number Without Spaces: ");
            String card = scanner.next();
            CreditCardPayment c1 = new CreditCardPayment(card);
            c1.pay(obj.finalBill()); // Adjust payment logic as needed
        } else if (payment.equalsIgnoreCase("paypal")) {
            System.out.print("\t\t\t\t\t\tEnter Email id: ");
            String email = scanner.next();
            PayPalPayment p1 = new PayPalPayment(email);
            p1.pay(obj.finalBill()); // Adjust payment logic as needed
        } else {
            System.out.println("\t\t\t\t\t\tWrong Choice");
        }
        //end of Payment
        //end of Program
        scanner.close();
    }
}
