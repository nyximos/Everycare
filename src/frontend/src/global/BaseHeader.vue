<template>
    <!-- <header>
        <div class="header">
            <div class="logo" @click="goMain()">Everycare</div>
            <div class="list">
                <v-btn text @click="match">케어매칭</v-btn>
                <v-btn text>케어노트</v-btn>
                <v-btn text to="/store">케어스토어</v-btn>
            </div>
            <div>
                <div>
                    <div v-if="this.$store.state.userStore.id !== ''">
                        <v-btn text v-on:click.native="signout()" to="/">로그아웃</v-btn>
                        <v-btn text v-on:click.native="mypage()" to="/mypage">{{ this.$store.state.userStore.id }}님</v-btn>
                    </div>
                    <div v-else>
                        <v-btn text to="/login">로그인</v-btn>
                        <v-btn text to="/signup">회원가입</v-btn>
                    </div>
                </div>
            </div>
        </div>
    </header> -->

    <div class="pt-15">
        <v-app-bar color="green lighten-2" dark fixed>
            <v-toolbar-title @click="goMain" class="logo">Everycare</v-toolbar-title>

            <v-spacer></v-spacer>
            <div class="list">
                <v-btn text>케어매칭</v-btn>
                <v-btn text>케어노트</v-btn>
                <v-btn text to="/store">케어스토어</v-btn>


            </div>

            <v-spacer></v-spacer>
           <div class="btn-group dropstart">
  <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false" style="width:80px;height:36px; background-color: #81C784; border: #81C784;">
    알림 
  </button>
  <ul class="dropdown-menu" style="width:280px;">
  
    <a class="navbar-brand" style="text-align: center; color:#81C784;">Everycare</a>
  
    <li  style="text-align: left;" v-for="(p, index) in profiles" :key="index" @click="move(p)" > 케어시터
                                    <strong style="color:#81C784;">'{{ p.careSitter.name }}'</strong >님이 구인글  <strong style="color:#81C784;">'{{ p.jobOffer.title }}'</strong>글에 계약 신청을 했습니다<hr></li>
    <!-- Dropdown menu links -->
  </ul>
</div>
&nbsp;
            <!-- <div class="menubar">
                <ul>
                    
                    <li><a href="#" id="current">알림</a>
                        <ul>
                            <li>
                               
                            </li>
                            <li v-for="(p, index) in profiles" :key="index"><a @click="move(p)">케어시터
                                    '{{ p.careSitter.name }}'님이 구인글 '{{ p.jobOffer.title }}' <br> 글에 계약 신청을 했습니다
                                    <hr>
                                </a></li>
                            <li v-for="(p,index) in profiles" :key="index"><a href="#">케어시터 '{{p.careSitter.name}}'님이 구인글 '{{p.jobOffer.title}}' 계약 신청을 했습니다</a></li>
                        </ul>
                    </li>
                </ul>
            </div> -->
            <div>

                <div>

                    <div v-if="this.$store.state.userStore.id !== ''">


                        <v-btn text v-on:click.native="signout()" to="/">로그아웃</v-btn>
                        <v-btn text v-on:click.native="mypage()" to="/mypage">{{ this.$store.state.userStore.id }}님
                        </v-btn>

                    </div>
                    <div v-else>

                        <v-btn text to="/login">로그인</v-btn>
                        <v-btn text to="/signup">회원가입</v-btn>

                    </div>
                </div>
            </div>

        </v-app-bar>

    </div>
    
</template>

<script>
import $ from 'jquery';

export default {

    data() {
        return {
            profiles: [],
        }
    },
    mounted() {
        this.$http
            .get('/api/recruitions/announce', {
                withCredential: true
            })
            .then((res) => {
                console.log(res.data.body)
                this.profiles = res.data.body

            }).catch((err) => {
                console.log(err)
            });
    },

     /* data(){
        return{
             
             : [
          {
            title: '케어노트(시터용)', 
          },
          {
            title: '케어노트(멤버용)',
          }
        ],
        }
    },
    */

    methods: {
        move(p) {
            
            this.$router.push({
            name: 'jobapplication',
            params: {
              contentId: p.careSitter.id
            }
          })
            
            
        },
        goMain() {
            if (this.$router.history.current.fullPath == '/') {
                console.log('go main');
            } else {
                this.$router.push('/');
            }
        },
        signout() {
            this.$store.commit('userStore/clearUsername');
            $.ajax({
                url: '/api/token',
                type: 'DELETE',
                cache: false,
                contentType: false,
                processData: false,
                async: false,
            }),
                (location.href = '/');
        },
        // id, 이름, 권한 응답을 넣어준다
        log() {
            this.$http
                .get('/api/user', {
                    withCredentials: true,
                })
                .then((res) => {
                    // state에 저장
                    console.log(this.$store.state.userStore.id);
                })
                .catch((err) => {
                    console.log(err);
                });
        },

        match(){
            this.$router.push({name:'caresitters'})

        }
    }
}

</script>
<style scoped>
.menubar {
    border: none;
    border: 0px;
    margin: 0px;
    padding: 0px;
    font: 67.5% "Lucida Sans Unicode", "Bitstream Vera Sans", "Trebuchet Unicode MS", "Lucida Grande", Verdana, Helvetica, sans-serif;
    font-size: 14px;
    font-weight: bold;
}

.menubar ul {
    background: #81C784;
    height: 50px;
    list-style: none;
    margin: 0;
    padding: 0;
}

.menubar li {
    float: left;
    padding: 0px;
}

.menubar li a {
    background: #81C784;
    color: #ffffff;
    display: block;
    font-weight: normal;
    line-height: 50px;
    margin: 0px;
    padding: 0px 25px;
    text-align: center;
    text-decoration: none;
}

.menubar li a:hover,
.menubar ul li:hover a {
    background: #81C784;
    color: #000000;
    text-decoration: none;
}

.menubar li ul {
    background: rgb(109, 109, 109);
    display: none;
    /* 평상시에는 드랍메뉴가 안보이게 하기 */
    height: auto;
    padding: 0px;
    margin: 0px;
    border: 0px;
    position: absolute;
    width: 500px;
    z-index: 200;
    /*top:1em;
/*left:0;*/
}

.menubar li:hover ul {
    display: block;
    /* 마우스 커서 올리면 드랍메뉴 보이게 하기 */
}

.menubar li li {
    background: #FFFFFF;
    display: block;
    float: none;
    margin: 0px;
    padding: 0px;
    width: 500px;
}

.menubar li:hover li a {
    background: none;
}

.menubar li ul a {
    display: block;
    height: 100px;
    font-size: 12px;
    font-style: normal;
    margin: 0px;
    padding: 0px 10px 0px 15px;
    text-align: left;
}



.menubar p {
    clear: left;
}





    
</style>
