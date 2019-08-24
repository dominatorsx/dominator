import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//Authored by Lahiru
//review by sulthan

@SuppressWarnings("serial")
//class name was started with Uppercase letter,changed to lowercase.
public class Member implements Serializable {
    //replaced with meaningful variable names
	private String lastName;
	private String fullName;
	private String email;
	private int phoneNo;
	private int memberID;
	private double fines;
	
	private Map<Integer, loan> loans;

	
	public member(String lastName, String firstName, String email, int phoneNo, int id) {
		this.lastName= lastName;
		this.firstName= firstName;
		this.email= email;
		this.phoneNo = phoneNo;
		this.memberID = id;
		
		this.loans = new HashMap<>();
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(ID).append("\n")
		  .append("  Name:  ").append(LN).append(", ").append(FN).append("\n")
		  .append("  Email: ").append(EM).append("\n")
		  .append("  Phone: ").append(PN)
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", FINES))
		  .append("\n");
		
		for (loan LoAn : LNS.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	//method name was replaced with a meaningful name.
	public int getMemberId() {
		return memberID;
	}

	//removed underscore and method name was replaced with a meaningful name.
	public List<loan> getLoans() {
		return new ArrayList<loan>(LNS.values());
	}

	//method name was replaced with a meaningful name.
	public int getNumberOfCurrentLoans() {
		return LNS.size();
	}

	//method name was replaced with a meaningful name.
	public double getFinesOwed() {
		return FINES;
	}

	//method name was replaced with a meaningful name.
	public void takeOutLoan(loan loan) {
		if (!LNS.containsKey(loan.ID())) {
			LNS.put(loan.ID(), loan);
		}
		else {
			throw new RuntimeException("Duplicate loan added to member");
		}		
	}

	//method name was replaced with a meaningful name.
	public String getLastName() {
		return lastName;
	}

	
	//method name was replaced with a meaningful name.
	public String getFirstName() {
		return firstName;
	}

    //made changes to method name
	public void addFine(double fine) {
		FINES += fine;
	}
	
	 //made changes to method name
	public double payFine(double AmOuNt) { //try changing AmOuNT -- SUL
		if (AmOuNt < 0) {
			throw new RuntimeException("Member.payFine: amount must be positive");
		}
		double change = 0;
		if (AmOuNt > FINES) {
			change = AmOuNt - FINES;
			FINES = 0;
		}
		else {
			FINES -= AmOuNt;
		}
		return change;
	}

     //made changes to method name
	public void dischargeLoan(loan LoAn) { //try chnaging LoAn -- SUL
		if (LNS.containsKey(LoAn.ID())) {
			LNS.remove(LoAn.ID());
		}
		else {
			throw new RuntimeException("No such loan held by member");
		}		
	}

}
