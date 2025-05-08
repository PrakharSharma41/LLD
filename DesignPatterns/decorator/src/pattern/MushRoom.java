package pattern;

public class MushRoom extends ToppingDecorator{
    BasePizza pizza;

    public MushRoom(BasePizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public int cost() {
        return this.pizza.cost()+10;
    }
    
}
