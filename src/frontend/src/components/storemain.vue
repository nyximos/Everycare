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
  
<v-container>
<!--nav-->
<nav class="nav d-flex justify-content-between">
      <a class="p-2 text-black" href="#">패션/잡화</a>
      <a class="p-2 text-black" href="#">화장품/미용</a>
      <a class="p-2 text-black" href="#">출산/육아</a>
      <a class="p-2 text-black" href="#">생활/건강</a>
      <a class="p-2 text-black" href="#">스포츠/레저</a>
    </nav>


<!--body-->
    </v-container>
    <!--ranking-->
      <ProdRank :storeList="storeList" @detail="detailShot" class="mb-10"/>
    
  <v-row class="mt-10">
    <v-toolbar class="elevation-0">
      <v-spacer></v-spacer>
      <v-cols col="1">
        <v-text-field
        v-model="searchText"
        @input="search"
        hide-details
        prepend-icon="mdi-magnify"
        single-line
      ></v-text-field>
      </v-cols>
      
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
    <v-row>
			<ProdList v-if="!searchvalue" :storeList="storeList" @detail="detailShot" />
    </v-row>
        <v-row>
        <SearchResult v-if="searchvalue" :result="result" @detail="detailShot"/>	
        </v-row>
 
</div>  
</template>

<script>
import SearchResult from '@/components/searchResult'
import ProdRank from '@/components/prodRank'
import ProdList from '@/components/prodList'
export default {
name: 'storeMain',
components:{
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
	})
},
data(){
	return{
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
        searchvalue:false
	}
},
methods:{
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