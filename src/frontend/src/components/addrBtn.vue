<template>
  <v-row>
    <v-dialog
      v-model="dialog"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          class="mt-3"
          v-bind="attrs"
          v-on="on"
          @click="addrList"
        >
          배송지 목록
        </v-btn>
      </template>
      <v-card>
        <v-card-text>
                 <div v-if="!addrInfo" class="mt-4">
           <h5 class="fw-bold text-black">저장된 주소가 없습니다.</h5>
            <v-container>
                <v-row>
                    <v-text-field v-model="recipientName" label="수취인 이름" ></v-text-field>
                </v-row>
                <v-row>
                    <v-text-field v-model="recipientNumber" label="수취인 번호" ></v-text-field>
                </v-row>
                <v-row> 
                   <v-col cols="8"><v-text-field v-model="zipcode" label="우편번호" ></v-text-field></v-col>
                    <v-col cols="2"><v-btn @click="execDaumPostcode">주소 찾기</v-btn></v-col>
                     <v-col cols="2"><addrBtn /></v-col>
                    <v-text-field v-model="address" label="주소" ></v-text-field>
                    <v-text-field v-model="detailedAddress" label="상세주소" ></v-text-field>
                    <v-text-field label="주문요청" v-model="comment" ></v-text-field>
                </v-row>
            </v-container>
       </div>
       <div v-else><addrList :addrInfo="addrInfo" @item="item"/></div>
          
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
            <v-btn
              color="green darken-1"
              text
              @click="dialog = false"
            >
              close
            </v-btn>
          <v-btn
            v-if="!addrInfo"
            color="green darken-1"
            text
            @click="add"
          >
            save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
import addrList from '@/components/addrList'
  export default {
    components:{
      addrList
    },
  data(){
    return{
      dialog: false,
      title: this.title,
      addrInfo:[],
      recipientName:this.recipientName,
      recipientNumber:this.recipientNumber,
      zipcode:this.zipcode,
      address:this.address,
      detailedAddress:this.detailedAddress,
      comment:this.comment
    }
  },
  methods:{
    add(){
      var formData = new FormData();
      formData.append('recipientName',this.recipientName);
      formData.append('recipientNumber',this.recipientNumber);
      formData.append('zipcode',this.zipcode);
      formData.append('address',this.address);
      formData.append('detailedAddress',this.detailedAddress);
      formData.append('comment',this.comment);
      this.$http
      .post('/api/address', formData,{
       withCredentials:true
      })
     .then(res => {
      console.log(res);
      })
     .catch(err => {
       console.log(err);
    });
    },
    item(item){
      // console.log(item.id)
      this.$emit('item', item)
    },
    addrList(){
    this.$http.get('/api/address',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.addrInfo=res.data.body
      }).catch(err =>{
				console.log(err);
			})
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
  }
  }
</script>