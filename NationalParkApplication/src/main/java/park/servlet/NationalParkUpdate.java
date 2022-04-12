package park.servlet;

import park.dal.*;
import park.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/nationalparkupdate")
public class NationalParkUpdate extends HttpServlet {
	
	protected NationalParksDao nationalParksDao;
	
	@Override
	public void init() throws ServletException {
		nationalParksDao = NationalParksDao.getInstance();
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String parkId = req.getParameter("parkid");
        if (parkId == null || parkId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ParkID. is"+parkId);
        } else {
        	try {
        		NationalParks np = nationalParksDao.getParkById(parkId);
        		if(np == null) {
        			messages.put("success", "ParkId does not exist.");
        		}
        		req.setAttribute("nationalPark", np);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/NationalparkUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String parkId = req.getParameter("parkid");
        if (parkId == null || parkId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid ParkId.");
        } else {
        	try {
        		NationalParks np = nationalParksDao.getParkById(parkId);
        		if(np == null) {
        			messages.put("success", "ParkId does not exist. No update to perform.");
        		} else {
        			String newDescription = req.getParameter("description");
        			if (newDescription == null || newDescription.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Description.");
        	        } else {
        	        	np = nationalParksDao.updateDescription(np, newDescription);
        	        	messages.put("success", "Successfully updated " + parkId);
        	        }
        		}
        		req.setAttribute("nationalPark", np);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        req.getRequestDispatcher("/NationalparkUpdate.jsp").forward(req, resp);
    }
}
