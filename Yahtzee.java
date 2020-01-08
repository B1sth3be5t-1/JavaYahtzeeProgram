import java.util.Random;
import java.util.Scanner;

/**
 * Play the final game of Yahtzee
 * @author  Brian Myers
 * @version 12-18-19
 */
public class Yahtzee
{
    private static YPlayer p1 = new YPlayer();
    private static YPlayer p2 = new YPlayer();
    private static Scanner in = new Scanner(System.in);
    
    /**
     * Play the game of Yahtzee
     */
    public static void playGame()
    {
        displayTitle();
        System.out.println();
        
        System.out.print("What is your name? ");
        p1.setName(in.nextLine());
        
        System.out.println();
        System.out.print("What is your name? ");
        p2.setName(in.nextLine());
        
        setPlayerOrder();
        System.out.println('\u000C');
        for (int t = 0; t<13; t++)
        {
            //p1 turn
            System.out.println(p1.getName() + ", it is your turn.");
            playerTurn(p1);
            
            System.out.println('\u000C');
            
            //newline
            System.out.println();
            
            //p2 turn
            System.out.println(p2.getName() + ", it is your turn.");
            playerTurn(p2);
            
            //clears
            System.out.println('\u000C');
        }
        
        getResults();
        
        displayCard();
    }
    
    /**
     * Get the winning results
     */
    private static void getResults()
    {
        System.out.println();
        System.out.println(p1.getName() + " has a total of " + p1.getTotalScore() + ".");
        System.out.println(p2.getName() + " has a total of " + p2.getTotalScore() + ".");
        System.out.println();
        if (p1.getTotalScore() > p2.getTotalScore()) 
        {
            System.out.println(p1.getName() + " won!");
        }
        else {System.out.println(p2.getName() + " won!");}
    }
    
    /**
     * Get the player choice, what to add to in the score card
     * @param p - the player
     */
    private static int pChoice(YPlayer p) 
    {
        p.getCardOptions();
        System.out.print("Which choice would you like? ");
        int choice = 0;
        do
        {
            choice = in.nextInt();
        } while (choice < 0 || choice > 14);
        
        if (choice == 1 && p.checkScorable(0)) {p.addToOnes();}
        else if (choice == 2 && p.checkScorable(1)) {p.addToTwos();}
        else if (choice == 3 && p.checkScorable(2)) {p.addToThrees();}
        else if (choice == 4 && p.checkScorable(3)) {p.addToFours();}
        else if (choice == 5 && p.checkScorable(4)) {p.addToFives();}
        else if (choice == 6 && p.checkScorable(5)) {p.addToSixes();}
        else if (choice == 7 && p.checkScorable(6)) {p.addToThreeKind();}
        else if (choice == 8 && p.checkScorable(7)) {p.addToFourKind();} 
        else if (choice == 9 && p.checkScorable(8)) {p.addToFullHouse();}
        else if (choice == 10 && p.checkScorable(9)) {p.addToSmStraight();}
        else if (choice == 11 && p.checkScorable(10)) {p.addToLgStraight();}
        else if (choice == 12 && p.checkScorable(11)) 
        {
            p.addToYahtzee();
        }
        //bonus
        else if (choice == 14) {p.addToBonus();}
        else if (choice == 13 && p.checkScorable(12)) {p.addToChance();}
        else if (choice == 0) {return 0;}
        else 
        {
            System.out.println('\u000C');
            System.out.println("Please pick an item in the list!");
            
            p.seeSaved();
            System.out.println();
            
            pChoice(p);
            
        }
        p.changeScorable(choice-1);

        return -1;
    }

    /**
     * Complete one player turn
     * @param p - the player to modify
     */  
        private static void playerTurn(YPlayer p)
    {
        int mChoice = 0;
        int rolls = 0;
        boolean cont = true;
        
        p.rollDice(5 - p.getSaved());
        p.seeRolled();
        p.seeSaved();
        rolls++;
        System.out.println();
        do
        {   
            
            displayGameMenu();
           
            System.out.println();
            
            int rollsLeft =  3 - rolls;

            System.out.println("You have " + rollsLeft + " roll(s) left.");
            System.out.println();
            displayCard(); 
            mChoice = in.nextInt();
            
            
            if (mChoice == 7 && rolls < 3) 
            {
                System.out.println('\u000C');
                p.rollDice(5 - p.getSaved());
                p.seeRolled();
                p.seeSaved();
                rolls++;
            }
            
            
            else if (mChoice == 7 && rolls >= 3)
            {
                System.out.println('\u000C');
                System.out.println("Sorry! You have already had 3 rolls.");
                p.seeRolled();
                p.seeSaved();
            }
            
            
            else if (mChoice == 8) 
            {
                System.out.println('\u000C');
                p.seeRolled();
                p.seeSaved();
                
                System.out.println();
                int back = -1;
                while (back != 0)
                {
                    System.out.println('\u000C');
                    p.seeRolled();
                    p.seeSaved();
                
                    System.out.print("Enter a number to put back (0 to exit): ");
                    back = in.nextInt();
                    p.putBack(back);                    
                }
                
                System.out.println('\u000C');
                p.seeRolled();
                p.seeSaved();
            }
            
            else if (mChoice == 9)
            {
                System.out.println('\u000C');
                p.seeRolled();
                p.seeSaved();
                
                cont = false;
                System.out.println();
                if (pChoice(p) == 0)
                {
                    System.out.println('\u000C');
                    cont = true;
                    
                    p.seeRolled();
                    p.seeSaved();
                }
            }
            
             else if (mChoice >=1 && mChoice <= 6) 
            {
                p.keepDie(mChoice);
                
                System.out.println('\u000C');
                p.seeRolled();
                p.seeSaved();
            }
            
            else 
            {
                System.out.println('\u000C');
                p.seeRolled();
                p.seeSaved();
                System.out.println("Please pick an item in the list!"); 
            }
            
        } while (mChoice != 9 || cont);
    }

    /**
     * Sets the player order
     */
    private static void setPlayerOrder()
    {
        Random gen = new Random();
        if (gen.nextInt(2)+1 == 1) 
        {
            YPlayer temp = p1;
            p1 = p2;
            p2 = temp;
        }
    }
    
    /**
     *Displays options for adding to score card
     @param p - the player to find the score card
     */
    private static void displayCardOptions(YPlayer p)
    {
        p.getCardOptions();
    }
    
    /**
     * Displays the title of each turn for a player
     */ 
    private static void displayGameMenu()
    {
        System.out.println();
        System.out.println("Enter a number 1-6 to save the die");
        System.out.println("------------------------------------");
        System.out.println("Enter 7 to roll again");
        System.out.println("Enter 8 to put a number back");
        System.out.println("Enter 9 to add to score card");
    }
    
    /**
     * Displays ascii art title of Yahtzee
     * Source code done by me
     */
    private static void displayTitle()
    {
        System.out.println();
        System.out.println(" -----------      __ __  _____  __ __  ______  _____  ______  ______    ----------- ");
        System.out.println("| \u2022       \u2022 |    |  |  ||  _  ||  |  ||_    _||__   ||  ____||  ____|  | \u2022       \u2022 |");
        System.out.println("|           |     \\_   ||     ||     |  |  |    /  / |  ____||  ____|  |           |");
        System.out.println("| \u2022       \u2022 |      _|__||__|__||__|__|__|__|___/  /__|______||______|  | \u2022       \u2022 |");
        System.out.println("|           |     /   ________________________/  /__________________|  |           |");
        System.out.println("| \u2022       \u2022 |    |  /  |                     |______________________|  | \u2022       \u2022 |");
        System.out.println(" -----------      \\___/                                                 -----------");
    }

    private static void displayCard()
    {
        System.out.println("|Category:|\t " + p1.getName()+"\t " + p2.getName());
        System.out.println("----------------------");
        System.out.println("|Ones     |\t " + p1.spotOnCard(0) + "\t " + p2.spotOnCard(0));
        System.out.println("|Twos     |\t " + p1.spotOnCard(1) + "\t " + p2.spotOnCard(1));
        System.out.println("|Threes   |\t " + p1.spotOnCard(2) + "\t " + p2.spotOnCard(2));
        System.out.println("|Fours    |\t " + p1.spotOnCard(3) + "\t " + p2.spotOnCard(3));
        System.out.println("|Fives    |\t " + p1.spotOnCard(4) + "\t " + p2.spotOnCard(4));
        System.out.println("|Sixes    |\t " + p1.spotOnCard(5) + "\t " + p2.spotOnCard(5));
        System.out.println("|3Kind    |\t " + p1.spotOnCard(6) + "\t " + p2.spotOnCard(6));
        System.out.println("|4Kind    |\t " + p1.spotOnCard(7) + "\t " + p2.spotOnCard(7));
        System.out.println("|Full Hs  |\t " + p1.spotOnCard(8) + "\t " + p2.spotOnCard(8));
        System.out.println("|Sm Str.  |\t " + p1.spotOnCard(9) + "\t " + p2.spotOnCard(9));
        System.out.println("|Lg Str.  |\t " + p1.spotOnCard(10) + "\t " + p2.spotOnCard(10));
        System.out.println("|Yahtzee  |\t " + p1.spotOnCard(11) + "\t " + p2.spotOnCard(11));
        System.out.println("|Chance   |\t " + p1.spotOnCard(12) + "\t " + p2.spotOnCard(12));
        System.out.println("|Total    |\t " + p1.getTotalScore() + "\t " + p2.getTotalScore());

    }
}