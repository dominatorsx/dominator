import java.io.Serializable;

//Authored by Lahiru
//review by Sulthan
@SuppressWarnings("serial")
//class name was started with Uppercase letter,changed to lowercase.
public class Book implements Serializable {
	
	//Variable names were in Uppercase letters,It's been changed to lowercase
	private String title; 
	private String author;
	private String callNo;
	//change varibale name to bookId
	private int bookId;
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	//variable name's been changed to lowercase
	private STATE state;
	
	//Constructor name changed
	public Book(String author, String title, String callNo, int bookId) {
		
		this.author = author;
		this.title = title;
		this.callNo = callNo;
		this.bookId = bookId;
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		   sb.append("Book: ").append(ID).append("\n")
		  .append("  Title:  ").append(TITLE).append("\n")
		  .append("  Author: ").append(AUTHOR).append("\n")
		  .append("  CallNo: ").append(CALLNO).append("\n")
		  .append("  State:  ").append(State);
		
		return sb.toString();
	}
     
    //method name was replaced with a meaningful name.
	public Integer getBookID() {
		return bookId;
	}
    //method name was replaced with a meaningful name.
	public String getTitle() {
		return title;
	}

    //method name was replaced with a meaningful name.
	public boolean isAvailable() {
		return state == STATE.AVAILABLE;
	}

	//method name was replaced with a meaningful name.
	public boolean booksOnloan() {
		return state == STATE.ON_LOAN;
	}

	//method name was replaced with a meaningful name.
	public boolean IsBookdamaged() {
		return state == STATE.DAMAGED;
	}

	//method name was replaced with a meaningful name.///////////////////
	public void borrowBook() {
		if (State.equals(STATE.AVAILABLE)) {
			State = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", State));
		}
		
	}

    //method name was replaced with a meaningful name.///////////////////
	public void returnBook(boolean DAMAGED) {
		if (State.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				State = STATE.DAMAGED;
			}
			else {
				State = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", State));
		}		
	}

	 //method name was replaced with a meaningful name.///////////////////
	public void repairBook() {
		if (State.equals(STATE.DAMAGED)) {
			State = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", State));
		}
	}


}
