<template>
<v-container fluid>
    <h1>상품 상세정보</h1>
        <v-row class="mt-5">
            <h5 class="fw-bold">{{this.title}}</h5>
        </v-row>
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
          <v-btn color="primary">장바구니</v-btn>
          <v-btn @click="back">취소</v-btn>
      </v-container>
</template>

<script>
export default {
mounted() {
    const id = Number(this.$route.params.id);
    this.$http
    .get(`/api/products/1`,{
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
    })
    .catch((err)=>{
        console.log(err)
    })
},
data(){
    return{
        title: this.title,
        name: this.name,
        isSale: this.isSale,
        price: this.price,
        store: this.store,
        storeFileName: this.storeFileName
    }
},
methods:{
back(){
    this.$router.push({
        path:'/joblist'
    })
}
}
}
</script>

<style>

</style>