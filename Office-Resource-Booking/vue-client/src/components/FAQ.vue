<template>
  <div class="faq-page-container">
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

    <!-- FAQ Content -->
    <div class="faq-container">
      <div class="faq-header">
        <h1>Frequently Asked Questions</h1>
        <div class="header-line"></div>
      </div>

      <div class="faq-items">
        <div class="faq-item" v-for="(faq, index) in faqs" :key="index">
          <div class="faq-question" @click="toggleAnswer(index)">
            <span>{{ faq.question }}</span>
            <span class="arrow" :class="{ 'open': isOpen(index) }">▼</span>
          </div>
          <div v-if="isOpen(index)" class="faq-answer">
            <p>{{ faq.answer }}</p>
            <div v-if="faq.image" class="faq-image">
              <img :src="require(`../assets/download.png`)" alt="Booking Image" />
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      faqs: [
        {
          question: "How do I book a resource?",
          answer: "Go to the calendar view and select an available time slot.",
          image: true,
        },
        {
          question: "Can I cancel a booking?",
          answer: "Yes, you can cancel a booking from your bookings page.",
        },
        {
          question: "What resources can I book?",
          answer: "Meeting rooms, shared workspaces, and equipment.",
        },
        {
          question: "How do I change my booking?",
          answer: "You can modify your booking directly from the booking details page.",
        },
        {
          question: "How do I check my booking status?",
          answer: "You can check the status of your bookings from the 'Bookings' page.",
        },
        {
          question: "Can I book multiple resources at the same time?",
          answer: "Yes, you can book multiple resources as long as they are available.",
        },
        {
          question: "What happens if I miss my booking?",
          answer: "If you miss your booking, the resource will become available for others to book.",
        },
        {
          question: "How do I contact support?",
          answer: "You can contact support through your database admin or IT Manager.",
        },
      ],
      openIndex: null,
    };
  },
  methods: {
    toggleAnswer(index) {
      this.openIndex = this.openIndex === index ? null : index;
    },
    isOpen(index) {
      return this.openIndex === index;
    },
  },
};
</script>

<style scoped>
/* Page Layout */
.faq-page-container {
  display: flex;
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

/* FAQ Content */
.faq-container {
  margin-left: 320px;
  padding: 25px 20px;
  font-family: Arial, sans-serif;
  flex-grow: 1;
}

.faq-header h1 {
  font-size: 2rem;
  font-weight: bold;
}

.header-line {
  width: 100%;
  height: 2px;
  background-color: #ddd;
  margin-bottom: 20px;
}

.faq-items {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.faq-item {
  border-bottom: 1px solid #ddd;
  padding: 10px 0;
}

.faq-question {
  display: flex;
  justify-content: space-between;
  cursor: pointer;
  font-weight: bold;
}

.arrow {
  transition: transform 0.3s;
}

.arrow.open {
  transform: rotate(180deg);
}

.faq-answer {
  margin-top: 10px;
  color: #555;
}

.faq-image img {
  max-width: 100%;
  border-radius: 8px;
  margin-top: 15px;
}
</style>
