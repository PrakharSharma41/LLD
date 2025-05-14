package com.example.interview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InterviewApplication {

	public static void main(String[] args) {
		int width=20,height=20;
		SnakeGame game=new SnakeGame(width, height);
		String[] input=new String[]{"RIGHT","RIGHT","RIGHT","UP","LEFT","LEFT","LEFT","LEFT","DOWN","RIGHT","UP"};
		for(int i=0;i<input.length;i++){
			String move=input[i];
			game.moveSnake(move);
			if(game.isGameOver()){
				System.out.println("game  is over at "+i);
				break;
			}
		}
		System.out.println("game finished");
	}

}
