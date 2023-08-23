/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exception;

/**
 *
 * @author jeanire
 */
//@ApplicationException(rollback=true)
public class PayementException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object[] param;
    private String code="";
    
    public PayementException(){
        
    }
     public PayementException(String code, String msg) {
        super(msg);
        this.code=code;
        
    }
    public PayementException(String code) {
        this.code=code;
        
    }

    public PayementException(String code,Object[] param) {
        this.code=code;
        this.param = param;
    }
    public PayementException(String code,Object[] param,String msg) {
        super(msg);
        this.code=code;
        this.param = param;
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

    /**
     * @return the param
     */
    public Object[] getParam() {
        return param;
    }

    /**
     * @param param the param to set
     */
    public void setParam(Object[] param) {
        this.param = param;
    }
    
}
