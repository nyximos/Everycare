import Vue from 'vue';
import Vuex from 'vuex';
import modules from '@/store/modules/index.js';
import careprofile from '@/store/careprofile.js';

Vue.use(Vuex);

export default new Vuex.Store({ 
  modules,
  careprofile
});

