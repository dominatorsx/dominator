//Author - Dilshan Amarasinghe 11673207

import java.util.Scanner;


public class PayFineUI {


	public static enum UIState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
									//changed "enum UI_STATE"
	private PayFineControl control; 				//changed "CoNtRol"
	private Scanner input;
	private UIState state;						//changed "UI_STATE" and "StAtE"

	
	public PayFineUI(PayFineControl control) {
		this.control = control;					//changed "CoNtRol"
		input = new Scanner(System.in);
		state = UIState.INITIALISED;				//changed "StAtE" and "UI_STATE"
		control.setUI(this);					//changed "Set_UI"
	}
	
	
	public void setState(UIState state) { 				//changed "Set_State" and "UI_STATE"
		this.state = state;
	}


	public void Run() {						//changed "RuN"
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {				//changed "StAtE"
			
			case READY:
				String member = input("Swipe member card (press <enter> to cancel): "); 	//changed "Mem_Str"
				if (member.length() == 0) {
					control.Cancel();		//changed "CoNtRol" and "CaNcEl"
					break;
				}
				try {
					int memberId = Integer.valueOf(member).intValue(); //changed "Member_ID"
					control.CardSwiped(memberId);		//changed "CoNtRol" , "Card_Swiped" and "Member_ID"
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;			//changed "AmouNT"
				String enteredAmount = input("Enter amount (<Enter> cancels) : "); //changed "Amt_Str" to "enteredAmount"
				if (enteredAmount.length() == 0) {
					control.Cancel();
					break;
				}
				try {
					amount = Double.valueOf(enteredAmount).doubleValue(); //changed "AmouNT" and "Amt_Str"
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.PayFine(amount);		//changed "CoNtRoL" and "PaY_FiNe"
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void display(Object object) { 				//changed "DiSplAY"
		output(object);
	}


}
