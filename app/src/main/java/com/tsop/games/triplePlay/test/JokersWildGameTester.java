package com.tsop.games.triplePlay.test;

import com.tsop.games.triplePlay.Faces;
import com.tsop.games.triplePlay.Games;
import com.tsop.games.triplePlay.Suits;
import com.tsop.games.triplePlay.WinningHands;
import com.tsop.games.triplePlay.games.GameBase;
import com.tsop.games.triplePlay.games.JokersWildGame;

import junit.framework.TestCase;

public class JokersWildGameTester extends GameTesterBase {
	
	public JokersWildGameTester()
	{
		game = new JokersWildGame();
	}
	
	public void testEvaluateHighestHand() {
		
		// Natural Royal Flush
		cards = TestUtil.GetTestHand(WinningHands.RoyalFlush);
		TestPositiveOutcome("Natural Royal Flush");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Deuce, Suits.Clubs);
		TestNegativeOutcome("Natural Royal Flush");
		
		// Wild Five Of A Kind
		cards = TestUtil.GetTestHand(Faces.Eight, Suits.Diamonds,
				Faces.Eight, Suits.Clubs,
				Faces.Eight, Suits.Hearts,
				Faces.Joker, Suits.None,
				Faces.Joker, Suits.None);
		TestPositiveOutcome("Five Of A Kind");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Eight, Suits.Spades);
		TestPositiveOutcome("Five Of A Kind");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Five, Suits.Spades);
		TestNegativeOutcome("Five Of A Kind");
				
		// Wild Royal Flush
		cards = TestUtil.GetTestHand(WinningHands.WildRoyalFlush);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Ace, Suits.Clubs);
		cards = TestUtil.ChangeCard(cards, 1, Faces.Joker, Suits.None);
		TestPositiveOutcome("Wild Royal Flush");
		cards = TestUtil.GetTestHand(Faces.Ace, Suits.Hearts, Faces.Queen, Suits.Hearts, Faces.Jack, Suits.Hearts, Faces.Joker, Suits.None, Faces.Joker, Suits.None);
		TestPositiveOutcome("Wild Royal Flush");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Five, Suits.Clubs);
		TestNegativeOutcome("Wild Royal Flush");
		
		// Straight Flush (Natural)
		cards = TestUtil.GetTestHand(WinningHands.StraightFlush);
		TestPositiveOutcome("Straight Flush");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Four, Suits.Diamonds);
		TestNegativeOutcome("Straight Flush");
		// Aces High (Royal Flush)
		cards = TestUtil.GetTestHand(Faces.Ace, Suits.Hearts, Faces.Queen, Suits.Hearts, Faces.Jack, Suits.Hearts, Faces.Ten, Suits.Hearts, Faces.King, Suits.Hearts);
		TestNegativeOutcome("Straight Flush");
		// Aces Low
		cards = TestUtil.GetTestHand(Faces.Ace, Suits.Hearts, Faces.Five, Suits.Hearts, Faces.Four, Suits.Hearts, Faces.Deuce, Suits.Hearts, Faces.Three, Suits.Hearts);
		TestPositiveOutcome("Straight Flush");

		// Straight Flush (Wild)
		cards = TestUtil.GetTestHand(WinningHands.StraightFlush);
		cards = TestUtil.ChangeCard(cards, 2, Faces.Joker, Suits.None);
		TestPositiveOutcome("Straight Flush");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Joker, Suits.None);
		TestPositiveOutcome("Straight Flush");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Four, Suits.Diamonds);
		TestNegativeOutcome("Straight Flush");
		// Aces High (Royal Flush)
		cards = TestUtil.GetTestHand(Faces.Ace, Suits.Hearts, Faces.Queen, Suits.Hearts, Faces.Jack, Suits.Hearts, Faces.Ten, Suits.Hearts, Faces.King, Suits.Hearts);
		cards = TestUtil.ChangeCard(cards, 4, Faces.Joker, Suits.None);
		TestNegativeOutcome("Straight Flush");
		// Aces Low
		cards = TestUtil.GetTestHand(Faces.Ace, Suits.Hearts, Faces.Joker, Suits.None, Faces.Four, Suits.Hearts, Faces.Joker, Suits.None, Faces.Deuce, Suits.Hearts);
		TestPositiveOutcome("Straight Flush");
		
		// Four Of A Kind
		// natural
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		TestPositiveOutcome("Four Of A Kind");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Seven, Suits.Clubs);
		TestNegativeOutcome("Four Of A Kind");
		// with wild cards
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Joker, Suits.None);
		TestPositiveOutcome("Four Of A Kind");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Joker, Suits.None);
		TestNegativeOutcome("Four Of A Kind");
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Joker, Suits.None);
		cards = TestUtil.ChangeCard(cards, 1, Faces.Joker, Suits.None);
		TestPositiveOutcome("Four Of A Kind");
		
		// Full House
		cards = TestUtil.GetTestHand(WinningHands.FullHouse);
		TestPositiveOutcome("Full House");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Seven, Suits.Clubs);
		TestNegativeOutcome("Full House");
		// with Jokers
		cards = TestUtil.GetTestHand(WinningHands.FullHouse);
		cards = TestUtil.ChangeCard(cards, 4, Faces.Joker, Suits.None);
		TestPositiveOutcome("Full House");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Joker, Suits.None);
		TestNegativeOutcome("Full House");
		cards = TestUtil.GetTestHand(Faces.Joker, Suits.None, Faces.Joker, Suits.None, Faces.Deuce, Suits.Diamonds, Faces.Nine, Suits.Clubs, Faces.Nine, Suits.Hearts);
		TestNegativeOutcome("Full House");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Five, Suits.Clubs);
		TestNegativeOutcome("Full House");
		
		// Flush
		cards = TestUtil.GetTestHand(WinningHands.Flush);
		TestPositiveOutcome("Flush");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Three, Suits.Diamonds);
		TestNegativeOutcome("Flush");
		cards = TestUtil.GetTestHand(WinningHands.Flush);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Joker, Suits.None);
		TestPositiveOutcome("Flush");
		cards = TestUtil.ChangeCard(cards, 1, Faces.Joker, Suits.None);
		TestPositiveOutcome("Flush");
		cards = TestUtil.ChangeCard(cards, 2, Faces.Deuce, Suits.Diamonds);
		TestNegativeOutcome("Flush");
		
		// Straight
		cards = TestUtil.GetTestHand(WinningHands.Straight);	// ace-low
		TestPositiveOutcome("Straight");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Joker, Suits.None);
		TestPositiveOutcome("Straight");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Joker, Suits.None);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Ten, Suits.Clubs, Faces.Jack, Suits.Hearts, Faces.Queen, Suits.Diamonds, Faces.King, Suits.Spades, Faces.Ace, Suits.Diamonds);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Five, Suits.Clubs, Faces.Deuce, Suits.Hearts, Faces.Seven, Suits.Diamonds, Faces.Joker, Suits.None, Faces.Nine, Suits.Diamonds);
		cards = TestUtil.ChangeCard(cards, 1, Faces.Joker, Suits.None);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Joker, Suits.None, Faces.Joker, Suits.None, Faces.Seven, Suits.Diamonds, Faces.Eight, Suits.Spades, Faces.Nine, Suits.Diamonds);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Seven, Suits.Clubs, Faces.Eight, Suits.Hearts, Faces.Nine, Suits.Diamonds, Faces.Joker, Suits.None, Faces.Joker, Suits.None);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Seven, Suits.Clubs, Faces.Eight, Suits.Hearts, Faces.Queen, Suits.Diamonds, Faces.Joker, Suits.None, Faces.Joker, Suits.None);
		TestNegativeOutcome("Straight");
		
		// Three Of A Kind
		cards = TestUtil.GetTestHand(WinningHands.ThreeOfAKind);
		TestPositiveOutcome("Three Of A Kind");
		cards = TestUtil.ChangeCard(cards, 0, Faces.King, Suits.Clubs);
		TestNegativeOutcome("Three Of A Kind");
		cards = TestUtil.GetTestHand(Faces.Joker, Suits.None, Faces.Five, Suits.Spades, Faces.Five, Suits.Hearts, Faces.Nine, Suits.Clubs, Faces.Ace, Suits.Spades);
		TestPositiveOutcome("Three Of A Kind");
		cards = TestUtil.ChangeCard(cards, 2, Faces.Joker, Suits.None);
		TestPositiveOutcome("Three Of A Kind");
		
		// Two Pair
		cards = TestUtil.GetTestHand(WinningHands.TwoPair);
		TestPositiveOutcome("Two Pair");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Joker, Suits.None);
		TestNegativeOutcome("Two Pair");
		
		// Kings Or Better
		cards = TestUtil.GetTestHand(WinningHands.KingsOrHigher);
		TestPositiveOutcome("Kings Or Better");
		cards = TestUtil.GetTestHand(WinningHands.Nothing);
		TestNegativeOutcome("Kings Or Better");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Joker, Suits.None);
		TestPositiveOutcome("Kings Or Better");
		cards = TestUtil.ChangeCard(cards, 1, Faces.King, Suits.Clubs);
		TestPositiveOutcome("Kings Or Better");
		
	}
	
	public void testEvalWinnings() {
		
		cards = TestUtil.GetTestHand(WinningHands.RoyalFlush);
		assertEquals(game.evalWinnings(cards), 800);
		cards = TestUtil.GetTestHand(Faces.Eight, Suits.Diamonds,
				Faces.Eight, Suits.Clubs,
				Faces.Eight, Suits.Hearts,
				Faces.Joker, Suits.None,
				Faces.Joker, Suits.None);
		assertEquals(game.evalWinnings(cards), 200);
		cards = TestUtil.GetTestHand(WinningHands.WildRoyalFlush);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Ace, Suits.Clubs);
		cards = TestUtil.ChangeCard(cards, 1, Faces.Joker, Suits.None);
		assertEquals(game.evalWinnings(cards), 100);
		cards = TestUtil.GetTestHand(WinningHands.StraightFlush);
		assertEquals(game.evalWinnings(cards), 50);
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		assertEquals(game.evalWinnings(cards), 17);
		cards = TestUtil.GetTestHand(WinningHands.FullHouse);
		assertEquals(game.evalWinnings(cards), 7);
	}

}
	
