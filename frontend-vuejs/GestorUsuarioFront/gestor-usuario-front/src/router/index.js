import { createRouter, createWebHistory } from 'vue-router'

import UserManagerView from '@/views/UserManagerView.vue'
import LoginView from '@/views/LoginView.vue'
import AddUserFormComp from "@/components/AddUserFormComp.vue";
import HomeView from "@/views/HomeView.vue";
import forbbiden from "@/views/forbbidenView.vue";


const routes = [
  {
    path: '/manager/user',
    name: 'UserManagerView',
    component: UserManagerView,
    meta: { requiresAuth: true },
  },
  {
    path: '/manager/add/user',
    name: 'AddUserFormComp',
    component: AddUserFormComp,
   
  },
  {
    path: '/',
    name: 'LoginView',
    component: LoginView,
  },
  {
    path: '/home',
    name: 'HomeView',
    component: HomeView,
    meta: { requiresAuth: true },
  },
  {
    path: '/forbbiden',
    name: 'forbbiden',
    component: forbbiden,
  },
]




const router = createRouter({ 
  history: createWebHistory(), 
  scrollBehavior: () => ({ y: 0 }),
  routes 
})

router.beforeEach((to, from, next) => {
  const userData = localStorage.getItem('user');
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (userData === "" || userData===null) {

      next('/forbbiden');
    } else {
      next()
    }
  } else {
    next();
  }
});


export default router