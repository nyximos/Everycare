<template>
<div class="content">
     <div class="title">
        시터찾기
    </div>
    <div class="r_list">
        <div class="list_more">
            <ul class="ul01">
                <li class="li01" v-for="(p,index) in profiles" :key="index"  @click="move(p)">
                    <!-- <router-link :to="{name:'addprofile2+', params:{caresitterId:p.id}}"> -->
                        <span class="tab01">
                            <span class="img01">
                                <!-- <img src="@/assets/user.png" class="vertical"> -->
                                <img :src="'https://localhost:8086/api/images/'+ p.attachFiles[index].storeFileName" alt="사진" class="vertical">
                                
                            </span>
                        </span>
                        <span class="tab02">
                            <span class="name">{{p.memberDTO.name}}
                                <span class="age"> 나이:{{p.memberDTO.birth}}</span>
                                
                            </span>
                            <span class="area">{{p.hopefulRegion}}</span>
                                <span class="pay">
                                    시급:{{p.hourlyWage}} 월급:{{p.monthlyWage}}
                                    <span class="bar0101">&nbsp;</span>
                                    <span class="week01">{{p.desiredDayWeek}}</span>
                                </span>
                        </span>
                        <div class="bar01"></div>
                        <span class="tab03">
                            <span class="icon03">{{p.is_vaccinated}}</span>
                            <span class="text01">{{p.memberDTO.gender}}</span>
                            <span class="text01">{{p.preferredType}}</span>
                            <span class="text01">{{p.activityTime}}</span>
                            <span class="">{{p.member}}</span>
                        </span>       
                 <!-- </router-link> -->
                </li>
            </ul>
        </div>
    </div>
    <!-- <v-card v-for="(userData,index) in $store.state.careprofileStore" :key="index">
        <p>{{userData.name}}</p>
    </v-card> -->
    <div class="text-center">
    <v-pagination v-model="page" :length="4" circle></v-pagination>
  </div>
 </div>
</template>

<script>
export default {
    data(){
		return{
            page:1,
			profiles:[],
            // attachFiles:this.profiles.attachFiles[0].storeFileName
            // id : p.id,
            img : this.img
	    }
    },
    methods:{
         move(p){
            const userData ={
                id : p.id
            }
            this.$router.push({name:'addprofile2' , params:{caresitterId: p.id}})
            this.$store.commit('careprofileStore/move',userData);
            console.log(userData)
        }
    },
	mounted(){
			this.$http
            .get('/api/caresitters' ,{
                withCredentials:true
            })
			.then((res)=>{
                // this.attachFiles = res.data.body.attachFiles[0].storeFileName
                // console.log(this.attachFiles)
                console.log(res.data.body)
                this.profiles = res.data.body
			}).catch(err =>{
				alert(err);
				console.log(err);
			})
	}
}
</script>

<style>
.title{
    width: 100%;
    display: inline-block;
    font-size: 1.2em;
    padding: 0 0 10px 0;
}
.r_list{
    width: 100%;
    max-width: 100%;
    padding: 0;
    box-sizing: border-box;
    position: relative;
}
.content .list .div_list .r_list .list_more{
    width: 100%;
    max-width: 100%;
    padding: 0;
    text-align: center;
    box-sizing: border-box;
    position: relative;
}
.r_list .list_more .ul01{
    width: 100%;
    max-width: 100%;
    padding: 0;
    box-sizing: border-box;
    position: relative;
}
.r_list .list_more .ul01 .li01{
    width: 100%;
    max-width: 100%;
    padding: 20px;
    box-sizing: border-box;
    position: relative;
    margin: 0 0 20px 0;
    border: 0;
    border-radius:5px ;
    line-height: 1.3em;
    background-color: #ffffff;
    text-align: left;
}
.r_list .list_more .ul01 .li01 .tab01{
    /* float: left; */
    width: 100px;
    display: inline-block;
    position: relative;
    box-sizing: border-box;
}
.r_list .list_more .ul01 .li01 .tab01 .img01{
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
.img01 .vertical{
    width: 105%;
    height: auto;
}
.ul01 .li01 .tab02{
    /* float: left; */
    width: calc(100% - 100px);
    display: inline-block;
    position: relative;
    letter-spacing: -0.03em;
    line-height: 1em;
    font-size: 1em;
}
.ul01 .li01 .tab02 .category{
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
.ul01 .li01 .tab02 .edit_date{
    display: inline-block;
    margin: 0 0 0 10px;
    font-size: 0.9em;
    color: #373737;
    font-weight: 300;
    box-sizing: border-box;
}
.tab02 .name{
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
.tab02 .name .age{
    font-size: 0.9em;
    box-sizing: border-box;
    color: #797979;
    font-weight: 300;
    padding: 0 0 0 0;
}
.tab02 .bar_star{
    display: inline-block;
    position: relative;
    width: 76px;
    height: 14.4px;
    box-sizing: border-box;
    background-color: #dedede;
    margin: 0 0 0 5px;
    vertical-align: top;
}
.bar_star .bg_star{
    position: absolute;
    left: 0;
    top: 0;
    display: inline-block;
    height: 14.4px;
    background-color: #f8b400;
}
.tab02 .area{
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
.ul01 .li01 .tab02 .pay{
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
.ul01 .li01 .tab02 .bar0101{
    display: inline-block;
    width: 1px;
    margin: 2px 3px 0 3px;
    background-color: #dedede;
    line-height: 0.9em;
}
.ul01 .li01 .bar01{
    float: left;
    width: 100%;
    clear: both;
    overflow: hidden;
    height: 0;
    border-top: 1px solid #eaeaea;
    margin: 15px 0 15px 0;
}
.ul01 .li01 .tab03{
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
.ul01 .li01 .tab03 .icon03{
    display: inline-block;
    background-color: #ffff93;
    padding: 1px 10px 1px 10px;
    border-radius: 20px;
    color: green;
    margin: 0 6px 0 0;
    font-weight: 400;
}
.ul01 .li01 .tab03 .text01{
    display: inline-block;
    background-color: #f4f4f4;
    padding: 1px 5px 1px 5px;
    color: #333333;
    margin: 0 5px 0 0;
    font-weight: 300;
}
</style>