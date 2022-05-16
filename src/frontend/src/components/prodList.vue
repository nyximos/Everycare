<template>
  <div>
        <v-card outlined>
            <v-card-title>{{storeList.name}}</v-card-title>
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

</style>