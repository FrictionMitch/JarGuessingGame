import java.util.Random;
import java.util.Scanner;

public class Game {
 
//  public static void main(String[] args) {
//    // Your code here
//    Game game = new Game();
//  }
//  
  private int mNumberOfGuesses;
    private boolean endGame;
    private String mUserName;
    private String mJarItem;
    private int mMaxItems;
    private int mTotalItems;
    private int mHighScore = 0;
    private int mGuess;
    private boolean correct;
    private String mScoreName;
  

    public String guessString;

    public Game() {

//        Scanner in = new Scanner(System.in);
//        System.out.printf("Please enter user name (\"Admin\" to customize game): %n");
//        mUserName = in.nextLine();
//        user();
        fill();
    }

    public void user() {
        if (mUserName.equalsIgnoreCase("Admin") || mUserName.equalsIgnoreCase("administrator")) {
            fill();
        } else {
            System.out.printf("Good luck, %s%n", mUserName);
            mJarItem ="taco";
            mMaxItems = 500;
            grammar();
            play();
        }
    }

    public void fill() {
        System.out.print("Enter the object name that will be filling the jar: ");
//      Console console = console(System.in);
        Scanner in = new Scanner(System.in);
        mJarItem = in.nextLine();
        grammar();
        System.out.printf("Please enter the maximum amount of %s: ", mJarItem.toUpperCase());
        mMaxItems = Integer.parseInt(in.nextLine());
        randomNumber(mMaxItems);
        play();
    }

    public void grammar() {
        if (mJarItem.toLowerCase().endsWith("y")) {
            //Not going to worry about words like mouse/mice
            // or singular words that end with s, etc.
            if(mJarItem.toLowerCase().endsWith("ay")|| mJarItem.endsWith("ey") ||
                    mJarItem.endsWith("iy") || mJarItem.endsWith("oy") || mJarItem.endsWith("uy")) {
                mJarItem += "s";
            } else {

                StringBuilder builder = new StringBuilder(mJarItem);
                builder.deleteCharAt(mJarItem.length() - 1);
                mJarItem = builder.toString() + "ies";
            }

        }
            if (mJarItem.toLowerCase().endsWith("s")) {
            } else {
            mJarItem += "s";
        }
    }

    public String pluralGuess(String string, Integer integer) {
        if (integer > 1) {
            string += "es";
        } else {
            return guessString = string;
        }
        return guessString = string;
    }

    public void title() {
        char[] caps = mScoreName.toCharArray();
        caps[0] = Character.toUpperCase(caps[0]);
        mScoreName = new String(caps);
    }

    public Integer randomNumber(Integer rand) {

        Random random = new Random();
        mTotalItems = random.nextInt(rand)+1;
        return mTotalItems;
    }

    public void play() {
        boolean gameOver = false;
        while (!gameOver) {
            mNumberOfGuesses = 0;
            System.out.printf("The jar is filled with %s%n%n", mJarItem.toUpperCase());
            System.out.printf("The range is between 1 and %d%n", mMaxItems);
            Scanner in = new Scanner(System.in);
            do {
                correct = false;
                System.out.printf("Guess how many %s are in the jar: %n", mJarItem);
                mGuess = in.nextInt();
                if (mGuess > mMaxItems) {
                    System.out.printf("Oops...the maximum amount of %s that could be in the jar is %d%n", mJarItem, mMaxItems);
                    System.out.printf("Try a mGuess that doesn't exceed %d%n", mMaxItems);
                    mGuess = in.nextInt();
                }
                mNumberOfGuesses++;
                if (mGuess > mTotalItems) {
                    System.out.printf("Sorry, %d is too high!%n", mGuess);
                }
                if (mGuess < mTotalItems) {
                    System.out.printf("Unfortunately %d is too low!%n", mGuess);
                }
                if (mGuess == mTotalItems) {
                    System.out.printf("Congrats!!! %d was the exact number%n", mGuess);
                    pluralGuess("mGuess", mNumberOfGuesses);
                    System.out.printf("It took you %d %s%n", mNumberOfGuesses, guessString);
                    if (mNumberOfGuesses == mHighScore) {
                        System.out.printf("You tied the high score but %s did it first%n", mScoreName);
                    } else
                    if (mHighScore == 0 || mHighScore > mNumberOfGuesses) {
                        mHighScore = mNumberOfGuesses;
                        System.out.printf("You have the new high score!...%n");
                        System.out.printf("Enter your name to be immortalized (until someone else defeats you!)%n");
                        Scanner n = new Scanner(System.in);
                        mScoreName = n.nextLine();
                        title();
                        System.out.printf("I doubt anyone will beat %s's score%n", mScoreName);
                    }
                    else {
                        System.out.printf("Good game, but %s has the high score of %d%n%n", mScoreName, mHighScore);
                    }

                    correct = true;
                    System.out.printf("Would you like to play again?%n (\"yes\" or \"no\")%n");
                    Scanner ta = new Scanner(System.in);
                    String tryAgain = ta.nextLine();
                    if (tryAgain.equalsIgnoreCase("yes") || tryAgain.equalsIgnoreCase("y")) {
                        randomNumber(mMaxItems);

//                        play();
                    } else {
                        gameOver = true;
//                        System.exit(0);
//                        mHighScore = 0;
                        fill();
                    }
                }
            } while (!correct);
        }
    }
}