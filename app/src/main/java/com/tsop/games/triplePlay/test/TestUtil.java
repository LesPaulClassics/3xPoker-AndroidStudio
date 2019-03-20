package com.tsop.games.triplePlay.test;

import com.tsop.games.triplePlay.Card;
import com.tsop.games.triplePlay.Faces;
import com.tsop.games.triplePlay.Hand;
import com.tsop.games.triplePlay.Suits;
import com.tsop.games.triplePlay.WinningHands;

public class TestUtil 
{
	public static Card[] GetTestHand(WinningHands type)
	{
		switch (type)
		{
		case RoyalFlush:
			return GetTestHand(Faces.Ten, Suits.Clubs,
					Faces.Jack, Suits.Clubs,
					Faces.Queen, Suits.Clubs,
					Faces.King, Suits.Clubs,
					Faces.Ace, Suits.Clubs);
		case FourDeuces:
			return GetTestHand(Faces.Deuce, Suits.Clubs,
					Faces.Deuce, Suits.Diamonds,
					Faces.Ten, Suits.Diamonds,
					Faces.Deuce, Suits.Hearts,
					Faces.Deuce, Suits.Spades);
		case StraightFlush:
			return GetTestHand(Faces.Ten, Suits.Clubs,
					Faces.Jack, Suits.Clubs,
					Faces.Queen, Suits.Clubs,
					Faces.King, Suits.Clubs,
					Faces.Nine, Suits.Clubs);
		case WildRoyalFlush:
			return GetTestHand(Faces.Ten, Suits.Clubs,
					Faces.Jack, Suits.Clubs,
					Faces.Deuce, Suits.Hearts,
					Faces.King, Suits.Clubs,
					Faces.Deuce, Suits.Spades);
		case FiveOfAKind:
			return GetTestHand(Faces.Eight, Suits.Clubs,
					Faces.Deuce, Suits.Clubs,
					Faces.Eight, Suits.Hearts,
					Faces.Deuce, Suits.Diamonds,
					Faces.Deuce, Suits.Spades);
		case FourOfAKind:
			return GetTestHand(Faces.Eight, Suits.Clubs,
					Faces.Eight, Suits.Diamonds,
					Faces.Eight, Suits.Hearts,
					Faces.Eight, Suits.Spades,
					Faces.Nine, Suits.Clubs);
		case FullHouse:
			return GetTestHand(Faces.Eight, Suits.Clubs,
					Faces.Eight, Suits.Diamonds,
					Faces.Eight, Suits.Hearts,
					Faces.Three, Suits.Spades,
					Faces.Three, Suits.Clubs);
		case Flush:
			return GetTestHand(Faces.Three, Suits.Clubs,
					Faces.Five, Suits.Clubs,
					Faces.Seven, Suits.Clubs,
					Faces.Nine, Suits.Clubs,
					Faces.King, Suits.Clubs);
		case Straight:
			return GetTestHand(Faces.Ace, Suits.Clubs,
					Faces.Deuce, Suits.Clubs,
					Faces.Three, Suits.Diamonds,
					Faces.Four, Suits.Hearts,
					Faces.Five, Suits.Clubs);
		case ThreeOfAKind:
			return GetTestHand(Faces.Three, Suits.Clubs,
					Faces.Three, Suits.Hearts,
					Faces.Three, Suits.Diamonds,
					Faces.Four, Suits.Hearts,
					Faces.Five, Suits.Clubs);
		case TwoPair:
			return GetTestHand(Faces.Three, Suits.Clubs,
					Faces.Three, Suits.Hearts,
					Faces.Five, Suits.Diamonds,
					Faces.Nine, Suits.Hearts,
					Faces.Nine, Suits.Clubs);
		case KingsOrHigher:
			return GetTestHand(Faces.Three, Suits.Clubs,
					Faces.Four, Suits.Hearts,
					Faces.Five, Suits.Diamonds,
					Faces.Ace, Suits.Hearts,
					Faces.Ace, Suits.Clubs);
			default:
				return GetTestHand(Faces.Ten, Suits.Clubs,
						Faces.Deuce, Suits.Spades,
						Faces.Five, Suits.Clubs,
						Faces.Seven, Suits.Diamonds,
						Faces.Ace, Suits.Hearts);
		}
	}
	
	public static Card[] ChangeCard(Card origCards[], int index, Faces newFace, Suits newSuit)
	{
		origCards[index] = new Card(newFace, newSuit);
		Hand hand = new Hand();
		hand.setCards(origCards);
		hand.SortCards();
		return hand.getCards();
	}
	
	public static Card[] GetTestHand(Faces face1, Suits suit1,
			Faces face2, Suits suit2,
			Faces face3, Suits suit3,
			Faces face4, Suits suit4,
			Faces face5, Suits suit5)
	{
		Card[] cards = new Card[5];
		Hand hand = new Hand();
		cards[0] = new Card(face1, suit1);
		cards[1] = new Card(face2, suit2);
		cards[2] = new Card(face3, suit3);
		cards[3] = new Card(face4, suit4);
		cards[4] = new Card(face5, suit5);
		hand.setCards(cards);
		hand.SortCards();
		return hand.getCards();
	}
}
