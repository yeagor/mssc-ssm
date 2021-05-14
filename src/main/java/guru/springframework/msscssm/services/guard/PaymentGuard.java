package guru.springframework.msscssm.services.guard;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import org.springframework.statemachine.guard.Guard;
import org.springframework.stereotype.Component;

import static guru.springframework.msscssm.services.PaymentServiceImpl.PAYMENT_ID_HEADER;

@Component
public class PaymentGuard {

    public Guard<PaymentState, PaymentEvent> getGuard() {
        return context -> context.getMessageHeader(PAYMENT_ID_HEADER) != null;
    }
}
