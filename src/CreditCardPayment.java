
class CreditCardPayment implements PaymentMethod {
    private final String cardNumber;
    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public void pay(double amount) {
        System.out.println("\t\t\t\t\t\tPaid $" + amount + " with credit card " + cardNumber);
    }
}