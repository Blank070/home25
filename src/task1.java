import java.util.Random;
import java.util.Scanner;
public class task1 {
    //co
    private static User user;
    private static Computer computer;
    private static int userScore;
    private static int computerScore;
    private static int numberOfGames;

    private enum Move {
        ROCK, PAPER, SCISSORS;
        public int compareMoves(Move otherMove) {
            if (this == otherMove)
                return 0;
            switch (this) {
                case ROCK:
                    return (otherMove == SCISSORS ? 1 : -1);
                case PAPER:
                    return (otherMove == ROCK ? 1 : -1);
                case SCISSORS:
                    return (otherMove == PAPER ? 1 : -1);
                }
            return 0;
        }
    }

    private class User {
        private Scanner inputScanner;

        public User() {
            inputScanner = new Scanner(System.in);
        }

        public Move getMove() {
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            char firstLetter = userInput.charAt(0);
            if (firstLetter == 'К' || firstLetter == 'Н' || firstLetter == 'Б') {
                switch (firstLetter) {
                    case 'К':
                        return Move.ROCK;
                    case 'Н':
                        return Move.PAPER;
                    case 'Б':
                        return Move.SCISSORS;
                }
            }
            return getMove();
        }


        public boolean playAgain() {
            System.out.println("Хотите сыграть еще раз? ");
            System.out.println("Если да, то напишите букву Д");
            System.out.println("Если хотите выйте, то нажмите любую кнопку");
            String userInput = inputScanner.nextLine();
            userInput = userInput.toUpperCase();
            return userInput.charAt(0) == 'Д';
        }
    }

    private class Computer {
        public Move getMove() {
            Move[] moves = Move.values();
            Random random = new Random();
            int index = random.nextInt(moves.length);
            return moves[index];
        }

    }

    public task1() {
        user = new User();
        computer = new Computer();
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
    }

    public static void startGame() {
        System.out.println("КАМЕНЬ, НОЖНИЦЫ, БУМАГА!");
        System.out.println("Камень - К, Ножницы - Н, Бумага - Б ");
        Move userMove = user.getMove();
        Move computerMove = computer.getMove();
        System.out.println("\nВаш ход " + userMove + ".");
        System.out.println("Ход компьютера " + computerMove + ".\n");
        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
            case 0:
                System.out.println("Draw!");
                break;
            case 1:
                System.out.println(userMove + " beats " + computerMove + ". Вы победили!");
                userScore++;
                break;
            case -1:
                System.out.println(computerMove + " beats " + userMove + ". Вы проиграли.");
                computerScore++;
                break;
        }
        numberOfGames++;

        if (user.playAgain()) {
            startGame();
        } else {
            printGameStats();
        }
    }
    private static void printGameStats() {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + ((double) ties) / 2) / numberOfGames;

        System.out.print("+");
        printDashes(68);
        System.out.println("+");

        System.out.printf("|  %6s  |  %6s  |  %6s  |  %12s  |  %14s  |\n",
                "WINS", "LOSSES", "DRAWS", "GAMES PLAYED", "PERCENTAGE WON");

        System.out.print("|");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(10);
        System.out.print("+");
        printDashes(16);
        System.out.print("+");
        printDashes(18);
        System.out.println("|");

        System.out.printf("|  %6d  |  %6d  |  %6d  |  %12d  |  %13.2f%%  |\n",
                wins, losses, ties, numberOfGames, percentageWon * 100);

        System.out.print("+");
        printDashes(68);
        System.out.println("+");
    }

    private static void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }

    public static void main(String[] args) {
        task1 game = new task1();
        game.startGame();
    }
}