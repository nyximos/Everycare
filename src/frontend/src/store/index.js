import Vue from 'vue';
import Vuex from 'vuex';
//import modules from '@/store/modules/index.js';
// import userStore from './userStore';
import careprofileStore from './careprofileStore'
import findpeople from './findpeople'
Vue.use(Vuex);

export default new Vuex.Store({ 
  modules:{
    // userStore,
    careprofileStore,
    findpeople
  }

});