import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home.vue'
import Hotel from '@/components/hotel/Hotel'
import User from '@/components/users/User'
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
      path: '/users',
      name: 'users',
      component: User
    }
  ]
})
