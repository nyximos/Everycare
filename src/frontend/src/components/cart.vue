<template>
  <div>
    <v-btn @click="removeAll">전체삭제</v-btn>
  <v-simple-table>
    <template v-slot:default>
      <thead>
        <tr>
          <th class="text-left">
            id
          </th>
          <th class="text-left">
            quantity
          </th>
          <th class="text-left">
            amount
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(item,index) in $store.state.cart.cart"
          :key="index"
        >
          <td>{{index}}/{{item.productId }}</td>
          <td>{{item.quantity}}<v-btn @click="minus(item)">-</v-btn><v-btn @click="plus(item)">+</v-btn></td>
          <td>{{item.amount * item.quantity}}</td>
          <td><v-btn @click="remove(item,index)">x</v-btn></td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  <h1>총금액 : {{total}}</h1>
  </div>
</template> 


<script>
export default {
  mounted() {
    this.$http.get('/api/cart',{
        withCredentials:true
      })
			.then((res)=>{
        console.log(res.data);
        // this.desserts=res.data.body
      }).catch(err =>{
				alert(err);
				console.log(err);
			})
  },
    methods:{
      plus(item){
        this.$store.commit("cart/incrementItemQuantity", item)
      },
      minus(item){
        if (item.quantity==1) {
          alert("최소 주문개수는 1개입니다")
        } else {      
        this.$store.commit("cart/minus", item) 
        }
      },
      remove(item,index){
      this.$http
      .delete(`/api/cart/${item.productId}`,{
      withCredentials: true
      })
      .then((res)=> {
        console.log(res)
        // console.log(this.item.index)
        this.$store.commit("cart/remoteList", index)
      }).catch((err)=>{
        console.log(err)
      })
      },
      removeAll(){
      this.$http
      .delete(`/api/cart`,{
      withCredentials: true
      })
      .then((res)=> {
        console.log(res)
        this.$store.commit("cart/removeAll")
      }).catch((err)=>{
        console.log(err)
      })
      }

    },
  computed:{
    total() {
    var total = 0;
    this.$store.state.cart.cart.forEach((item)=> {
      total += item.quantity * item.amount
    })
    return total;
     },
   }
  }

</script>

<style>

</style>