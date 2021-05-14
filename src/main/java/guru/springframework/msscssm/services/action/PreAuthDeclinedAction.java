package guru.springframework.msscssm.services.action;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

@Component
public class PreAuthDeclinedAction {

    public Action<PaymentState, PaymentEvent> getAction() {
        return context -> System.out.println("PreAuth Declined!!!");
    }
}
