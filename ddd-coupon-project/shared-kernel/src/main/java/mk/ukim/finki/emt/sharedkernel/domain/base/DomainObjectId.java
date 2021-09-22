package mk.ukim.finki.emt.sharedkernel.domain.base;

//import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.springframework.lang.NonNull;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@Embeddable
@Getter
public class DomainObjectId implements Serializable {

    private String id;


    protected DomainObjectId(@NonNull String uuid){
        this.id = Objects.requireNonNull(uuid, "uuid must not be null");
    }

    // generate random instance of a given class
    @NonNull
    public static <ID extends DomainObjectId> ID randomId(@NonNull Class<ID> idClass){
        Objects.requireNonNull(idClass, "idClass must not be null");
        try {
            return idClass.getConstructor(String.class).newInstance(UUID.randomUUID().toString());
        }catch (Exception e){
            throw new RuntimeException("Cound not create new instance of " + idClass, e);
        }
    }
}
