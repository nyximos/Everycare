<template>
<v-row class="mt-10">
    <v-toolbar class="elevation-0">
      <v-spacer></v-spacer>
      <v-cols col="1">
        <v-text-field
        v-model="searchText"
        @input="search"
        hide-details
        prepend-icon="mdi-magnify"
        single-line
      ></v-text-field>
      </v-cols>
      
      <v-btn icon @click="goCart">
        <v-icon>mdi-cart</v-icon>
          <v-badge v-if="!this.$store.state.cart.cart.length==0" :content="this.$store.state.cart.cart.length" />
      </v-btn>

      <v-btn icon @click="wish">
        <v-icon>mdi-heart</v-icon>
          <v-badge v-if="!this.$store.state.wish.wish.length==0" :content="this.$store.state.wish.wish.length" />
      </v-btn>
      
      <v-btn icon @click="goCreateStore">
        <v-icon>mdi-login</v-icon>
      </v-btn>
    </v-toolbar>
    </v-row>  
</template>

<script>
export default {
data(){
    return{
        searchText:this.searchText,
        result:this.result,
        searchvalue:false
    }
},
methods:{
    search(){
     this.$http
     .get('/api/store/products/name',
     {params: {name: this.searchText}},{
        withCredentials:true
      })
		.then((res)=>{
        this.result=res.data.body
        this.searchvalue=true
        this.$emit('result', this.result)
        this.$emit('searchvalue', this.searchvalue)
      }).catch(err =>{
				console.log(err);
			})
    },
    goCart(){
      this.$emit('goCart')
    },
    wish(){
      this.$emit('wish')
    },
    goCreateStore(){
      this.$emit('goCreateStore')
    },
}
}
</script>

<style>

</style>