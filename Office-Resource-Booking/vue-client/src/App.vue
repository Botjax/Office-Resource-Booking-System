<template>
  <div id="app">
    <component :is="currentComponent" @login-success="handleLoginSuccess" @navigate="navigate" />
  </div>
</template>

<script>
import { ref, provide } from 'vue';
import Login from './components/Login.vue';
import Dashboard from './components/Dashboard.vue';
import ForgotPassword from './components/forgotPassword.vue';
import CreateAccount from './components/createAccount.vue';
import Bookings from './components/Bookings.vue';
import Calendar from './components/Calendar.vue';
import Faq from './components/FAQ.vue'; // Import FAQ component
import AboutUs from './components/aboutUs.vue'; // Import About Us component

export default {
  name: 'App',
  components: {
    Login,
    Dashboard,
    ForgotPassword,
    CreateAccount,
    Bookings,
    Calendar,
    Faq, // Register FAQ component
    AboutUs, // Register About Us component
  },
  setup() {
    // Define sessionID as a reactive ref
    const sessionID = ref(null);

    // Define a method to update sessionID
    const setSessionID = (value) => {
      console.log('Setting sessionID to:', value); // Debugging log
      sessionID.value = value; // Update the reactive ref's value
    };

    // Provide sessionID and setSessionID globally
    provide('sessionID', sessionID);
    provide('setSessionID', setSessionID);

    // Debugging log
    console.log('Session ID in setup:', sessionID);

    return {
      sessionID,
    };
  },
  data() {
    return {
      currentComponent: 'Login', // Default to the Login component
    };
  },
  methods: {
    handleLoginSuccess() {
      this.currentComponent = 'Dashboard'; // Switch to Dashboard when login is successful
    },
    navigate(component) {
      this.currentComponent = component; // Dynamically set the current component
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  padding: 0;
  margin: 0;
  border: 0;
}
</style>
