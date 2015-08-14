package intercomWebGAE;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class ToolBox {

	public static List<Appareil> getAppAByCompte(String compte) {
		List<Appareil> result;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Appareil.class);
		query.setFilter("compte == compteParam");
		query.declareParameters("java.lang.String compteParam");

		try {
			result = (List<Appareil>) query.execute(compte);
			if (result.isEmpty()) {
				return null;
			}
		} finally {
			query.closeAll();
		}

		pm.close();
		return result;
	}

	public static Appareil getAppAByImei(String imei) {
		Appareil app;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Appareil.class);
		query.setFilter("imei == imeiParam");
		query.declareParameters("java.lang.String imeiParam");

		try {
			List<Appareil> result = (List<Appareil>) query.execute(imei);
			if (!result.isEmpty()) {
				app = result.get(0);
			} else {
				return null;
			}
		} finally {
			query.closeAll();
		}

		pm.close();
		return app;
	}

	public static void addAppA(Appareil app) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(app);
		} finally {
			pm.close();
		}
	}

	public static void updateOrAddAppA(Appareil app) {
		Appareil app2;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Appareil.class);
		query.setFilter("imei == imeiParam");
		query.declareParameters("java.lang.String imeiParam");	
		try {
			List<Appareil> result = (List<Appareil>) query.execute(app.getImei());
			if (!result.isEmpty()) {
				app2 = result.get(0);
				app2.setPort(app.getPort());
				app2.setEvent(app.getEvent());
				app2.setImei(app.getImei());
			} else {
				/*Alors on le cree*/
				addAppA(app);
			}
		} finally {
			query.closeAll();
		}

		pm.close();
	}

	//Cette methode enregistre une autorisation pour une ouverture de porte automatique
	public static boolean setAllowToOpenDoor(OpenDoorAuto openDoor) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		boolean res=false;
		try {
			if(pm.makePersistent(openDoor)!=null)
				res=true;
		} finally {
			pm.close();
		}
		return res;
	}

	//Cette methode verifie si l'ouverture auto de la porte est autorisee
	public static boolean allowToOpenDoor(String compte) {
		OpenDoorAuto od;
		Date date = new Date();
		boolean bool = false;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(OpenDoorAuto.class);
		query.setFilter("compte == compteParam");
		query.declareParameters("java.lang.String compteParam");
		try {
			List<OpenDoorAuto> result = (List<OpenDoorAuto>) query.execute(compte);
			if (!result.isEmpty()) {
				Iterator<OpenDoorAuto> i = result.iterator();
				while(i.hasNext()){
					od=i.next();
					if(od.getUntilDate().after(date)){
						bool = true;
					}
					pm.deletePersistent(od);
				}
			}
		} finally {
			query.closeAll();
		}

		pm.close();
		return bool;
	}
}
