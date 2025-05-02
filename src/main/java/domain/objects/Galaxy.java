package domain.objects;

import domain.enums.Status;
import domain.interfaces.Targetable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Galaxy implements Targetable {
    private String name;
    private int population;
    private Status status;

    public Galaxy(String name, int population) {
        this.name = name;
        this.population = population;
        this.status = Status.FINE;
    }
}
