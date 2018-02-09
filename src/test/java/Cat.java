import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cat extends Animal implements Meowable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Cat is running");
    }

    public static void main(String[] args) {

        Cat cat = new Cat();
        cat.doStuffs();

        HashMap<String, Cat> cats = new HashMap<String, Cat>();
        cats.put("Bob", new Cat());
        cats.put("Tom", new Cat());
        cats.put("Joe", new Cat());
        cats.put("Steve", new Cat());
        cats.put("Mill", new Cat());
        cats.put("Sue", new Cat());
        cats.put("Boris", new Cat());
        cats.put("Michael", new Cat());
        cats.put("Cate", new Cat());
        cats.put("Samantha", new Cat());

        Iterator<Map.Entry<String, Cat>> entries = cats.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Cat> entry = entries.next();
            System.out.println(entry.getKey());
        }

        System.out.println();

        /// l'ambda ///
        cats.forEach((key, value) -> {
            System.out.println(key);
            System.out.println(value);
        });

    }

    void eat() {
        System.out.println("Cat is eating");
    }

    void sleep() {
        System.out.println("Cat is sleeping");
    }

    public void meow() {
        System.out.println("Cat says meow");
    }

    public void doStuffs() {
        run();
        eat();
        sleep();
        meow();
    }

}
