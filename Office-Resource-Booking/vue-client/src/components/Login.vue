<template>
  <div class="login">
    <div class="square"></div>
    <div class="login-box">
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

        if (response.data) {
          this.errorMessage = '';
          this.$emit('login-success');

        } else {
          this.errorMessage = 'Invalid email or password';
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
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
  color: #000000;
}

.login-box{
  display: flex;
  min-width: 50%;
  height: 100%;
  flex-direction: column;
  justify-content: center;
}

.login-form {
  background: rgba(186, 206, 250, 0.86);
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  display: flex;
  flex-direction: column;
}

.links {
  margin-top: 20px;
}

a {
  color: black;
  text-decoration: underline;
  cursor: pointer;
}

:root{
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
}

.html{
  margin: 0;
  border: 0;
}

.square{
    min-width: 50%;
    height: 200vh;
    position: relative;
    background: linear-gradient(45deg,#bbdefb,#d1c4e9,#29b6f6,#2c3e50);
    background-size: cover;
}

</style>
