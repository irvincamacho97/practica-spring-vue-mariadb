import { createRouter, createWebHistory } from 'vue-router'

import UserManagerView from '@/views/UserManagerView.vue'
import LoginView from '@/views/LoginView.vue'
import AddUserFormComp from "@/components/AddUserFormComp.vue";
import HomeView from "@/views/HomeView.vue";
const routes = [
  {
    path: '/manager/user',
    name: 'UserManagerView',
    component: UserManagerView,
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
  }
]

const router = createRouter({ 
  history: createWebHistory(), 
  scrollBehavior: () => ({ y: 0 }),
  routes 
})

export default router