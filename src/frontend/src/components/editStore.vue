<template>
  <div class="container" id="content">
    <v-card>
      <v-card-title>쇼핑몰 정보</v-card-title>
      <hr>
      <v-card-text>
        <div>
          <v-text-field
          label="스토어명"
          v-model="storeName"
          name="name"
          id="name"
          >{{this.storeName}}</v-text-field>
        </div>
        <div>
          <v-text-field 
          label="사이트 URL"
          v-model="storeUrl"
          name="url"
          id="url"
          ></v-text-field>
        </div>
      </v-card-text>
    </v-card>
    <v-card class="mt-5">
      <v-card-title>사업자 정보</v-card-title>
      <hr>
      <v-card-text>
        <div>
          <v-text-field
          label="사업자 등록번호"
          v-model="businessNumber"
          name="businessLicenseNumber"
          id="businessLicenseNumber"></v-text-field>
        </div>
        <div>
          <v-text-field 
          label="업체(법인) 명"
          v-model="businessName"
          name="companyCorporationName"
          id="companyCorporationName">
          </v-text-field>
        </div>
        <div>
          <v-text-field 
          label="대표자명"
          v-model="ceoName"
          name="representativeName"
          id="representativeName">
          </v-text-field>
        </div>
        <div>
          <v-text-field 
          label="사업장 소재지"
          v-model="businessLocation"
          name="businessLocation"
          id="businessLocation">
          </v-text-field>
        </div>
        <div>
          <v-text-field 
          label="이메일"
          v-model="email"
          name="email"
          id="email">
          </v-text-field>
        </div>
      </v-card-text>
    </v-card>
    <v-card class="mt-5">
      <v-card-title>고객센터 정보</v-card-title>
      <hr>
      <v-card-text>
        <div>
          <v-text-field
          label="고객센터 번호"
          v-model="callcenterNumber"
          name="customerServiceNumber"
          id="customerServiceNumber">
          </v-text-field>
        </div>
        <div>
          <v-row>
          <v-col cols="4">운영 시작시간</v-col>
          <v-col cols="6">
            <input type="time"
            v-model="openTime"
            name="operationStartTime"
            id="operationStartTime">
            </v-col>
          </v-row>
        </div>
        <div>
          <v-row>
          <v-col cols="4">운영 종료시간</v-col>
          <v-col cols="6">
            <input type="time" 
            v-model="closeTime"
            name="operationEndTime"
            id="operationEndTime"></v-col>
          </v-row>
        </div>
        <div>
          <v-row>
          <v-col cols="4">점심 시작시간</v-col>
          <v-col cols="6">
            <input type="time"
            v-model="lunchstartTime"
            name="lunchStartTime"
            id="lunchStartTime"></v-col>
          </v-row>
        </div>
        <div>
          <v-row>
          <v-col cols="4">점심 종료시간</v-col>
          <v-col cols="6">
            <input type="time" 
            v-model="lunchendTime"
            name="lunchEndTime"
            id="lunchEndTime"></v-col>
          </v-row>
        </div>
      <v-row>
        <v-col cols="12">
          <v-select
            v-model="closedDay"
            :items="states"
            label="휴일"
            item-text="name"
            item-value="value"
            multiple
            chips
            name="closeDay">
            </v-select>
        </v-col>
      </v-row>
      </v-card-text>
    </v-card>
      <v-btn
        text
        :to="{name:'Main'}">
          Cancel
        </v-btn>
        <v-btn
          :disabled="!formIsValid"
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
  </div>
</template>

<script>
export default {
mounted() {
  const id = Number(this.$route.params.contentId);
  this.$http
    .get(`/api/stores/${id}`, {
    withCredentials: true
    })
    .then(res => {
      console.log(res)
      this.storeName = res.data.body.name;
      this.businessNumber = res.data.body.businessLicenseNumber;      this.ceoName = res.data.body.companyCorporationName;
      this.callcenterNumber = res.data.body.customerServiceNumber;
      this.email = res.data.body.email;
      this.lunchstartTime = res.data.body.lunchStartTime;
      this.lunchendTime = res.data.body.lunchEndTime;
      this.openTime = res.data.body.operationStartTime;
      this.closeTime = res.data.body.operationEndTime;
      this.storeUrl = res.data.body.url;
      this.businessLocation = res.data.body.businessLocation;
      this.businessName = res.data.body.companyCorporationName;
    })
      .catch(err => {
       console.log(err);
    });
},

data(){
  return{
    id:this.$route.params.id,
    storeName: this.storeName,
    storeUrl: this.storeUrl,
    businessNumber: this.businessNumber,
    businessName: this.businessName,
    ceoName: this.ceoName,
    businessLocation: this.businessLocation,
    email: this.email,
    callcenterNumber: this.callcenterNumber,
    openTime: this.openTime,
    closeTime: this.closeTime,
    lunchstartTime: this.lunchstartTime,
    lunchendTime: this.lunchendTime,
    closedDay: this.closedDay,
    states: [
      {name:'월', value:'월'},
      {name:'화', value:'화'},
      {name:'수', value:'수'},
      {name:'목', value:'목'},
      {name:'금', value:'금'},
      {name:'토', value:'토'},
      {name:'일', value:'일'},
      {name:'없음', value:'없음'},
    ]
  }
},

methods:{
  edit(){
    var formData = new FormData();
    formData.append('id', this.id);
    formData.append('name',this.storeName);
    formData.append('url', this.storeUrl);
    formData.append('businessLicenseNumber', this.businessNumber);
    formData.append('email', this.email);
    formData.append('operationStartTime', this.openTime);
    formData.append('operationEndTime', this.closeTime);
    formData.append('lunchStartTime', this.lunchstartTime);
    formData.append('lunchEndTime', this.lunchendTime);
    formData.append('closedDay',this.closedDay.toString());
    formData.append('companyCorporationName', this.businessName);
    formData.append('representativeName',this.ceoName);
    formData.append('businessLocation', this.businessLocation);
    formData.append('customerServiceNumber', this.callcenterNumber);

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
    // this.$http.delete(`/api/store/account/${this.id}`,{
    //   withCredentials: true
    // })
    // .then((res)=> {
    //   console.log(res)
    // }).catch((err)=>{
    //   console.log(err)
    // })
  }
},
computed:{
        formIsValid () {
        return (
          this.storeName &&
          this.storeUrl &&
          this.businessNumber &&
          this.businessName &&
          this.ceoName &&
          this.businessLocation &&
          this.email &&
          this.callcenterNumber &&
          this.openTime &&
          this.closeTime &&
          this.lunchstartTime &&
          this.lunchendTime &&
          this.closedDay 
        )},
}
}
</script>

<style>

</style>