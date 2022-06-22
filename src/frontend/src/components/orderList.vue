<template>
    <div>
        <h3>{{ this.$store.state.userStore.id }}님 주문내역</h3>
         <v-card v-for="item in orderList" :key="item.index" class="cards mx-auto mt-8 ml-15 mb-4" width="1600">
          <v-card-text class="text-black">
            <p>{{item.product.name}}</p>
            <b>{{item.product.store.name}}</b>
            <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+item.product.storeFileName" 
                                    alt="사진" width="100" height="100"/>
            <div class="mt-3">{{item.name}}</div>
            <p>{{item.amount}} 원</p>
            <v-card-action>
                <v-btn block @click="goReview(item)">후기글 쓰기</v-btn>
            </v-card-action>
            
          </v-card-text>
        </v-card>
    </div>
</template>

<script>
export default {
    mounted() {
        this.$http.get('/api/dashboard/orders/payment-history',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.orderList = res.data.body
      }).catch(err =>{
				console.log(err);
			})
    },
    data() {
        return {
            orderList: this.orderList
        }
    },
    methods:{
        goReview(item){
            console.log('hi')
            this.$router.push({
                name: 'NewReview',
                params: {
                    contentId: item.product.id
                }
            })
        }
    }
}
</script>

<style>

</style>
