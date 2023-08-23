package exception;


public class ExcelException extends  Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] param;
    private String code="";
    /**
     * Creates a new instance of
     * <code>InscriptionException</code> without detail message.
     */
    public ExcelException() {
    }

    /**
     * Constructs an instance of
     * <code>InscriptionException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
 
    public ExcelException(String code, String msg) {
        super(msg);
        this.code=code;
        
    }
    public ExcelException(String code) {
        this.code=code;
        
    }

    public ExcelException(String code,Object[] param) {
        this.code=code;
        this.setParam(param);
    }
    public ExcelException(String code,String msg,Object[] param) {
        super(msg);
        this.code=code;
        this.setParam(param);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

	public Object[] getParam() {
		return param;
	}

	public void setParam(Object[] param) {
		this.param = param;
	}
    
}
