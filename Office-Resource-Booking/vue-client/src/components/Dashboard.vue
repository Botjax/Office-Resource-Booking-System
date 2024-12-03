<template>
  <div class="dashboard">
    <div class="side-bar">
      <img alt src="../assets/logo.png" />
      <p style="text-transform:uppercase">Menu</p>
      <div class="dashboard-item">
        <img class="dashboard-icon" alt src="../assets/dash.svg" />
        <button class="dashboard-text" @click="$emit('navigate','Dashboard')">
          Dashboard
        </button>
      </div>

      <div class="book-item">

        <img class="book-icon" alt src="../assets/book.svg" />
        <button class="book-text" @click="$emit('navigate','Bookings')">
          Bookings
        </button>

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

        <img class="calendar-icon" alt src="../assets/calendar.svg" />
        <button class="calendar-text" @click="$emit('navigate','Calendar')">
          Calendar
        </button>

      </div>
    </div>

    <div class="dashboard-wrapper">
      <div class="dashboard-header">
        <header class="dashboard-text">Dashboard</header>
        <div class="logout-button">
          <button @click="$emit('navigate', 'Login')">Logout</button>
        </div>
      </div>
      <!-- Greeting and Calendar -->
      <div class="main-content">
        <!-- Greeting Section -->
        <div class="greeting-section">
          <div>
            <h1 class="greeting">
              Hello <span class="username">{{ userName }}</span>
            </h1>
          </div>
          <div>
          <p class="subtitle">Here is your daily summary</p>
          </div>
        </div>
       </div>

      <!-- Calendar Widget -->
        <div class="calendar-widget-container">
          <CalendarWidget />
        </div>
    </div>
  </div>
</template>

<script>

import { inject } from "vue";
import axios from 'axios';
import CalendarWidget from "../components/CalendarWidget.vue";

export default {
  name: "DashboardP",
  components: {CalendarWidget},
  setup() {
    const sessionID = inject("sessionID"); // Inject the sessionID ref

    return {
      sessionID,
    };
  },
  data() {
    return {
      userName: "",
    };
  },
  methods: {
    async fetchName() {
      try {
        if (!this.sessionID) {
          console.error('Token error');
          return;
        }
        const response = await axios.post(`http://25.59.250.215:9490/api/dashboard/welcome`,
            {
              token: this.sessionID,
            }
        );
        this.userName = response.data;
      } catch (e) {
        console.log(e);
      }
    },
  },
  mounted() {
    this.fetchName();
  },
};
</script>

<style scoped>

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

.dashboard-header button {
  padding: 0.8rem 2rem;
  font-size: 1rem;
  background-color: #bbdefb;
  color: #000000;
  border: none;
  border-radius: 25px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-right: 2vw;
  margin-top: 1.5vh;
}
.dashboard-header button:hover {
  background-color: #29b6f6;
}
/* Greeting section */
.greeting-section {
  display: block;
  flex-direction: row;
  justify-content: space-evenly;
  margin-left: 5vh;
  margin-right: 5vw;
  margin-bottom: 2.5vw;
  align-items: center;
}
.subtitle {
  font-size: 1.2rem;
  color: #0c87bf;
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.greeting {
  font-size: 2.5rem;
  font-weight: bold;
  color: #34495e;
  margin: 0;
  display: table-row;
  flex-direction: column;
  align-content: flex-start;
}
.username {
  color: #3480ef;
}
/* Calendar Widget */
.calendar-widget-container {
  display: flex;
  flex-direction: column;
  max-width: 50%;
}
/* Responsive Design */
@media (max-width: 768px) {
  .main-content {
    padding: 0;
    display: flex;
    flex-direction: row;
    align-items: center;
  }

  .greeting-section,
  .calendar-widget-container {
    max-width: 100%;
    text-align: center;
  }

  .calendar-widget-container {
    margin-top: 2rem;
    display: flex;
    flex-direction: column;
  }
}
</style>