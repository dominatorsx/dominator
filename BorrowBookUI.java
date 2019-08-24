import java.util.Scanner;


//Reviewed by Lahiru


public class BorrowBookUI {
	//change class name
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	
	//change the variable name
	private BorrowBookControl control;
	private Scanner input;
	//change data type and the variable name
	private UiState state;

	
	public BorrowBookUI(BorrowBookControl control) {
		//change the variable name
		this.control = control;
		input = new Scanner(System.in);
		//change data type and variable name
		state = UiState.INITIALISED;
		//change method name
		control.setUi(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
	//change data type and parameter		
	public void Set_State(UiState state) {
		this.state = state;
	}

	
	public void run() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			//change the variable name
			switch (state) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String MEM_STR = input("Swipe member card (press <enter> to cancel): ");
				if (MEM_STR.length() == 0) {
					//change the variable name
					control.cancel();
					break;
				}
				try {
					//change the variable name
					int memberId = Integer.valueOf(MEM_STR).intValue();
					//change the variable name
					control.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				//change the variable name
				control.cancel();
				break;
			
				
			case SCANNING:
				String Book_Str = input("Scan Book (<enter> completes): ");
				if (Book_Str.length() == 0) {
					//change the variable name
					control.Complete();
					break;
				}
				try {
					//change the variable names
					int bookId = Integer.valueOf(Book_Str).intValue();
					//change the variables
					control.Scanned(bookId);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
			//change variable name
				String ans = input("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {
					//change variable name
					control.cancel();
					
				} else {
					//change the method name
					control.commitLoans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				output("Borrowing Completed");
				return;
	
				
			default:
				output("Unhandled state");
				//change the variable name
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}


	public void Display(Object object) {
		output(object);		
	}


}
