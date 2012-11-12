// The "Info" class.

public class Info extends Thread
{
    private int points = 0;// the game point that use get for playing 
    Info ()
    {
    }



    public void addNewPoints (int p)//the method that will add points 
    {
	if (p >= 0)
	    points += p;
	else if (p < 0)
	{
	    if (points < 100)
		points = 0;
	    else// if it is '-' then will lose 500 points 
		points -= 500;
	}
    }


    public int[] getCurPoints ()// return the point that for graphics to print
    {
	if (points < 0)
	    points = 0;
	String temp = "" + points;
	int t[] = new int [temp.length ()];
	for (int i = 0 ; i < temp.length () ; i++)
	    t [i] = Integer.parseInt ("" + temp.charAt (i));
	return t;// the number in 1 dig
    }
} // Info class
