<template>
<div id="container">
  <div id="content" class="cont_wrap resume_view view">
    <div class="section">
            <div id="resume_view">
                <div class="meta" style="">
                    <ul class="func">
                        <li data-is_mine="N" style=""><router-link :to="{name:'profile_update' ,params:{caresitterId:this.$store.state.userStore.careSitterId}}"><span class="btn_pack paper print s">수정하기</span></router-link></li>
                    </ul>
                </div>
                <div class="subject" style="">
                    <h1>케어시터 프로필</h1>
                </div>
                <div class="tbl_personinfo" style="">
                    <table class="tbl_comm">
                        <colgroup>
                            <col style="width:167px">
                            <col style="width:126px">
                            <col>
                            <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th rowspan="5" class="perpic">
                                    <div class="wrapper_pic">
                                        <img :src="'https://localhost:8086/api/images/' + details.attachFiles[0].storeFileName" width="90" height="120" alt="프로필사진" id="per_pic">
                                    </div>
                                </th>
                                <th class="text-l">이름</th>
                                <td colspan="3">
                                    <span class="name bold">{{details.memberDTO.name}}</span>
                                    (<span class="sex bold gender_M">{{details.memberDTO.gender}}</span> / <span class="birth_y">{{details.memberDTO.birth}}</span>년생)
                                </td>
                            </tr>
                            <tr>
                                <th class="text-l">아이디</th>
                                <td class="tel" width="170"></td>
                                <th class="text-l" width="126">휴대폰</th>
                                <td class="mobile">{{details.memberDTO.phone}}</td>
                            </tr>
                            <tr>
                                <th class="text-l">이메일</th>
                                <td class="email">{{details.memberDTO.email}}</td>
                            </tr>
                            <tr>
                                <th class="text-l">주소</th>
                                <td class="homepage">{{details.memberDTO.address}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tbl_hopecondition" style="">
                    <h3><img src="#" alt="희망근무조건"></h3>
                    <table class="tbl_comm">
                        <colgroup>
                            <col width="124">
                            <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th class="text-l">희망시급		</th><td class="hp_jikjong">{{details.hourlyWage}}</td>
                            </tr>
                            <tr>
                                <th class="text-l">희망월급	</th><td class="hp_loc">{{details.monthlyWage}}</td>
                            </tr>
                            <tr>
                                <th class="text-l">선호유형		</th><td class="hp_job_type">{{details.preferredType}}</td>
                            </tr>
                            <tr>
                                <th class="text-l">희망활동시간		</th><td class="hp_pay">{{details.activityTime}}</td>
                            </tr>
                            <tr class="last">
                                <th class="text-l">희망지역		</th><td class="keyword">{{details.hopefulRegion}}</td>
                            </tr>
                            <tr class="last">
                                <th class="text-l">희망요일		</th><td class="keyword">{{details.desiredDayWeek}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tbl_certification" style="">
                    <h3><img src="#" alt="자격증"></h3>
                    <table class="tbl_comm">
                        <colgroup>
                            <col style="width:130px">
                            <col style="width:180px">
                            <col style="width:180px">
                            <col>
                        </colgroup>
                        <thead>
                            <tr class="first">
                                <th>취득일</th>
                                <th>자격증명</th>
                                <th>?</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="text-c"> 
                                    2021년
                                    
                                </td>
                                <td>1급</td>
                                <td class="text-c">ㅇ</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tbl_employment_deals" style="">
                    <h3><img src="#" alt="정보"></h3>
                    <table class="tbl_comm tbl_st2">
                        <colgroup>
                            <col style="width:140px;">
                            <col style="width:240px">
                            <col style="width:140px">
                            <col>
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>CCTV 동의여부</th>
                                <td>{{details.cctvAgreement}}</td>
                                <th>백신여부</th>
                                <td>{{details.is_vaccinated}}</td>
                            </tr>
                            <tr>
                                <th>게시여부</th>
                                <td>{{details.disclosureStatus}}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="tbl_myself" style="">
                    <h3><img src="/img/resume/in_view_t16.gif" alt="자기소개서"></h3>
                    <table class="tbl_comm empty_myself">
                        <tbody>
                            <tr>
                                <td class="empty">
                                    {{details.introduction}}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                 <div class="btn_area" style="">
                    <a href="#" title="돌아가기"><img src="#" alt="돌아가기"></a>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {   
    data(){
        return{
            details:[

            ]
        }
    },
    mounted(){
        const id = this.$store.state.userStore.careSitterId
        this.$http
        .get(`/api/caresitters/${id}`,{
            withCredentials:true
        })
        .then((res)=>{
            console.log(res.data.body);
            this.details = res.data.body;
        }).catch(err=>{
            console.log(err);
        })
    }

}
</script>

<style>
    #container{
        position: relative;
        width: 960px;
        margin: 0 auto;
    }
    #container #content.cont_wrap{
        width: 760px;
    }
    #container #content{
        position: relative;
        margin: 0 auto 30px;
        min-width: 685px;
    }
    #content.resume_view *{
        color: #333;
    }
    #content.resume_view .subject h1{
        font-size: 24px;
        font-weight: normal;
        padding: 20px 10px;
        border-top: 2px solid #595959;
        border-bottom: 1px solid #e5e5e5;
        margin-top: 25px;
        margin-bottom: 45px;
        text-align: center;
    }
    #content.resume_view .btn_pack.paper.print{
        background-position: -144px -310px;
    }
    .btn_pack.paper.print{
        padding-left: 20px;
    }
    .btn_pack.paper.s{
        padding: 3px 5px 2px;
        border: 1px solid #D5D9DC;
        background-color: #f9f9f9;
        font-size: 11px;
    }
</style>