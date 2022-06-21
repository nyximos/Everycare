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
    </v-col>
    <v-col cols="10">
      <v-card
       elevation="0"
       class="mt-5"
  >
  <v-card-title>월별 판매량</v-card-title>
  <input type="date" name="" id="" v-model="startdate">
      <input type="date" name="" id="" v-model="enddate">
            <v-btn @click="searchh">search</v-btn>

    <h6>1 : <label>{{this.jan}}</label> </h6>
    <h6>2 : <label>{{this.feb}}</label></h6>
    <h6>3 : <label>{{this.mar}}</label></h6>
    <h6>4 : <label>{{this.apr}}</label></h6>
    <h6>5 : <label>{{this.may}}</label></h6>
    <h6>6 : <label>{{this.june}}</label></h6>
    <h6>7 : <label>{{this.jul}}</label></h6>
    <h6>8 : <label>{{this.aug}}</label></h6>
    <h6>9 : <label>{{this.sep}}</label></h6>
    <h6>10 : <label>{{this.oct}}</label></h6>
    <h6>11 : <label>{{this.nov}}</label></h6>
    <h6>12 : <label>{{this.dec}}</label></h6>

    <v-card-text class="pt-0">
      <p>상품 별 통계</p>
        <input v-model="searchValue" placeholder="상품 아이디 입력"/>
    <input type="date" name="" id="" v-model="startsearch">
      <input type="date" name="" id="" v-model="endsearch">
            <v-btn @click="searchprod">search</v-btn><br>
            {{prodTotal}}
    </v-card-text>
  </v-card>
    </v-col>      
  </v-row>
</div>
</template>

<script>
export default {
data(){
    return{
        jan:this.jan,
        feb:this.feb,
        mar:this.mar,
        apr:this.apr,
        may:this.may,
        june:this.june,
        jul:this.jul,
        aug:this.aug,
        sep:this.sep,
        oct:this.oct,
        nov:this.nov,
        dec:this.dec,
        prodTotal:this.prodTotal,
        search:this.search,
        startdate:this.startdate,
        enddate:this.enddate,
        id: this.$route.params.contentId,
        searchValue:this.searchValue,
        startsearch:this.startsearch,
        endsearch:this.endsearch,
        sum1: this.sum1,
        items: [
          { title: '상품 등록', icon: 'mdi-home-city', name:'ProdCreate'},
          { title: '상품 관리', icon: 'mdi-home-city', name:'storeProdList'},
          { title: '스토어 관리', icon: 'mdi-account-group-outline', name:'storeEdit' },
        ],
      }
    },
    methods:{
        message(item){     
            this.$router.push({ 
              name: item.name, 
              params: { contentId: this.$store.state.userStore.storeId}
              })
            },
        searchh(){     
          this.$http
          .get('/api/store/statistics',
          {params: {startDate:this.startdate, endDate:this.enddate}},{
          withCredentials:true
          })
        .then(res => {
          console.log(res.data);
          this.jan = res.data.body[1];
          this.feb = res.data.body[2];
          this.mar = res.data.body[3];
          this.apr = res.data.body[4];
          this.may = res.data.body[5];
          this.june = res.data.body[6];
          this.jul = res.data.body[7];
          this.aug = res.data.body[8];
          this.sep = res.data.body[9];
          this.oct = res.data.body[10];
          this.nov = res.data.body[11];
          this.dec = res.data.body[12];
          let sum1=0;
          for (let i = 0; i < Object.keys(this.jan).length; i++) {
            sum1 += parseInt(Object.keys(this.jan)[i])
            this.jan = sum1
          }
          let sum2=0;
          for (let i = 0; i < Object.keys(this.feb).length; i++) {
            sum2 += parseInt(Object.keys(this.feb)[i])
          }
          let sum3=0;
          for (let i = 0; i < Object.keys(this.mar).length; i++) {
            sum3 += parseInt(Object.keys(this.mar)[i])
          }
          let sum4=0;
          for (let i = 0; i < Object.keys(this.apr).length; i++) {
            sum4 += parseInt(Object.keys(this.apr)[i])
          }
          let sum5=0;
          for (let i = 0; i < Object.keys(this.may).length; i++) {
            sum5 += parseInt(Object.keys(this.may)[i])
          }
          let sum6=0;
          for (let i = 0; i < Object.keys(this.june).length; i++) {
            sum6 += parseInt(Object.keys(this.june)[i])
          }
          console.log(sum6)
          let sum7=0;
          for (let i = 0; i < Object.keys(this.jul).length; i++) {
            sum7 += parseInt(Object.keys(this.jul)[i])
          }
          let sum8=0;
          for (let i = 0; i < Object.keys(this.aug).length; i++) {
            sum8 += parseInt(Object.keys(this.aug)[i])
          }
          let sum9=0;
          for (let i = 0; i < Object.keys(this.sep).length; i++) {
            sum9 += parseInt(Object.keys(this.sep)[i])
          }
          let sum10=0;
          for (let i = 0; i < Object.keys(this.oct).length; i++) {
            sum10 += parseInt(Object.keys(this.oct)[i])
          }
          let sum11=0;
          for (let i = 0; i < Object.keys(this.nov).length; i++) {
            sum11 += parseInt(Object.keys(this.nov)[i])
          }
          let sum12=0;
          for (let i = 0; i < Object.keys(this.dec).length; i++) {
            sum12 += parseInt(Object.keys(this.dec)[i])
          }
          this.jan= sum1;
          this.feb= sum2; 
          this.mar= sum3;
          this.apr= sum4;
          this.may= sum5;
          this.june= sum6;
          this.jul= sum7;
          this.aug= sum8;
          this.sep= sum9;
          this.oct= sum10;
          this.nov= sum11;
          this.dec= sum12;
        })
        .catch(err => {
          console.log(err);
        });
            },
            searchprod(){
               this.$http
          .get('/api/store/account/sales',
          {params: {product:this.searchValue,start:this.startsearch, end:this.endsearch}},{
          withCredentials:true
          })
        .then(res => {
          console.log(res);
          this.prodTotal = res.data.body
          })
        .catch(err => {
          console.log(err);
        });
            }
        
    }
}
</script>

<style>

</style>