package domain.objects;

import domain.enums.Atmosphere;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Event {
    private String eventName;
    private Atmosphere atmosphere;
}
