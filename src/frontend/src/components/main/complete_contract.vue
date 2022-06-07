<template>
  <div class="content">
      <h2>완료된 계약서</h2>
      <v-card
    class="mx-auto"
    max-width="300"
    v-for="(c,index) in contract" :key="index"
  >
    <v-img
      src="@/assets/handshake.png"
      height="200px"
      width="150px"
      style="margin:0 auto;"
    ></v-img>

    <v-card-title>
        회원 아이디:{{c.memberId}} <br>
        회원 이름:{{c.memberName}} <br>
        케어대상인:{{c.careTargetName}}
    </v-card-title>
    <v-card-subtitle>
      날짜: {{c.day}}<br>
      {{c.payDateTime}}
    </v-card-subtitle>
 

    <v-card-actions>
      <!-- <v-btn color="orange lighten-2" text @click="dialog(c)">보기</v-btn> -->
      <div style="margin:0 auto;">
        <v-btn color="#69f0ae" text @click="note(c)">노트</v-btn>
        <v-btn text color="#69f0ae" @click="reveal = true">계약서</v-btn>
      </div>
    </v-card-actions>
    <v-expand-transition>
      <v-card
        v-if="reveal"
        class="transition-fast-in-fast-out v-card--reveal"
        style="height: 100%;"
      >
        <v-card-text class="pb-0">
          <p class="text-h5 text--primary" style="text-align:center; margin-bottom:10px;">
            계약정보
          </p>
          <p class="text-h6 text--primary" style="font-style:bold; margin-bottom:10px;">회원정보</p>
          <p class="text-h8 text--primary">이름:{{c.careTargetName}}({{c.gender}}) / 생년월일:{{c.birth}}</p>
          <p class="text-h8 text--primary">주소: {{c.address}} {{c.detailedAddress}}</p>
          <p class="text-h8 text--primary">반려동물:<span class="check">{{c.pet}}</span> / CCTV:<span class="check">{{c.isCctvAgreement}}</span> / 백신: <span class="check">{{c.coronaTest}}</span></p>
          <p class="text-h8 text--primary">케어타입:{{c.careTpe}} / 장기요양등급: {{c.longTermCareGrade}}</p>
          <p class="text-h6 text--primary" style="font-style:bold; margin-bottom:10px;">계약정보</p>
          <p class="text-h9 text--primary">시작시간: {{c.startDate}}</p>
          <p class="text-h9 text--primary">종료시간: {{c.endDate}}</p>
          <p class="text-h9 text--primary">금여: {{c.amount}}</p>
        </v-card-text>
        <v-card-actions class="pt-0">
          <v-btn
            text
            color="#69f0ae"
            style="margin:0 auto;"
            @click="reveal = false"
          >
            Close
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-expand-transition>
    <!-- <v-expand-transition>
      <div v-show="show">
        <v-divider></v-divider>

        <v-card-text>
          I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
        </v-card-text>
      </div>
    </v-expand-transition> -->
  </v-card>
    
    <!-- <v-dialog
        v-model="contractdialog"
        fullscreen
        hide-overlay
        transition="dialog-bottom-transition"
        scrollable
      >
        <v-card tile>
          <v-toolbar flat dark color="primary">
            <v-btn
              icon
              dark
              @click="close"
            >
              <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-toolbar-title>계약서</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-card-text>
            <v-list
              three-line
              subheader
            >
              <v-subheader></v-subheader>
            <v-subheader>회원 정보</v-subheader>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>회원 아이디:OOO / 회원 이름:OOO </v-list-item-title>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>대상인 이름:OOO / 성별:M</v-list-item-title>
                  <v-list-item-subtitle>생년월일:1999-01-01</v-list-item-subtitle>
                  <v-list-item-subtitle>주소:대구광역시~~</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>
            <v-divider></v-divider>
            <v-list
              three-line
              subheader
            >
              <v-subheader>계약 정보</v-subheader>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>대상인 이름:OOO   /   성별:M</v-list-item-title>
                  <v-list-item-subtitle>생년월일:1999-01-01</v-list-item-subtitle>
                  <v-list-item-subtitle>주소:대구~~</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>Sound</v-list-item-title>
                  <v-list-item-subtitle>Auto-update apps at any time. Data charges may apply</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
              <v-list-item>
                <v-list-item-content>
                  <v-list-item-title>Auto-add widgets</v-list-item-title>
                  <v-list-item-subtitle>Automatically add home screen widgets</v-list-item-subtitle>
                </v-list-item-content>
              </v-list-item>
            </v-list>
          </v-card-text>

          <div style="flex: 1 1 auto;"></div>
        </v-card>
      </v-dialog> -->
  </div>
  
</template>

<script>
export default {
    data(){
        return{
            contract:[],
            // contractdialog:false,
              // show: false,
              reveal : false,
        }
    },
    mounted(){
        this.$http
        .get('/api/carenote/contracts/completion',{
            withCredential : true
        })
        .then((res) => {
            console.log(res.data.body)
            this.contract = res.data.body;

        }).catch((err) => {
            console.log(err) 
        });
    },
    methods:{
      note(c){     
         const caretarget ={
                contractId : c.id,
            };
            this.$store.commit('carenoteStore/caretarget', caretarget);

        console.log(c.id);
        this.$router.push({ name: 'complete_note', params: { contentId: c.id }})
      },
      
      // dialog(c){
      //   this.contractdialog = true;
      //   // console.log(c.pet)
      // },
      // close(){
      //   this.contractdialog = false;
      // }
    }
}
</script>

<style>
    /* .v-toolbar__content{
      height: 0px;
    } */
  .content{
    width: 100%;
    height: 100%;
    margin-bottom: 20px;
  }
  .v-card--reveal {
  bottom: 0;
  opacity: 1 !important;
  position: absolute;
  width: 100%;
}
.check{
  font-size: 15px;
  color: rgba(0, 0, 0, 0.6);
}
.v-application p {
    margin-bottom: 10px;   
}
.title{
  text-align: center;
  /* font-size: 10px; */
}
</style>