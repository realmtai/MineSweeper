// The "New" class.
/*
creater: Yongxin Dai
update Data: Jan 19
descrpition: this is the finial project for computer secince 4 year. this program will copy the same rule as the winodws
game Minesweeper. it has the same rule as the game in winodws Minesweeper. except the flaging.
future notics: this will be the v1 java Minesweeper theres will be v2 and more in the future .
Coding and graphics are all done by yongxin Dai
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class New extends JPanel
{
    private static New n = new New (); // create the object for the program that able to run Frames.
    private Core c = null; //the Centeral system of this program, that handle marks
    private Info i = null; // pr-process all the point and and lvl for the graphics to display.
    public JFrame f; //the main game console.
    private boolean gameFreze = false; // will disable the user input and game play
    private boolean Draggedb = false; //allow to change the lvls
    private boolean helpOn = false; //the help pic on or of
    private boolean cheat = false;
    private Dimension dim; // the size of the screem
    private int lvl = 1; // lvl of the game
    private Point move, click, Draggedp; // all the position of moving, clicking and dragging.
    private MouseListener mouse; //the mouse listener for the main console
    private MouseMotionListener ls; // the mouse motion listener for the main game consloe
    private Image buttonDis, buttonOver, closeDis, closeOver, background, timeLabel, pointsLabel, pointZero, pointNine, helpLabel;
    // all the image that make up the graphics interface
    private Image lvlImage[] = new Image [3]; //lvl images
    private Image buttonState[] = new Image [10]; // number images
    private Image help[] = new Image [2]; //help image array
    private String clickOnBombMaaage, winBombMassage, restartMas; //massage location
    private boolean grad[] [] = new boolean [10] [10]; // the grad of the map, for displaying.
    private JFrame mas; // massage frame.

    public void gameMasage (String massage)
    { //massage frame.
	mas = new JFrame ();
	Image lable = Toolkit.getDefaultToolkit ().getImage (massage); // display the massage that need to display
	ImageIcon ic = new ImageIcon (lable); //convert the image to icon
	Image yes = Toolkit.getDefaultToolkit ().getImage ("data//label//yesNo//yes.png"); //load yes image
	Image no = Toolkit.getDefaultToolkit ().getImage ("data//label//yesNo//no.png"); //load no images
	ImageIcon yesi = new ImageIcon (yes); // convert yes image to icon
	ImageIcon noi = new ImageIcon (no); // convert no image to icon
	JLabel l = new JLabel (ic, 4); // create the label on the massage frame
	JButton yesb = new JButton (yesi); // create the yes buttom
	yesb.setActionCommand ("yes"); //create the yes action command.
	JButton nob = new JButton (noi); //create the no buttom.
	nob.setActionCommand ("no"); //create the no action command.
	mas.getContentPane ().add (l); // add in the massage label.
	mas.getContentPane ().setLayout (new GridLayout (3, 1)); // set the layout grid layout.
	mas.getContentPane ().add (yesb); // add yes buttom
	mas.getContentPane ().add (nob); // add no buttom
	mas.setLocation (500, -40); // set the location of he frame.
	mas.pack (); // chose the size.
	mas.setResizable (false); // make the frame non-resizable
	ActionListener t = new ActionListener ()  // add a action listen
	{
	    public void actionPerformed (ActionEvent e)  // performed when the action is takes place
	    {
		if ("yes".equals (e.getActionCommand ())) // when the action commen is yes
		{
		    mas.setVisible (false); //make the massage frame incisable
		    mas.dispose (); //dispose the massage frame.
		    f.setVisible (false); // mak the game invisable.
		    n.setGradToDefault (false, false); // set every the for a restart.
		    n.createFrame ("MineSweeper"); //create the new game console call Minesweeper.
		}
		else
		    System.exit (0); // if other massage comes up then exit the game.
	    }
	}
	;
	yesb.addActionListener (t); // add the action listener to yes buttom
	nob.addActionListener (t); // add the action listener to no buttom
	mas.setVisible (true); // display the massage frame.
    }


    public void createFrame (String name)
    {
	// main game console
	f = new JFrame (name); // create the game frame accroding the name.
	c = new Core (lvl); //create the game core according the lvl of the game
	move = new Point (); // init the the move points
	click = new Point (); // init the click points.
	f.setLocation (0, 0); // set the location the game frame to 0 , 0
	dim = Toolkit.getDefaultToolkit ().getScreenSize (); //create the size of the screem
	f.setSize (dim); //set size the frame to the same size of the sreem
	f.setResizable (false); // make the size of the frame no-resiable
	ls = new MouseMotionListener ()  // create the mouse motion listener
	{
	    public void mouseDragged (MouseEvent e)  //action proform when mouse is dragged
	    {
		Draggedp = new Point ((int) (e.getX ()), (int) (e.getY ())); // set value to the mouse draggedp for future uses
		if (e.getX () > (int) (dim.getWidth () - 200) && e.getY () > 200 && e.getX () < (int) (dim.getWidth () - 100) && e.getY () < 250)
		{
		    //enable lvl part when the action is dreeged within the condition's area.
		    Draggedb = true; //dragged switch on
		    gameFreze = true; // set game to freeze up
		}
		if (e.getX () > (int) (dim.getWidth () - 200) && e.getY () > 200 && e.getX () < (int) (dim.getWidth () - 100) && e.getY () < 250)
		    lvl = 0; // is at the condition's position will set the game to lvl easy
		else if (e.getX () > (int) (dim.getWidth () - 200) && e.getY () > 250 && e.getX () < (int) (dim.getWidth () - 100) && e.getY () < 300)
		    lvl = 1; //is at the condition's position will set the game to lvl mid
		else if (e.getX () > (int) (dim.getWidth () - 200) && e.getY () > 300 && e.getX () < (int) (dim.getWidth () - 100) && e.getY () < 350)
		    lvl = 2; //is at the condition's position will set the game to lvl hard
		e.consume ();
		if (!gameFreze)
		    repaint (); // repaint when the game is not freeze
	    }
	    public void mouseMoved (MouseEvent e)
	    {
		move.setLocation (e.getX (), e.getY ()); // get the moving position
		e.consume ();
		repaint (); // print the responding action's and images
	    }
	}
	;
	mouse = new MouseListener ()  // add mouse listener
	{
	    public void mouseClicked (MouseEvent e)  // proform when mouse is clicked
	    {
		click.setLocation (e.getX (), e.getY ()); //get x y position
		if (click.x < 500 && click.y < 500) //click within the 0,0,500,500 it will go to this part, which is the game play
		{
		    if (!gameFreze) // if game is not freeze then run the game
		    {
			if (9 != c.isBombAt (click.x / 50, click.y / 50)) // click is this click position is not bomb
			    aOE (click.x / 50, click.y / 50); // go to aoe [area of effect]
			else if (9 == c.isBombAt (click.x / 50, click.y / 50)) // click on the bomb
			{
			    i.addNewPoints (-10); // if it is bomb will send a point flag, see more info in [info.class]
			    gameMasage (clickOnBombMaaage); //then end to game and send a massage to use
			    n.setGradToDefault (true, true); //set the grad to all ture to see the map. and set the game to freeze up
			}
		    }
		    else
			if (helpOn)
			{
			    gameFreze = !gameFreze;
			    helpOn = !helpOn;
			    repaint ();
			}
			else
			    helpOn = !gameFreze;
		}
		else if (click.x >= (dim.getWidth () - 450) && click.x <= (dim.getWidth () - 350) && click.y >= 50 && click.y <= 100)
		{
		    helpOn = !helpOn;
		    gameFreze = !gameFreze;
		    // System.out.println ("Help is on");
		}
		else if (click.x >= (dim.getWidth () - 250) && click.y >= (dim.getHeight () - 200)) // the exit area
		    if (!helpOn)
		    {
			if (!Draggedb) // is the lvl part is on then there will be no exit
			    System.exit (0); // exit the game console
		    }
		    else
		    {
			helpOn = !gameFreze;
			cheat = !cheat;
			gameFreze = !gameFreze;
		    }
		else
		{
		    helpOn = false;
		    gameFreze = false;
		}
		e.consume ();
		if (!gameFreze)
		    repaint (); // repaint when game is not freeze
	    }

	    public void mouseEntered (MouseEvent e)
	    {
	    }

	    public void mouseExited (MouseEvent e)
	    {
	    }

	    public void mousePressed (MouseEvent e)
	    {
		if (!helpOn)
		    if (e.getX () > (int) (dim.getWidth () - 200) && e.getY () > 200 && e.getX () < (int) (dim.getWidth () - 100) && e.getY () < 250)
			Draggedb = true; // then dragged to false
		e.consume ();
		if (!gameFreze)
		    repaint ();
	    }

	    public void mouseReleased (MouseEvent e)
	    {
		if (Draggedb)
		    if (e.getX () > (int) (dim.getWidth () - 200) && e.getY () > 200 && e.getX () < (int) (dim.getWidth () - 100) && e.getY () < 350)
			gameMasage (restartMas); // to restart the game to apply setting
		Draggedb = false; // then dragged to false
		e.consume ();
		if (!gameFreze)
		    repaint ();
	    }
	}
	;
	f.addMouseMotionListener (ls); // add mouse motion listener
	f.addMouseListener (mouse); // add mouse listener
	f.getContentPane ().add (this, java.awt.BorderLayout.CENTER); // to add the paint in the frame, allow the frame to able to draw stuff
	f.setUndecorated (true); // set frame undecorated
	f.setVisible (true); // set visible to ture;
    }


    private void imageLoader ()  // load images
    {
	buttonDis = Toolkit.getDefaultToolkit ().getImage ("data//button//button_dis.png");
	buttonOver = Toolkit.getDefaultToolkit ().getImage ("data//button//Button_over.gif");
	for (int i = 0 ; i < 9 ; i++)
	    buttonState [i] = Toolkit.getDefaultToolkit ().getImage ("data//button//button_sle_noBomb_" + i + ".png");
	for (int i = 0 ; i < 3 ; i++)
	    lvlImage [i] = Toolkit.getDefaultToolkit ().getImage ("data//label//lvl//lvl_" + (i + 1) + ".png");
	pointZero = Toolkit.getDefaultToolkit ().getImage ("data//button//button_sle_noBomb_0_num.png");
	pointNine = Toolkit.getDefaultToolkit ().getImage ("data//button//button_sle_noBomb_9_num.png");
	buttonState [9] = Toolkit.getDefaultToolkit ().getImage ("data//button//button_sle_Bomb_9.png");
	closeDis = Toolkit.getDefaultToolkit ().getImage ("data//close//close_dis.png");
	closeOver = Toolkit.getDefaultToolkit ().getImage ("data//close//close_over.png");
	background = Toolkit.getDefaultToolkit ().getImage ("data//background//b.png");
	timeLabel = Toolkit.getDefaultToolkit ().getImage ("data//label//lvl//lvl.png");
	helpLabel = Toolkit.getDefaultToolkit ().getImage ("data//help//helpLabel.png");
	pointsLabel = Toolkit.getDefaultToolkit ().getImage ("data//label//points.png");
	for (int i = 0 ; i < 1 ; i++)
	    help [i] = Toolkit.getDefaultToolkit ().getImage ("data//help//help" + i + ".png");
	clickOnBombMaaage = "data//label//uJustBeenBombed.png";
	winBombMassage = "data//label//gg.png";
	restartMas = "data//label//reGG.png";
    }


    private void aOE (int x, int y)
    {
	// the Area Of Effect

	if (!grad [x] [y])
	    c.clickOnEmpty ();
	if (c.getEmptySpaceLeft () == 0) //if number of empty sqr become 0 then the game is a GG game will end and create a new game
	{
	    gameMasage (winBombMassage); //display the winnning massage
	    setGradToDefault (true, true); // set everything to default
	}
	if (c.isBombAt (x, y) == 0) // if the click on position is 0 then go though AOE
	{
	    if (!grad [x] [y]) // if the place is not be discovered then
		i.addNewPoints (1); // add 1 point
	    grad [x] [y] = true; // then then the this place to true
	    for (int a = -1 ; a <= 1 ; a++)
		for (int aa = -1 ; aa <= 1 ; aa++)
		{ //click all 8 sqr around the click on sqr
		    try
		    {
			if (!grad [x + a] [y + aa]) //if the sqr is not been discovered then discoiver it using AOE again
			    aOE (x + a, y + aa); // go to aor
		    }
		    catch (ArrayIndexOutOfBoundsException event)
		    {
			//if it is out of bound then nothing will be done
		    }
		}
	}
	else if (c.isBombAt (x, y) != 9)
	{
	    // if the discover position of the place is not bomb which is 9
	    if (!grad [x] [y]) // check for if the plance is discovered or not.
		i.addNewPoints (c.isBombAt (x, y) * 10); // give the info the point
	    grad [x] [y] = true; // set the position to true.
	}
	else
	{
	}
    }


    private void printGrads ()
    {
	int temp[] [] = c.returnGard ();

	System.out.print ("Core");
	for (int i = 0 ; i < 55 ; i++)
	    System.out.print ("=");
	System.out.println (" ");
	for (int i = 0 ; i < temp.length ; i++)
	{
	    for (int ii = 0 ; ii < temp [i].length ; ii++)
		System.out.print ("|" + temp [ii] [i] + "|");
	    System.out.println (" ");
	}
	System.out.print ("Grad [][]");
	for (int i = 0 ; i < 55 ; i++)
	    System.out.print ("=");
	System.out.println (" ");
	for (int i = 0 ; i < grad.length ; i++)
	{
	    for (int ii = 0 ; ii < grad [i].length ; ii++)
		System.out.print ("|" + grad [ii] [i] + "|");
	    System.out.println (" ");
	}
	cheat = !cheat;
    }


    private void setGradToDefault (boolean ans, boolean gameF)
    {
	// set everything back to begining.
	for (int i = 0 ; i < 10 ; i++)
	    for (int ii = 0 ; ii < 10 ; ii++)
		grad [i] [ii] = ans;
	gameFreze = gameF;
    }


    public static void main (String[] args)
    {
	// Place your code here
	n.i = new Info (); // to handle all the points and processing
	n.imageLoader (); // load all the image graphics
	n.createFrame ("MineSweeper"); // create the game console.
    } // main method


    public void paintComponent (Graphics g)
    {
	update (g);
    }


    public void update (Graphics g)
    {
	setBackgroundButtons (10, 10, g.create ());
	if (cheat)
	    printGrads ();
    }


    private void setBackgroundButtons (int x, int y, Graphics g)
    {
	g.fillRect (0, 0, (int) (dim.getWidth ()), (int) (dim.getHeight ())); //set background to black
	g.drawImage (background, 0, 0, this); // draw background image
	g.drawImage (closeDis, (int) (dim.getWidth () - 250), (int) (dim.getHeight () - 200), this); // draw close image
	for (int i = 0 ; i / 50 < x ; i += 50)
	    for (int ii = 0 ; ii / 50 < y ; ii += 50)
	    {
		if (!grad [i / 50] [ii / 50]) // draw the grad for the game play
		    g.drawImage (buttonDis, i, ii, this); // the image if it hasnt been discover
		else
		    g.drawImage (buttonState [c.isBombAt (i / 50, ii / 50)], i, ii, this); // if it is discover then draw the number according to the number within the core
	    }
	int temp[] = i.getCurPoints (); // the temp buffer for points
	g.drawImage (pointsLabel, (int) (dim.getWidth () - 200), 50, this); // draw the point lable
	g.drawImage (timeLabel, (int) (dim.getWidth () - 350), 200, this); // draw the lvl label
	g.drawImage (helpLabel, (int) (dim.getWidth () - 450), 50, this); // draw the help label
	if (Draggedb) // if the dragged funtion is profrem
	    for (int i = 200 ; i < 350 ; i += 50) // draw the lvls from easy to hard
		g.drawImage (lvlImage [(i - 200) / 50], (int) (dim.getWidth () - 200), i, this);
	else
	    g.drawImage (lvlImage [lvl], (int) (dim.getWidth () - 200), 200, this);
	for (int a = temp.length * 50 ; a > 0 ; a -= 50)
	    if (temp [(a / 50) - 1] == 0)
		g.drawImage (pointZero, (int) (dim.getWidth () - (50 * temp.length - a) - 100), 130, this);
	    else if (temp [(a / 50) - 1] == 9)
		g.drawImage (pointNine, (int) (dim.getWidth () - (50 * temp.length - a) - 100), 130, this);
	    else
		g.drawImage (buttonState [temp [(a / 50) - 1]], (int) (dim.getWidth () - (50 * temp.length - a) - 100), 130, this);
	if (move.x > (int) (dim.getWidth () - 250) && move.y > (int) (dim.getHeight () - 200)) // the close buttom then mouse move over the close buttom
	    g.drawImage (closeOver, (int) (dim.getWidth () - 250), (int) (dim.getHeight () - 200), this); // draw the image
	if (move.getX () < 500 && move.getY () < 500)
	    if (!grad [move.x / 50] [move.y / 50]) //draw use program relaction
		if (!cheat)
		    g.drawImage (buttonOver, ((move.x - 1) / 50) * 50, ((move.y - 1) / 50) * 50, this); // draw mouse over when mouse over the sqr
		else
		    g.drawImage (buttonState [c.isBombAt (move.x / 50, move.y / 50)], ((move.x - 1) / 50) * 50, ((move.y - 1) / 50) * 50, this);
	if (helpOn)
	    g.drawImage (help [0], (int) (dim.getWidth () - 700), 0, this);
    } // New class
}


