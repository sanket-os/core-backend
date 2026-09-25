package designPatterns;

public class PaymentService {

    private final AppLogger logger;


    public PaymentService(AppLogger logger) {
        /*
         * PaymentService gets the SAME Logger instance.
         */
        this.logger = logger;
    }

    public void processPayment(double amount) {
        logger.info("Processing payment: Rs." + amount);
        // Imagine payment processing here...
        logger.info("Payment completed successfully");
    }
}
