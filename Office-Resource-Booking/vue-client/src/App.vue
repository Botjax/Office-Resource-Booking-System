<template>
  <div id="app">
    <component :is="currentComponent" />
    <button v-if="currentComponent === 'Login'" @click="login">Login</button>
  </div>
</template>

<script>
import Login from './components/Login.vue';
import Dashboard from './components/Dashboard.vue';

export default {
  name: 'App',
  components: {
    Login,
    Dashboard,
  },
  data() {
    return {
      currentComponent: 'Login', // Default to the Login component
    };
  },
  methods: {
    login() {
      // Logic to simulate login and navigate to Dashboard
      this.currentComponent = 'Dashboard'; // Switch to Dashboard
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
  margin-top: 60px;
}
</style>
