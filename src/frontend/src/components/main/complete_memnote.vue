<template>
  <div class="content">
    <div v-if="!note.length"><h2 class="text">완료된 노트가 없습니다.</h2></div>
    <v-card class="mx-auto" max-width="300" v-for="(n,index) in note" :key="index">
    <v-img v-if="n.storeFileName==null"
    src="@/assets/note.png"
    height="200px"
    width="200px"
    style="margin:0 auto;"
    ></v-img>

    <v-img 
    v-else
    :src="'https://localhost:8086/api/images/' + n.storeFileName"
    height="250px"
    width="250px"
    style="margin:0 auto;"
    ></v-img>
    <!-- <v-card-title>
        <h4 class="write">{{n.date}}</h4>
    </v-card-title> -->
    <v-card-title>
      <h5 class="write">{{n.startTime.slice(0,10)}} ~ {{n.endTime.slice(0,10)}}</h5>

    </v-card-title>
    <v-card-actions>
      <v-btn
        color="#69f0ae"
        width="100%"
        text
        @click="detail(n)"
      >
        스케줄 관리
      </v-btn>

      <v-spacer></v-spacer>
    </v-card-actions>

  </v-card>
  <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-bottom-transition"
    >
      <v-card>
        <v-toolbar
          dark
          color="#69f0ae"
        >
          <v-btn
            icon
            dark
            @click="dialog = false"
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title>노트 상세보기</v-toolbar-title>
        </v-toolbar>
        <v-list
          three-line
          subheader
        >

          <h3 class="title01">케어 대상인 이름:{{notedetail.careTargetName}}</h3>
          <v-list-item>
            <!-- <v-list-item-content>
              <v-list-item-title>시간</v-list-item-title>
              <v-list-item-subtitle>{{notedetail.startTime}} ~ {{notedetail.endTime}}</v-list-item-subtitle>
            </v-list-item-content> -->
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <section class="main">
            <div class="board">
              <h2 class="subtitle">활동</h2>
                <table class="table">
                    <thead>

                <tr>
                    <th>Id</th>
                    <th>시간</th>
                    <th>스케줄명</th>
                    <th>요구사항</th>
                </tr>
            </thead>
            <tbody v-for="( a ,index) in activity" :key="index">
                <tr>
                    <td>{{index+1 }}</td>
                    <td>{{a.startTime}}~{{a.endTime}}</td>
                    <td>{{a.name}}</td>
                    <td>{{a.requirement}}</td>
                </tr>
            </tbody>

                </table>
            </div>
    </section>

            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list
          three-line
          subheader
        >

          <v-list-item>
            <v-list-item-content>
            <section class="main">
            <div class="board">
              <h2 class="subtitle">리뷰</h2>
                <table class="table">
                    <thead>
                <tr>
                    <th>Id</th>
                    <th>name</th>
                    <th>수정 날짜</th>
                    <th>comment</th>
                    <th>review</th>
                </tr>
            </thead>
            <tbody v-for="( r ,index) in review" :key="index">
                <tr>
                    <td>{{ index+1 }}</td>
                    <td>{{r.categoryName}}</td>
                    <td>{{r.updatedAt.slice(0,10)}}</td>
                    <td>{{r.comment}}</td>
                    <td><v-rating v-model="review[index].rating"
                            background-color="green lighten-2"
                            color="green lighten-2"
                            size="30"
                            readonly
                            ></v-rating>
                    </td>
                </tr>
            </tbody>
                </table>
            </div>
    </section>

            </v-list-item-content>
          </v-list-item>
        </v-list>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
export default {
    data(){
        return{
            note:[],
            dialog:false,
            notedetail:[],
            activity:[],
            review:[],
        }
    },
    mounted(){
        const caretargetId = this.$route.params.targetId
        const contractId = this.$route.params.contractId
        this.$http
        .get(`/api/member/caretargets/${caretargetId}/contracts/complition/${contractId}/carenotes`,{
            withCredential:true
        }).then((res)=>{
            console.log(res.data.body)
            this.note = res.data.body

        }).catch((err)=>{
            console.log(err)
        })
    },
    methods:{
        detail(n){
          this.dialog = true
          const caretargetId = this.$route.params.targetId
          const contractId = this.$route.params.contractId
          const id =n.id

          this.$http
          .get(`/api/member/caretargets/${caretargetId}/contracts/complition/${contractId}/carenotes/${id}`,{
            withCredential:true
          }).then((res)=>{
            console.log(res.data.body)
            this.notedetail = res.data.body
            this.activity = res.data.body.careNoteActivityInformationDTOs
            this.review = res.data.body.careSitterCareNoteReviewDTOs
            console.log(this.activity)
          }).catch((err)=>{
            console.log(err)
          })
        }

    }
}
</script>

<style>
.text{
  text-align: center;
  padding-top: 40px;
}
.title01{
  padding-top: 10px;
  text-align: center;
}
    html{
    height:100%;
}

body{
    margin: 0px;
    height:100%;
}

.main{
    height:100%;
    width: 100%;
}


.d-none{
    display: none;
}

.board{
    width: 1200px;
    height: 100%;
    margin: auto;
}

 

.table{
    font-size :15px;
    border-collapse: collapse;
    width: 1200px;
    margin-top:20px;
    border: 1px solid gray;    
    text-align: center;
}

.thead td {
    background: #B2B2B2;
}

td{
    height: 20px;
    border-bottom:1px solid black;
}

</style>