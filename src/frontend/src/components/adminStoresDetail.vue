<template>
  <div class="container" id="content">
    <v-card>
      <v-card-title>신청인 정보</v-card-title>
      <hr>
      <v-card-text>
        <div>
          <v-text-field
          label="스토어 아이디"
          v-model="memberId"
          name="name"
          id="name"
          ></v-text-field>
        </div>
        <div>
          <v-text-field
          label="스토어명"
          v-model="memberName"
          ></v-text-field>
        </div>
      </v-card-text>
    </v-card>
    <v-card class="mt-5">
      <v-card-title>쇼핑몰 정보</v-card-title>
      <hr>
      <v-card-text>
        <div>
          <v-text-field
          label="스토어 아이디"
          v-model="storeId"
          name="name"
          id="name"
          ></v-text-field>
        </div>
        <div>
          <v-text-field
          label="스토어명"
          v-model="name"
          ></v-text-field>
        </div>
        <div>
          <v-text-field 
          label="사이트 URL"
          v-model="url"
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
          v-model="customerServiceNumber"
          ></v-text-field>
        </div>
        <div>
          <v-text-field 
          label="업체(법인) 명"
          v-model="companyCorporationName"
          ></v-text-field>
        </div>
        <div>
          <v-text-field 
          label="대표자명"
          v-model="representativeName"
          ></v-text-field>
        </div>
        <div>
          <v-text-field 
          label="사업장 소재지"
          v-model="businessLocation"
          ></v-text-field>
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
          v-model="customerServiceNumber"
          ></v-text-field>
        </div>
        <div>
          <v-row>
          <v-col cols="4">운영 시작시간</v-col>
          <v-col cols="6">
            <input type="time"
            v-model="operationStartTime" />
            </v-col>
          </v-row>
        </div>
        <div>
          <v-row>
          <v-col cols="4">운영 종료시간</v-col>
          <v-col cols="6">
            <input type="time" 
            v-model="operationEndTime"
            /></v-col>
          </v-row>
        </div>
        <div>
          <v-row>
          <v-col cols="4">점심 시작시간</v-col>
          <v-col cols="6">
            <input type="time"
            v-model="lunchStartTime"
            /></v-col>
          </v-row>
        </div>
        <div>
          <v-row>
          <v-col cols="4">점심 종료시간</v-col>
          <v-col cols="6">
            <input type="time" 
            v-model="lunchEndTime"
            /></v-col>
          </v-row>
        </div>
      <v-row>
        <v-col cols="4">휴일</v-col>
        <v-col cols="6">
            <input
            v-model="closedDay"
            /></v-col>
      </v-row>
      </v-card-text>
    </v-card>
      <v-btn
        text
        @click="back">
        Cancel
        </v-btn>
        <v-btn
          text
          color="primary"
          @click="submit"
        >
          승인
        </v-btn>
  </div>  
</template>

<script>
export default {
mounted() {
     const id = Number(this.$route.params.contentId);
    this.$http
    .get(`/api/admin/stores/${id}`,{
        withCredentials:true
      })
	.then((res)=>{
        console.log(res.data);
        this.storeId= res.data.body.id,
        this.name= res.data.body.name,
        this.businessLicenseNumber= res.data.body.businessLicenseNumber,
        this.businessLocation= res.data.body.businessLocation,
        this.closedDay= res.data.body.closedDay,
        this.companyCorporationName= res.data.body.companyCorporationName,
        this.createdAt= res.data.body.createdAt,
        this.customerServiceNumber= res.data.body.customerServiceNumber,
        this.email= res.data.body.email,
        this.lunchEndTime= res.data.body.lunchEndTime,
        this.lunchStartTime= res.data.body.lunchStartTime,
        this.memberId= res.data.body.member.id,
        this.memberName= res.data.body.member.name,
        this.operationEndTime= res.data.body.operationEndTime,
        this.operationStartTime= res.data.body.operationStartTime,
        this.representativeName= res.data.body.representativeName,
        this.url= res.data.body.url
      }).catch(err =>{
				alert(err);
				console.log(err);
			})
        },
data(){
    return{
        storeId: this.StoreId,
        name: this.name,
        businessLicenseNumber: this.businessLicenseNumber,
        businessLocation: this.businessLocation,
        closedDay: this.closedDay,
        companyCorporationName: this.companyCorporationName,
        createdAt: this.createdAt,
        customerServiceNumber: this.customerServiceNumber,
        email: this.email,
        lunchEndTime: this.lunchEndTime,
        lunchStartTime: this.lunchStartTime,
        memberId: this.memberId,
        memberName: this.memberName,
        operationEndTime: this.operationEndTime,
        operationStartTime: this.operationStartTime,
        representativeName: this.representativeName,
        url: this.url
    }
},
methods:{
    submit(){
    const id = Number(this.$route.params.contentId);
    this.$http
    .post(`/api/admin/stores/${id}`, {
    withCredentials: true
    })
     .then(res => {
      console.log(res);
    })
      .catch(err => {
       console.log(err);
    });
  },
back(){
    this.$router.push({
        path:'/admin/stores'
    })
}
}
}
</script>

<style>

</style>