<template>
  <v-card
    class="mx-auto"
    max-width="400"
  >
    <v-img
      class="white--text align-end"
      height="200px"
      src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
    >
      
    </v-img>

    <v-card-subtitle class="pb-0">
      <button class="add">추가</button>
    </v-card-subtitle>
    <br>
    <v-card-text class="text--primary"> 
      <textarea class="textbox"> 
        내용을 입력해주세요.
      </textarea>
    </v-card-text>

    <v-card-actions>
      <v-btn
        color="orange"
        text
      >
        등록
      </v-btn>

      <v-btn
        color="orange"
        text
      >
        취소
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {

}
</script>

<style>
  .add{
    margin: 0 auto;
    display: block;
    width: 50%;
    height: 40px;
    color: black;
    background-color: #777;
  }
  .text--primary .textbox{
    width: 100%;
    height: 100px;
    color: black;
    background: #777;
  }
</style>