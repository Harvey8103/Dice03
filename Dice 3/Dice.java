import java.util.Random;

public class Dice {
    private int answer1;
    private int answer2;
    private int answer3;
    private int answer4;
    private Random random = new Random();
    private boolean correct = false;
    
    //roll the dices
    private void rollDices() {
        answer1 = random.nextInt(6) + 1;
        answer2 = random.nextInt(6) + 1;
        answer3 = random.nextInt(6) + 1;
        answer4 = random.nextInt(6) + 1;
    }
    
    public static void main(String[] args) {
        Dice game = new Dice();
        game.rollDices();
        
       

        System.out.println("Dice 1: " + game.answer1);
        System.out.println("Dice 2: " + game.answer2);    
        System.out.println("Dice 3: " + game.answer3);
        System.out.println("Dice 4: " + game.answer4);
    }
}
