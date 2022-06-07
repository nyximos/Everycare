<template>
    <div>
              <transition name="fade">
          <toast  v-if="showToast" :message="toastMessage" :type="toastAlertType" />
        </transition>
        <div v-if="this.$store.state.cart.cart.length==0">장바구니가 비었습니다.</div>
        <v-card v-else>
            <v-btn @click="removeAll">전체삭제</v-btn>
                <v-simple-table>
                    <template v-slot:default>
                        <thead>
                            <tr>
                                <th class="text-left">img</th>
                                <th class="text-left">id</th>
                                <th class="text-left">name</th>
                                <th class="text-left">quantity</th>
                                <th class="text-left">amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item,index) in $store.state.cart.cart" :key="index">
                                <td><v-img id="divProfile" :src="'https://localhost:8086/api/images/'+item.image" 
                                    alt="사진" width="100" height="100"/></td>
                                <td>{{index}}/{{ item.productId }}</td>
                                <td>{{item.name}}</td>
                                <td>{{ item.quantity }}</td>
                                <td>{{ item.amount * item.quantity }}</td>
                                <td><v-btn @click="remove(index)">x</v-btn></td>
                            </tr>
                        </tbody>
                    </template>
                </v-simple-table>
                <h1>총금액 : {{ total }}</h1>
                <v-card-text>
                <v-row class="text-center mt-4">
                    <h3>주문정보</h3>
                </v-row>
                <v-row>
                    <v-text-field label="이름" v-model="firstName" disabled ></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field v-model="length" label="개수" disabled></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field v-model="recipientName" label="수취인 이름" ></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field v-model="recipientNumber" label="수취인 번호" ></v-text-field>
                </v-row>
                <v-row> 
                   <v-col cols="10"><v-text-field v-model="zipcode" label="우편번호" ></v-text-field></v-col>
                    <v-col cols="1"><v-btn @click="execDaumPostcode">주소 찾기</v-btn></v-col>
                     <v-col cols="1"><addrBtn @item="item"/></v-col>
                    <v-text-field v-model="address" label="주소" ></v-text-field>
                    <v-text-field v-model="detailedAddress" label="상세주소" ></v-text-field>
                    <v-text-field label="주문요청" v-model="comment" ></v-text-field>
                    <v-text-field v-model="total" label="결재금액" disabled></v-text-field>
                </v-row>
          </v-card-text>
        </v-card>
        <v-btn  @click="order" block :disabled="!formIsValid">주문</v-btn>
    </div>
</template>

<script>
import toast from '@/components/toast'
import addrBtn from '@/components/addrBtn'
export default {
  components:{
    toast,
    addrBtn
  },
  mounted() {
    this.$http.get('/api/cart',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
      }).catch(err =>{
				console.log(err);
			})
  },
  data(){
    return{
      tossPayments : TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg"),
      orderId : new Date().getTime(),
      customDate : new Date(),
      amount: this.length,
      recipientName: this.recipientName,
      recipientNumber: this.recipientNumber,
      zipcode: this.zipcode,
      address: this.address,
      detailedAddress: this.detailedAddress,
      comment: this.comment,
      paymentAmount: this.total,
      Order_Id:this.Order_Id,
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
      item(item){
        this.recipientName=item.recipientName
        this.recipientNumber=item.recipientNumber
        this.zipcode=item.zipcode
        this.address=item.address
        this.detailedAddress=item.detailedAddress
        this.comment=item.comment
      },
      payment(){
        var tossPayments = TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg");
        var customDate = new Date()
        var paymentData = {
            amount: this.total,
            orderId: this.orderId,
            orderName: this.firstName,
            customerName: this.$store.state.userStore.id,
            successUrl: `https://localhost:8086/api/cart/orders/payments?orderTableId=${this.Order_Id}`,
            failUrl: 'https://localhost:8080/fail',
        };
         tossPayments.requestPayment("카드", paymentData);
         
    },
      execDaumPostcode() {
        new window.daum.Postcode({
          oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.zipcode = data.zonecode;
        },
      }).open();
    },
      order(){
      var formData = new FormData();
      formData.append('name',this.firstName);
      formData.append('amount',this.length);
      formData.append('recipientName',this.recipientName);
      formData.append('recipientNumber',this.recipientNumber);
      formData.append('zipcode','');
      formData.append('address',this.address);
      formData.append('detailedAddress',this.detailedAddress);
      formData.append('comment',this.comment);
      formData.append('paymentAmount',this.total);
      this.$http
      .post('/api/cart/orders', formData,{
       withCredentials:true
      })
     .then(res => {
      console.log(res);
      this.Order_Id=res.data.body
      this.payment()
      })
     .catch(err => {
       console.log(err);
    });
     this.$http
      .get('/api/cart/orders/payments', formData,{
       withCredentials:true
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
      remove(index){
      this.$http
      .delete(`/api/cart/${index}`,{
      withCredentials: true
      })
      .then((res)=> {
        console.log(res)
        this.$store.commit("cart/remoteList", index)
        this.triggerToast('Successfully saved!')
      }).catch((err)=>{
        console.log(err)
        this.triggerToast('Something went wrong', 'error')
      })
      },
      removeAll(){
      this.$http
      .delete(`/api/cart`,{
      withCredentials: true
      })
      .then((res)=> {
        console.log(res)
        this.$store.state.cart.cart=[]
        this.triggerToast('Successfully saved!')
      }).catch((err)=>{
        console.log(err)
        this.triggerToast('Something went wrong', 'error')
      })
      },
    },
    computed: {
        firstName(){
          return this.$store.state.cart.cart[0].productId+'외'+(this.$store.state.cart.cart.length-1)+'건';
        },
        length() {
            return this.$store.state.cart.cart.length;
        },
        total() {
            var total = 0;
            this.$store.state.cart.cart.forEach((item) => {
                total += item.quantity * item.amount;
            });
            return total;
        },
        formIsValid () {
        return (
          this.recipientName &&
          this.recipientNumber &&
          this.address &&
          this.detailedAddress 
        )},
    },
};
</script>

<style scoped>
.fade-enter-active,
.fade-leave-active {
  transition: all 0.5s ease;;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
  transform: translateY(-30px);
}

.fade-enter-to,
.fade-leave-from {
  opacity: 1;
  transform: translateY(0px);
}
</style>