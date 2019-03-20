package com.tsop.games.triplePlay.games;

import java.util.ArrayList;

import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.WinningHand;
import com.tsop.games.triplePlay.WinningHands;

public abstract class GameBase 
{
	public abstract int evalWinnings(Card eval[]);
	
	protected String highestHandString;
	
	protected ArrayList<WinningHand> winningHands;
	
	protected boolean useJokers = false;
	
	protected String name = "Video Poker";
	
	public String getName()
	{
		return name;
	}
	
	public boolean JokersAreUsed()
	{
		return useJokers;
	}
	
	public String evaluateHighestHand(Card[] eval) 
	{
		evalWinnings(eval);
		return highestHandString;
	}
	
	public WinningHand getHandByType(WinningHands type)
	{
		for (WinningHand hand : winningHands)
		{
			if (hand.getType() == type)
				return hand;
		}
		return null;
	}
	
	public int processWinningHand(WinningHands type)
	{
		WinningHand hand = getHandByType(type);
 	   	highestHandString = hand.toString();
 	   	return hand.getPayout();
	}
	
	public ArrayList<WinningHand> getWinningHands()
	{
		return winningHands;
	}
	

}
