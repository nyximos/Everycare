<template>
 <div>
        <v-card outlined>
            <v-card-title>{{listItem.name}}</v-card-title>
          <v-card-text>
              <div>{{listItem.price}}원</div>
          </v-card-text>
            <v-card-actions>
              <v-btn
                outlined
                rounded
                text
                @click="drop">
                delete
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
            // console.log(this.listItem.id)
        //   this.$emit('drop', this.listItem.id);
      }
    }
  }

</script>

<style>

</style>