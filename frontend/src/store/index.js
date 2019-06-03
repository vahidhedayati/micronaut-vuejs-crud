import Vue from 'vue'
import Vuex from 'vuex'
import vuexI18n from 'vuex-i18n'
import axios from 'axios'
import VueAxios from 'vue-axios'
import createPersistedState from 'vuex-persistedstate'
import vehicles from './modules/vehicles';

import en from '../locales/en';
import de from '../locales/de'
Vue.use(Vuex)
Vue.use(VueAxios, axios)

const store = new Vuex.Store({
  plugins: [createPersistedState()],
  modules: {
    vehicles
  }
})

Vue.use(vuexI18n.plugin, store, {
  moduleName: 'i18n',
  onTranslationNotFound (locale, key) {
    console.warn(`i18n :: Key '${key}' not found for locale '${locale}'`)
  }
})



Vue.i18n.add('en', de)
Vue.i18n.add('de', en)

Vue.i18n.set('en')

export default store
