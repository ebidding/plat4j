package org.net.plat4j.sr.core.base;

/**
 * @author chenshiming
 *
 */
public class BaseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Throwable throwable;
	/**
	 * @see BaseSrModelException
	 */
	public BaseException(String message) {
		super(message);
	}
	/**
	 * 
	 * @see BaseSrModelException
	 */	
	public BaseException(Throwable e) {
		this.throwable = e;
	}

	


	public String getMessage() {
		if (throwable != null) {
			return throwable.getClass()+":"+throwable.getMessage();
		} else {
			return super.getMessage();
		}		
	}
	public void printStackTrace() {
		if (throwable != null) {
			throwable.printStackTrace();
		} else {
			super.printStackTrace();
		}
	}

}
