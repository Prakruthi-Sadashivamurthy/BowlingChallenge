import java.util.Scanner;

public class BowlingGame {
    private int[] rolls;
    private int currentRoll;

    public BowlingGame() {
        rolls = new int[21]; 
        currentRoll = 0;
    }

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int getScore() {
        int score = 0;
        int i = 0;

        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(i)) {
                score += 10 + strikeBonus(i);
                i++;
            } else if (isSpare(i)) {
                score += 10 + spareBonus(i);
                i += 2;
            } else {
                score += rolls[i] + rolls[i + 1];
                i += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int i) {
        return rolls[i] == 10;
    }

    private boolean isSpare(int i) {
        return rolls[i] + rolls[i + 1] == 10;
    }

    private int strikeBonus(int i) {
        return rolls[i + 1] + rolls[i + 2];
    }

    private int spareBonus(int i) {
        return rolls[i + 2];
    }

    public static void main(String[] args) {
        BowlingGame game = new BowlingGame();
        Scanner scanner = new Scanner(System.in);

        for (int frame = 1; frame <= 9; frame++) {
            System.out.println("Frame " + frame);
            System.out.print("Enter the number of pins knocked down in roll 1: ");
            int roll1 = scanner.nextInt();
            game.roll(roll1);

            if (roll1 == 10) {
                System.out.println("Strike!");
                continue;
            }

            System.out.print("Enter the number of pins knocked down in roll 2: ");
            int roll2 = scanner.nextInt();
            game.roll(roll2);

            if (roll1 + roll2 == 10) {
                System.out.println("Spare!");
            }
        }

        System.out.println("Frame 10");
        System.out.print("Enter the number of pins knocked down in roll 1: ");
        int roll1 = scanner.nextInt();
        game.roll(roll1);

        if (roll1 == 10) {
            System.out.println("Strike!");
            System.out.print("Enter the number of pins knocked down in extra roll 1: ");
            int extraRoll1 = scanner.nextInt();
            game.roll(extraRoll1);

            if (extraRoll1 == 10) {
                System.out.print("Enter the number of pins knocked down in extra roll 2: ");
                int extraRoll2 = scanner.nextInt();
                game.roll(extraRoll2);
            }
        } else {
            System.out.print("Enter the number of pins knocked down in roll 2: ");
            int roll2 = scanner.nextInt();
            game.roll(roll2);

            if (roll1 + roll2 == 10) {
                System.out.println("Spare!");
                System.out.print("Enter the number of pins knocked down in extra roll 1: ");
                int extraRoll1 = scanner.nextInt();
                game.roll(extraRoll1);
            }
        }

        int totalScore = game.getScore();
        System.out.println("Total Score: " + totalScore);
        scanner.close();
    }
}