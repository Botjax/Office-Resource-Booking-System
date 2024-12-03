<template>
  <div class="calendar">
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
        <img class="calendar-icon" alt src="../assets/calendar.svg" />
        <button class="calendar-text" @click="$emit('navigate','Calendar')">
          Calendar
        </button>
      </div>
    </div>

    <div class="calendar-wrapper">
      <div class="calendar-header">
        <header class="calendar-header-text">Calendar</header>
        <div class="logout-button">
          <button @click="$emit('navigate','Login')">Logout</button>
        </div>
      </div>

      <!-- Calendar Grid -->
      <div class="calendar-grid">
        <div class="day-header" v-for="(day, index) in daysOfWeek" :key="index">
          {{ day }}
        </div>
        <div
            v-for="(day, index) in days"
            :key="index"
            class="calendar-day"
            :class="{ 'empty-day': !day, 'filled-day': day }"
        >
          <div v-if="day">
            <span class="date">{{ day.date }}</span>
            <div class="events">
              <span
                  v-for="(event, eventIndex) in day.events"
                  :key="eventIndex"
                  class="event"
                  :style="{ backgroundColor: event.color }"
              >
                {{ event.title }}
              </span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { inject } from 'vue';

export default {
  name: 'CalendarP',
  setup() {
    const sessionID = inject('sessionID');
    console.log('Injected sessionID in Calendar:', sessionID.value);

    return {
      sessionID,
    };
  },
  data() {
    return {
      currentMonth: new Date().toLocaleString('default', { month: 'long' }),
      currentYear: new Date().getFullYear(),
      daysOfWeek: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
      days: [], // Holds days and events
      events: [
        { date: '2024-11-01', title: 'Upcoming Reservation', color: '#ffcc00' },
        { date: '2024-11-03', title: 'Room Reserved', color: '#00c853' },
        { date: '2024-11-05', title: 'Room Reserved', color: '#00c853' },
        // More events
      ],
    };
  },
  methods: {
    generateCalendar() {
      const today = new Date(this.currentYear, new Date().getMonth(), 1);
      const firstDay = today.getDay() === 0 ? 7 : today.getDay(); // Start day (adjust for Monday start)
      const totalDays = new Date(this.currentYear, today.getMonth() + 1, 0).getDate();

      const days = [];
      let dayCounter = 1;

      for (let i = 0; i < 42; i++) {
        if (i >= firstDay - 1 && dayCounter <= totalDays) {
          const formattedDate = `${this.currentYear}-${String(
              today.getMonth() + 1
          ).padStart(2, '0')}-${String(dayCounter).padStart(2, '0')}`;
          days.push({
            date: dayCounter,
            events: this.events.filter((event) => event.date === formattedDate),
          });
          dayCounter++;
        } else {
          days.push(null); // Empty days for padding
        }
      }
      this.days = days;
    },
    prevMonth() {
      const current = new Date(this.currentYear, new Date().getMonth() - 1, 1);
      this.updateMonthYear(current);
    },
    nextMonth() {
      const current = new Date(this.currentYear, new Date().getMonth() + 1, 1);
      this.updateMonthYear(current);
    },
    updateMonthYear(date) {
      this.currentMonth = date.toLocaleString('default', { month: 'long' });
      this.currentYear = date.getFullYear();
      this.generateCalendar();
    },
  },
  mounted() {
    this.generateCalendar();
  },
};
</script>

<style scoped>
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
  margin-right: 65%;
  margin-top: 15%;
  display: block;
  font-size: small;
}
.side-bar .dashboard-item {
  display: flex;
  align-items: center;
  gap: 0.5rem;
  margin-left: 9%;
  margin-top: 5%;
}
.side-bar .dashboard-icon{
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
.calendar {
  min-width: 35vh;
  max-height: 100vh;
  position: relative;
  display: flex;
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
.calendar-header {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  border-bottom: 1.5px solid #e8e3e3;
  background-color: #ffffff;
  color: #000000;
  transition: background-color 0.3s ease;
  max-height: 10vh;
  min-width: 80vw;
  display: flex;
  margin-bottom: 5vh;
}
.logout-button{
  margin-left: auto;
  cursor: pointer;
}
.calendar-header-text {
  font-size: 1.5rem;
  color: #000000;
  font-weight: bold;
  margin-top: 1.5vh;
  margin-left: 2vw;
}
.calendar-header button {
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
.calendar-header button:hover {
  background-color: #29b6f6;
}
@media (max-width: 768px) {
  .calendar-header {
    flex-direction: column;
    text-align: center;
    padding: 1rem;
  }

  .calendar-header-text {
    font-size: 1.2rem;
    margin-bottom: 0.5rem;
  }

  .calendar-header button {
    width: 100%;
    font-size: 1rem;
  }
}
.calendar-grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 10px;
  padding: 1rem;
}

.day-header {
  text-align: center;
  font-weight: bold;
}

.calendar-day {
  min-height: 100px;
  border: 1px solid #ddd;
  background: #f9f9f9;
  border-radius: 5px;
  position: relative;
}

.empty-day {
  background: transparent;
}

.date {
  position: absolute;
  top: 10px;
  left: 10px;
  font-size: 14px;
  font-weight: bold;
}

.events {
  position: absolute;
  bottom: 10px;
  left: 10px;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.event {
  padding: 2px 5px;
  border-radius: 3px;
  font-size: 12px;
  color: white;
  text-align: center;
}

</style>