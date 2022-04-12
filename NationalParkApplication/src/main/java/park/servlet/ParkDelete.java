package park.servlet;

import park.dal.*;
import park.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/parkdelete")
public class ParkDelete extends HttpServlet {

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
    // Provide a title and render the JSP.
    messages.put("title", "Delete NationalParks");
    req.getRequestDispatcher("/ParkDelete.jsp").forward(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String parkId = req.getParameter("parkid");
    if (parkId == null || parkId.trim().isEmpty()) {
      messages.put("title", "Invalid ParkId");
      messages.put("disableSubmit", "true");
    } else {
      // Delete the NationalPark.
      try {
    	NationalParks nationalParks = nationalParksDao.getParkById(parkId);
        nationalParks = nationalParksDao.delete(nationalParks);
        // Update the message.
        if (nationalParks == null) {
          messages.put("title", "Successfully deleted " + parkId);
          messages.put("disableSubmit", "true");
        } else {
          messages.put("title", "Failed to delete " + parkId);
          messages.put("disableSubmit", "false");
        }
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/ParkDelete.jsp").forward(req, resp);
  }
}