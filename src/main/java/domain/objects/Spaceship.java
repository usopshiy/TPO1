package domain.objects;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Spaceship {
    private String name;
    private ArrayList<Weapon> weapons;

    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }
}
