import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

// Interface para metodos de pagamento
interface PaymentMethod {
    boolean validate();
    void pay(double amount);
}

class CreditCard implements PaymentMethod {
    @Override
    public boolean validate(){
        System.out.println("Validando cartão de crédito");
        return true;
    }

    @Override
    public void pay(double amount){
        System.out.println("Pagando " + amount + " com cartão de crédito");
    }
}

class PayPal implements PaymentMethod {
    @Override
    public boolean validate(){
        System.out.println("Validando conta PayPal");
        return true;
    }

    @Override
    public void pay(double amount){
        System.out.println("Pagando " + amount + " com PayPal");
    }
}

class Pix implements PaymentMethod {
    @Override
    public boolean validate() {
        System.out.println("Validando chave Pix...");
        return true;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Pagamento de R$" + amount + " realizado via Pix.");
    }
}

class PaymentFactory {
    private static final Map<String, Supplier<PaymentMethod>> paymentMethods = new HashMap<>();

    static {
        paymentMethods.put("creditcard", CreditCard::new);
        paymentMethods.put("paypal", PayPal::new);
        paymentMethods.put("pix", Pix::new);
    }

    public static PaymentMethod createPaymentMethod(String type) {
        Supplier<PaymentMethod> payment = paymentMethods.get(type.toLowerCase());
        if (payment != null) {
            return payment.get();
        }
        throw new IllegalArgumentException("Método de pagamento não suportado: " + type);
    }
}

public class PaymentSystem {
    public static void main(String[] args) {
        PaymentMethod creditCard = PaymentFactory.createPaymentMethod("creditcard");
        creditCard.validate();
        creditCard.pay(100.00);

        PaymentMethod paypal = PaymentFactory.createPaymentMethod("paypal");
        paypal.validate();
        paypal.pay(50.00);

        PaymentMethod pix = PaymentFactory.createPaymentMethod("pix");
        pix.validate();
        pix.pay(30.00);
    }
}
