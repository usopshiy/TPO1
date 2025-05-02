package domain.characters;

import domain.enums.Race;
import domain.interfaces.Listener;
import domain.interfaces.Observable;
import domain.objects.Clothes;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Person implements Listener, Observable {
    private String name;
    private Race race;
    private ArrayList<Clothes> clothes = new ArrayList<>();
}
