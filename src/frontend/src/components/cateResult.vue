<template>
  <v-row>
        <v-card v-for="item in cateResult" :key="item.index" class="cards mx-auto mt-2" max-width="344">
          <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+item.storeFileName" alt="사진" width="344" height="200"/>
          <v-card-title>{{item.name}}</v-card-title>
          <v-card-text>
            <div class="mt-3">{{item.price}}원</div>
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