<template>
<div>
  <Toast v-if="showToast" :message="toastMessage" :type="toastAlertType"/>
<v-container>
  <v-card>
  <v-card-text>
      <div style="float:right">
      <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+this.thumbnail" 
      alt="사진" width="300" height="170"/>
    </div>
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
    <v-row>
      <v-col cols="2"  v-for="(item,index) in detailImg" :key="index" style="float:left">
    <div class="text-center">
    <v-btn icon @click="delImg(item)">
      <v-icon>mdi-close</v-icon>
    </v-btn>
    <v-img id="divProfile" :src="'https://localhost:8086/api/images/'+item.storeFileName" alt="사진" width="150px" height="200px"/>
    </div>
    </v-col>
    </v-row>
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
</div>
</template>

<script>
import Toast from '@/components/toast'
export default {
  components:{
    Toast
  },
mounted() {
  this.$http
    .get(`/api/store/products/${this.id}`, {
    withCredentials: true
    })
    .then(res => {
      console.log(res)
      this.name = res.data.body.name;
      this.price = res.data.body.price;      
      this.inventoryQuantity = res.data.body.inventoryQuantity;
      this.comment = res.data.body.comment;
      this.thumbnail = res.data.body.storeFileName;
      this.detailImg = res.data.body.imagesDTOs;
      this.inventoryQuantity = res.data.body.inventoryQuantity;
    })
      .catch(err => {
       console.log(err);
       this.triggerToast('Something went wrong', 'error')
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
    id:this.$route.params.contentId,
    name:this.name,
    price: this.price,
    inventoryQuantity: this.inventoryQuantity,
    comment: this.comment,
    productCategory:this.productCategory,
    thumbnail:this.thumbnail,
    attachFile: null,
    attachFiles: [],
    detailImg:[],
    isSale:this.isSale,
    prodCategory:[],
    prodStatus : [
      {name:'판매',value:'1'},
      {name:'입고예정',value:'2'},
      {name:'품절',value:'3'},
      ],
      showToast : false,
      toastMessage: '',
      toastAlertType: ''
  }
},
methods:{
  triggerToast(message, type='success'){
    this.toastMessage = message;
    this.toastAlertType = type;
    this.showToast= true
    setTimeout(()=>{
      this.toastMessage='';
      this.showToast=false;
      this.showToast = '';
    },3000)
  },
  edit(){
    var formData = new FormData();
        formData.append('id', this.id);
        formData.append('name',this.name);
        formData.append('price', this.price);
        formData.append('inventoryQuantity', this.inventoryQuantity);
        formData.append('comment', this.comment);
        formData.append('productCategory', this.productCategory);
        if (this.attachFile!=null){
          formData.append('attachFile', this.attachFile)
        }
        if (this.attachFiles===null) {
          for (let i = 0; i < this.item.length; i++) {
            formData.append('attachFiles', this.item.detailImg[i]);
          }
        }else{
          for (let i = 0; i < this.attachFiles.length; i++) {
            formData.append('attachFiles', this.attachFiles[i]);
          }
        }
        formData.append('isSale', this.isSale);
        this.$http
        .patch(`/api/dashboard/store/products/${this.id}`,formData, {
        withCredentials: true
         })
     .then(res => {
      console.log(res);
      this.triggerToast('Successfully saved!')
    })
      .catch(err => {
       console.log(err);
       this.triggerToast('Something went wrong', 'error')
    });
  },
  drop(){
    this.$http.delete(`/api/dashboard/store/products/${this.id}`,{
      withCredentials: true
    })
    .then((res)=> {
      console.log(res)
    }).catch((err)=>{
      console.log(err)
      this.triggerToast('Something went wrong', 'error')
    })
  },
  delImg(item){
    // console.log(item.id)
    this.$http
    .delete(`/api/dashboard/store/products/${this.id}/image/${item.id}`,{
      withCredentials:true
    })
    .then((res)=>{
      console.log(res)
    }).catch((err)=>{
      console.log(err)
      this.triggerToast('Something went wrong', 'error')
    })
  }
 },
}
</script>

<style>

</style>