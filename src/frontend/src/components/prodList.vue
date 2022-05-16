<template>
  <div>
    <v-layout wrap>
      <v-flex xs4 class="p-1"
        v-for="(storeList, index) in storeList"
        :key="index"
        mb-2>
        <v-card outlined>
            <v-card-title>{{storeList.name}} / {{storeList.id}}</v-card-title>
          <v-card-text>
              <div>{{storeList.price}}원</div>
          </v-card-text>
            <v-card-actions>
              <v-btn
                outlined
                rounded
                text
                @click="detail">
                detail
              </v-btn>
            </v-card-actions>
        </v-card>
      </v-flex>
  </v-layout>
  </div>
</template>

<script>
export default {
props:{
    storeList:{
        required:true
    }
},
data(){
  return{
  }
},
methods:{
   detail(){
      this.$emit('detail',1);
  }
}
}
</script>

<style>

</style>