<template>
 <div>
    <div class="text-end mt-4">
        <v-btn @click="gocreate">
          <v-icon left>
            mdi-pencil
          </v-icon>
            Create
        </v-btn>
    </div>
    <div class="header">
        <v-row>
        <h1>구인리스트</h1>
        </v-row>
    </div>
    <br>
 <div class="search">
    <form name="search_form" method="">
      <div class="text01" id="area_text">
        <button class="addressbutton" @click="search">🔍</button>
        <input type="text" v-model="address" name="address" @click="execDaumPostcode()" placeholder = "지역을 선택하세요." readonly />
        
      </div>
      <div class="text01" id="category_text">
        <span class="exam01">카테고리를 선택해주세요.</span>
      </div>
      <div class="text01" id="date_text">
        <span class="exam01">날짜를 선택해주세요.</span>
      </div>
   
    </form>
  </div>
  <v-text-field
    v-model="SearchText"
    placeholder="Search"
    filled
    rounded
    dense
  ></v-text-field>
   <div v-if="!listData.length">글이 없습니다</div>
  <div v-if="!filteredList.length && listData.length">
    검색결과가 없습니다
  </div>
  <!-- {{this.listData}} -->
  <ListItem class="mt-5" v-for="(listItem, index) in filteredList" :key="index"
  :listItem="listItem" @detail="detailShot"
  />
  <div class="text-center">
    <v-pagination
      class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
      circle
    ></v-pagination>
  </div>
 </div>
</template>

<script>
import ListItem from '@/components/listItem'
export default {
    name: 'componentjoblist',
    components:{
      ListItem
    },
    mounted() {
        this.$http
        .get('/api/recruitions', {
        withCredentials: true
        })
        .then(res => {
          console.log(res.data)
          this.listData = res.data.body;
          this.id = res.data.body.id;
          console.log(res)
          console.log(res.body.body[0].careTarget.address)
          
          
        })
          .catch(err => {
          console.log(err);
        });
        
    },
    data(){
        return{
          listData:[],
          dataPerPage:3,
          curPageNum:1,
          SearchText: '',
          address:'',
          detailedAddress:'',
          
        }
    },
    methods:{
      search(){
       this.$route.params({ params: { region:res.body.body[i].careTarget.address}})
        this.$http.get('/api/recruitions/region',{
        withCredentials: true  
        })
        .then((res)=> {
          console.log(res)
        }).catch((err)=>{
          console.log(err)
        })
        console.log(region)
      },
        gocreate(){
          this.$router.push({
            path: '/recruitions/new'
          })
        },
        detailShot(id){              
          this.$router.push({
            name: 'detail', 
            params: {
              contentId: id
            }
          })
          
        },
        execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } 
 
          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          
        },
      }).open();
    },
        
    },
    computed: {
      startOffset() {
        return ((this.curPageNum - 1) * this.dataPerPage);
      },
      endOffset() {
        return (this.startOffset + this.dataPerPage);
      },   
      numOfPages() {
        return Math.ceil(this.listData.length / this.dataPerPage);
      },
      calData() {
        return this.listData.slice(this.startOffset, this.endOffset)
      },
      filteredList(){
        if (this.SearchText) {
          return this.listData.filter(listItem => {
            return listItem.title.includes(this.SearchText);
          });
        }else{
          return this.calData
        }
      }
    }
}
</script>

<style>
.search{
  position: relative;
  display: inline-block;
  width: 100%;
  border: 1px solid #dedede;
  border-radius: 5px;
  background-color: #ffffff;
  margin: 0 0 20px 0;
}
.search form{
  margin: 0;
  padding: 0;
  border: 0;
  outline: 0;
  font-size: 100%;
}
.search #area_text{
  border-bottom: 1px solid #eaeaea;
}
.search .text01{
  padding: 10px 20px 10px 20px;
  font-size: 1.2em;
  letter-spacing: 0;
  font-weight: 300;
}
.search #category_text{
  border-bottom: 1px solid #eaeaea;
}
input{
  width: 70%;
}
</style>