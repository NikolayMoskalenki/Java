import java.util.List;

public final class Dog { 
    private final String name;
    private final int age;
    private final List<Toy> toys;

    Dog(String name, int age, List<Toy> toys) {
        this.name = name;
        this.age = age;
        this.toys = List.copyOf(toys); 
    }

    List<Toy> getToys() {
        return List.copyOf(toys); 
    }

    int getAge() {
        return age;
    }

    String getName() {
        return name;
    }

    void bark() {
        System.out.println(name + " говорит: Гав!");
    }
}
