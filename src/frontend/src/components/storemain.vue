<template>
<v-container>
  <header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 text-center">
        <p class="display-5 fw-bold">Carestore</p>
      </div>
      <div class="col-4 d-grid gap-2 d-md-flex justify-content-md-end">
        
          <v-icon  @click="goCart">mdi-cart</v-icon>
          <v-badge overlap v-if="!this.$store.state.cart.cart.length==0" :content="this.$store.state.cart.cart.length" />
          <v-icon  @click="wish">mdi-heart</v-icon>
          <v-badge overlap v-if="!this.$store.state.wish.wish.length==0" :content="this.$store.state.wish.wish.length" />
          <v-icon @click="goCreateStore">mdi-login</v-icon>
      </div>
    </div>
  </header>
  
<nav class="nav d-flex justify-content-between">
      <a class="p-2 text-black" href="#">패션/잡화</a>
      <a class="p-2 text-black" href="#">화장품/미용</a>
      <a class="p-2 text-black" href="#">출산/육아</a>
      <a class="p-2 text-black" href="#">생활/건강</a>
      <a class="p-2 text-black" href="#">스포츠/레저</a>
    </nav>
<!--carousel-->
<div class="container">
 <v-carousel hide-delimiters>
    <v-carousel-item
      v-for="(item,i) in items"
      :key="i"
      :src="item.src"
    ></v-carousel-item>
  </v-carousel>
</div>

<!--ranking-->
      <ProdRank :storeList="storeList" @detail="detailShot" />
<!--body-->
        <p class="display-6 text-center mt-5">Menu</p>
      <v-row>
      <v-col cols="10"></v-col>
      <v-col cols="2">
          <v-text-field
            v-model="searchText"
            @input="search"
            filled
            dense
            rounded
            label="search"
            prepend-inner-icon="mdi-magnify"
          ></v-text-field>
      </v-col>
    </v-row>
			<ProdList v-for="(storeList, index) in storeList"
        :key="index"
        mb-2 :storeList="storeList" @detail="detailShot" />		
</v-container>   
</template>

<script>
import ProdRank from '@/components/prodRank'
import ProdList from '@/components/prodList'
export default {
name: 'storeMain',
components:{
 ProdList,
 ProdRank
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
            src: 'https://cdn.vuetifyjs.com/images/carousel/bird.jpg',
          },
          {
            src: 'https://cdn.vuetifyjs.com/images/carousel/planet.jpg',
          },
        ],
	}
},
methods:{
  search(){
     this.$http.get('/api/store/products/name',{params: {name: this.searchText}},{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.addrInfo=res.data.body
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