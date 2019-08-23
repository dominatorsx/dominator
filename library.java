
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Authored by Lahiru


@SuppressWarnings("serial")

//class name was started with Uppercase letter,changed to lowercase.
public class Library implements Serializable {
	
	//changed all constant variable names to uppercase
	private static final String LIBRARY_FILE = "library.obj";
	private static final int LOAN_LIMIT = 2;
	private static final int LOAN_PERIOD = 2;
	private static final double FINES_PER_DAY = 1.0;
	private static final double MAX_FINES_OWED = 1.0;
	private static final double DAMAGE_FEE = 2.0;
	
	//changed all variable names to lowercase
	private static Library Self;
	private int bookId;
	private int memberID;
	private int loanId;
	private Date loanDate;
	
	private Map<Integer, book> catalog;
	private Map<Integer, member> members;
	private Map<Integer, loan> loans;
	private Map<Integer, loan> currentLoans;
	private Map<Integer, book> damageBooks;
	
    //Renamed the constructor
	private Library() {
		//renamed the variable names
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damageBooks = new HashMap<>();
		bookId = 1;
		memberID= 1;		
		loadId = 1;		
	}

	//Data type was started with lowercase letter,changed to Uppercase.
	//method name changed to lowercase
	public static synchronized Library instance() {		
		if (SeLf == null) {
			Path PATH = Paths.get(libraryFile);			
			if (Files.exists(PATH)) {	
				try (ObjectInputStream LiF = new ObjectInputStream(new FileInputStream(libraryFile));) {
			    
					SeLf = (library) LiF.readObject();
					Calendar.INSTANCE().Set_dATE(SeLf.LOAN_DATE);
					LiF.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else SeLf = new library();
		}
		return SeLf;
	}

	// changed method name to lowercase
	public static synchronized void save() {
		if (SeLf != null) {
			SeLf.LOAN_DATE = Calendar.INSTANCE().Date();
			try (ObjectOutputStream LoF = new ObjectOutputStream(new FileOutputStream(libraryFile));) {
				LoF.writeObject(SeLf);
				LoF.flush();
				LoF.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	//method name was replaced with a meaningful name.
	public int getBookId() {
		return bookId;
	}
	
	//method name was replaced with a meaningful name.
	public int getMemberId() {
		return memberId;
	}
	
	//method name was replaced with a meaningful name.
	private int getNextBookId() {
		return bookId++;
	}

	//method name was replaced with a meaningful name.
	private int getNextMemberId() {
		return memberId++;
	}

	//method name was replaced with a meaningful name.
	private int getNextLoanId() {
		return loanId++;
	}

	//first letter of data type Member was in lowercase letter,changed it to uppercase letter
	//method name was replaced with a meaningful name.
	public List<Member> getMembers() {		
		return new ArrayList<member>(MEMBERS.values()); 
	}

    
	//first letter of data type Book was in lowercase letter,changed it to uppercase letter
	//method name was replaced with a meaningful name.
	public List<Book> getBooks() {		
		return new ArrayList<book>(CATALOG.values()); 
	}

    //first letter of data type Loan was in lowercase letter,changed it to uppercase letter
	//method name was replaced with a meaningful name.
	public List<Loan> getCurrentLoans() {
		return new ArrayList<loan>(CURRENT_LOANS.values());
	}

    //first letter of data type Member was in lowercase letter,changed it to uppercase letter
	//method name was replaced with a meaningful name.
	public Member addMember(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, NextMID());
		MEMBERS.put(member.GeT_ID(), member);		
		return member;
	}

	//first letter of data type Book was in lowercase letter,changed it to uppercase letter
	//method name was replaced with a meaningful name.
	public Book addBook(String a, String t, String c) {		
		book b = new book(a, t, c, NextBID());
		CATALOG.put(b.ID(), b);		
		return b;
	}

	//first letter of data type Member was in lowercase letter,changed it to uppercase letter
	//method name was replaced with a meaningful name.
	public Member getMember(int memberId) {
		if (MEMBERS.containsKey(memberId)) 
			return MEMBERS.get(memberId);
		return null;
	}

	//first letter of data type Member was in lowercase letter,changed it to uppercase letter
	//method name was replaced with a meaningful name.
	public Book getBook(int bookId) {
		if (CATALOG.containsKey(bookId)) 
			return CATALOG.get(bookId);		
		return null;
	}

	//method name was replaced with a meaningful name.
	public int getLoanLimit() {
		return loanLimit;
	}

	//method name was replaced with a meaningful name.
	public boolean memberCanBorrow(member member) {		
		if (member.Number_Of_Current_Loans() == loanLimit ) 
			return false;
				
		if (member.Fines_OwEd() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.GeT_LoAnS()) 
			if (loan.OVer_Due()) 
				return false;
			
		return true;
	}

	//method name was replaced with a meaningful name.
	public int getLoansRemainingForMember(member member) {		
		return loanLimit - member.Number_Of_Current_Loans();
	}

	//method name was replaced with a meaningful name.
	//changed the data type
	public Loan issueLoan(book book, member member) {
		Date dueDate = Calendar.INSTANCE().Due_Date(loanPeriod);
		loan loan = new loan(NextLID(), book, member, dueDate);
		member.Take_Out_Loan(loan);
		book.Borrow();
		LOANS.put(loan.ID(), loan);
		CURRENT_LOANS.put(book.ID(), loan);
		return loan;
	}
	
	//method name was replaced with a meaningful name.
	//changed the data type
	public Loan getLoanByBookId(int bookId) {
		if (CURRENT_LOANS.containsKey(bookId)) {
			return CURRENT_LOANS.get(bookId);
		}
		return null;
	}

	//method name was replaced with a meaningful name.
	//changed the data type
	public double calculateOverdueFine(loan loan) {
		if (loan.OVer_Due()) {
			long daysOverDue = Calendar.INSTANCE().Get_Days_Difference(loan.Get_Due_Date());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}

    //method name was replaced with a meaningful name.
	//made changes to the argument data types
	public void dischargeLoan(Loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = CalculateOverDueFine(currentLoan);
		member.Add_Fine(overDueFine);	
		
		member.dIsChArGeLoAn(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.Add_Fine(damageFee);
			DAMAGED_BOOKS.put(book.ID(), book);
		}
		currentLoan.DiScHaRgE();
		CURRENT_LOANS.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : CURRENT_LOANS.values()) {
			loan.checkOverDue();
		}		
	}

    //method name was replaced with a meaningful name.
	//made changes to the argument data types
	public void repairBook(book currentBook) {
		if (DAMAGED_BOOKS.containsKey(currentBook.ID())) {
			currentBook.Repair();
			DAMAGED_BOOKS.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
