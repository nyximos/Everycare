<template>
  <div>
    <v-container class="fill-height" fluid style="min-height: 500px">
    <v-fade-transition mode="out-in">
      <v-row v-for="(c,index) in target" :key="index">
        <v-col cols="2" style="margin:0 auto;">
          <v-card @click="move(c)">
            <v-img
              :src="'https://localhost:8086/api/images/'+c.imageDTOs[index].storeFileName"
              height="250"
              class="grey darken-4"
            ></v-img>
            <v-card-title class="text-h6">
              {{c.name}}
            </v-card-title>
          </v-card>
        </v-col>
      </v-row>
    </v-fade-transition>
  </v-container>
  </div>
</template>

<script>
export default {
    data(){
        return{
            target:[]
        }
    }, 
    mounted(){
    this.$http
    .get('/api/dashboard/caretargets', {
      withCredentials: true
    })
    .then((res) => {
        console.log(res.data.body)
        this.target = res.data.body
    }).catch(err => {
        console.log(err);
    })
    },
    methods:{
        move(c){
            this.$router.push({name:'complete_memcontract' , params:{targetId:c.id}})
        }
    }
}
</script>

<style>

</style>