package com.tsop.games.triplePlay.games;

import java.util.ArrayList;

import com.tsop.games.triplePlay.Calculator;
import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.WinningHand;
import com.tsop.games.triplePlay.WinningHands;

public class DeucesWildGame extends GameBase {

	public DeucesWildGame()
	{
		name = "Deuces Wild Poker";
		winningHands = new ArrayList<WinningHand>();
		winningHands.add(new WinningHand(0, "Nothing", WinningHands.Nothing, 0));
		winningHands.add(new WinningHand(1, "Three Of A Kind", WinningHands.ThreeOfAKind, 1));
		winningHands.add(new WinningHand(2, "Straight", WinningHands.Straight, 2));
		winningHands.add(new WinningHand(2, "Flush", WinningHands.Flush, 3));
		winningHands.add(new WinningHand(3, "Full House", WinningHands.FullHouse, 4));
		winningHands.add(new WinningHand(5, "Four Of A Kind", WinningHands.FourOfAKind, 5));
		winningHands.add(new WinningHand(9, "Straight Flush", WinningHands.StraightFlush, 6));
		winningHands.add(new WinningHand(15, "Five Of A Kind", WinningHands.FiveOfAKind, 7));
		winningHands.add(new WinningHand(25, "Wild Royal Flush", WinningHands.WildRoyalFlush, 8));
		winningHands.add(new WinningHand(200, "Four Deuces", WinningHands.FourDeuces, 9));
		winningHands.add(new WinningHand(800, "Natural Royal", WinningHands.RoyalFlush, 10));
		}
	
	@Override
	public int evalWinnings(Card[] eval) {
	       if (Calculator.CheckIfRoyal(eval))
	    	   return processWinningHand(WinningHands.RoyalFlush);
	       if (Calculator.CheckIfFourDeuces(eval))
	    	   return processWinningHand(WinningHands.FourDeuces);
	       if (Calculator.CheckIfWildDeucesRoyal(eval))
	    	   return processWinningHand(WinningHands.WildRoyalFlush);
	       if (Calculator.CheckIfWildDeucesFive(eval))
	    	   return processWinningHand(WinningHands.FiveOfAKind);
	       if (Calculator.CheckIfWildDeucesStraightFlush(eval))
	    	   return processWinningHand(WinningHands.StraightFlush);
	       if (Calculator.CheckIfWildDeucesFourOfAKind(eval))
	    	   return processWinningHand(WinningHands.FourOfAKind);
	       if (Calculator.CheckIfWildDeucesFullHouse(eval))
	    	   return processWinningHand(WinningHands.FullHouse);
	       if (Calculator.CheckIfWildDeucesFlush(eval))
	    	   return processWinningHand(WinningHands.Flush);
	       if (Calculator.CheckIfWildDeucesStraight(eval))
	    	   return processWinningHand(WinningHands.Straight);
	       if (Calculator.CheckIfWildDeucesThreeOfAKind(eval))
	    	   return processWinningHand(WinningHands.ThreeOfAKind);
	       return processWinningHand(WinningHands.Nothing);
	}

}
