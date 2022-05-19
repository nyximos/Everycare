<template>
  <div>
        <v-card class="cards mx-auto mt-2" max-width="344">
          <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+this.storeList.storeFileName" alt="사진" width="344" height="200"/>
          <v-card-title>{{storeList.name}}</v-card-title>
          <v-card-text>
            <div class="mt-3">{{storeList.price}}원</div>
          </v-card-text>
            <v-card-actions>
              <v-btn
                color="orange lighten-2" text
                @click="detail">
                detail
              </v-btn>
            </v-card-actions>
        </v-card>
  </div>
</template>

<script>
export default {
props:{
    storeList:{
        required:true
    }
},
methods:{
   detail(){
      this.$emit('detail',this.storeList.id);
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