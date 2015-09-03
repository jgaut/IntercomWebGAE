package intercomWebGAE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OpenDoorAutoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5413269371278741000L;
	private Gson gson;
	private GsonBuilder builder;

	public OpenDoorAutoServlet() {
		super();
		builder = new GsonBuilder();
		builder.excludeFieldsWithoutExposeAnnotation();
		gson = builder.create();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/plain");

		//http://1-dot-intercomwebgae.appspot.com/od/?action=add&tps=x
		if(req.getParameter("action")!=null && req.getParameter("action").equals("add") && req.getParameter("compte")!=null){
			int tps = 5;
			try { 
				tps = Integer.parseInt(req.getParameter("action")); 
			} 
			catch (Exception e) { 

			}

			resp.getWriter().println(gson.toJson(ToolBox.setAllowToOpenDoor(new OpenDoorAuto(req.getParameter("compte"), tps))));
		}

		//http://1-dot-intercomwebgae.appspot.com/od/?action=add&compte=c1
		if(req.getParameter("action")!=null && req.getParameter("action").equals("open") && req.getParameter("compte")!=null){
			resp.getWriter().println(gson.toJson(ToolBox.allowToOpenDoor(req.getParameter("compte"), true)));
		}

		//http://1-dot-intercomwebgae.appspot.com/od/?action=ring&compte=c1
		if(req.getParameter("action")!=null && req.getParameter("action").equals("ring") && req.getParameter("compte")!=null){
			resp.getWriter().println(gson.toJson(ToolBox.allowToOpenDoor(req.getParameter("compte"), false)));
		}
	}

}
