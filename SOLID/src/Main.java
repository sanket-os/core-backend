//Let's use a small example: notifications.

//Suppose we start with this:

public class NotificationService {

    public void send(
            String type,
            String message
    ) {

        if (type.equals("EMAIL")) {
            System.out.println(
                    "Sending email: " + message
            );
        }

        if (type.equals("SMS")) {
            System.out.println(
                    "Sending SMS: " + message
            );
        }
    }
}

//Let's see how SOLID can improve it.

//Step 1: OCP

//Create an extension point:

public interface NotificationSender {

    void send(String message);
}

//Implementations:

public class EmailNotificationSender
        implements NotificationSender {

    @Override
    public void send(String message) {
        System.out.println(
                "Sending EMAIL: " + message
        );
    }
}
public class SmsNotificationSender
        implements NotificationSender {

    @Override
    public void send(String message) {
        System.out.println(
                "Sending SMS: " + message
        );
    }
}

//Now adding WhatsApp doesn't require modifying existing senders:

public class WhatsAppNotificationSender
        implements NotificationSender {

    @Override
    public void send(String message) {
        System.out.println(
                "Sending WhatsApp message: " + message
        );
    }
}

//OCP: Extend with new implementations.

//Step 2: DIP
//
//Create a service that depends on the abstraction:

public class NotificationService {

    private final NotificationSender sender;

    public NotificationService(
            NotificationSender sender
    ) {
        this.sender = sender;
    }

    public void notify(String message) {
        sender.send(message);
    }
}

//DIP: The service depends on NotificationSender, not directly on email or SMS.


//Step 3: LSP
//
//Every implementation should honor this contract:

void send(String message)

//So:

NotificationSender sender =
        new EmailNotificationSender();

//can be replaced with:

NotificationSender sender =
        new SmsNotificationSender();

//without breaking NotificationService.
//
//        LSP: Implementations should be safely substitutable.


//Step 4: ISP
//
//Suppose we had initially created:

interface NotificationSystem {

    void sendEmail();
    void sendSms();
    void sendPushNotification();
    void generateReport();
}

//That would force every implementation to deal with unrelated methods.

//Instead, keep focused interfaces:

interface NotificationSender {
    void send(String message);
}

//ISP: Don't force clients to depend on methods they don't need.

//Step 5: SRP
//
//Don't put everything into NotificationService.
//
//For example:
//
//NotificationService
//        ↓
//Coordinates notification logic
//
//        EmailNotificationSender
//        ↓
//Knows how to send email
//
//        SmsNotificationSender
//        ↓
//Knows how to send SMS
//
//        NotificationRepository
//        ↓
//Stores notification records
//
//Each class has a clearer reason to change.
//
//SRP: Separate responsibilities that change for different reasons.



//The five principles in one picture
//S — Single Responsibility
//    "Does this class have too many reasons to change?"
//
//O — Open/Closed
//    "Can I add a new variation without constantly modifying old code?"
//
//L — Liskov Substitution
//    "Can implementations genuinely replace each other?"
//
//I — Interface Segregation
//    "Am I forcing clients to depend on methods they don't need?"
//
//D — Dependency Inversion
//    "Does my important business logic depend on abstractions rather than details?"


//How they are connected
//
//The interesting thing is that SOLID principles are not completely separate.
//
//They often support each other.
//
//Small focused responsibilities
//            ↓
//SRP
//            ↓
//Small focused interfaces
//            ↓
//ISP
//            ↓
//Implementations can satisfy contracts properly
//            ↓
//LSP
//            ↓
//New implementations can be added
//            ↓
//OCP
//            ↓
//High-level code depends on those contracts
//            ↓
//DIP
//
//So a typical modern design may look like:

//        ┌───────────────────────┐
//        │   PaymentService      │
//        │                       │
//        │ depends on interface  │
//        └───────────┬───────────┘
//        │
//        ▼
//        ┌───────────────────────┐
//        │ PaymentProcessor      │
//        │      interface        │
//        └───────────┬───────────┘
//        │
//        ┌───────────┴───────────┐
//        ▼                       ▼
//        ┌────────────────┐      ┌────────────────┐
//        │ UpiPayment     │      │ CardPayment    │
//        └────────────────┘      └────────────────┘
//
//

// This gives us:
//
//SRP → Each class has a focused job.
//OCP → Add new payment methods through new implementations.
//LSP → Any valid PaymentProcessor implementation can be used.
//ISP → The interface contains only relevant payment operations.
//DIP → PaymentService depends on PaymentProcessor, not UPI/Card implementations.





// DIP and Dependency Injection
//
//These two are related, but they are not the same thing.
//
//Dependency Inversion Principle
//
//A design principle:
//
//Depend on abstractions
//rather than concrete implementations
//Dependency Injection
//
//A technique for providing dependencies from outside.
//
//For example:
//
//public OrderService(OrderRepository repository) {
//    this.repository = repository;
//}
//
//This is constructor injection.
//
//The dependency is injected:
//
//OrderRepository repository =
//        new MySqlOrderRepository();
//
//OrderService service =
//        new OrderService(repository);
//
//So:
//
//DIP = Design principle
//DI  = Technique
//
//Dependency Injection is often used to help achieve Dependency Inversion.
//
//Frameworks such as Spring can manage this automatically, but you should understand the plain Java version first—which you're doing here.


























