import { createRouter, createWebHistory } from 'vue-router'
import TestView from '../views/test/TestView.vue'
import HomeView from '../views/HomeView.vue'

const routes = [
    { path: '/', name: 'home', component: HomeView },
    { path: '/test', name: 'Test', component: TestView },
]
const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
