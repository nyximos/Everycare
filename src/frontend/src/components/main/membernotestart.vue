<template>
    <div class="content">
        <div class="head">
            <div class="title">
                <h2> 스케줄</h2>
                <div>
                    <span class="tit"><h4>케어대상인: OOO</h4></span>
                    <span class="tit"><h4>케어 시터: OOO</h4></span>
                </div>
            </div>
        <div class="table">
        <table  style="margin:0 auto;" class="content-table" >
            <thead>
                <tr class="head">
                    <th>Id</th>
                    <th>시간</th>
                    <th>스케줄명</th>
                    <th>요구사항</th>
                    <th>사진</th>
                    <th>활동</th>
                    <th>후기</th>


                </tr>
            </thead>
            <tbody v-for="(s,index) in schedule" :key="index">
                <tr>
                    <td>{{ s.date }}</td>
                    <td>{{s.date}}</td>
                    <td>{{s.date}}</td>
                    <td>{{s.date}}</td>
                    <td @click="add">{{s.date}}</td>
                    <td>{{s.date}}</td>
                    <td>{{s.date}}</td>
                </tr>
            </tbody>
        </table>
        </div>
        <br><br><br><br><br><br><br><br>
        </div>

        <div class="modal1">
     <v-dialog v-model="introDialog"
              max-width="500px"
              @click:outside="closeDialog"
              @keydown.esc="closeDialog"
              >
         <v-card>
          <v-card-text style="max-height: 550px">
            <v-card-text class="v-time-picker">
                <h2>후기작성</h2>
            </v-card-text>
            <img src="" alt="사진없음">
            <v-container fluid>
              <h2>활동 이름</h2>
              <v-rating empty-icon="$mdiStarOutline" full-icon="$mdiStar" half-icon="$mdiStarHalfFull" hover length="5" size="64" value="3"></v-rating>
            </v-container>
            

            
            <v-container fluid>
                <v-textarea solo name="input-7-4" label="Solo textarea"></v-textarea>
            </v-container>
            </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <div style="margin:auto;">
            <v-btn class="ma-2" outlined color="indigo">등록</v-btn>
            <v-btn class="ma-2" outlined color="indigo">닫기</v-btn>
            </div>
          </v-card-actions>
        </v-card>
    </v-dialog>

    <v-dialog v-model="addDialog"
              max-width="500px"
              @click:outside="closeDialog"
              @keydown.esc="closeDialog"
              >
         <v-card>
          <v-card-text style="max-height: 550px">
            <v-card-text class="v-time-picker">
                <h2>출근/ 퇴근 정보</h2>
            </v-card-text>
            <img src="" alt="사진없음">
            <v-container fluid>
              <h2>출근 시간</h2>
              <h2>퇴근 시간</h2>
            </v-container>
            
            <v-container fluid>
              <h2>출근 시간</h2>
              <h2>퇴근 시간</h2>
            </v-container>
                <img src="#" alt="사진">
            </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <div style="margin:auto;">
            <v-btn class="ma-2" outlined color="indigo">닫기</v-btn>
            </div>
          </v-card-actions>
        </v-card>
    </v-dialog>
  </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
            schedule:[],
            addDialog:false
        }
    },
    mounted(){

        const id = this.$route.params.contentId;
        this.$http
        .get(`/api/carenotes/${id}/schedules`,{
            withCredentail:true
        })
        .then((res)=>{
            // console.log(res.data.body);
            this.schedule= res.data.body
            console.log(this.schedule)
            console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        })


    },
    methods:{
        closeDialog(){
            addDialog = false;
        }
    }
}
</script>

<style>
    .content{
        width: 100%;

    }
    .head{
        margin: 20px auto;
        width: 1000px;
    }
    .tit{
        text-align: center;
    }
    .ma-2{
        width: 200px;
        /* float: right; */
    }
    /* .btn{
    } */
    .table{
        width: 100%;
        padding: 20px;
    }
    .content-table{
        border-collapse: collapse;
        margin: 25px 0;
        font-size: 0.9em;
        min-width: 400px;
        width: 100%;
    }
    .content-table thead tr{
        background-color: #009879;
        color: #ffffff;
        text-align: center;
    }
    .content-table th,
    .content-table td{
        padding: 12px 15px;
    }

    .content-table tbody tr{
        border-bottom: 1px solid #dddddd;
    }

    .content-table tbody tr:nth-of-trpe(even){
        background-color: #f3f3f3;
    }

    .content-table tbody tr:last-of-type{
        border-bottom: 2px solid #009879;
    }
</style>