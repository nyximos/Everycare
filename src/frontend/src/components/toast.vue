<template>
    <v-alert
      outlined
      :type="`${type}`"
      text
      max-width="300"
      max-height="50"
      style="left: 80%;"
    >
      {{message}}    
</v-alert>
</template>

<script>
export default {
props:{
  message:{
    type: String,
    required: true
  },
  type:{
    type: String,
    default: 'success'
  }
}
}
</script>

<style>

</style>