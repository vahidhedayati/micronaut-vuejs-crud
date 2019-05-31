import Vue from 'vue'
import Router from 'vue-router'
import Welcome from '@/components/Welcome'
import Home from '@/views/Home.vue'
import Hotel from '@/components/hotel/Hotel'
Vue.use(Router)

export default new Router({
  routes: [
    {
        path: '/',
        name: 'home',
        component: Home
    },
    {
      path: '/hotel',
      name: 'hotel',
      component: Hotel
    },
    {
      path: '/welcome',
      name: 'Welcome',
      component: Welcome
    }
  ]
})
