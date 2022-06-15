import Vue from 'vue';
import Vuex from 'vuex';
//import modules from '@/store/modules/index.js';
// import userStore from './userStore';
import userStore from '@/store/userStore'
import careprofileStore from '@/store/careprofileStore'
import caretargetStore from '@/store/caretargetStore'
import carenoteStore from '@/store/carenoteStore'
import userCalendar from '@/store/userCalendar'
import checkId from '@/store/checkId';
import cart from './cart'
import wish from './wish'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({ 
  modules:{
    // userStore,
    caretargetStore,
    careprofileStore,
    carenoteStore,
    userCalendar,
    cart,
    wish,
    userStore,
    checkId
  },
  plugins:[createPersistedState({
    paths:["userStore","checkId"]
  })],

})
