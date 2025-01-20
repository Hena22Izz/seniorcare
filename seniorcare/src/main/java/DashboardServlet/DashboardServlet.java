package DashboardServlet;

import DashboardServlet.dao.CaregiverDAO;
import DashboardServlet.models.Caregiver;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DashboardServlet extends HttpServlet {
    private CaregiverDAO caregiverDAO;

    @Override
    public void init() throws ServletException {
        caregiverDAO = new CaregiverDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("view".equals(action)) {
                List<Caregiver> caregivers = caregiverDAO.getAllCaregivers();
                request.setAttribute("caregivers", caregivers);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database Error");
        }
    }
}
