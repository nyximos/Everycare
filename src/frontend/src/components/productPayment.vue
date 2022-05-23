<template>
  <div>
       <h1>구매하기</h1>
    <h3>토스 티셔츠</h3>
    <span>19,000 원</span>
    <p>----------------------</p>
    <div><label><input type="radio" name="method" value="카드" checked/>신용카드</label></div>
    <div><label><input type="radio" name="method" value="가상계좌"/>가상계좌</label></div>
    <p>----------------------</p>
    <button id="payment-button" @click="payment">결제하기</button>
  </div>
</template>


<script>
export default {

data(){
    return{
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
}
//   var tossPayments = TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg");
//     var button = document.getElementById("payment-button");

//     var orderId = new Date().getTime();
//     let customDate = new Date()

//     button.addEventListener("click", function () {
//         var method = document.querySelector('input[name=method]:checked').value; // "카드" 혹은 "가상계좌"

//         //결제 정보 필수 파라미터(결제금액(amount), 주문ID(orderId), 주문명(orderName), 성공주소(successUrl), 실패주소(failUrl)

//         // 파라미터 설명 https://docs.tosspayments.com/guides/windows/card#%EA%B2%B0%EC%A0%9C-%EC%A0%95%EB%B3%B4-%ED%8C%8C%EB%9D%BC%EB%AF%B8%ED%84%B0
//         var paymentData = {
//             amount: 15000,
//             orderId: orderId,
//             orderName: '토스 티셔츠 외 2건',
//             customerName: '박토스',
//             successUrl: `https://localhost:8086/api/cart/orders/payments?orderTableId=1`,
//             failUrl: 'https://localhost:8080/fail',
//         };

//         if (method === '가상계좌') {
//             paymentData.virtualAccountCallbackUrl = window.location.origin + '/virtual-account/callback'
//         }

//         //첫번째 파라미터로 결제수단(카드, 가상계좌)
//         //두번째 파라미터로 결제 정보
//         tossPayments.requestPayment(method, paymentData);


}
</script>

<style>

</style>