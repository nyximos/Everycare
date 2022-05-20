
<template>
<div class="container">
<div class="row">
<p class="display-5 mt-5">문의사항</p>
</div>
<div class="row">
    <div class=" d-md-flex justify-content-md-end">
        <button class="btn btn-primary" type="button">Create</button>
    </div>
</div>
<div class="row mt-3">
    <List />
</div>
</div>
</template>

<script>
import List from '@/components/List.vue';
export default {
components:{
    List
}

}
</script>

<style>

</style>