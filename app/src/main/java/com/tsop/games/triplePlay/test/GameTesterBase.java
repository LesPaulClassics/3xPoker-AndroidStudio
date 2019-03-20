package com.tsop.games.triplePlay.test;

import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.games.DeucesWildGame;
import com.tsop.games.triplePlay.games.GameBase;

import junit.framework.TestCase;

public class GameTesterBase extends TestCase 
{
	protected GameBase game = new DeucesWildGame();
	protected Card[] cards = new Card[5];
	
	protected void TestPositiveOutcome(String resultString)
	{
		assertEquals(game.evaluateHighestHand(cards), resultString);
		System.out.println(GetCardsString() + " is a " + resultString);
	}
	
	protected void TestNegativeOutcome(String resultString)
	{
		assertNotSame(game.evaluateHighestHand(cards), resultString);
		System.out.println(GetCardsString() + " is NOT a " + resultString);
	}
	
	protected String GetCardsString()
	{
		StringBuilder sBldr = new StringBuilder();
		sBldr.append("[");
		for (int i = 0; i < cards.length; i++)
		{
			sBldr.append(cards[i].toString());
			if (i < cards.length - 1)
				sBldr.append(", ");
		}
		sBldr.append("]");
		return sBldr.toString();
	}
}
