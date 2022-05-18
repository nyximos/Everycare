<template>
 <div>
        <v-card class="mx-auto mt-2" max-width="344">
          <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+this.listItem.storeFileName" alt="사진"/>
          <v-card-title>{{listItem.name}}</v-card-title>
          <v-card-text>
            <div class="mt-3">{{listItem.price}}원</div>
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
        listItem: {
            required: true
        }
    },
    data(){
        return{
            // id : listItem.id,
        }
    },
    methods:{
        drop(){
            this.$http.delete(`/api/dashboard/wishlist/${this.listItem.id}`,{
      withCredentials: true
    })
    .then((res)=> {
      console.log(res)
    }).catch((err)=>{
      console.log(err)
        })
      }
    }
  }

</script>

<style>

</style>