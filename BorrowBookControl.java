import java.util.ArrayList;
import java.util.List;
//Authored by Tharindu
//Reviewed by Dilshan

public class BorrowBookControl {
	
	//change the variable name
	private BorrowBookUI ui;
	
	//change the data type & variable name
	private Library library;
	//change the data type & variable name
	private Member memberId;
	//change class name
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	//change the data type & variable
	private ControlState state;
	
	//change the data type
	private List<Book> pending;
	//change the data type
    private List<Loan> completed;
	//change the data type & variable
	private Book book;
	
	
	public BorrowBookControl() {
		//change the variable name & method name
		this.library = library.instance();
		//change the data type & variable
	    state = ControlState.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		//change the variable name and data type
		if (!state.equals(ControlState.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		//change the variable name	
		this.ui = ui;
		//change the data type & method name
		ui.setState(BorrowBookUI.UiState.READY);
		//change the variable & data type
		state = ControlState.READY;		
	}

	//change the variable name
	public void Swiped(int memberId) {
		if (!state.equals(ControlState.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		//change variable names annd the data type
		memberId = library.Member(memberId);
		//change the variable name
		if (memberId == null) {
			UI.Display("Invalid memberId");
			return;
		}
		
		if (LIBRARY.MEMBER_CAN_BORROW(M)) {
			PENDING = new ArrayList<>();
			//change the method name and a data type
			UI.setState(BorrowBookUI.UiState.SCANNING);
			//change the variable name and data type
			state = ControlState.SCANNING; }
		else 
		{
			UI.Display("Member cannot borrow at this time");
			//change data types
			UI.setState(BorrowBookUI.UiState.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		//change the variable name
		book = null;
		//change the data type
		if (!state.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		//change variable names
		book = library.book(bookId);
		//change the variable name
		if (book == null) {
			UI.Display("Invalid bookId");
			return;
		}
		//change the variable name
		if (!book.AVAILABLE()) {
			UI.Display("Book cannot be borrowed");
			return;
		}
		//change the variable name
		PENDING.add(book);
		for (book B : PENDING) {
			UI.Display(B.toString());
		}
		//change the variable name
		if (library.Loans_Remaining_For_Member(M) - PENDING.size() == 0) {
			UI.Display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			UI.Display("\nFinal Borrowing List");
			for (book B : PENDING) {
				UI.Display(B.toString());
			}
			//change the data type
			COMPLETED = new ArrayList<Loan>();
			//change the method name and a data type
			UI.setState(BorrowBookUI.UiState.FINALISING);
			//change the variable name and data type
			state = ControlState.FINALISING;
		}
	}

	//change the method name
	public void commitLoans() {
		//change the variable name and data type
		if (!state.equals(ControlState.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book B : PENDING) {
			//change variable names and the data type
			Loan loan = library.ISSUE_LOAN(B, M);
			//change the variable name
			COMPLETED.add(loan);			
		}
		
		UI.Display("Completed Loan Slip");
		
		for (loan LOAN : COMPLETED) {
			UI.Display(LOAN.toString());
		}
		//change the method name and a data type
		UI.setState(BorrowBookUI.UiState.COMPLETED);
		//change the variable name and the data type
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		//change the method name and a data type
		UI.setState(BorrowBookUI.UiState.CANCELLED);
		//change the variable name and the data type
		state = ControlState.CANCELLED;
	}
	
	
}
