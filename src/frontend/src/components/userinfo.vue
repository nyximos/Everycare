<template>
  <div>
        <el-card class="fade-in-left">

            <h3>회원정보수정</h3>
            <el-input type="text" v-model="state.name" placeholder="변경할 이름" style="width:200px" />
            <el-button @click="handleUpdateAction" style="margin:10px">정보변경</el-button>
        </el-card>
    </div>
</template>

<script>
import { reactive } from 'vue';
import axios from 'axios';

export default {
    setup () {
        const state = reactive({
            name : '',
            token: sessionStorage.getItem("TOKEN")
        });

        const handleUpdateAction = async() => {
            if(state.token !== null) {
                const url = `/member/update`;
                const headers = { 
                    "Content-Type": "application/json",
                    "token": state.token
                };
                const body = {
                    name: state.name
                }
                const response = await axios.put(url, body, { headers })
                console.log(response.data);
                if(response.data.status === 200) {
                    alert('정보 변경 완료');
                }
            }
        }

    return { state, handleUpdateAction }
    }
}
</script>

<style>

</style>