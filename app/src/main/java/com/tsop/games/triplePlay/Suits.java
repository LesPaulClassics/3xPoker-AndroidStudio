package com.tsop.games.triplePlay;

public enum Suits
{

	Hearts(0),
	Diamonds(1),
	Clubs(2),
	Spades(3),
	None(4);
	
	Suits(int index)
	{
		this.index = index;
	}
	
	private int index;
	
	public int getIndex()
	{
		return index;
	}
}
