<template>

<div class="content">
  
	<div class="list">
		<div class="div_list">

	<div class="search">
		

				<div class="text01" id="area_text"><span class="exam01" @click="search01">지역을 선택하시려면 클릭해주세요.</span></div>
						<div class="text01" id="sphere_text"><span class="exam01" @click="search02" >희망날짜를 선택하시려면 클릭해주세요.</span></div>
			
		<div class="layer01" id="search_layer">
		</div>
	</div>
	<div class="tit">일자리찾기
		<span class="sort01">
		<v-btn @click="gocreate">
          <v-icon left>
            mdi-pencil
          </v-icon>
            Create
        </v-btn>
		</span>
	</div>
	<div class="rlist">
		<div class="list_more list_more1"><ul class="ul01"><li class="li01" v-for="(p, index) in listData" :key="index" >
<!-- <a href="/recruit/view.php?idx=21557"> -->
	<span class="tab01">
		<span class="img01"><img src="" class=""></span>
		<span class="text01">{{p.careTargetSchedule.name}}</span>
	</span>
	<span class="tab02">
		
		<span class="sphere"> 베이비시터<span class="edit_date">35분전</span></span>
		<span class="subject">하루 야간 아기봐주실분</span>
		<!-- <span class="area">경기 부천시 원종동</span> -->
		<span class="pay"><img src="/images/common/icon_won.png" class="icon_won">시급 13,000 <span class="bar0101">&nbsp;</span> <span class="working_time">19:00 ~ 07:00</span></span>
	</span>
	<div class="bar01"></div>
	<span class="tab03">
		<!-- <span class="text01">단기근무</span>
		
		<span class="text01">6월 25일부터</span>
		<span class="text01">한국인</span> -->
		<span class="text01">CCTV설치</span>
		<span class="text01">반려동물있음</span>
		<!-- <span class="text01">면접비지급하지않음</span>
		<span class="text01">보험무관</span> -->
	</span>
  <!-- </a> -->

</li></ul></div>
		<div class="list_none" style="display: none;">현재 선택하신 조건의 정보가 없습니다.<br>다시 검색해주세요!</div>
		<div class="loading" style="display: none;"><img src="/images/common/icon_loading.gif"></div>
	</div>

	
	
</div>

	</div>
</div>

    <!-- <div class="text-end mt-4">
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
    </div> -->
    <!-- <div class="search">
      <div class="text01" id="area_text">
        <span class="exam01" @click="search01">지역을 선택하세요.</span>
      </div>
      <div class="text01" id="category_text">
        <span class="exam01" @click="search02">희망날짜를 선택해주세요.</span>
      </div>
      <div class="text01" id="date_text">
        <span class="exam01" @click="search03">활동시간를 선택해주세요.</span>
      </div>
   </div> -->

    <!-- <v-dialog v-model="regiondialog" max-width="500px">
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
      </v-dialog> -->

   <!-- <v-dialog v-model="timedialog" max-width="500px" @click:outside="dialog" @keydown.esc="dialog">
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
    </v-dialog> -->
    <!-- <v-dialog v-model="catedialog" max-width="1000px" @click:outside="dialog" @keydown.esc="dialog">
         <v-card>
        <v-card-title>선호 유형</v-card-title>
        <v-divider></v-divider>
        <v-card-text style="height: 600px;">
        <v-row>
    <v-col
      cols="12"
      sm="6"
    >
      <v-date-picker
        v-model="dialogm2"
        range
      ></v-date-picker>
    </v-col>
    <v-col
      cols="12"
      sm="6"
    >

    </v-col>
  </v-row>

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
    </v-dialog> -->

  <!-- <v-text-field
    v-model="SearchText"
    placeholder="Search"
    filled
    rounded
    dense
  ></v-text-field>
   <div v-if="!listData.length">글이 없습니다</div>
  <div v-if="!filteredList.length && listData.length">
    검색결과가 없습니다
  </div> -->
  <!-- <div class="title">시터찾기</div>
        <div class="r_list">
            <div class="list_more">
                <ul class="ul01">
                    <li class="li01" v-for="(p, index) in listData" :key="index" >
                        <span class="tab01">
                            <span class="img01">
                              
                            </span>
                        </span>
                        <span class="tab02">
                            <span class="name"
                                >{{ p.title }}
                                <span class="age"></span>
                            </span>
                            <span class="area">{{ p.careTarget.address }}</span>
                            <span class="pay">
                                시작일:{{ p.startDate }} 종료일:{{ p.endDate }}
                                <span class="bar0101">&nbsp;</span>
                                <span class="week01">희망날짜: {{ p.desiredDayWeek }}</span>
                            </span>
                        </span>
                        <v-btn @click="detail">상세보기</v-btn>
                        <div class="bar01"></div>
                        
                    </li>
                </ul>
            </div>
        </div> -->
        <!-- <div class="btn-cover">
          <button :disabled="pageNum === 0" @click="prevPage" class="page-btn">이전</button>
            <span class="page-count">{{pageNum + 1 }} / {{pageCount}} 페이지</span>
          <button :disabled="pageNum >= pageCount -1" @click="nextPage" class="page-btn">다음</button>
            < <v-pagination v-model="curpage" :length="pages" circle></v-pagination> -->
        <!-- </div>  -->
  <!-- {{this.listData}} -->
  <!-- <ListItem class="mt-5" v-for="(listItem, index) in filteredList" :key="index"
  :listItem="listItem" @detail="detailShot"
  /> -->
  <!-- <div class="text-center">
    <v-pagination
      class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
      circle
    ></v-pagination>
  </div> -->
 
</template>

<script>
// import ListItem from '@/components/listItem'
export default {
    name: 'componentjoblist',
    // components:{
    //   ListItem
    // },
    mounted() {
        this.$http
        .get('/api/recruitions', {
        withCredentials: true
        })
        .then(res => {
          console.log(res.data)
          this.listData = res.data.body;
          this.id = res.data.body.id;
          console.log(this.listData)
          console.log(res)
          
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
          // SearchText: '',
          pageNum:0,
            regiondialog:false,
            timedialog:false,
            catedialog:false,
            dialogm1:'',
            dialogm2:'',
            hope_loc1:'', 
            detail_area:[],
            hopeloc1_detail:'',
            time:[],
            dates:[],
            
            
       select: [
        {name: '서울', value: '서울'},
        {name: '인천', value: '인천'},
        {name: '경기', value: '경기'},
        {name: '부산', value: '부산'},
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
        }
    },
    methods:{
      detail() {
            
            this.$emit('detail', this.listItem.id);
            
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
      .get(`/api/recruitions/region?region=${region2}`,{
        withCredentials:true
      }).then((res)=>{
        console.log(res)
        this.listData = res.data.body
        console.log(res.data.body)
        this.regiondialog = false
      }).catch((err)=>{
        console.log(err)
      })
    },
     catesearch(){
       this.$http
       .get(`/api/recruitions/date?date=${this.dialogm2}`,{
         withCredentials:true
       }).then((res)=>{
         console.log(res)
         this.listData = res.data.body
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
       this.listData = res.data.body
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
      nextPage(){
        this.pageNum += 1;
      },
      prevPage(){
        this.pageNum -= 1;
      }
    },
    computed: {
       pageCount(){
        let listLeng = this.listData.length,
            listSize = this.pageSize,
            page = Math.floor(listLeng / listSize);

        if (listLeng % listSize > 0) page += 1;

        return page;
      },
      
      // startOffset() {
      //   return ((this.curPageNum - 1) * this.dataPerPage);
      // },
      // endOffset() {
      //   return (this.startOffset + this.dataPerPage);
      // },   
      // numOfPages() {
      //   return Math.ceil(this.listData.length / this.dataPerPage);
      // },
      // calData() {
      //   return this.listData.slice(this.startOffset, this.endOffset)
      // },
      
      // filteredList(){
      //   if (this.SearchText) {
      //     return this.listData.filter(listItem => {
      //       return listItem.title.includes(this.SearchText);
      //     });
      //   }else{
      //     return this.calData
      //   }
      // }
    }
}
</script>

<style>
.content { width:100%; position:relative; }
.content .list { width:100%; max-width:670px; min-height:800px; background-color:#FFFFFF; position:relative; margin:0 auto; border-left:1px solid #DEDEDE; border-right:1px solid #DEDEDE; }
.content .list .div_list { width:100%; max-width:100%; min-height:800px; padding:30px 15px 30px 15px; box-sizing:border-box; position:relative; background-color:#F4F4F4; }
.content .list .div_list .tit { width:100%; display:inline-block; font-size:1.2em; padding:0 0 10px 0; }
.content .list .div_list .tit .sort01 { position:absolute; right:15px; margin:-6px 0 0 0; }
.content .list .div_list .tit .sort01 .psort { padding:5px; font-size:0.7em; border:1px solid #DEDEDE; border-radius:3px; }
.content .list .div_list .rlist { width:100%; max-width:100%; padding:0; box-sizing:border-box; position:relative; }
.content .list .div_list .rlist .list_more { width:100%; max-width:100%; padding:0; text-align:center; box-sizing:border-box; position:relative; }
.content .list .div_list .rlist .list_more .ul01 { width:100%; max-width:100%; padding:0; box-sizing:border-box; position:relative; }
.content .list .div_list .rlist .list_more .ul01 .li01 { width:100%; max-width:100%; padding:20px; box-sizing:border-box; position:relative; margin:0 0 20px 0; border:0; border-radius:5px; line-height:1.3em; background-color:#FFFFFF; text-align:left; overflow:hidden; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 { float:left; width:100px;  display:inline-block; position:relative; box-sizing:border-box;  }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 .img01 { position:relative; width:60px; height:60px; border-radius:60px; overflow:hidden; box-sizing:border-box; float:left; background-color:#FAFAFA; border:1px solid #EFEFEF; margin:5px 30px 0 10px;  }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 .img01 img { position: absolute; left: 50%; top: 50%; height: 100%; width: auto; -webkit-transform: translate(-50%,-50%); -ms-transform: translate(-50%,-50%); transform: translate(-50%,-50%); }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 .text01 { position:relative; display:inline-block; box-sizing:border-box; font-size:0.75em; border:0; padding:0 5px 0 5px; margin:7px 0 0 5px; text-align:center; background-color:#F4F4F4; border-radius:30px; color:#676767; width:70px; letter-spacing:0; }
					
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 .img01 .horizontal { width:auto; height:105%; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 .img01 .vertical { width:105%; height:auto; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 .img01 .auto { width:auto; height:auto; }

.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 { float:left; width:calc(100% - 100px); display:inline-block; position:relative; letter-spacing:-0.03em; line-height:1em; font-size:1em; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .sphere { width:100%; display:inline-block; font-size:0.9em; box-sizing:border-box; color:#2c786c; font-weight:400; padding:5px 0 0 0; overflow: hidden; text-overflow: ellipsis;  white-space: nowrap;  }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .sphere .option01 { display:inline-block; font-size:1em; box-sizing:border-box; background-color:#ff7979; color:#FFFFFF; font-weight:400; font-size:0.8em; padding:3px 5px 3px 5px; vertical-align:baseline; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .subject { width:100%; display: inline-block; overflow: hidden; text-overflow: ellipsis;  white-space: nowrap; font-size:0.9em; font-weight:400; box-sizing:border-box; padding:10px 0 0 0; color:#000000 }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .area { width:100%; display:inline-block; font-size:0.9em; font-weight:300; box-sizing:border-box; color:#797979; padding:5px 0 0 0; overflow: hidden; text-overflow: ellipsis;  white-space: nowrap; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .pay { width:100%; display:inline-block; font-size:0.9em; font-weight:300; box-sizing:border-box; color:#666666; padding:5px 0 0 0; overflow: hidden; text-overflow: ellipsis;  white-space: nowrap;  }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .bar0101 { display:inline-block; width:1px; margin:2px 7px 0 7px; background-color:#DEDEDE; line-height:0.9em }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .working_time { display:inline-block; font-weight:300; box-sizing:border-box; color:#666666; margin:0 0 0 0; padding:0 0 0 17px; background:url('/images/common/icon_clock.png') 0 2px no-repeat; background-size:13px; letter-spacing:0; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .edit_date { display:inline-block; margin:0 0 0 10px; font-size:0.9em; color:#9A9A9A; font-weight:300; box-sizing:border-box;}
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .icon_won { width:13px; vertical-align:bottom; margin:0 3px 1px 0; }
.content .list .div_list .rlist .list_more .ul01 .li01 .bar01 { float:left; width:100%; clear:both; overflow:hidden; height:0; border-top:1px solid #EAEAEA; margin:15px 0 15px 0;}

.content .list .div_list .rlist .list_more .ul01 .li01 .tab03 { width:100%;  display:inline-block; position:relative; box-sizing:border-box; font-size:0.75em; color:#797979; padding:0 0 0 5px;  overflow: hidden; text-overflow: ellipsis;  white-space: nowrap; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab03 .day { display:inline-block; margin:0 10px 0 0 }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab03 .day .week { display:inline-block; background-color:#F4F4F4; padding:1px 5px 1px 5px; color:#AAAAAA }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab03 .day .week.on { background-color:#f8b400; color:#FFFFFF }
.content .list .div_list .rlist .loading { width:100%; text-align:center; display:none; background-color:#FFFFFF; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab03 .text01 { display:inline-block; background-color:#F4F4F4; padding:1px 5px 1px 5px; color:#333333; margin:0 5px 0 0; font-weight:300;  }

.content .list .div_list .rlist .list_more .ul01 .li01 .follow01 { position:absolute; display:inline-block; right:20px; top:20px; z-index:4; width:30px; height:30px; background-color:#F4F4F4; border:1px solid #EFEFEF; border-radius:15px; box-sizing:border-box; }
.content .list .div_list .rlist .list_more .ul01 .li01 .follow01 .heart01 {  display:inline-block; width:24px; height:22px; background:url('/images/common/icon_heart_off2.png') no-repeat; background-size:23px; margin:4px 3px 4px 3px; box-sizing:border-box; }
.content .list .div_list .rlist .list_more .ul01 .li01 .follow01 .heart01.on { background:url('/images/common/icon_heart_on.png') no-repeat; background-size:23px; }

.content .list .div_list .rlist .list_none {  display:none; width:100%; max-width:100%; padding:80px 20px 80px 20px; box-sizing:border-box; position:relative; margin:0 0 20px 0; border:0; border-radius:5px; font-weight:300; line-height:1.5em; background-color:#FFFFFF; color:#9A9A9A; text-align:center; overflow:hidden; }


.content .list .div_list .search { position:relative; display:inline-block; width:100%; border:1px solid #DEDEDE; border-radius:5px; background-color:#FFFFFF; margin:0 0 20px 0; }
.content .list .div_list .search .text01 { padding:20px 20px 20px 40px; font-size:1.1em; letter-spacing:0; font-weight:300; }
.content .list .div_list .search #area_text { border-bottom:1px solid #EAEAEA;  }
.content .list .div_list .search #area_text:after { width:35px; height:55px; content:' '; position:absolute; left:0; top:0; display:block; background:url('/images/common/icon_search_gps.png') 20px 25px no-repeat; background-size:13px auto; opacity:0.4; }
.content .list .div_list .search #sphere_text:after { width:35px; height:55px; content:' '; position:absolute; left:0; top:55px; display:block; background:url('/images/common/icon_search_sphere.png') 20px 25px no-repeat; background-size:13px auto; opacity:0.4; }
.content .list .div_list .search .text01 .exam01 { color:#9A9A9A }
.content .list .div_list .search .text01 .text0101 { display:inline-block; color:#333333; font-weight:400; }


.content .list .div_list .search .layer01 { width:100%; position:relative; display:none; }
.content .list .div_list .search .layer01 .layer0101 { position:fixed; border-radius:10px; width:90%; max-width:640px; margin:0 auto; top:calc( 100% / 2 - 220px ); border:1px solid #DEDEDE; background-color:#FFFFFF; z-index:22; padding:20px; box-sizing:border-box; box-shadow:3px 3px 10px 2px #797979; text-align:center; }
.content .list .div_list .search .layer01 .layer0101 .text01 { display:inline-block; width:100%; text-align:center; padding:20px 0 10px 0; box-sizing:border-box;}
.content .list .div_list .search .layer01 .layer0101 .label01 { display:inline-block; width:100%; text-align:center; text-align:left; padding:10px; background-color:#F4F4F4; box-sizing:border-box; border-top:1px solid #DEDEDE; border-bottom:1px solid #DEDEDE; }
.content .list .div_list .search .layer01 .layer0101 .label01 .list01 { display:inline-block; width:100%; text-align:left; box-sizing:border-box; padding:0 0 12px 0; font-size:0.9em; }
.content .list .div_list .search .layer01 .layer0101 .text02 { background-color:#77777712; display:inline-block; margin:0 auto; text-align:center; padding:5px; margin:10px 0 20px 0; font-size:0.9em; color:#777777; }
.content .list .div_list .search .layer01 .layer0101 .text02 strong { color:red; }
.content .list .div_list .search .layer01 .layer0101 .btn0101 { border-top:1px solid #DEDEDE; padding:20px 0 0 0; display:inline-block; width:100%; text-align:center;}
.content .list .div_list .search .layer01 .layer0101 .btn0101 .btn03 { background-color:#F4F4F4; border:1px solid #DEDEDE; border-radius:5px; padding:5px 10px 5px 10px; font-weight:300; margin:0 5px 0 0; }
.content .list .div_list .search .layer01 .layer0101 .btn0101 .btn04 { background-color:#2c786c; border:1px solid #2c786c; color:#FFFFFF; border-radius:5px; padding:5px 10px 5px 10px; font-weight:500; margin:0 0 0 5px; }


.content .list .div_list .search .layer01 .layer0101 .layer_contract { width: 100%; margin: 0 0 0 0; background-color: #FFFFFF; padding: 0; overflow: hidden; letter-spacing:0; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .sido { width: 25%; box-sizing:border-box; border-left:1px solid #E3E3E3; border-bottom: 1px solid #E3E3E3; overflow-y: scroll; height: 355px; display: inline-block; float:left;  }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .gugun { width: 40%; box-sizing:border-box; border-left: 1px solid #E3E3E3; border-bottom: 1px solid #E3E3E3; overflow-y: scroll; height: 355px; display: inline-block; float:left; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .dong { width: 35%; box-sizing:border-box; border-left: 1px solid #E3E3E3; border-right: 1px solid #E3E3E3; border-bottom: 1px solid #E3E3E3; overflow-y: scroll; height: 355px; display: inline-block; float:left; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .area_check { width:100%; position:relative; overflow:hidden; border-top: 1px solid #E3E3E3; border-bottom: 1px solid #E3E3E3 }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .area_check span { display: inline-block; box-sizing:border-box; width: 25%; float:left; text-align: center; padding: 15px 0 15px 0; font-size: 15px; font-weight: 500; 

background: #F8F8F8; /* For browsers that do not support gradients */
background: -webkit-linear-gradient(bottom, #F8F8F8 , #FEFEFE); /* For Safari 5.1 to 6.0 */
background: -o-linear-gradient(top, #F8F8F8, #FEFEFE); /* For Opera 11.1 to 12.0 */
background: -moz-linear-gradient(top, #F8F8F8, #FEFEFE); /* For Firefox 3.6 to 15 */
background: linear-gradient(to top, #F8F8F8 , #FEFEFE); /* Standard syntax */
border-left: 1px solid #E3E3E3;
}
.content .list .div_list .search .layer01 .layer0101 .layer_contract .area_check span:nth-child(1) { width:25%; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .area_check span:nth-child(2) { width:40%; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .area_check span:nth-child(3) { width:35%; border-right: 1px solid #E3E3E3; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .close { position: absolute; top: 5px; right: 15px; font-size: 20px; font-weight: bold; }


.content .list .div_list .search .layer01 .layer0101 .layer_contract .sido .sido_area { display: inline-block; position:relative; width: 100%; text-align: left; box-sizing:border-box; font-size: 1em; padding: 10px 0 10px 10px; font-weight: 400; color:#888888; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sido .sido_area.on { background-color: #eaf9f7; color: #2c786c }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sido .sido_area.on:before { content:'\2714'; position:absolute; right:10px; font-size:1.3em; top:7px; color:#2c786c; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .gugun .gugun_area { display: inline-block; position:relative; width: 100%; text-align: left; box-sizing:border-box; font-size: 1em; padding: 10px 0 10px 10px; font-weight: 400; color:#888888; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .gugun .gugun_area.on { background-color: #eaf9f7; color: #2c786c }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .gugun .gugun_area.on:before { content:'\2714'; position:absolute; right:10px; font-size:1.3em; top:7px; color:#2c786c; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .dong .dong_area { display: inline-block; position:relative; width: 100%; text-align: left; box-sizing:border-box; font-size: 1em; padding: 10px 0 10px 10px; font-weight: 400; color:#888888; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .dong .dong_area.on { background-color: #eaf9f7; color: #2c786c }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .dong .dong_area.on:before { content:'\2714'; position:absolute; right:10px; font-size:1.3em; top:7px; color:#2c786c !important; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere01 { width:100%; display:block; position:relative; text-align:right; padding:0 0 10px 0; border-bottom:1px solid #EFEFEF; margin:0 0 10px 0 }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere_list { width:100%; display:block; position:relative; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere_list .r_sphere1 { position:relative; background-color:#FAFAFA; border:1px solid #ecf2f8; color:#373737; border-radius:50px; width:calc( 100% / 3 - 7px ); height:40px; margin:0 2px 15px 2px; font-size:0.9em; letter-spacing:-0.05em; box-sizing:border-box; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere_list .r_sphere1_blank { display:inline-block; width:calc( 100% / 3 - 7px  ); height:40px; border:0; background:none; margin:5px 2px 10px 2px; box-sizing:border-box; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere_list .r_sphere1.on { background-color:#eaf9f7; border:1px solid #2c786c; color:#2c786c; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere_list .r_sphere1 .check { position:absolute; right:-9px; display:none; color:#2c786c; background-color:#FFFFFF; width:24px; height:24px; border:1px solid #2c786c; border-radius:30px; padding:1px 0 0 0; margin:10px 0 0 0; box-sizing:border-box; font-weight:500; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere_list .r_sphere1.on .check { display:inline-block; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .btn01 { display:inline-block; width:100%; text-align:center }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .btn01 #btn_search_sphere { background-color: #2c786c; border: 0; padding: 10px; width: 100%; color: #FFFFFF; border-radius: 20px; font-size: 1.05em; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .sphere01 { width:100%; text-align:right; border-bottom:1px solid #EFEFEF; padding:5px 0 5px ; margin:0 0 10px 0; font-size:0.8em; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .tit01 { font-size:1.2em; font-weight:500; background-color:#F4F4F4; display:block; width:100%; padding:10px;}
.content .list .div_list .search .layer01 .layer0101 .layer_contract .tit0101 { font-size:1em; font-weight:500; text-align:left; padding:20px 10px 10px 0; color:#000000 }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 { width:100%; position:relative; }
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_day { width:100%; position:relative; padding:10px 0 10px 0; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_day .r_week { display:inline-block; width:calc(100% / 7 - 5px); max-width:40px; padding:10px 0 10px 0; margin:0 5px 0 5px; background-color:#F4F4F4; border:1px solid #DEDEDE; box-sizing:border-box; cursor:pointer; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_day .r_week.on { background-color:#2c786c; color:#FFFFFF; border:1px solid #2c786c; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_working_time { width:100%; position:relative; padding:10px 0 10px 0; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_working_time .r_time { display:inline-block; width:calc(100% / 4 - 5px); max-width:120px; padding:10px 0 10px 0; margin:0 5px 0 5px; background-color:#F4F4F4; border:1px solid #DEDEDE; box-sizing:border-box; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_working_time .r_time.on { background-color:#2c786c; color:#FFFFFF; border:1px solid #2c786c; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_paylist { width:100%; position:relative; padding:10px 0 10px 0; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_paylist .r_pay { display:inline-block; width:calc(100% / 7 - 5px); max-width:40px; padding:10px 0 10px 0; margin:0 5px 0 5px; background-color:#F4F4F4; border:1px solid #DEDEDE; box-sizing:border-box; } 
.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_paylist .r_pay.on { background-color:#2c786c; color:#FFFFFF; border:1px solid #2c786c; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .bar01 { width:100%; position:relative; border-bottom:1px solid #DEDEDE; margin:10px 0 10px 0; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .btn01 #btn_search_detail { background-color: #2c786c; border: 0; padding: 10px; width: 100%; color: #FFFFFF; border-radius: 20px; font-size: 1.05em; margin:10px 0 0 0; }

.content .list .div_list .bottom01 { width:100%; position:relative; text-align:center; }
.content .list .div_list .bottom01 .btn01 { position:fixed; bottom:20px; z-index:5; left:0; right:0; padding:0 10px 0 0; }
.content .list .div_list .bottom01 .btn01 .btn0101 { display:inline-block; padding:14px 45px 14px 60px; box-sizing:border-box; border-radius:30px; border:1px solid #DEDEDE; background:url('/images/common/icon_search_glass.png') 17px 15px no-repeat; background-size:18px; color:#FFFFFF; background-color:#2c786c; font-size:1.2em; }

.content .list .div_list .bottom01 .btn02 { position:fixed; bottom:20px; z-index:5; right:0; padding:0 10px 0 0; }
.content .list .div_list .bottom01 .btn02 .btn0201 { display:inline-block; padding:15px 20px 15px 20px; box-sizing:border-box; border-radius:30px; border:0; background:url('/images/common/icon_arrow_top_gray.png') 7px 7px no-repeat; background-size:30px; color:#2c786c; background-color:#F4F4F4; border:1px solid #DEDEDE;}

#slider_pay { width:90%; margin:15px auto 40px auto; }

@media (max-width:500px) {
.content .list .div_list .rlist .list_more .ul01 .li01 { font-size:0.9em; padding:10px; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab01 { width:80px; }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 { line-height:1.1em;  }
.content .list .div_list .search .text01 { font-size:1em; }

.content .list .div_list .search .layer01 .layer0101 .layer_contract .con01 .r_day .r_week { margin:0; }

.content .list .div_list .rlist .list_more .ul01 .li01 .follow01 { right:10px; top:10px; }

#slider_pay { width:85%; margin:15px auto 40px auto; }

	.content .list .div_list .bottom01 .btn01 .btn0101 { display:inline-block; padding:10px 35px 10px 45px; box-sizing:border-box; border-radius:30px; border:1px solid #DEDEDE; background:url('/images/common/icon_search_glass.png') 10px 12px no-repeat; background-size:13px; color:#FFFFFF; background-color:#2c786c; font-size:1.1em; }
	.content .list .div_list .bottom01 .btn02 .btn0201 { display:inline-block; padding:10px 15px 10px 15px; box-sizing:border-box; border-radius:30px; border:0; background:url('/images/common/icon_arrow_top_gray.png') 6px 7px no-repeat; background-size:22px; color:#2c786c; background-color:#F4F4F4; border:1px solid #DEDEDE;}
}

@media (max-width:320px) {
.content .list .div_list .search  { font-size:0.8em; }
.content .list .div_list .rlist .list_more .ul01 .li01 { font-size:0.9em; line-height:1.5em }
.content .list .div_list .rlist .list_more .ul01 .li01 .tab02 .sphere { width:90%; }
}
.content { width:100%; background-image: -webkit-gradient(linear,left top,left bottom,from(#fff),color-stop(60.62%,#e9f1f8),to(#f2f5f7)); background-image: -webkit-linear-gradient(top,#fff 0,#e9f1f8 60.62%,#f2f5f7 100%); background-image: linear-gradient(to bottom,#fff 0,#e9f1f8 60.62%,#f2f5f7 100%); min-height: 800px; overflow:hidden; }

/* ë¡œê·¸ì¸ */
.content .login { width:100%; max-width:410px; margin:100px auto; border:0; padding:0 15px 0 15px; box-sizing:border-box; }
.content .login .div_login { width:100%; position:relative; box-sizing:border-box; text-align:center; background-color:#FFFFFF; border:0; box-shadow:0 0 18px #d3d3d3; height:390px; border-radius:5px; box-sizing:border-box; padding:20px; background-color:#FFFFFF }
.content .login .div_login .text01 { width:100%; font-size:1em; display:inline-block; text-align:left; }
.content .login .div_login .text01 .text0101 { display:inline-block; float:right; font-weight:500; }
.content .login .div_login .input01 { width:100%; display:inline-blcok; padding:10px 0 0 0; }
.content .login .div_login .input01 input { width:100%; margin:40px 0 0 0; padding:15px 15px 15px 15px; font-size:1em; box-sizing:border-box; letter-spacing:0; border-radius:10px; border:1px solid #EAEAEA; }
.content .login .div_login .input01 input::-webkit-input-placeholder { color:#D4D4D4 }
.content .login .div_login .input02 input { width:100%; margin:20px 0 0 0; padding:15px 15px 15px 15px; font-size:1em; box-sizing:border-box; letter-spacing:0; border-radius:10px; border:1px solid #EAEAEA; }
.content .login .div_login .input02 input::-webkit-input-placeholder { color:#D4D4D4 }

.content .login .btn01 input { width:100%; margin:20px 0 0 0; padding:15px 15px 15px 15px; font-size:1em; box-sizing:border-box; letter-spacing:0; background-color:#2c786c; color:#FFFFFF; border-radius:10px; border:0; }

.content .login .btn02 { width:100%; display:inline-block;  text-align:center; font-size:0.9em; color:#9A9A9A; padding:30px 0 10px 0; }

/* íšŒì›ê°€ìž… */
.content .join { width:100%; max-width:670px;margin:50px auto 150px auto; position:relative; text-align:left; box-sizing:border-box; padding:0 15px 0 15px; }
.content .join .div_join { width:100%; position:relative; box-sizing:border-box; text-align:center; background-color:#FFFFFF; border:0; box-shadow:0 0 18px #d3d3d3; border-radius:10px; box-sizing:border-box; padding:40px 10px 40px 10px;}
.content .join .div_join .btn01 { display:inline-block; width:100%; position:relative; box-sizing:border-box;  padding:20px; text-align:center;  line-height:2em; }
.content .join .div_join .btn01 .img0101 { text-align:right; display:inline-block; width:50%; margin:0 auto; box-sizing:border-box; padding:0 30px 0 0; float:left;}
.content .join .div_join .btn01 .img0101 img { width:100%; max-width:150px; }
.content .join .div_join .btn01 .img0102 { text-align:right; display:inline-block; width:50%; margin:0 auto; box-sizing:border-box; padding:0 30px 0 0; float:left;}
.content .join .div_join .btn01 .img0102 img { width:100%; max-width:150px; }
.content .join .div_join .btn01 .text0101 { display:inline-block; width:50%; float:left; text-align:left; margin:40px 0 0 0;}
.content .join .div_join .btn01 .text0102 { display:inline-block; width:50%; float:left; text-align:left; font-size:1.5em; color:#2c786c }
.content .join .div_join .btn02 { display:inline-block; width:100%; position:relative; box-sizing:border-box; border:1px solid #DEDEDE; border-radius:5px; padding:20px; text-align:center;  line-height:2em; }
.content .join .div_join .btn03 { display:inline-block; width:100%; position:relative; box-sizing:border-box;  text-align:center;  line-height:1.5em; font-size:0.9em; color:#797979 }
.content .join .div_join .btn03 .btn0301 { display:inline-block; width:100%; font-size:1em; color:#000000 }
.content .join .div_join .bar01 { display:inline-block; border-top:1px solid #DEDEDE; width:100%; margin:20px 0 20px 0; }
.content .join .div_join .bar02 { display:inline-block; width:80%; margin:0 auto; border-bottom:1px solid #EFEFEF; margin:40px 0 20px 0; }


/* ì •ë³´ìˆ˜ì • */
.content .modify { width:100%; max-width:670px;margin:50px auto 150px auto; position:relative; text-align:left; }
.content .modify .div_modify { width:100%; position:relative; box-sizing:border-box; text-align:center; background-color:#FFFFFF; border:0; box-shadow:0 0 18px #d3d3d3; border-radius:10px; box-sizing:border-box; padding:40px 20px 40px 20px;}
.content .modify .div_modify .btn01 { display:inline-block; width:100%; position:relative; box-sizing:border-box; border:1px solid #DEDEDE; border-radius:5px; padding:20px; text-align:center; background-color:#FFFFFF; line-height:2em; }
.content .modify .div_modify .btn02 { display:inline-block; width:100%; position:relative; box-sizing:border-box; border:1px solid #DEDEDE; border-radius:5px; padding:20px; text-align:center; background-color:#FFFFFF; line-height:2em; }
.content .modify .div_modify .btn03 { display:inline-block; width:100%; position:relative; box-sizing:border-box;  text-align:center;  line-height:1.5em; font-size:0.9em; color:#797979 }
.content .modify .div_modify .bar01 { display:inline-block; width:100%; margin:20px 0 20px 0; }
.content .modify .div_modify .bar02 { display:inline-block; width:80%; margin:0 auto; border-bottom:1px solid #EFEFEF; margin:40px 0 20px 0; }


.content .con_blank01 { width:100%; position:relative; overflow:hidden; height:15px; }
.content .con_blank02 { width:100%; position:relative; overflow:hidden; height:30px; }
.content .con_blank03 { width:100%; position:relative; overflow:hidden; height:10px; }
.content .con_blank04 { width:100%; position:relative; overflow:hidden; height:5px; }


.content .con_bar01 { width:100%; display:inline-block; position:relative; overflow:hidden; height:10px; background-color:#F4F4F4; border-top:1px solid #DEDEDE; border-bottom:1px solid #EFEFEF}
.content .con_bar02 { width:100%; display:inline-block; position:relative; overflow:hidden; height:10px; background-color:#FFFFFF; border-top:1px solid #DEDEDE; border-bottom:1px solid #EFEFEF}
.content .con_bar03 { width:100%; display:inline-block; position:relative; overflow:hidden; background-color:#FFFFFF; border-top:1px solid #DEDEDE; padding:10px 0 10px 0}
.content .con_bar04 { width:100%; display:inline-block; position:relative; overflow:hidden; background-color:#FFFFFF; border-top:1px solid #DEDEDE; margin:10px 0 10px 0}

.content .table_css01 { width:100%; table-layout:fixed;  }
.content .table_css01 th.table_head { border-top:2px solid #DEDEDE; border-bottom:1px solid #DEDEDE; padding:10px 0 10px 0; text-align:center; color:#373737; font-weight:500; background-color:#FAFAFA; }
.content .table_css01 td.table_con { border-bottom:1px solid #DEDEDE; padding:10px 0 10px 0; color:#575757; font-weight:400; background-color:#FFFFFF; text-align:left; font-size:0.9em; }
.content .table_css01 td.table_con.cen { text-align:center; }



.checks {position: relative;}

.checks input[type="checkbox"] {  /* ì‹¤ì œ ì²´í¬ë°•ìŠ¤ëŠ” í™”ë©´ì—ì„œ ìˆ¨ê¹€ */
  position: absolute;
   width: 1px;
   height: 1px;
   padding: 0;
   margin: -1px;
   overflow: hidden;
   clip:rect(0,0,0,0);
   border: 0
}
.checks input[type="checkbox"] + label {
  display: inline-block;
  position: relative;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}
.checks input[type="checkbox"] + label:before {  /* ê°€ì§œ ì²´í¬ë°•ìŠ¤ */
  content: ' ';
  display: inline-block;
  width: 18px;  /* ì²´í¬ë°•ìŠ¤ì˜ ë„ˆë¹„ë¥¼ ì§€ì • */
  height: 18px;  /* ì²´í¬ë°•ìŠ¤ì˜ ë†’ì´ë¥¼ ì§€ì • */
  line-height: 18px; /* ì„¸ë¡œì •ë ¬ì„ ìœ„í•´ ë†’ì´ê°’ê³¼ ì¼ì¹˜ */
  text-align: center; 
  vertical-align: middle;
  background: #FFFFFF;
  border: 1px solid #cacece;
  border-radius : 3px;
  box-shadow: 0px 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05);
  margin:-2px 8px 0 10px;
}
.checks input[type="checkbox"] + label:active:before,
.checks input[type="checkbox"]:checked + label:active:before {
  box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}

.checks input[type="checkbox"]:checked + label:before {  /* ì²´í¬ë°•ìŠ¤ë¥¼ ì²´í¬í–ˆì„ë•Œ */ 
  content: '\2714';  /* ì²´í¬í‘œì‹œ ìœ ë‹ˆì½”ë“œ ì‚¬ìš© */
  color: #2c786c;
  text-shadow: 1px 1px #fff;
  background: #FFFFFF;
  border-color: #adb8c0;
  box-shadow: 0px 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05), inset 15px 10px -12px rgba(255,255,255,0.1);
}


.checks input[type="radio"] {  /* ì‹¤ì œ ì²´í¬ë°•ìŠ¤ëŠ” í™”ë©´ì—ì„œ ìˆ¨ê¹€ */
  position: absolute;
   width: 1px;
   height: 1px;
   padding: 0;
   margin: -1px;
   overflow: hidden;
   clip:rect(0,0,0,0);
   border: 0
}
.checks input[type="radio"] + label {
  display: inline-block;
  position: relative;
  cursor: pointer;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
}
.checks input[type="radio"] + label:before {  /* ê°€ì§œ ì²´í¬ë°•ìŠ¤ */
  content: ' ';
  display: inline-block;
  width: 18px;  /* ì²´í¬ë°•ìŠ¤ì˜ ë„ˆë¹„ë¥¼ ì§€ì • */
  height: 18px;  /* ì²´í¬ë°•ìŠ¤ì˜ ë†’ì´ë¥¼ ì§€ì • */
  line-height: 18px; /* ì„¸ë¡œì •ë ¬ì„ ìœ„í•´ ë†’ì´ê°’ê³¼ ì¼ì¹˜ */
  text-align: center; 
  vertical-align: middle;
  background: #FFFFFF;
  border: 1px solid #cacece;
  border-radius : 10px;
  box-shadow: 0px 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05);
  margin:-2px 6px 0 10px;
}
.checks input[type="radio"] + label:active:before,
.checks input[type="radio"]:checked + label:active:before {
  box-shadow: 0 1px 2px rgba(0,0,0,0.05), inset 0px 1px 3px rgba(0,0,0,0.1);
}

.checks input[type="radio"]:checked + label:before {  /* ì²´í¬ë°•ìŠ¤ë¥¼ ì²´í¬í–ˆì„ë•Œ */ 
  content: '\2714';  /* ì²´í¬í‘œì‹œ ìœ ë‹ˆì½”ë“œ ì‚¬ìš© */
  color: #2c786c;
  text-shadow: 1px 1px #fff;
  background: #FFFFFF;
  border-color: #adb8c0;
  box-shadow: 0px 1px 2px rgba(0,0,0,0.05), inset 0px -15px 10px -12px rgba(0,0,0,0.05), inset 15px 10px -12px rgba(255,255,255,0.1);
}
</style>
