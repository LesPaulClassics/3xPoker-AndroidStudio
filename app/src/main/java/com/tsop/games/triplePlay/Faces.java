package com.tsop.games.triplePlay;

public enum Faces
{
	Ace(1,14),
	Deuce(2),
	Three(3),
	Four(4),
	Five(5),
	Six(6),
	Seven(7),
	Eight(8),
	Nine(9),
	Ten(10),
	Jack(11),
	Queen(12),
	King(13),
	Joker(0);
	
	private final int value;
	private final int altValue;
	
	Faces(int value, int altValue)
	{
		this.value = value;
		this.altValue = altValue;
	}
	
	Faces(int value)
	{
		this.value = value;
		this.altValue = value;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int getAlternateValue()
	{
		return altValue;
	}


}
