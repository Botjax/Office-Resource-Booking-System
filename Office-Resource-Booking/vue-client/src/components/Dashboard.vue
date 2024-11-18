<template>
  <div class="dashboard">
    <h1>Welcome to the Dashboard</h1>
    <!-- Bookings Button -->
    <h2>Make a Booking</h2>
    <button class="bookings-button" @click="$emit('navigate', 'Bookings')">Bookings</button>
  </div>
</template>

<script>
export default {
  name: 'DashboardP',
};
</script>

<style>
.dashboard {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.bookings-button {
  padding: 10px 20px;
  font-size: 16px;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.bookings-button:hover {
  background-color: #0056b3;
}
</style>
