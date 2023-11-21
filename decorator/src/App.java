import pattern.BasePizza;
import pattern.ExtraCheese;
import pattern.Farmhouse;
import pattern.VegDelight;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        BasePizza pizza = new ExtraCheese(new VegDelight());
        System.out.println(pizza.cost());
    }
}
