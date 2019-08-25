public class FixBookControl {
	
	//author by Sulthan ---SUL
	//reviewed by Tharindu
	
	//change the varibale -- tharindu
	private FixBookUi ui;
	private enum ControlState { INITIALISED, READY, FIXING }; //CONTROL_STATE to ControlState -- SUL
	private ControlState state; //StAtE to state -- SUL
	
	//change the variable name and data type -- tharindu
	private library library;
	// change the data type -- tharindu
	private Book curBook; //removed underscore and capatilized first letter -- SUL


	public FixBookControl() 
	{
		this.library = library.INSTANCE();
		state = ControlState.INITIALISED; //StAtE to state -- SUL
	}
	
	
	public void setui(FixBookUi ui) //Set to set -- SUL, remove the underscope -- tharindu
	{
		if (!state.equals(ControlState.INITIALISED)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call setui except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(FixBookUi.ui_STATE.READY); //Set to set -- SUL, remove the underscope -- tharindu
		state = ControlState.READY;		//StAtE to state -- SUL
	}


	public void bookScanned(int bookId) //Book_Scanned to bookScanned --SUL
	{
		if (!state.equals(ControlState.READY)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		curBook = library.Book(bookId); //removed underscore and capatilized first letter -- SUL
		
		if (curBook == null) //removed underscore and capatilized first letter -- SUL
		{
			ui.display("Invalid bookId");
			return;
		}
		if (!curBook.IS_Damaged()) //removed underscore and capatilized first letter -- SUL
		{
			ui.display("Book has not been damaged");
			return;
		}
		ui.display(Cur_Book.toString());
		ui.Set_State(FixBookUi.ui_STATE.FIXING);
		state = ControlState.FIXING;	//StAtE to state -- SUL	
	}


	public void fixBook(boolean MUST_fix) //FIX_Book to fixBook -- SUL
	{
		if (!state.equals(ControlState.FIXING)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (MUST_fix) 
		{
			library.Repair_BOOK(curBook); //removed underscore and capatilized first letter -- SUL
		}
		curBook = null; //removed underscore and capatilized first letter -- SUL
		ui.setState(FixBookUi.ui_STATE.READY);//change the method name -- tharindu
		state = ControlState.READY;	//StAtE to state -- SUL	
	}

	
	public void scanningComplete()   //changed irregular capatilized words into order -SUL
	{
		if (!state.equals(ControlState.READY)) //StAtE to state -- SUL
		{
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUi.ui_STATE.COMPLETED);//change the method name 		
	}
}