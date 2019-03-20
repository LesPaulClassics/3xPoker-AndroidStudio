package com.tsop.games.triplePlay.games;

import java.util.ArrayList;

import com.tsop.games.triplePlay.Calculator;
import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.WinningHand;
import com.tsop.games.triplePlay.WinningHands;

public class BonusPokerGame extends GameBase 
{

	public BonusPokerGame()
	{
		name = "Bonus Poker";
		winningHands = new ArrayList<WinningHand>();
		winningHands.add(new WinningHand(0, "Nothing", WinningHands.Nothing, 0));
		winningHands.add(new WinningHand(1, "Jacks Or Higher", WinningHands.JacksOrHigher, 1));
		winningHands.add(new WinningHand(2, "Two Pair", WinningHands.TwoPair, 2));
		winningHands.add(new WinningHand(3, "Three Of A Kind", WinningHands.ThreeOfAKind, 3));
		winningHands.add(new WinningHand(4, "Straight", WinningHands.Straight, 4));
		winningHands.add(new WinningHand(5, "Flush", WinningHands.Flush, 5));
		winningHands.add(new WinningHand(8, "Full House", WinningHands.FullHouse, 6));
		winningHands.add(new WinningHand(25, "Four 5s to Ks", WinningHands.Four5toK, 7));
		winningHands.add(new WinningHand(40, "Four 2s to 4s", WinningHands.Four2to4, 8));
		winningHands.add(new WinningHand(80, "Four Aces", WinningHands.FourAces, 9));
		winningHands.add(new WinningHand(50, "Straight Flush", WinningHands.StraightFlush, 10));
		winningHands.add(new WinningHand(800, "Royal Flush", WinningHands.RoyalFlush, 11));
	}
	
	@Override
	public int evalWinnings(Card[] eval) 
	{
	       if (Calculator.CheckIfRoyal(eval))
	    	   return processWinningHand(WinningHands.RoyalFlush);
	       if ((Calculator.CheckIfFlush(eval)) &&
	           (Calculator.CheckIfStraight(eval)))
	    	   return processWinningHand(WinningHands.StraightFlush);
	       if (Calculator.CheckIfFourAces(eval))
	    	   return processWinningHand(WinningHands.FourAces);
	       if (Calculator.CheckIfFour2to4(eval))
	    	   return processWinningHand(WinningHands.Four2to4);
	       if (Calculator.CheckIfFour5toK(eval))
	    	   return processWinningHand(WinningHands.Four5toK);
	       if (Calculator.CheckIfFullHouse(eval))
	    	   return processWinningHand(WinningHands.FullHouse);
	       if (Calculator.CheckIfFlush(eval))
	    	   return processWinningHand(WinningHands.Flush);
	       if (Calculator.CheckIfStraight(eval))
	    	   return processWinningHand(WinningHands.Straight);
	       if (Calculator.CheckIfThree(eval))
	    	   return processWinningHand(WinningHands.ThreeOfAKind);
	       if (Calculator.CheckIfTwoPair(eval))
	    	   return processWinningHand(WinningHands.TwoPair);
	       if (Calculator.CheckIfJacks(eval))
	    	   return processWinningHand(WinningHands.JacksOrHigher);
	       return processWinningHand(WinningHands.Nothing);
	}

	
}
