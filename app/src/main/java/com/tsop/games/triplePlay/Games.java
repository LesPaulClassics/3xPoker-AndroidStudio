package com.tsop.games.triplePlay;

public enum Games {
	
	JacksOrBetter(0, "Jacks Or Better"),
	BonusPoker(1, "Bonus Poker"),
	DeucesWild(2, "Deuces Wild"),
	JokersWild(3, "Joker Poker");
	
	private int index;
	
	private String gameName;
	
	private Games(int idx, String name)
	{
		index = idx;
		gameName = name;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public static Games GameFromIndex(int index)
	{
		for (Games game : Games.values())
			if (game.getIndex() == index)
				return game;
		return Games.JacksOrBetter;
				
	}

}
