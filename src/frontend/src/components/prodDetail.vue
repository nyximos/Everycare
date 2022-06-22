<template>
<div>
<Toast />
<v-container>
 <v-card>
    <v-img :src="'https://localhost:8086/api/images/'+storeFileName" alt="사진" height="600"/>
      <v-card-title class="fw-bold">{{store.name}}</v-card-title>
      <v-card-text class="text-black">
        <p>{{name}}</p>
        <hr>
        <v-row class="mt-3 mb-5">
       <b class="fs-4">{{price}}원</b>
       </v-row>
       <v-row>
            <v-col>
            <v-text-field
                v-model="quantity"
                label="수량"
                outlined
                type="number"
          ></v-text-field>
            </v-col>
            <v-col>
            <v-text-field
                v-model="startOffset"
                label="가격"
                outlined
          ></v-text-field>
            </v-col>
        </v-row>
       <v-row>
         <p class="fs-4 text-center">Details</p>
            <v-col>
                 <v-img v-for="item in this.imagesDTOs" :key="item.id" :src="'https://localhost:8086/api/images/'+item.storeFileName" alt="사진" />
            </v-col>
        </v-row>
      </v-card-text>
    <v-row>
    <v-bottom-navigation
    fixed grow
    dark
  >
    <v-btn>
      <qnaBtn />
    </v-btn>

    <v-btn>
      <reviewBtn />
    </v-btn>

    <v-btn @click="wishList">
      <wishBtn />
    </v-btn>

    <v-btn @click="cart">
      <cartBtn />
    </v-btn>
  </v-bottom-navigation>
  </v-row>
  </v-card>
</v-container>
</div>
</template>

<script>
import wishBtn from '@/components/wishBtn'
import cartBtn from '@/components/cartBtn'
import qnaBtn from '@/components/qnaBtn'
import reviewBtn from '@/components/reviewBtn'
export default {
components:{
  cartBtn,
  wishBtn,
  qnaBtn,
  reviewBtn,
},
mounted() {
    const id = Number(this.$route.params.contentId);
    this.$http
    .get(`/api/dashboard/store/products/${id}`,{
        withCredentials: true
    })
    .then((res)=>{
        console.log(res)
        this.id = res.data.body.id
        this.name = res.data.body.name
        this.isSale = res.data.body.isSale
        this.price = res.data.body.price
        this.store = res.data.body.store
        this.storeFileName = res.data.body.storeFileName
        this.imagesDTOs = res.data.body.imagesDTOs
        
    })
    .catch((err)=>{
        console.log(err)
    })
},
data(){
    return{
         sheet: false,
      tiles: [
        { img: 'keep.png', title: 'Keep' },
        { img: 'inbox.png', title: 'Inbox' },
        { img: 'hangouts.png', title: 'Hangouts' },
        { img: 'messenger.png', title: 'Messenger' },
        { img: 'google.png', title: 'Google+' },
      ],
        name: this.name,
        isSale: this.isSale,
        price: this.price,
        store: this.store,
        storeFileName: this.storeFileName,
        imagesDTOs: this.imagesDTOs,
        id: this.id,
        quantity: 1,
    }
},
methods:{
  // review(){
  //   this.$router.push({
  //     name:'reviewBtn',
  //     params: {
  //       contentId: this.id
  //     }
  //   })
  // },
back(){
    this.$router.push({
        path:'/store'
    })
},
wishList(){
    const product = {
    id: this.id,
    quantity: this.quantity,
    amount: this.startOffset
    }
    const id = Number(this.$route.params.contentId);
    var formData = new FormData();
    formData.append('id',this.id);
    this.$http
    .post(`/api/products/${id}/wish`,formData, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
      this.$store.commit('wish/pushProductToWish', product)
    })
      .catch(err => {
       console.log(err);
    });
},
cart(){
    const product = {
        id: this.id,
        quantity: this.quantity,
        amount: this.startOffset,
        name : this.name,
        image: this.storeFileName
    }
    console.log(product)
    const id = Number(this.$route.params.contentId);
    var formData = new FormData();
    formData.append('id',this.id);
    formData.append('quantity',this.quantity);
    formData.append('amount', this.startOffset);
    this.$http
    .post(`/api/products/${id}/cart`,formData, {
    withCredentials: true
    })
     .then(res => {
      console.log(res.data);
      this.$store.commit('cart/pushProductToCart', product)
      console.log(this.$store.state.cart.cart);
    })
      .catch(err => {
       console.log(err);
    });
}
},
computed:{
    startOffset() {
        return ((this.price) * this.quantity);
      },
}
}
</script>

<style>

</style>