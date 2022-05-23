<template>
<div>
    <v-card>
        <v-row>
          <v-text-field
            label="이름"
            v-model="name"
            solo
          ></v-text-field>
        </v-row>
                <v-row>
          <v-text-field
          v-model="amount"
            label="가격"
            solo
          ></v-text-field>
        </v-row>
                <v-row>
          <v-text-field
          v-model="recipientName"
            label="수취인 이름"
            solo
          ></v-text-field>
        </v-row>
                <v-row>
          <v-text-field
          v-model="recipientNumber"
            label="수취인 번호"
            solo
          ></v-text-field>
        </v-row>
                <v-row>
          <v-text-field
          v-model="address"
            label="주소"
            solo
          ></v-text-field>
            <v-text-field
            v-model="detailedAddress"
            label="상세주소"
            solo
          ></v-text-field>
            <v-text-field
            label="주문요청"
            v-model="comment"
            solo
          ></v-text-field>
            <v-text-field
            v-model="paymentAmount"
            label="결재금액"
            solo
          ></v-text-field>
          <v-file-input
            v-model="zipcode"
            label="File input"
        ></v-file-input>
          <span>결제날짜</span><input type="date">
        </v-row>
          <v-btn @click="order">주문하기</v-btn>


        
    </v-card>
</div>  
</template>

<script>
export default {
data(){
    return{
        name: this.name,
        amount: this.amount,
        recipientName: this.recipientName,
        recipientNumber: this.recipientNumber,
        zipcode: this.zipcode,
        address: this.address,
        detailedAddress: this.detailedAddress,
        comment: this.comment,
        paymentAmount: this.paymentAmount
    }
},
methods:{
    order(){
      var formData = new FormData();
      formData.append('name',this.name);
      formData.append('amount',this.amount);
      formData.append('recipientName',this.recipientName);
      formData.append('recipientNumber',this.recipientNumber);
      // formData.append('zipcode', this.zipcode);
      formData.append('address',this.address);
      formData.append('detailedAddress',this.detailedAddress);
      formData.append('comment',this.comment);
      formData.append('paymentAmount',this.paymentAmount);
    
    this.$http
      .post('https://localhost:8086/api/cart/orders', formData,{
       withCredentials:true
      })
     .then(res => {
      console.log(res);
      })
     .catch(err => {
       console.log(err);
    });
    }
}
}
</script>

<style>

</style>