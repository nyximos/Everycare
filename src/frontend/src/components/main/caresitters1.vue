<template>
    <div class="content">
        <div>
  <div class="search">
      <div class="text01" id="area_text">
        <span class="exam01" @click="search01">지역을 선택하세요.</span>
      </div>
      <div class="text01" id="category_text">
        <span class="exam01" @click="search02">선호유형를 선택해주세요.</span>
      </div>
      <div class="text01" id="date_text">
        <span class="exam01" @click="search03">활동시간를 선택해주세요.</span>
      </div>
   </div>

    <v-dialog v-model="regiondialog" max-width="500px">
        <v-card>
          <v-card-title>
            희망지역
          </v-card-title>
          <v-card-text>
            <v-select name="sido1" id="sido1"
              v-model="hope_loc1"
              :items="select"
              label="시/도"
              item-text="name"
              item-value="value"
              @change="categoryChange($event)"
            ></v-select>
            <v-select name="sido_detail" id="sido1_detail"  
                v-model="hopeloc1_detail"
                :items="detail_area"
                label="시/군/구"
                item-text="name"
                item-value="value">
            </v-select>
          </v-card-text>
          <v-card-actions>
            <v-btn
              color="primary"
              text
              @click="regionsearch"
            >
              검색
            </v-btn>
            <v-btn
              color="primary"
              text
              @click="dialog"
            >
              취소
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>

   <v-dialog v-model="timedialog" max-width="500px" @click:outside="dialog" @keydown.esc="dialog">
         <v-card>
        <v-card-title>활동 시간</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 400px;">
          <v-radio-group
            v-model="dialogm1"
            column
          >
            <v-radio
              label="1시간"
              value="1"
            ></v-radio>
            <v-radio
              label="2시간"
              value="2"
            ></v-radio>
            <v-radio
              label="3시간"
              value="3"
            ></v-radio>
            <v-radio
              label="4시간"
              value="4"
            ></v-radio><v-radio
              label="5시간"
              value="5"
            ></v-radio>
            <v-radio
              label="6시간"
              value="6"
            ></v-radio>
            <v-radio
              label="7시간"
              value="7"
            ></v-radio>
            <v-radio
              label="8시간"
              value="8"
            ></v-radio>
            <v-radio
              label="9시간"
              value="9"
            ></v-radio>
            <v-radio
              label="10시간"
              value="10"
            ></v-radio>
            <v-radio
              label="11시간"
              value="11"
            ></v-radio>
            <v-radio
              label="12시간"
              value="12"
            ></v-radio>
          </v-radio-group>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="timesearch01"
          >
            검색
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="timedialog = false"
          >
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-dialog v-model="catedialog" max-width="500px" @click:outside="dialog" @keydown.esc="dialog">
         <v-card>
        <v-card-title>선호 유형</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 300px;">
          <v-radio-group
            v-model="dialogm2"
            column
          >
            <v-radio
              label="아동"
              value="아동"
            ></v-radio>
            <v-radio
              label="노인"
              value="노인"
            ></v-radio>
            <v-radio
              label="임산부"
              value="임산부"
            ></v-radio>
            <v-radio
              label="환자"
              value="환자"
            ></v-radio>
            <!-- <v-radio
              label="초등학생"
              value="초등학생"
            ></v-radio>
            <v-radio
              label="중학생"
              value="중학생"
            ></v-radio>
            <v-radio
              label="고등학생"
              value="고등학생"
            ></v-radio> -->
          </v-radio-group>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-btn
            color="blue darken-1"
            text
            @click="catesearch"
          >
            검색
          </v-btn>
          <v-btn
            color="blue darken-1"
            text
            @click="catedialog = false"
          >
            취소
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
        <div class="title">시터찾기</div>
        <div class="r_list">
            <div class="list_more">
                <ul class="ul01">
                    <li class="li01" v-for="(p, index) in profiles" :key="index" @click="move(p)">
                        <span class="tab01">
                            <span class="img01">
                                <img :src="'https://localhost:8086/api/images/' + p.storeFileNames[0]" alt="사진" class="vertical" />
                            </span>
                        </span>
                        <span class="tab02">
                            <span class="name"
                                >{{ p.name }}
                                <span class="age"> {{ p.birth }}</span>
                            </span>
                            <span class="area">{{ p.hopefulRegion }}</span>
                            <span class="pay">
                                시급:{{ p.hourlyWage }} 월급:{{ p.monthlyWage }}
                                <span class="bar0101">&nbsp;</span>
                                <span class="week01">{{ p.desiredDayWeek }}</span>
                            </span>
                        </span>
                        <div class="bar01"></div>
                        <span class="tab03">
                            <span class="icon03">{{ isVaccinated }}</span>
                            <span class="text01">{{ p.gender }}</span>
                            <span class="text01">{{ p.preferredType }}</span>
                            <span class="text01">{{ p.activityTime }}시간</span>
                        </span>
                    </li>
                </ul>
            </div>
        </div>
        <!-- <div class="btn-cover">
          <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
            <span class="page-count">{{pageNum + 1 }} / {{pageCount}} 페이지</span>
          <button :disabled="pageNum >= pageCount -1" @click="nextPage" class="page-btn">다음</button> -->
            <!-- <v-pagination v-model="curpage" :length="pages" circle></v-pagination> -->
        <!-- </div> -->
    </div>
</template>
<script>
export default {
    data() {
        return {
          profiles: [],
            img: this.img,
            isVaccinated:this.isVaccinated,
            regiondialog:false,
            timedialog:false,
            catedialog:false,
            dialogm1:'',
            dialogm2:'',
            hope_loc1:'', 
            detail_area:[],
            hopeloc1_detail:'',
            time:[],
            curpage: 1,
            pages:3,
            pageNum:0,
            pageSize:{
              type:Number,
              required:false,
              default:10
            },

      select: [
        {name: '서울', value: '서울특별시'},
        {name: '인천', value: '인천광역시'},
        {name: '경기', value: '경기'},
        {name: '부산', value: '부산광역시'},
        {name: '대구', value: '대구광역시'},
        {name: '대전', value: '대전'},
        {name: '세종', value: '세종'},
        {name: '광주', value: '광주'},
        {name: '울산', value: '울산'},
        {name: '강원', value: '강원'},
        {name: '경남', value: '경남'},
        {name: '경북', value: '경북'},
        {name: '전남', value: '전남'},
        {name: '전북', value: '전북'},
        {name: '충남', value:'충남'},
        {name: '충북', value:'충북'},
        {name: '제주', value: '제주'},
        ],
        area1 : [
      {name:'강남구', value:'강남구'},
      {name:'강동구', value:'강동구'},
      {name:'강북구', value:'강북구'},
      {name:'강서구', value:'강서구'},
      {name:'관악구', value:'관악구'},
      {name:'광진구', value:'광진구'},
      {name:'구로구', value:'구로구'},
      {name:'금천구', value:'금천구'},
      {name:'노원구', value:'노원구'},
      {name:'도봉구', value:'도봉구'},
      {name:'동대문구', value:'동대문구'},
      {name:'동작구', value:'동작구'},
      {name:'마포구', value:'마포구'},
      {name:'서대문구', value:'서대문구'},
      {name:'서초구', value:'서초구'},
      {name:'성동구', value:'성동구'},
      {name:'송파구', value:'송파구'},
      {name:'양천구', value:'양천구'},
      {name:'영등포구', value:'영등포구'},
      {name:'용산구', value:'용산구'},
      {name:'은평구', value:'은평구'},
      {name:'종로구', value:'종로구'},
      {name:'중구', value:'중구'},
      {name:'중랑구', value:'중랑구'},
    ],
    area2:[
      {name:'중구', value:'중구'},
      {name:'계양구', value:'계양구'},
      {name:'서구', value:'서구'},
      {name:'남동구', value:'남동구'},
      {name:'옹진구', value:'옹진구'},
      {name:'동구', value:'동구'},
      {name:'연수구', value:'연수구'},
      {name:'부평구', value:'부평구'},
      {name:'강화구', value:'강화구'},
      {name:'미추홀구', value:'미추홀구'}
    ],
    area3:[
      {name:'가평군', value:'가평군'},
      {name:'연천군', value:'연천군'},
      {name:'동두천시', value:'동두천시'},
      {name:'안산시', value:'안산시'},
      {name:'시흥시', value:'시흥시'},
      {name:'광주시', value:'광주시'},
      {name:'오산시', value:'오산시'},
      {name:'양주시', value:'양주시'},
      {name:'포천시', value:'포천시'},
      {name:'용인시', value:'용인시'},
      {name:'파주시', value:'파주시'},
      {name:'구리시', value:'구리시'},
      {name:'이천시', value:'이천시'},
      {name:'의정부시', value:'의정부시'},
      {name:'안양시', value:'안양시'},
      {name:'수원시', value:'수원시'},
      {name:'성남시', value:'성남시'},
      {name:'양평시', value:'양평시'},
      {name:'하남시', value:'하남시'},
      {name:'부천시', value:'부천시'},
      {name:'의왕시', value:'의왕시'},
      {name:'평택시', value:'평택시'},
      {name:'군포시', value:'군포시'},
      {name:'화성시', value:'화성시'},
      {name:'남양주시', value:'남양주시'},
      {name:'광명시', value:'광명시'},
      {name:'고양시', value:'고양시'},
      {name:'여주시', value:'여주시'},
      {name:'안성시', value:'안성시'},
      {name:'과천시', value:'과천시'},
      {name:'김포시', value:'김포시'},
    ],
    area4:[
      {name:'수영구', value:'수영구'},
      {name:'해운대구', value:'해운대구'},
      {name:'중구', value:'중구'},
      {name:'동구', value:'동구'},
      {name:'남구', value:'남구'},
      {name:'연제구', value:'연제구'},
      {name:'금정구', value:'금정구'},
      {name:'부산진구', value:'부산진구'},
      {name:'북구', value:'북구'},
      {name:'사하구', value:'사하구'},
      {name:'동래구', value:'동래구'},
      {name:'강서구', value:'강서구'},
      {name:'서구', value:'서구'},
      {name:'사상구', value:'사상구'},
      {name:'영도구', value:'영도구'},
      {name:'기장군', value:'기장군'},
    ],
    area5:[
      {name:'동구', value:'동구'},
      {name:'남구', value:'남구'},
      {name:'수성구', value:'수성구'},
      {name:'중구', value:'중구'},
      {name:'서구', value:'서구'},
      {name:'북구', value:'북구'},
      {name:'달성군', value:'달성군'},
      {name:'달서구', value:'달서구'},
    ],
    area6:[
      {name:'대덕구', value:'대덕구'},
      {name:'동구', value:'동구'},
      {name:'서구', value:'서구'},
      {name:'유성구', value:'유성구'},
      {name:'중구', value:'중구'},
    ],
    area7:[
      {name:'가람동', value:'가람동'},
      {name:'갈산리', value:'갈산리'},
    ],
    area8:[
      {name:'광산구', value:'광산구'},
      {name:'남구', value:'남구'},
      {name:'동구', value:'동구'},
      {name:'북구', value:'북구'},
      {name:'서구', value:'서구'},
    ],
    area9:[
      {name:'남구', value:'남구'},
      {name:'동구', value:'동구'},
      {name:'북구', value:'북구'},
      {name:'울주구', value:'울주군'},
      {name:'중구', value:'중구'},
    ],
    area10:[
      {name:'강릉시', value:'수영구'},
      {name:'고성군', value:'고성군'},
      {name:'동해시', value:'동해시'},
      {name:'삼척시', value:'삼척시'},
      {name:'속초시', value:'속초시'},
      {name:'양구군', value:'양구군'},
      {name:'양양군', value:'양양군'},
      {name:'영월군', value:'영월군'},
      {name:'원주시', value:'원주시'},
      {name:'인제군', value:'인제군'},
      {name:'정선군', value:'정선군'},
      {name:'철원군', value:'철원군'},
      {name:'춘천시', value:'춘천시'},
      {name:'태백시', value:'태백시'},
      {name:'평창군', value:'평창군'},
      {name:'홍천군', value:'홍천군'},
      {name:'화천군', value:'화천군'},
      {name:'횡성군', value:'횡성군'},
    ],
    area11:[
      {name:'진주시', value:'진주시'},
      {name:'고성군', value:'고성군'},
      {name:'김해시', value:'김해시'},
      {name:'밀양시', value:'밀양시'},
      {name:'산청군', value:'산청군'},
      {name:'남해군', value:'남해군'},
      {name:'의령군', value:'의령군'},
      {name:'창녕군', value:'창녕군'},
      {name:'양산시', value:'양산시'},
      {name:'거제시', value:'거제시'},
      {name:'창원시', value:'창원시'},
      {name:'통영시', value:'통영시'},
      {name:'거창군', value:'거창군'},
      {name:'사천시', value:'사천시'},
      {name:'함양군', value:'함양군'},
      {name:'하동군', value:'하동군'},
      {name:'함안군', value:'함안군'},
      {name:'합천군', value:'합천군'},
    ],
    area12:[
      {name:'안동시', value:'안동시'},
      {name:'김천시', value:'김천시'},
      {name:'경주시', value:'경주시'},
      {name:'울진군', value:'울진군'},
      {name:'포항시', value:'포항시'},
      {name:'군위군', value:'군위군'},
      {name:'경산시', value:'경산시'},
      {name:'구미시', value:'구미시'},
      {name:'영양군', value:'영야군'},
      {name:'영천시', value:'영천시'},
      {name:'성주군', value:'성주군'},
      {name:'영주시', value:'영주시'},
      {name:'상주시', value:'상주시'},
      {name:'청도군', value:'청도군'},
      {name:'울릉군', value:'울릉군'},
      {name:'봉화군', value:'봉화군'},
      {name:'예천군', value:'예천군'},
      {name:'의성군', value:'의성군'},
      {name:'문경시', value:'문경시'},
      {name:'청송군', value:'청송군'},
      {name:'영덕군', value:'영덕군'},
      {name:'칠곡군', value:'칠곡군'},
      {name:'고령군', value:'고령군'},
    ],
    area13:[
      {name:'영암군', value:'영암구'},
      {name:'영광군', value:'영광구'},
      {name:'완도군', value:'완도군'},
      {name:'화순군', value:'화순군'},
      {name:'담양군', value:'담양구'},
      {name:'구례군', value:'구례구'},
      {name:'신안군', value:'신안구'},
      {name:'장흥군', value:'장흥군'},
      {name:'광양시', value:'광양시'},
      {name:'해남군', value:'해남군'},
      {name:'무안군', value:'무안군'},
      {name:'함평군', value:'함평군'},
      {name:'장성군', value:'장성군'},
      {name:'보성군', value:'보성군'},
      {name:'나주시', value:'나주시'},
      {name:'순천시', value:'순천시'},
      {name:'여수시', value:'여수시'},
      {name:'곡성군', value:'곡성군'},
      {name:'목포시', value:'목포시'},
      {name:'진도군', value:'진도군'},
      {name:'강진군', value:'강진군'},
      {name:'고흥군', value:'고흥군'},
    ],
    area14:[
      {name:'군산시', value:'군산시'},
      {name:'임실군', value:'임실군'},
      {name:'순창군', value:'순창군'},
      {name:'완주군', value:'완주군'},
      {name:'무주군', value:'무주군'},
      {name:'고창군', value:'고창군'},
      {name:'김제시', value:'김제시'},
      {name:'남원시', value:'남원시'},
      {name:'장수군', value:'장수군'},
      {name:'익산시', value:'익산시'},
      {name:'진안군', value:'진안군'},
      {name:'정읍시', value:'정읍시'},
      {name:'전주시', value:'전주시'},
      {name:'부안군', value:'부안군'},
    ],
    area15:[
      {name:'천안시', value:'천안시'},
      {name:'보령시', value:'보령시'},
      {name:'예산군', value:'예산군'},
      {name:'청양군', value:'청양군'},
      {name:'서산시', value:'서산시'},
      {name:'서천군', value:'서천군'},
      {name:'논산시', value:'논산시'},
      {name:'당진시', value:'당진시'},
      {name:'금산군', value:'금산군'},
      {name:'태안군', value:'태안군'},
      {name:'홍성군', value:'홍성군'},
      {name:'공주시', value:'공주시'},
      {name:'계롱시', value:'계롱시'},
      {name:'아산시', value:'아산시'},
      {name:'부여군', value:'부여군'},
    ],
    area16:[
      {name:'보은군', value:'보은군'},
      {name:'영동군', value:'영동군'},
      {name:'증평군', value:'증평군'},
      {name:'괴산군', value:'괴산군'},
      {name:'충주시', value:'충주시'},
      {name:'청주시', value:'청주시'},
      {name:'제천시', value:'제천시'},
      {name:'옥천군', value:'옥천군'},
      {name:'진천군', value:'진천군'},
      {name:'음성군', value:'음성군'},
      {name:'단양군', value:'단양군'},
    ],
    area17:[
      {name:'서귀포시', value:'서귀포시'},
      {name:'제주시', value:'제주시'},
    ],
        };
    },
    methods: {
        move(p) {
            const userData = {
                id: p.id,
            };
            this.$router.push({ name: 'addprofile2', params: { caresitterId: p.id } });
            this.$store.commit('careprofileStore/move', userData);
            console.log(userData);
        },
        search01(){
     this.regiondialog = true
   },
    search02(){
     this.catedialog = true
   },
   search03(){
     this.timedialog = true
   },
   dialog(){
     this.regiondialog = false
     this.catedialog = false
     this.timedialog = false
   },
    regionsearch(){
    const region = this.hope_loc1
    const region1 = this.hopeloc1_detail
    const region2 = region +" "+region1

      this.$http
      .get(`/api/caresitters/region?region=${region2}`,{
        withCredentials:true
      }).then((res)=>{
        console.log(res)
        this.profiles = res.data.body
        this.regiondialog = false
      }).catch((err)=>{
        console.log(err)
      })
    },
     catesearch(){
       this.$http
       .get(`/api/caresitters/caretype?caretype=${this.dialogm2}`,{
         withCredentials:true
       }).then((res)=>{
         console.log(res)
         this.profiles = res.data.body
         this.catedialog = false
       }).catch((err)=>{
         console.log(err)
       })
     },
   timesearch01(){
     this.$http
     .get(`/api/caresitters/time?time=${this.dialogm1}`,{
       withCredentials:true
     }).then((res)=>{
       console.log(res)
       this.profiles = res.data.body
       this.timedialog = false
     }).catch((err)=>{
       console.log(err)
     })
   },
    categoryChange(event){
        if(event =='서울'){
          this.detail_area = this.area1;
        }else if(event == '인천'){
          this.detail_area = this.area2;
        }else if(event =='경기'){
          this.detail_area = this.area3;
        }else if(event =='부산'){
          this.detail_area = this.area4
        }else if(event =='대구광역시'){
          this.detail_area = this.area5
        }else if(event =='대전'){
          this.detail_area = this.area6
        }else if(event =='세종'){
          this.detail_area = this.area7
        }else if(event =='광주'){
          this.detail_area = this.area8
        }else if(event =='울산'){
          this.detail_area = this.area9
        }else if(event =='강원'){
          this.detail_area = this.area10
        }else if(event =='경남'){
          this.detail_area = this.area11
        }else if(event =='경북'){
          this.detail_area = this.area12
        }else if(event =='전남'){
          this.detail_area = this.area13
        }else if(event =='전북'){
          this.detail_area = this.area14
        }else if(event =='충남'){
          this.detail_area = this.area15
        }else if(event =='충북'){
          this.detail_area = this.area16
        }else if(event =='제주'){
          this.detail_area = this.area17
        }
      },
      // nextPage(){
      //   this.pageNum += 1;
      // },
      // prevPage(){
      //   this.pageNum -= 1;
      // }
    },
    mounted() {
        this.$http
            .get('/api/caresitters', {
                withCredentials: true,
            })
            .then((res) => {
                console.log(res.data.body);
                this.profiles = res.data.body;
                if(res.data.body.isVaccinated == 0){
                  this.isVaccinated = "1차접종"
                }else if(res.data.body.isVaccinated == 1){
                  this.isVaccinated = "추가접종"
                }else{
                  this.isVaccinated = "미접종"
                }
                
            })
            .catch((err) => {
                console.log(err);
            });
    },
    computed:{
      pageCount(){
        let listLeng = this.profiles.length,
            listSize = this.pageSize,
            page = Math.floor(listLeng / listSize);

        if (listLeng % listSize > 0) page += 1;

        return page;
      },
      // profiles(){
      //   const start = this.pageNum * this.pageSize,
      //         end = start + this.pageSize;

      //   return this.profiles.slice(start,end);
      // }
    }
}
</script>
<style>
ul{
  list-style: none;
}
.title {
    width: 100%;
    display: inline-block;
    font-size: 1.2em;
    padding: 0 0 10px 0;
}
.r_list {
    width: 100%;
    max-width: 100%;
    padding: 0;
    box-sizing: border-box;
    position: relative;
}
.content .list .div_list .r_list .list_more {
    width: 100%;
    max-width: 100%;
    padding: 0;
    text-align: center;
    box-sizing: border-box;
    position: relative;
}
.r_list .list_more .ul01 {
    width: 100%;
    max-width: 100%;
    padding: 0;
    box-sizing: border-box;
    position: relative;
}
.r_list .list_more .ul01 .li01 {
    width: 100%;
    max-width: 100%;
    padding: 20px;
    box-sizing: border-box;
    position: relative;
    margin: 0 0 20px 0;
    border: 0;
    border-radius: 5px;
    line-height: 1.3em;
    background-color: #ffffff;
    text-align: left;
}
.r_list .list_more .ul01 .li01 .tab01 {
    /* float: left; */
    width: 100px;
    display: inline-block;
    position: relative;
    box-sizing: border-box;
}
.r_list .list_more .ul01 .li01 .tab01 .img01 {
    position: relative;
    width: 60px;
    height: 60px;
    border-radius: 60px;
    overflow: hidden;
    box-sizing: border-box;
    float: left;
    background-color: #fafafa;
    border: 1px solid #efefef;
    margin: 5px 30px 0 10px;
}
.img01 .vertical {
    width: 105%;
    height: auto;
}
.ul01 .li01 .tab02 {
    /* float: left; */
    width: calc(100% - 100px);
    display: inline-block;
    position: relative;
    letter-spacing: -0.03em;
    line-height: 1em;
    font-size: 1em;
}
.ul01 .li01 .tab02 .category {
    width: 100%;
    display: inline-block;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    font-size: 0.9em;
    font-weight: 400;
    box-sizing: border-box;
    color: black;
}
.ul01 .li01 .tab02 .edit_date {
    display: inline-block;
    margin: 0 0 0 10px;
    font-size: 0.9em;
    color: #373737;
    font-weight: 300;
    box-sizing: border-box;
}
.tab02 .name {
    display: inline-block;
    font-size: 1em;
    box-sizing: border-box;
    color: #000000;
    font-weight: 400;
    padding: 7px 0 0 0;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.tab02 .name .age {
    font-size: 0.9em;
    box-sizing: border-box;
    color: #797979;
    font-weight: 300;
    padding: 0 0 0 0;
}
.tab02 .bar_star {
    display: inline-block;
    position: relative;
    width: 76px;
    height: 14.4px;
    box-sizing: border-box;
    background-color: #dedede;
    margin: 0 0 0 5px;
    vertical-align: top;
}
.bar_star .bg_star {
    position: absolute;
    left: 0;
    top: 0;
    display: inline-block;
    height: 14.4px;
    background-color: #f8b400;
}
.tab02 .area {
    width: 100%;
    display: inline-block;
    font-size: 0.85em;
    font-weight: 300;
    box-sizing: border-box;
    color: #797979;
    padding: 5px 0 0 0;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.ul01 .li01 .tab02 .pay {
    width: 100%;
    display: inline-block;
    font-size: 0.8em;
    font-weight: 300;
    box-sizing: border-box;
    color: #000000;
    padding: 5px 0 0 0;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.ul01 .li01 .tab02 .bar0101 {
    display: inline-block;
    width: 1px;
    margin: 2px 3px 0 3px;
    background-color: #dedede;
    line-height: 0.9em;
}
.ul01 .li01 .bar01 {
    float: left;
    width: 100%;
    clear: both;
    overflow: hidden;
    height: 0;
    border-top: 1px solid #eaeaea;
    margin: 15px 0 15px 0;
}
.ul01 .li01 .tab03 {
    width: 100%;
    display: inline-block;
    position: relative;
    box-sizing: border-box;
    font-size: 0.75em;
    color: #797979;
    padding: 0 0 0 5px;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
}
.ul01 .li01 .tab03 .icon03 {
    display: inline-block;
    background-color:  #ffff93;
    padding: 1px 10px 1px 10px;
    border-radius: 20px;
    color: green;
    margin: 0 6px 0 0;
    font-weight: 400;
}
.ul01 .li01 .tab03 .text01 {
    display: inline-block;
    background-color: #f4f4f4;
    padding: 1px 5px 1px 5px;
    color: #333333;
    margin: 0 5px 0 0;
    font-weight: 300;
}
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
</style>
