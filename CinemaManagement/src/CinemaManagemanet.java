import java.util.Scanner;         //import Scanner package
import java.util.regex.Pattern;   //import regex(reguler expression) Pattern and Matcher package.
import java.util.regex.Matcher;   //https://www.javatpoint.com/java-regex
public class CinemaManagemanet {
    public static int[][] seat_plan = new int[3][16];   //Seat list
    static Ticket[] BookSeats = new Ticket[48];      //Booked seat list
    private static final String Name_Validator = "[a-zA-z]*";     //Name validator pattern
    private static final String Email_Validator = "[a-zA-Z0-9]+@[a-z]+[.][com]{2,3}"; //Email Validator pattern
    private static int Repeat;   //create variable

    public static void main(String[]args){
        do {
            try {
                String symbol_1 = "-";
                String symbol_2 = " ";
                Scanner input1 = new Scanner(System.in);
                System.out.println("\n          Welcome to The London Lumiere.");
                System.out.println(symbol_1.repeat(48));
                System.out.println("Please select an option: ");
                System.out.println(symbol_2.repeat(3) + "1) Buy a seat");
                System.out.println(symbol_2.repeat(3) + "2) Cancel ticket");
                System.out.println(symbol_2.repeat(3) + "3) See seating plan");
                System.out.println(symbol_2.repeat(3) + "4) Find first seat available");
                System.out.println(symbol_2.repeat(3) + "5) Print ticket information and total price");
                System.out.println(symbol_2.repeat(3) + "6) Search ticket");
                System.out.println(symbol_2.repeat(3) + "7) Sort ticket by price");
                System.out.println(symbol_2.repeat(3) + "8) Exit");
                System.out.println(symbol_1.repeat(48));
                System.out.print("Select option: ");
                int option = input1.nextInt();
                switch (option) {
                    case 1:
                        BuySeat();
                        break;
                    case 2:              //Display main menu and loop the code using Do-While.
                        CancelSeat();
                        break;
                    case 3:
                        SeeSeatingPlan();
                        break;
                    case 4:
                        FindFirstAvailable();
                        break;
                    case 5:
                        PrintInformation();
                        break;
                    case 6:
                        SearchTicket();
                        break;
                    case 7:
                        SortTicket();
                        break;
                    case 8:
                        System.out.println("Have a good day!");
                        System.out.println("Thank You!");
                        System.exit(0);
                    default:
                        System.out.println("Enter the correct option number.");
                }
            }catch (Exception e){   //Exception handling
                System.out.println("Enter the correct option number.");
            }
        }while (true);
    }
    public static void BuySeat(){
        String Symbol_4 = "-";
        String x = Symbol_4.repeat(5);
        Scanner input2 = new Scanner(System.in);
        try{
            System.out.println("\n"+ x + "Buy Seat" + x);
            System.out.print("Enter the seat row (1-3): ");   //Get inputs,check the valid seat row and seat Number,add exception handling,
            int SeatRow = input2.nextInt();
            ValidSeatRow(SeatRow);
            System.out.print("Enter the seat number: (1-16): ");
            int SeatNumber = input2.nextInt();
            ValidSeatNumber(SeatNumber);
            if(seat_plan[SeatRow - 1][SeatNumber - 1] == 0){
                GetPersonalInfo(SeatRow,SeatNumber);
                System.out.println("The seat has been booked.");
                seat_plan[SeatRow - 1][SeatNumber - 1] = 1;
                BuySeatRepeat();
            }else{
                System.out.println("This seat is not available.");
                BuySeatRepeat();
            }
        }catch (Exception e){
            System.out.println("Enter the correct seat row/number.");
            BuySeatRepeat();
        }
    }
    public static void CancelSeat(){
        String Symbol_5 = "-";
        String x = Symbol_5.repeat(5);
        Scanner input3 = new Scanner(System.in);
        try{
            System.out.println("\n"+ x + "Cancel Seat" + x);
            System.out.print("Enter the seat row (1-3): ");
            int SeatRow = input3.nextInt();                    //Get inputs,check the valid seat row and seat Number,add exception handling,
            ValidSeatRow(SeatRow);
            System.out.print("Enter the seat Number (1-16): ");
            int SeatNumber = input3.nextInt();
            ValidSeatNumber(SeatNumber);
            if(seat_plan[SeatRow - 1][SeatNumber - 1] == 1){
                System.out.println("The seat has been cancelled.");
                seat_plan[SeatRow - 1][SeatNumber - 1] = 0;
                if (SeatRow == 1) {
                    BookSeats[SeatNumber - 1] = null;
                } else if (SeatRow == 2) {
                    BookSeats[16 + SeatNumber - 1] = null;           //Remove elemants
                } else if (SeatRow == 3) {
                    BookSeats[32 + SeatNumber - 1] = null;
                }
                CancelSeatRepeat();
            }else{
                System.out.println("This seat is already available.");
                CancelSeatRepeat();
            }
        }catch (Exception e){
            System.out.println("Enter the correct seat row/number.");
            CancelSeatRepeat();
        }
    }
    public static void SeeSeatingPlan(){
        String Symbol_6 = "-";
        String x = Symbol_6.repeat(5);
        System.out.println("\n"+ x + "Seating Plan" + x);
        String Symbols3 = "*";                                //Display Seating Plan
        System.out.println("\n"+Symbols3.repeat(17));
        System.out.println("*     SCREEN    *");
        System.out.println(Symbols3.repeat(17));
        for (int[] Row : seat_plan) {
            for (int NumberSeat = 0; NumberSeat < Row.length; NumberSeat++) {
                System.out.print(Row[NumberSeat] == 0 ? "O" : "X");     //Ternary Operator
                if ((NumberSeat + 1) % 8 == 0) {           //https://www.geeksforgeeks.org/java-ternary-operator-with-examples/
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    public static void FindFirstAvailable() {
        String Symbol_5 = "-";
        String x = Symbol_5.repeat(5);          //Find first seat
        System.out.println("\n"+ x + "First Available Seat" + x);
        for(int Row = 0; Row < seat_plan.length; Row++) {
            for(int Number = 0; Number < seat_plan[Row].length; Number++) {
                if(seat_plan[Row][Number] == 0) {
                    System.out.println((Row + 1) + " row "  + (Number + 1) +  " seat number  is available.\n");
                    return;
                }
            }
        }
    }
    public static void PrintInformation(){
        String Symbol_5 = "-";
        String x = Symbol_5.repeat(5);
        int TotalSales = 0;                                            // print all ticket information
        int Count = 0;
        System.out.println("\n"+ x + "All Ticket Information" + x);
        for (Ticket bookSeat : BookSeats) {   //enhanced loop
            if (bookSeat != null) {//https://www.geeksforgeeks.org/difference-between-for-loop-and-enhanced-for-loop-in-java/
                System.out.println("\n😊Ticket " + (Count + 1) + ": ");
                System.out.println("----------Seat Information----------");
                System.out.println("Row: " + bookSeat.getSeatRow());
                System.out.println("Seat Number: " + bookSeat.getSeatNumber());
                System.out.println("Price: £"+bookSeat.getPrice());
                System.out.println("----------Personal Information----------");
                System.out.println("Name: " + bookSeat.person.getName());
                System.out.println("SurName: " + bookSeat.person.getSurname());
                System.out.println("Email: " + bookSeat.person.getEmail());
                TotalSales += bookSeat.getPrice();
                Count++;
            }
        }
        System.out.println("\n•Total Sales: £"+ TotalSales);
    }
    public static void SearchTicket(){
        String Symbol_5 = "-";
        String x = Symbol_5.repeat(5);
        Scanner input4 = new Scanner(System.in);
        try{
            System.out.println("\n"+ x + "Search Ticket Information" + x);
            System.out.print("Enter the seat row (1-3): ");
            int SeatRow = input4.nextInt();
            ValidSeatRow(SeatRow);
            System.out.print("Enter the seat number (1-16): ");
            int SeatNumber = input4.nextInt();
            ValidSeatNumber(SeatNumber);                         //Search ticket and print informations
            if(seat_plan[SeatRow - 1][SeatNumber - 1] == 1){
                System.out.println("\nThis seat is booked.");
                for (Ticket bookSeat : BookSeats) {
                    if (bookSeat != null && bookSeat.getSeatRow() == SeatRow
                            && bookSeat.getSeatNumber() == SeatNumber) {
                        System.out.println("\n✍️Ticket Found: ");
                        System.out.println("\n----------Seat Information----------");
                        System.out.println("Row: " + bookSeat.getSeatRow());
                        System.out.println("Seat Number: " + bookSeat.getSeatNumber());
                        System.out.println("Price: £" + bookSeat.getPrice());
                        System.out.println("----------Personal Information----------");
                        System.out.println("Name: " + bookSeat.getPerson().getName());
                        System.out.println("Surname: " + bookSeat.getPerson().getSurname());
                        System.out.println("Email: " + bookSeat.getPerson().getEmail());
                        break;
                    }
                }
                SearchlSeatRepeat();
            }else {
                System.out.println("The seat is already available.");
                SearchlSeatRepeat();
            }
        }catch(Exception e){
            System.out.println("Enter the correct seat row/number.");
            SearchlSeatRepeat();
        }
    }
    public static void SortTicket(){
        int Seat;
        for (Seat = 0;Seat < BookSeats.length;Seat++ ){
            if (BookSeats[Seat] == null) continue;
            if (Seat > 31 ){
                DisplayTicInfo(Seat);
            }
        }                                         //Sort ticket using price.
        for (Seat = 0; Seat < BookSeats.length;Seat++ ){
            if (BookSeats[Seat] == null) continue;
            if (Seat > 15 && Seat < 32 ){
                DisplayTicInfo(Seat);
            }
        }
        for (Seat = 0;Seat  < BookSeats.length;Seat++ ){
            if (BookSeats[Seat] == null) continue;
            if (Seat < 16 ){
                DisplayTicInfo(Seat);
            }
        }
    }
    public static void GetPersonalInfo(int SeatRow,int SeatNumber){
        String Symbol_4 = "-";
        String x = Symbol_4.repeat(5);
        Scanner input5 = new Scanner(System.in);
        System.out.println("\n"+ x + "Personal Information" + x);
        System.out.print("Enter the Name: ");
        String Name = input5.next();
        if(!isValidName(Name)){
            System.out.println("Enter the correct Name.");
            GetPersonalInfo(SeatRow,SeatNumber);
            return;
        }                                                           //Get NAme,Surname,Email
        System.out.print("Enter the Surname: ");
        String Surname = input5.next();
        if (!isValidSurname(Surname)){
            System.out.println("Enter the Correct surname.");
            GetPersonalInfo(SeatRow,SeatNumber);
            return;
        }
        System.out.print("Enter the Email: ");
        String Email = input5.next();
        if(!isValidEmail(Email)){
            System.out.println("Enter the correct email address.");
            GetPersonalInfo(SeatRow,SeatNumber);
            return;
        }
        AppendData(SeatRow,SeatNumber,Name,Surname,Email); //call the method
    }
    public static void DisplayTicInfo(int Seat){
        System.out.println("\n👇Ticket: ");
        System.out.println("----------Seat Information----------");
        System.out.println("Row: " + BookSeats[Seat].getSeatRow());
        System.out.println("Seat Number: " + BookSeats[Seat].getSeatNumber());
        System.out.println("Price: £" + BookSeats[Seat].getPrice());
        System.out.println("----------Personal Information----------");
        System.out.println("Name: " + BookSeats[Seat].getPerson().getName());
        System.out.println("Surname: " + BookSeats[Seat].getPerson().getSurname());
        System.out.println("Email: " + BookSeats[Seat].getPerson().getEmail());
    }
    public static void AppendData (int SeatRow,int SeatNumber,String Name,String Surname,String Email){
        Person person = new Person(Name,Surname,Email);
        int Price = 0;
        int index = 0;
        if (SeatRow == 1){
            Price = 12;
            index = SeatNumber - 1;
        } else if (SeatRow == 2) {
            Price = 10;                      //Generate the price and append data to list
            index = 16 + SeatNumber - 1;
        } else if (SeatRow == 3) {
            Price = 8;
            index = 32 + SeatNumber - 1;
        }
        BookSeats[index] = new Ticket(SeatRow,SeatNumber,person,Price);
    }
    public static void ValidSeatRow(int SeatRow){
        if (!(SeatRow > 0 && SeatRow < 4 )){           //Check the valid row
            System.out.println("Enter the valid Seat Row.");
            main(null);
        }
    }
    public static void ValidSeatNumber(int SeatNumber){
        if (!(SeatNumber > 0 && SeatNumber < 17 )){
            System.out.println("Enter the correct Seat Number");
            main(null);                     //check the vali seat
        }
    }
    public static boolean isValidEmail(String Email){
        Pattern Email_format = Pattern.compile(Email_Validator);
        Matcher Valid_Email = Email_format.matcher(Email);
        return Valid_Email.matches();                        //Check the valid Email
    }

    public static boolean isValidName(String Name){
        Pattern Valid_name = Pattern.compile(Name_Validator);
        Matcher Correct_Format = Valid_name.matcher(Name);       //Check the valid NAme
        return Correct_Format.matches();
    }
    public static boolean isValidSurname(String Surname){
        Pattern Surname_format = Pattern.compile(Name_Validator);       //Check the valid Surname.
        Matcher Correct_Name = Surname_format.matcher(Surname);
        return Correct_Name.matches();
    }

    //Repeat methods
    public static void Repeat(){
        Scanner scanner =new Scanner (System.in);
        System.out.println("\n01. Main Menu");
        System.out.println("02. Run Again");
        System.out.print("Select the option: ");
        Repeat = scanner.nextInt();
    }
    private static boolean RepeatCode(){
        if( Repeat == 1){
            return true;
        }
        return false;
    }
    public static void BuySeatRepeat(){
        Repeat();
        if (!RepeatCode()) {
            BuySeat();
        }
    }
    public static void CancelSeatRepeat(){
        Repeat();
        if (!RepeatCode()) {
            CancelSeat();
        }
    }
    public static void SearchlSeatRepeat(){
        Repeat();
        if (!RepeatCode()) {
            SearchTicket();
        }
    }
}
