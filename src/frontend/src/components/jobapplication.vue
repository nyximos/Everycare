<template>
<div>
  <div class="content">
	<div class="match">
<link rel="stylesheet" href="/css/cupertino.datepicker.css?v=202103040001">
	<div class="match_div"> 
		<h3 class="tit">채용내역</h3>
		<div class="con_blank01"></div>
		<div class="con_bar01"></div>
		<div class="con02">
			<ul class="tab01">
				<a href="javascript:tab_list(2);"><li class="menu02 on">받은 내역</li></a>
			</ul>
		</div>
		<div class="con_bar02"></div>
		<div class="con03 tab1" id="div01" style="display: none;">
			<ul class="list01">
				<li class="con0302">
				  현재 받은 내역이 없습니다.
				</li>
			</ul>
		</div>
    
		<div class="con03 tab2" id="div02" style="display: block;"  v-for="(p,index) in profiles" :key="index"
     >
			<ul class="list01">
								<li class="con0301">
					<a><span class="more01" @click="move(p)">상세보기</span></a>
					<span class="name01">{{p.careSitterDTO.name}}</span>
					<span class="sex01">{{p.memberDTO.gender}} <span class="bar01">|</span> {{p.memberDTO.birth}}</span>
					<span class="pay01">* 희망급여 <span>시급 : <span style="color: #3c3c3c">{{p.pay}}원</span></span></span>
          <span class="pay01">* 케어날짜 <span>시작일 : <span style="color: #3c3c3c">{{p.startDate}}</span></span> <span class="bar01">|</span> <span>종료일 : {{p.endDate}}</span></span>
					<span class="subject01">{{p.careSitterDTO.introduction}}</span>
					<span class="con_bar04"></span>
					<span class="btn01">
					<input type="button" value="승낙 및 결제" class="btn0103" @click="payment(p)">
					</span>
        	</li>
				</ul>
		</div>

		<div class="layer01" id="layer_match_list">
		</div>
	</div>	</div>
</div>
</div>

</template>

<script>
export default {
    name:'jobapplication',
    data() {
        return {
          profiles: [],
          tossPayments : TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg"),
          orderId : new Date().getTime(),
          customDate : new Date(),
        }   
    },
    methods:{
      move(p) {
            this.$router.push({ name: 'addprofile2', params: { caresitterId: p.careSitterDTO.id } });   
        },
       payment(p){
        var tossPayments = TossPayments("test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg");
        var customDate = new Date()
        var paymentData = {
            amount: p.pay,
            orderId: this.orderId,
            orderName: p.name,
            
            
            // successUrl: `https://localhost:8086/api/dashboard/contracts/payments?contractId=${p.id}`,
            successUrl: `https://localhost:8086/test2/?contractId=${p.id}`,
            // successUrl:`https://localhost:8086/`,
            failUrl: 'https://localhost:8080/fail',
        };

         tossPayments.requestPayment("카드", paymentData);

         
        //  var link = `https://localhost:8086/api/dashboard/contracts/payments?paymentKey=test_ck_Lex6BJGQOVDGPJNGkJq3W4w2zNbg&orderId=${this.orderId}&amount=${p.pay}&contractId=${p.id}`;
        //  window.open(link); 
      // this.$router.push({
      //   path: '/carepayment'
      // })

    
       }
    },
 mounted() {

  const id = this.$route.params.contentId;
 
  console.log(id);
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
},
}

</script>

<style scoped>
li{
  list-style: none;
}
.mx-auto{
  display: inline-block;
    text-align: center;
    border: 1px dashed #666;
    background-color: #fff;
    font-size: 17px;
    font-weight: bold;
    color: #222;
}
.content { width:100%; position:relative; }
.content .match .con_bar02 { float:left; }
.content .match { width:100%; max-width:670px; min-height:800px; background-color:#FFFFFF; position:relative; margin:0 auto; border-left:1px solid #DEDEDE; border-right:1px solid #DEDEDE; }
.content .match .match_div { width:100%; max-width:670px; padding:30px 0 0 0; box-sizing:border-box; position:relative; }
.content .match .match_div h3.tit { width:100%; padding:0 15px 0 15px; box-sizing:border-box; position:relative; font-size:1.3em; font-weight:500; color:#333333; }

.content .match .match_div .con01 { width:100%; position:relative; margin:0 auto; padding:15px 15px 15px 15px; box-sizing:border-box; border-top:1px solid #2c786c; background-color:#2c786c; color:#FFFFFF; overflow:hidden; }
.content .match .match_div .con01 .profile { width:75px; margin:0 15px 0 0; float:left; border-radius:100px; position:relative; display:inline-block; }
.content .match .match_div .con01 .profile img { width:100%; position:relative; display:inline-block; }
.content .match .match_div .con01 .name { width:calc(100% - 90px); position:relative; display:inline-block; text-decoration:underline;}
.content .match .match_div .con01 .phone { width:calc(100% - 90px); position:relative; display:inline-block; padding:10px 0 0 0; letter-spacing:0; }
.content .match .match_div .con01 .pay { width:calc(100% - 90px);  position:relative; display:inline-block; padding:5px 0 0 0 }
.content .match .match_div .con01 .modify { display:inline-block; width:auto; background-color:#FFFFFF; border:0; border-radius:25px; color:#2c786c; font-size:0.8em; padding:5px; margin:0 0 0 10px;}
.content .match .match_div .con01 .paysubmit { position:absolute; display:inline-block; padding:10px; background-color:#f8b400; border:0; border-radius:25px; color:#FFFFFF; right:15px; top:15px; font-size:0.9em; }

.content .match .match_div .con02 { width:100%; position:relative; margin:0 auto; padding:0 0 0 0; box-sizing:border-box; overflow:hidden; }
.content .match .match_div .con02 .tab01 { width:100%; position:relative; }
.content .match .match_div .con02 .tab01 .menu01 { width:50%; padding:20px 0 20px 0; box-sizing:border-box; position:relative; border-bottom:1px solid #EFEFEF; float:left; text-align:center; background-color:#2c786c2e; color:#2c786ca8 }
.content .match .match_div .con02 .tab01 .menu02 { width:100%; padding:20px 0 20px 0; box-sizing:border-box; position:relative; border-bottom:1px solid #EFEFEF; float:left; text-align:center; background-color:#2c786c2e; color:#2c786ca8 }
.content .match .match_div .con02 .tab01 .on { color:#FFFFFF; background-color:#81C784 }


.content .match .match_div .con03 { width:100%; position:relative; margin:0 auto; padding:15px; box-sizing:border-box; overflow:hidden; display:none; }
.content .match .match_div .con03 .list01 { width:100%; border:1px solid #DEDEDE; border-radius:10px; position:relative; margin:0 auto; padding:10px; box-sizing:border-box; overflow:hidden; }
.content .match .match_div .con03 .con0301 { width:100%; border-bottom:1px solid #DEDEDE; position:relative; margin:10px auto 10px auto; padding:10px; box-sizing:border-box; overflow:hidden; line-height:1.2em; }
.content .match .match_div .con03 .con0302 { width:100%; position:relative; margin:10px auto 10px auto; padding:30px 10px 30px 10px; box-sizing:border-box; overflow:hidden; line-height:1.2em; text-align:center; }
.content .match .match_div .con03 .con0301 .more01 { position:absolute; float:right; right:10px; display:inline-block; margin:0 0 10px 0; font-size:0.9em; color:#FFFFFF; background-color:#2c786c; border:1px solid #2c786c; border-radius:30px; padding:3px 10px 3px 10px; }
.content .match .match_div .con03 .con0301 .date01 { display:inline-block; width:100%; margin:0 0 10px 0; font-size:0.9em; color:#9A9A9A }
.content .match .match_div .con03 .con0301 .date02 { display:inline-block; width:100%; margin:0 0 10px 0; font-size:0.9em; color:#373737; text-align:center; }
.content .match .match_div .con03 .con0301 .date02 .head01 { display:inline-block; color:#A9A9A9; margin:0 10px 0 0; }
.content .match .match_div .con03 .con0301 .date02 .cont01 { display:inline-block; color:#2c786c; font-size:1em }
.content .match .match_div .con03 .con0301 .date02 .icon01 { display:inline-block; background-color:#2c786c; font-size:0.9em; padding:0 5px; color:#FFFFFF; border-radius:3px; margin:0 5px 0 0; font-weight:400 }
.content .match .match_div .con03 .con0301 .date02 .icon02 { display:inline-block; background-color:#f8b400; font-size:0.9em; padding:0 5px; color:#FFFFFF; border-radius:3px; margin:0 5px 0 0; font-weight:400 }
.content .match .match_div .con03 .con0301 .date02 .icon03 { display:inline-block; background-color:#F4F4F4; font-size:0.9em; padding:0 5px; color:#9A9A9A; border-radius:3px; margin:0 5px 0 0; font-weight:400 }
.content .match .match_div .con03 .con0301 .name01 { display:inline-block; margin:0 5px 5px 0; }
.content .match .match_div .con03 .con0301 .sex01 { display:inline-block; margin:0 5px 0 0; font-size:0.9em; color:#797979 }
.content .match .match_div .con03 .con0301 .area01 { display:inline-block; margin:0 5px 0 0; font-size:0.9em; color:#797979 }
.content .match .match_div .con03 .con0301 .pay01 { display:inline-block; width:100%; margin:0 0 0 0; font-size:0.8em; color:#797979 }
.content .match .match_div .con03 .con0301 .insurance01 { display:inline-block; width:100%; margin:0 0 5px 0; font-size:0.8em; color:#9A9A9A }
.content .match .match_div .con03 .con0301 .service_date01 { display:inline-block; width:100%; margin:0 0 5px 0; font-size:0.8em; color:#797979 }
.content .match .match_div .con03 .con0301 .subject01 { display:inline-block; width:100%; margin:0 0 0 0; }
.content .match .match_div .con03 .con0301 .bar01 { display:inline-block; margin:0 0 0 0; font-size:0.7em; color:#DEDEDE }
.content .match .match_div .con03 .con0301 .btn01 { width:100%; display:inline-block; text-align:center; }
.content .match .match_div .con03 .con0301 .btn01 .btn0101 { border:1px solid #2c786c; background-color:#2c786c; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:calc(50% - 8px); margin:0 6px 0 0; text-align:center; font-weight:600; }
.content .match .match_div .con03 .con0301 .btn01 .btn0102 { border:1px solid #DEDEDE; background-color:#F4F4F4; color:#797979; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:calc(50% - 8px); margin:0 0px 0 6px;  text-align:center;}
.content .match .match_div .con03 .con0301 .btn01 .btn0103 { border:1px solid #DEDEDE; background-color:#F4F4F4; color:#797979; border-radius:5px; padding:10px; display:inline-block; width:calc(50% - 10px); margin:0 3px 0 3px;  text-align:center;}
.content .match .match_div .con03 .con0301 .btn01 .btn0104 { border:1px solid #f8b400; background-color:#f8b400; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:calc(50% - 8px); margin:0 6px 0 0; text-align:center; font-weight:600; }
.content .match .match_div .con03 .con0301 .btn01 .btn0104.on { border:1px solid #f8b400; background-color:#f8b400; color:#000000; font-weight:bold; }

.content .match .match_div .con03 .con0301 .btn01 .btn0105 { border:1px solid #f8b400; background-color:#f8b400; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:calc(50% - 8px); margin:0 6px 0 0; text-align:center; font-weight:600; }
.content .match .match_div .con03 .con0301 .btn01 .btn0106 { border:1px solid #DEDEDE; background-color:#F4F4F4; color:#373737; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:calc(50% - 8px); margin:0 6px 0 0; text-align:center; font-weight:600; }

.content .match .match_div .layer01 { width:100%; position:relative; display:none; }
.content .match .match_div .layer01 .layer0101 { position:fixed; border-radius:10px; max-width:640px; width:90%; left:0; right:0; margin:0 auto; top:calc( 100% / 2 - 250px ); border:1px solid #DEDEDE; background-color:#FFFFFF; z-index:22; padding:20px; box-sizing:border-box; box-shadow:3px 3px 10px 2px #797979; text-align:center; }
.content .match .match_div .layer01 .layer0101 .text01 { display:inline-block; width:100%; text-align:center; padding:20px 0 10px 0; box-sizing:border-box;}
.content .match .match_div .layer01 .layer0101 .btn01 { width:100%; display:inline-block; text-align:center; padding:30px 0 50px 0;}
.content .match .match_div .layer01 .layer0101 .btn01 .btn0101 { border:1px solid #2c786c; background-color:#2c786c; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:30%; margin:0 6px 0 0; text-align:center; font-weight:600; }
.content .match .match_div .layer01 .layer0101 .btn01 .btn0102 { border:1px solid #f8b400; background-color:#f8b400; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:30%; margin:0 6px 0 0; text-align:center; font-weight:600; }
.content .match .match_div .layer01 .layer0101 .btn01 .btn0103 { border:1px solid #DEDEDE; background-color:#F4F4F4; color:#9A9A9A; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:30%; margin:0 6px 0 0; text-align:center; font-weight:600; }
.content .match .match_div .layer01 .layer0101 .text02 { display:none; width:100%; text-align:center; padding:40px 0 20px 0; box-sizing:border-box; border-radius:10px; background-color:#F4F4F4; }
.content .match .match_div .layer01 .layer0101 .text02 .text0201 { line-height:1.3em; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 { width:100%; display:inline-block; text-align:center; padding:30px 0 20px 0;}
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .btn0201 { border:1px solid #2c786c; background-color:#2c786c; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:45%; margin:0 3px 0 0; text-align:center; font-weight:600; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .btn0202 { border:1px solid #DEDEDE; background-color:#DEDEDE; color:#9A9A9A; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:45%; margin:0 0 0 3px; text-align:center; font-weight:600;}
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .text0202 .date01 { position:relative; width:100%; text-align:center; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .btn0203 { border:1px solid #2c786c; background-color:#2c786c; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:90%; margin:10px 0 0 0; text-align:center; font-weight:600; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .btn0204 { border:1px solid #DEDEDE; background-color:#DEDEDE; color:#9A9A9A; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:90%; margin:15px 0 0 0; text-align:center; font-weight:600;}
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .bar02 { display:inline-block; width:80%; margin:0 auto; border-top:1px solid #DEDEDE}

.content .match .match_div .layer01 .layer0101 .text02 .btn02 .text0203 { display:inline-block; position:relative; width:100%; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .text0203 .select01 { width:90%; box-sizing:border-box; height:42px; font-size:0.8em; text-align:center; letter-spacing:0; border-radius:5px; border:1px solid #2c786c; margin:0 0 0 0; text-indent:10px;  }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .text0203 .textarea01 { display:inline-block; width:90%; box-sizing:border-box; height:150px; font-size:0.9em; text-align:left; letter-spacing:0; border-radius:5px; border:1px solid #2c786c; margin:10px 0 0 0; text-indent:0; padding:10px; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .text0203 .textarea01::placeholder { line-height:1.5em; color:#9A9A9A }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .star01 { text-align:center; color:#2c786c; display:inline-block; padding:0px 0 10px 0; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .star01 .icon_star { color:#2c786c; margin:0 5px 0 5px; font-size:1.8em; letter-spacing:0; content:'☆' }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .star01 .icon_star:after { content:'☆' }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .star01 .icon_star.on { color:#f8b400; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .star01 .icon_star.on:after { content:'★'  }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .btn0205 { border:1px solid #2c786c; background-color:#2c786c; color:#FFFFFF; border-radius:5px; padding:10px; display:inline-block; box-sizing:border-box; width:90%; margin:10px 0 0 0; text-align:center; font-weight:600; }


.content .match .match_div .layer01 .layer0101 .text02 .btn02 .sample01 { display:inline-block; position:relative; width:100%; margin:7px 0 0 0; }
.content .match .match_div .layer01 .layer0101 .text02 .btn02 .sample01 .select01 { width:90%; box-sizing:border-box; height:42px; font-size:0.8em; text-align:left; letter-spacing:0; border-radius:5px; border:1px solid #2c786c; margin:0 0 0 0; text-indent:5px;  }
#ui-datepicker-div { display:none; top:180px !important; z-index:33 !important; position:fixed !important; margin:0 auto 0 auto; left:0 !important; right:0 !important; width:90%; max-width:320px; box-sizing:border-box; }
.ui-datepicker-trigger { width:41px; height:40px; border:1px solid #2c786c; border-radius:5px; vertical-align:top; }
#input_interview_date { width:calc(90% - 53px); height:42px; font-size:1.1em; text-align:center; letter-spacing:0; border-radius:5px; box-sizing:border-box; border:1px solid #2c786c; margin:0 5px 0 0; text-indent:10px; }
html,body,div,span,object,iframe,h1,h2,h3,h4,h5,h6,p,blockquote,pre,abbr,address,cite,code,del,dfn,em,img,ins,kbd,q,samp,small,strong,sub,sup,var,b,i,dl,dt,dd,ol,ul,li,fieldset,form,label,legend,table,caption,tbody,tfoot,thead,tr,th,td,article,aside,canvas,details,figcaption,figure,footer,header,hgroup,menu,nav,section,summary,time,mark,audio,video{margin:0;padding:0;border:0;outline:0;font-size:100%;}
body{line-height:1; font-size:1em; font-family:"Noto Sans KR", sans-serif; color:#3c3c3c; letter-spacing:-0.05em}
html,body{background:#FFFFFF}
article,aside,details,figcaption,figure,footer,header,hgroup,menu,nav,section{display:block}
nav ul{list-style:none}
blockquote,q{quotes:none}
blockquote:before,blockquote:after,q:before,q:after{content:none}
a{margin:0;padding:0;font-size:100%;vertical-align:baseline;background:transparent;text-decoration:none;color:#666;}
a:link,a:visited {color:#000;text-decoration:none;}
a:hover,a:focus,a:active{color:#000;text-decoration:none;}
ins{background-color:#ff9;color:#000;text-decoration:none}
mark{background-color:#ff9;color:#000;font-style:italic;font-weight:bold}
del{text-decoration:line-through}
abbr[title],dfn[title]{border-bottom:1px dotted;cursor:help}
table{border-collapse:collapse;border-spacing:0}
hr{display:block;height:1px;border:0;border-top:1px solid #ccc;margin:1em 0;padding:0}
input,select{vertical-align:middle; background-color:#FFFFFF }

input:focus, textarea:focus, select:focus, option:focus {
    outline:none !important;
}
/*�߰�*/
table{width:100%}
ol, ul {list-style:none}
address, caption, em, cite {font-weight:normal;font-style:normal}
fieldset, img, abbr, acronym {border:none}
img {border:0;vertical-align:baseline}
button {overflow:visible;margin:0;padding:0;border:0 none;background:transparent;cursor:pointer}
</style>