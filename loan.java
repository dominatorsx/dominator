import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


//Authored by Lahiru

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LOAN_STATE { CURRENT, OVER_DUE, DISCHARGED };
	
	//replaced with meaningful variable names and done few formattings
	private int loanId;
	private Book book;
	private Member member;
	private Date dueDate;
	private LOAN_STATE state;

	////made changes to the method argument data types
	public loan(int loanId, Book book, Member member, Date dueDate) {
		this.loanId = loanId;
		this.book = book;
		this.member = member;
		this.dueDate = dueDate;
		this.state = LOAN_STATE.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.INSTANCE().Date().after(D)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	////method name was replaced with a meaningful name.
	public boolean checkOverdue() {
		return state == LOAN_STATE.OVER_DUE;
	}

	//method name was replaced with a meaningful name.
	public Integer getLoanId() {
		return loanId;
	}

    //formatted method name
	public Date getDueDate() {
		return dueDate;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.GeT_ID()).append(" : ")
		  .append(M.Get_LastName()).append(", ").append(M.Get_FirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.TITLE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}

    //method name was replaced with a meaningful name.
    //formatted return data type
	public Member getMember() {
		return member;
	}

    //method name was replaced with a meaningful name.
    //formatted return data type
	public Book getBook() {
		return book;
	}

    //method name was replaced with a meaningful name.
	public void isDischarged() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
