<template>
  <div>
    <h1>Create Account</h1>

    <form @submit.prevent="handleCreateAccount">
      <div>
        <label for="email">Email: </label>
        <input type="email" id="email" v-model="email" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <div>
        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" v-model="confirmPassword" required />
      </div>
      <button type="submit">Create Account</button>
    </form>

    <p v-if="message">{{ message }}</p>
    <a href="#" @click.prevent="$emit('navigate', 'Login')">Back to Login</a>
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

        if (response.data) {
          this.message = 'Account Created Successfully';
        } else {
          this.message = 'Error creating account';
        }
      } catch (error) {
        this.message = 'An error occurred. Please try again.';
      }
    },
  },
};
</script>

<style scoped>
/* Add any desired styles here */
</style>
