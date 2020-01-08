import java.util.ArrayList;

/**
 * Creates a blank score card for player to use
 * @author  Brian Myers
 * @version 12-16-19
 */
public class Ycard
{
    private ArrayList<Integer> scores = new ArrayList<Integer>();
    private ArrayList<Boolean> canScore = new ArrayList<Boolean>();
    private boolean bonus;
    
    //lots of setters, in the order of boolean declaration
    public Ycard()
    {
        for (int i = 0; i<14; i++) {scores.add(0);}
        for (int i = 0; i<14; i++) {canScore.add(true);}   
        
        bonus = false;
    }
    
    public void setBonus() {bonus = true;}
    
    public boolean getBonus() {return bonus;}
    
    
    /**
     * Check to see if you can add to a specific score
     * @param i - the index of list
     * @return a boolean of if you can return it
     */
    public boolean canAddToScore(int i)
    {
        return canScore.get(i);
    }
    
    public void setScorable(int i)
    {
        canScore.set(i,false);
    }
    
    public void addToScore(int i, int s)
    {
        scores.set(i,s);
    }
    
    /**
     *Get the value at a specific point in the ArrayList scores
     *@param i - index of list
     */
    public int getScore(int i)
    {
        return scores.get(i);
    }

    //return total
    public int getTotal()
    {
        int tot = 0;
        int ttot = 0;
        for (int i : scores) {tot += i;}
        
        //top section
        for (int i = 0; i< 6; i++)
        {
            ttot += scores.get(i);
        }
        
        if (ttot >= 63) {tot += 35;} 
        if (bonus) {tot += 100;}
        
        return tot;
    }
}
