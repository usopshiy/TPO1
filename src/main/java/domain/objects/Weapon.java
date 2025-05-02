package domain.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Weapon {
    private String name;
    private int power;
}
