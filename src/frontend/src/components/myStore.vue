<template>
<div>
  <v-row>
    <v-col cols="2">
 <v-card
    height="800"
    elevation="0"
  >
    <v-navigation-drawer
      permanent
    >
      <v-list>
        <v-list-item
          v-for="item in items"
          :key="item.title"
          link
        >
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>

      <template v-slot:append>
        <div class="pa-2">
          <v-btn block>
            Logout
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </v-card>
    </v-col>
    <v-col cols="10">
      <v-card
       elevation="0"
       class="mt-5"
  >
  <v-card-title>월별 판매량</v-card-title>
    <v-sheet
      color="blue"
      max-width="calc(100% - 32px)"
    >
      <v-sparkline
        :labels="labels"
        :value="value"
        color="white"
        line-width="2"
        padding="16"
      ></v-sparkline>
    </v-sheet>

    <v-card-text class="pt-0">
      <div class="text-h6 font-weight-light mb-2">
        User Registrations
      </div>
      <div class="subheading font-weight-light grey--text">
        Last Campaign Performance
      </div>
      <v-divider class="my-2"></v-divider>
      <v-icon
        class="mr-2"
        small
      >
        mdi-clock
      </v-icon>
      <span class="text-caption grey--text font-weight-light">last registration 26 minutes ago</span>
    </v-card-text>
  </v-card>
    </v-col>

      
  </v-row>
</div>
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
 labels: [
        '1',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
        '9',
        '10',
        '11',
        '12'
      ],
      value: [
        200,
        675,
        410,
        390,
        310,
        460,
        250,
        240,
        100,
        100,
        150,
        100
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