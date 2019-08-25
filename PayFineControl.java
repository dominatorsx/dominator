//Author - Dilshan Amarasinghe 11673207
//Reviewed by Lahiru

import PayFineControl.ControlState;

public class PayFineControl {
	
	private PayFineUI ui;						//changed 'Ui' to 'ui'
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };  // changed "CONTROL_STATE" to "ControlState"
	private ControlState state; // changed "CONTROL_STATE" to "ControlState" + "StAtE" to "state"
	
	private Library library;					//changed "LiBrArY" to "Library"
	private Member member;						//changed "MeMbEr" to "member"


	public PayFineControl() {
		this.library = Library.INSTANCE();			//changed "LiBrArY" to "Library"
		state = ControlState.INITIALISED;			//changed "StAtE" and "CONTROL_STATE"
	}
	
	
	public void setUI(PayFineUI ui) {				//changed "Set_UI" to "setUI"
		if (!state.equals(ControlState.INITIALISED)) {  	//changed "StAtE" and "CONTROL_STATE"
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui; 						//changed "Ui" to "ui"
		ui.setState(PayFineUI.UIState.READY);	//changed "Set_State" to "setState"
		state = ControlState.READY;				
	}


	public void CardSwiped(int memberId) {				//changed "Card_Swiped" to "CardSwiped"
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = Library.MEMBER(memberId);		
		
		if (member == null) {
			ui.display("Invalid Member Id");
			return;
		}
		ui.display(member.toString());				//changed "DiSplAY" to "display"
		ui.setState(PayFineUI.UI_STATE.PAYING); 		//changed "Set_State" to "setState"
		state = ControlState.PAYING;
	}
	
	
	public void cancel() {  // changed "Cancel" to cancel
		ui.setState(PayFineUI.UI_STATE.CANCELLED);
		state = ControlState.CANCELLED;
	}


	public double payFine(double amount) { 				//changed "PaY_FiNe" to "PayFine"   //changed PayFine to payFine()
		if (!state.equals(ControlState.PAYING)) { 	
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.PayFine(amount); 		//changed "ChAnGe", "MeMbEr", "Pay_Fine" and "AmOunt"
		if (change > 0) {
			ui.display(String.format("Change: $%.2f", change)); 	//changed "Ui" and "DiSplAY"
		}
		ui.display(member.toString());
		ui.setState(PayFineUI.UI_STATE.COMPLETED);
		state = ControlState.COMPLETED;
		return change;
	}
	


}
