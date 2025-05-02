package domain.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shorts extends Clothes {
    private double length;

    public Shorts(String name, String desc, double length) {
        this.length = length;
        this.setName(name);
        this.setDescription(desc);
    }
}
