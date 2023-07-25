import { createApp } from 'vue'
import { createStore } from 'vuex'

import App from './App.vue'
import router from './router';
import ElementPlus from 'element-plus'
import moment from 'moment'


import 'element-plus/dist/index.css'

const store = createStore({
  state () {
    return {
      pInfo:{},
      dInfo:{},
      dept:'',
      registration:'',
    }
  },
})

const app=createApp(App);

app.use(router);
app.use(store);
app.use(ElementPlus);
app.config.globalProperties.$moment=moment;

app.mount('#app')

// 在此处进行配置全局的路由守卫（全局前置钩子）
// 目的是：判断当前用户中本地存储是否有token，使用户在登录之前，只能访问到/login页面，其他的页面是访问不到的。
// router.beforeEach((to, from, next) => {
//     let dInfo = window.localStorage.getItem("dInfo"); //在本地存储中获取token
//     let pInfo = window.localStorage.getItem("pInfo"); //在本地存储中获取token
//     if (dInfo || pInfo) {
//       next();
//     } else {
//       //在没有token的前提下，to下面的path是否为/login，如果不是则页面跳转到登录页面
//       if (to.path == "/") {
//         next();
//       } else {
//         next({ path: "/" }); //跳转页面到login页
//       }
//     }
//   });
