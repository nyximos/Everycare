<template>
  <div class="content">
      <v-card
    class="mx-auto"
    max-width="300"
    v-for="(n,index) in note" :key="index">
    <v-img 
    v-if="n.storename != null"
      src="'https://localhost:8086/api/images/' + n.storeName"
      height="250px"
    ></v-img>
    <v-img 
    v-else
    src="@/assets/note.png"
    height="200px"
    width="200px"
    style="margin:0 auto;"
    ></v-img>
    <v-card-title>
        <h4 class="write">{{n.date}}</h4>
    </v-card-title>
    <v-card-title>
      시간:<h5 class="write" format="yyyy-MM-dd HH:mm:ss">{{n.startTime}} ~ {{n.endTime}}</h5>
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
          <v-subheader>케어 대상인 이름:{{notedetail.careTargetName}}</v-subheader>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>날짜</v-list-item-title>
              <v-list-item-subtitle>{{notedetail.date}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>시간</v-list-item-title>
              <v-list-item-subtitle>{{notedetail.startTime}} ~ {{notedetail.endTime}}</v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>
          <v-list-item>
            <v-list-item-content>
          <table border="2px">
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
            </v-list-item-content>
          </v-list-item>
        </v-list>
        <v-divider></v-divider>
        <v-list
          three-line
          subheader
        >
          <v-subheader>리뷰</v-subheader>
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>Notifications</v-list-item-title>
              <v-list-item-subtitle>Notify me about updates to apps or games that I downloaded</v-list-item-subtitle>
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

</style>