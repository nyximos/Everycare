<template>
<div>
  <!--carousel-->
 <v-carousel
  cycle
  hide-delimiters
  show-arrows-on-hover>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
    ></v-carousel-item>
  </v-carousel>
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
<v-container>
<!--nav-->

</v-container>

 <!--ranking-->
  <ProdRank :storeList="storeList" @detail="detailShot" class="mb-10"/>
    
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
  <CateResult :cateResult="cateResult"/>
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
    searchText:this.searchText,
		items: [
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/squirrel.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/sky.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],
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
.ranking > img:hover{
    transform:scale(1.2, 1.2);  /* 가로2배 새로 1.5배 로 커짐 */
    transition: transform.5s;  /* 커지는 시간 */
	}
</style>