<template>
  <div>
    <header>
      <h1>Office Resource Booking</h1>
      <button @click="logout">Logout</button>
    </header>

    <section>
      <h2>Available Office Resource</h2>
      <div>
      <div v-for="resource in resources" :key="resource.id">
        <h3>{{resource.name}}</h3>
        <p>{{resource.description}}</p>
      </div>
      </div>
      <h2> Scroll Down To Book Your Resource</h2>
    </section>

    <section>
      <h2>Book a Resource</h2>
      <form @submit.prevent="submitBooking">
        <div>
          <label for="name">Name: </label>
          <input type="text" v-model="booking.name" required />
        </div>
        <div>
          <label for="email">Email:</label>
          <input type="email" v-model="booking.email" required />
        </div>
        <div>
          <label for="resource">Resource:</label>
          <select v-model="booking.resource" required>
            <option disabled value="">Select a resource</option>
            <option v-for="resource in resources" :key="resource.id" :value="resource.name">
              {{resource.name}}
            </option>
          </select>
        </div>
        <div>
          <label for="data">Date:</label>
          <input type="date" v-model="booking.date" required />
        </div>
        <button type="submit">Book Now</button>
      </form>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      // Booking form data
      booking: {
        name: "",
        email: "",
        resource: "",
        date: "",
      },

      // Office resources
      resources: [
        {id: 1, name: "Meeting Room 1", description: "A medium-sized meeting room with 6 chairs and a projector. With Webex, Zoom, etc. meeting abilities." },
        {id: 2, name: "Meeting Room 2", description: "A small-sized meeting room with 4 chairs and a TV with added features."},
        {id: 3, name: "Hub Space 1", description: "A small-sized circle with desks and monitors for collaboration type work place."},
        {id: 4, name: "Conference Room 1", description: "A large room teleconferencing facilities and seating for 10 people."},
        {id: 5, name: "Standing Desks", description: "Adjustable-height desks for ergonomic working."},
        {id: 6, name: "Whiteboards", description: "Mobile whiteboards for brainstorming sessions."},
        {id: 7, name: "Quiet Room 1", description: "A noise free space for working and calls."},
        {id: 8, name: "Quiet Room 2", description: "A noise free space for working and calls."},
        {id: 9, name: "Wellness Room", description: "A private space for meditation, relaxation, or nursing."},
        {id: 10, name: "VR Room", description: "A dedicated space for virtual reality experiments or gaming."},
        { id: 11, name: "Photography Studio", description: "A fully-equipped studio with lights, backdrops, and cameras for photoshoots." },
      ],
    };
  },
  methods: {
    submitBooking() {
      // Hardcoded recipient email
      const recipientEmail = "makalynkline@gmail.com";

      // Email content
      const subject = `Booking Confirmation: ${this.booking.resource}`;
      const body = `Hello,

A new booking has been made:

Name: ${this.booking.name}
Email: ${this.booking.email}
Resource: ${this.booking.resource}
Date: ${this.booking.date}

Thank you,
Office Resource Booking System`;

      // Open the user's default email client
      window.location.href = `mailto:${recipientEmail}?subject=${encodeURIComponent(subject)}&body=${encodeURIComponent(body)}`;

      // Optionally, reset the form after the mailto link is triggered
      this.resetForm();
    },

    resetForm() {
      this.booking = {
        name: "",
        email: "",
        resource: "",
        date: "",
      };
    },

    logout() {
      alert("You have been logged out.");
      this.$router.push("/login");
    },
  },
};
</script>