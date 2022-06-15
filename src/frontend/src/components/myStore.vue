<template>

<v-card height="350px">
    <v-navigation-drawer
      absolute
      permanent
    >
      <template v-slot:prepend>
        <v-list-item two-line>
          <v-list-item-avatar>
            <img src="https://randomuser.me/api/portraits/women/81.jpg">
          </v-list-item-avatar>

          <v-list-item-content>
            <v-list-item-title>{{id}}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </template>

      <v-divider></v-divider>

      <v-list dense>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
          @click="message(item)"
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </v-card>
</template>

<script>
export default {
    mounted() {
        this.$http.get('/api/dashboard/store/products',{
        withCredentials:true
      })
		.then((res)=>{
        console.log(res.data);
        // this.applications=res.data.body
      }).catch(err =>{
		console.log(err);
        
	})
    },
data(){
    return{
        id: this.$route.params.contentId,
        items: [
          { title: '상품 등록', icon: 'mdi-home-city', name:'ProdCreate'},
          { title: '상품 수정', icon: 'mdi-account', name:'prodEdit'},
          { title: '스토어 수정', icon: 'mdi-account-group-outline', name:'storeEdit' },
        ],
      }
    },
    methods:{
        message(item){     
            this.$router.push({ name: item.name, params: { contentId: this.$store.state.userStore.storeId}})
        }
        
    }
}
</script>

<style>

</style>