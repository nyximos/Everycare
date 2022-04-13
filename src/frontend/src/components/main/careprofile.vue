<template>
  <div class="content">
      <div class="modify">
          <div class="div_modify">
              <div class="rwrite">
                  <div class="rwrite_div">
                      <div class="page_btn">
                          <span class="page_btn1 on"></span>
                          <span class="page_btn1 "></span>
                      </div>
                      <div class="con10 con1">
                          <h3 class="profile_title">케어시터 프로필</h3>
                      </div>
                      <div class="con10_blank"></div>
                      <div class="area_profile">
                          <div class="div_img">
                              <label for="input_file">
                                  <img :src="avatar" class="img-avatar">
                                  <!-- <img src="@/assets/user.png"> -->
                              </label>
                          </div>
                          <div class="div_text">
                                 <span><input type="file" v-bind:src="image" id="uppic" accept="image/gif,image/jpg,image/png" @change="changeImage($event)" ref="avatarInput" class="uppic"></span>
                             
                              
                              <!-- <v-file-input v-model="image" truncate-length="22" label="사진을 넣으세요"  @change="changeImage($event)" ></v-file-input> -->
                          </div>
                          <div class="con10_blank"></div>
                          <div class="r_content">
                              <br>
                              <v-text-field v-model="name" label="Name" required></v-text-field>
                               <v-radio-group v-model="radios" mandatory>
                                    <v-radio label="남" value="Man"></v-radio>
                                    <v-radio label="여" value="Woman"></v-radio>
                                </v-radio-group>
                              <h2>나이</h2>
                              <v-text-field v-model="age" label="Age" required></v-text-field>
                              <br>
                              <h5>자기소개</h5>
                              <textarea class="content_add" placeholder="자기소개써주세요" v-model="textarea"></textarea>
                          </div>
                          <!-- <router-link :to="{name: 'careprofile1'}"> -->
                                <v-btn class="ma-2" outlined color="indigo" @click="nextpage1">다음</v-btn>
                          <!-- </router-link> -->
                          <router-link to="/"> <v-btn class="ma-2" outlined color="indigo">취소</v-btn></router-link>
                          </div>
                      </div>
                  </div>
              </div>
          </div>
      </div>
</template>

<script>
export default {
  name:'careprofilecom',
  data(){
     return{
        avatar:require('@/assets/user.png'),
        image: [],
        name:'',
        radios:'',
        age:'',
        textarea:''
     }
  },
  methods:{
      changeImage(e){
        var file = e.target.files[0]
        var reader = new FileReader()
        var that = this
        reader.readAsDataURL(file)
        reader.onload = function(){
            that.avatar = this.result
        }
    },
    
     
      nextpage1(){
          
        const userData = {
        image : this.image.name,
        name:this.name,
        sex:this.radios,
        age:this.age,
        textarea:this.textarea
        }
        try{
            if(this.image.name ==""){
                alert("사진을 넣어주세요!");
                this.image.name.focus();
                return;
            }
            if(this.name == ""){
                alert("이름을 입력해주세요!"); 
                this.name.focus();
                return;
            }
            if(this.radio ==""){
                 alert("성별을 입력해주세요!");
                 return;
            }
            if(this.age ==""){
                 alert("나이를 입력해주세요!");
                 return;
            }else{
          this.$store.commit('careprofileStore/set_user1', userData);
          console.log(this.$store.state.careprofileStore.image);
            this.$router.push({ path: '/dashboard/careprofile1' })


            }
            
        } catch(error){
           console.log(error); 
        }
        
    }
  }
}
</script>

<style>
div{
    display: block;
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
    .area_profile .r_content{
        width: 100%;
        margin: 0 auto;
        position: relative;
        overflow: hidden;
        text-align: left;
        
    }
    .area_profile .r_content .content_add{
        width: 100%;
        height: 100px;
        border: 1px solid #cccccc;
        border-radius: 5px;
        overflow: hidden;
    }
</style>