<template>
  <div>
      <!-- <ul v-for="(c,index) in certification" :key="index">
        <li>{{certification[index].id}}</li>
        <li>{{certification[index].name}}</li>
        <li>{{certification[index].createdAt}}</li>
        <li>{{certification[index].approvalDate}}</li>
      </ul> -->
      <br><br><br>
      <section class="main">
            <div class="board">
              <h2>자격증 조회</h2>
                <table class="table" v-for="(c,index) in certification" :key="index">
                    <thead class="thead">
                        <tr>
                            <td>번호</td>
                            <td>사진</td>
                            <td>자격증 이름</td>
                            <td>등록일시</td>
                            <td>승인일시</td>
                        </tr>
                    </thead>
                    <tbody class="tbody">
                        <tr>
                            <td>{{certification[index].id}}</td>
                            <td><img id="divProfile" :src="'https://localhost:8086/api/images/' + certification[index].storeName" alt="사진" width="50px" height="30px" @click="picture"></td>
                            <td>{{certification[index].name}}</td>
                            <td>{{certification[index].createdAt}}</td>
                            <td>{{certification[index].approvalDate}}</td>
                        </tr>
                    </tbody>
                </table>
            </div>

    </section>
      <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
  </div>
</template>

<script>
export default {
  data(){
    return{
      certification:[

      ],
      id:this.$store.state.userStore.careSitterId
    }
  },
  mounted(){
    const id = this.$store.state.userStore.careSitterId;

    this.$http
    .get(`/api/dashboard/caresitter/${id}/certifications`,{
        withCredentials:true
    })
    .then((res)=>{
      console.log(res.data.body);
      this.certification = res.data.body;
    }).catch(err=>{
      console.log(err);
    })
  },
  methods:{
    picture(){
      
    }
  }
}
</script>

<style>
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
    height: 40px;
    border-bottom:1px solid black;
}

</style>