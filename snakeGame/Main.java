import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int width = 5;
        int height = 5;
        List<int[]> food = Arrays.asList(
            new int[]{1, 2},
            new int[]{0, 1},
            new int[]{2, 2}
        );

        SnakeGame game = new SnakeGame(width, height, food);

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter directions (U/D/L/R), type 'exit' to quit.");
        try{
            while (true) {
                System.out.print("Move: ");
                String input = br.readLine().toUpperCase();
                if (input.equals("EXIT")) {
                    break;
                }
    
                try {
                    int score = game.move(input);
                    if (score == -1) {
                        System.out.println("Game Over!");
                        break;
                    } else {
                        System.out.println("Current Score: " + score);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid direction. Please enter U, D, L, or R.");
                }
            }
        }catch(Exception e){

        }


        System.out.println("Thanks for playing!");        
    }
}
