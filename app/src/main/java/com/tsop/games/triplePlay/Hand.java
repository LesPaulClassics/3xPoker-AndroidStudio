package com.tsop.games.triplePlay;

public class Hand
{
	public Hand()
	{
		cards = new Card[5];
	}
	
	private Card[] cards;
	
	public Card[] getCards()
	{
		return cards;
	}
	
	public void setCards(Card[] value)
	{
		cards = value;
	}
	
	public boolean contains(Card card)
	{
		for (Card c : getCards())
			if (c.equals(card))
				return true;
		
		return false;
	}
	
	   public void SortCards()
	   {
	      //bubble sort algorithm
          int i = 0;
	      for (int pass=1; pass < cards.length; pass++)
	        for (i = 0; i < cards.length - 1; i++)
	          if (cards[i].getFaceValue() > cards[i+1].getFaceValue()) 
	        	  swap(cards,i,i+1);

	   }  //end SortCard method

	   // swap two elements of a Card array
	   private void swap( Card c[], int first, int second )
	   {
	      Card hold;  // temporary holding area for swap

	      hold = c[ first ];
	      c[ first ] = c[ second ];
	      c[ second ] = hold;
	   }

}
