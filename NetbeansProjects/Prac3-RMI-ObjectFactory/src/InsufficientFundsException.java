/**
 * An exception indicating that the account funds did not support
 * a particular Account operation.
 */

public class InsufficientFundsException extends Exception {
  public InsufficientFundsException(String msg) {
    super(msg);
  }

  public InsufficientFundsException() {
    super();
  }
}
