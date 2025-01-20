@WebServlet("/CaregiverServlet")
public class CaregiverServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String specialty = request.getParameter("specialty");
            String phone = request.getParameter("phone");

            // Check if all required parameters are provided
            if (name != null && specialty != null && phone != null) {
                try {
                    // Adding the caregiver using CaregiverDAO
                    CaregiverDAO.addCaregiver(new Caregiver(name, specialty, phone));
                    response.sendRedirect("dashboard.jsp");
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding caregiver.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required fields.");
            }
        }
    }
}
