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
        path: '/',
        name: 'home',
        component: Home
    },
    {
      path: '/loc',
      redirect: `/${defaultLocale}`,
    },
    {
      path: '/:locale',
      component: {
        template: '<router-view />',
      },
      children: [
        {
          path: '',
          component: {
            template: '<div>Home</div>',
          },
        },
        {
          path: 'products/:id',
          component: {
            template: '<div>ProductShow</div>',
          },
        },
        {
          path: 'products',
          component: {
            template: '<div>ProductIndex</div>',
          },
        },
        {
          path: 'about',
          component: {
            template: '<div>About</div>',
          },
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
