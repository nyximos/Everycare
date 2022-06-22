<template>
<div>
  <!--banner-->
 <v-card color="gray">
      <v-img
        src="../images/store.png" height="300"/>
    </v-card>

  <!--nav-->
  <v-card>
    <v-tabs
      background-color="green lighten-3"
      center-active
      dark
      class="fw-bold"
    >
      <v-tab v-for="item in prodCategory" :key="item.index" @click="nav(item)">{{item.name}}</v-tab>  
    </v-tabs>
  </v-card>

 <!--ranking-->
  <ProdRank :storeRank="storeRank" @detail="detailShot" class="mb-10"/>
    
<!--body-->
  <v-row class="mt-10">
    <v-toolbar class="elevation-0">
      <v-spacer></v-spacer>
      <v-col cols="1">
        <v-text-field
        v-model="searchText"
        @input="search"
        hide-details
        prepend-icon="mdi-magnify"
        single-line
      ></v-text-field>
      </v-col>
      
      <v-btn icon @click="goCart">
        <v-icon>mdi-cart</v-icon>
          <v-badge v-if="!this.$store.state.cart.cart.length==0" :content="this.$store.state.cart.cart.length" />
      </v-btn>

      <v-btn icon @click="wish">
        <v-icon>mdi-heart</v-icon>
          <v-badge v-if="!this.$store.state.wish.wish.length==0" :content="this.$store.state.wish.wish.length" />
      </v-btn>
      
      <v-btn icon @click="goCreateStore">
        <v-icon>mdi-login</v-icon>
      </v-btn>
    </v-toolbar>
    </v-row>  
	
  <ProdList v-if="!cateResult && !searchText" :storeList="storeList" @detail="detailShot" />
  <CateResult :cateResult="cateResult" @detail="detailShot"/>
  <SearchResult v-if="searchText" :result="result" @detail="detailShot"/>	
 
</div>  
</template>

<script>
import CateResult from '@/components/cateResult'
import SearchResult from '@/components/searchResult'
import ProdRank from '@/components/prodRank'
import ProdList from '@/components/prodList'
export default {
name: 'storeMain',
components:{
 CateResult,
 ProdList,
 ProdRank,
 SearchResult
},
mounted(){
  this.$http
	.get('/api/store/products/best',{
		withCredential: true
	})
	.then((res)=>{
		console.log(res)
		this.storeRank= res.data.body
	})
	.catch((err)=>{
		console.log(err)
	});
	this.$http
	.get('/api/store/products',{
		withCredential: true
	})
	.then((res)=>{
		console.log(res)
		this.storeList= res.data.body
	})
	.catch((err)=>{
		console.log(err)
	});
  this.$http
        .get('/api/product-categories',{
          withCredentials: true
        })
        .then(res => {
          console.log(res);
          this.prodCategory= res.data.body
        })
        .catch(err => {
          console.log(err);
        })
},
data(){
	return{
    prodCategory:this.prodCategory,
		storeList:this.storeList,
    storeRank:this.storeRank,
    searchText:this.searchText,
    result:this.result,
    searchvalue:false,
    cateResult:this.cateResult
	}
},
methods:{
  nav(item){
    this.$http
     .get('/api/store/products/category',
     {params: {categoryId: item.id}},{
        withCredentials:true
      })
		.then((res)=>{
        this.cateResult=res.data.body
        console.log(res.data);
        this.searchvalue=true
      }).catch(err =>{
				console.log(err);
			})
  },
  search(){
     this.$http
     .get('/api/store/products/name',
     {params: {name: this.searchText}},{
        withCredentials:true
      })
		.then((res)=>{
        this.result=res.data.body
        console.log(res.data);
        this.searchvalue=true
      }).catch(err =>{
				console.log(err);
			})
  },
	goCreateStore(){
		this.$router.push({
			path:'/store/new'
		})
	},
	goCart(){
		this.$router.push({
			path:'/dashboard/cart'
		})
	},
  wish(){
    this.$router.push({
      path: '/dashboard/wishlist'
    })
  },
  detailShot(id){
    this.$router.push({
    name: 'prodDetail',
    params: {
      contentId:id
      }
    })
  }
}
}
</script>

<style>

</style>