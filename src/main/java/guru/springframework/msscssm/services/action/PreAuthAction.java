package guru.springframework.msscssm.services.action;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

import static guru.springframework.msscssm.domain.PaymentEvent.PRE_AUTH_APPROVED;
import static guru.springframework.msscssm.domain.PaymentEvent.PRE_AUTH_DECLINED;
import static guru.springframework.msscssm.services.PaymentServiceImpl.PAYMENT_ID_HEADER;

@Component
public class PreAuthAction {

    public Action<PaymentState, PaymentEvent> getAction() {
        return context -> {
            System.out.println("PreAuth was called!!!");

            if (new Random().nextInt(10) < 8) {
                System.out.println("PreAuth Approved");
                context.getStateMachine()
                        .sendEvent(MessageBuilder.withPayload(PRE_AUTH_APPROVED)
                                .setHeader(PAYMENT_ID_HEADER, context.getMessageHeader(PAYMENT_ID_HEADER))
                                .build());
            } else {
                System.out.println("PreAuth Declined! No credit!!!");
                context.getStateMachine()
                        .sendEvent(MessageBuilder.withPayload(PRE_AUTH_DECLINED)
                                .setHeader(PAYMENT_ID_HEADER, context.getMessageHeader(PAYMENT_ID_HEADER))
                                .build());
            }
        };
    }
}
