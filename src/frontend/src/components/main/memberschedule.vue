<template>
  <div class="content">
        <div class="head">
            <div class="title">
                <h2 class="main_title"> 스케줄</h2>
                <div class="tit">
                        <span class="name">케어대상인: OOO</span>
                        <span class="name">케어시터: OOO</span>
                        <!-- <span class="button"><v-btn depressed>출근/퇴근 정보</v-btn></span> -->
                        <span class="button"><v-btn depressed @click="information">후기작성 정보</v-btn></span>
                </div>
          
            </div>
            <div class="review">
             <v-chip class="ma-2" color="success" outlined v-for="(c,index) in cate" :key="index" style="width:100px;" @click="write(c)">
                 <!-- <v-icon left>mdi-server-plus</v-icon> -->
                <h6 class="cate_name">{{c.name}}</h6>
            </v-chip>
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
                    <td v-if="s.storeFileName == null">사진</td>
                    <td v-else>
                        <img :src="'https://localhost:8086/api/images/' + s.storeFileName">
                    </td>
                    <td v-if="s.content == null">활동</td>
                    <td v-else>
                        {{s.content}}
                    </td>
                </tr>
            </tbody>
        </table>
        </div>
        <br><br><br><br><br><br><br><br>
    </div>
    <v-dialog v-model="Dialog" max-width="500px" @click:outside="closeDialog" @keydown.esc="closeDialog">
         <v-card>
          <v-card-text style="max-height: 550px">
            <v-card-text>
                <h2 class="title01">후기작성</h2>
            </v-card-text>
            <v-card-text>
                <div>
                    <span><h5 class="title02">{{this.$store.state.carenoteStore.name}}</h5></span>
                    <span><v-rating v-model="rating" background-color="indigo lighten-3" color="indigo" size="30"></v-rating></span>
                </div>
            </v-card-text>
             <v-col cols="12" md="12">
                <v-textarea solo name="input-7-4" label="내용을 입력하시오" v-model="text"></v-textarea>
            </v-col>
          
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
    
</template>

<script>
export default {
    data(){
        return{
            schedule:[],
            Dialog:false,
            rating:0,
            text:'',
            cate:[]
        }
    },
    mounted(){
        const id = this.$route.params.contentId;
        this.$http
        .get(`/api/carenotes/${id}/schedules`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.schedule= res.data.body.activityInformationDTOs
            
            // console.log("데이터" + res);
        })
        .catch((err)=>{
            console.log(err);
        }),
        this.$http
        .get(`/api/carenote/${id}/categories`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.cate = res.data.body;
        })
        .catch((err)=>{
            console.log(err);
        })
    },
    methods:{
        write(c){
            this.Dialog = true
            const caretarget ={
                categoryId : c.id,
                name : c.name
            };
            this.$store.commit('carenoteStore/caretarget', caretarget);
        },
        closeDialog(){
            this.Dialog=false
        },
        submit(){
            const id =this.$route.params.contentId;
            var formData = new FormData();
            formData.append('rating', this.rating)
            formData.append('comment', this.text)
            formData.append('activityClassificationId', this.$store.state.carenoteStore.categoryId)
            this.$http
            .post(`/api/carenote/${id}/reviews`,formData,{
                withCredentail:true
            })
            .then((res)=>{
                console.log(res);
                alert("등록완료")
            })
            .catch((err)=>{
                console.log(err);
            })
        },
        information(c){
            this.$router.push({name:'review_list' , params:{contentId:this.$route.params.contentId}})
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
    .v-chip__content{
        margin: 0 auto;
    }
    .name{
        align-items: left;
    }
    .review{
        padding-left: 20px;
    }
    .main_title{
        text-align: center;
    }
    .cate_name{
        text-align: center;
        margin: 0 auto;
        margin-bottom: 0px;
    }
    h6{
        margin-bottom: 0px;
        text-align: center;
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
        padding: 20px;
    }
    .ma-2{
        width: 200px;
        /* float: right; */
    }
     .table{
        width: 100%;
        padding: 20px;
    }
    .title01{
        text-align: center;
        color: black;
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
</style>