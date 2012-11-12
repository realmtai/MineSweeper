// The "Core" class.
/*
coder: yoongxin Dai
update Date: jan 19
descrpition: on the grad number 9 is bomb; 

*/
public class Core
{
    private boolean gg = false;// is the game is ended 
    private int numBomb, empty;// number of bomb and number of bomb that is left 
    private int[] [] grad = new int [10] [10];// the game grad
    private int num = 1;// number of bumbs

    Core (int lvl)
    {
	num = getRandomNumAtLvl (lvl);// convert lvl to number of bombs
	if (num == 0)// if theres no bomb make the number of bomb to 1 
	    num = 1;
	loadBomb (num);// load bomb according the number of bomb
	empty = 100 - num;// set number of sqr of non-bonb
	loadGameSet ();// start the game set
    }


    private int getRandomNumAtLvl (int lvl)// set lvl for the game 
    {
	int temp = 0;
	if (lvl == 0)
	    temp = (int) (Math.random () * 5);
	else if (lvl == 1)
	    while (temp < 5)
		temp = (int) (Math.random () * 10);
	else
	    while (temp <= 10)
		temp = (int) (Math.random () * 15);
	return temp;
    }


    private void loadBomb (int num)// load bombs on the grad 
    {
	int x, y;
	for (int i = 0 ; i < num ; i++)
	{
	    x = (int) (Math.random () * 10);
	    y = (int) (Math.random () * 10);
	    if (grad [x] [y] != 9)
		grad [x] [y] = 9;
	    else
		i--;
	}
    }


    private void loadGameSet ()// load the number around the bombs on the grad
    {
	int temp = 0;
	for (int i = 0 ; i < 10 ; i++)
	{
	    for (int ii = 0 ; ii < 10 ; ii++)
		if (grad [i] [ii] != 9)//if the cell is bomb, go around the bomb and give numbers 
		{
		    for (int a = -1 ; a <= 1 ; a++)
		    {
			for (int aa = -1 ; aa <= 1 ; aa++)
			    try
			    {
				if (grad [i + a] [ii + aa] == 9)
				    temp++;
			    }
			catch (ArrayIndexOutOfBoundsException e)
			{
			}
		    }
		    grad [i] [ii] = temp++;
		    temp = 0;
		}
	}
    }


    public int isBombAt (int x, int y)// check if theres bomb, if the grad doesnt exit then give -1 as flag
    {
	try
	{
	    return grad [x] [y];
	}
	catch (ArrayIndexOutOfBoundsException e)
	{
	    return -1;
	}
    }



    public void clickOnEmpty ()
    {
	empty--;// decrease the number of non-bomb cell
    }


    public int getEmptySpaceLeft ()
    {
	return empty;// give how many non-bomb cell 
    }


    public int getNumOfBomb ()
    {
	return num;// give number of bomb 
    }


    public int[] [] returnGard ()
    {
	return grad;// return the game grad
    }
} // Core class


