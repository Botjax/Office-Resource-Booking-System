<template>
  <div class="dashboard">
    <div class="side-bar">
      <img alt src="../assets/logo.png">
      <p style="text-transform:uppercase">Menu</p>
      <div class="dashboard-item">
        <img class="dashboard-icon" alt src="../assets/dash.svg">
        <button class="dashboard-text" @click="$emit('navigate','Dashboard')">Dashboard</button>
      </div>
      <div class="book-item">
        <img class="book-icon" alt src="../assets/book.svg">
        <button class="book-text" @click="$emit('navigate','Bookings')">Bookings</button>
      </div>
      <div class="calendar-item">
        <img class="calendar-icon" alt src="../assets/calendar.svg">
        <button class="calendar-text" @click="$emit('navigate','Calendar')">Calendar</button>
       </div>
    </div>
    <div class="dashboard-wrapper">
      <div class="dashboard-header">
        <header class="dashboard-text">Dashboard</header>
        <div class="logout-button">
          <button @click="$emit('navigate','Login')">Logout</button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { inject } from 'vue';
export default {
  name:'DashboardP',
  setup() {
    const sessionID = inject('sessionID'); // Inject the sessionID ref

    // Debugging log
    console.log('Injected sessionID in Dashboard:', sessionID.value);

    return {
      sessionID,
    };
  },
  mounted() {
    console.log('Session ID:', this.sessionID); // Log the current sessionID
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
.dashboard {
  min-width: 35vh;
  max-height: 100vh;
  position: relative;
  display: flex;
}
.dashboard-header {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  border-bottom: 1.5px solid #e8e3e3;
  background-color: #ffffff;
  color: #000000;
  cursor: pointer;
  transition: background-color 0.3s ease;
  max-height: 10vh;
  min-width: 80vw;
  display: flex;
  margin-bottom: 5vh;
}
.logout-button{
  margin-left: auto;
}
.dashboard-text {
  font-size: 1.5rem;
  color: #000000;
  font-weight: bold;
  margin-top: 1.5vh;
  margin-left: 2vw;
}
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

@media (max-width: 768px) {
  .dashboard-header {
    flex-direction: column;
    text-align: center;
    padding: 1rem;
  }

  .dashboard-text {
    font-size: 1.2rem;
    margin-bottom: 0.5rem;
  }

  .dashboard-header button {
    width: 100%;
    font-size: 1rem;
  }
}
</style>