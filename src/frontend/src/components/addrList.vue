<template>
    <div>
        <h5 class="text-center fw-bold text-black">주소 목록({{addrInfo.length}})</h5>
       <hr>
        <v-card v-for="item in addrInfo" :key="item.index" class="mt-4" @click="clickCard(item)">
            <v-card-title>{{item.recipientName}}</v-card-title>
            <v-card-text>
                <v-row>
                    <label>우편번호<span>{{item.zipcode}}</span></label><br>
                    <label>주소<span>{{item.address}}</span></label><br>
                    <label>상세주소<span>{{item.detailedAddress}}</span></label><br>
                    <label>전화번호<span>{{item.recipientNumber}}</span></label><br>
                    <label>주문요청<span>{{item.comment}}</span></label><br>
                </v-row>
            </v-card-text>
            <v-btn
            color="green darken-1"
            text
            @click="drop(item)"
            >
            drop
            </v-btn>
        </v-card>
          <div class="text-center">
    <v-pagination
      class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
    ></v-pagination>
  </div>
    </div>
</template>

<script>
export default {
    props:{
        addrInfo:{
            required:true
        }
    },
data(){
    return{
        dataPerPage:4,
        curPageNum:1,
    }
},
methods:{
    drop(item){
        this.$http
      .delete(`/api/address/${item.id}`,{
      withCredentials: true
      })
      .then((res)=> {
        console.log(res)
      }).catch((err)=>{
        console.log(err)
      })
    },
    clickCard(item){
    this.$emit('item', item);
    },
},
    computed: {
      startOffset() {
        return ((this.curPageNum - 1) * this.dataPerPage);
      },
      endOffset() {
        return (this.startOffset + this.dataPerPage);
      },   
      numOfPages() {
        return Math.ceil(this.addrInfo.length / this.dataPerPage);
      },
      calData() {
        return this.addrInfo.slice(this.startOffset, this.endOffset)
      },
    }
}
</script>

<style>

</style>