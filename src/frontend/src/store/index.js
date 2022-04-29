import Vue from 'vue';
import Vuex from 'vuex';
//import modules from '@/store/modules/index.js';
// import userStore from './userStore';
import userStore from '@/store/userStore'
import userStore2 from '@/store/userStore2'
import careprofileStore from './careprofileStore'

import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({ 
  modules:{
    // userStore,
    careprofileStore,

    userStore,
    userStore2,
  },
  plugins:[createPersistedState({
    paths:["userStore2"]
  })],

})
