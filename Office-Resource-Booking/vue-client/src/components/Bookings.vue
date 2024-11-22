<template>
  <div class="bookings">
    <div class="sideBar">
      <img alt src="../assets/logo.png">
      <p style="text-transform:uppercase">Menu</p>
      <div class="dashboard-item">
      <img class="dashboard-icon" alt src="../assets/dash.svg">
      <span class="dashboard-text">Dashboard</span>
      </div>
      <div class="book-item">
        <img class="book-icon" alt src="../assets/book.svg">
        <span class="book-text">Bookings</span>
      </div>
      <div class="calendar-item">
        <img class="calendar-icon" alt src="../assets/calendar.svg">
        <span class="calendar-text">Calendar</span>
      </div>
    </div>
    <div class="bookings-wrapper">
      <div class="bookings-header">
        <header class="bookings-text">Bookings</header>
        <div class="logout-button">
          <button @click="logout">Logout</button>
        </div>
      </div>
      <div class="room-boxes">
        <!--        JAVASCRIPT-->
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: 'BookingsP',
  mounted() {
    //API data
    const rooms = [
      { id: 1, name: "Meeting Room 1" },
      { id: 2, name: "Meeting Room 2" },
      { id: 3, name: "Hub Space" },
      { id: 4, name: "Quiet Room 1" },
      { id: 5, name: "Quiet Room 2" },
      { id: 6, name: "Conference Room" },
    ];

    // Function to render rooms dynamically
    const renderRooms = (roomList) => {
      const room_boxes = document.querySelector('.room-boxes');

      roomList.forEach((room, index) => {
        // Create a room box
        const roomBox = document.createElement('div');
        roomBox.className = 'room-box';

        // Add room content
        roomBox.innerHTML = `
      <h3 class="room-box-text">${room.name}</h3>
      <div class="time-field">
        <div class="date-text-box">
            <label class="time-label">Date</label>
        </div>
        <div class="time-input-wrapper">
          <input type="date" class="time-input" />
        </div>
      </div>
      <div class="time-wrapper">
      <div class="time-field">
        <label class="time-label">Start Time</label>
        <div class="time-input-wrapper">
          <input type="time" class="time-input" />
        </div>
      </div>
      <div class="time-field">
        <label class="time-label">End Time</label>
        <div class="time-input-wrapper">
          <input type="time" class="time-input" />
        </div>
      </div>
      </div>
      <div class="box-buttons">
        <button class="check-avail-button">Check Availability</button>
        <button class="reserve-room-button">Reserve Room</button>
      </div>

    `;

        // Add the box to the dashboard
        // Odd-indexed rooms go to the left (column 1), even-indexed to the right (column 2)
        roomBox.style.gridColumn = index % 2 === 0 ? '1' : '2';
        room_boxes.appendChild(roomBox);
      });
    };

    // Render the rooms
    renderRooms(rooms);
  }
};
</script>

<style>
.room-box {
    border: 1px solid #ffffff;
    padding: 11px;
    border-radius: 8px;
    max-width: 392px;
    box-shadow: 0 2px 4px rgb(195, 194, 194);
    justify-content: center;
    align-items: center;
  }

.room-boxes{
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
  margin-left: 5vw;
  margin-right: 5vw;
  align-items: center;
  max-height: 85vh;
  overflow-y: auto;
  margin-bottom: 10vh;
}
.room-box .reserve-room-button {
  padding: 0.5rem 1rem;
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
.room-box .reserve-room-button:hover {
  background-color: #29b6f6;
}

@media (max-width: 768px) {
  .room-box .room-boxes {
    flex-direction: column;
    text-align: center;
    padding: 1rem;
  }

  .room-box .room-box-text {
    font-size: 20rem;
    margin-bottom: 0.5rem;
  }

  .room-box .reserve-room-button button {
    width: 100%;
    font-size: 1rem;
  }
}
.room-box .check-avail-button {
  padding: 0.5rem 1rem;
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
.room-box .check-avail-button:hover {
  background-color: #29b6f6;
}

@media (max-width: 768px) {
  .room-box .room-boxes {
    flex-direction: column;
    text-align: center;
    padding: 1rem;
  }

  .room-box .room-box-text {
    font-size: 20rem;
    margin-bottom: 0.5rem;
  }

  .room-box .check-avail-button button {
    width: 100%;
    font-size: 1rem;
  }
}
.room-box .time-wrapper{
  display: flex;
  justify-content: space-evenly;
  align-items: center;
}

.room-box .box-buttons {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: auto;
}

.room-box .box-buttons button {
  font-size: small;
}

.room-box .time-field .time-input-wrapper{
  align-items: center;
  justify-content:center;
  display: flex;
  max-height: 30px;
}

.room-box .time-field .time-input-wrapper .time-input[type="date"] {
  min-width: 290px;
}

.room-box .time-field {
  margin: 0.5rem 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.room-box .time-label {
  font-size: 0.9rem;
  color: #757575;
  margin-bottom: 0.3rem;
  justify-content: flex-start;
  text-align: left;
  width: 100%;
  display: block;
}

.room-box .date-text-box {
  font-size: 0.9rem;
  color: #757575;
  margin-bottom: 0.3rem;
  justify-content: flex-start;
  text-align: left;
  width: 100%;
  display: block;
}

.room-box .date-text-box .time-label {
  margin-left: 20px;
}

.room-box .time-input-wrapper {
  display: flex;
  align-items: center;
  background-color: #f9f9f9;
  border: 1px solid #e0e0e0;
  border-radius: 25px;
  padding: 0.5rem 1rem;
}
.room-box .time-input {
  border: none;
  background: transparent;
  font-size: 1rem;
  color: #000;
  outline: none;
  width: 100%;
  margin-left: 0.5rem;
}
.room-box .time-icon {
  font-size: 1.2rem;
  color: #757575;
}

.room-box .time-input-wrapper:hover {
  border-color: #29b6f6;
}

.room-box .time-input:focus {
  color: #29b6f6;
}
</style>
<style scoped>
.sideBar {
    min-width: 20vw;
    height: 100vh;
    position: relative;
    background: #bbdefb; /*#3480ef,#29b6f6,#2c3e50*/
    background-size: cover;
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
.bookings {
  min-width: 35vh;
  max-height: 100vh;
  position: relative;
  display: flex;
}
.bookings-header {
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

.bookings-text {
  font-size: 1.5rem;
  color: #000000;
  font-weight: bold;
  margin-top: 1.5vh;
  margin-left: 2vw;
}

.bookings-header button {
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

.bookings-header button:hover {
  background-color: #29b6f6;
}

@media (max-width: 768px) {
  .bookings-header {
    flex-direction: column;
    text-align: center;
    padding: 1rem;
  }

  .bookings-text {
    font-size: 1.2rem;
    margin-bottom: 0.5rem;
  }

  .bookings-header button {
    width: 100%;
    font-size: 1rem;
  }
}


</style>