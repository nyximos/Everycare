<template>
  <div>
    <!--banner-->
    <v-card color="gray">
      <v-img
        src="../images/care.jpg">
      <div class="lb-text">
        <v-row>
          <v-card-text class="text-h3 fw-bold">
            믿을 수 있는<br> 케어매칭 서비스
          </v-card-text>
        </v-row>
          <v-card-subtitle>
            no.1 병원 전문 간병서비스, 에브리케어
          </v-card-subtitle>
        <div>
          <v-btn color="light-green lighten-5" rounded @click="go('carepeople')">간병인 신청</v-btn>
        </div>
      </div>
      </v-img>
    </v-card>
  
  <!--content-->
  <v-container fluid>
    <v-row class="mt-5">
      <v-col cols="12">
       <v-card>
         <v-card-title class="fw-bold">자주찾는 서비스</v-card-title>
         <v-card-subtitle>케어시터 찾기 / 일자리 찾기 / 케어노트 / 마이페이지</v-card-subtitle>
         <v-row>
        <v-container class="d-flex justify-space-around">
          <v-btn class="p-5" fab @click="go('caresitters')">
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
          <v-btn class="p-5" fab @click="go('recruitions')">
            <v-icon>mdi-magnify</v-icon>
          </v-btn>
          <v-btn class="p-5" fab @click="go('membernote')">
            <v-icon> mdi-note</v-icon>
          </v-btn>
          <v-btn class="p-5" fab @click="go('dashboard')">
            <v-icon>mdi-account</v-icon>
          </v-btn>
        </v-container>
        </v-row>
       </v-card>
       </v-col>
    </v-row>
   <v-row class="mt-10 mb-5">
     <v-col cols="6">
        <v-card>
         <v-card-title class="fw-bold">자주묻는 질문
           <v-btn icon @click="go('Faq')"><v-icon>mdi-plus</v-icon></v-btn>
         </v-card-title>
    <v-expansion-panels accordion>
      <v-expansion-panel
        v-for="item in faq"
        :key="item.index"
      >
        <v-expansion-panel-header>{{item.title}}</v-expansion-panel-header>
        <v-expansion-panel-content>
         {{item.content}}
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-card>
     </v-col>
     <v-col cols="6">
       <v-card>
         <v-card-title class="fw-bold">공지사항
           <v-btn icon @click="go('Notice')"><v-icon>mdi-plus</v-icon></v-btn>
         </v-card-title>
    <v-expansion-panels accordion>
      <v-expansion-panel
        v-for="item in notice"
        :key="item.index"
      >
        <v-expansion-panel-header>{{item.title}}</v-expansion-panel-header>
        <v-expansion-panel-content>
          {{item.content}}
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </v-card>
     </v-col>
   </v-row>
      </v-container>
    </div>
  </template>

<script>
export default {
    name: 'Main',
    mounted(){
      this.$http
    .get('/api/notice', {
    withCredentials: true,
    })
    .then((res) => {
        console.log(res.data);
        this.notice = res.data.body.slice(0,2);
    })
    .catch((err) => {
        alert(err);
        console.log(err);
    });
    this.$http
    .get('/api/faq', {
    withCredentials: true,
    })
    .then((res) => {
        console.log(res.data);
        this.faq = res.data.body.slice(0,2);
    })
    .catch((err) => {
        alert(err);
        console.log(err);
    });
    },
    data() {
        return {
          notice: this.notice,
          faq: this.faq
        };
    },
    methods: {
        go(link) {
            this.$router.push('/' + link);
        }
    }
};
</script>
<style lang="scss" scoped>
.lb-text {
  padding: 10px 20px;
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}


</style>
