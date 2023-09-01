
class CreditCardPayment implements PaymentMethod {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void pay(double amount) {
        System.out.printf("\t\t\t\t\t\tPaid $" + "%.3f", amount);
        System.out.print(" with credit card number " + cardNumber + ".");
        System.out.println();
    }
}