<template>
<div>
    <div class="preloader" style="display: none;">
      <div class="lds-ripple">
        <div class="lds-pos"></div>
        <div class="lds-pos"></div>
      </div>
    </div>
    <div id="main-wrapper" data-layout="vertical" data-navbarbg="skin5" data-sidebartype="mini-sidebar" data-sidebar-position="absolute" data-header-position="absolute" data-boxed-layout="full">
     <header class="topbar" data-navbarbg="skin5">
        <nav class="navbar top-navbar navbar-expand-md navbar-dark">
          <div class="navbar-header" data-logobg="skin5">
            <a class="navbar-brand" href="index.html">
              <b class="logo-icon ps-2">
                <img src="@/assets/main.png" alt="homepage" class="light-logo" width="25">
              </b>
              <span class="logo-text ms-2">
                <img src="#" alt="homepage" class="light-logo">
              </span>
            </a>
            <a class="nav-toggler waves-effect waves-light d-block d-md-none" href="javascript:void(0)"><i class="ti-menu ti-close"></i></a>
          </div>
          <div class="navbar-collapse collapse" id="navbarSupportedContent" data-navbarbg="skin5">
            <ul class="navbar-nav float-start me-auto">
              <li class="nav-item d-none d-lg-block">
                <a class="nav-link sidebartoggler waves-effect waves-light" href="javascript:void(0)" data-sidebartype="mini-sidebar"><i class="mdi mdi-menu font-24"></i></a>
              </li>
            </ul>
            <ul class="navbar-nav float-end">
              <li class="nav-item dropdown">
                <a class="
                    nav-link
                    dropdown-toggle
                    text-muted
                    waves-effect waves-dark
                    pro-pic
                  " href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                  <img src="@/assets/profileuser.png" alt="user" class="rounded-circle" width="31">
                </a>
                <ul class="dropdown-menu dropdown-menu-end user-dd animated" aria-labelledby="navbarDropdown">
                  <a class="dropdown-item" href="javascript:void(0)"><i class="mdi mdi-account me-1 ms-1"></i> My Profile</a>
                  <a class="dropdown-item" href="javascript:void(0)"><i class="mdi mdi-wallet me-1 ms-1"></i> My 1</a>
                  <a class="dropdown-item" href="javascript:void(0)"><i class="mdi mdi-email me-1 ms-1"></i> email</a>
                  <div class="dropdown-divider"></div>
                  
                  <a class="dropdown-item" href="javascript:void(0)"><i class="fa fa-power-off me-1 ms-1"></i> Logout</a>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>
      <aside class="left-sidebar" data-sidebarbg="skin5">
        <div class="scroll-sidebar">
          <nav class="sidebar-nav">
            <ul id="sidebarnav" class="pt-4 in">
              <li class="sidebar-item">
                <a class="sidebar-link waves-effect waves-dark sidebar-link" aria-expanded="false"><i class="mdi mdi-account-box-outline"></i>&nbsp;&nbsp;<button class="hide-menu" @click="member()">회원 관리</button></a>
              </li>
              <li class="sidebar-item">
                <router-link to="/admin_report" class="sidebar-link waves-effect waves-dark sidebar-link"  aria-expanded="false"><i class="mdi mdi-alert-box"></i>&nbsp;&nbsp;<span class="hide-menu">신고 관리</span></router-link>
              </li>
              <li class="sidebar-item">
                <a class="sidebar-link waves-effect waves-dark sidebar-link" aria-expanded="false"><i class="mdi mdi-store"></i>&nbsp;&nbsp;<button class="hide-menu" @click="store()">스토어 관리</button></a>
              </li>
              <li class="sidebar-item selected">
                <a class="sidebar-link waves-effect waves-dark sidebar-link active" href="#" aria-expanded="false"><i class="mdi mdi-file-outline"></i>&nbsp;&nbsp;<span class="hide-menu">케어시터 자격증 승인</span></a>
              </li>
              <li class="sidebar-item">
                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="#" aria-expanded="false"><i class="mdi mdi-filter-outline"></i>&nbsp;&nbsp;<span class="hide-menu">케어 활동 분류 관리</span></a>
              </li>
              <li class="sidebar-item">
                <a class="sidebar-link has-arrow waves-effect waves-dark" href="#" aria-expanded="false"><i class="mdi mdi-information"></i>&nbsp;&nbsp;<span class="hide-menu">건강 정보 관리</span></a>
              </li>
            </ul>
          </nav>
        </div>
      </aside>
      <div class="page-wrapper">
        <div class="page-breadcrumb">
          <div class="row">
            <div class="col-12 d-flex no-block align-items-center">
              <h4 class="page-title">회원 관리</h4>
              <div class="ms-auto text-end">
                <nav aria-label="breadcrumb">
                  <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="#">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">
                      관리자 페이지
                    </li>
                  </ol>
                </nav>
              </div>
            </div>
          </div>
        </div>
        <div class="container-fluid">
          <div class="row">
            <div class="col-12">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title mb-0">회원</h5>
                </div>
                <div class="table-responsive custom-table-responsive">

        <table class="table custom-table" show-select >
          <thead>
            <tr>  

              <th scope="col">
                <label class="control control--checkbox">
                  <input type="checkbox" @click="selectAll" v-model="allSelected">
                  <div class="control__indicator"></div>
                </label>
              </th>
              
              <th scope="col">아이디</th>
              <th scope="col">이름</th>
              <th scope="col">권한</th>
              <th scope="col">성별</th>
              <th scope="col">생년월일</th>
              <th scope="col">전화번호</th>
              <th scope="col">이메일</th>
              <th scope="col">회원가입일</th>
              <th scope="col">활동상태</th>
              <th scope="col">관리자 권한</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(p,index) in profiles" :key="index">
              <th scope="row">
                <label class="control control--checkbox">
                  <input type="checkbox" />
                  <div class="control__indicator"></div>
                </label>
              </th>
              
              <td>
                {{p.id}}
              </td>
              <td><a href="#">{{p.name}}</a></td>
              <td>
                {{p.role}}
                
              </td>
              <td>{{p.gender}}</td>
              <td>{{p.birth}}</td>
              <td>{{p.phone}}</td>
              <td>{{p.email}}</td>
              <td>{{p.createdAt}}</td>
              <td>{{p.activityStatus}}</td>
              <td><button class="custom-btn btn-11" @click="click(p)">관리자등록</button>
              <button class="custom-btn btn-11" @click="drop(p)">관리자삭제</button>
              </td>
            </tr>
            
            
            
            
            
          </tbody>
        </table>
      </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</div>
</template>

<script>

  export default {
  
    data () {
      return {
        allSelected: false,
        selected: [],
        profiles: [],
        id: this.$route.params.memberId,
      }
    },
    methods: {
      member(){
      this.$router.push({
            path: '/admin/members'
          })
      },
      store(){
        this.$router.push({
          path: '/admin/stores'
        })
      },
      selectAll: function() {
            this.userIds = [];

            if (this.allSelected) {
                for (user in this.users) {
                    this.userIds.push(this.users[user].id.toString());
                }
            }
        },
        select: function() {
            this.allSelected = false;
        },
        drop(p){
          this.$router.push({ params: { memberId: p.id }})
          this.$http
          .patch(`/api/admin/members/${p.id}`, {
            withCredentials: true
          })
          .then((res)=> {
            console.log(res);
          })
          .catch((err)=>{
            console.log(err)
          })
          alert('관리자 권한이 해제 되었습니다.')        
       location.reload();
        },
        
        click(p){
          this.$router.push({ params: { memberId: p.id}})
          this.$http
          .post(`/api/admin/members/${p.id}`, {
            withCredentials: true
          })
          .then((res)=> {
            console.log(res);
          })
          .catch((err)=>{
            console.log(err)
          })
          alert('관리자 권한이 등록 되었습니다.')        
       location.reload();
        },
      
    },
    mounted() {
      this.$http
      .get('/api/admin/members', {
        withCredentials: true
      })
      .then((res)=>{
        console.log(res.data.body)
        this.profiles = res.data.body  
      })
      .catch((err)=>{
        alert(err);
        console.log(err)
      })
    },
    
  }
  
</script>

<style scoped>
.btn-11 {
  overflow: hidden;
  transition: all 0.3s ease;
  font-size:12px;
}
.btn-11:hover {
   background: #81C784;
  color: #fff;
}
.btn-11:before {
    position: absolute;
    content: '';
    display: inline-block;
    top: -180px;
    left: 0;
    width: 30px;
    height: 100%;
    background-color: #fff;
    animation: shiny-btn1 3s ease-in-out infinite;
    font-size: 10;
}
.btn-11:active{
  box-shadow:  4px 4px 6px 0 rgba(255,255,255,.3),
              -4px -4px 6px 0 rgba(116, 125, 136, .2), 
    inset -4px -4px 6px 0 rgba(255,255,255,.2),
    inset 4px 4px 6px 0 rgba(0, 0, 0, .2);
}

button {
  margin: 3px;
  outline: none;
  
}
.custom-btn {
  width: 130px;
  height: 40px;
  padding: 10px 25px;
  border: 2px solid #000;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
}
    *,:after,:before{
        box-sizing: border-box;
    }


    .preloader{
        width: 100%;
        height: 100%;
        top: 0px;
        position: fixed;
        z-index: 99999;
        background: #fff;
        overflow: hidden;
    }

     /* .lds-ripple {
      display: inline-block;
      position: relative;
      width: 64px;
      height: 64px;
      position: absolute;
      top: calc(50% - 3.5px);
      left: calc(50% - 3.5px)
    }

    .lds-ripple .lds-pos {
      position: absolute;
      border: 2px solid #2962ff;
      opacity: 1;
      border-radius: 50%;
      animation: lds-ripple 1s cubic-bezier(0, 0.1, 0.5, 1) infinite
    } */

    #main-wrapper{
        width: 100%;
        overflow: hidden;
    }

    #main-wrapper .topbar[data-navbarbg=skin5]{
        background: #1f262d;
    }
    
    #main-wrapper .topbar .navbar-collapse[data-navbarbg=skin5]{
        background: #1f262d;
    }

    .topbar{
        position: relative;
        z-index: 50;
        box-shadow: 1px 0px 7px rgb(0,0,0/5%);
        background: #fff;
        height: 64px;
    }
    .navbar{
        position: relative;
        display: flex;
        flex-wrap: wrap;
        align-items: center;
        justify-content: space-between;
        padding-top: 0.5rem;
        padding-bottom: 0.5rem;
    }
    .topbar .top-navbar{
        min-height: 64px;
        padding: 0px;
    }

    @media(min-width:768px){
      .navbar-expand-md{
        flex-wrap: nowrap;
        justify-content: flex-start;
      }
    }

    @media(min-width: 768px){
      #main-wrapper[data-layout=vertical][data-sidebartype=mini-sider] .topbar .top-navbar .navbar-brand{
        width: 65px;
      }
    }

    /* @media(min-width:768px){
      #main-wrapper[data-layout=vertical][data-sidebartype=mini-sidebar] .topbar .top-navbar .navbar-header{
        width: 65px;
      }
    } */
    #main-wrapper .topbar .top-navbar .navbar-header[data-logobg=skin5]{
        background: #1f262d;
    }

    @media(max-width:767px){
    .topbar .top-navbar .navbar-header{
     display: flex;
     align-items: center;
     width: 100%;
     justify-content: space-between;   
      }
    }

    .topbar .top-navbar .navbar-header{
        line-height:64px
    }

  .topbar .top-navbar .navbar-header .navbar-brand{
    width: 250px;
    display: flex;
    align-items: center;
    margin: 0px;
    padding: 0 10px 0 10px;
  }

    .navbar-dark .navbar-brand{
        color: #fff;
    }
    .topbar .top-navbar .navbar-header .navbar-brand .logo-icon{
        margin-right: 5px;
    }

    .ps-2{
        padding-left: 0.5rem!important;
    }

    b,strong{
        font-weight: bolder;
    }
    img{
        vertical-align: middle;
        width: 35px;
    }

    .ms-2{
      margin-left: 0.5rem!important;
    }
    
    *{
      outline: none;
    }
    
    @media(min-width:768px){
      #main-wrapper[data-layout=vertical][data-sidebartype=mini-sidebar] .topbar .top-navbar .navbar-header .logo-text{
        display: none;
      }
    }

    .topbar .nav-toggler{
      color: #fff;
      padding: 0 15px;
    }

    .waves-effort{
      position: relative;
      cursor: pointer;
      /* display: inline-block; */
      overflow: hidden;
      /* -webkit-user-select: none; */
      user-select: none;
      -webkit-tap-highlight-color: transparent;
      vertical-align: middle;
      z-index: 1;
      will-change: opacity,transform;
      transition: all 0.1s ease-out;
    }

    @media(min-width:768px){
      .d-md-none{
        display: none!important;
      }
    }

    .d-block{
      display: block!important;
    }

    #navbarSupportedContent{
        background: #141619!important;
    }

    a{
      text-decoration: none;
    }

    [class^="ti-"],[class*="ti-"]{
      speak:none;
      font-style: normal;
      font-weight: normal;
      font-variant: normal;
      text-transform: none;
      line-height: 1;
      -webkit-font-smoothing:antialiased;
    }


    .topbar .navbar-collapse{
        padding: 0px 10px 0 0;
    }

    @media(min-width:768px){
    .navbar-expand-md .navbar-collapse{
        display:flex!important
      }
    }

    .collapse:not(.show){
        display: none;
    }

    .navbar-collapse{
        align-items: center;
        width: 100%;
    }
    @media(min-width:768px){
      .navbar-expand-md .navbar-nav{
        flex-direction: row;
      }
    }
      .me-auto{
        margin-right: auto!important;
      }
      .float-start{
        float: left!important;
      }
      .navbar-nav{
        display: flex;
        flex-direction: column;
        padding-left: 0;
        margin-bottom: 0;
        list-style: none;
      }
      ul{
        margin-top: 0;
        margin-bottom: 1rem;
      }
    .d-none{
      display: none!important;
    }
    li{
      display: list-item;
      text-align: -webkit-match-parent;
    }

    .topbar .top-navbar .navbar-nav>.nav-item>.nav-link{
      padding: 0px 15px;
      font-size: 0.875rem;
      line-height: 64px;
      height: 64px;
    }

    .float-end{
      float: right!important;
    }

    .dropdown{
      position: relative;
    }

    .navbar-dark .navbar-nav .nav-link{
      color: rgba(255,255,255,0.8);
    }

    @media(min-width: 768px){
      .navbar-expand-md .navbar-nav .nav-link{
        padding-right: 0.5rem;
        padding-left: 0.5rem;
      }
    }

    .navbar-nav .nav-link{
      padding-right: 0;
      padding-left: 0;
    }

    .rounded-circle{
      border-radius: 50%!important;
    }

    @media(min-width:768px){
      .navbar-expand-md .navbar-nav .dropdown-menu{
        position: absolute;
      }
    }

    .topbar .dropdown-menu{
     padding-top: 0px;
     border: 0px;
     box-shadow: 1px 1px 15px rgba(0,0,0, 0.1); 
    }
    .topbar .user-dd{
      min-width: 280px;
    }

    .navbar-nav .dropdown-menu{
      position: static;
    }

    .animated{
      animation-duration: 1s;
      animation-fill-mode: both;
    }

    .dropdown-menu-end{
      --bs-position:end;
      right: 0;
      left: auto;
    }

    .dropdown-menu{
      /* position: absolute; */
      top: 100%;
      /* left: 0; */
      z-index: 1000;
      /* min-width: 10rem; */
      padding: 0.5rem 0;
      margin: 0.125rem 0 0;
      font-size: 0.875rem;
      color: #3e5569;
      text-align: left;
      list-style: none;
      background-color: #fff;
      background-clip: padding-box;
      /* border: 1px solid #e9ecef; */
      border-radius: 2px;
    }

    .dropdown-item{
      display: block;
      width: 100%;
      padding: 0.65rem 1rem;
      clear: both;
      font-weight: 400;
      color: #212529;
      text-align: inherit;
      text-decoration: none;
      white-space: nowrap;
      background-color: transparent;
      border: 0;
    }

    @media(max-width:767px){
      .topbar .top-navbar .dropdown-menu{
        position: absolute;
        width: 100%;
      }
    }

    .dropdown-divider{
      height: 0;
      margin: 0.5rem 0;
      overflow: hidden;
      border-top: 1px solid #f8f9fa;
    }

    .ms-1{
      margin-left: 0.25rem!important;
    }
    .me-1{
      margin-right: 0.25rem!important;
    }
    html[dir=ltr]body .p-10{
      padding: 10px;
    }
    ps-4{
      padding-left: 1.5rem!important;
    }
    .btn-rounded.btn-sm{
      padding: 0.25rem 0.5rem;
      font-size: 12px;
    }
    .btn-rounded{
      border-radius: 60px;
      padding: 7px 18px;
    }
    .text-white{
      color: #fff!important;
    }
    .btn-success{
      color: #000;
      background-color: #28b779;
      border-color: #28b779;
    }
    .btn{
      display: inline-block;
      font-weight: 400;
      line-height: 1.5;
      /* color: #3e5569; */
      text-align: center;
      text-decoration: none;
      vertical-align: middle;
      cursor: pointer;
      /* -webkit-user-select: none; */
      user-select: none;
      /* background-color: transparent; */
      border: 1px solid transparent;
      /* padding: 0.375rem 0.75rem; */
      font-size: 0.875rem;
      /* border-radius: 2px; */
      transition: color 0.15s ;
    }

    @media(max-width:767px){
      .topbar .top-navbar .navbar{
        flex-direction: row;
      }
    }

    @media(max-width:767px){
      #main-wrapper[data-sidebartype=mini-sidebar] .left-sidebar{
        left:-250px
      }
    }
    #main-wrapper .left-sidebar[data-sidebarbg=skin5]{
      background: #1f262d;
    }
    .left-sidebar{
      position: absolute;
      width: 250px;
      height: 100%;

      z-index: 10;
      padding-top: 64px;
      /* background: #fff; */
      box-shadow: 1px 0px 20px rgba(0,0,0, 0.08);
    }
    
    .scroll-sidebar{
      height: calc(100%-20px);
      position: relative;
    }
    nav{
      display: block;
    }

    #main-wrapper .left-sidebar[data-sidebarbg=skin5] ul{
      background: #1f262d;
    }
    
    .sidebar-nav ul{
      margin: 0px;
      padding: 0px;
    }

    .pt-4{
      padding-top: 1.5rem!important;
    }
    .sidebar-nav ul li{
      list-style: none;
    }

    .sidebar-nav ul .sidebar-item .sidebar-link{
      color: #fff;
      padding: 14px 15px;
      display: flex;
      white-space: nowrap;
      align-items: center;
      line-height: 25px;
      opacity: 0.6;
    }

    @media(min-width: 768px){
      #main-wrapper[data-layout=vertical][data-sidebartype=mini-sidebar] .page-wrapper{
        margin-left: 250px;
      }
    }
    .page-wrapper{
      background: #eeeeee;
      position: relative;
    }

    .page-breadcrumb{
      padding: 20px 20px 0 20px;
    }

    .row{
      --bs-gutter-x:20px;
      --bs-gutter-y:0;
      display: flex;
      flex-wrap: wrap;
      margin-top: calc(var(--bs-gutter-y) * -1);
      margin-right: calc(var(--bs-gutter-x) / -2);
      margin-left: calc(var(--bs-gutter-x) /-2);
    }
    .align-items-center{
      align-items: center!important;
    }
    .d-flex{
      display: flex!important;
    }
    .col-12{
      flex: 0 0 auto;
      width: 100%;
      
    }
    .row>*{
      /* flex-shrink: 0; */
      /* width: 100%; */
      max-width: 100%;
      padding-right: calc(var(--bs-gutter-x) /2);
      padding-left: calc(var(--bs-gutter-x) /2);
      margin-top: var(--bs-gutter-y);
    }

    .page-breadcrumb .page-title{
      margin-bottom: 0px;
    }
    h4{
      font-size: 1.125rem;
    }

    .text-end{
      text-align: right!important;
    }
    .ms-auto{
      margin-left: auto!important;
    }

    .page-breadcrumb .breadcrumb{
      padding: 0px;
      margin: 0px;
      background: transparent;
      font-size: 12px;
    }
    .breadcrumb{
      display: flex;
      flex-wrap: wrap;
      list-style: none;
    }
    .breadcrumb-item.active{
      color: #6c757d;
    }
    .breadcrumb-item+.breadcrumb-item{
      padding-left: 0.5rem;
    }

    .page-breadcrumb .breadcrumb .breadcrumb-item+.breadcrumb-item:before{
      content: ">";
      color: #ced4da;
      font-size: 11px;
    }
    .breadcrumb-item+.breadcrumb-item:before{
      float: left;
      padding-right: 0.5rem;
    }
    .page-wrapper>.container-fluid{
      padding: 20px;
      min-height: calc(100vh - 180px);
    }
    .container-fluid{
      width: 100%;
      margin-right: auto;
      margin-left: auto;
    }
    .card{
      margin-bottom: 20px;
    }
    .card{
      position: relative;
      display: flex;
      flex-direction: column;
      min-width: 0;
      word-wrap: break-word;
      background-color: #fff;
      background-clip: border-box;
      border: 0px solid transparent;
      border-radius: 0px;
    }

    .card-body{
      padding: 1.25rem;
      flex: 1 1 auto;
    }
    .card .card-title{
      position: relative;
      font-weight: 600;
    }
    .mb-0{
      margin-bottom: 0!important;
    }

</style>