<template>
    <div id="content" class="content cont_pay_done">


        <div class="pay_success">

            <div class="success_text">
                <p>주문이 <strong>정상적으로 완료</strong>되었습니다.</p>
                <p>주문하신 내역은 주문/배송조회에서 다시 확인이 가능합니다.</p>
            </div>

        </div>



        <div class="sect">

            <div class="sect_header">

            </div>



            <table class="table table-striped">
                ...
            </table>


        </div>

        <div class="order_submit">
            <a href="" class="btnset_pay btn_view_delivery"><span>구매내역확인</span></a>
            <a href="" class="btnset_pay btn_keep_shop"><span>홈으로</span></a>
        </div>

    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
@charset 'utf-8';

/* Initialize */










.info_table th,
.info_table td {
    border-top: 1px solid #e9e9e9;
    padding: 15px 5px 12px;
}

.info_table th {
    background: #f6f6f6;
    font-weight: bold;
}

.info_table tr:first-child th,
.info_table tr:first-child td {
    border-top-width: 0;
}

.info_table tr:first-child {
    border-top: 1px solid #666;
}

.info_table tbody th {
    text-align: left;
}

/* Sub Info Table */
.sub_info_table {
    table-layout: fixed;
    border-collapse: collapse;
    border-spacing: 0;
    font-size: 11px;
    border: 1px solid #e9e9e9;
}

.sub_info_table tr:first-child {
    border: 1px solid #e9e9e9;
}

.sub_info_table th,
.sub_info_table td {
    padding: 8px 9px 6px;
}

/* Qunatity Set */
.qnt_set {
    overflow: hidden;
    zoom: 1;
}

.qnt_set .input_qnt {
    float: left;
    text-align: center;
    width: 25px;
    height: 19px;
    line-height: 19px;
    border-right-width: 0;
}

.qnt_set .edit_number {
    float: left;
    border-top-width: 0;
    zoom: 1;
    line-height: 9px;
    margin-right: 2px;
}

.qnt_set .btn_qnt_up,
.qnt_set .btn_qnt_down {
    display: block;
    position: relative;
    width: 11px;
    height: 11px;
    border: none;
    cursor: pointer;
    padding: 0;
    margin: 0;
    overflow: hidden;
    border: 1px solid #d1d1d1;
    text-indent: -100px;
    background: url(//img.danawa.com/new/billing/img/bg_quantity.png);
}

.qnt_set .btn_qnt_up {
    background-position: 0 0;
}

.qnt_set .btn_qnt_down {
    background-position: 0 -10px;
    border-top: none;
    height: 10px;
}

.qnt_set .btn_apply_num {
    vertical-align: top;
    width: 34px;
    height: 20px;
    background: url(//img.danawa.com/new/billing/img/bg_quantity.png) -20px 0;
    border: none;
    text-indent: -100px;
    overflow: hidden;
    cursor: pointer;
}




/* Payment Style */
.sub_header .head_cart span {
    background-position: 0 -300px;
    width: 95px;
}

.sub_header .head_order span {
    background-position: 0 -350px;
    width: 108px;
}

.sub_header .head_done span {
    background-position: -150px -350px;
    width: 108px;
}

.order_sn,
.table_order .order_code,
.table_order .stat_info {
    color: #553de8;
}



.table_info_pay .sel_phone {
    padding-left: 4px;
    width: 60px;
    height: 22px;
    -webkit-appearance: none;
    appearance: none;
    -moz-appearance: none;
}

.table_info_pay .btn_basic,
.table_info_pay .btn_basic_gray {
    vertical-align: bottom;
}

.table_info_pay .address_before {
    margin-bottom: 10px;
}

.table_info_pay .address_num,
.table_info_pay .address_opt {
    display: inline-block;
    *display: inline;
    vertical-align: middle;
}

.table_info_pay .btn_find_address {
    margin: 0 16px 0 6px;
}

.table_info_pay .input_address_1 {
    width: 275px;
    margin-right: 0px;
}

.table_info_pay .input_address_2 {
    width: 300px;
}

.table_info_pay .btn_bill_faq {
    display: block;
    width: 81px;
    font-weight: normal;
}

.table_info_pay .sel_type {
    width: 103px;
    height: 22px;
    -webkit-appearance: none;
    appearance: none;
    -moz-appearance: none;
    padding-left: 4px;
}

.table_info_pay .certi_input {
    margin-bottom: 15px;
}

.table_info_pay .sub_bill {
    margin-top: 12px;
}

.table_info_pay .sub_bill .caution_list {
    margin-bottom: 2px;
}

.table_info_pay .sub_info_table {
    margin-bottom: 17px;
}

.table_info_pay p {
    line-height: 1.7;
}

.table_info_pay .link_ref {
    font-size: 11px;
    color: #553de8;
}

.table_info_pay .agreement_box {
    font-size: 12px;
    line-height: 19px;
    width: auto;
    height: 62px;
    padding: 10px;
    border: 1px solid;
    border-color: #cdcece #e8e8e8 #e8e8e8 #cdcece;
    overflow-y: auto;
    margin: 8px 0;
}

.table_info_pay .agreement_box .agr_em_txt {
    font-size: 15px;
    font-weight: bold;
    text-decoration: underline
}

.table_info_pay textarea.agreement_box {
    display: block;
    width: 99%;
    padding: 4px .5%;
    resize: vertical;
}

.table_info_pay .check_agree {
    font-size: 12px;
    margin-top: 4px;
}

.table_info_pay .sub_span {
    background: url(//img.danawa.com/new/billing/img/ico_text.png) no-repeat 0 -350px;
    padding-left: 7px;
    margin-left: 3px;
}

.table_info_pay .deli_caution {
    padding: 10px 5px 10px 20px;
    position: relative;
    font-size: 11px;
    color: #666;
    letter-spacing: -0.07em;
}

.table_info_pay .deli_caution strong {
    color: #553de8;
}

.table_info_pay .deli_caution .strong_caution {
    font-weight: bold;
}

.table_info_pay .deli_caution .strong_caution strong {
    color: #553de8;
}

.table_info_pay .deli_caution .strong_caution em {
    font-weight: normal;
    font-style: normal;
}

.table_info_pay .deli_caution .check_agree {
    display: inline-block;
    margin: 10px 0 6px;
}

.table_info_bill {
    width: 100%;
}

.table_info_bill col.sub_subject {
    width: 155px;
}

.table_info_bill td {
    padding-left: 9px;
}

.table_info_bill input[type=text].input_sub_info {
    width: 100%;
    padding: 0;
}

.pay_success {
    border: 4px solid #e9e9e9;
    height: 102px;
    position: relative;
    margin: 40px 0 31px;
}

.pay_success .success_text {
    position: absolute;
    background: url(//img.danawa.com/new/shop_danawa/img/mypage/bg_pay_success2.png) no-repeat 50% 8px #fff;
    top: -4px;
    bottom: -4px;
    left: 95px;
    right: 95px;
    padding: 4px 0;
    overflow: hidden;
    *height: 100%;
    *width: 820px;
}

.pay_success .success_text.type--previewer {
    background-image: url(//img.danawa.com/new/shop_danawa/img/mypage/bg_pay_success3.png);
}

.pay_success .success_text p {
    width: 1px;
    white-space: nowrap;
    margin-left: -10px;
    overflow: hidden;
    text-indent: -300px;
}

.odr_num {
    position: relative;
    top: 77px;
    text-align: center;
    font-size: 14px;
    letter-spacing: -0.1em;
}

/* My Shopping Style */
.sub_header .head_cart span {
    background-position: 0 -300px;
    width: 95px;
}

.my_head {
    background: #553de8;
    color: #fff;
    height: 100px;
    width: 226px;
    text-align: left;
}

.my_head .my_h2 {
    font-size: 22px;
    letter-spacing: -0.025em;
    font-weight: bold;
    color: #fff;
    font-family: "Malgun Gothic", Dotum, "ë‹ì›€", "Apple SD Gothic Neo", Helvetica, Sans-serif;
    padding: 35px 0 0 32px;
    margin: 0;
}

.my_info {
    zoom: 1;
    width: 845px
}

.my_info:after {
    content: "";
    display: block;
    clear: both;
    height: 0;
}

.my_info h3 {
    width: 180px;
    font-size: 16px;
    line-height: 22px;
    color: #333;
    text-align: center;
    float: left;
    height: 60px;
    padding: 40px 5px 0;
}

.sub_info {
    background: url(//img.danawa.com/new/billing/img/pack_mine.png) no-repeat 0 -30px;
    padding-left: 51px;
    height: 100px;
}

.no_sub_info h3 {
    width: auto;
    padding-left: 42px;
}

.my_info .sub_info {
    margin-left: 190px;
}

.my_info .info_list {
    overflow: hidden;
    display: table;
    width: 100%;
    table-layout: fixed;
}

.my_info .info_list li {
    display: table-cell;
    text-align: center;
    padding-top: 24px;
    *float: left;
    *padding: 24px 25px 0;
}

.my_info .info_list .item {
    display: block;
    color: #666;
    font-size: 11px;
    line-height: 14px;
    margin-bottom: 1px;
}

.my_info .info_list .item_num {
    font-family: Tahoma, Dotum, "ë‹ì›€", "Apple SD Gothic Neo", Helvetica, Sans-serif;
    font-size: 30px;
    line-height: 42px;
    color: #553de8;
}

.my_info .info_list .num_none {
    color: #333;
}

.my_info .info_list .item_text {
    margin-top: 11px;
    display: block;
    font-size: 14px;
    line-height: 20px;
    color: #333;
}

.my_info .info_list .order_code {
    color: #553de8;
    text-decoration: underline;
}

.sect .order_duration {
    margin-bottom: 20px;
}





</style>