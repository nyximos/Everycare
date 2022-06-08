<template>
      <v-row>
        <p>검색결과 총 {{result.length}}건</p>
        <v-card v-for="item in result" :key="item.index" class="cards mx-auto mt-2" max-width="344">
          <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+item.storeFileName" alt="사진" width="344" height="200"/>
          <v-card-title>{{item.name}}</v-card-title>
          <v-card-text>
            <div class="mt-3">{{item.price}}원잉잉</div>
          </v-card-text>
            <v-card-actions>
              <v-btn
                color="orange lighten-2" text
                @click="detail(item)">
                detail
              </v-btn>
            </v-card-actions>
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