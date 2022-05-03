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
                  <!-- <div class="ps-4 p-10">
                    <a href="javascript:void(0)" class="btn btn-sm btn-success btn-rounded text-white">Logout</a>
                  </div> -->
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
                <router-link to="/adminpage" class="sidebar-link waves-effect waves-dark sidebar-link" href="#" aria-expanded="false"><i class="mdi mdi-account-box-outline"></i>&nbsp;&nbsp;<span class="hide-menu">회원 관리</span></router-link>
              </li>
              <li class="sidebar-item">
                <router-link to="/admin_report" class="sidebar-link waves-effect waves-dark sidebar-link" href="#" aria-expanded="false"><i class="mdi mdi-alert-box"></i>&nbsp;&nbsp;<span class="hide-menu">신고 관리</span></router-link>
              </li>
              <li class="sidebar-item">
                <a class="sidebar-link waves-effect waves-dark sidebar-link" href="#" aria-expanded="false"><i class="mdi mdi-store"></i>&nbsp;&nbsp;<span class="hide-menu">스토어 관리</span></a>
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
        <div class="card-body">
                  <h5 class="card-title">Basic Datatable</h5>
                  <div class="table-responsive">
                    <div id="zero_config_wrapper" class="dataTables_wrapper container-fluid dt-bootstrap4"><div class="row"><div class="col-sm-12 col-md-6"><div class="dataTables_length" id="zero_config_length"><label>Show <select name="zero_config_length" aria-controls="zero_config" class="form-control form-control-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label></div></div><div class="col-sm-12 col-md-6"><div id="zero_config_filter" class="dataTables_filter"><label>Search:<input type="search" class="form-control form-control-sm" placeholder="" aria-controls="zero_config"></label></div></div></div><div class="row"><div class="col-sm-12"><table id="zero_config" class="table table-striped table-bordered dataTable" role="grid" aria-describedby="zero_config_info">
                      <thead>
                        <tr role="row"><th class="sorting" tabindex="0" aria-controls="zero_config" rowspan="1" colspan="1" aria-label="Name: activate to sort column ascending" style="width: 173px;">Name</th><th class="sorting_desc" tabindex="0" aria-controls="zero_config" rowspan="1" colspan="1" aria-label="Position: activate to sort column ascending" style="width: 276.2px;" aria-sort="descending">Position</th><th class="sorting" tabindex="0" aria-controls="zero_config" rowspan="1" colspan="1" aria-label="Office: activate to sort column ascending" style="width: 126.6px;">Office</th><th class="sorting" tabindex="0" aria-controls="zero_config" rowspan="1" colspan="1" aria-label="Age: activate to sort column ascending" style="width: 58.6px;">Age</th><th class="sorting" tabindex="0" aria-controls="zero_config" rowspan="1" colspan="1" aria-label="Start date: activate to sort column ascending" style="width: 113.8px;">Start date</th><th class="sorting" tabindex="0" aria-controls="zero_config" rowspan="1" colspan="1" aria-label="Salary: activate to sort column ascending" style="width: 108.4px;">Salary</th></tr>
                      </thead>
                      <tbody>
                      <tr role="row" class="odd">
                          <td class="">Prescott Bartlett</td>
                          <td class="sorting_1">Technical Author</td>
                          <td class="">London</td>
                          <td class="">27</td>
                          <td>2011/05/07</td>
                          <td>$145,000</td>
                        </tr><tr role="row" class="even">
                          <td class="">Gavin Cortez</td>
                          <td class="sorting_1">Team Leader</td>
                          <td class="">San Francisco</td>
                          <td class="">22</td>
                          <td>2008/10/26</td>
                          <td>$235,500</td>
                        </tr><tr role="row" class="odd">
                          <td class="">Gloria Little</td>
                          <td class="sorting_1">Systems Administrator</td>
                          <td class="">New York</td>
                          <td class="">59</td>
                          <td>2009/04/10</td>
                          <td>$237,500</td>
                        </tr><tr role="row" class="even">
                          <td class="">Lael Greer</td>
                          <td class="sorting_1">Systems Administrator</td>
                          <td class="">London</td>
                          <td class="">21</td>
                          <td>2009/02/27</td>
                          <td>$103,500</td>
                        </tr><tr role="row" class="odd">
                          <td class="">Tiger Nixon</td>
                          <td class="sorting_1">System Architect</td>
                          <td class="">Edinburgh</td>
                          <td class="">61</td>
                          <td>2011/04/25</td>
                          <td>$320,800</td>
                        </tr><tr role="row" class="even">
                          <td class="">Quinn Flynn</td>
                          <td class="sorting_1">Support Lead</td>
                          <td class="">Edinburgh</td>
                          <td class="">22</td>
                          <td>2013/03/03</td>
                          <td>$342,000</td>
                        </tr><tr role="row" class="odd">
                          <td class="">Sakura Yamamoto</td>
                          <td class="sorting_1">Support Engineer</td>
                          <td class="">Tokyo</td>
                          <td class="">37</td>
                          <td>2009/08/19</td>
                          <td>$139,575</td>
                        </tr><tr role="row" class="even">
                          <td class="">Olivia Liang</td>
                          <td class="sorting_1">Support Engineer</td>
                          <td class="">Singapore</td>
                          <td class="">64</td>
                          <td>2011/02/03</td>
                          <td>$234,500</td>
                        </tr><tr role="row" class="odd">
                          <td class="">Finn Camacho</td>
                          <td class="sorting_1">Support Engineer</td>
                          <td class="">San Francisco</td>
                          <td class="">47</td>
                          <td>2009/07/07</td>
                          <td>$87,500</td>
                        </tr><tr role="row" class="even">
                          <td class="">Zorita Serrano</td>
                          <td class="sorting_1">Software Engineer</td>
                          <td class="">San Francisco</td>
                          <td class="">56</td>
                          <td>2012/06/01</td>
                          <td>$115,000</td>
                        </tr></tbody>
                      <tfoot>
                        <tr><th rowspan="1" colspan="1">Name</th><th rowspan="1" colspan="1">Position</th><th rowspan="1" colspan="1">Office</th><th rowspan="1" colspan="1">Age</th><th rowspan="1" colspan="1">Start date</th><th rowspan="1" colspan="1">Salary</th></tr>
                      </tfoot>
                    </table></div></div><div class="row"><div class="col-sm-12 col-md-5"><div class="dataTables_info" id="zero_config_info" role="status" aria-live="polite">Showing 1 to 10 of 57 entries</div></div><div class="col-sm-12 col-md-7"><div class="dataTables_paginate paging_simple_numbers" id="zero_config_paginate"><ul class="pagination"><li class="paginate_button page-item previous disabled" id="zero_config_previous"><a href="#" aria-controls="zero_config" data-dt-idx="0" tabindex="0" class="page-link">Previous</a></li><li class="paginate_button page-item active"><a href="#" aria-controls="zero_config" data-dt-idx="1" tabindex="0" class="page-link">1</a></li><li class="paginate_button page-item "><a href="#" aria-controls="zero_config" data-dt-idx="2" tabindex="0" class="page-link">2</a></li><li class="paginate_button page-item "><a href="#" aria-controls="zero_config" data-dt-idx="3" tabindex="0" class="page-link">3</a></li><li class="paginate_button page-item "><a href="#" aria-controls="zero_config" data-dt-idx="4" tabindex="0" class="page-link">4</a></li><li class="paginate_button page-item "><a href="#" aria-controls="zero_config" data-dt-idx="5" tabindex="0" class="page-link">5</a></li><li class="paginate_button page-item "><a href="#" aria-controls="zero_config" data-dt-idx="6" tabindex="0" class="page-link">6</a></li><li class="paginate_button page-item next" id="zero_config_next"><a href="#" aria-controls="zero_config" data-dt-idx="7" tabindex="0" class="page-link">Next</a></li></ul></div></div></div></div>
                  </div>
                </div>
      </div>
    </div>
    </div>
</template>

<script>
export default {

}
</script>

<style>
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

    /* .navbar-brand{
        padding-top: 0.3359375rem;
        padding-bottom: 0.3359375rem;
        margin-right: 1rem;
        font-size: 1.09375rem;
        text-decoration: none;
        white-space: nowrap;
    } */
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
      top: 50px;
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
      flex: 1 1  auto;
    }
    .card .card-title{
      position: relative;
      font-weight: 600;
      margin-bottom: 10px;
    }
    .table-responsive{
        overflow-x:auto ;
    }
    .mb-0{
      margin-bottom: 0!important;
    }

    .table-responsive{
      overflow: auto;
    }

    .table{
      --bs-table-bs:transparent;
      --bs-table-striped-color:#3e5569;
      --bs-table-striped-bg:rgba(0,0,0,0.05);
      --bs-table-active-color:#3e5569;
      --bs-table-active-bg: rgba(0,0,0,0.1);
      --bs-table-hover-color: #3e5569;
      --bs-table-hover-bg: #f8f9fa;
      width: 100%;
      margin-bottom: 1rem;
      color: #3e5569;
      vertical-align: top;
      border-color: #e9ecef;
    }

    .table>thead{
      vertical-align: bottom;
    }
    thead, tr{
      border-color: inherit;
      border-style: solid;
      border-width: 0;
    }

  .table>:not(:last-child)>:last-child>*{
    border-bottom-color: #e9ecef;
  }
  .table .thead-light th{
    color: #4f5467;
    background-color: #e9ecef;
    border-color: #e9ecef;
  }
  .table th, .table thead th{
    font-weight: 500;
    }
  .mb-3{
    margin-bottom:1.4rem!important;
  }
  .customcheckbox{
    display: block;
    position: relative;
    padding-left: 24px;
    cursor: pointer;
    font-size: 22px;
    user-select: none;
  }
  .customcheckbox input{
    position: absolute;
    opacity: 0;
    cursor: pointer;
  }
  input{
    margin: 0;
    font-family: inherit;
    font-size: inherit;
    line-height: inherit;
  }
  .checkmark{
    position: absolute;
    top: 0;
    left: 0;
    height: 20px;
    width: 20px;
    background-color: #cdcdcd;
     border-radius: 6px;
   }
   .table .thead-light th{
     color: #4f5467;
     background-color: #e9ecef;
     border-color: #e9ecef;
   }
   .table th{
     padding: 1rem;
   }

   .table>tbody{
     vertical-align: inherit;
   }
   .customcheckbox input:checked ~ .checkmark:after{
     display: block;
   }
   .customcheckbox {
  display: block;
  position: relative;
  padding-left: 24px;
  font-weight: 100;
  /*margin-bottom: 12px;*/
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none; }

.customcheckbox input {
  position: absolute;
  opacity: 0;
  cursor: pointer; }

.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 20px;
  width: 20px;
  background-color: #CDCDCD;
  border-radius: 6px; }

.customcheckbox:hover input ~ .checkmark {
  background-color: #ccc; }

.customcheckbox input:checked ~ .checkmark {
  background-color: #2196BB; }

.checkmark:after {
  content: "";
  position: absolute;
  display: none; }

.customcheckbox input:checked ~ .checkmark:after {
  display: block; }

.customcheckbox .checkmark:after {
  left: 8px;
  top: 4px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg); }

.check-1 {
  margin-top: 12px;
  margin-left: 14px; }

</style>