package com.tsop.games.triplePlay.games;

import java.util.ArrayList;

import com.tsop.games.triplePlay.Calculator;
import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.WinningHand;
import com.tsop.games.triplePlay.WinningHands;

public class JokersWildGame extends GameBase {

	public JokersWildGame()
	{
		name = "Jokers Wild Poker";
		useJokers = true;
		winningHands = new ArrayList<WinningHand>();
		winningHands.add(new WinningHand(0, "Nothing", WinningHands.Nothing, 0));
		winningHands.add(new WinningHand(1, "Kings Or Better", WinningHands.KingsOrHigher, 1));
		winningHands.add(new WinningHand(1, "Two Pair", WinningHands.TwoPair, 2));
		winningHands.add(new WinningHand(2, "Three Of A Kind", WinningHands.ThreeOfAKind, 3));
		winningHands.add(new WinningHand(3, "Straight", WinningHands.Straight, 4));
		winningHands.add(new WinningHand(5, "Flush", WinningHands.Flush, 5));
		winningHands.add(new WinningHand(7, "Full House", WinningHands.FullHouse, 6));
		winningHands.add(new WinningHand(17, "Four Of A Kind", WinningHands.FourOfAKind, 7));
		winningHands.add(new WinningHand(50, "Straight Flush", WinningHands.StraightFlush, 8));
		winningHands.add(new WinningHand(100, "Wild Royal Flush", WinningHands.WildRoyalFlush, 9));
		winningHands.add(new WinningHand(200, "Five Of A Kind", WinningHands.FiveOfAKind, 10));
		winningHands.add(new WinningHand(800, "Natural Royal", WinningHands.RoyalFlush, 11));		
	}
	
	@Override
	public int evalWinnings(Card[] eval) {
	       if (Calculator.CheckIfRoyal(eval))
	    	   return processWinningHand(WinningHands.RoyalFlush);
	       if (Calculator.CheckIfWildJokersFive(eval))
	    	   return processWinningHand(WinningHands.FiveOfAKind);
	       if (Calculator.CheckIfWildJokersRoyal(eval))
	    	   return processWinningHand(WinningHands.WildRoyalFlush);
	       if (Calculator.CheckIfWildJokersStraightFlush(eval))
	    	   return processWinningHand(WinningHands.StraightFlush);
	       if (Calculator.CheckIfWildJokersFourOfAKind(eval))
	    	   return processWinningHand(WinningHands.FourOfAKind);
	       if (Calculator.CheckIfWildJokersFullHouse(eval))
	    	   return processWinningHand(WinningHands.FullHouse);
	       if (Calculator.CheckIfWildJokersFlush(eval))
	    	   return processWinningHand(WinningHands.Flush);
	       if (Calculator.CheckIfWildJokersStraight(eval))
	    	   return processWinningHand(WinningHands.Straight);
	       if (Calculator.CheckIfWildJokersThreeOfAKind(eval))
	    	   return processWinningHand(WinningHands.ThreeOfAKind);
	       if (Calculator.CheckIfTwoPair(eval))	// cannot have two pair with jokers!
	    	   return processWinningHand(WinningHands.TwoPair);
	       if (Calculator.CheckIfWildJokersKingsOrBetter(eval))
	    	   return processWinningHand(WinningHands.KingsOrHigher);
	       return processWinningHand(WinningHands.Nothing);	}

}
