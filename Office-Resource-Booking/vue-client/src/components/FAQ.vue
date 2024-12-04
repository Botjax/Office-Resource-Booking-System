<template>
  <div class="faq-page-container">
    <!-- Sidebar -->
    <div class="side-bar">
      <img alt="Logo" src="../assets/logo.png" class="sidebar-logo">
      <p class="menu-text" style="margin-top: 15%;">MENU</p>

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
        <ul class="footer-links" >
          <li><a @click="$emit('navigate', 'AboutUs')">About Us</a></li> |
          <li><a @click="$emit('navigate', 'Faq')">FAQ</a></li>
        </ul>
        <p >&copy; 2024 Resource Booking System. All Rights Reserved.</p>
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
            <p style="font-size: medium">{{ faq.answer }}</p>
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
  min-width: 20vw;
  height: 100vh;
  position: relative;
  background: #bbdefb; /*#3480ef,#29b6f6,#2c3e50*/
  background-size: cover;
}
.side-bar button:hover{
  color: #29b6f6;
  transition-duration: 0.3s;
}
.side-bar button{
  all: unset;
  cursor: pointer;
}
:root {
  --blue: #29b6f6;
  --green: #9ccc65;
  --purple: #BA68C8;
  --cyan: #4dd0e1;
  --black-dark: #101010;
  --black-light: #2c3e50;
  --soft-pink: #ffc1e3;
  --soft-purple: #d1c4e9;
  --soft-blue: #bbdefb;
  --soft-yellow: #fff9c4;
  --white: whitesmoke;
  padding: 0;
  margin: 0;
  border: 0;
}
img{
  width: 15vw;
  height: auto;
  display: block;
  margin-top: 1.5vh;
  margin-left: 2vw;
}
p{


  display: block;
  font-size: small;
}
.dashboard-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-left: 9%;
  margin-top: 5%;
}
.dashboard-icon{
  width: 2vw;
  height: auto;
  display: block;
  margin: 0;
}
.dashboard-text {
  font-size: 1rem;
  color: #000000;
  margin: 0;
}
.book-item{
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-left: 9%;
  margin-top: 10%;
}
.book-icon{
  width: 2vw;
  height: auto;
  display: block;
  margin: 0;
}
.book-text{
  font-size: 1rem;
  color: #000000;
  margin: 0;
}
.calendar-item{
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-left: 9%;
  margin-top: 10%;
}
.calendar-icon{
  width: 2vw;
  height: auto;
  display: block;
  margin: 0;
}
.calendar-text{
  font-size: 1rem;
  color: #000000;
  margin: 0;
}

/* Sidebar Footer */
.side-footer {
  margin-top: 390px;
  text-align: center;
  font-size: 0.9rem;
  cursor: pointer;
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
