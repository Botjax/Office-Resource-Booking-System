<template>
  <div>
    <h1>Forgot Password</h1>
    <form @submit.prevent="handleForgotPassword">
      <div>
        <label for="email">Enter your email: </label>
        <input type="email" id="email" v-model="email" required/>
      </div>
      <button type="submit">Send Reset Link</button>
    </form>
    <p v-if="message">{{message}}</p>
    <a href="#" @click.prevent="$emit('navigate', 'Login')">Back to Login</a>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "ForgotPassword",
  data() {
    return {
      email: '',
      message: '',
    };

  },
  methods: {
    async handleForgotPassword() {
      try {
        const response = await axios.get(`25.59.250.215`, {
          params: {
            email: this.email
          }
        });

        if (response.data.success) {
          this.message = 'Password reset link has been sent to your email';
        } else {
          this.message = 'Email not found';
        }
      } catch (error) {
        this.message = 'An error occurred. Please try again.';
        console.error(error);
      }
    },
  }

};
</script>

<style scoped>

</style>