<template>
  <div class="dashboard">
    <div class="side-bar">
      <img alt="Logo" src="../assets/logo.png" class="sidebar-logo">
      <p class="menu-text">MENU</p>

      <div class="dashboard-item">
        <img class="dashboard-icon" alt="Dashboard Icon" src="../assets/dash.svg">
        <button class="dashboard-text" @click="$emit('navigate', 'Dashboard')">Dashboard</button>
      </div>

      <div class="book-item">
        <img class="book-icon" alt="Book Icon" src="../assets/book.svg">
        <button class="book-text" @click="$emit('navigate', 'Bookings')">Bookings</button>
      </div>

      <div class="calendar-item">
        <img class="calendar-icon" alt="Calendar Icon" src="../assets/calendar.svg">
        <button class="calendar-text" @click="$emit('navigate', 'Calendar')">Calendar</button>
      </div>

      <!-- Footer Section -->
      <div class="footer">
        <ul class="footer-links">
          <li><a @click="$emit('navigate', 'AboutUs')">About Us</a></li>
          <li><a @click="$emit('navigate', 'Faq')">FAQ</a></li>
        </ul>
        <p>&copy; 2024 Resource Booking System. All Rights Reserved.</p>
      </div>
    </div>

    <div class="dashboard-wrapper">
      <div class="dashboard-header">
        <header class="dashboard-text">Dashboard</header>
        <div class="logout-button">
          <button @click="$emit('navigate', 'Login')">Logout</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DashboardP',
};
</script>

<style scoped>
/* General Sidebar Layout */
.side-bar {
  width: 20vw;
  height: 100vh;
  background: #bbdefb; /* Light blue */
  display: flex;
  flex-direction: column;
  justify-content: space-between; /* Ensures footer sticks to the bottom */
  padding: 20px;
  position: fixed; /* Sidebar fixed on the page */
  top: 0;
  left: 0;
  box-sizing: border-box; /* Include padding in height calculation */
}

/* Sidebar Logo */
.sidebar-logo {
  width: 80%;
  margin: 0 auto;
  display: block;
}

/* Sidebar Menu Text */
.menu-text {
  text-transform: uppercase;
  font-weight: bold;
  text-align: center;
  margin: 20px 0;
}

/* Sidebar Items */
.dashboard-item, .book-item, .calendar-item {
  display: flex;
  align-items: center;
  gap: 10px;
  margin: 10px 0;
}

.dashboard-icon, .book-icon, .calendar-icon {
  width: 24px;
  height: 24px;
}

.dashboard-text, .book-text, .calendar-text {
  font-size: 1rem;
  color: #000;
  cursor: pointer;
  background: none;
  border: none;
  outline: none;
}

.dashboard-text:hover, .book-text:hover, .calendar-text:hover {
  color: #007bff;
}

/* Footer Section */
.footer {
  text-align: center;
  margin-top: auto; /* Pushes footer to the bottom of the sidebar */
}

.footer-links {
  list-style: none;
  padding: 0;
  margin: 10px 0;
  display: flex;
  justify-content: center;
  gap: 20px;
}

.footer-links li {
  margin: 0;
}

.footer-links a {
  text-decoration: none;
  color: #000;
}

.footer-links a:hover {
  color: #007bff;
}

.footer p {
  font-size: 0.9rem;
  color: #555;
  margin-top: 10px;
}

/* Dashboard Wrapper */
.dashboard {
  display: flex;
  margin-left: 20vw; /* Matches the sidebar width */
  width: calc(100% - 20vw); /* Ensures main content fits next to sidebar */
  flex-direction: column;
}

.dashboard-header {
  padding: 10px 20px;
  font-size: 1.2rem;
  font-weight: bold;
  color: #000;
  background: #fff;
  border-bottom: 1px solid #ddd;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.logout-button button {
  background: #bbdefb;
  color: #000;
  padding: 10px 20px;
  border: none;
  border-radius: 20px;
  cursor: pointer;
  font-size: 1rem;
}

.logout-button button:hover {
  background: #007bff;
  color: #fff;
}
</style>
