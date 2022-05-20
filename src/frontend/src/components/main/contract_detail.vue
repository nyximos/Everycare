<template>
  <div>
      <ul v-for="(c ,index) in contract" :key="index">  
          <li>케어대상인 정보</li>
          <li>{{c.careTargetDetailDTO.address}}</li>
      </ul>
  </div>
</template>

<script>
export default {
    date(){
        return{
            // target:[
            // ],
            // id:this.address,
            // address:this.address,
            contract:[

            ],
        }
    },
    mounted(){
        const id = this.$route.params.contractId;
        // console.log(id)
        this.$http
        .get(`/api/dashboard/caresitter/contracts/${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.contract = res.data.body;
            console.log(this.contract)
            // this.target = res.data.body.careTargetDetailDTO;
            // this.address = res.data.body.careTargetDetailDTO.address;
            // console.log(this.address);
            // console.log(res.data.body.careTargetDetailDTO);

        }).catch(err=>{
            console.log(err);
        })
    }
}
</script>

<style>

</style>