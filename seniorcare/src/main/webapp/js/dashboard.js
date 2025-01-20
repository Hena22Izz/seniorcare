document.addEventListener("DOMContentLoaded", function () {
    fetch("DashboardServlet")
        .then(response => response.json())
        .then(data => {
            document.getElementById("seniorCount").innerText = data.seniorCount;
            document.getElementById("caregiverCount").innerText = data.caregiverCount;
            document.getElementById("appointmentCount").innerText = data.appointmentCount;
        })
        .catch(error => console.error("Error loading dashboard data:", error));
});
