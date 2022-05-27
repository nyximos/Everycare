import Vue from 'vue';
import Vuex from 'vuex';
//import modules from '@/store/modules/index.js';
// import userStore from './userStore';
import userStore from '@/store/userStore'
import careprofileStore from '@/store/careprofileStore'

import cart from './cart'
import wish from './wish'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({ 
  modules:{
    // userStore,
    careprofileStore,

    cart,
    wish,
    userStore,
  },
  plugins:[createPersistedState({
    paths:["userStore"]
  })],

})
