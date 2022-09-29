<template>
  <v-row>
        <v-card v-for="item in myProdList" :key="item.index" class="cards mx-auto mt-8 ml-15" max-width="350" elevation="0" @click="detail(item)">
          <v-img id="divProfile" :src="'https://localhost:8080/api/images/'+item.storeFileName" alt="사진" width="350" height="250"/>
          <v-card-text class="text-black">
            <div class="mt-3">{{item.name}}</div>
            <b>{{item.store.name}}</b>
            <p>{{item.price}} 원</p>
          </v-card-text>
        </v-card>
  </v-row>
</template>

<script>
export default {
mounted() {
    this.$http.get('/api/dashboard/store/products',{
    withCredentials:true
    })
	.then((res)=>{
        console.log(res.data);
        this.myProdList= res.data.body
    })
    .catch(err =>{
		console.log(err); 
	    })
    },
data(){
    return{
        myProdList: this.myProdList
        }
    },
methods:{
   detail(item){
      this.$emit('detail',item.id);
      this.$router.push({ 
              name: 'ProdEdit',
              params: { contentId: item.id}
              })
  }
 }
}
</script>

<style>
.cards{
  float: left;
  left: 20px;
}
</style>