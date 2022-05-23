<template>
 <div>
      <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            id
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(item,index) in $store.state.wish.wish"
          :key="index"
        >
          <td>{{index}}/{{item.productId }}</td>
          <td><v-btn @click="remove(item,index)">x</v-btn></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
 </div>  
</template>

<script>

export default {
  mounted() {
    console.log(this.$store.state.wish.wish)
  },
  methods:{
    // drop(){
    //   this.$http.delete(`/api/dashboard/wishlist/${this.listItem.id}`,{
    //   withCredentials: true
    // })
    // .then((res)=> {
    //   console.log(res)
    // }).catch((err)=>{
    //   console.log(err)
    //     })
    //   },
      remove(item,index){
      // this.$http
      // .delete(`/api/cart/${item.productId}`,{
      // withCredentials: true
      // })
      // .then((res)=> {
      //   console.log(res)
      //   console.log(this.item.index)
        this.$store.commit("wish/remoteList", index)
      // }).catch((err)=>{
      //   console.log(err)
      // })
      },
    }
  }

</script>

<style>

</style>