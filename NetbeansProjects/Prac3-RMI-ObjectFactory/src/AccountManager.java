
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccountManager extends Remote {

    //Open account by name
    public Account open(String name) throws RemoteException;
    
}
