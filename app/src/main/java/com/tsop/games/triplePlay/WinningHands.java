package com.tsop.games.triplePlay;

public enum WinningHands
{
	Nothing,
	JacksOrHigher,
	TwoPair,
	ThreeOfAKind,
	Straight,
	Flush,
	FullHouse,
	FourOfAKind,
	StraightFlush,
	RoyalFlush,
	FourAces,
	Four2to4,
	Four5toK,
	FiveOfAKind,
	WildRoyalFlush,
	FourDeuces,
	KingsOrHigher;
	
	
	public String toString()
	{
		switch (this)
		{
		case Nothing:
			return "Nothing";
		case JacksOrHigher:
			return "Jacks Or Higher";
		case TwoPair:
			return "Two Pair";
		case ThreeOfAKind:
			return "Three Of A Kind";
		case Straight:
			return "Straight";
		case Flush:
			return "Flush";
		case FullHouse:
			return "Full House";
		case FourOfAKind:
			return "Four Of A Kind";
		case StraightFlush:
			return "Straight Flush";
		case RoyalFlush:
			return "Royal Flush";
		case FourAces:
			return "Four Aces";
		case Four2to4:
			return "Four 2s to 4s";
		case Four5toK:
			return "Four 5s to Ks";
		case FiveOfAKind:
			return "Five Of A Kind";
		case WildRoyalFlush:
			return "Wild Royal Flush";
		case FourDeuces:
			return "Four Deuces";
		default:
			return this.toString();
			
		}
		
	}
}
