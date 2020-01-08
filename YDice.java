import java.util.Random;

/**
 * Create a dice for the Yahtzee game
 * @author  Brian Myers
 * @version 12-16-19
 */
public class YDice
{
    public int roll()
    {
        Random gen = new Random();
        return gen.nextInt(6)+1;
    }
}
