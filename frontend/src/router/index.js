import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home.vue'
import Hotel from '@/components/hotel/Hotel'
import User from '@/components/users/User'
Vue.use(Router)
import { defaultLocale } from '../components/i18n/i18n'
export default new Router({
  routes: [
    {
        path: '/home',
        name: 'home',
        component: Home
    },
    {
      path: '/',
      redirect: `/${defaultLocale}`,
    },
    {
      path: '/:locale',
      component: {
        template: '<router-view />',
      },
      children: [
        {
          path: 'home',
          component: Home
        },
        {
          path: 'hotel/:id',
          component: {
            template: '<div>hotelShow</div>',
          },
        },
        {
          path: 'hotel',
          component: Hotel
        },
        {
          path: 'users',
          component: User
        },
      ],
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
