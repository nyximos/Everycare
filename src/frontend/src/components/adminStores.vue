<template>
    <div>
      <v-data-table
        :headers="headers"
        :items="applications"
        :items-per-page="5"
        :search="search"
        class="elevation-1"
        @click:row="storeClick"
      >
      <template v-slot:top>
        <v-text-field
          v-model="search"
          append-icon="mdi-magnify"
          label="Search"
          placeholder="스토어명"
          class="mx-4"
        ></v-text-field>
      </template></v-data-table>
    </div>
</template>

<script>
export default {
mounted() {
    this.$http.get('/api/admin/stores',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        this.applications=res.data.body
      }).catch(err =>{
		console.log(err);
	})
},
data(){
    return{
        headers:[
            {text:'아이디', value:'id'},
            {text:'스토어명', value:'name'},
            {text:'멤버', value:'memberId'},
            ],
        applications:[],
        search: '',
    }
},
methods: {
    storeClick(value){
      this.$router.push({
      name: 'adminStoresDetail',
      params: {
        contentId: value.id
      }
    })
    }
},
}
</script>

<style>

</style>