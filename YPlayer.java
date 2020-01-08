import java.util.ArrayList;
import java.util.Collections;

/**
 * Creates a player to keep track of dice and such
 * @author  Brian Myers
 * @version 12-16-19
 */
public class YPlayer
{
    private YDice dice = new YDice();
    private ArrayList<Integer> handOfDice = new ArrayList<Integer>();
    private ArrayList<Integer> keptDice = new ArrayList<Integer>();
    private int score;
    private Ycard card = new Ycard();
    private String name;
    
    /**
     * Create a blank player
     */
    public YPlayer()
    {
        score = 0;
        name = "";
    }
    
    /**
     * Set name
     * @param n - name of player
     */
    public void setName(String n) {name = n;}
    
    /**
     * Get name
     * @return the name
     */
    public String getName() {return name;}
    
    /**
     * Show what the player has rolled
     * @return What the player has rolled
     */
    public void seeRolled()
    {
        String hand = name + " has: ";
        
        for (int i : handOfDice) {hand += i + " ";}
        System.out.println(hand);
    }
    
    /**
     * Show what the player has kept
     * @return A string of what the player has kept
     */
    public void seeSaved()
    {
        String hand = name + " has kept: ";
        
        for (int i : keptDice) {hand += i + " ";}
        System.out.println(hand);
    }
    
    /**
     * Return how many dice the play has saved
     * @return the number of saved dice
     */
    public int getSaved()
    {
        return keptDice.size();
    }
    
    /**
     * Keep a die that is selected, given its in the list of rolled.
     * @param d - the number to keep
     */
    public void keepDie(int d)
    {   
        for (int i = 0; i<handOfDice.size();i++)
        {
            if (handOfDice.get(i) == d)
            {
                keptDice.add(handOfDice.get(i));
                handOfDice.remove(i);
                break;
            }
        }
    }
    
    /**
     * Remove a die that is sleected
     * @param d - the number to put back
     */
    public void putBack(int d)
    {   
        for (int i = 0; i<keptDice.size();i++)
        {
            if (keptDice.get(i) == d)
            {
                handOfDice.add(keptDice.get(i));
                keptDice.remove(i);
                break;
            }
        }
    }
    
    /**
     * Roll dice x times
     * @param t - times to roll
     */
    public void rollDice(int t)
    {
        handOfDice.clear();
        for (int i = 0; i<t; i++)
        {
            handOfDice.add(dice.roll());
        }
    }
    
    //Start adding scores
    //-----------------------------------------------------------------------
    public void addToOnes()
    {
        int tot = 0;
        for (int i : keptDice) {if (i == 1) {tot += 1;}}
        for (int j : handOfDice) {if (j == 1) {tot += 1;}}
        
        handOfDice.clear();
        keptDice.clear();
        card.addToScore(0,tot);
        card.setScorable(0);
    }

    
    public void addToTwos()
    {
        int tot = 0;
        for (int i : keptDice) {if (i == 2) {tot += 2;}}
        for (int j : handOfDice) {if (j == 2) {tot += 2;}}
        
        handOfDice.clear();
        keptDice.clear();
        card.addToScore(1,tot);
        card.setScorable(1);
    }
    
    public void addToThrees()
    {
        int tot = 0;
        for (int i : keptDice) {if (i == 3) {tot += 3;}}
        for (int j : handOfDice) {if (j == 3) {tot += 3;}}
        
        handOfDice.clear();
        keptDice.clear();
        card.addToScore(2,tot);
        card.setScorable(2);
    }
    
    public void addToFours()
    {
        int tot = 0;
        for (int i : keptDice) {if (i == 4) {tot += 4;}}
        for (int j : handOfDice) {if (j == 4) {tot += 4;}}
        
        handOfDice.clear();
        keptDice.clear();
        card.addToScore(3,tot);
        card.setScorable(3);
    }
    
    public void addToFives()
    {
        int tot = 0;
        for (int i : keptDice) {if (i == 5) {tot += 5;}}
        for (int j : handOfDice) {if (j == 5) {tot += 5;}}
        
        handOfDice.clear();
        keptDice.clear();
        card.addToScore(4,tot);
        card.setScorable(4);
    }
    
    public void addToSixes()
    {
        int tot = 0;
        for (int i : keptDice) {if (i == 6) {tot += 6;}}
        for (int j : handOfDice) {if (j == 6) {tot += 6;}}
        
        handOfDice.clear();
        keptDice.clear();
        card.addToScore(5,tot);
        card.setScorable(5);
    }
    
    public void addToThreeKind()
    {
        int score = 0;
        
        ArrayList<Integer> countsOfDice = new ArrayList<Integer>();
        ArrayList<Integer> numberOfDice = new ArrayList<Integer>();
        
        for (int i : handOfDice) {keptDice.add(i);}
        
        
        for (int i : keptDice)
        {
            if (numberOfDice.contains(i))
            {
                int index = numberOfDice.indexOf(i);
                countsOfDice.set(index, countsOfDice.get(index)+1);
            }
            else 
            {
                numberOfDice.add(i);
                countsOfDice.add(1);
            }
        }
        
        boolean t = false;
        for (int i : countsOfDice)
        {
            if (i >= 3) {t = true;}
        }
        
        for (int i : keptDice) {score += i;}

        if (t) {card.addToScore(6, score);}
        handOfDice.clear();
        keptDice.clear();
    }
    
    public void addToFourKind()
    {
        int score = 0;
        
        ArrayList<Integer> countsOfDice = new ArrayList<Integer>();
        ArrayList<Integer> numberOfDice = new ArrayList<Integer>();
        
        for (int i : handOfDice) {keptDice.add(i);}
        
        
        for (int i : keptDice)
        {
            if (numberOfDice.contains(i))
            {
                int index = numberOfDice.indexOf(i);
                countsOfDice.set(index, countsOfDice.get(index)+1);
            }
            else 
            {
                numberOfDice.add(i);
                countsOfDice.add(1);
            }
        }
        
        boolean t = false;
        for (int i : countsOfDice)
        {
            if (i >= 4) {t = true;}
        }
        
        for (int i : keptDice) {score += i;}
        
        if (t) {card.addToScore(7, score);}
        handOfDice.clear();
        keptDice.clear();
    }
    
    public void addToChance()
    {
        
        int score = 0;
        
        for (int i : keptDice){score += i;}
        for (int j: handOfDice) {score += j;}
        
        card.addToScore(12,score);
        handOfDice.clear();
        keptDice.clear();
    }
    
    public void addToSmStraight()
    {
        for (int i : handOfDice) {keptDice.add(i);}
        ArrayList<Integer> seenDice = new ArrayList<Integer>();
        
        //put the first item of kept dice in seen dice
        seenDice.add(keptDice.get(0));
        keptDice.remove(0);
        
        for (int i : keptDice)
        {
            boolean seen = false;
            for (int j : seenDice)
            {
                if (i == j)
                {
                    seen = true;
                }
            }
            

            if (!seen) {
                seenDice.add(i);
            }
        }
        
        // ---- check the list of kept dice ----
        boolean tf = true;
        boolean tb = true;
        
        //sort list
        Collections.sort(seenDice);
        
        //loop forwards
        for (int i = 0; i<3; i++)
        {
            if (seenDice.get(i+1)-1 != seenDice.get(i))
            {
                tf = false;
            }
        }
        
        Collections.reverse(seenDice);
        
        //loop backwards
        for (int i = 0; i<3; i++)
        {
            if (seenDice.get(i+1)+1 != seenDice.get(i))
            {
                tb = false;
            }
        }
        
        if (tf || tb) {card.addToScore(9,30);}
        
        keptDice.clear();
        handOfDice.clear();
    }
    
    public void addToLgStraight()
    {
        boolean t = true;
        
        for (int j : handOfDice) {keptDice.add(j);}
        //sort list
        Collections.sort(keptDice);
        
        
        for (int i = 0; i<4; i++)
        {
            if (keptDice.get(i+1)-1 != keptDice.get(i))
            {
                t = false;
            }
        }
        
        if (t) {card.addToScore(10, 40);}
        
        keptDice.clear();
        handOfDice.clear();
    }
    
    public void addToFullHouse()
    {
        for (int i : handOfDice) {keptDice.add(i);}
        Collections.sort(keptDice);
        
        int n1 = keptDice.get(0);
        int n2 = keptDice.get(3);
        
        int n1tot = 0;
        int n2tot = 0;        
        
        for (int i : keptDice)
        {
            if (i == n1) {n1tot++;}
            else if (i == n2) {n2tot++;}
        }
        
        if ((n1tot == 2 && n2tot == 3) || (n1tot == 3 && n2tot == 2))
        {
            keptDice.clear();
            handOfDice.clear();
            card.addToScore(8,25);
        }
    }

    public void addToYahtzee()
    {
        for (int i : handOfDice) {keptDice.add(i);}
        
        //create number to see what the yahtzee
        int num = keptDice.get(0), tot = 0;
        
        for (int i : keptDice)
        {
           if (i == num)
           {
               tot += 1;
           }
        }
        
        if (tot == 5) 
           {
               card.addToScore(11,50);
           }
        handOfDice.clear();
        keptDice.clear();
    }
    
    public void addToBonus()
    {
        for (int i : handOfDice) {keptDice.add(i);}
        
        int num = keptDice.get(0), tot = 0;
        
        for (int i : keptDice)
        {
           if (i == num)
           {
               tot += 1;
           }
        }
        
        for (int j : handOfDice)
        {
           if (j == num)
           {
               tot += 1;
           } 
        }
        
        if (tot >= 5) {card.setBonus();}
        
        handOfDice.clear();
        keptDice.clear();
    }
    
    /**
     * Get the options available to put in the card
     */
     public void getCardOptions()
    {
        String out = "Available options are to add points to are: ";
        
        if (card.canAddToScore(0)) {out += "\n 1 --- Ones";}
        if (card.canAddToScore(1)) {out += "\n 2 --- Twos";}
        if (card.canAddToScore(2)) {out += "\n 3 --- Threes";}
        if (card.canAddToScore(3)) {out += "\n 4 --- Fours";}
        if (card.canAddToScore(4)) {out += "\n 5 --- Fives";}
        if (card.canAddToScore(5)) {out += "\n 6 --- Sixes";}
        
        if (card.canAddToScore(6)) {out += "\n 7 --- Three of a Kind";}
        if (card.canAddToScore(7)) {out += "\n 8 --- Four of a Kind";}
        if (card.canAddToScore(8)) {out += "\n 9 --- Full House";}
        if (card.canAddToScore(9)) {out += "\n 10 -- Small Straight";}
        if (card.canAddToScore(10)) {out += "\n 11 -- Large Straight";}
        
        if (card.canAddToScore(11)) {out += "\n 12 -- Yahtzee!";}
        if (card.canAddToScore(12)) {out += "\n 13 -- Chance";}
        if (card.getScore(11) == 50) {out += "\n 14 -- Bonus for Yahtzee";}
        
        
        out+="\n 0 --- Return to the main screen";
        
        System.out.println(out);
    }
    
    /**
     * Change state of card scorable
     * @param i - index of element
     */
    public void changeScorable(int i)
    {
        card.setScorable(i);
    }
    
    public boolean checkScorable(int i)
    {
        return card.canAddToScore(i);
    }
    
    /**
     * Find the value to put on the score card
     *@param i - index of card spot
     *@return what to put on the card
     */
    public String spotOnCard(int i)
    {
        if(card.canAddToScore(i))
        {
            return "--";
        }
        else {return Integer.toString(card.getScore(i));}
        
    }

    public boolean hasBonus() {return card.getBonus();}
    
    public void setPBonus() {card.setBonus();}
    //Get total score
    //-----------------------------------------------------------------------
    
    public int getTotalScore() {return card.getTotal();}
}