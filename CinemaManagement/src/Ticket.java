public class Ticket {
    int SeatRow;
    int SeatNumber;  //Attributes
    Person person;
    int Price;

    //Constructor
    Ticket(int SeatRow, int SeatNumber, Person person, int Price) {
        this.SeatRow = SeatRow;
        this.SeatNumber = SeatNumber;
        this.person = person;
        this.Price = Price;
    }
    // Getters
    public int getPrice() {
        return Price;
    }
    public int getSeatRow() {

        return SeatRow;
    }
    public int getSeatNumber() {

        return SeatNumber;
    }
    public Person getPerson() {

        return person;
    }
}
