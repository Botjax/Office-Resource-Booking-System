<template>
  <div id="app">
    <component :is="currentComponent" @login-success="handleLoginSuccess"   @navigate="navigate" />
  </div>
</template>

<script>
import Login from './components/Login.vue';
import Dashboard from './components/Dashboard.vue';
import ForgotPassword from './components/forgotPassword.vue'
import CreateAccount from "./components/createAccount.vue";


export default {
  name: 'App',
  components: {
    Login,
    Dashboard,
    ForgotPassword,
    CreateAccount,
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
      this.currentComponent = component;
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
