package com.tsop.games.triplePlay;

//import java.awt.Font;
import java.util.ArrayList;

import com.tsop.games.triplePlay.games.BonusPokerGame;
import com.tsop.games.triplePlay.games.DeucesWildGame;
import com.tsop.games.triplePlay.games.GameBase;
import com.tsop.games.triplePlay.games.JacksOrBetterGame;
import com.tsop.games.triplePlay.games.JokersWildGame;

//import javax.swing.JTextField;

/**
 * This is a static utility class that contains helper functions for the TriplePlay application
 * @author Stan Dewan
 *
 */
public class Calculator
{
	// the indexes of this array must match the index of the Games enumeration
	private static GameBase games[] = new GameBase[] {new JacksOrBetterGame(), new BonusPokerGame(), new DeucesWildGame(), new JokersWildGame()};
	
	private Calculator()
	{

	}
	
	/**
	 * Method that returns the credit payout for a single hand
	 * @param game the game which is currently being played
	 * @param eval that hand of cards for which to evaluate
	 * @return the credits won (or 0)
	 */
	public static int EvalWinnings(Games game, Card eval[])
	{
		try
		{
			GameBase currentGame = games[game.getIndex()];
			return currentGame.evalWinnings(eval);
		}
		catch (Exception ex)
		{
			GameBase defaultGame = games[0];
			return defaultGame.evalWinnings(eval);
		}

	}

	/**
	 * Returns the name of the specified game
	 * @param game
	 * @return
	 */
	public static String GetGameName(Games game)
	{
		try
		{
			GameBase currentGame = games[game.getIndex()];
			return currentGame.getName();
		}
		catch (Exception ex)
		{
			GameBase defaultGame = games[0];
			return defaultGame.getName();
		}
	}
	
	/**
	 * Returns true if Jokers are required for the specified game
	 * @param game
	 * @return
	 */
	public static boolean JokersAreUsed(Games game)
	{
		try
		{
			GameBase currentGame = games[game.getIndex()];
			return currentGame.JokersAreUsed();
		}
		catch (Exception ex)
		{
			GameBase defaultGame = games[0];
			return defaultGame.JokersAreUsed();
		}
		
	}
	

	/**
	 * Method to evalute passed hand and return a String that describes what that hand is.  
	 * @param eval an array of cards to be evaluated for a winning hand
	 * @return a string describing whether the hand is a winning hand
	 */
   // 
   public static String getHighestHandString(Games game, Card eval[])
   {
		try
		{
			GameBase currentGame = games[game.getIndex()];
			return currentGame.evaluateHighestHand(eval).toString();
		}
		catch (Exception ex)
		{
			GameBase defaultGame = games[0];
			return defaultGame.evaluateHighestHand(eval).toString();
		}

   }
   
   public static ArrayList<WinningHand> getWinningHands(Games game)
   {
		try
		{
			GameBase currentGame = games[game.getIndex()];
			return currentGame.getWinningHands();
		}
		catch (Exception ex)
		{
			GameBase defaultGame = games[0];
			return defaultGame.getWinningHands();
		}
	   
   }
   
	// TODO: this doesn't exactly work.  Even with a winning hand being held, it returns less than
	// a 100% chance of getting that hand
   /**
    * given a hand of five cards that have already been dealt, and an indication of which of those cards will be kept,
    * this function evaluates the possibility of every winning hand resulting from the next draw
    * @param hand the 5-card hand that was dealt
    * @param keepCards a boolean array indicating which cards in the hand will be kept
    * @return returns an OddsTable object indicating the odds of each winning hand occurring
    */
//   public static OddsTable evaluateOdds(Hand hand, boolean[] keepCards)
//   {
//		// what are the chances of getting each payout?
//		
//		// (assuming I don't have a deck of the 47 remaining cards, create a new one:)
//		Deck dTemp = new Deck(52);
//		
//		Deck d = new Deck(47);
//		
//		int i = 0;
//		for (int j = 0; j < 52; j++)
//		{
//			if (!hand.contains(dTemp.getCards()[j]))
//				d.getCards()[i++] = dTemp.getCards()[j];
//				
//		}
//		
//		int keepCount = 0;
//		for (boolean keep : keepCards)
//			if (keep)
//				keepCount++;
//		
//		Card[] eval = new Card[5];
//		i = 0;
//		for (int j = 0; j < 5; j++)
//			if (keepCards[j] == true)
//				eval[i++] = hand.getCards()[j];
//		
//		OddsTable ot = new OddsTable();
//		for (int loopCounter = 0; loopCounter < 100000; loopCounter++)
//		{
//			// keep reusing the same 47-card deck
//			d.shuffle();
//			
////			for (int cardIt = 0; cardIt < 5; cardIt++)
////				if (!keepCards[cardIt])
////					eval[cardIt] = d.dealCard();
//			for (int cardIt = keepCount; cardIt < 5; cardIt++)
//			{
//				eval[cardIt] = d.dealCard();
//			}
//			
//			WinningHands[] wHands = Calculator.evaluateAllHands(eval);
////			System.out.println(wHand.toString());
//			ot.incrementNumOccurences(wHands);
//		}
//		
//		return ot;
//   }
//   
 

   /**
    * returns true if the hand contains a pair
    * @param h an array of cards to evaluate as a hand
    * @return true if a pair is present
    */
   public static boolean CheckIfPair (Card h[])
   {
      for(int i=1;  i < h.length; i++)
      {

        if (h[i].getFace().equals(h[i-1].getFace())) return true;

      }
      return false;
   }

   /**
    * returns true if the hand contains a jack or higher pair
    * @param h an array of cards to evaluate as a hand
    * @return true if a jack or higher pair is present
    */
   public static boolean CheckIfJacks (Card h[])    //Jacks or Better Test
   {
//      int value[] = new int[5];
//
//      //first assign a value to every face
//      for(int i=0; i <= 4; i++)
//      {
//        for(int j = 0; j <= 12; j++)
//        {
//          if (h[i].getFace().equals(Constants.faces[j])) value[i] = j+1;
//          if (h[i].getFace().equals("Ace")) value[i] = 14;
//        }   //end for j loop
//      } //end for i loop

      //then compare each pair & check values
      for(int i=1;  i <= 4; i++)
      {
        if ((h[i].getFace().equals(h[i-1].getFace())) && ((h[i].getFaceValue() > 10) || (h[i].getFaceAlternateValue() > 10)))
           return true;

      }

      return false;
   }

   /**
    * returns true if the hand contains two pair
    * @param h an array of cards to evaluate as a hand
    * @return true if two pairs are present
    */
   public static boolean CheckIfTwoPair (Card h[])
   {
	  if (h.length == 4)
	  {
		  if (h[0].getFace().equals(h[1].getFace())
				  && h[2].getFace().equals(h[3].getFace()))
				  return true;
	  }
	  else if (h.length == 5)
	  {
	      for(int i=1;  i <= 2; i++)
	      {
	
	        if
	          (h[i].getFace().equals(h[i-1].getFace()))
	             for (int j=i+1; j <= 3; j++)
	                if (h[j].getFace().equals(h[j+1].getFace()))
	                  return true;
	
	      }
	  }
      return false;
   }

   /**
    * returns true if the hand contains 3-of-a-kind
    * @param h an array of cards to evaluate as a hand
    * @return true if a 3-of-a-kind is present
    */
   public static boolean CheckIfThree (Card h[])
   {
	   if (h.length < 3)
		   return false;
      for(int i=2;  i < h.length; i++)
      {
         if
          (h[i].getFace().equals(h[i-1].getFace()) &&
          h[i-1].getFace().equals(h[i-2].getFace()))

         return true;

      }
      return false;
   }

   /**
    * returns true if the hand contains a full house
    * @param h an array of cards to evaluate as a hand
    * @return true if a full house is present
    */
   public static boolean CheckIfFullHouse (Card h[])
   {
      if (CheckIfTwoPair(h) &&
            (CheckIfThree(h)))
          return true;
      else
          return false;
   }

   /**
    * returns true if the hand contains 4-of-a-kind
    * @param h an array of cards to evaluate as a hand
    * @return true if a 4-of-a-kind is present
    */
   public static boolean CheckIfFour (Card h[])
   {
	   if (h.length < 4)
		   return false;
	   
      for(int i=3;  i <= h.length - 1; i++)
      {

          if
          (h[i].getFace().equals(h[i-1].getFace()) &&
          h[i-1].getFace().equals(h[i-2].getFace()) &&
          h[i-2].getFace().equals(h[i-3].getFace()))

         return true;


      }
      return false;
   }

   /**
    * returns true if the hand contains 4-of-a-kind (Aces)
    * @param h an array of cards to evaluate as a hand
    * @return true if a 4-of-a-kind is present
    */
   public static boolean CheckIfFourAces (Card h[])
   {
      for(int i=3;  i <= 4; i++)
      {

          if
          (h[i].getFace().equals(h[i-1].getFace()) &&
        		  h[i-1].getFace().equals(h[i-2].getFace()) &&
        		  h[i-2].getFace().equals(h[i-3].getFace()) &&
        		  h[i].getFace().equals(Faces.Ace))
        	  return true;


      }
      return false;
   }

   /**
    * returns true if the hand contains 4-of-a-kind (2's through 4's)
    * @param h an array of cards to evaluate as a hand
    * @return true if a 4-of-a-kind is present
    */
   public static boolean CheckIfFour2to4 (Card h[])
   {
      for(int i=3;  i <= 4; i++)
      {

          if
          (h[i].getFace().equals(h[i-1].getFace()) &&
        		  h[i-1].getFace().equals(h[i-2].getFace()) &&
        		  h[i-2].getFace().equals(h[i-3].getFace()) &&
        		  (h[i].getFaceValue() >= 2 && h[i].getFaceValue() <= 4))
        	  return true;


      }
      return false;
   }

   /**
    * returns true if the hand contains 4-of-a-kind (5's through K's)
    * @param h an array of cards to evaluate as a hand
    * @return true if a 4-of-a-kind is present
    */
   public static boolean CheckIfFour5toK (Card h[])
   {
      for(int i=3;  i <= 4; i++)
      {

          if
          (h[i].getFace().equals(h[i-1].getFace()) &&
        		  h[i-1].getFace().equals(h[i-2].getFace()) &&
        		  h[i-2].getFace().equals(h[i-3].getFace()) &&
        		  (h[i].getFaceValue() >= 5 && h[i].getFaceValue() <= 13))
        	  return true;


      }
      return false;
   }

   /**
    * returns true if the hand contains a straight
    * @param h an array of cards to evaluate as a hand
    * @return true if a straight is present
    */
   public static boolean CheckIfStraight (Card h[])
   {
//      int value[] = new int[5];
      //
      //First the test proceeds normally, assuming ACE = 1
      //
//      //first assign a value to every face
//      for(int i=0; i <= 4; i++)
//      {
//        for(int j = 0; j <= 12; j++)
//        {
//          if (h[i].getFace().equals(Constants.faces[j])) value[i] = j+1;
//        }   //end for j loop
//      } //end for i loop

      //Compare each element
      if
      ((h[1].getFaceValue() - h[0].getFaceValue() == 1) &&
        ((h[2].getFaceValue() - h[1].getFaceValue() == 1) &&
          ((h[3].getFaceValue() - h[2].getFaceValue() == 1) &&
            (h[4].getFaceValue() - h[3].getFaceValue() == 1))))

        return true;

      //Compare each element with Alternate values (assuming Ace = 14)
      if
      ((h[0].getFaceAlternateValue() - h[4].getFaceAlternateValue() == 1) &&    //since Ace would be in first position
        ((h[2].getFaceAlternateValue() - h[1].getFaceAlternateValue() == 1) &&
          ((h[3].getFaceAlternateValue() - h[2].getFaceAlternateValue() == 1) &&
            (h[4].getFaceAlternateValue() - h[3].getFaceAlternateValue() == 1))))

        return true;

      return false;

   }

   /**
    * returns true if the hand contains a flush
    * @param h an array of cards to evaluate as a hand
    * @return true if a flush is present
    */
   public static boolean CheckIfFlush (Card h[])
   {
      if
      ((h[0].getSuit().equals(h[1].getSuit())) &&
        ((h[1].getSuit().equals(h[2].getSuit())) &&
          ((h[2].getSuit().equals(h[3].getSuit())) &&
            (h[3].getSuit().equals(h[4].getSuit())))))

        return true;
      else
        return false;
   }

   /**
    * returns true if the hand contains a flush
    * @param h an array of cards to evaluate as a hand
    * @return true if a flush is present
    */
   public static boolean CheckIfOneDeuce (Card h[])
   {
      if
      	(CountDeuces(h) == 1)
    	  return true;
      else
    	  return false;
   }

   /**
    * returns true if the hand contains a flush
    * @param h an array of cards to evaluate as a hand
    * @return true if a flush is present
    */
   public static boolean CheckIfTwoDeuces (Card h[])
   {
      if
	    (CountDeuces(h) == 2)
    	  return true;
      else
        return false;
   }

   /**
    * returns true if the hand contains a flush
    * @param h an array of cards to evaluate as a hand
    * @return true if a flush is present
    */
   public static boolean CheckIfThreeDeuces (Card h[])
   {
      if
      (CountDeuces(h) == 3)
        return true;
      else
        return false;
   }

   /**
    * returns true if the hand contains a flush
    * @param h an array of cards to evaluate as a hand
    * @return true if a flush is present
    */
   public static boolean CheckIfFourDeuces (Card h[])
   {
      if
      (CountDeuces(h) == 4)
        return true;
      else
        return false;
   }
   
   /*
    *Count the number of deuces in the hand
    * 
    */
   private static int CountDeuces(Card h[])
   {
	   int result = 0;
	   for (int i=0; i < h.length; i++)
	   {
		   if (h[i].getFace().equals(Faces.Deuce))
			   result++;
	   }
	   return result;
   }
   
   /*
    *Count the number of jokers in the hand
    * 
    */
   private static int CountJokers(Card h[])
   {
	   int result = 0;
	   for (int i=0; i < h.length; i++)
	   {
		   if (h[i].getFace().equals(Faces.Joker))
			   result++;
	   }
	   return result;
   }
   
   private static Card[] GetNonDeuceCards(Card h[])
   {
	   ArrayList<Card> nonDeuces = new ArrayList<Card>();
	   for (Card card : h)
	   {
		   if (card.getFace().equals(Faces.Deuce))
			   continue;
		   nonDeuces.add(card);
	   }
	   Card cards[] = new Card[nonDeuces.size()];
	   nonDeuces.toArray(cards);
	   return cards;
   }
   
   private static Card[] GetNonJokerCards(Card h[])
   {
	   ArrayList<Card> nonJokers = new ArrayList<Card>();
	   for (Card card : h)
	   {
		   if (card.getFace().equals(Faces.Joker))
			   continue;
		   nonJokers.add(card);
	   }
	   Card cards[] = new Card[nonJokers.size()];
	   nonJokers.toArray(cards);
	   return cards;
   }
   
   private static boolean IsSameSuit(Card h[])
   {
	   // if only one card, then it's definitely the same suit
	   if (h.length <= 1)
		   return true;
	   // more than one card, so compare the suit of each card to the first one
	   Suits suit = h[0].getSuit();
	   for (int i=1; i < h.length; i++)
		   if (h[i].getSuit().compareTo(suit) != 0)
		   		return false;
	
	   return true;
   }
   
   private static boolean IsSameFace(Card h[])
   {
	   // if only one card, then it's definitely the same face
	   if (h.length <= 1)
		   return true;
	   // more than one card, so compare the face of each card to the first one
	   Faces face = h[0].getFace();
	   for (int i=1; i < h.length; i++)
		   if (h[i].getFace().compareTo(face) != 0)
		   		return false;
	
	   return true;
   }
   
   private static boolean AreAllCardsRoyal(Card h[])
   {
	   for (Card card : h)
	   {
		   if (card.getFaceAlternateValue() < 10)
			   return false;
	   }
	   return true;
   }

   /**
    * returns true if the hand contains a royal flush
    * @param h an array of cards to evaluate as a hand
    * @return true if a royal flush is present
    */
   public static boolean CheckIfRoyal (Card h[])
   {
      if (((CheckIfFlush(h)) &&
           (CheckIfStraight(h))) &&
           (h[0].getFace().equals(Faces.Ace) && h[4].getFace().equals(Faces.King)))
          return true;
      return false;
   }

   /**
    * returns true if the hand contains a royal flush using deuces as wild cards
    * @param h an array of cards to evaluate as a hand
    * @return true if a royal flush is present
    */
   public static boolean CheckIfWildDeucesRoyal (Card h[])
   {
	   // must be at least one deuce for this to true
	   if (CountDeuces(h) == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   // all the non-wild cards must be the same suit
	   if (!IsSameSuit(nonDeuces))
		   return false;
	   // all the non-wild cards must be royal
	   if (AreAllCardsRoyal(nonDeuces))
		   return true;
       return false;
   }
   
   /**
    * returns true if there are wild deuces in the hand resulting in 5 of kind
    * @param h an array of cards to evaluate as a hand
    * @return true if 5 of a kind is present
    */
   public static boolean CheckIfWildDeucesFive(Card h[])
   {
	   // must be at least one deuce for this to true
	   if (CountDeuces(h) == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   // all the non-wild cards must be the same face
	   if (IsSameFace(nonDeuces))
		   return true;
	   return false;
   }

   /**
    * returns true if the hand evaluates to a straight flush, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a straight flush is present
    */
   public static boolean CheckIfWildDeucesStraightFlush(Card h[])
   {
	   // test if this is a natural straight flush
	   if (CheckIfStraight(h) && CheckIfFlush(h))
		   return true;
	   // if not, there must be at least one wild deuce
	   if (CountDeuces(h) == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   // all the non-wild cards must be the same suit
	   if (!IsSameSuit(nonDeuces))
		   return false;
	   // if the non-deuces are of the same suit, they must be
	   // within 5 cards from the lowest to highest.  Assume Aces
	   // are valued at 1, since a high-Ace would have returned true
	   // as a royal flush
	   int lowValue = nonDeuces[0].getFaceValue();
	   int highValue = nonDeuces[nonDeuces.length - 1].getFaceValue();
	   if (highValue - lowValue < 5)
		   return true;
	   return false;
   }
 
   /**
    * returns true if the hand evaluates to 4 of a kind, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a straight flush is present
    */
   public static boolean CheckIfWildDeucesFourOfAKind(Card h[])
   {
	   // test if this is a natural four of a kind
	   if (CheckIfFour(h))
		   return true;
	   // if not, there must be at least one wild deuce
	   if (CountDeuces(h) == 0)
		   return false;
	   // if there are three deuces, then this is at least 4 of a kind
	   if (CheckIfThreeDeuces(h))
		   return true;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   // if there are two deuces, then there must be a pair in the non-deuces
	   if (CheckIfTwoDeuces(h) && CheckIfPair(nonDeuces))
		   return true;
	   // if there is one deuce, then there must be three of a kind in the non-deuces
	   if (CheckIfOneDeuce(h) && CheckIfThree(nonDeuces))
		   return true;
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to a full housed, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a full house is present
    */
   public static boolean CheckIfWildDeucesFullHouse(Card h[])
   {
	   // test if this is a natural full house
	   if (CheckIfFullHouse(h))
		   return true;
	   // if not, there must be only one wild deuce
	   if (CountDeuces(h) != 1)
		   return false;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   // if there is one deuce, then the non-wild cards must be two pair
	   if (CheckIfTwoPair(nonDeuces))
		   return true;
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to a flush, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a flush is present
    */
   public static boolean CheckIfWildDeucesFlush(Card h[])
   {
	   // test if this is a natural flush
	   if (CheckIfFlush(h))
		   return true;
	   // if not, there must be at least one wild deuce, but no more than two
	   if (CountDeuces(h) == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   // if there is at least one deuce, then the non-wild cards must be of the same suit
	   if (IsSameSuit(nonDeuces))
		   return true;
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to a straight, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a straight is present
    */
   public static boolean CheckIfWildDeucesStraight(Card h[])
   {
	   // test if this is a natural straight
	   if (CheckIfStraight(h))
		   return true;
	   // if not, there must be at least one wild deuce, but no more than three
	   int numDeuces = CountDeuces(h);
	   if (numDeuces == 0 || numDeuces > 3)
		   return false;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   // there cannot be any pairs or three-of-a-kinds among the non-deuces
	   if (CheckIfPair(nonDeuces) || CheckIfThree(nonDeuces))
		   return false;
	   // the sorted cards must fall with 5 of each other
	   int lowValue = nonDeuces[0].getFaceValue();
	   int highValue = nonDeuces[nonDeuces.length - 1].getFaceValue();
	   if (highValue - lowValue < 5)
		   return true;
	   if (nonDeuces[0].getFace().equals(Faces.Ace))
	   {
		   int altHighValue = nonDeuces[0].getFaceAlternateValue();
		   int altLowValue = nonDeuces[1].getFaceAlternateValue();
		   if (altHighValue - altLowValue < 5)
			   return true;
	   }
	   
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to three of a kind, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if three of a kind is present
    */
   public static boolean CheckIfWildDeucesThreeOfAKind(Card h[])
   {
	   // test if this is a natural three of a kind
	   if (CheckIfThree(h))
		   return true;
	   // if not, there must be at least one wild deuce, but no more than two
	   int numDeuces = CountDeuces(h);
	   if (numDeuces == 0 || numDeuces > 2)
		   return false;
	   // get the non-wild cards
	   Card[] nonDeuces = GetNonDeuceCards(h);
	   if (numDeuces == 2)
		   return true;
	   if (CheckIfPair(nonDeuces))
		   return true;
	   
	   return false;
   }

   /**
    * returns true if the hand evaluates to five of a kind using one or more Jokers
    * @param h an array of cards to evaluate as a hand
    * @return true if five of a kind is present
    */
   public static boolean CheckIfWildJokersFive(Card h[])
   {
	   // there must be at least one wild joker
	   int numJokers = CountJokers(h);
	   if (numJokers == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   if (numJokers == 1 && CheckIfFour(nonJokers))
		   return true;
	   if (numJokers == 2 && CheckIfThree(nonJokers))
		   return true;   
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to a royal flush using one or more Jokers
    * @param h an array of cards to evaluate as a hand
    * @return true if a wild royal flush is present
    */
   public static boolean CheckIfWildJokersRoyal(Card h[])
   {
	   // must be at least one joker for this to true
	   if (CountJokers(h) == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   // all the non-wild cards must be the same suit
	   if (!IsSameSuit(nonJokers))
		   return false;
	   // all the non-wild cards must be royal
	   if (AreAllCardsRoyal(nonJokers))
		   return true;
       return false;
   }
   
   /**
    * returns true if the hand evaluates to a straight flush, including the possibility of wild jokers
    * @param h an array of cards to evaluate as a hand
    * @return true if a straight flush is present
    */
   public static boolean CheckIfWildJokersStraightFlush(Card h[])
   {
	   // test if this is a natural straight flush
	   if (CheckIfStraight(h) && CheckIfFlush(h))
		   return true;
	   // if not, there must be at least one wild joker
	   if (CountJokers(h) == 0)
		   return false;
//	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   // all the non-wild cards must be the same suit
	   if (!IsSameSuit(nonJokers))
		   return false;
	   // if the non-jokers are of the same suit, they must be
	   // within 5 cards from the lowest to highest.  Assume Aces
	   // are valued at 1, since a high-Ace would have returned true
	   // as a royal flush
	   int lowValue = nonJokers[0].getFaceValue();
	   int highValue = nonJokers[nonJokers.length - 1].getFaceValue();
	   if (highValue - lowValue < 5)
		   return true;
	   return false;
   }
 
   /**
    * returns true if the hand evaluates to 4 of a kind, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a straight flush is present
    */
   public static boolean CheckIfWildJokersFourOfAKind(Card h[])
   {
	   // test if this is a natural four of a kind
	   if (CheckIfFour(h))
		   return true;
	   // if not, there must be at least one wild joker
	   int numJokers = CountJokers(h);
	   if (numJokers == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   // if there are two jokers, then there must be a pair in the non-jokers
	   if (numJokers == 2 && CheckIfPair(nonJokers))
		   return true;
	   // if there is one joker, then there must be three of a kind in the non-jokers
	   if (numJokers == 1 && CheckIfThree(nonJokers))
		   return true;
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to a full housed, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a full house is present
    */
   public static boolean CheckIfWildJokersFullHouse(Card h[])
   {
	   // test if this is a natural full house
	   if (CheckIfFullHouse(h))
		   return true;
	   // if not, there must be only one wild Joker
	   if (CountJokers(h) != 1)
		   return false;
	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   // if there is one joker, then the non-wild cards must be two pair
	   if (CheckIfTwoPair(nonJokers))
		   return true;
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to a flush, including the possibility of wild jokers
    * @param h an array of cards to evaluate as a hand
    * @return true if a flush is present
    */
   public static boolean CheckIfWildJokersFlush(Card h[])
   {
	   // test if this is a natural flush
	   if (CheckIfFlush(h))
		   return true;
	   // if not, there must be at least one wild joker, but no more than two
	   if (CountJokers(h) == 0)
		   return false;
	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   // if there is at least one joker, then the non-wild cards must be of the same suit
	   if (IsSameSuit(nonJokers))
		   return true;
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to a straight, including the possibility of wild deuces
    * @param h an array of cards to evaluate as a hand
    * @return true if a straight is present
    */
   public static boolean CheckIfWildJokersStraight(Card h[])
   {
	   // test if this is a natural straight
	   if (CheckIfStraight(h))
		   return true;
	   // if not, there must be at least one wild joker, but no more than three
	   int numJokers = CountJokers(h);
	   if (numJokers == 0 || numJokers > 3)
		   return false;
	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   // there cannot be any pairs or three-of-a-kinds among the non-jokers
	   if (CheckIfPair(nonJokers) || CheckIfThree(nonJokers))
		   return false;
	   // the sorted cards must fall with 5 of each other
	   int lowValue = nonJokers[0].getFaceValue();
	   int highValue = nonJokers[nonJokers.length - 1].getFaceValue();
	   if (highValue - lowValue < 5)
		   return true;
	   if (nonJokers[0].getFace().equals(Faces.Ace))
	   {
		   int altHighValue = nonJokers[0].getFaceAlternateValue();
		   int altLowValue = nonJokers[1].getFaceAlternateValue();
		   if (altHighValue - altLowValue < 5)
			   return true;
	   }
	   
	   return false;
   }
   
   /**
    * returns true if the hand evaluates to three of a kind, including the possibility of wild jokers
    * @param h an array of cards to evaluate as a hand
    * @return true if three of a kind is present
    */
   public static boolean CheckIfWildJokersThreeOfAKind(Card h[])
   {
	   // test if this is a natural three of a kind
	   if (CheckIfThree(h))
		   return true;
	   // if not, there must be at least one wild joker, but no more than two
	   int numJokers = CountJokers(h);
	   if (numJokers == 0 || numJokers > 2)
		   return false;
	   // get the non-wild cards
	   Card[] nonJokers = GetNonJokerCards(h);
	   if (numJokers == 2)
		   return true;
	   if (CheckIfPair(nonJokers))
		   return true;
	   
	   return false;
   }
  
   /**
    * returns true if the hand contains a kings or higher pair
    * @param h an array of cards to evaluate as a hand
    * @return true if a kings or higher pair is present
    */
   public static boolean CheckIfKingsOrBetter (Card h[])
   {
      //compare each pair of cards
      for(int i=1;  i <= 4; i++)
      {
        if ((h[i].getFace().equals(h[i-1].getFace())) && ((h[i].getFaceValue() > 12) || (h[i].getFaceAlternateValue() > 12)))
           return true;

      }

      return false;
   }

   /**
    * returns true if the hand contains a kings or higher pair with the possibility of jokers
    * @param h an array of cards to evaluate as a hand
    * @return true if a kings or higher pair is present
    */
   public static boolean CheckIfWildJokersKingsOrBetter (Card h[])    //Jacks or Better Test
   {
      //check if this is a natural kings or better
	  if (CheckIfKingsOrBetter(h))
		  return true;
	  // if not, there must be exactly one joker
	  if (CountJokers(h) != 1)
		  return false;
	  // if there is one joker, then there must be a king or ace in the deck
	  for (int i=1; i <= 4; i++)
	  {
		  if (h[i].getFaceAlternateValue() > 12)
			  return true;
	  }
      return false;
   }

}
