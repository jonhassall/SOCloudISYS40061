
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class AccountManagerImpl implements AccountManager {
    @Override
    public synchronized Account open(String name)
    {
        //TODO: Problem with hashtable casting
        //Exception in thread "main" java.lang.ClassCastException: java.util.Hashtable can
        //not be cast to Account
        Account account = (Account) _accounts;
        
        if (account == null)
        {
            float balance = Math.abs(_random.nextInt()) % 100000 / 100f;
            try {
                account = new AccountImpl(name, balance);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        return account;
    }
    
//    private Dictionary _accounts = new Hashtable<String, Account>();
    private Dictionary _accounts = new Hashtable();
    private Random _random = new Random();
}
