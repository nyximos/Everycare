<template>
    <div>
        <div v-if="this.desserts.length==0">장바구니가 비었습니다.</div>
        <v-card v-else>
            <v-btn @click="removeAll">전체삭제</v-btn>
                <v-simple-table>
                    <template v-slot:default>
                        <thead>
                            <tr>
                                <th class="text-left">id</th>
                                <th class="text-left">quantity</th>
                                <th class="text-left">amount</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in desserts" :key="index">
                                <td>{{ index }}/{{ item.id }}</td>
                                <td>{{ item.quantity }}</td>
                                <td>{{ item.amount * item.quantity }}</td>
                                <td><v-btn @click="remove(item, index)">x</v-btn></td>
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
                    <v-text-field label="이름" v-model="name" solo></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field v-model="amount" label="가격" solo></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field v-model="recipientName" label="수취인 이름" solo></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field v-model="recipientNumber" label="수취인 번호" solo></v-text-field>
                </v-row>
                <v-row>
                    <div class="daummap">
    <h1>우편번호: <span>{{ zip }}</span></h1>
    <h1>기본주소: <span>{{ addr1 }}</span></h1>
    <h1>상세주소: <span>{{ addr2 }}</span></h1>
    <div ref="embed"></div>
    <button @click="showApi">주소API 호출</button>
  </div>
                    <v-text-field v-model="address" label="주소" solo></v-text-field>
                    <v-text-field v-model="detailedAddress" label="상세주소" solo></v-text-field>
                    <v-text-field label="주문요청" v-model="comment" solo></v-text-field>
                    <v-text-field v-model="paymentAmount" label="결재금액" solo></v-text-field>
                    <v-file-input v-model="zipcode" label="File input"></v-file-input>
                    <span>결제날짜</span><input type="date" class="form-control" />
                </v-row>
            </v-card-text>
        </v-card>

        <v-btn @click="order" block>주문하기</v-btn>
    </div>
</template>

<script>
export default {
  mounted() {
    this.$http.get('/api/cart',{
        withCredentials:true
      })
			.then((res)=>{
        console.log(res.data);
        this.desserts=res.data.body
        this.id=this.desserts[0]
      }).catch(err =>{
				alert(err);
				console.log(err);
			})
  },
  data(){
    return{
      desserts:[],
      id:this.id,
      name: `${this.id}외건`,
      amount: this.amount,
      recipientName: this.recipientName,
      recipientNumber: this.recipientNumber,
      zipcode: this.zipcode,
      address: this.address,
      detailedAddress: this.detailedAddress,
      comment: this.comment,
      paymentAmount: this.paymentAmount,
      zip:'',
      addr1:'',
      addr2:''
    }
  },
    methods:{
showApi() {
      new window.daum.Postcode({
        oncomplete: (data) => {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            let fullRoadAddr = data.roadAddress; // 도로명 주소 변수
            let extraRoadAddr = ''; // 도로명 조합형 주소 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
              extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
            if(fullRoadAddr !== ''){
                fullRoadAddr += extraRoadAddr;
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            this.zip = data.zonecode; //5자리 새우편번호 사용
            this.addr1 = fullRoadAddr;
        }
      }).embed(this.$refs.embed)
    },
      order(){
      var formData = new FormData();
      formData.append('name','박지은');
      formData.append('amount',1);
      formData.append('recipientName','박지은');
      formData.append('recipientNumber',123);
      formData.append('zipcode',this.zipcode);
      formData.append('address','포항시북구');
      formData.append('detailedAddress','404-222');
      formData.append('comment','배송전문의');
      formData.append('paymentAmount',100);
        this.$http
      .post('/api/cart/orders', formData,{
       withCredentials:true
      })
     .then(res => {
      console.log(res);
      })
     .catch(err => {
       console.log(err);
       console.log(this.hi)
    });
      },
      remove(index){
      this.$http
      .delete(`/api/cart/${index}`,{
      withCredentials: true
      })
      .then((res)=> {
        console.log(res)
        // this.$store.commit("cart/remoteList", index)
      }).catch((err)=>{
        console.log(err)
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
      }).catch((err)=>{
        console.log(err)
      })
      }

    },
    data() {
        return {
            desserts: [],
            name: this.name,
            amount: this.amount,
            recipientName: this.recipientName,
            recipientNumber: this.recipientNumber,
            zipcode: this.zipcode,
            address: this.address,
            detailedAddress: this.detailedAddress,
            comment: this.comment,
            paymentAmount: this.paymentAmount,
        };
    },
    methods: {
        order() {
            var formData = new FormData();
            formData.append('name', '박지은');
            formData.append('amount', 1);
            formData.append('recipientName', '박지은');
            formData.append('recipientNumber', 123);
            formData.append('zipcode', this.zipcode);
            formData.append('address', '포항시북구');
            formData.append('detailedAddress', '404-222');
            formData.append('comment', '배송전문의');
            formData.append('paymentAmount', 100);
            this.$http
                .post('/api/cart/orders', formData, {
                    withCredentials: true,
                })
                .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    console.log(err);
                    console.log(this.hi);
                });
        },
        plus(item) {
            this.$store.commit('cart/incrementItemQuantity', item);
        },
        minus(item) {
            if (item.quantity == 1) {
                alert('최소 주문개수는 1개입니다');
            } else {
                this.$store.commit('cart/minus', item);
            }
        },
        remove(item, index) {
            this.$http
                .delete(`/api/cart/${index}`, {
                    withCredentials: true,
                })
                .then((res) => {
                    console.log(res);
                    this.$store.commit('cart/remoteList', index);
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        removeAll() {
            this.$http
                .delete(`/api/cart`, {
                    withCredentials: true,
                })
                .then((res) => {
                    console.log(res);
                    this.$store.state.cart.cart = [];
                })
                .catch((err) => {
                    console.log(err);
                });
        },
    },
    computed: {
        total() {
            var total = 0;
            this.desserts.forEach((item) => {
                total += item.quantity * item.amount;
            });
            return total;
        },
    },
};
</script>

<style></style>