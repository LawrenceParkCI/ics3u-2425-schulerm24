package idk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Black_jack {

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> deck;
        int[] playerMoney = {1000, 1000, 1000}; // Balances for 3 players

        System.out.println("Welcome to Multi-Player Blackjack!");
        System.out.print("Enter the number of players (1-3): ");
        int numPlayers = scanner.nextInt();

        if (numPlayers < 1 || numPlayers > 3) {
            System.out.println("Invalid number of players. Exiting...");
            return;
        }

        boolean keepPlaying = true;

        while (keepPlaying) {
            deck = initializeDeck();
            Collections.shuffle(deck);

            ArrayList<ArrayList<String>> playerHands = new ArrayList<>();
            for (int i = 0; i < numPlayers; i++) {
                playerHands.add(new ArrayList<>());
            }

            ArrayList<String> dealerHand = new ArrayList<>();
            dealerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));

            int[] bets = new int[numPlayers];

            // Get bets from each player
            for (int i = 0; i < numPlayers; i++) {
                if (playerMoney[i] > 0) {
                    System.out.println("\nPlayer " + (i + 1) + ", your current balance: $" + playerMoney[i]);
                    System.out.print("Enter your bet: $");
                    while (true) {
                        bets[i] = scanner.nextInt();
                        if (bets[i] > 0 && bets[i] <= playerMoney[i]) {
                            break;
                        } else {
                            System.out.print("Invalid bet. Enter an amount between $1 and $" + playerMoney[i] + ": ");
                        }
                    }
                } else {
                    System.out.println("\nPlayer " + (i + 1) + " is out of money and cannot play this round.");
                }
            }

            // Deal initial cards to players
            for (int i = 0; i < numPlayers; i++) {
                if (playerMoney[i] > 0) {
                    playerHands.get(i).add(deck.remove(0));
                    playerHands.get(i).add(deck.remove(0));
                }
            }

            // Players' turns
            for (int i = 0; i < numPlayers; i++) {
                if (playerMoney[i] > 0) {
                    System.out.println("\nPlayer " + (i + 1) + "'s turn.");
                    System.out.println("Your hand: " + playerHands.get(i) + " (Total: " + calculateHandValue(playerHands.get(i)) + ")");
                    System.out.println("Dealer's face-up card: " + dealerHand.get(0));

                    boolean playerTurn = true;
                    while (playerTurn) {
                        System.out.print("Do you want to 'hit' or 'stand'? ");
                        String choice = scanner.next().toLowerCase();

                        if (choice.equals("hit")) {
                            playerHands.get(i).add(deck.remove(0));
                            System.out.println("You drew a " + playerHands.get(i).get(playerHands.get(i).size() - 1));
                            int playerTotal = calculateHandValue(playerHands.get(i));
                            System.out.println("Your hand: " + playerHands.get(i) + " (Total: " + playerTotal + ")");
                            if (playerTotal > 21) {
                                System.out.println("You busted!");
                                playerTurn = false;
                            }
                        } else if (choice.equals("stand")) {
                            playerTurn = false;
                        } else {
                            System.out.println("Invalid choice. Please type 'hit' or 'stand'.");
                        }
                    }
                }
            }

            // Dealer's turn
            System.out.println("\nDealer's turn.");
            System.out.println("Dealer's hand: " + dealerHand + " (Total: " + calculateHandValue(dealerHand) + ")");
            while (calculateHandValue(dealerHand) < 17) {
                Thread.sleep(1000);
                dealerHand.add(deck.remove(0));
                System.out.println("Dealer draws a " + dealerHand.get(dealerHand.size() - 1));
            }
            int dealerTotal = calculateHandValue(dealerHand);
            System.out.println("Dealer's final hand: " + dealerHand + " (Total: " + dealerTotal + ")");

            // Determine results for each player
            for (int i = 0; i < numPlayers; i++) {
                if (playerMoney[i] > 0) {
                    String result = determineWinner(playerHands.get(i), dealerHand);
                    if (result.equals("win")) {
                        playerMoney[i] += bets[i];
                        System.out.println("Player " + (i + 1) + " wins! New balance: $" + playerMoney[i]);
                    } else if (result.equals("loss")) {
                        playerMoney[i] -= bets[i];
                        System.out.println("Player " + (i + 1) + " loses. New balance: $" + playerMoney[i]);
                    } else {
                        System.out.println("Player " + (i + 1) + " ties. Balance remains: $" + playerMoney[i]);
                    }
                }
            }

            // Check if players want to continue
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                keepPlaying = false;
            }
        }

        System.out.println("Thank you for playing! Final balances:");
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": $" + playerMoney[i]);
        }
        scanner.close();
    }

    // Initialize the deck
    public static ArrayList<String> initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        ArrayList<String> deck = new ArrayList<>();
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(rank + " of " + suit);
            }
        }
        return deck;
    }

    // Calculate the value of a hand
    public static int calculateHandValue(ArrayList<String> hand) {
        int value = 0;
        int aces = 0;

        for (String card : hand) {
            String rank = card.split(" ")[0];
            if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
                value += 10;
            } else if (rank.equals("Ace")) {
                value += 11;
                aces++;
            } else {
                value += Integer.parseInt(rank);
            }
        }

        // Adjust for aces
        while (value > 21 && aces > 0) {
            value -= 10;
            aces--;
        }

        return value;
    }

    // Determine the winner
    public static String determineWinner(ArrayList<String> playerHand, ArrayList<String> dealerHand) {
        int playerTotal = calculateHandValue(playerHand);
        int dealerTotal = calculateHandValue(dealerHand);

        if (playerTotal > 21) {
            return "loss";
        } else if (dealerTotal > 21 || playerTotal > dealerTotal) {
            return "win";
        } else if (playerTotal < dealerTotal) {
            return "loss";
        } else {
            return "tie";
        }
    }
}
