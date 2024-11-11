<template>
  <div class="login">
    <h1>Login</h1>
    <form @submit.prevent="handleLogin" class="login-form">
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="text" id="email" v-model="email" required />
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>

      <button type="submit">Login</button>
    </form>
    <p v-if="errorMessage">{{ errorMessage }}</p>
    <div class="links">
      <a href="#" @click.prevent="$emit('navigate', 'ForgotPassword')">Forgot Password?</a> |
      <a href="#" @click.prevent="$emit('navigate', 'CreateAccount')">Create an Account</a>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "LoginP",
  data() {
    return {
      email: '',
      password: '',
      errorMessage: '',
      loginStatus: null,
      sessionID: null,
    };
  },
  methods: {
    async handleLogin() {
      try {
        const response = await axios.post('http://25.59.250.215:9490/api/user/login', {
          email: this.email,
          password: this.password
        }, {
          headers: {
            'Content-Type': 'application/json',
          }
        });
        const{loginStatus, sessionID} = response.data;

        if (loginStatus === 'SUCCESS') {
          this.loginStatus = 'Success';
          this.sessionID = sessionID;
          this.$emit('login-success');
        } else if (loginStatus === 'INVALID_CREDENTIALS') {
          this.errorMessage = 'Invalid email or password';
        }
        else if (loginStatus === 'FAILURE'){
          this.sessionID = null;
          this.loginStatus = 'Failure';
          this.errorMessage = 'Login Failed. Please Try Again';
        }
      } catch (error) {
        this.errorMessage = 'An error occurred. Please try again.';
        console.error(error);
      }
    },
  },
};
</script>

<style scoped>
/* Styling similar to previous setup */
.login {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
}

.login-form {
  background: rgba(255, 255, 255, 0.2);
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.links {
  margin-top: 20px;
}

a {
  color: black;
  text-decoration: underline;
  cursor: pointer;
}
</style>
