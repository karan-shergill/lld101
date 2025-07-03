package tictactoev2;

import tictactoev2.model.GameBoard;
import tictactoev2.model.PlayingPiece;
import tictactoev2.model.player.Player;
import tictactoev2.model.player.PlayerFactory;
import tictactoev2.model.player.PlayerType;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class TicTacToeGame implements BoardGames {
    Deque<Player> players;
    GameBoard gameBoard;
    private Scanner inputScanner;

    public TicTacToeGame() {
        initiateGame();
    }

    @Override
    public void initiateGame() {
        System.out.println("Welcome To Tic Tac Toe Game");
        System.out.println("You can be play in two modes: \n 1) Play with another human \n 2) Play with AI ");
        System.out.println("Choose option 1 or option 2");

        inputScanner = new Scanner(System.in);
        int gameMode = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume the remaining newline

        // to keep players obj who will be playing the game
        players = new LinkedList<>();

        switch (gameMode) {
            case 1 -> {
                getTwoPlayers(players);
            }
            case 2 -> {
                getOnePlayer(players);
            }
        }

        int size = getBoardSizeFromPlayer();
        gameBoard = new GameBoard(size);
    }

    private int getBoardSizeFromPlayer() {
        System.out.println("Enter Tic Tac Toe Game Board Size");
        int size = inputScanner.nextInt();
        inputScanner.nextLine(); // Consume the remaining newline
        return size;
    }

    @Override
    public void playGame() {
        boolean gameInProgress = true;
        while (gameInProgress) {
            if (!gameBoard.hasEmptyCell()) {
                // No cell is empty
                break;
            }

            Player currPlayer = players.removeFirst();
            int[] rowAndCol = getInputForCurrPlayerTurn(currPlayer);

            if (gameBoard.isValidMove(rowAndCol[0], rowAndCol[1])) {
                currPlayer.makeMove(gameBoard, rowAndCol[0], rowAndCol[1]);
                players.addLast(currPlayer);
            } else {
                System.out.println("Player " + currPlayer.getPlayerName() + ", your move was not valid, TRY AGAIN!");
                players.addFirst(currPlayer);
                continue;
            }

            if (checkIfCurrPlayerWonTheGame(rowAndCol[0], rowAndCol[1], currPlayer.getPlayerPlayingPiece())) {
                System.out.println(currPlayer.getPlayerName() + " Congratulations! You WON the game.");
                gameInProgress = false;
                break;
            }
        }
        if (gameInProgress) {
            System.out.println("No Winner! Game TIE");
        }
    }
    
    // Close the scanner when the game is completely finished
    public void cleanup() {
        if (inputScanner != null) {
            inputScanner.close();
        }
    }

    private int[] getInputForCurrPlayerTurn(Player currPlayer) {
        while (true) {
            try {
                gameBoard.printGameBoard();
                System.out.println(currPlayer.getPlayerName() + ", please enter row & col(R,C) for your move");
                String userInput = inputScanner.nextLine();
                String[] rowAndCol = userInput.split(",");

                if (rowAndCol.length != 2) {
                    System.out.println(currPlayer.getPlayerName() + ", please provide only two index separated by a comma(R,C)");
                    continue;
                }

                int row = Integer.parseInt(rowAndCol[0]);
                int col = Integer.parseInt(rowAndCol[1]);
                return new int[]{row, col};
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter numbers only!");
            }
        }
    }

    // Helper method to validate and get playing piece input
    private PlayingPiece getValidPlayingPiece(String playerName) {
        while (true) {
            System.out.println("Enter " + playerName + " playing symbol: X or O");
            String input = inputScanner.nextLine().trim().toUpperCase();

            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter X or O.");
                continue;
            }

            if (input.equals("X")) {
                return PlayingPiece.X;
            } else if (input.equals("O")) {
                return PlayingPiece.O;
            } else {
                System.out.println("Invalid input: '" + input + "'. Please enter only X or O.");
            }
        }
    }

    private void getOnePlayer(Deque<Player> players) {
        System.out.println("Enter player 1 name:");
        String player1Name = inputScanner.nextLine();
        PlayingPiece player1Piece = getValidPlayingPiece(player1Name);

        System.out.println("Enter AI player 2 name of your choice:");
        String player2Name = inputScanner.nextLine();
        PlayingPiece player2Piece = player1Piece.equals(PlayingPiece.X) ? PlayingPiece.O : PlayingPiece.X;

        players.add(PlayerFactory.createPlayer(PlayerType.HUMAN, player1Name, player1Piece));
        players.add(PlayerFactory.createPlayer(PlayerType.AI, player2Name, player2Piece));
    }

    private void getTwoPlayers(Deque<Player> players) {
        System.out.println("Enter player 1 name:");
        String player1Name = inputScanner.nextLine();
        PlayingPiece player1Piece = getValidPlayingPiece(player1Name);

        System.out.println("Enter player 2 name:");
        String player2Name = inputScanner.nextLine();
        PlayingPiece player2Piece = player1Piece.equals(PlayingPiece.X) ? PlayingPiece.O : PlayingPiece.X;

        players.add(PlayerFactory.createPlayer(PlayerType.HUMAN, player1Name, player1Piece));
        players.add(PlayerFactory.createPlayer(PlayerType.HUMAN, player2Name, player2Piece));
    }

    private boolean checkIfCurrPlayerWonTheGame(int row, int col, PlayingPiece playingPiece) {
        boolean rowCheck = true;
        boolean columnCheck = true;
        boolean diagonalCheck = true;
        boolean antiDiagonalCheck = true;

        for (int i=0; i<gameBoard.getSize(); i++) {
            if (gameBoard.getBoard()[row][i] != playingPiece) {
                rowCheck = false;
                break;
            }
        }

        for (int i=0; i<gameBoard.getSize(); i++) {
            if (gameBoard.getBoard()[i][col] != playingPiece) {
                columnCheck = false;
                break;
            }
        }

        for (int i=0, j=0; i<gameBoard.getSize(); i++, j++) {
            if (gameBoard.getBoard()[i][j] != playingPiece) {
                diagonalCheck = false;
                break;
            }
        }

        for (int i=0, j=gameBoard.getSize()-1; i<gameBoard.getSize(); i++, j--) {
            if (gameBoard.getBoard()[i][j] != playingPiece) {
                antiDiagonalCheck = false;
                break;
            }
        }

        return rowCheck || columnCheck || diagonalCheck || antiDiagonalCheck;
    }
}
