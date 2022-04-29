<template>
<div class="list-item">
    <!-- <img :src="product.title" class="thumb"> -->
    <div class="info">
        <!-- <h3>{{product.productName}}</h3>
        <h6>{{price}}원</h6> -->
        <h1>{{product.title}}</h1>
        <h3>{{product.comment}}</h3>
        <v-btn
        @click="order">
        상세보기
        </v-btn>
    </div>
</div>  
</template>

<script>
export default {
name: 'componentproductItem',
props:{
    product:{
        required: true
    }
},
methods:{
    order(){
        this.$emit('order', this.product.postId);
    }
}

}
</script>

<style>

</style>