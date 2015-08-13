package intercomWebGAE;

import com.google.appengine.api.datastore.Key;
import com.google.gson.annotations.Expose;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Appareil {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    @Expose
    private String compte;
    @Persistent
    @Expose
    private int port;
    @Persistent
    private String event;
    @Persistent
    private String imei;
    
    public Appareil(String compte, String imei, int port, String event) {
		super();
		this.compte = compte;
		this.port = port;
		this.event=event;
		this.imei=imei;
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}




}