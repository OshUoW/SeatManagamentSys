import java.util.Scanner;
import java.util.InputMismatchException;

public class PlaneManagement {

    // Seating plan
    private static int row = 4;
    private static final int seat = 14;

    // Arrays used
    private static final int[][] position = new int[row][seat];
    private static Ticket[] ticketDetails = new Ticket[52];

    private static int totTickets = 0;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("                WELCOME TO THE PLANE MANAGEMENT APPLICATION        ");

        while (true) {
            System.out.println();
            System.out.println("");
            System.out.println();
            System.out.println("*             M E N U   I T E M S                *");
            System.out.println();
            System.out.println("");
            System.out.println();
            System.out.println("     1) Buy a seat ");
            System.out.println("     2) Cancel a seat ");
            System.out.println("     3) Find first available seat ");
            System.out.println("     4) Show seating plan ");
            System.out.println("     5) Print tickets information and total sales ");
            System.out.println("     6) Search ticket ");
            System.out.println("     7) Quit ");
            System.out.println();
            System.out.println("");
            System.out.println();
            System.out.print("Please select an option: ");
            String choice = input.next();
            input.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Buying a Seat");
                    buy_Seat(input);

                    break;
                case "2":
                    System.out.println("Cancelling a seat");
                    cancel_seat();
                    break;
                case "3":
                    System.out.println("Find first available seat");
                    find_first_available();
                    break;
                case "4":
                    System.out.println("Seating plan");
                    show_seating_plan();
                    break;
                case "5":
                    System.out.println("Print seating tickets");
                    print_Tickets_Info();
                    break;
                case "6":
                    System.out.println("Searching ticket");
                    search_Ticket();
                    break;
                case "0":
                    System.out.println("Thank you!");
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        }
    }

    public static void buy_Seat(Scanner input) {
        char rowLetter = obtainRowLetter();
        int seatNumber = obtainSeatNumber();

        int seatRows = rowLetter - 'A';
        int seatDigit = seatNumber - 1;
        int seatPrice = ticketPrice(seatNumber);

        // Check 1: Seat sold
        if (position[seatRows][seatDigit] == 1) {
            System.out.println("Seat already sold. Please choose another seat.");

            // Check 2: Seats rows B and C
        } else if ((rowLetter == 'B' || rowLetter == 'C') && seatNumber > 12) {
            System.out.println("no seats available in that position");

        } else {
            //Allocating that the seat is sold
            position[seatRows][seatDigit] = 1;

            System.out.print("Enter name :");
            String name = input.next();
            System.out.print("Enter surname   :");
            String surname = input.next();
            System.out.print("Enter email     :");
            String email = input.next();

            //Entering info to create person and ticket object
            Person person = new Person(name, surname, email);// entering person info to the person object
            Ticket ticket = new Ticket(rowLetter, seatNumber, seatPrice, person);

            // Adds the ticket count
            ticketDetails[totTickets++] = ticket;
            ticket.save();
            System.out.println("Seat " + rowLetter + seatNumber + " sold for : " + seatPrice);
        }
    }

    public static void cancel_seat() {

        char rowLetter = obtainRowLetter();
        int seatNumber = obtainSeatNumber();

        int seatRows = rowLetter - 'A';
        int seatDigit = seatNumber - 1;

        // Check 1: Seat sold
        if (position[seatRows][seatDigit] == 1) {
            System.out.println("Seat is already sold. Please choose another seat.");

            // Check 2: Seats rows B and C
        } else if (rowLetter == 'B' || rowLetter == 'C' && seatNumber > 12) {
            System.out.println("No seats available in this position");
        } else {
            //Allocating that the seat is sold
            position[seatRows][seatDigit] = 0;
            for (int i = 0; i < totTickets; i++) {
                Ticket ticket = ticketDetails[i];
                if (ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {

                    for (int j = i; j < totTickets - 1; j++) {
                        ticketDetails[j] = ticketDetails[j + 1];
                    }
                    //Decreasing the ticket count by one
                    totTickets--;
                    ticketDetails[totTickets] = null; // Setting the last element to null to avoid duplicate tickets
                }

            }

            System.out.println("Seat " + rowLetter + seatNumber + " has been successfully canceled.");
        }

    }

    public static char obtainRowLetter() {
        try {
            while (true) {
                System.out.print("Enter the row letter (A-D): ");
                String rowLetter = input.nextLine().toUpperCase();
                char finalRowLetter = rowLetter.charAt(0);

                if (finalRowLetter < 'A' || finalRowLetter > 'D') { // Check 1: input A-D
                    System.out.println("Invalid row letter.Try again!! ");

                } else if (rowLetter.length() > 1) { // Check 2: length of row input
                    System.out.println("Invalid row letter.Enter only 1 character");

                } else {
                    return finalRowLetter;
                }
            }
        } catch (StringIndexOutOfBoundsException f) {
            //Checking string validity of input
            System.out.println("Invalid input. Please enter a valid integer.");
            input.nextLine();
            return obtainRowLetter();
        }

    }
    public static int obtainSeatNumber() {

        try {
            while (true) {
                System.out.print("Enter the seat number (1-14): ");
                int seatNumber = input.nextInt();
                input.nextLine();

                if (seatNumber < 1 || seatNumber > seat) { // Check 1: input A-D
                    System.out.println("Invalid seat number. Please try again.");

                } else {
                    return seatNumber;
                }
            }
        } catch (InputMismatchException e) {
            //Checking int validity of input
            System.out.println("Invalid input. Please enter a valid integer.");
            input.nextLine();
            return obtainSeatNumber();
        }

    }

    @SuppressWarnings("unused")
    public static void find_first_available() {

        for (int i = 0; i < position.length; i++) {

            for (int j = 0; j < position[i].length; j++) {
                // Check if seat numbers are between 12-14 in rows B and C
                if ((i == 1 || i == 2) && j >= 12) {
                    continue;

                }

                if (position[i][j] == 0) {
                    char rowLetter = (char) ('A' + i); // Adjusting row letter and seat number
                    int seatnumber = j + 1;
                    System.out.println("first available seat is" + rowLetter + seatnumber);
                    break;
                }

            }
            break;

        }

    }

    public static void show_seating_plan() {
        for (int i = 0; i < row; i++) {
            System.out.println();
            for (int j = 0; j < position[i].length; j++) {
                if ((i == 1 || i == 2) && j >= 12) {
                    continue;

                }
                if (position[i][j] == 0) { // printing seat plan
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }

            }
        }

    }

    //Method calculating price of tickets
    public static int ticketPrice(int seatIndex) {
        int price;
        if (seatIndex <= 5) {
            price = 200;
        } else if (seatIndex >= 6 && seatIndex <= 9) {
            price = 150;
        } else {
            price = 180;
        }
        return price;

    }
    public static void print_Tickets_Info() {
        int totAmount = 0;
        for (int i = 0; i < totTickets; i++) { // printing all ticket info and total price
            Ticket ticket = ticketDetails[i];
            Person person= Ticket.getPerson();
            // Printing person and ticket info
            person.printPersonInfo();
            Ticket.printTicketInfo();
        }
        for (int i = 0; i < totTickets; i++) {
            Ticket ticket = ticketDetails[i];

            int totalamount1 = Ticket.getPrice();
            totAmount = totAmount + totalamount1;

        }
        System.out.println("total price is :" + totAmount);

    }

    public static void search_Ticket() {

        char rowLetter = obtainRowLetter();
        int seatNumber = obtainSeatNumber();

        int row = rowLetter - 'A';
        int seatIndex = seatNumber - 1;

        if (position[row][seatIndex] == 1) {
            // Check if seat numbers are between 12-14 in rows B and C
            for (int i = 0; i < totTickets; i++) {
                Ticket ticket = ticketDetails[i];

                // checking if row and seat number match any of the sold tickets
                if (ticket.getRow() == rowLetter && ticket.getSeat() == seatNumber) {
                    Person person = Ticket.getPerson();
                    person.printPersonInfo();
                    ticket.printTicketInfo();

                }
            }

        } else {
            System.out.println("this seat is available");
        }

    }
}