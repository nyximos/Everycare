<template>

  <div class="content">
      <div class="table">
        <h3 class="title">대상인 이름:{{detail.careTargetName}}</h3>
        <h5 class="subtitle">날짜:{{detail.startTime.slice(0,10)}}~{{detail.endTime.slice(0,10)}}</h5>
        
        <!-- <div>
            <h2>스케줄</h2>
        </div> -->
        <table  style="margin:0 auto;" class="content-table">

            <thead>
                <tr class="head">
                    <th>Id</th>
                    <th>시작 시간</th>
                    <th>끝나는 시간</th>
                    <th>스케줄명</th>
                    <th>요구사항</th>
                </tr>
            </thead>
            <tbody v-for="( n ,index) in note" :key="index">
                <tr>
                    <td>{{ n.id }}</td>
                    <td>{{n.startTime}}</td>
                    <td>{{n.endTime}}</td>
                    <td>{{n.name}}</td>
                    <td>{{n.requirement}}</td>

                </tr>
            </tbody>
        </table>
        <br><br><br>
        <table  style="margin:0 auto;" class="content-table01">
            <thead>
                <tr class="head01">
                    <th>Id</th>
                    <th>name</th>
                    <th>수정 날짜</th>
                    <th>comment</th>
                    <th>review</th>
                </tr>
            </thead>
            <tbody v-for="( n ,index) in note01" :key="index">
                <tr>
                    <td>{{ index+1 }}</td>
                    <td>{{n.categoryName}}</td>
                    <td>{{n.updatedAt.slice(0,10)}}</td>
                    <td>{{n.comment}}</td>
                    <td><v-rating v-model="note01[index].rating"
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
  </div>
</template>

<script>
export default {
    data(){
        return{

            detail:[],
            note:[],
            note01:[],
            review:[]

        }
    },
    mounted(){
        const contractId = this.$route.params.contractId;

        // console.log(contractId);
        const id = this.$route.params.noteId;
        // console.log(id);

        this.$http
        .get(`/api/carenote/contracts/completion/${contractId}/carenotes/${id}`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);

            this.detail = res.data.body
            this.note = res.data.body.careNoteActivityInformationDTOs
            this.note01 = res.data.body.careSitterCareNoteReviewDTOs
            // console.log(this.note);
            // console.log(this.note01);

        })
        .catch((err)=>{
            console.log(err);
        })
    }
}
</script>

<style>

    .content{
        width: 100%;
    }
    .table{
        padding: 100px;
    }
    .title{
        text-align: center;
        padding: 20px;
    }
    .subtitle{
        text-align: right;
        padding: 10px;
    }

    .content-table{
        border-collapse: collapse;
        margin: 25px 0;
        font-size: 0.9em;

        min-width: 300px;

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

     .content-table01{
        border-collapse: collapse;
        margin: 25px 0;
        font-size: 0.9em;
        min-width: 400px;
        width: 100%;
    }
    .content-table01 thead tr{
        background-color: lightgreen;
        color: #ffffff;
        text-align: center;
        height: 70px;
    }
    .content-table01 th,
    .content-table01 td{
        padding: 12px 15px;
    }

    .content-table01 tbody tr{
        border-bottom: 1px solid #dddddd;
    }

    .content-table01 tbody tr:nth-of-trpe(even){
        background-color: #f3f3f3;
    }

    .content-table01 tbody tr:last-of-type{
        border-bottom: 2px solid lightgreen;
    }
    .content-table01 tbody tr td{
        text-align: center;
    }

</style>