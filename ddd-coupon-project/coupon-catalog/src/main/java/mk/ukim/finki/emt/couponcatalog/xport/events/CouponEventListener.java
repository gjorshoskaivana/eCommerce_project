package mk.ukim.finki.emt.couponcatalog.xport.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.couponcatalog.domain.models.CouponId;
import mk.ukim.finki.emt.couponcatalog.services.CouponService;
import mk.ukim.finki.emt.sharedkernel.domain.config.TopicHolder;
import mk.ukim.finki.emt.sharedkernel.domain.events.DomainEvent;
import mk.ukim.finki.emt.sharedkernel.domain.events.orders.OrderItemCreated;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CouponEventListener {

    private final CouponService couponService;

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "couponCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage){
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
            couponService.orderItemCreated(CouponId.of(event.getCouponId()), event.getQuantity());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "couponCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage){
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage, OrderItemCreated.class);
            couponService.orderItemRemoved(CouponId.of(event.getCouponId()), event.getQuantity());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
