// Fig. 7.10: DeckOfCards.java
// DeckOfCards class represents a deck of playing cards.
import java.util.Random;

public class DeckOfCards
{
   private Card deck[]; // array of Card objects
   private int currentCard; // index of next Card to be dealt
   private final int NUMBER_OF_CARDS = 52; // constant number of Cards
   private Random randomNumbers; // random number generator

   private final String faces[] = { "Ace", "Deuce", "Three", "Four", "Five", "Six",
           "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King" };

   // constructor fills deck of Cards
   public DeckOfCards()
   {
      String suits[] = { "Hearts", "Diamonds", "Clubs", "Spades" };

      deck = new Card[ NUMBER_OF_CARDS ]; // create array of Card objects
      currentCard = 0; // set currentCard so first Card dealt is deck[ 0 ]
      randomNumbers = new Random(); // create random number generator

      // populate deck with Card objects
      for ( int count = 0; count < deck.length; count++ ) 
         deck[ count ] = 
            new Card( faces[ count % 13 ], suits[ count / 13 ] );
   } // end DeckOfCards constructor

   // shuffle deck of Cards with one-pass algorithm
   public void shuffle()
   {
      // after shuffling, dealing should start at deck[ 0 ] again
      currentCard = 0; // reinitialize currentCard

      // for each Card, pick another random Card and swap them
      for ( int first = 0; first < deck.length; first++ ) 
      {
         // select a random number between 0 and 51 
         int second =  randomNumbers.nextInt( NUMBER_OF_CARDS );
         
         // swap current Card with randomly selected Card
         Card temp = deck[ first ];        
         deck[ first ] = deck[ second ];   
         deck[ second ] = temp;            
      } // end for
   } // end method shuffle

   // deal one Card
   public Card dealCard()
   {
      // determine whether Cards remain to be dealt
      if ( currentCard < deck.length )
         return deck[ currentCard++ ]; // return current Card in array
      else        
         return null; // return null to indicate that all Cards were dealt
   } // end method dealCard

   public Card[] dealHand() {
      Card[] hand = new Card[5];

      for (int i = 0; i < hand.length; i++) {
         hand[i] = dealCard();
      }

      return hand;
   }

   public int getHandRank(Card[] hand) {
      if (hasFourOfAKind(hand)) {
         return 1;
      }

      if (hasFullHouse(hand)) {
         return 2;
      }

      if (hasFlush(hand)) {
         return 3;
      }

      if (hasStraight(hand)) {
         return 4;
      }

      if (hasThreeOfAKind(hand)) {
         return 5;
      }

      if (hasTwoPair(hand)) {
         return 6;
      }

      if (hasPair(hand)) {
         return 7;
      }

      return 8;
   }

   public String getRankLabel(int rank) {
      return switch (rank) {
         case 1 -> "Four of a Kind";
         case 2 -> "Full House";
         case 3 -> "Flush";
         case 4 -> "Straight";
         case 5 -> "Three of a Kind";
         case 6 -> "Two Pair";
         case 7 -> "One Pair";
         default -> "none";
      };
   }

   private boolean hasPair(Card[] hand) {
      int[] counters = totalHand(hand);

      for (int i = 0; i < counters.length; i++) {
         if (counters[i] == 2) {
            return true;
         }
      }

      return false;
   }

   private int[] totalHand(Card[] hand) {
      int[] counters = new int[faces.length];

      for (int i = 0; i < hand.length; i++) {
         for (int j = 0; j < faces.length; j++) {
            if (hand[i].getFace().equals(faces[j])) {
               counters[j]++;
            }
         }
      }

      return counters;
   }

   private boolean hasTwoPair(Card[] hand) {
      int[] counters = totalHand(hand);
      int pairs = 0;

      for (int i = 0; i < counters.length; i++) {
         if (counters[i] == 2) {
            pairs++;
         }

         if (pairs == 2) {
            return true;
         }
      }

      return false;
   }

   private boolean hasThreeOfAKind(Card[] hand) {
      int[] counters = totalHand(hand);

      for (int i = 0; i < counters.length; i++) {
         if (counters[i] == 3) {
            return true;
         }
      }

      return false;
   }

   private boolean hasFourOfAKind(Card[] hand) {
      int[] counters = totalHand(hand);

      for (int i = 0; i < counters.length; i++) {
         if (counters[i] == 4) {
            return true;
         }
      }

      return false;
   }

   private boolean hasFlush(Card[] hand) {
      String suit = hand[0].getSuit();

      for (int i = 1; i < hand.length; i++) {
         if (!hand[i].getSuit().equals(suit)) {
            return false;
         }
      }

      return true;
   }

   private boolean hasStraight(Card[] hand) {
      int[] counters = totalHand(hand);

      int count = 0;

      for (int i = 0; i < counters.length; i++) {
         if (counters[i] == 1) {
            count++;
         } else {
            count = 0;
         }

         if (count == 5) {
            return true;
         }
      }

      // Ace, 10, Jack, Queen and King is also a Straight.
      if (count == 4 && counters[0] == 1) {
         return true;
      }

      return false;
   }

   private boolean hasFullHouse(Card[] hand) {
      return hasPair(hand) && hasThreeOfAKind(hand);
   }

} // end class DeckOfCards


