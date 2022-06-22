<template>
      <v-row>
        <p>검색결과 총 {{result.length}}건</p>
        <v-card v-for="item in result" :key="item.index" class="cards mx-auto mt-8 ml-15" max-width="350" elevation="0" @click="detail(item)">
          <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+item.storeFileName" alt="사진" width="350" height="250"/>
           <v-card-text class="text-black">
            <div class="mt-3">{{item.name}}</div>
            <b>{{item.store.name}}</b>
            <p>{{item.price}} 원</p>
          </v-card-text>
        </v-card>
  </v-row>
</template>

<script>
export default {
props:{
    result:{
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
.cards{
  float: left;
  left: 20px;
}
</style>