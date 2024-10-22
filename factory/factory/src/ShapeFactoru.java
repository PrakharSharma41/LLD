public class ShapeFactoru {
    Shape getShape(String input){
        switch (input) {
            case "CIRCLE":
                return new Circle();

            case "RECTANGLE":
                return new Circle();
        }
        return new Circle();
    }
}
