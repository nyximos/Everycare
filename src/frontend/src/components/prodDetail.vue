<template>
<v-container fluid>
    <h1>상품 상세정보</h1>
        <v-row>
          <v-col cols="4">
              <label>상품명</label>
              </v-col>
              <v-col col cols="4">
                <p>{{this.name}}</p>
              </v-col>
        </v-row>
        <v-row>
          <v-col cols="4">
              <label>가격</label>
              </v-col>
              <v-col col cols="4">
                <p>{{this.price}}</p>
              </v-col>
        </v-row>
          <v-row>
          <v-col cols="4">
              <label>스토어</label>
              </v-col>
              <v-col col cols="4">
                <p>{{this.store}}</p>
              </v-col>
        </v-row>
        <v-row>
            <v-col>
                 <v-img v-for="item in this.imagesDTOs" :key="item.id" id="divProfile" :src="'https://localhost:8086/api/images/'+item.storeFileName" alt="사진" width="344" height="200"/>
            </v-col>
        </v-row>
        <v-row>
            <v-col>
                <label>개수:</label><input type="number" class="form-control" v-model="quantity">
            </v-col>
                        <v-col>
                <label>가격:</label>{{startOffset}}
            </v-col>
        </v-row>
          <v-btn color="danger" @click="wishList">찜</v-btn>
          <v-btn color="primary" @click="cart">장바구니</v-btn>
          <v-btn @click="back">취소</v-btn>
      </v-container>
</template>

<script>
export default {
async mounted() {
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
        amount: this.startOffset
    }
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
      console.log(res);
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