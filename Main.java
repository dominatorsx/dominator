import java.text.SimpleDateFormat;
import java.util.Scanner;

//author by Sulthan ---SUL
//Reviewed by Lahiru

public class Main {
	
	
	//made changes to variable names and formatted data types
	private static Scanner input;
	private static Library library;
	private static String menu;
	private static Calendar calender;
	private static SimpleDateFormat date;
	
	//change method name to meaningful one
	private static String getMenu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			//replaced variable names
			input = new Scanner(System.in);
			library = library.INSTANCE();
			calendar = Calendar.INSTANCE();
			date = new SimpleDateFormat("dd/MM/yyyy");
	        
	        //replaced method name accordingly to match with library class
			for (Member m : library.getMembers()) {
				output(m);
			}
			output(" ");
			for (book b : LIB.BOOKS()) {
				output(b);
			}
						
			MENU = Get_menu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.Date()));
				String c = input(MENU);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					ADD_MEMBER();
					break;
					
				case "LM": 
					MEMBERS();
					break;
					
				case "B": 
					ADD_BOOK();
					break;
					
				case "LB": 
					BOOKS();
					break;
					
				case "FB": 
					FIX_BOOKS();
					break;
					
				case "L": 
					BORROW_BOOK();
					break;
					
				case "R": 
					RETURN_BOOK();
					break;
					
				case "LL": 
					CURRENT_LOANS();
					break;
					
				case "P": 
					FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				library.save(); //SAVE to save -- SUL
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

	
	private static void fines() //FINES to fines -- SUL
	{
		new PayFineUI(new PayFineControl()).RuN();		
	}

    //formatted the method name
	private static void currentLoans() {
		output("");
		for (loan loan : LIB.CurrentLoans()) {
			output(loan + "\n");
		}		
	}


    //formatted the method name
	private static void getBooks() {
		output("");
		for (book book : LIB.BOOKS()) {
			output(book + "\n");
		}		
	}


    //formatted the method name
	private static void getMembers() {
		output("");
		for (member member : LIB.MEMBERS()) {
			output(member + "\n");
		}		
	}


     //formatted the method name
	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}

     //formatted the method name
	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).RuN();		
	}

     //formatted the method name
	private static void fixBook() {
		new FixBookUI(new FixBookControl()).RuN();		
	}

     //formatted the method name
	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.checkCurrentLoans();
			output(SDF.format(CAL.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}

     //formatted the method name
	private static void addBook() {
		
		String A = input("Enter author: ");
		String T  = input("Enter title: ");
		String C = input("Enter call number: ");
		book B = LIB.Add_book(A, T, C);
		output("\n" + B + "\n");
		
	}

	//formatted the method name
	private static void addMember() {
		try {
			//replaced the variable names
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			Member member = LIB.Add_mem(LN, FN, EM, PN);
			output("\n" + M + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}

    //replaced method name with meaningful one
	private static String getInput(String prompt) {
		System.out.print(prompt);
		//replaced the variable name
		return input.nextLine();
	}
	
	
	//replaced method name with meaningful one
	private static void getOutput(Object object) {
		System.out.println(object);
	}
}
