package park.servlet;

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



@WebServlet("/findnationalparks")
public class FindNationalParks extends HttpServlet {
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

        List<NationalParks> nationalParks = new ArrayList<NationalParks>();

        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String state = req.getParameter("state");
        if (state == null || state.trim().isEmpty()) {
            messages.put("success", "Please enter a valid state.");
        } else {
            // Retrieve BlogUsers, and store as a message.
            try {
                nationalParks = nationalParksDao.getParksByState(state);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + state);
            // Save the previous search term, so it can be used as the default
            // in the input box when rendering FindUsers.jsp.
            messages.put("previousParkstate", state);
        }
        req.setAttribute("nationalParks", nationalParks);

        req.getRequestDispatcher("/FindNationalParks.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<NationalParks> nationalParks = new ArrayList<NationalParks>();

        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindUsers.jsp).
        String state = req.getParameter("state");
        if (state == null || state.trim().isEmpty()) {
            messages.put("success", "Please enter a valid parkId.");
        } else {
            // Retrieve BlogUsers, and store as a message.
            try {
            	nationalParks = nationalParksDao.getParksByState(state);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
            messages.put("success", "Displaying results for " + state);
        }
        req.setAttribute("nationalParks", nationalParks);

        req.getRequestDispatcher("/FindNationalParks.jsp").forward(req, resp);
    }

}
