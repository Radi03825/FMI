// Fig. 7.11: DeckOfCardsTest.java
// Card shuffling and dealing application.

public class DeckOfCardsTest
{
   // execute application
   public static void main( String args[] )
   {
      DeckOfCards myDeckOfCards = new DeckOfCards();
      myDeckOfCards.shuffle(); // place Cards in random order

      System.out.printf("%-20s%-20s\n", "Left hand:", "Right hand:");

      Card[] leftHand = myDeckOfCards.dealHand();
      Card[] rightHand = myDeckOfCards.dealHand();

      for (int i = 0; i < leftHand.length; i++) {
         System.out.printf("%-20s%-20s\n", leftHand[i].toString(), rightHand[i].toString());
      }

      System.out.println("\nHand Values:");

      int leftHandRank = myDeckOfCards.getHandRank(leftHand);
      int rightHandRank = myDeckOfCards.getHandRank(rightHand);

      System.out.printf("%-20s%-20s\n", myDeckOfCards.getRankLabel(leftHandRank), myDeckOfCards.getRankLabel(rightHandRank));

      if (leftHandRank < rightHandRank) {
         System.out.println("\nResult: left hand is better.");
      } else if (leftHandRank > rightHandRank) {
         System.out.println("\nResult: right hand is better.");
      } else {
         System.out.println("\nResult: both hands is equal.");
      }

      // print all 52 Cards in the order in which they are dealt
//      for ( int i = 0; i < 13; i++ )
//      {
//         // deal and print 4 Cards
//         System.out.printf( "%-20s%-20s%-20s%-20s\n",
//            myDeckOfCards.dealCard(), myDeckOfCards.dealCard(),
//            myDeckOfCards.dealCard(), myDeckOfCards.dealCard() );
//      } // end for
   } // end main


} // end class DeckOfCardsTest

