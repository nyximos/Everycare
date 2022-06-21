<template>
 <div class="content">
  
	<div class="list">
		<div class="div_list">


 
	<div class="tit">내 구인글 목록
		<!-- <span class="sort01">
		<v-btn @click="gocreate">
          <v-icon left>
            mdi-pencil
          </v-icon>
            Create
        </v-btn>
		</span> -->
	</div>
	<div class="rlist">
		<div class="list_more list_more1"><ul class="ul01"><li class="li01" v-for="(p, index) in listData" :key="index" @click="move(p)">

	<span class="tab01">
		<span class="img01"><img src="" class=""></span>
		<span class="text01">{{p.careTargetSchedule.name}}</span>
	</span>
	<span class="tab02">
		
		<span class="sphere"> {{p.careTarget.address}}</span>
		<span class="subject">{{p.title}}</span>

		<span class="pay">시급 {{p.pay}}원 <span class="bar0101">&nbsp;&nbsp;월급 {{p.amount}} 원</span></span>
    <span class="subject">희망날짜 : {{p.startDate}} ~ {{p.endDate}}</span>
	</span>
	<div class="bar01"></div>
	<span class="tab03">



	</span>
  <!-- </a> -->

</li></ul></div>

	</div>


   
   <!-- <div class="text-center">
    <v-pagination
      class="mt-3"
      v-model="curPageNum"
      :length="numOfPages"
      circle
    ></v-pagination>
  </div>  -->
  
</div>

	</div>
</div>
</template>

<script>
import ListItem from '@/components/listItem'
export default {
    name: 'mypagejoblist',
    components:{
      ListItem
    },
    mounted() {
        this.$http
        .get('/api/teeeeest', {
        withCredentials: true
        })
        .then(res => {
          console.log(res.data.body)
          this.listData = res.data.body;
        })
          .catch(err => {
          console.log(err);
        });
    },
    data(){
        return{
          listData:[],
          dataPerPage:3,
          // curPageNum:1,
          SearchText: '',
        }
    },
    methods:{
      move(p) {
            
            this.$router.push({
            name: 'jobapplication',
            params: {
              contentId: p.id
            }
          })
            
            
        },
        gocreate(){
          this.$router.push({
            path: '/recruitions/new'
          })
        },
        detailShot(id){
          this.$router.push({
            name: 'jobapplication',
            params: {
              contentId: id
            }
          })
        }
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