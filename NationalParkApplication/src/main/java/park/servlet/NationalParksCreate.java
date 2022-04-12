package park.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import park.dal.NationalParksDao;
import park.model.NationalParks;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/nationalparkcreate")
public class NationalParksCreate extends HttpServlet {
	
protected NationalParksDao nationalParksDao;
	
	public void init() throws ServletException {
		nationalParksDao = NationalParksDao.getInstance();
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/NationalParksCreate.jsp").forward(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String state = req.getParameter("state");
        if (state == null || state.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
        	// Create the NationalParks.
        	String parkId = req.getParameter("parkId");
        	String ranking = req.getParameter("ranking");
        	String parkName = req.getParameter("parkname");
        	String acres = req.getParameter("acres");
        	String latitude = req.getParameter("latitude");
        	String longitude = req.getParameter("longitude");
        	String active = req.getParameter("active");
        	String city = req.getParameter("city");
        	//String state = req.getParameter("state");
        	String zip = req.getParameter("zip");
        	String description = req.getParameter("description");
 
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	NationalParks nationalParks = new NationalParks(parkId, Integer.parseInt(ranking),parkName,Long.parseLong(acres),new BigDecimal(latitude),new BigDecimal(longitude),Boolean.parseBoolean(active),city,state,zip,description);
	        	nationalParks = nationalParksDao.create(nationalParks);
	        	messages.put("success", "Successfully created " + nationalParks);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/NationalParksCreate.jsp").forward(req, resp);
    }
}

