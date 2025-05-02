package domain.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Cruiser extends Spaceship {
    public Cruiser(String name) {
        this.setName(name);
        this.setWeapons(new ArrayList<>());
    }

    @Override
    public void addWeapon(Weapon weapon){
        if (this.getWeapons().size() < 3) {
            this.getWeapons().add(weapon);
        }
        else {
            throw new IllegalStateException("Cruiser already has 3 weapons");
        }
    }
}
