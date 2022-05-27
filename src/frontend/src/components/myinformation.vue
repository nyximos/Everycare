<template>
    <div class="myinfo_comp">
        <h2>내 정보</h2>
        <br />
        <table class="myinfo_table" border="1" width="600px" cellpadding="15px" frame="void">
            <tr>
                <th id="id-line" width="20%">아이디</th>
                <td id="id-text" width="30%"> {{memberData.id}}</td>
                <th id="pw-line" width="20%">비밀번호</th>
                <td id="pw-text" width="30%">....</td>
            </tr>
            <tr>
                <th id="name-line">이름</th>
                <td id="name-text" width="30%"> {{memberData.name}}</td>
                <th id="gen-line" width="20%">성별</th>
                <td id="gen-text-line" width="30%"> {{memberData.gender}}</td>
            </tr>
            <tr>
                <th id="bir-line">생년월일</th>
                <td id="bir-text-line" colspan="3"><v-col
          cols="5"
          sm="5"
          md="4"
        >
        {{memberData.birth}}
          <v-text-field v-model="birth2"
          ></v-text-field>
        </v-col></td>
            </tr>
            <tr>
                <th id="num-line">연락처</th>
                <td id="num-text-line" colspan="3"><v-col
          cols="5"
          sm="5"
          md="4"
        >
        {{memberData.phone}}
          <v-text-field v-model="phone2"
          ></v-text-field>
        </v-col></td>
            </tr>
            <tr>
                <th id="mail-line">이메일</th>
                <td id="mail-text-line" colspan="3"><v-col
          cols="5"
          sm="5"
          md="4"
        >
        {{memberData.email}}
          <v-text-field v-model="email2"
          ></v-text-field>
        </v-col></td>
            </tr>
            <tr>
                <th id="addr-line"><br /><br />주소<br /><br /><br /></th>
                <td id="addr-text-line" colspan="3"><v-col
          cols="5"
          sm="5"
          md="4"
        >
        {{memberData.zipcode}}
        {{memberData.address}}
        {{memberData.detailedAddress}}
          <v-text-field v-model="zipcode2"
          ></v-text-field>
          <v-text-field v-model="address2"
          ></v-text-field>
          <v-text-field v-model="detailedAddress2"
          ></v-text-field>
        </v-col></td>
            </tr>
        </table>

        <br />
        <div id="btn-box">
            <v-btn id="ok-btn" color="#69f0ae" dark > 수정 </v-btn>
            <v-btn id="cen-btn" color="grey" dark > 뒤로 </v-btn>
        </div>
    </div>
</template>

<script>
export default {
    data(){
        return{
           memberData:[],
           zipcode2:'',
           address2:'',
           detailedAddress2:'',
           email2:'',
           phone2:'',
           birth2:'',
        }
    },
    mounted(){
         this.$http
            .get('/api/dashboard/account/', {
                withCredentials: true,
            })
            .then((res) => {
                console.log(res.data.body);
                this.memberData = res.data.body;
                 this.id = res.data.body.id;
                this.name = res.data.body.name;
                this.gender = res.data.body.gender;
                this.birth = res.data.body.birth;
                this.phone = res.data.body.phone;
                this.address = res.data.body.address;
                this.detailedAddress = res.data.body.detailedAddress;
                this.zipcode = res.data.body.zipcode;
                this.email = res.data.body.email;
            })
            .catch((err) => {
                console.log(err);
            });
    }
};
</script>

<style>
.myinfo_comp{
    margin: auto;
    width: 1000px;
}
.myinfo_table {
    height: 500px;
}
table {
    width: 100%;
    border: 0;
    border-collapse: collapse;
}
th,
td {
    border: solid 1px;
}
#id-line {
    border-left: none;
    border-right-color: #53c38d;
    border-bottom-color: #53c38d;
    background-color: #69f0ae;
    border-top: 2px solid #17af65;
    text-align: center;
}
#id-text {
    border-top: 2px solid #17af65;
}
#id-text,
#name-text {
    border-right-color: #53c38d;
    border-bottom-color: #53c38d;
}
#pw-line {
    border-top: 2px solid #17af65;
}
#pw-line,
#gen-line {
    background-color: #69f0ae;
    border-right-color: #53c38d;
    border-bottom-color: #53c38d;
    text-align: center;
}
#pw-text {
    border-right: none;
    border-top: 2px solid #17af65;
    border-bottom-color: #53c38d;
}
#name-line,
#bir-line,
#num-line,
#mail-line,
#con-line {
    border-left: none;
    background-color: #69f0ae;
    text-align: center;
    border-right-color: #53c38d;
    border-bottom-color: #53c38d;
}
#addr-line {
    background-color: #69f0ae;
    border-right-color: #53c38d;
    border-bottom: 2px solid #17af65;
    text-align: center;
    border-left: none;
}
#addr-text-line {
    border-right: none;
    border-bottom: 2px solid #17af65;
}

#bir-text-line,
#num-text-line,
#gen-text-line,
#mail-text-line,
#con-text-line {
    border-right: none;
    border-right-color: #53c38d;
    border-bottom-color: #53c38d;
}
#ok-btn {
    margin: auto;
    display: block;
    float: left;
    margin: 10px;
}
#cen-btn {
    margin: auto;
    display: block;
    float: left;
    margin: 10px;
}
#ok-btn {
    position: relative;
    left: 600px;
}
#cen-btn {
    position: relative;
    left: 600px;
}
</style>
