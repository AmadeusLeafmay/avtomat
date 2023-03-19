package Entity;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Bullet {
    private final String type;
    private final int damage;
    public Bullet(){
        this.type = randomType();
        this.damage = randomDamage();
    }
    private String randomType(){
        int value = (int)(Math.random()*3);
        if(value == 0){
            return "Regular";
        } else if (value == 1){
            return "Tracing";
        } else {
            return "Explosive";
        }
    }
    private int randomDamage(){
        if(this.type.equals("Regular")){
            return (int)(Math.random()*10);
        } else if (this.type.equals("Tracing")) {
            return (int)(Math.random()*20);
        } else {
            return (int)(Math.random()*30);
        }
    }
    public String getType(){
        return this.type;
    }

    @Override
    public String toString() {
        return "Type: " + this.type + "| Damage: " + this.damage;
    }

    public static List<Bullet> createMagazine(int capacity){
        List<Bullet> magazine = Stream.generate(Bullet::new).limit(capacity).collect(Collectors.toList());
        return magazine;
    }

    public static void fire(List<Bullet> magazine){
        Map<String, List<Bullet>> bulletByType = magazine.stream().collect(Collectors.groupingBy(Bullet::getType));
        bulletByType.forEach((type, magazineGrouped) -> {
            System.out.println(type);
            magazineGrouped.forEach((System.out::println));
            System.out.println();
        } );

    }
}
