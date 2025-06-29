package tictactoev1;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello and welcome to tic tac toe game!");
        TicTacToeGame ticTacToeGame = new TicTacToeGame();
        System.out.println("Game winner is :" + ticTacToeGame.playTicTacToeGame());
    }
}
