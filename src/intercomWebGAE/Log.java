package intercomWebGAE;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.gson.annotations.Expose;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Log {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    @Expose
    private String compte;
    @Persistent
    @Expose
    private String event;
    @Persistent
    @Expose
    private String date;
    
    public Log(String compte) {
		super();
		this.compte = compte;
		this.event = getCallingMethod();
		this.date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	private String getCallingMethod() {
    	return trace(Thread.currentThread().getStackTrace(), 3);
	}
	
	private String trace(StackTraceElement[] e, int level) {
    	if (e != null && e.length >= level) {
        		StackTraceElement s = e[level];
        		if (s != null) {
            		return s.getClassName() + "." + s.getMethodName()+"("+s.getLineNumber()+")";
        		}
    	}
    return null;
}
	
	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}




}