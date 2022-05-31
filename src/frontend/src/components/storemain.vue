<template>
<v-container>
  <header class="blog-header py-3">
    <div class="row flex-nowrap justify-content-between align-items-center">
      <div class="col-4 text-center">
        <p class="display-5 fw-bold">Carestore</p>
      </div>
      <div class="col-4 d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="position-relative" @click="goCart">
		🛒
		</button>
          <button class="position-relative btn-danger" @click="wish">
		❤
		</button>
		<button class="btn btn-warning" type="button" @click="goCreateStore">입점하기</button>
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

<div class="row">
	<p class="fs-2 mt-5 fw-bold">Top 3</p>
      <div class="ranking col" v-for="index in 3" :key="index">
	<img src="../images/ear.jpg" class="rounded-circle mx-auto d-block" width="140" height="140">
        <div class="text-center">
		<h2 class="mt-2">체온계</h2>
<button class="btn btn-secondary" href="#">View details &raquo;</button>
		</div>
      </div>
</div>

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
import ProdList from '@/components/prodList'
export default {
name: 'storeMain',
components:{
 ProdList
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