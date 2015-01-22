import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * AccountClient: An example client for an RMI-exported Account.
 */
public class AccountClient {

    public static void main(String args[]) {
        String host;
//        if (args.length < 1) {
//            host = null;
//        } else {
//            host = args[0];
//        }
        //Work on local registry only
//        host = null;
        host = "127.0.0.1";
        
        String name;
        
        if (args.length < 1) {
            name = args[0];
        }
        else
        {
            name = "Default name";
        }
        
        try {
            // Lookup account object
            Registry registry = LocateRegistry.getRegistry(host);
            
            //List names in registry
            String[] registryList = registry.list();
            System.out.println("Listing entries in registry:");
            for (String registryListEntry : registryList) {
                System.out.println("Registry entry: " + registryListEntry);
            }
            
            AccountManager acctMgr = (AccountManager) registry.lookup("SOCT-BANK");
            
            Account myAcct = acctMgr.open(name);
            
            myAcct.deposit(123);
            System.out.println("Deposited 12,000 into account owned by "
                    + myAcct.getName());
            System.out.println("Balance now totals: " + myAcct.getBalance());
            
            //Get account stub from registry
//            Account stub = (Account) registry.lookup("BlobbyAccount");
//            Account my

            // Make deposit
//            stub.deposit(12000);
//
//            // Report results and balance.
//            System.out.println("Deposited 12,000 into account owned by "
//                    + stub.getName());
//            System.out.println("Balance now totals: " + stub.getBalance());
            
            //Calculate interest on current balance
            float interestCalculation = myAcct.calculateInterest(5);
            System.out.println("Interest calculated on current balance is: " + interestCalculation);
            
        } catch (RemoteException re) {
            System.err.println("Remote exception while looking up account: "
                    + re.getMessage());
        } catch (NotBoundException nbe) {
            System.err.println("Server object not found at expected URL: "
                    + nbe.getMessage());
        }
    }
}
