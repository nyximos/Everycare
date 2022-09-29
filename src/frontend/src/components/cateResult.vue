<template>
  <v-row>
        <v-card v-for="item in cateResult" :key="item.index" class="cards mx-auto mt-8 ml-15" max-width="350" elevation="0" @click="detail(item)">
          <v-img id="divProfile" :src="'https://localhost:8080/api/images/'+item.storeFileName" alt="사진" width="350" height="250"/>
          <v-card-title>{{item.name}}</v-card-title>
          <v-card-text>
            <div class="mt-3">{{item.price}}원</div>
          </v-card-text>
        </v-card>
  </v-row>
</template>

<script>
export default {
    props:{
    cateResult:{
        required: true,
    }
    },
    methods:{
   detail(item){
      this.$emit('detail',item.id);
  }
 }
}
</script>

<style>

</style>