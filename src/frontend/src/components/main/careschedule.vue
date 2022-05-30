<template>
    <div class="content">
        <div class="head">
            <div class="title">
            <!-- <h1>{{schedule.date}}</h1> -->
                <h2> 스케줄</h2>
                <div>
                    <!-- {{schedule.date}} -->
                    <span class="tit"><h4>케어대상인: OOO</h4></span>
                    <v-btn class="ma-2" outlined color="indigo">활동 종료</v-btn>
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
                </tr>
            </thead>
            <tbody v-for="( s ,index) in schedule" :key="index">
                <tr>
                    <td>{{ s.id }}</td>
                    <td>{{s.startTime}}</td>
                    <td>{{s.name}}</td>
                    <td>{{s.requirement}}</td>
                    <td v-if="storeFileName=''">{{s.storeFileName}}</td>
                    <td v-else @click="add()">사진</td>
                    <td>활동</td>
                </tr>
            </tbody>
        </table>
        </div>
        <br><br><br><br><br><br><br><br>
        </div>

        <div class="modal1">
         <v-dialog v-model="addDialog" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card>
          <v-card-text style="max-height: 550px">
            <v-card-text>
                <h2>이름:{{schedule.name}}</h2>
            </v-card-text>
            <div class="img">    
                <img v-if="storeFileName=''" src="" alt="사진없음">
                <img v-else src="@/assets/image.png" >
            </div>
            <v-container fluid class="btn">
              <!-- <v-btn class="ma-2" outlined color="indigo" style="width:250px;">수정</v-btn> -->
              <!-- <label for="input_file?> -->
                <!-- <img :src="avatar" class="img-avatar">
                <input type="file" name="avatar" id="uppic" accept="image/gif,image/gif,image/jpg,image/png" @change="changeImage($event)" ref="avatarInput" class="uppic" label="파일"> -->
              <!-- </label> -->
              <!-- <label>
              <input type="text" v-model="m" disabled>
              </label> -->
              <input type="file" style="margin:0 auto;" class="file">
            </v-container>
            
          </v-card-text>
          <v-divider style="margin:auto;"></v-divider>
          <v-card-actions>
            <div style="margin:auto;">
            <v-btn class="ma-2" outlined color="indigo" @click="submit">등록</v-btn>
            <v-btn class="ma-2" outlined color="indigo" @click="closeDialog">닫기</v-btn>
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
            this.schedule= res.data.body.activityInformationDTOs
            console.log(this.schedule)
            console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{
        closeDialog(){
            this.addDialog = false;
        },
        add(){
            this.addDialog = true;
        },
        submit(){
            const carenoteId = this.$route.params.contentId;
    
            this.$http
            .patch(`/api/carenote/${carenoteId}/schedules/${activityId}/photo`,{

            })
        }
    }
}
</script>

<style>
    .content{
        width: 100%;
    }
    .img{
        text-align:center;
        background: #d2d2d2;
        width: 250px;
        height: 200px;
        margin: 0 auto;
    }
    img{
        margin: 0 auto;
        width: 160px;
        height: 150px;
        padding: 20px;
        margin-top: 15px;
        /* background: gray; */
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
    .file{
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
        height: 70px;
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
    .content-table tbody tr td{
        text-align: center;
    }
    .btn{
        align-items: center;
    }
</style>