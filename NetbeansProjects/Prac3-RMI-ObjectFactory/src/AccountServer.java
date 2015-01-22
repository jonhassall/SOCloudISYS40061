import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

/**
 * AccountServer: A utility class that registers an Account with the local
 * RMI registry for remote access
 */

public class AccountServer {
    public static void main(String argv[]) {
        //Install the security manager
//        System.setSecurityManager(new RMISecurityManager());
        
        try {
            AccountManagerImpl acct = new AccountManagerImpl();
            //            AccountImpl acct = new AccountImpl("Mr_Blobby");
 	    AccountManager stub = (AccountManager) UnicastRemoteObject.exportObject(acct, 0);
            
            // Register it with the local naming registry
            Registry registry = LocateRegistry.getRegistry();
	    registry.rebind("SOCT-BANK", stub);

//            System.out.println("\nRegistered account as BlobbyAccount");
            System.out.println("\nRegistered the SOCT-BANK Account Factory as SOCT-BANK");
            
            // Keep the server process alive indefinitely
            Object dummy = new Object();
            synchronized (dummy) {
                try {
                    dummy.wait();
                }
                catch (InterruptedException ie) {}
            }
        }
        catch (RemoteException re) {
            System.err.println("Remote exception while creating/registering: "
                               + re.getMessage());
        }
    }
}
