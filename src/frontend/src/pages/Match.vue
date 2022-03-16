<template>
  <v-container class="con">
      <Match />
      <Match1 />
  </v-container>
  
</template>

<script>
import Match from '@/components/main/matching'
import Match1 from '@/components/main/matching1'

export default {
  components: {
       Match,
       Match1
       },

};
</script>

<style>
  .con{
    background-color:#f4f4f4;
    padding: 30px 15px;
  }
</style>