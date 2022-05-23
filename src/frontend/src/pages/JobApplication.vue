<template>
    <v-container>
        <JobApplication />
    </v-container>  
</template>

<script>
import JobApplication from '@/components/jobapplication'
export default {
    name: 'Job_list',
    components:{
        JobApplication
    },
    data(){
        return{

        }
    }
}
</script>

<style>

</style>