package domain.objects;

import domain.enums.Status;
import domain.interfaces.Observer;
import domain.interfaces.Targetable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class Fleet implements Targetable, Observer {
    private Targetable currentTarget;
    private Galaxy currentGalaxy;
    private String name;
    private ArrayList<Spaceship> composition;

    public void attack(Targetable target) {}

    public void unite(Fleet fleet) {
        this.composition.addAll(fleet.getComposition());
        this.name = "united fleet of" + fleet.getName() + " and " + name;
    }

    public Fleet (String name, Galaxy galaxy) {
        this.name = name;
        this.currentGalaxy = galaxy;
        this.composition = new ArrayList<>();
    }

    @Override
    public void update() {
        attack(currentTarget);
        this.currentGalaxy.setStatus(Status.IN_RUINS);
    }
}
