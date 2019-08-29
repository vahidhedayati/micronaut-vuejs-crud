import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home.vue'
import Hotel from '@/components/hotel/Hotel'
import User from '@/components/users/User'
import Login from '@/components/login/Login'
import Logout from '@/components/Logout'

import { defaultLocale } from '../components/i18n/i18n'
Vue.use(Router)
const router = new Router({
  routes: [

    {
      path: 'logout',
      name: "Logout",
      component: Logout ,
      meta: { requiresAuth:false}
    },
    {
      path: 'login',
      name: 'Login',
      component: Login,
      meta: { requiresAuth:false}
    },
    {
      path: '/',
      redirect: `/${defaultLocale}`,
      meta: { requiresAuth:false}
    },
    {
      path: '/:locale',
      component: {
        template: '<router-view />',
      },
      children: [
        {
          path: 'home',
          name: 'home',
          component: Home,
          meta: { requiresAuth: false}
        },
        {
          path: 'logout',
          name: "logout",
          component: Logout ,
          meta: { requiresAuth:false}
        },
        {
          path: 'login',
          name: 'Login',
          component: Login,
          meta: { requiresAuth:false}
        },
        {
          path: 'hotel/:id',
          component: {
            template: '<div>hotelShow</div>',
            meta: { requiresAuth: true , roles:['ROLE_ADMIN', 'ROLE_USERS']}

          },
        },
        {
          path: 'hotel',
          component: Hotel,
          meta: { requiresAuth: true , roles:['ROLE_ADMIN', 'ROLE_USERS']}
        },
        {
          path: 'users',
          component: User,
          meta: { requiresAuth: true , roles:['ROLE_ADMIN', 'ROLE_USERS']}
        },
      ],
    },
    {
      path: '/hotel',
      name: 'hotel',
      component: Hotel,
      meta: { requiresAuth: true , roles:['ROLE_ADMIN', 'ROLE_USERS']}
    },
    {
      path: '/users',
      name: 'users',
      component: User,
      meta: { requiresAuth: true , roles:['ROLE_ADMIN', 'ROLE_USERS']}
    },
    {
      path: '*',
      redirect:"/"
    }
  ],
  scrollBehavior (to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { x: 0, y: 0 }
    }
  }
});
var findOne = function (haystack, arr) {
  return arr.some(function (v) {
    return haystack.indexOf(v) >= 0;
  });
};
router.beforeEach((to, from, next) => {
  const authUser = JSON.parse(window.localStorage.getItem('vuex'))
  if(!to.meta.requiresAuth && !to.meta.roles) {
  return next()
}

if(!authUser ||authUser &&  authUser.user && !authUser.user.token) {
  return next({name:'Login'})
}

if (findOne(to.meta.roles,authUser.auth.isAuthenticated.roles)) {
  console.log(' yes >'+to.meta.roles+ " <> "+authUser.auth.isAuthenticated.roles);
  return next()
} else {
  return next({name:'Home'})
}
})

export default router

