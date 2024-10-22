package pattern;

public class ColoredCar {
    BaseCar car;

    public ColoredCar(BaseCar car) {
        this.car = car;
    }
    public int cost(){
        return car.cost()+20;
    }
}
