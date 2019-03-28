package com.tsop.android.triplePlayPoker;

import java.util.Date;
import java.util.HashMap;

import com.tsop.games.triplePlay.Calculator;
import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.Deck;
import com.tsop.games.triplePlay.Faces;
import com.tsop.games.triplePlay.Games;
import com.tsop.games.triplePlay.Hand;
import com.tsop.games.triplePlay.Suits;
import com.tsop.games.triplePlay.WinningHands;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowGameBoard extends Activity {
	
	// GUI objects
	private ImageView imgHoldFrames[];
	private ImageButton btnCards[];
	private ImageView imgCards[][];
	private ImageButton btnDeal, btnMaxBet, btnBetOne, btnSound, btnVibrate;
	public static Drawable CardImage[][];
	protected static Drawable back;
	private TextView txtWinValue, txtBetValue, txtCreditsValue, txtStatus;
	private TextView[] txtResultsNames, txtBetLabels;
	private TextView[] txtResultsValues, txtBetValues;
	private ImageView[] imgResultsFrames, imgBetFrames;
	private Menu mnuOptions;
	private MenuItem mnuVibrate, mnuSound, mnuChangeGame;

    private SoundPool soundPool; 
    private HashMap<Sounds, Integer> soundPoolMap; 
     
	private Animation anmFlipOver[], anmFlipBack[];

	// Game variables
	private static Hand hands[],
		 evalHands[];
	private static Deck[] decks;
	private int credits, winnings, bet, currentBet;
	private boolean enableHand2, enableHand3, firstDealBoolean, newBet;
	private boolean keepCard[] = {false,false,false,false,false};
	private static final int maxBet = 15;
	private int betPerHand[] = {1,1,1};
	private static boolean betTaken = false;  //variable to keep track of deducting
	private static boolean soundOn = true;
	private static boolean vibrateOn = true;
	private long clickTime = 0;
	private String[] betMessages;
	private int betIndex = 0;
	private Games currentGame = Games.JacksOrBetter;
	private boolean useJokers = false;
	private int numCards = com.tsop.games.triplePlay.Constants.NUM_CARDS_IN_DECK;

//////////////////////////////////////////////////////////////////////////////////////////
// Construction /Destruction
//////////////////////////////////////////////////////////////////////////////////////////
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.gameboard);
        setContentView(R.layout.gameboard_new);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        imgHoldFrames = new ImageView[] {(ImageView)findViewById(R.id.imgHold1),
        		(ImageView)findViewById(R.id.imgHold2),
        		(ImageView)findViewById(R.id.imgHold3),
        		(ImageView)findViewById(R.id.imgHold4),
        		(ImageView)findViewById(R.id.imgHold5)};

        btnCards = new ImageButton[] {(ImageButton)findViewById(R.id.btnCard1),
        		(ImageButton)findViewById(R.id.btnCard2),
        		(ImageButton)findViewById(R.id.btnCard3),
        		(ImageButton)findViewById(R.id.btnCard4),
        		(ImageButton)findViewById(R.id.btnCard5)};
        
        imgCards = new ImageView[][] {{
        	(ImageView)findViewById(R.id.imgCard21),
        	(ImageView)findViewById(R.id.imgCard22),
        	(ImageView)findViewById(R.id.imgCard23),
        	(ImageView)findViewById(R.id.imgCard24),
        	(ImageView)findViewById(R.id.imgCard25),
        }, {
        	(ImageView)findViewById(R.id.imgCard31),
        	(ImageView)findViewById(R.id.imgCard32),
        	(ImageView)findViewById(R.id.imgCard33),
        	(ImageView)findViewById(R.id.imgCard34),
        	(ImageView)findViewById(R.id.imgCard35),
        }};

        for (int i=0; i < 5; i++)
        {
        	btnCards[i].setOnClickListener(HoldClickListener);
        	btnCards[i].setClickable(false);
        }
        
        btnDeal = (ImageButton)findViewById(R.id.btnDeal);
        btnDeal.setOnClickListener(DealClickListener);
        
        btnMaxBet = (ImageButton)findViewById(R.id.btnMaxBet);
        btnMaxBet.setOnClickListener(MaxBetClickListener);
        
        btnBetOne = (ImageButton)findViewById(R.id.btnBetOne);
        btnBetOne.setOnClickListener(BetOneClickListener);
        
        btnSound = (ImageButton)findViewById(R.id.btnSound);
        btnSound.setOnClickListener(ToggleSoundClickListener);
        btnSound.setImageDrawable(getResources().getDrawable(android.R.drawable.ic_lock_silent_mode_off));
        
        btnVibrate = (ImageButton)findViewById(R.id.btnVibrate);
        btnVibrate.setOnClickListener(ToggleVibrateClickListener);
        btnVibrate.setImageDrawable(getResources().getDrawable(R.drawable.ic_vibrate));
        
//        mnuOptions = (Menu)findViewById(R.menu.options_menu);
//        mnuSound = mnuOptions.getItem(R.id.SoundToggle);
//        mnuVibrate = mnuOptions.getItem(R.id.VibrateToggle);
        
        LoadPreferences();
        soundOn = !soundOn;
        vibrateOn = !vibrateOn;
        ToggleSound();
        ToggleVibrate();
        InitCurrentGameMenu();
        
        back = getResources().getDrawable(R.drawable.back);
        CardImage = new Drawable[14][4];
        loadCardImages();
        
        txtWinValue = (TextView) findViewById(R.id.txtWinValue);
        txtCreditsValue = (TextView) findViewById(R.id.txtCreditsValue);
        txtBetValue = (TextView) findViewById(R.id.txtBetValue);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        
        txtResultsNames = new TextView[] {
        		(TextView)findViewById(R.id.txtResultsName1),
        		(TextView)findViewById(R.id.txtResultsName2),
        		(TextView)findViewById(R.id.txtResultsName3)
        };
        txtResultsValues = new TextView[] {
        		(TextView)findViewById(R.id.txtResultsValue1),
        		(TextView)findViewById(R.id.txtResultsValue2),
        		(TextView)findViewById(R.id.txtResultsValue3)
        };
        imgResultsFrames = new ImageView[] {
        		(ImageView)findViewById(R.id.imgResults1),
        		(ImageView)findViewById(R.id.imgResults2),
        		(ImageView)findViewById(R.id.imgResults3)
        };
        
        txtBetLabels = new TextView[] {
        		(TextView)findViewById(R.id.txtBetLabel1),
        		(TextView)findViewById(R.id.txtBetLabel2),
        		(TextView)findViewById(R.id.txtBetLabel3)
        };
        txtBetValues = new TextView[] {
        		(TextView)findViewById(R.id.txtBetValue1),
        		(TextView)findViewById(R.id.txtBetValue2),
        		(TextView)findViewById(R.id.txtBetValue3)
        };
        imgBetFrames = new ImageView[] {
        		(ImageView)findViewById(R.id.imgBet1),
        		(ImageView)findViewById(R.id.imgBet2),
        		(ImageView)findViewById(R.id.imgBet3)
        };
        
        anmFlipOver = new Animation[5];
        anmFlipBack = new Animation[5];
        for (int i=0; i < 5; i++){
        	anmFlipOver[i] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_flip_over);
        	anmFlipOver[i].setStartOffset(50 + (i * 50));
        	anmFlipOver[i].setFillEnabled(true);
        	anmFlipOver[i].setFillAfter(true);
            anmFlipBack[i] = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.card_flip_back);
            anmFlipBack[i].setFillEnabled(true);
            anmFlipBack[i].setFillBefore(true);
       }
       
        betMessages = new String[] {getResources().getString(R.string.BET_MESSAGE_1),
        		getResources().getString(R.string.BET_MESSAGE_2)
        	};
        
        InitSounds();
        Init();
        
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {    
    	MenuInflater inflater = getMenuInflater();    
    	inflater.inflate(R.menu.options_menu, menu);    
    	mnuOptions = menu;
    	mnuSound = menu.findItem(R.id.SoundToggle);
    	mnuVibrate = menu.findItem(R.id.VibrateToggle);
    	mnuChangeGame = menu.findItem(R.id.ChangeGame);
    	if (soundOn)
    	{
	    	if (mnuSound != null) mnuSound.setTitle(R.string.MENU_ITEM_SOUND_OFF);
    	}
    	else
    	{
    		if (mnuSound != null) mnuSound.setTitle(R.string.MENU_ITEM_SOUND_ON);
    	}
    	if (vibrateOn)
    	{
	    	if (mnuVibrate != null) mnuVibrate.setTitle(R.string.MENU_ITEM_VIBRATE_OFF);
    	}
    	else
    	{
    		if (mnuVibrate != null) mnuVibrate.setTitle(R.string.MENU_ITEM_VIBRATE_ON);
    	}
    	
		SetCurrentGameMenu();
    	return true;
	}
    
    private void InitDecks()
    {
        decks = new Deck[3];
        numCards = com.tsop.games.triplePlay.Constants.NUM_CARDS_IN_DECK;
        if (Calculator.JokersAreUsed(currentGame))
        	numCards += com.tsop.games.triplePlay.Constants.NUM_JOKERS_IN_DECK;
        decks[0] = new Deck(numCards);
        decks[1] = new Deck(numCards - 5);
        decks[2] = new Deck(numCards - 5);    	
    }
    
    private void Init()
    {
    	//Initialize Game variables & instantiate objects
    	InitDecks();

        firstDealBoolean = true;
//        credits = 200;
        LoadCredits();
        if (credits == 0)
        	credits = Constants.STARTING_CREDITS;
        winnings = 0;
        bet = 0;
        enableHand2 = true;        //initial state is all 3 hands turned on
        enableHand3 = true;

        hands = new Hand[] {new Hand(), new Hand(), new Hand()};
        evalHands = new Hand[] {new Hand(), new Hand(), new Hand()};
        
        ClearResults();
        ClearBetFrames();
        SetStatusText(R.string.PLACE_A_BET);
        txtCreditsValue.setText(Integer.toString(credits));
        txtWinValue.setText(Integer.toString(winnings));
        txtBetValue.setText(Integer.toString(bet));
        
        // flip all cards over
        for (int i=0; i < 5; i++)
        	btnCards[i].setImageResource(R.drawable.back);
        for (int i=0; i < 2; i++)
        	for (int j=0; j < 5; j++)
        		imgCards[i][j].setImageResource(R.drawable.back);
        
        for (int i = 0; i <= 4; i++)                   //reset Keep variables
        {
     	imgHoldFrames[i].setVisibility(4);
         keepCard[i] = false;		
        }
        
        firstDealBoolean  = true;    //so next action will enter deal loop
        newBet = true;               //so betting can restart at 1
        btnBetOne.setEnabled(true);  //enable bet button for next deal cycle
        btnMaxBet.setEnabled(true);
        btnDeal.setImageDrawable(getResources().getDrawable(R.drawable.btndeal));  //change label to deal

        for (int i = 0; i <= 4; i++)
          btnCards[i].setClickable(false);


    }
    
    private void InitSounds() { 
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100); 
        soundPoolMap = new HashMap<Sounds, Integer>(); 
        soundPoolMap.put(Sounds.Bet, soundPool.load(getApplicationContext(), R.raw.bet, 1)); 
        soundPoolMap.put(Sounds.Deal, soundPool.load(getApplicationContext(), R.raw.deal, 1));
        soundPoolMap.put(Sounds.Hold, soundPool.load(getApplicationContext(), R.raw.hold, 1));
        soundPoolMap.put(Sounds.Jackpot, soundPool.load(getApplicationContext(), R.raw.jackpot, 1));
        soundPoolMap.put(Sounds.Lose, soundPool.load(getApplicationContext(), R.raw.lose, 1));
        soundPoolMap.put(Sounds.Win, soundPool.load(getApplicationContext(), R.raw.win, 1));
        soundPoolMap.put(Sounds.WinningHand, soundPool.load(getApplicationContext(), R.raw.hand, 1));
        soundPoolMap.put(Sounds.RoyalJackpot, soundPool.load(getApplicationContext(), R.raw.royal_jackpot, 1));
   }

//////////////////////////////////////////////////////////////////////////////////////////
// Private Methods
//////////////////////////////////////////////////////////////////////////////////////////

    private void FirstDeal()
	{
    	PlaySound(Sounds.Deal);
    	ClearResults();
    	ClearBetFrames();
        currentBet = 0;   //"erases" bet if betButton is first used, and then overidden with maxBet

        for (int i = 0; i <= 4; i++)                   //reset Keep variables
           {
        	imgHoldFrames[i].setVisibility(4);
            keepCard[i] = false;		
           }
        shuffle(0);

        for(int i=0; i <= 4; i++)
        {
           hands[0].getCards()[i] = decks[0].dealCard();
        }
//        hands[0].getCards()[0] = new Card(Faces.Ace, Suits.Clubs);
//        hands[0].getCards()[1] = new Card(Faces.King, Suits.Clubs);
//        hands[0].getCards()[2] = new Card(Faces.Queen, Suits.Clubs);
//        hands[0].getCards()[3] = new Card(Faces.Jack, Suits.Clubs);
//        hands[0].getCards()[4] = new Card(Faces.Ten, Suits.Clubs);
        
         //if ( hand[0][4] != null ) {
           copy();
           evalHands[0].SortCards();

           for (int j = 0; j <= 4; j++)
           {
        	   FlipCard(j, hands[0].getCards()[j].toPict());
//              btnCards[j].setImageDrawable(hands[0].getCards()[j].toPict());
//              btnCards[j].invalidate();
           }
           
//           String winningHand = Calculator.getHighestHandString(currentGame, evalHands[0].getCards());
//           if (winningHand.compareTo(WinningHands.Nothing.toString()) != 0)
//        	   txtStatus.setText(winningHand);
//           else
           		SetStatusText(R.string.HOLD_MESSAGE);
           
           //"Turn Over" cards in hands 2 and 3
           for (int i = 0; i <= 1; i++)
             for (int j = 0; j <= 4; j++)
               imgCards[i][j].setImageDrawable(back);


           txtCreditsValue.setText(Integer.toString(credits));
           txtBetValue.setText(Integer.toString(bet));
           txtWinValue.setText("");

        // deduct bet from credits and update display
        if (!betTaken) //when last bet is repeated by default
           {
             if (credits >= bet)    // check that sufficient credits exits
               credits -= bet;
             else                   // if not, use up remaining credits
               {
                 bet = credits;
                 credits = 0;

               }
             txtCreditsValue.setText(Integer.toString(credits));
             txtBetValue.setText(Integer.toString(bet));
           }

        // move these lines here so they are executed all the time
        if (bet == 1) { enableHand2 = false; enableHand3 = false;}
        if (bet == 2) { enableHand2 = true; enableHand3 = false;}
        if (bet >= 3) {enableHand2 = true;  enableHand3 = true;}

        betTaken = false;

        firstDealBoolean = false;
        btnDeal.setImageDrawable(getResources().getDrawable(R.drawable.btndraw));

        btnBetOne.setEnabled(false);
        btnMaxBet.setEnabled(false);
        if (mnuChangeGame != null)
        	mnuChangeGame.setEnabled(false);
        
        for (int i = 0; i <= 4; i++)
         btnCards[i].setClickable(true);
	
	}
    
    private void Draw()
    {
        CopyDeck(decks[0].getCards());
        shuffle(1);
        shuffle(2);
	   	for(int i=0; i <= 4; i++)
          {
          if(keepCard[i] == false)
              for (int j = 0; j <= 2; j++)
                hands[j].getCards()[i] = decks[j].dealCard();
          else
            for (int j = 1; j <= 2; j++)
                hands[j].getCards()[i] = hands[0].getCards()[i];
          } //end for loop

        //if ( hand[0][4] != null )
        //  {
 	   	copy();
            for (int i = 0; i <= 2; i++)
                evalHands[i].SortCards();

            for (int j = 0; j <= 4; j++)
//            	FlipCard(j, hands[0].getCards()[j].toPict());
            	btnCards[j].setImageDrawable(hands[0].getCards()[j].toPict());

            if (enableHand2)
              for (int j = 0; j <= 4; j++)
                imgCards[0][j].setImageDrawable(hands[1].getCards()[j].toPict());
            else
              for (int j = 0; j <= 4; j++)
                imgCards[0][j].setImageDrawable(back);

            if (enableHand3)
              for (int j = 0; j <= 4; j++)
                imgCards[1][j].setImageDrawable(hands[2].getCards()[j].toPict());
            else
              for (int j = 0; j <= 4; j++)
                imgCards[1][j].setImageDrawable(back);
          //}

            int winnings1 = EvalWinnings(evalHands[0].getCards())*CalcBetPerHand(bet,0);
            int winnings2 = EvalWinnings(evalHands[1].getCards())*CalcBetPerHand(bet,1);
            int winnings3 = EvalWinnings(evalHands[2].getCards())*CalcBetPerHand(bet,2);
            	
            ShowResult(0, EvalString(evalHands[0].getCards()), Integer.toString(winnings1));

        if (enableHand2) 
        	ShowResult(1, EvalString(evalHands[1].getCards()), Integer.toString(winnings2));
        else 
        	ClearResult(1);
        if (enableHand3) 
        	ShowResult(2, EvalString(evalHands[2].getCards()), Integer.toString(winnings3));
        else 
        	ClearResult(2);

        //calculate winnings, adjust balance
        winnings = 0;
        winnings += winnings1;
        winnings += winnings2;
        winnings += winnings3;
        credits += winnings;

        VibrateWinnings(winnings);
        if (winnings > 799)
        {
        	PlaySound(Sounds.RoyalJackpot);
        }
        else if (winnings > 74)
        {
        	PlaySound(Sounds.Jackpot);
        }
        else if (winnings > 0) 
        {
        	PlaySound(Sounds.Win);
        }
        else 
        	PlaySound(Sounds.Lose);

        txtCreditsValue.setText(Integer.toString(credits));
        txtBetValue.setText(Integer.toString(bet));
        txtWinValue.setText(Integer.toString(winnings));
        
        SetStatusText(betMessages[betIndex++ % 2]);

        firstDealBoolean  = true;    //so next action will enter deal loop
        newBet = true;               //so betting can restart at 1
        btnBetOne.setEnabled(true);  //enable bet button for next deal cycle
        btnMaxBet.setEnabled(true);
        if (mnuChangeGame != null)
        	mnuChangeGame.setEnabled(true);
        btnDeal.setImageDrawable(getResources().getDrawable(R.drawable.btndeal));  //change label to deal

        for (int i = 0; i <= 4; i++)
          btnCards[i].setClickable(false);
        
        SaveCredits();

        if (credits <= 0)
          {
          	SetStatusText(R.string.GAME_OVER);
            btnDeal.setEnabled(false);
            btnBetOne.setEnabled(false);
            btnMaxBet.setEnabled(false);
          }


    }
    
    //After first deal, this method copies cards 6-52
    //of the original deck to the 2nd and 3rd decks
    public void CopyDeck(Card origDeck[])
    {
       for (int i = 5; i < numCards; i++)
         {
         decks[1].getCards()[i-5] = origDeck[i];
         decks[2].getCards()[i-5] = origDeck[i];
         }
    }

    private void shuffle(int deckNo)
    {
 	   decks[deckNo].shuffle();
 	   btnDeal.setEnabled(true);

    }

    public void copy()    //copies real hand to evaluation hand
    {                     //for subsequent sorting
     for (int i=0;i <= 4; i++)
       {
       for(int j = 0; j <= 2; j++)
         evalHands[j].getCards()[i] = hands[j].getCards()[i];
       }
    }
    
    private void loadCardImages()
    {
       String f[] = {"carda","card2","card3","card4","card5","card6","card7","card8","card9",
                           "card10","cardj","cardq","cardk"};
       String s[] = {"h","d","c","s"};

       try
       {
       Class res = Class.forName("com.tsop.android.triplePlayPoker.R");
       Class[] subClasses = res.getDeclaredClasses();  
       Class draw = null;  
       for (Class subclass : subClasses) {  
    	   if ("com.tsop.android.triplePlayPoker.R.drawable".equals(subclass.getCanonicalName())) {  
    		   draw = subclass;  
    		   break;
    	   }
       }
 
       for (int i = 0; i <= 12; i++)
         for (int j = 0; j <=3; j++)
           {
            String resString = f[i] + s[j];
            int id = (int) draw.getField(resString).getInt(draw);
            System.out.println(resString + " = " + id);
            CardImage[i][j] = getResources().getDrawable(id);
           }
       
       CardImage[13][0] = getResources().getDrawable(R.drawable.cardjoker);
       }
       catch (Exception ex)
       {
    	   System.out.println("loadCardImages(): " + ex.getMessage());
       }
    }

    private void ClearResult(int i)
    {
		imgResultsFrames[i].setVisibility(4);
		txtResultsNames[i].setVisibility(4);
		txtResultsValues[i].setVisibility(4);
    }
    
    private void ClearResults()
    {
    	for (int i=0; i < 3; i++)
    		ClearResult(i);
    	SetStatusText("");
    }
    
    private void ClearBetFrames()
    {
    	for (int i=0; i < 3; i++)
    	{
    		imgBetFrames[i].setVisibility(4);
    		txtBetLabels[i].setVisibility(4);
    		txtBetValues[i].setVisibility(4);
    	}
    }
    
    private void ShowBets()
    {
    	ClearResults();
    	for (int i=0; i < 3; i++)
    	{
    		txtBetValues[i].setText(Integer.toString(CalcBetPerHand(bet,i)));
    		imgBetFrames[i].setVisibility(0);
    		txtBetLabels[i].setVisibility(0);
    		txtBetValues[i].setVisibility(0);
    	}
    }
    
    private void ShowResult(int index, String name, String value)
    {
    	ClearBetFrames();
    	txtResultsNames[index].setText(name);
    	txtResultsValues[index].setText(value);
		imgResultsFrames[index].setVisibility(0);
		txtResultsNames[index].setVisibility(0);
		txtResultsValues[index].setVisibility(0);
    	
    }

    // Method to evalute passed hand and return a String
    //that describes what that hand is.  Also changes font
    //of passed JTextField to BOLD if hand is a winner
    public String EvalString(Card eval[])
    {
       return Calculator.getHighestHandString(currentGame, eval);
    } //end EvalString


    // Method to evalute passed hand and return an integer
    //which is the value of the winnings for that hand
    //per coin
    public int EvalWinnings(Card eval[])
    {
       return Calculator.EvalWinnings(currentGame, eval);
    } 

    //Method to figure out how many credits are bet per Hand
    public static int CalcBetPerHand (int bet, int hand)
    {

       if (hand == 0) return ((int)(bet/3) + ((bet % 3 == 0) ? 0 : 1) );
       if (hand == 1) return ((int)(bet/3) + ((bet % 3 == 2) ? 1 : 0) );
       return ((int)(bet/3));
    }

    private void holdCard(int cardToHold)
    {
       PlaySound(Sounds.Hold);
       if (imgHoldFrames[cardToHold].getVisibility() == 4)	// if card is held
       {
         keepCard[cardToHold] = false;
         imgCards[0][cardToHold].setImageDrawable(back);
         imgCards[1][cardToHold].setImageDrawable(back);
       }
       else
       {
         keepCard[cardToHold] = true;
         if (enableHand2)
           imgCards[0][cardToHold].setImageDrawable(hands[0].getCards()[cardToHold].toPict());
         if (enableHand3)
           imgCards[1][cardToHold].setImageDrawable(hands[0].getCards()[cardToHold].toPict());
       }
    }
    
    private void PlaySound(Sounds sound)
    {
    	if (!soundOn)
    		return;

        AudioManager mgr = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);   
        float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);   
        float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);       
        float volume = streamVolumeCurrent / streamVolumeMax;   
        soundPool.play(soundPoolMap.get(sound), volume, volume, 1, 0, 1f); 

    }

    private void VibrateWinnings(int winnings)
    {
	long duration = 0;
	if (winnings >= 4000)
	    duration = winnings;
	else if (winnings >= 800)
	    duration = 2000 + (int) ((winnings - 800) * 0.625);
	else if (winnings >= 75)
	    duration = 1000 + (int) ((winnings - 75) * 1.38);
	else if (winnings >= 10)
	    duration = 10 * winnings;
	else if (winnings > 0)
	    duration = 100;
 	    
	if (duration > 0)
		Vibrate(duration);
    }
    
    private void Vibrate(long time)
    {
    	if (!vibrateOn)
    		return;
    	
    	Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);   
    	v.vibrate(time);   
    }
    
    private void LoadPreferences()
    {
    	// TODO Add last game played to preferences
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
        soundOn = settings.getBoolean(Constants.SOUND_ON_PREF, true);
        vibrateOn = settings.getBoolean(Constants.VIBRATE_ON_PREF, true);
        int currentGameID = settings.getInt(Constants.CURRENT_GAME_PREF, 0);
        currentGame = Games.GameFromIndex(currentGameID);
        }
    
    private void SavePreferences()
    {      
    	SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
    	SharedPreferences.Editor editor = settings.edit();
    	editor.putBoolean(Constants.SOUND_ON_PREF, soundOn);
    	editor.putBoolean(Constants.VIBRATE_ON_PREF, vibrateOn);
    	editor.putInt(Constants.CURRENT_GAME_PREF, currentGame.getIndex());
    	editor.commit();
    }
    
    private void LoadCredits()
    {
        SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
        credits = settings.getInt(Constants.CREDITS_PREF, Constants.STARTING_CREDITS);
    }
    
    private void SaveCredits()
    {      
    	SharedPreferences settings = getSharedPreferences(Constants.PREFS_NAME, 0);
    	SharedPreferences.Editor editor = settings.edit();
    	editor.putInt(Constants.CREDITS_PREF, credits);
    	editor.commit();
    }

    private void FlipCard(int index, Drawable newCard)
    {
		btnCards[index].setImageDrawable(getResources().getDrawable(R.drawable.back));
		anmFlipOver[index].setAnimationListener(new FinishFlip(index, newCard));
		btnCards[index].startAnimation(anmFlipOver[index]);

    }
    
    /**
     * This class listens for the end of the first half of the card flip animation.
     * It then changes the card to the correct image and finishes the flip
     */
    private final class FinishFlip implements Animation.AnimationListener {
        private final int cardIndex;
        private final Drawable cardImage;

        private FinishFlip(int index, Drawable newCard) {
            cardIndex = index;
            cardImage = newCard;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
        	btnCards[cardIndex].setImageDrawable(cardImage);
        	btnCards[cardIndex].startAnimation(anmFlipBack[cardIndex]);
//        	PlaySound(Sounds.Deal); 
        	// if this is the last card flipped, determine if the dealt hand is a winner and
        	// alert the user
        	if (cardIndex == 4)
        	{
        		ShowDealtWinningHand();
         	}
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }
    
    private void SetStatusText(int statusID)
    {
  	   txtStatus.setText(statusID);
 	   txtStatus.setTextColor(getResources().getColor(R.color.Yellow));
// 	   txtStatus.setBackgroundDrawable(null);
    }
    
    private void SetStatusText(String status)
    {
  	   txtStatus.setText(status);
 	   txtStatus.setTextColor(getResources().getColor(R.color.Yellow));
// 	   txtStatus.setBackgroundDrawable(null);
    }

    private boolean ShowDealtWinningHand()
    {
        String winningHand = Calculator.getHighestHandString(currentGame, evalHands[0].getCards());
        if (winningHand.compareTo(WinningHands.Nothing.toString()) != 0)
        {
     	   txtStatus.setText(winningHand);
     	   txtStatus.setTextColor(getResources().getColor(R.color.White));
//     	   txtStatus.setBackgroundColor(getResources().getColor(R.color.Yellow));
     	   PlaySound(Sounds.WinningHand);
     	   return true;
        }
        else
        	return false;
   	
    }
    

 //////////////////////////////////////////////////////////////////////////////////////////
 // Handlers
 //////////////////////////////////////////////////////////////////////////////////////////
    private View.OnClickListener HoldClickListener = new View.OnClickListener() {
		public void onClick(View arg0) {
			int holdIndex = 0;
			switch (arg0.getId())
			{
			case R.id.btnCard1:
				holdIndex = 0;
				break;
			case R.id.btnCard2:
				holdIndex = 1;
				break;
			case R.id.btnCard3:
				holdIndex = 2;
				break;
			case R.id.btnCard4:
				holdIndex = 3;
				break;
			case R.id.btnCard5:
				holdIndex = 4;
				break;
			}
       		if (imgHoldFrames[holdIndex].getVisibility() == 0)
       			imgHoldFrames[holdIndex].setVisibility(4);
    		else
    			imgHoldFrames[holdIndex].setVisibility(0);
			holdCard(holdIndex);
		}
	};
	
	   private View.OnClickListener DealClickListener = new View.OnClickListener() {
			public void onClick(View arg0) {
				long timeNow = (new Date()).getTime();
				if (timeNow - clickTime < 500)
					return;
				clickTime = timeNow;
				if (bet == 0) 
				{
					SetStatusText(R.string.PLACE_A_BET);
					return;
				}
			      if (firstDealBoolean)
			          FirstDeal();
			        else Draw();
			}
	   };
	   
	   private View.OnClickListener MaxBetClickListener = new View.OnClickListener() {
			public void onClick(View arg0) {
		      PlaySound(Sounds.Bet);
		      if (credits >= maxBet)
		        {
		        bet = maxBet;
		        credits -= bet;
		        }
		      else
		        {
		        bet = credits;
		        credits = 0;
		        }
		      credits += currentBet;
		      txtCreditsValue.setText(Integer.toString(credits));
		      betTaken = true;
		      btnMaxBet.setEnabled(false);
		      btnBetOne.setEnabled(false);
		      btnDeal.performClick();
			}
	   };

	   private View.OnClickListener BetOneClickListener = new View.OnClickListener() {
			public void onClick(View arg0) {
			  PlaySound(Sounds.Bet);
		      if (newBet)
		        {
		        bet = 0;
		        newBet = false;
		        }
		      bet++;
		      credits--;
		      currentBet++;
		      txtCreditsValue.setText(Integer.toString(credits));
		      betTaken = true;
		      txtBetValue.setText(Integer.toString(bet));
		      if (bet == 1) { enableHand2 = false; enableHand3 = false;}
		      if (bet == 2) { enableHand2 = true; enableHand3 = false;}
		      if (bet >= 3) {enableHand2 = true;  enableHand3 = true;}
		      if ((bet >= maxBet) || (credits == 0))
		      {
		        btnBetOne.setEnabled(false);
		        btnMaxBet.setEnabled(false);
		      }
		      ShowBets();
			}
	   };

	   private View.OnClickListener ToggleSoundClickListener = new View.OnClickListener() {
			public void onClick(View arg0) {
				ToggleSound();
			}
	   };
	   
  
	   private View.OnClickListener ToggleVibrateClickListener = new View.OnClickListener() {
			public void onClick(View arg0) {
				ToggleVibrate();
			}
	   };
	   
	   private void ToggleSound()
	   {
	      soundOn = !soundOn;
	      if (soundOn)
	      {
	    	  btnSound.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume));
	    	  if (mnuSound != null) mnuSound.setTitle(R.string.MENU_ITEM_SOUND_OFF);
	      }
	      else
	      {
	    	  btnSound.setImageDrawable(getResources().getDrawable(R.drawable.ic_volume_off));
	    	  if (mnuSound != null) mnuSound.setTitle(R.string.MENU_ITEM_SOUND_ON);
	      }
	      SavePreferences();
	   }
	   
	   private void ToggleVibrate()
	   {
	      vibrateOn = !vibrateOn;
	      if (vibrateOn)
	      {
	    	  btnVibrate.setImageDrawable(getResources().getDrawable(R.drawable.ic_vibrate));
	    	  if (mnuVibrate != null) mnuVibrate.setTitle(R.string.MENU_ITEM_VIBRATE_OFF);
	      }
	      else
	      {
	    	  btnVibrate.setImageDrawable(null);
	    	  if (mnuVibrate != null) mnuVibrate.setTitle(R.string.MENU_ITEM_VIBRATE_ON);
	      }
	      SavePreferences();
	   }

	   private void InitCurrentGameMenu()
	   {
		   if (mnuOptions != null)
		   {
				mnuOptions.findItem(R.id.ChangeGameDeucesWild).setChecked(false);
				mnuOptions.findItem(R.id.ChangeGameJacksOrBetter).setChecked(false);
				mnuOptions.findItem(R.id.ChangeGameBonusPoker).setChecked(false);
				mnuOptions.findItem(R.id.ChangeGameJokerPoker).setChecked(false);
				switch (currentGame)
				{
				case JacksOrBetter:
					mnuOptions.findItem(R.id.ChangeGameJacksOrBetter).setChecked(true);
					break;
				case BonusPoker:
					mnuOptions.findItem(R.id.ChangeGameBonusPoker).setChecked(true);
					break;
				case DeucesWild:
					mnuOptions.findItem(R.id.ChangeGameDeucesWild).setChecked(true);
					break;
				case JokersWild:
					mnuOptions.findItem(R.id.ChangeGameJokerPoker).setChecked(true);
					break;
				}
			}
	   }
	   
	   private void Exit()
	   {
		    soundPool.release();
			int pid = android.os.Process.myPid(); 
			android.os.Process.killProcess(pid);
	   }
	   
	   private void ShowPayoutDialog()
	   {
		   PayoutDialog payoutDlg = new PayoutDialog(this);
		   payoutDlg.setCurrentGame(currentGame);
		   payoutDlg.show();	
	   }
	   
	   /* Handles item selections */
	   public boolean onOptionsItemSelected(MenuItem item) {    
		   switch (item.getItemId()) {    
		   	case R.id.SoundToggle:        
		   		ToggleSound();        
		   		return true;    
		   	case R.id.VibrateToggle:        
		   		ToggleVibrate();        
		   		return true;    
	   		case R.id.Reset:        
	   			credits = Constants.STARTING_CREDITS;
	   			SaveCredits();	// re-initializes the saved credits
	   			Init();
	   			return true;    
	   		case R.id.Payouts:
	   			ShowPayoutDialog();
	   			return true;
	   		case R.id.Exit:
	   			Exit();
	   			return true;
	   			// TODO Add game chosen to status window
	   		case R.id.ChangeGameBonusPoker:
	   			ChangeGame(Games.BonusPoker);
	   			return true;
	   		case R.id.ChangeGameJacksOrBetter:
	   			ChangeGame(Games.JacksOrBetter);
	   			return true;
	   		case R.id.ChangeGameDeucesWild:
	   			ChangeGame(Games.DeucesWild);
	   			return true;
	   		case R.id.ChangeGameJokerPoker:
	   			ChangeGame(Games.JokersWild);
	   			return true;
   			}    
		   return false;
	   }
	   
	   protected void ChangeGame(Games game)
	   {
		   currentGame = game;
		   InitDecks();
		   SetCurrentGameMenu();
		   SavePreferences();
		   SetStatusText(Calculator.GetGameName(currentGame));
	   }
	   
	   protected void SetCurrentGameMenu()
	   {
			mnuOptions.findItem(R.id.ChangeGameDeucesWild).setChecked(false);
			mnuOptions.findItem(R.id.ChangeGameJacksOrBetter).setChecked(false);
			mnuOptions.findItem(R.id.ChangeGameBonusPoker).setChecked(false);
			mnuOptions.findItem(R.id.ChangeGameJokerPoker).setChecked(false);
			switch (currentGame)
			{
			case JacksOrBetter:
				mnuOptions.findItem(R.id.ChangeGameJacksOrBetter).setChecked(true);
				break;
			case BonusPoker:
				mnuOptions.findItem(R.id.ChangeGameBonusPoker).setChecked(true);
				break;
			case DeucesWild:
				mnuOptions.findItem(R.id.ChangeGameDeucesWild).setChecked(true);
				break;
			case JokersWild:
				mnuOptions.findItem(R.id.ChangeGameJokerPoker).setChecked(true);
				break;
			}
	   }
	   
	   @Override
	   protected void onStop()
	   {
		   super.onStop();
		   SavePreferences();
	   }
}