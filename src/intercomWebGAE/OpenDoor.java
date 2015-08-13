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
public class OpenDoor {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    @Expose
    private Date untilDate;

    public OpenDoor(int delta) {
		super();
		this.untilDate=new Date();
		this.untilDate.setMinutes(this.untilDate.getMinutes()+delta);
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

}