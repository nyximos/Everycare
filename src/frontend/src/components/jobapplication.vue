<template>
<div>
  <v-card v-for="(p,index) in profiles" :key="index"
    class="mx-auto" 
    max-width="344"
    outlined
  >
    <v-list-item three-line>
      <v-list-item-content>
        <div class="text-overline mb-4">
          {{p.name}}
        </div>
        <v-list-item-title class="text-h5 mb-1">
          {{p.introduction}}
        </v-list-item-title>
        <v-list-item-subtitle>{{p.introduction}}</v-list-item-subtitle>
      </v-list-item-content>

      <v-list-item-avatar
        tile
        size="80"
        color="grey"
      ></v-list-item-avatar>
    </v-list-item>

    <v-card-actions>
      <v-btn
        outlined
        rounded
        text
      >
        승낙
      </v-btn>
    </v-card-actions>
  </v-card>
</div>
</template>

<script>
export default {
    name:'JobApplication',
    data() {
        return {
            profiles: [],
            // id: this.id
            // id: this.$route.params.caresitterId,
            
            
        }
    },
 mounted() {
    // const id = this.$store.state.userStore.id
     
    this.$http
    .get('/api/dashboard/contracts', {
      withCredentials: true
    })
    .then((res) => {
          // console.log(res)
          console.log(res.data.body)
          // this.id = res.data.body.id
        
  }).catch(err => {
    alert(err);
    console.log(err);
  })
  const id = this.$route.params.contractId;
    this.$http
    .get(`/api/dashboard/recruitions/${id}/caresitters`, {
      withCredentials: true
    })
      .then((res) => {
          console.log(res.data.body)
          this.profiles = res.data.body
        
          
  }).catch(err => {
    alert(err);
    console.log(err);
  })
}
}
</script>

<style>

</style>