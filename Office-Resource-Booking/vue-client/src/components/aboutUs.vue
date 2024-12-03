<template>
  <div class="about-page-container">
    <!-- Sidebar -->
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
      <footer class="side-footer">
        <ul class="footer-links">
          <li><a @click="$emit('navigate', 'AboutUs')">About Us</a></li>
          <li><a @click="$emit('navigate', 'Faq')">FAQ</a></li>
        </ul>
        <p>&copy; 2024 Resource Booking System. All Rights Reserved.</p>
      </footer>
    </div>

    <!-- Logout Button -->
    <div class="logout-button">
      <button @click="$emit('navigate', 'Login')">Logout</button>
    </div>

    <!-- Main Content -->
    <div class="about-content">
      <h1>About Us</h1>
      <p>
        Welcome to our resource booking system! Our mission is to simplify the process of managing and booking office resources.
        Whether you need a meeting room, shared workspace, or equipment, we've got you covered.
        Our platform is designed to be user-friendly and efficient, helping teams stay organized and productive. If you have any
        questions or suggestions, feel free to reach out to our support team.
      </p>
      <h1>Meet the Team</h1>
      <h2>Jordan Lewis</h2>
      <p><b>Role:</b> Front-End UI Developer</p>
      <p><b>About Me:</b> I have a strong passion for Front-End development as well as Cyber Security, as well as keeping learning new things to expand my learning.</p>
      <h2>Makalyn Kline</h2>
      <p><b>Role:</b> Database Administrator</p>
      <p><b>About Me:</b> I have an emphasis in data science, and my goal is to become a machine learning engineer.</p>
      <h2>Jack Madison</h2>
      <p><b>Role:</b> Back-End API Developer</p>
      <p><b>About Me:</b> I have a strong interest in backend and video game development. My goal is to hone my skills in API design and systems architecture.</p>
      <h2>Debora Andrade</h2>
      <p><b>Role:</b> Front-End UI/UX Developer</p>
      <p><b>About Me:</b> I have a passion for creating intuitive and engaging user experiences. My focus is on developing my front-end skills to design and build seamless interfaces that enhance usability and aesthetics.</p>
    </div>
  </div>
</template>

<script>
export default {
  name: "AboutUs",
};
</script>

<style scoped>
/* General Layout */
.about-page-container {
  display: flex;
  flex-direction: row;
  min-height: 100vh;
}

/* Sidebar */
.side-bar {
  width: 300px;
  background-color: #bbdefb;
  height: 100vh;
  position: fixed;
  top: 0;
  left: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.sidebar-logo {
  width: 80%;
  margin-bottom: 20px;
}

.menu-text {
  font-weight: bold;
  font-size: 1.2rem;
  margin-bottom: 20px;
  text-align: center;
}

.dashboard-item, .book-item, .calendar-item {
  display: flex;
  align-items: center;
  margin-top: 15px;
  width: 100%;
}

.dashboard-icon, .book-icon, .calendar-icon {
  width: 24px;
  height: 24px;
  margin-right: 10px;
}

.dashboard-text, .book-text, .calendar-text {
  font-size: 1rem;
  background: none;
  border: none;
  cursor: pointer;
  color: #000;
  text-align: left;
  width: 100%;
  outline: none;
}

.dashboard-text:hover, .book-text:hover, .calendar-text:hover {
  color: #007bff;
}

/* Sidebar Footer */
.side-footer {
  margin-top: auto;
  text-align: center;
  font-size: 0.9rem;
}

.side-footer ul {
  list-style: none;
  padding: 0;
  display: flex;
  gap: 1rem;
  justify-content: center;
}

.side-footer ul li {
  margin: 0;
}

.side-footer ul li a {
  text-decoration: none;
  color: #000;
}

.side-footer ul li a:hover {
  color: #007bff;
}

/* Logout Button */
.logout-button {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
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

/* About Content */
.about-content {
  margin-left: 320px;
  padding: 28px 20px;
  font-family: Arial, sans-serif;
}

.about-content h1 {
  font-size: 2rem;
  font-weight: bold;
}

.about-content p {
  font-size: 1.2rem;
  line-height: 1.6;
  margin-bottom: 15px;
  color: #555;
}
</style>
