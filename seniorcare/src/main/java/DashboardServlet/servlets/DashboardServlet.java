@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Fetch counts for seniors, caregivers, and appointments from their respective DAOs
            int seniorCount = SeniorDAO.getSeniorCount();
            int caregiverCount = CaregiverDAO.getCaregiverCount();
            int appointmentCount = AppointmentDAO.getAppointmentCount();

            // Prepare JSON response with the data
            response.setContentType("application/json");
            response.getWriter().write(
                String.format("{\"seniorCount\": %d, \"caregiverCount\": %d, \"appointmentCount\": %d}",
                              seniorCount, caregiverCount, appointmentCount));

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error fetching data for the dashboard.");
        }
    }
}
