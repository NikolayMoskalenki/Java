import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Toy ball = new Toy("Мяч");
        Toy bone = new Toy("Кость");

        List<Toy> bobsToys = new ArrayList<>();
        bobsToys.add(ball);
        bobsToys.add(bone);

        Dog myDog = new Dog("Шарик", 3, List.of(ball));
        Dog friendDog = new Dog("Бобик", 5, bobsToys);

        myDog.bark();
        friendDog.bark();

        System.out.println("У Бобика игрушек: " + friendDog.getToys().size());

        
        bobsToys.remove(0);

        System.out.println("После кражи у Бобика игрушек: " + friendDog.getToys().size());

        
        try {
            friendDog.getToys().add(new Toy("Мяч"));
        } catch (UnsupportedOperationException e) {
            System.out.println("Добавить игрушку через геттер нельзя: список защищён");
        }
    }
}
