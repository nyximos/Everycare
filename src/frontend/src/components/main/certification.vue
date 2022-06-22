<template>
   <div class="content">
      <div class="modify">
          <div class="div_modify">
              <div class="rwrite">
                  <div class="rwrite_div">
                      <div class="area_profile">
                        <h5 class="title">자격증 등록</h5>   
                <ul>
                    <li>자격증 분류<br>
                        <v-select name="certification1" id="certification1"
                            v-model="certification"
                            :items="certi"
                            label="카테고리"
                            item-text="name"
                            item-value="value" 
                            @change="categoryChange($event)" onon>
                        </v-select>
                        <v-select name="sido_detail" id="sido1_detail"
                      v-model="detail"
                      :items="certi_detail"
                      label="자격증 이름"
                      item-text="name"
                      item-value="value"
                    v-if="select1"
                      >
                    </v-select>
                    </li>
                    <br><br>
                    <li>
                    자격증 사진<br><br><br>
                        <v-file-input 
                            v-model="attachFile" 
                            label="File input" 
                            type="file"
                            id="attachFile"
                            outlined dense>
                        </v-file-input>
                    </li>
                </ul>                          
                        <v-btn class="ma-2" outlined color="indigo" @click="submit">완료</v-btn>
                              <router-link to="/mypage_profile"><v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
                         </div>
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
            certi:[
                {name:'아동', value:'아동'},
                {name:'노인', value:'노인'},
            ],
            certi_01:[
                {name:'아동심리상담사 1급', value:'아동심리상담사 1급'},
                {name:'아동요리지도사 1급', value:'아동요리지도사 1급'},
                {name:'독서논술지도사', value:'독서논술지도사'},
            ],
            certi_02:[
                {name:'노양보호사', value:'노양보호사'},
                {name:'생활지원사', value:'생활지원사'},
                {name:'사회복지사', value:'사회복지사'},
                {name:'운전면허', value:'운전면허'},
            ],
            attachFile:'',
            certification:'',
            certi_detail:[],
            detail:'',
            id:this.$store.state.userStore.careSitterId,
            result:[],
            select1:false
        }   
    },
    methods:{
        submit(){
            var formData = new FormData();

            if(this.detail==""){
            alert("자격증을 선택해주세요");
            return;
          }
           if(this.attachFile==""){
            alert("자격증 사진을 등록해주세요");
            return;
          }
            const id = this.$store.state.userStore.careSitterId;
            formData.append('careSitterId',this.id);
            formData.append('classification', this.detail);
            formData.append('attachFile', this.attachFile);

            
            this.$http
           .post(`/api/dashboard/caresitter/${id}/certifications`,formData,{
               withCredentials:true
           })
           .then(res=>{
        
               console.log(res);
               alert('자격증 등록완료');
              this.$router.push({ path: '/Main' })
           }).catch(err=>{
               console.log(err);
           })
        },
        categoryChange(event){
            
        if(event =='아동'){
            this.select1=true
          this.certi_detail = this.certi_01;
        }else if(event == '노인'){
            this.select1=true
          this.certi_detail = this.certi_02;
        }
    }
    }
}
</script>

<style>
        div{
    display: block;
}
li{
    list-style: none;
}
.title{
    text-align: left;
}
    .content{
        width: 100%;
        min-height: 800px;
        overflow: hidden;
    }
    .content .modify{
        width: 100%;
        max-width: 670px;
        margin: 50px auto 150px auto;
        position: relative;
        text-align: left;
    }
    .content .modify .div_modify{
        width: 100%;
        position: relative;
        box-sizing: border-box;
        text-align: center;
        background-color: #ffffff;
        border: 0;
        box-shadow: 0 0 18px #d3d3d3;
        border-radius: 10px;
        box-sizing: border-box;
        padding: 40px 20px 40px 20px;
    }
    .content .modify .div_modify .rwrite .rwrite_div{
        widows: 100%;
        position: relative;
        padding: 0 0 10px 0;
        overflow: hidden;
    }
    .content .modify .div_modify .rwrite .page_btn{
        width: 100%;
        position: relative;
        height: 25px;
        text-align: center;
    }
    .content .modify .div_modify .div_modify .rwrite .page_btn span{
        display: inline-block;
        border-radius: 17px;
        box-sizing: border-box;
        width: 17px;
        height: 17px;
        background-color: #cccccc;
        font-size: 0;
        line-height: 0;
        margin: 0 4px 0 4px;
    }
    .content .modify .div_modify .div_modify .rwrite .page_btn span on{
        background-color: #2c786c;
    }
    .content .modify .div_modify .rwrite .rwrite_div .con10{
        width: 100%;
        position: relative;
        overflow: hidden;
        padding: 30px 0 20px 0;
        text-align: left;
    }
    .profile_title{
        text-size:1.3em;
        font-weight: 400;
        text-align: center;
    }
    .content .con10_blank{
        width: 100%;
        position: relative;
        overflow: hidden;
        height: 5px;
    }
    .area_profile{
        width: 100%;
        position: relative;
        overflow: hidden;
        padding: 0 15px 0 15px;
        box-sizing: border-box;
        text-align: center;
    }
    .area_profile .div_img{
        position: relative;
        width: 160px;
        text-align: center;
        margin: 0 auto;
        height: 160px;
        overflow: hidden;
        border-radius: 160px;
        box-sizing: border-box;
        border: 1px solid #e3e3e3;
    }
    .area_profile .div_img img{
        width: 100%;
    }
    .content01{
    width: 100%;
    position: relative;
    box-sizing: border-box;
    overflow: hidden;
    padding: 0 15px 0 15px;
    display: none;
  }
  .work_area{
    width: 100%;
    margin: 0 auto;
    display: none;
    text-align: left;
  }
  .area_stitle{
    display: inline-block;
    width: 52px;
    font-weight: 500;
    box-sizing: border-box;
    padding: 9px 0 9px 9px 0;
    margin: 0 3px 0 0;
    vertical-align: middle;
    text-align: center;
    background-color: aquamarine;
    color: white;
    border-radius: 5px;
  }
  .select_css{
    border-radius: 5px;
    font-size: 1em;
    padding: 5px;
    width: 20%;
    height: 34px;
    margin: 0 3px 0 0;
    
    color: black;
  }
  .area_day{
    display: block;
    width: 100%;
    padding: 20px 0 10px 0;
  }
  .sub_title{
    display: block;
    width: 100%;
    padding: 10px 0 10px 0;
    border-bottom:1px solid #dedede ;
    font-size: 1.1em;
    text-align: center;
    font-weight: 400;
  }
  .area6_r_day{
    width: 100%;
    display: block;
    padding: 10px 0 0 0;
    text-align: center;
  }
  .area_day .r_day1{
    position: relative;
    background-color: #fefefe;
    border: 1px solid #dedede;
    color: #797979;
    border-radius: 50px;
    width: 20%;
    height: 40px;
    margin: 0 2px 15px 2px;
    font-size: 0.9em;
    letter-spacing: -0.05em;
  }
  .area_pay{
    display: block;
    width: 100%;
    padding: 30px 0 10px 0;
  }
  .r_pay{
    width: 100%;
    display: block;
    padding: 10px 0 0 0;
    text-align: center;
  }
  .area_pay .r_pay1{
    display: inline-block;
    margin: 0 10px 10px 0;
  }
  .area_pay .pay_plan.on{
    background-color: aqua;
    border: 1 px solid aqua;
    color: black;
  }
  .area_pay .select_css04{
    border-radius: 5px;
    font-size: 1em;
    padding: 5px;
    width: 120px;
    height: 40px;
    vertical-align: middle;
  }
  .area_pay .text_css01{
    height: 40px;
    border-radius:5px;
    box-sizing: border-box;
    border: 1px solid #666666;
    width: 30%;
    margin: 0 auto;
  }
  .content_add{
        width: 100%;
        height: 100px;
        border: 1px solid #cccccc;
        border-radius: 5px;
        overflow: hidden;
    }

</style>