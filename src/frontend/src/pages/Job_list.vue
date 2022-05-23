<template>
    <v-container>
        <componentjoblist />
    </v-container>
</template>

<script>
import componentjoblist from '@/components/job_list';
export default {
    name: 'Job_list',
    components: {
        componentjoblist,
    },
    data() {
        return {};
    },
};
</script>

<style></style>
