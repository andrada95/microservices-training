package net.safedata.microservices.training.order.adapters;

import net.safedata.microservices.training.order.channels.OutboundChannels;
import net.safedata.microservices.training.order.events.OrderCreatedEvent;
import net.safedata.microservices.training.order.marker.OutboundAdapter;
import net.safedata.microservices.training.order.ports.MessagingOutboundPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

@Component
@EnableBinding(OutboundChannels.class)
public class MessageProducer implements MessagingOutboundPort, OutboundAdapter {

    private final OutboundChannels outboundChannels;

    @Autowired
    public MessageProducer(final OutboundChannels outboundChannels) {
        this.outboundChannels = outboundChannels;
    }

    public void publishEvent(final OrderCreatedEvent orderCreatedEvent) {
        outboundChannels.orders()
                        .send(createMessage(orderCreatedEvent));
    }

    private Message<?> createMessage(final OrderCreatedEvent orderCreatedEvent) {
        return MessageBuilder.withPayload(orderCreatedEvent)
                             .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                             .build();
    }
}
