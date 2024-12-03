<template>
  <div class="calendar-widget-row">
    <!-- Left: Date Information -->
    <div class="date-info">
      <div class="month">{{ currentMonth }}</div>
      <div class="day">{{ currentDay }}</div>
      <div class="weekday">{{ currentWeekday }}</div>
    </div>

    <!-- Right: Reservations -->
    <div class="reservations">
      <div class="reservation-header">UPCOMING RESERVATIONS</div>
      <div
          class="reservation-item"
          v-for="(reservation, index) in events"
          :key="index"
      >
        <div class="reservation-details">
          <div class="room">{{ reservation.room }}</div>
          <div class="time">{{ reservation.time }}</div>
        </div>
        <div
            class="reservation-indicator"
            :style="{ backgroundColor: reservation.color }"
        ></div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { inject } from 'vue';

export default {
  name: "CalendarWidget",
  setup() {
    const sessionID = inject('sessionID'); // Inject the sessionID ref

    // Debugging log
    console.log('Injected sessionID in CalendarWidget:', sessionID?.value);

    return {
      sessionID,
    };
  },
  data() {
    return {
      currentDay: new Date().getDate(),
      currentWeekday: new Date().toLocaleString("default", { weekday: "long" }),
      currentMonth: new Date().toLocaleString("default", { month: "long" }),
      events: [],
    };
  },
  methods: {
    async fetchReservations() {
      try {
        // Ensure the sessionID is available
        if (!this.sessionID) {
          console.error('Session ID is missing!');
          return;
        }

        const response = await axios.post(
            `http://25.59.250.215:9490/api/dashboard/event-list`,
            {
              token: this.sessionID, // Pass the token from sessionID
            }
        );

        // Process response data
        const data = response.data;
        this.events = data.map((reservation) => ({
          room: reservation.room,
          time: this.formatTime(reservation.time),
          color: reservation.color,
        }));
      } catch (error) {
        console.error("Failed to fetch reservations:", error);
      }
    },
    formatTime(isoTime) {
      const date = new Date(isoTime);
      return date.toLocaleString("default", {
        weekday: "short",
        hour: "2-digit",
        minute: "2-digit",
        hour12: true, // Use 12-hour clock format
      });
    },
  },
  mounted() {
    this.fetchReservations();
  },
};
</script>



<style scoped>
.calendar-widget-row {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 1rem;
  background: linear-gradient(135deg, #bbdefb, rgb(52, 128, 239), #3480ef);
  border-radius: 8px;
  color: #ffffff;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  font-family: Arial, sans-serif;
  margin-left: 3vw;
  min-width: 74vw;
}
.date-info {
  display: flex;
  flex-direction: column;
  align-items: center;
  flex: 0 0 100px;
}
.date-info .month {
  font-size: 1.2rem;
  text-transform: capitalize;
  margin-bottom: 0.2rem;
  color: #ffffff;
  font-weight: bold;
}
.date-info .day {
  font-size: 3rem;
  font-weight: bold;
  line-height: 1;
  color: #ffffff;
}
.date-info .weekday {
  font-size: 1rem;
  color: #ffffff;
  margin-top: 0.2rem;
}
.reservations {
  display: flex;
  flex-direction: column;
  flex: 1;
  margin-left: 1rem;
}

.reservation-header {
  font-size: 1rem;
  font-weight: bold;
  margin-bottom: 0.5rem;
  color: #ffffff;
}

.reservation-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  background: rgba(255, 255, 255, 0.1);
  padding: 0.5rem;
  border-radius: 4px;
  margin-bottom: 0.5rem;
}

.reservation-details {
  display: flex;
  flex-direction: column;
  flex: 1;
}

.reservation-details .room {
  font-size: 1rem;
  font-weight: bold;
}

.reservation-details .time {
  font-size: 0.9rem;
  color: #ddd;
}

.reservation-indicator {
  width: 10px;
  height: 40px;
  border-radius: 5px;
}
</style>


