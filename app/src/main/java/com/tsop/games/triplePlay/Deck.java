package com.tsop.games.triplePlay;

public class Deck
{

	public Deck(int numCards)
	{
		setCards(new Card[numCards]);
		
	     for ( int i = 0; i < getCards().length; i++ )
	     {
	         getCards()[ i ] = new Card( Faces.values()[ i % 13 ],
	                               Suits.values()[ i / 13 ] );
	         if (i >= com.tsop.games.triplePlay.Constants.NUM_CARDS_IN_DECK)
	        	 getCards()[i] = new Card(Faces.Joker, Suits.None);
	     }
//	     getCards()[ i ] = new Card( Constants.faces[ i % 13 ],
//                 Constants.suits[ i / 13 ] );
	}
	
	private int index = 0;
	
	private Card[] cards;
	
	public Card[] getCards()
	{
		return cards;
	}
	
	public void setCards(Card[] value)
	{
		cards = value;
	}
	
   public void shuffle()    //shuffles the passed deck
   {
	   index = 0;

	   for ( int i = 0; i < getCards().length; i++ )
	   {
		   	int j =  ( int ) ( Math.random() * getCards().length );
	    	Card temp = getCards()[ i ];          // swap
			getCards()[ i ] = getCards()[ j ];  // the
			getCards()[ j ] = temp;               // cards
	  }
   }
   
   public Card dealCard()
   {
	   if (index >= cards.length)
	   		shuffle();
	   return cards[index++];
		   
   }

}
