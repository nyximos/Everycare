<template>
<div>
  <v-card v-for="(p,index) in profiles" :key="index"
    class="mx-auto" 
    max-width="344"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{p.name}}
        </div>
        <v-list-item-title class="text-h5 mb-1">
          {{p.startdate}}~{{p.enddate}}
        </v-list-item-title>
        <v-list-item-subtitle>{{p.introduction}}</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>
      <v-btn
        outlined
        rounded
        text
        @click="payment"
      >
        승낙
      </v-btn>
    </v-card-actions>
    
  </v-card>
  
</div>

</template>

<script>
export default {
    name:'jobapplication',
    data() {
        return {
            profiles: [],
            // id: this.id
            // id: this.$route.params.caresitterId,
          tossPayments : TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg"),
          orderId : new Date().getTime(),
          customDate : new Date()
            
            
        }
    },
    methods:{
       payment(){
        var tossPayments = TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg");
        var customDate = new Date()
        var paymentData = {
            amount: 15000,
            orderId: this.orderId,
            orderName: '토스 티셔츠 외 2건',
            customerName: '박토스',
            successUrl: 'https://localhost:8086/api/cart/orders/payments?orderTableId=여기는 값',
            failUrl: 'https://localhost:8080/fail',
        };
         tossPayments.requestPayment("카드", paymentData);
    }
    },
 mounted() {

  
  const id = this.$route.params.contentId;
 
  console.log(id);
    this.$http
    .get(`/api/dashboard/recruitions/${id}/caresitters`, {
      withCredentials: true
    })
      .then((res) => {
          console.log(res)
          console.log(res.data.body)
          this.profiles = res.data.body
        
          
  }).catch(err => {
    alert(err);
    console.log(err);
  })
}
}
</script>

<style>

</style>