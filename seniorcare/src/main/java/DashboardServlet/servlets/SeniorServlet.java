@WebServlet("/SeniorServlet")
public class SeniorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String dob = request.getParameter("dob");
            String phone = request.getParameter("phone");
            String medical = request.getParameter("medical");

            // Validate required fields
            if (name != null && dob != null && phone != null && medical != null) {
                try {
                    // Convert dob to Date format if necessary (assuming dob is in yyyy-MM-dd format)
                    java.sql.Date dateOfBirth = java.sql.Date.valueOf(dob);

                    // Add new Senior
                    SeniorDAO.addSenior(new Senior(name, dateOfBirth, phone, medical));
                    response.sendRedirect("dashboard.jsp");
                } catch (SQLException e) {
                    e.printStackTrace();
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding senior.");
                } catch (IllegalArgumentException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid date format.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required fields.");
            }
        }
    }
}
