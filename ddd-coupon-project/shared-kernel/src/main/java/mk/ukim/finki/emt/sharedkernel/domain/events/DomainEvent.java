package mk.ukim.finki.emt.sharedkernel.domain.events;

import java.time.Instant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class DomainEvent {

    private String topic;
    private Instant occurredOn;

    public DomainEvent(String topic){
        this.topic = topic;
        this.occurredOn = Instant.now();
    }

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        String output = null;
        try{
            output = objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException ignored){

        }
        return output;
    }

    public String topic(){
        return topic;
    }

    public static <E extends DomainEvent> E fromJson(String json, Class<E> eventClass) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json,eventClass);
    }

}
