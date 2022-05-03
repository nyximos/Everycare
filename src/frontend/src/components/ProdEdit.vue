<template>
<v-container>
  <v-card>
  <v-card-title>상품수정</v-card-title>
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
        item-value="value"
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
      <v-btn
          text
          color="primary"
          @click="edit"
        >
          edit
        </v-btn>
        <v-btn
          text
          color="primary"
          @click="drop"
        >
          삭제
        </v-btn>
  </v-card-text>
</v-card>
</v-container>
</template>

<script>
export default {
mounted() {
  const id = Number(this.$route.params.id);
  this.$http
    .get(`/api/stores/${id}/prodedit`, {
    withCredentials: true
    })
    .then(res => {
      console.log(res)
      this.name = res.data.body.name;
      this.price = res.data.body.price;      
      this.inventoryQuantity = res.data.body.inventoryQuantity;
      this.comment = res.data.body.comment;
    })
      .catch(err => {
       console.log(err);
    });
},

data(){
  return{
    id:this.$route.params.id,
    name:this.name,
    price: this.price,
    inventoryQuantity: this.inventoryQuantity,
    comment: this.comment,
    productCategory:this.productCategory,
    attachFile: [],
    attachFiles: [],
    isSale:this.isSale,
    prodCategory:[
      {name: '보행', value:'1'}
    ],
    prodStatus : [
      {name:'판매',value:'1'},
      {name:'입고예정',value:'2'},
      {name:'품절',value:'3'},
      ]
  }
},
methods:{
  edit(){
    var formData = new FormData();
        formData.append('id', this.id);
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
        .patch(`/api/store/account/${this.id}`,formData, {
        withCredentials: true
         })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    });
  },
  drop(){
    this.$http.delete(`/api/store/account/${this.id}`,{
      withCredentials: true
    })
    .then((res)=> {
      console.log(res)
    }).catch((err)=>{
      console.log(err)
    })
  }
 },
}
</script>

<style>

</style>