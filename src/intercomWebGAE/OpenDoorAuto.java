package intercomWebGAE;

import java.util.Date;

import com.google.appengine.api.datastore.Key;
import com.google.gson.annotations.Expose;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;


@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class OpenDoorAuto {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    @Expose
    private String compte;
    @Persistent
    @Expose
    private Date untilDate;

    public OpenDoorAuto(String compte, int delta) {
		super();
		this.untilDate=new Date();
		this.untilDate.setMinutes(this.untilDate.getMinutes()+delta);
		this.compte = compte;
	}

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public Date getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(Date untilDate) {
		this.untilDate = untilDate;
	}

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

}