<template>
  <div>
    <br><br><br>
    <div v-if="!contract.length">신청된 계약서가 없습니다.</div>
      <v-card
      v-for="(c,index) in contract" :key="index"
    class="mx-auto"
    max-width="344"
  >
    <v-card-text>
      <div>아이디:{{c.id}}</div>
      <p class="text-h6 text--primary">
        제목:{{c.title}}
      </p>
      급여:<span>{{c.amount}}</span>
      <div class="text--primary">
        날짜:{{c.startDate}}~{{c.endDate}}
      </div>
    </v-card-text>
    <v-card-actions>
      <v-btn
        text
        color="success"
        @click="move(c)"
      >
        상세보기
      </v-btn>
    </v-card-actions>
  </v-card>
  <br><br><br><br><br><br><br><br><br><br>
  </div>
  <!-- <div class="cPrdlists_wrap cPrdlists_wrap_respon">
  <div class="cPrdlists_rows">
    <h2 class="cPrdlists_title">계약서</h2>
  </div>
  <div class="cPrdlists_rows basic cPrdlists_section">
    <div class="cPrdlists_rowsgrp">
      <div class="cPrdlists_box">
        <div class="cPrdlists_box_pos">
          <div class="cPRdlists_box_text">
            <div class="cCpName">asd</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div> -->
</template>

<script>
export default {
    data(){
        return{
            contract:[]
        }
    },
    mounted(){
        this.$http
        .get('/api/dashboard/caresitter/contracts',{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.contract = res.data.body; 
        }).catch(err=>{
            console.log(err);
        })
    },
    methods:{
        move(c){
            this.$router.push({name:'contract_detail',params:{contractId:c.id}})
        }
    }
}
</script>

<style>
  /* .mx-auto{
    margin-bottom: 50px;
  } */
  .cPrdlists_wrap{
    width: 100%;
    font-size: 15px;
    letter-spacing: -0.05em;
    margin: 0 auto;
    padding: 23px 0 0 0;
  }
  .cPrdlists_section{
    padding-bottom: 12px;
  }
  .cPrdlists_rows{
    max-width: 1260px;
    margin: 0 auto;
  }
  .cPrdlists_title{
    position: relative;
    font-weight: bold;
    font-size: 20px;
    line-height: 33px;
    color: #121212;
    margin: 0 0 12px 0;
  }
  .cPrdlists_rows:after{
    content: "";
    display: block;
    clear: both;
  }
  .cPrdlists_rowsgrp{
    width: calc(100% - 16px);
  }
  .cPrdlists_rows.basic .cPrdlists_cols{
    margin-bottom: 16px;
    height: 156px;
  }
  .cPrdlists_cols .cPrdlist_box_text{
    position: absolute;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    padding: 0 20px;
}
</style>