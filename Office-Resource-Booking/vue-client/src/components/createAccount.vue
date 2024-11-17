<template>
  <div class="createAccount">
    <div class="square">
      <header>Resource Booking <b> Made Effortless</b></header>
    </div>
    <div class="createAccount-box">
      <div class="wrapper">
        <img alt="Company Logo" src="../assets/logo.png">
        <header>Create Account</header>
        <form @submit.prevent="handleCreateAccount" class="createAccount-form">
          <div class="createAccount-input-wrapper">
            <input type="email" id="email" v-model="email" class="createAccount-input" required />
            <label for="email">Email:</label>
          </div>
          <div class="createAccount-input-wrapper">
            <input type="password" id="password" v-model="password" class="createAccount-input" required />
            <label for="password">Password:</label>
          </div>
          <div class="createAccount-input-wrapper">
            <input type="password" id="confirmPassword" v-model="confirmPassword" class="createAccount-input" required />
            <label for="confirmPassword">Confirm Password:</label>
          </div>
          <div class="'createAccount-input-wrapper">
            <button class="submission-button" type="submit"><span class="submission-text">Create Account</span></button>
          </div>
        </form>

        <p v-if="message">{{ message }}</p>
        <a href="#" @click.prevent="$emit('navigate', 'Login')">Back to Login</a>
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";

export default {
  name: "CreateAccount",
  data() {
    return {
      email: '',
      password: '',
      confirmPassword: '',
      message: '',
      createStatus: null,
    };
  },
  methods: {
    async handleCreateAccount() {
      // Check if passwords match
      if (this.password !== this.confirmPassword) {
        this.message = 'Passwords do not match.';
        return;
      }

      try {
        const response = await axios.post('http://25.59.250.215:9490/api/user/create', {
          email: this.email,
          password: this.password
        }, {
          headers: {
            'Content-Type': 'application/json',
          }
        });
        const createStatus = response.data;

        if (createStatus === 'SUCCESS') {
          this.message = 'Account Created Successfully';
        } else if (createStatus === 'EMAIL_TAKEN') {
          this.message = 'The email provided is already taken.';
        }
        else if (createStatus === 'INVALID_EMAIL'){
          this.message = 'Invalid Email. Please provide a valid email.';
        }
        else if (createStatus === 'FAILURE'){
          this.message = 'Failure Creating Account';
        }
      } catch (error) {
        this.message = 'An error occurred. Please try again.';
      }
    },
  },
};
</script>


<style>
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
.createAccount-box {
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
  margin-left: 8px;
  margin-top: 8px;
  margin-bottom: 16px;
  font-weight: 700;
  font-size: 30px;
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
.createAccount {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  height: 100vh;
  text-align: center;
  color: #000000;
}
.createAccount-input {
  height: 45px;
  width: 100%;
  background: transparent;
  border: none;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
  outline: none;
  margin-bottom: 20px;
  color: #40414a;
}

.createAccount-input-wrapper {
  display: flex;
  flex-direction: column;
  position: relative;
  padding: 0 10px;
  margin-bottom: 20px;
}

/* Label floating effect */
.createAccount-input-wrapper label {
  position: absolute;
  top: 10px;
  left: 10px;
  pointer-events: none;
  transition: 0.3s ease;
}

.createAccount-input-wrapper input:focus ~ label,
.createAccount-input-wrapper input:not(:placeholder-shown) ~ label {
  top: -10px;
  font-size: 13px;
  color: #5d5076;
}

/* Additional style when input is focused */
.createAccount-input-wrapper input:focus {
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
</style>

