class PayPalPayment implements PaymentMethod {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public void pay(double amount) {
        System.out.printf("\t\t\t\t\t\tPaid $" + "%.3f", amount);
        System.out.println(" with PayPal using email " + email + ".");
        System.out.println();
    }
}