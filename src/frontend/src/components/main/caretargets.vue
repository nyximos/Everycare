<template>
  <v-card
    :loading="loading"
    class="mx-auto my-12"
    max-width="300"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

    <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>

    <v-card-title text-align="center">이름이름이름</v-card-title>

    <v-card-title>Tonight's availability</v-card-title>

    <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-btn color="error">
      삭제
    </v-btn>
<v-btn
      tile
      color="success"
    >
      
      수정
    </v-btn>
        <v-chip>7:30PM</v-chip>

        <v-chip>8:00PM</v-chip>

        
      </v-chip-group>
    </v-card-text>

    <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="reserve"
      >
        Reserve
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  
}
</script>

<style scoped>
v-chip-group{
text-align: center;
}
</style>