
import java.lang.*;
import java.util.*;

class Customer {
    private String name;
    private Vector rentals = new Vector();
    public Customer (String newname){
        name = newname;
    };
    public void addRental(Rental arg) {
        rentals.addElement(arg);
    };
    public String getName (){
        return name;
    };
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        Enumeration enum_rentals = rentals.elements();	    
        String result = createRentalRecordHeader();

        while (enum_rentals.hasMoreElements()) {
            //get each rental
            Rental each = (Rental) enum_rentals.nextElement();
            double thisAmount = each.calculateRentalAmount();;
            
            
            // add frequent renter points
            frequentRenterPoints += calculateFrequentRenterPoints(each);

            //show figures for this rental
            result += "\t" + each.getMovie().getTitle()+ "\t" + "\t" + each.getDaysRented() + "\t" + String.valueOf(thisAmount) + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
        result += "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points";
        return result;
    }

    private String createRentalRecordHeader() {
        return "Rental Record for " + this.getName() + "\n" +
               "\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n";
    }
    
    private int calculateFrequentRenterPoints(Rental rental) {
        int points = 1;
        if (rental.getMovie().getPriceCode() == Movie.NEW_RELEASE && rental.getDaysRented() > 1) {
            points++;
        }
        return points;
    }
    

    

}
    