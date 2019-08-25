//Author - Dilshan Amarasinghe 11673207
//Reviewed by Lahiru

import java.util.Scanner;


public class ReturnBookUI {

	public static enum UIState { INITIALISED, READY, INSPECTING, COMPLETED }; 	//changed "UI_STATE"

	private ReturnBookControl control;						//changed "CoNtRoL"
	private Scanner input;
	private UIState state;								//changed "UI_STATE"

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;							//changed "CoNtRol"
		input = new Scanner(System.in);
		state = UIState.INITIALISED;						//changed "UI_STATE"
		control.setUI(this);
	}


	public void run() {								//changed "RuN"
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {  						//changed "StATe"
			
			case INITIALISED:
				break;
				
			case READY:
				String bookTitle = input("Scan Book (<enter> completes): ");	//"Book_STR" changed into "bookTitle"
				if (bookTitle.length() == 0) {
					control.scanningComplete();   //changed "CoNtRoL" and "Scanning_Complete"
				}
				else {
					try {
						int bookId = Integer.valueOf(bookTitle).intValue();	//changed "Book_Id"
						control.bookScanned(bookTitle);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String answer = input("Is book damaged? (Y/N): ");   	//changed "ans"
				boolean isDamaged = false;				//changed "Is_Damaged"
				if (answer.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged);			//changed "CoNtRoL" and "Discharge_loan" and "Is_Damaged"
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String getInput(String prompt) { //changed "input()" to getInput()
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void getOutput(Object object) {  //changed "output()" to getOutput()
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UIState state) {						//changed "Set_State" and "StATe"
		this.state = state;
	}

	
}
