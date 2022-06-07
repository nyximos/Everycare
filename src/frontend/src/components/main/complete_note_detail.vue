<template>
  <div>
      detail
      <div class="table">
        <table  style="margin:0 auto;" class="content-table" >
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
                     <!-- <td>{{n.careNoteActivityInformationDTOs.startTime}}</td> -->
                    <!-- <td>{{n.careNoteActivityInformationDTOs.endTime}}</td>
                    <td>{{n.careNoteActivityInformationDTOs.name}}</td>
                    <td>{{n.careNoteActivityInformationDTOs.requirement}}</td>  -->
                    <!-- <td>{{}}</td> -->
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
            note:[],
            note01:[]
        }
    },
    mounted(){
        const contractId = this.$route.params.contractId;
        console.log(contractId);
        const id = this.$route.params.noteId;
        console.log(id);
        this.$http
        .get(`/api/carenote/contracts/completion/${contractId}/carenotes/${id}`,{
            withCredentail:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.note = res.data.body.careNoteActivityInformationDTOs
            this.note01 = res.data.body.careSitterCareNoteReviewDTOs
            console.log(this.note);
            console.log(this.note01);
        })
        .catch((err)=>{
            console.log(err);
        })
    }
}
</script>

<style>
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