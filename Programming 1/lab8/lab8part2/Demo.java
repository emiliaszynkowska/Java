import java.util.*;

public class Demo {

  ArrayList<Animal> animals = new ArrayList<Animal>();

  public Demo() {
    Animal dWolf1 = new Wolf("Luke", 6);
    animals.add(dWolf1);
    Animal dWolf2 = new Wolf("Alex", 3);
    animals.add(dWolf2);
    Animal dParrot1 = new Parrot("Sally", 8);
    animals.add(dParrot1);
  }

  public void order() {
    Collections.sort(animals);
    for(Animal animal : animals) {
      System.out.println(animal.getName());
    }
  }

}
