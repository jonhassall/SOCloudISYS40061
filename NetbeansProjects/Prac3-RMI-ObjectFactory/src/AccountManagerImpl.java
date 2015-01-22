
import java.rmi.RemoteException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;

public class AccountManagerImpl implements AccountManager {
    @Override
    public synchronized Account open(String name)
    {
        //TODO: Problem with hashtable casting
        //Exception in thread "main" java.lang.ClassCastException: java.util.Hashtable can
        //not be cast to Account
        Account account = (Account) accountsMap.get(name);
        
        if (account == null)
        {
            System.out.println("Creating new account");
//            float balance = Math.abs(random.nextInt()) % 100000 / 100f;
            
            float minimum = 10;
            float maximum = 5000;
            float randomNum = minimum + (float)(Math.random()*maximum); 

            System.out.println("New random balance is " + randomNum);
                
            try {
                account = new AccountImpl(name, randomNum);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("Adding account to hashmap");
            accountsMap.put(name, account);
        }
        else
        {
            System.out.println("Retrieved existing account");
            try
            {
                System.out.println("Balance is " + account.getBalance());
            } catch (RemoteException ex)
            {
                System.out.println("Failure.");
            }
        }
        
        return account;
    }
    
//    private Dictionary _accounts = new Hashtable<String, Account>();
    //Hashtable is synchronized (multi-threading), hashmap is not
    //The Dictionary class is the abstract parent of any class, such as Hashtable, which maps keys to values. 
//    private Dictionary _accounts = new Hashtable( Map<String, Account> );
//    private Dictionary _accounts = new Map<String, Account> Hashtable();
//      private Dictionary _accounts = new Hashtable();
      private final Map accountsMap = new Hashtable();

    private final Random random = new Random();
}
