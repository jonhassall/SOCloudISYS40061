import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Account: A simple RMI interface that represents a banking account.
 */
public interface Account extends Remote {

    // Get the name on the account

    public String getName() throws RemoteException;

    // Get current balance

    public float getBalance() throws RemoteException;

    // Take some money away

    public void withdraw(float amt)
            throws RemoteException, InsufficientFundsException;

    // Put some money in

    public void deposit(float amt) throws RemoteException;

    // Move some money from one account into this one

    public void transfer(float amt, Account src)
            throws RemoteException, InsufficientFundsException;
    
    public float calculateInterest(float interestRatePercent) throws RemoteException;;
}
