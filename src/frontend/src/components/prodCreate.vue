<template>
<v-container>
<v-card>
  <v-card-title>상품등록</v-card-title>
  <v-card-text>
    <v-text-field
      v-model="name"
      placeholder="상품명"
      filled
      rounded
      dense
    ></v-text-field>
    <v-text-field
      v-model="price"
      placeholder="가격"
      filled
      rounded
      dense
    ></v-text-field>
      <v-text-field
      v-model="inventoryQuantity"
      placeholder="재고수량"
      filled
      rounded
      dense
    ></v-text-field>
      <v-textarea
        v-model="comment"
        filled
        auto-grow
        label="설명"
        shaped
      ></v-textarea>
      <v-file-input
      v-model="attachFile"
      chips
      label="썸네일"
    ></v-file-input>
    <v-file-input
      v-model="attachFiles"
      chips
      multiple
      label="상품사진"
    ></v-file-input>
        <v-select
        v-model="productCategory"
        :items="prodCategory"
        item-text="name"
        item-value="id"
        label="상품카테고리"
        chips
      ></v-select>
      <v-select
        v-model="isSale"
        :items="prodStatus"
        label="판매여부"
        chips
        item-text="name"
        item-value="value"
      ></v-select>
    <v-btn block outlined @click="submit">
        등록
    </v-btn>
  </v-card-text>
</v-card>
</v-container>
</template>

<script>
export default {
mounted() {
  this.$http
        .get('/api/product-categories',{
          withCredentials: true
        })
        .then(res => {
          console.log(res);
          this.prodCategory= res.data.body
                console.log(this.$route)
        })
        .catch(err => {
          console.log(err);
                console.log(this.$route)
        })
                    
},
data(){
  return{
    name:'',
    price: '',
    inventoryQuantity: '',
    comment: '',
    productCategory:'',
    attachFile: [],
    attachFiles: [],
    isSale:'',
    prodCategory:[],
    prodStatus : [
      {name:'판매',value:'1'},
      {name:'입고예정',value:'2'},
      {name:'품절',value:'3'},
      ]
  }
 },
  methods:{
  submit(){
    var formData = new FormData();
        formData.append('name',this.name);
        formData.append('price', this.price);
        formData.append('inventoryQuantity', this.inventoryQuantity);
        formData.append('comment', this.comment);
        formData.append('productCategory', this.productCategory);
        formData.append('attachFile', this.attachFile);
        for (let i = 0; i < this.attachFiles.length; i++) {
                formData.append('attachFiles', this.attachFiles[i]);
                }
        formData.append('isSale', this.isSale);
      
        this.$http
        .post('/api/dashboard/store/products', formData, {
          withCredentials: true
        })
        .then(res => {
          console.log(res);
        })
        .catch(err => {
          console.log(err);
        })
    },
  }
}
</script>

<style>

</style>