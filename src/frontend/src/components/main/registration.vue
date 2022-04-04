<template>
  <v-card :loading="loading" class="mx-auto my-12" max-width="374">
    <template slot="progress">
      <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
    </template>

    <v-img height="250" src="https://cdn.vuetifyjs.com/images/cards/cooking.png"></v-img>

    <v-card-title>OOO님</v-card-title>

    <v-card-text>
      <v-chip-group v-model="selection" active-class="deep-purple accent-4 white--text" column>
        <v-chip>삭제</v-chip>
        <v-chip>수정</v-chip>
        <v-chip>보기</v-chip>
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

<style>

</style>