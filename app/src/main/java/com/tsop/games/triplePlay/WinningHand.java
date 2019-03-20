package com.tsop.games.triplePlay;

public class WinningHand 
{
	private int payout;
	private String name;
	private WinningHands type;
	private int rank;
	
	public WinningHand(int payout, String name, WinningHands type, int rank)
	{
		this.payout = payout;
		this.name = name;
		this.type = type;
		this.rank = rank;
	}
	
	public WinningHands getType()
	{
		return this.type;
	}
	
	public int getPayout()
	{
		return payout;
	}
	
	public String toString()
	{
		return name;
	}
}
