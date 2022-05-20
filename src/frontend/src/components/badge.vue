<template>
<div>
    <v-card
    class="mx-auto my-12">
    <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    />
    <v-card-title><span class="fw-bold">권희은</span>님</v-card-title>
    <v-card-text>
      <v-row>
        <v-rating
          :value="4.5"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>
      </v-row>
        <v-row>
      <div>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.</div>
      </v-row>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>보유 뱃지</v-card-title>

    <v-card-text>
      <v-chip-group>
        <v-chip>🧹✨</v-chip>

        <v-chip>🏫💙</v-chip>

        <v-chip>🍚</v-chip>
      </v-chip-group>
    </v-card-text>
  </v-card>
  </div> 
</template>

<script>
export default {

}
</script>

<style>

</style>