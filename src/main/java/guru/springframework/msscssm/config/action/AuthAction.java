package guru.springframework.msscssm.config.action;

import guru.springframework.msscssm.domain.PaymentEvent;
import guru.springframework.msscssm.domain.PaymentState;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Random;

import static guru.springframework.msscssm.domain.PaymentEvent.AUTH_APPROVED;
import static guru.springframework.msscssm.domain.PaymentEvent.AUTH_DECLINED;
import static guru.springframework.msscssm.services.PaymentServiceImpl.PAYMENT_ID_HEADER;

@Component
public class AuthAction implements Action<PaymentState, PaymentEvent> {

    @Override
    public void execute(StateContext<PaymentState, PaymentEvent> context) {
        System.out.println("Authorization was called!!!");

        if (new Random().nextInt(10) < 8) {
            System.out.println("Auth approved");
            context.getStateMachine()
                    .sendEvent(MessageBuilder.withPayload(AUTH_APPROVED)
                            .setHeader(PAYMENT_ID_HEADER, context.getMessageHeader(PAYMENT_ID_HEADER))
                            .build());
        } else {
            System.out.println("Auth declined! No credit!!!");
            context.getStateMachine()
                    .sendEvent(MessageBuilder.withPayload(AUTH_DECLINED)
                            .setHeader(PAYMENT_ID_HEADER, context.getMessageHeader(PAYMENT_ID_HEADER))
                            .build());
        }
    }
}
