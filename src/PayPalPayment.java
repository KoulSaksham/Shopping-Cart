class PayPalPayment implements PaymentMethod  {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    public void pay(double amount) {
        System.out.println("\t\t\t\t\t\tPaid $" + amount + " with PayPal using email " + email);
    }
}