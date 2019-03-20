package com.tsop.games.triplePlay;

import com.tsop.android.triplePlayPoker.ShowGameBoard;

import android.graphics.drawable.Drawable;



//import javax.swing.Icon;

public class Card {
	   private Faces face;
	   private Suits suit;

	   public Card( Faces f, Suits s )
	   {
	      face = f;
	      suit = s;
	   }
	   
	   public boolean equals(Card compareCard)
	   {
		   return (this.face.equals(compareCard.face) 
				   && this.suit.equals(compareCard.suit));
		   
	   }
	   
	   public int getFaceValue() {return face.getValue();}
	   
	   public int getFaceAlternateValue() {return face.getAlternateValue();}

	   public Faces getFace() {return face;}

	   public Suits getSuit() {return suit;}

	   public String toString() { return face.toString() + " of " + suit.toString(); }

	   public Drawable toPict()
	   {
	      // return corresponding Icon
		   if (face.equals(Faces.Joker))
			   return ShowGameBoard.CardImage[13][0];
	      return ShowGameBoard.CardImage[face.getValue() - 1][suit.getIndex()];

	   }
	}
