<template>
  <div class="bookings">
    <div class="side-bar">
      <img alt src="../assets/logo.png">
      <p style="margin-top: 15%;">MENU</p>
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
      <!-- Footer Section -->
      <footer class="side-footer">
        <ul class="footer-links">
          <li><a @click="$emit('navigate', 'AboutUs')">About Us</a></li> |
          <li><a @click="$emit('navigate', 'Faq')">FAQ</a></li>
        </ul>
        <p >&copy; 2024 Resource Booking System. All Rights Reserved.</p>
      </footer>
    </div>
    <div class="bookings-wrapper">
      <div class="bookings-header">
        <header class="bookings-text">Bookings</header>
        <div class="logout-button">
          <button @click="$emit('navigate','Login')">Logout</button>
        </div>
      </div>
      <div class="room-boxes">
        <!--        JAVASCRIPT-->
      </div>
    </div>
  </div>

</template>

<script>
import { inject } from 'vue';
import axios from 'axios';

export default {
  name: 'BookingsP',
  setup() {
    const sessionID = inject('sessionID'); // Inject the sessionID ref

    // Debugging log
    console.log('Injected sessionID in Bookings:', sessionID.value);

    return {
      sessionID,
    };
  },
  async mounted() {
    try {
      // Fetch rooms from the API
      const response = await axios.post('http://25.59.250.215:9490/api/bookings/rooms/list', {
        token: this.sessionID
      }, {
        headers: {
          'Content-Type': 'application/json',
        }
      });

      const rooms = response.data; // Assuming API returns an array of rooms

      // Debugging log to ensure rooms are fetched
      console.log('Fetched Rooms:', rooms);

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
                  <input type="time" class="start-time-input" />
                </div>
              </div>
              <div class="time-field">
                <label class="time-label">End Time</label>
                <div class="time-input-wrapper">
                  <input type="time" class="end-time-input" />
                </div>
              </div>
            </div>
            <div class="box-buttons">
              <button class="check-avail-button" data-room-name="${room.name}">Check Availability</button>
              <button class="reserve-room-button" data-room-name="${room.name}">Reserve Room</button>
            </div>
          `;

          roomBox.querySelectorAll('.check-avail-button').forEach(button => {
            button.addEventListener('click', async (e) => {
              try {
                // Get the room name from the button's data attribute
                const roomName = e.target.getAttribute('data-room-name');

                // Find the parent room box for this button
                const roomBox = e.target.closest('.room-box');

                // Extract input values for start time, end time, and date
                const dateInput = roomBox.querySelector('input[type="date"]').value;
                const startTimeInput = roomBox.querySelector('input[class="start-time-input"]').value;
                const endTimeInput = roomBox.querySelector('input[class="end-time-input"]').value;

                // Ensure all inputs are provided
                if (!dateInput || !startTimeInput || !endTimeInput) {
                  alert('Please provide date, start time, and end time.');
                  return;
                }

                // Combine date and time inputs into ISO 8601 format
                const startTime = new Date(`${dateInput}T${startTimeInput}`).toISOString();
                const endTime = new Date(`${dateInput}T${endTimeInput}`).toISOString();

                // Retrieve the security token from sessionID
                const securityToken = this.sessionID;

                // Debugging logs
                console.log('Sending POST request with:');
                console.log({ securityToken, roomName, startTime, endTime });

                // Send POST request to check availability
                const response = await axios.post('http://25.59.250.215:9490/api/bookings/rooms/check-availability', {
                  securityToken,
                  roomName,
                  startTime,
                  endTime,
                }, {
                  headers: {
                    'Content-Type': 'application/json',
                  }
                });

                const {responseStatus} = response.data;
                console.log(responseStatus);

                // Handle the response for check availability
                if (responseStatus === 'AVAILABLE') {
                  alert(`Room Available`);
                } else if (responseStatus === 'UNAVAILABLE') {
                  alert(`Room Unavailable.`);
                }
                else if (responseStatus === 'ERROR'){
                  alert('Server Error');
                }
              } catch (error) {
                console.error('Error checking availability.', error);
                alert('An error occurred while checking availability.');
              }
            });
          });
          roomBox.querySelectorAll('.reserve-room-button').forEach(button => {
            button.addEventListener('click', async (e) => {
              try {
                // Get the room name from the button's data attribute
                const roomName = e.target.getAttribute('data-room-name');

                // Find the parent room box for this button
                const roomBox = e.target.closest('.room-box');

                // Extract input values for start time, end time, and date
                const dateInput = roomBox.querySelector('input[type="date"]').value;
                const startTimeInput = roomBox.querySelector('input[class="start-time-input"]').value;
                const endTimeInput = roomBox.querySelector('input[class="end-time-input"]').value;

                // Ensure all inputs are provided
                if (!dateInput || !startTimeInput || !endTimeInput) {
                  alert('Please provide date, start time, and end time.');
                  return;
                }

                // Combine date and time inputs into ISO 8601 format
                const startTime = new Date(`${dateInput}T${startTimeInput}`).toISOString();
                const endTime = new Date(`${dateInput}T${endTimeInput}`).toISOString();

                // Retrieve the security token from sessionID
                const securityToken = this.sessionID;

                // Debugging logs
                console.log('Sending POST request with:');
                console.log({ securityToken, roomName, startTime, endTime });

                // Send POST request to check availability
                const response = await axios.post('http://25.59.250.215:9490/api/bookings/rooms/make-reservation', {
                  securityToken,
                  roomName,
                  startTime,
                  endTime,
                }, {
                  headers: {
                    'Content-Type': 'application/json',
                  }
                });
                const {responseStatus} = response.data;
                console.log(responseStatus);

                // Handle the response for reserving room button
                if (responseStatus === 'SUCCESS') {
                  alert(`Room reserved`);
                } else if (responseStatus === 'ERROR') {
                  alert(`Failed to reserve room.`);
                }
                else if (responseStatus === 'UNAVAILABLE'){
                  alert('Room unavailable during selected time');
                }
              } catch (error) {
                console.error('Error reserving room.', error);
                alert('An error occurred while reserving the room.');
              }
            });
          });

          // Add the box to the dashboard
          // Odd-indexed rooms go to the left (column 1), even-indexed to the right (column 2)
          roomBox.style.gridColumn = index % 2 === 0 ? '1' : '2';
          room_boxes.appendChild(roomBox);
        });
      };

      // Render the rooms dynamically
      renderRooms(rooms);
    } catch (error) {
      console.error('Error fetching rooms:', error);
    }
  },
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