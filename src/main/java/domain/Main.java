package domain;

import domain.characters.Leader;
import domain.enums.Atmosphere;
import domain.enums.Race;
import domain.objects.*;

public class Main {
    public static void main(String[] args) {
        Galaxy farFarGalaxy = new Galaxy("SomeGalaxy", 100000);
        Galaxy milkyWay = new Galaxy("MilkyWay", 1000000);

        Leader vluhr = new Leader("leader1", Race.VLHURG);
        Leader guvunt = new Leader("leader2", Race.GGUGVUNTT);
        Fleet vluhrFleet = new Fleet("Vluhr armada", farFarGalaxy);
        Fleet duvuntFleet = new Fleet("Duvunt corp", farFarGalaxy);

        ElectricWeapon mainCaliber = new ElectricWeapon("BigPEwPew", 10000);
        Cruiser cruiser1 = new Cruiser("Vluhr destiny");
        Cruiser cruiser2 = new Cruiser("Duvunt dominance");
        cruiser1.addWeapon(mainCaliber);
        vluhrFleet.setCurrentTarget(duvuntFleet);
        vluhrFleet.getComposition().add(cruiser1);
        duvuntFleet.getComposition().add(cruiser2);

        Meeting lastOne = new Meeting("Meeting of representatives", Atmosphere.SILENCE);
        lastOne.getAttendees().add(vluhr);
        lastOne.getAttendees().add(guvunt);

        Shorts fancy = new Shorts("GUCCI shorts", "black, covered in diamonds shorts", 22);
        vluhr.getClothes().add(fancy);

        vluhr.attach(vluhrFleet);
        vluhr.attach(lastOne);

        guvunt.getObservers().add(vluhr);

        guvunt.say("А вот у меня, кажется, большие проблемы с образом жизни");
        vluhrFleet.unite(duvuntFleet);
        vluhrFleet.setCurrentTarget(milkyWay);
    }
}
