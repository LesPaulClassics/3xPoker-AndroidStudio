package com.tsop.games.triplePlay.test;

import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.Faces;
import com.tsop.games.triplePlay.Hand;
import com.tsop.games.triplePlay.Suits;
import com.tsop.games.triplePlay.WinningHands;
import com.tsop.games.triplePlay.games.DeucesWildGame;
import com.tsop.games.triplePlay.games.GameBase;

import junit.framework.TestCase;

public class DeucesWildGameTester extends GameTesterBase {

	public void testEvaluateHighestHand() {
		
		// Natural Royal Flush
		cards = TestUtil.GetTestHand(WinningHands.RoyalFlush);
		TestPositiveOutcome("Natural Royal Flush");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Deuce, Suits.Clubs);
		TestNegativeOutcome("Natural Royal Flush");
		
		// Four Deuces
		cards = TestUtil.GetTestHand(WinningHands.FourDeuces);
		TestPositiveOutcome("Four Deuces");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Five, Suits.Clubs);
		TestNegativeOutcome("Four Deuces");
		
		// Wild Royal Flush
		cards = TestUtil.GetTestHand(WinningHands.WildRoyalFlush);
		TestPositiveOutcome("Wild Royal Flush");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Five, Suits.Clubs);
		TestNegativeOutcome("Wild Royal Flush");
		
		// Wild Five Of A Kind
		cards = TestUtil.GetTestHand(WinningHands.FiveOfAKind);
		TestPositiveOutcome("Five Of A Kind");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Five, Suits.Clubs);
		TestNegativeOutcome("Five Of A Kind");
		
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
		cards = TestUtil.ChangeCard(cards, 2, Faces.Deuce, Suits.Clubs);
		TestPositiveOutcome("Straight Flush");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Deuce, Suits.Diamonds);
		TestPositiveOutcome("Straight Flush");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Four, Suits.Diamonds);
		TestNegativeOutcome("Straight Flush");
		// Aces High (Royal Flush)
		cards = TestUtil.GetTestHand(Faces.Ace, Suits.Hearts, Faces.Queen, Suits.Hearts, Faces.Jack, Suits.Hearts, Faces.Ten, Suits.Hearts, Faces.King, Suits.Hearts);
		cards = TestUtil.ChangeCard(cards, 4, Faces.Deuce, Suits.Clubs);
		TestNegativeOutcome("Straight Flush");
		// Aces Low
		cards = TestUtil.GetTestHand(Faces.Ace, Suits.Hearts, Faces.Deuce, Suits.Clubs, Faces.Four, Suits.Hearts, Faces.Deuce, Suits.Hearts, Faces.Deuce, Suits.Diamonds);
		TestPositiveOutcome("Straight Flush");
		
		// Four Of A Kind
		// natural
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		TestPositiveOutcome("Four Of A Kind");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Seven, Suits.Clubs);
		TestNegativeOutcome("Four Of A Kind");
		// with wild cards
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Deuce, Suits.Clubs);
		TestPositiveOutcome("Four Of A Kind");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Deuce, Suits.Diamonds);
		TestNegativeOutcome("Four Of A Kind");
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Deuce, Suits.Diamonds);
		cards = TestUtil.ChangeCard(cards, 1, Faces.Deuce, Suits.Clubs);
		TestPositiveOutcome("Four Of A Kind");
		cards = TestUtil.ChangeCard(cards, 2, Faces.Deuce, Suits.Spades);
		TestPositiveOutcome("Four Of A Kind");
		cards = TestUtil.ChangeCard(cards, 3, Faces.Deuce, Suits.Hearts);
		TestNegativeOutcome("Four Of A Kind");
		
		// Full House
		cards = TestUtil.GetTestHand(WinningHands.FullHouse);
		TestPositiveOutcome("Full House");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Seven, Suits.Clubs);
		TestNegativeOutcome("Full House");
		// with Deuces
		cards = TestUtil.GetTestHand(WinningHands.FullHouse);
		cards = TestUtil.ChangeCard(cards, 4, Faces.Deuce, Suits.Clubs);
		TestPositiveOutcome("Full House");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Deuce, Suits.Hearts);
		TestNegativeOutcome("Full House");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Deuce, Suits.Spades);
		TestNegativeOutcome("Full House");
		cards = TestUtil.GetTestHand(Faces.Deuce, Suits.Hearts, Faces.Deuce, Suits.Spades, Faces.Deuce, Suits.Diamonds, Faces.Nine, Suits.Clubs, Faces.Nine, Suits.Hearts);
		TestNegativeOutcome("Full House");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Five, Suits.Clubs);
		TestNegativeOutcome("Full House");
		
		// Flush
		cards = TestUtil.GetTestHand(WinningHands.Flush);
		TestPositiveOutcome("Flush");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Three, Suits.Diamonds);
		TestNegativeOutcome("Flush");
		cards = TestUtil.GetTestHand(WinningHands.Flush);
		cards = TestUtil.ChangeCard(cards, 0, Faces.Deuce, Suits.Diamonds);
		TestPositiveOutcome("Flush");
		cards = TestUtil.ChangeCard(cards, 1, Faces.Deuce, Suits.Hearts);
		TestPositiveOutcome("Flush");
		cards = TestUtil.ChangeCard(cards, 2, Faces.Deuce, Suits.Clubs);
		TestNegativeOutcome("Flush");
		
		// Straight
		cards = TestUtil.GetTestHand(WinningHands.Straight);	// ace-low
		TestPositiveOutcome("Straight");
		cards = TestUtil.ChangeCard(cards, 0, Faces.Deuce, Suits.Hearts);
		TestPositiveOutcome("Straight");
		cards = TestUtil.ChangeCard(cards, 4, Faces.Deuce, Suits.Spades);
		TestNegativeOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Ten, Suits.Clubs, Faces.Jack, Suits.Hearts, Faces.Queen, Suits.Diamonds, Faces.King, Suits.Spades, Faces.Ace, Suits.Diamonds);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Five, Suits.Clubs, Faces.Deuce, Suits.Hearts, Faces.Seven, Suits.Diamonds, Faces.Deuce, Suits.Spades, Faces.Nine, Suits.Diamonds);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Deuce, Suits.Clubs, Faces.Deuce, Suits.Hearts, Faces.Seven, Suits.Diamonds, Faces.Eight, Suits.Spades, Faces.Nine, Suits.Diamonds);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Seven, Suits.Clubs, Faces.Eight, Suits.Hearts, Faces.Nine, Suits.Diamonds, Faces.Deuce, Suits.Spades, Faces.Deuce, Suits.Diamonds);
		TestPositiveOutcome("Straight");
		cards = TestUtil.GetTestHand(Faces.Seven, Suits.Clubs, Faces.Eight, Suits.Hearts, Faces.Queen, Suits.Diamonds, Faces.Deuce, Suits.Spades, Faces.Deuce, Suits.Diamonds);
		TestNegativeOutcome("Straight");
		
		// Three Of A Kind
		cards = TestUtil.GetTestHand(WinningHands.ThreeOfAKind);
		TestPositiveOutcome("Three Of A Kind");
		cards = TestUtil.ChangeCard(cards, 0, Faces.King, Suits.Clubs);
		TestNegativeOutcome("Three Of A Kind");
		cards = TestUtil.GetTestHand(Faces.Deuce, Suits.Clubs, Faces.Five, Suits.Spades, Faces.Five, Suits.Hearts, Faces.Nine, Suits.Clubs, Faces.Ace, Suits.Spades);
		TestPositiveOutcome("Three Of A Kind");
		cards = TestUtil.ChangeCard(cards, 2, Faces.Deuce, Suits.Hearts);
		TestPositiveOutcome("Three Of A Kind");
	}
	
	public void testEvalWinnings() {
		
		cards = TestUtil.GetTestHand(WinningHands.RoyalFlush);
		assertEquals(game.evalWinnings(cards), 800);
		cards = TestUtil.GetTestHand(WinningHands.FourDeuces);
		assertEquals(game.evalWinnings(cards), 200);
		cards = TestUtil.GetTestHand(WinningHands.FiveOfAKind);
		assertEquals(game.evalWinnings(cards), 15);
		cards = TestUtil.GetTestHand(WinningHands.WildRoyalFlush);
		assertEquals(game.evalWinnings(cards), 25);
		cards = TestUtil.GetTestHand(WinningHands.StraightFlush);
		assertEquals(game.evalWinnings(cards), 9);
		cards = TestUtil.GetTestHand(WinningHands.FourOfAKind);
		assertEquals(game.evalWinnings(cards), 5);
		cards = TestUtil.GetTestHand(WinningHands.FullHouse);
		assertEquals(game.evalWinnings(cards), 3);
		cards = TestUtil.GetTestHand(WinningHands.Flush);
		assertEquals(game.evalWinnings(cards), 2);
		cards = TestUtil.GetTestHand(WinningHands.Straight);
		assertEquals(game.evalWinnings(cards), 2);
		cards = TestUtil.GetTestHand(WinningHands.ThreeOfAKind);
		assertEquals(game.evalWinnings(cards), 1);
	}


	public void testProcessWinningHand() {
		fail("Not yet implemented");
	}

	public void testGetWinningHands() {
		fail("Not yet implemented");
	}

}
