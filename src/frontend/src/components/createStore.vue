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
          ></v-text-field>
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
          @click="submit"
        >
          Submit
        </v-btn>
  </div>
</template>

<script>
export default {
data(){
  return{
    storeName: '',
    storeUrl: '',
    businessNumber: '',
    businessName: '',
    ceoName: '',
    businessLocation: '',
    email: '',
    callcenterNumber: '',
    openTime: '',
    closeTime: '',
    lunchstartTime: '',
    lunchendTime: '',
    closedDay: [],
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
  submit(){
    const storeinfo = {
      name: this.storeName,
      url : this.storeUrl,
      businessLicenseNumber: this.businessNumber,
      companyCorporationName: this.businessName,
      representativeName: this.ceoName,
      businessLocation: this.businessLocation,
      email: this.email,
      customerServiceNumber: this.callcenterNumber,
      operationStartTime: this.openTime,
      operationEndTime: this.closeTime,
      lunchStartTime: this.lunchstartTime,
      lunchEndTime: this.lunchendTime,
      closedDay: this.closedDay.toString()
    }
    console.log(storeinfo)
    this.$http
    .post('/api/store',storeinfo, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    });
    // const formData = new FormData();

    // formData.append('name', $('#name').val());
    // formData.append('url', $('#url').val());
    // formData.append('businessLicenseNumber', $('#businessLicenseNumber').val());
    // formData.append('email', $('#email').val());
    // formData.append('operationStartTime', $('#operationStartTime').val());
    // formData.append('operationEndTime', $('#operationEndTime').val());
    // formData.append('lunchStartTime', $('#lunchStartTime').val());
    // formData.append('lunchEndTime', $('#lunchEndTime').val());
    // formData.append('closedDay', $('#closedDay option:selected').val());
    // formData.append('companyCorporationName', $('#companyCorporationName').val());
    // formData.append('representativeName', $('#representativeName').val());
    // formData.append('businessLocation', $('#businessLocation').val());
    // formData.append('customerServiceNumber', $('#customerServiceNumber').val());

    // console.log(formData)
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