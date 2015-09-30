package intercomWebGAE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.annotation.WebServlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//@WebServlet( name="do", urlPatterns = "/test" )
public class Jintercom13Servlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5413269371278741000L;
	private Gson gson;
	private GsonBuilder builder;

	public Jintercom13Servlet() {
		super();
		builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		gson = builder.create();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
	    resp.setContentType("text/plain");
		
		//Exemple : ?action=imei&imei=XXX
		if(req.getParameter("action")!=null && req.getParameter("action").equals("imei")){
			if(req.getParameter("imei")!=null){
				Appareil app = ToolBox.getAppAByImei(req.getParameter("imei"));
				GsonBuilder builder = new GsonBuilder();
				builder.excludeFieldsWithoutExposeAnnotation();
				gson = builder.create();
				resp.getWriter().println(gson.toJson(app));
			}
		}

		//Exemple : ?action=compte&compte=CPT1
		if(req.getParameter("action")!=null && req.getParameter("action").equals("compte")){ 
			if(req.getParameter("compte")!=null){
				List<Appareil> lapp = ToolBox.getAppAByCompte(req.getParameter("compte"));
				GsonBuilder builder = new GsonBuilder();
				builder.excludeFieldsWithoutExposeAnnotation();
				gson = builder.create();
				resp.getWriter().println(gson.toJson(lapp));
			}
		}
		
		if(req.getParameter("action")!=null && req.getParameter("action").equals("test")){
				resp.getWriter().println("This is a test");
		}

		//Exemple : /?action=add&port=22&compte=X1&imei=XXXX&server=s1&event=test&portSsh=23
		if(req.getParameter("action")!=null && req.getParameter("action").equals("add") && req.getParameter("port")!=null){
			String compte = req.getParameter("compte");
			String imei = req.getParameter("imei");
			String server = req.getParameter("server");
			String event = req.getParameter("event");
			int port = Integer.parseInt(req.getParameter("port"));
			//int portSsh = Integer.parseInt(req.getParameter("portSsh"));
			//resp.getWriter().println(req.toString());
			if(compte!=null && port!=0){
				ToolBox.updateOrAddAppA(new Appareil(compte, imei, port, event));
				resp.getWriter().println(gson.toJson(true));
			}
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		this.doGet(req, resp);
	}
}
