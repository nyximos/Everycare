<template>
 <v-container>
     <h1>wish List</h1>
     <wish v-for="(listItem, index) in listData" :key="index" 
     :listItem="listItem" @drop="drop"/>
 </v-container> 
</template>

<script>
import wish from '@/components/wish.vue'
export default {
name: 'Wish',
components:{
    wish
  },
    mounted() {
        this.$http
        .get('/api/dashboard/wishlist', {
        withCredentials: true
        })
        .then(res => {
          console.log(res.data)
          this.listData = res.data.body;
        })
          .catch(err => {
          console.log(err);
        });
    },
    data(){
        return{
            listData: []
        }
    },
}
</script>

<style>

</style>