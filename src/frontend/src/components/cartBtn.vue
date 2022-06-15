<template>
    <div>
    <v-btn
      dark
      @click="snackbar = true"
    >
      <span>Cart</span>
      <v-icon>mdi-cart</v-icon>
    </v-btn>
    <v-snackbar
      shaped
      class="mb-15"
      v-model="snackbar"
      :timeout="timeout"
    >
      {{ text }}
    </v-snackbar>
  </div>
</template>

<script>
export default {
data(){
    return{
        snackbar: false,
        text: '장바구니에 담겼습니다!',
        timeout: 1000,
    }
  }
}
</script>

<style>

</style>