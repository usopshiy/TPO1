package domain.characters;

import domain.enums.Race;
import domain.interfaces.Listener;
import domain.interfaces.Observable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Person implements Listener, Observable {
    private String name;
    private Race race;
}
