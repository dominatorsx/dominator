//Author - Dilshan Amarasinghe 11673207

public class ReturnBookControl {

	private ReturnBookUI ui;						//changed "Ui"
	private enum ControlState { INITIALISED, READY, INSPECTING };		//changed "CONTROL_STATE"
	private ControlState state;				//changed "CONTROL_STATE" and "sTaTe"
	
	private Library library;						//changed "lIbRaRy"
	private Loan currentLoan;						//changed "loan"
		

	public ReturnBookControl() {
		this.library = Library.INSTANCE();				//changed "lIbRaRy"
		state = ControlState.INITIALISED;				//changed "CONTROL_STATE" and "sTaTe"
	}
	
	
	public void setUI(ReturnBookUI ui) {					//changed "Set_UI"
		if (!state.equals(ControlState.INITIALISED)) { 			//changed "CONTROL_STATE" and "sTaTe"
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;  							//changed "this.Ui" 
		ui.setState(ReturnBookUI.UIState.READY); 			//changed "Set_State"
		state = ControlState.READY;					//changed "CONTROL_STATE" and "sTaTe"
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookId); 			// "CUR_book" changed into "currentBook"
		
		if (currentBook == null) {					// "CUR_book" changed into "currentBook"
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.onLoan()) {					// "CUR_book" changed into "currentBook"
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.loanByBookId(bookId);			//changed "CurrENT_loan" , "lIbRaRy" and "LOAN_BY_BOOK_ID"
		double fineOverDue = 0.0;					//changed "Over_Due_Fine
		if (currentLoan.overDue()) { 					//changed "OVer_Due"
			fineOverDue = library.CalculateOverDueFine(currentLoan);
		}
		ui.display("Inspecting");
		ui.display(currentBook.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.overDue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", fineOverDue));
		}
		ui.setState(ReturnBookUI.UIState.INSPECTING);
		state = ControlState.INSPECTING;		
	}


	public void scanningComplete() {					//changed "Scanning_Complete"
		if (!state.equals(ControlState.READY)) {			//changed "CONTROL_STATE" and "sTaTe"
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UIState.COMPLETED);			//changed "Set_State" and "UI_STATE"	
	}


	public void dischargeLoan(boolean isDamaged) {				//changed "Discharge_loan"
		if (!state.equals(ControlState.INSPECTING)) {			//changed "CONTROL_STATE" and "sTaTe"
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UIState.READY);
		state = ControlState.READY;				
	}


}
