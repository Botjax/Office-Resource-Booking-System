<template>
  <div class="login">
    <div class="square">
      <header>Resource Booking <b> Made Effortless</b></header>
    </div>
    <div class="login-box">
      <div class="wrapper">
        <img alt src="../assets/logo.png">
        <header>Login</header>
        <form @submit.prevent="handleLogin" class="login-form">
          <div class="input-box">
            <div class="input-field">
              <label for="email">Email:</label>
              <input class="input" type="text" id="email" v-model="email" required />
            </div>
            <div class="input-field">
              <label for="password">Password:</label>
              <input class="input" type="password" id="password" v-model="password" required />
            </div>
            <div class="input-field">
              <button class="submission-button" type="submit"><span class="submission-text">Login</span></button>
            </div>
          </div>
        </form>

        <p v-if="errorMessage">{{ errorMessage }}</p>
        <div class="links">
          <a href="#" @click.prevent="$emit('navigate', 'ForgotPassword')">Forgot Password?</a> |
          <a href="#" @click.prevent="$emit('navigate', 'CreateAccount')">Create an Account</a>
        </div>
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

<style>
/* Styling similar to previous setup */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
html,body {
  margin: 0;
  padding: 0;
  height: 100%;
  overflow: hidden;
}
.login {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
  color: #000000;
}

.login-box {
  display: flex;
  min-width: 50vw;
  height: 100vh;
  flex-direction: column;
  justify-content: center;
}

header{
  text-align: left;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 8px;
  font-weight: 700;
  font-size: 30px;
}
.login-form {
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  position: relative;
}
.links {
  margin-top: 20px;
  font-size: 14px;
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
  padding: 0;
  margin: 0;
  border: 0;
}
.square{
  min-width: 50vw;
  height: 100vh;
  position: relative;
  background: linear-gradient(45deg,#bbdefb, #3480ef,#29b6f6,#2c3e50);
  background-size: cover;
}
.square header{
  font-size: 50px;
  color: white;
  margin-top: 45%;
  margin-left: 22%;
}

img{
  width: 20vw;
  height: auto;
  display: flex;
  margin-left: 8px;
  flex-direction: column;
  justify-content: center;
}
.input-field{
  display: flex;
  flex-direction: column;
  position: relative;
  padding: 0 10px 0 10px;
}
.input {
  height: 45px;
  width: 100%;
  background: transparent;
  border: none;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
  outline: none;
  margin-bottom: 20px;
  color: #40414a;
}

.input-box .input-field label {
  position: absolute;
  top: 10px;
  left: 10px;
  pointer-events: none;
  transition: 0.5s ease; /* Smooth transition for the effect */
}

/* Move label up and shrink on focus or when input has value */
.input-field input:focus ~ label,
.input-field input:valid ~ label,
.input-field:hover label {
  top: -10px;
  font-size: 13px;
  color: #5d5076;
}

/* Additional style when focused to add highlight */
.input-field .input:focus,
.input-field .input:valid {
  border-bottom: 1px solid #743ae1;
}
.submission-button {
  align-items: center;
  /*#bbdefb, #3480ef,#29b6f6,#2c3e50*/
  background-image: linear-gradient(144deg,#bbdefb, #3480ef 50%,#29b6f6);
  border: 0;
  border-radius: 8px;
  box-shadow: rgba(151, 65, 252, 0.2) 0 15px 30px -5px;
  box-sizing: border-box;
  color: #FFFFFF;
  display: flex;
  font-family: Phantomsans, sans-serif;
  font-size: 20px;
  justify-content: center;
  line-height: 1em;
  max-width: 50vh;
  min-width: 140px;
  padding: 3px;
  text-decoration: none;
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  white-space: nowrap;
  cursor: pointer;
  height: 40px;
}
.submission-button:active,
.submission-button:hover {
  outline: 0;
  border: none;
}

.submission-button span {
  background-color: rgb(5, 6, 45);
  padding: 5px 10px;
  border-radius: 6px;
  width: 100%;
  height:100%;
  transition: 300ms;
  font-size: 20px;
}

.submission-button:hover span {
  background: none;
}

@media (min-width: 768px) {
  .submission-button {
    font-size: 24px;
    min-width: 196px;
  }
}
.wrapper{
  padding: 0 20% 0 20%;
}
</style>
