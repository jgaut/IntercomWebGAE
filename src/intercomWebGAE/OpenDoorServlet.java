package intercomWebGAE;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OpenDoorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5413269371278741000L;

	public OpenDoorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		resp.setContentType("text/plain");

		//http://1-dot-intercomwebgae.appspot.com/od/?action=add
		if(req.getParameter("action")!=null && req.getParameter("action").equals("add")){
			ToolBox.openDoor(new OpenDoor(5));
			resp.getWriter().println("ok");
		}
		
		//http://1-dot-intercomwebgae.appspot.com/od/?action=open
		if(req.getParameter("action")!=null && req.getParameter("action").equals("open")){
			Gson gson;
			GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			gson = builder.create();
			resp.getWriter().println(gson.toJson(ToolBox.getOpenDoor()));
		}
		
	}

}
