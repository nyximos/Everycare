<template>
<div class="wrapper">
    <div class="px-4 py-5 my-5 text-center">
        <h1 class="display-5 fw-bold">Everycare</h1>
    <img class="d-block mx-auto mb-4" src="@/images/shopping-bag.png" alt="" width="144" height="114">
    
    <div class="col-lg-6 mx-auto">
      <h2 class="lead mb-4">결제가 <strong>정상적으로 완료</strong> 되었습니다.</h2>
      <p>주문하신 내역은 <strong>주문/배송조회</strong>에서 다시 확인이 가능합니다.</p>
      <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
        <button type="button" class="btn btn-primary btn-lg px-4 gap-3">결제 내역 확인</button>
        <button type="button" class="btn btn-outline-secondary btn-lg px-4" @click="goMain">홈으로</button>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
export default {
methods:{
     goMain() {
            if (this.$router.history.current.fullPath == '/') {
                console.log('go main');
            } else {
                this.$router.push('/');
            }
        },
}
}
</script>

<style scoped>
.wrapper{
    margin: 250px;
}
h1{
    color: #81C784;
}

.btn-primary{
background-color: #81C784;
border-color: #81C784;
}
strong{
    color: #81C784;
}
px-4 py-5 my-5{
    text-align: center;
}

























/* table_info_pay
.table_info_pay{width:100%;}
.table_info_pay col.subject{width:170px;}
.table_info_pay th,.table_info_pay td{padding-left:20px;}
.table_info_pay td {position:relative;}
.table_info_pay .caution_list{margin:3px 0 4px;}
.table_info_pay .radio_label{display:inline-block;min-width:75px;margin-right:25px;}
.table_info_pay .radio_label .delivery_text{margin-left:6px}
.table_info_pay .radio_label input:checked + .delivery_text{color:#333}
.table_info_pay .radio_label input:disabled + .delivery_text{color:#919191}
.table_info_pay .check_label{margin-right:15px;}
.table_info_pay input[type=text]{height:20px;line-height:20px;padding:0 4px;outline:0;}
.table_info_pay input[type=text].input_date{padding:0 20px 0 4px;}
.table_info_pay .input_basic{width:195px;}
.table_info_pay .input_memo{width:559px;}
.table_info_pay .slct_rqst + .input_memo {margin-top:10px;}
.table_info_pay .input_num{width:49px;}
.table_info_pay .input_num_long{width:89px;}
.table_info_pay .input_num_year{width:80px;}
.table_info_pay .sel_phone{padding-left:4px;width:60px;height:22px;-webkit-appearance:none;appearance:none;-moz-appearance:none;}
.table_info_pay .btn_basic,.table_info_pay .btn_basic_gray{vertical-align:bottom;}
.table_info_pay .address_before{margin-bottom:10px;}
.table_info_pay .address_num,.table_info_pay .address_opt{display:inline-block;*display:inline;vertical-align:middle;}
.table_info_pay .btn_find_address{margin:0 16px 0 6px;}
.table_info_pay .input_address_1{width:275px;margin-right:0px;}
.table_info_pay .input_address_2{width:300px;}
.table_info_pay .btn_bill_faq{display:block;width:81px;font-weight:normal;}
.table_info_pay .sel_type{width:103px;height:22px;-webkit-appearance:none;appearance:none;-moz-appearance:none;padding-left:4px;}
.table_info_pay .certi_input{margin-bottom:15px;}
.table_info_pay .sub_bill{margin-top:12px;}
.table_info_pay .sub_bill .caution_list{margin-bottom:2px;}
.table_info_pay .sub_info_table{margin-bottom:17px;}
.table_info_pay p{line-height:1.7;}
.table_info_pay .link_ref{font-size:11px;color:#553de8;}
.table_info_pay .agreement_box{font-size:12px;line-height:19px;width:auto;height:62px;padding:10px;border:1px solid;border-color:#cdcece #e8e8e8 #e8e8e8 #cdcece;overflow-y:auto;margin:8px 0;}
.table_info_pay .agreement_box .agr_em_txt{font-size:15px;font-weight:bold;text-decoration:underline}
.table_info_pay textarea.agreement_box{display:block;width:99%;padding:4px .5%;resize:vertical;}
.table_info_pay .check_agree{font-size:12px;margin-top:4px;}
.table_info_pay .sub_span{background:url(//img.danawa.com/new/billing/img/ico_text.png) no-repeat 0 -350px;padding-left:7px;margin-left:3px;}
.table_info_pay .deli_caution{padding:10px 5px 10px 20px;position:relative;font-size:11px;color:#666;letter-spacing:-0.07em;}
.table_info_pay .deli_caution strong {color:#553de8;}
.table_info_pay .deli_caution .strong_caution{font-weight:bold;}
.table_info_pay .deli_caution .strong_caution strong{color:#553de8;}
.table_info_pay .deli_caution .strong_caution em{font-weight:normal;font-style:normal;}
.table_info_pay .deli_caution .check_agree{display:inline-block;margin:10px 0 6px;}
.table_info_bill{width:100%;}
.table_info_bill col.sub_subject{width:155px;}
.table_info_bill td{padding-left:9px;}
.table_info_bill input[type=text].input_sub_info{width:100%;padding:0;}
.pay_success{border:4px solid #e9e9e9;height:102px;position:relative;margin:40px 0 31px;}

.pay_success .success_text.type--previewer{background-image:url(//img.danawa.com/new/shop_danawa/img/mypage/bg_pay_success3.png);}
.pay_success .success_text p{width:1px;white-space:nowrap;margin-left:-10px;overflow:hidden;text-indent:-300px;}
.odr_num {position:relative;top:77px;text-align:center;font-size:14px;letter-spacing:-0.1em;} */









</style>