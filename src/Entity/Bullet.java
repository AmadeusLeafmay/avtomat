package Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bullet {
    private String type;
    private int damage;
    HashMap<Integer, String> types;
    public Bullet(){
        createTypesMap();
        randomTypeAndDamage();
    }
    private void createTypesMap(){
        this.types = new HashMap<>();
        types.put(0, "Regular");
        types.put(1, "Tracing");
        types.put(2, "Explosive");
    }
    private void randomTypeAndDamage(){
        int value = (int)(Math.random()*3);
        Function<Integer, String> randomType = x -> types.get(x);
        Supplier<Integer> randomDamage = () -> (int)((Math.random()*(value + 1) * 10));
        this.type = randomType.apply(value);
        this.damage = randomDamage.get();
    }

    public String getType(){
        return this.type;
    }

    public int getDamage() {
        return damage;
    }

    public static List<Bullet> createMagazine(int capacity){
        Supplier<Bullet> bulletGenerator = Bullet::new;
        return Stream.generate(bulletGenerator).limit(capacity).collect(Collectors.toList());
    }

    public static void fire(List<Bullet> magazine){
        Consumer<String> printer = System.out::println;
        Consumer<Bullet> bulletPrinter = x -> System.out.println("Type: " + x.getType() + "| Damage: " + x.getDamage());
        Map<String, List<Bullet>> bulletByType = magazine.stream().collect(Collectors.groupingBy(Bullet::getType));
        bulletByType.forEach((type, magazineGrouped) -> {
            printer.accept(type);
            magazineGrouped.forEach(bulletPrinter);
            printer.accept("");
        } );

    }
}
