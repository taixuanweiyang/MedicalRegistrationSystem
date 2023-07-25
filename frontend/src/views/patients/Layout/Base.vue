<template>
    <div class="common-layout">
    <el-container style="height:100vh">
      <el-header>
        <div class="text"><b>欢迎使用HIS挂号预约系统</b></div>
        <avatar></avatar>
      </el-header>
      <el-container>
        <el-aside width="auto">
        <el-menu active-text-color="#fed71a"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <el-menu-item index="1">
          <el-icon><location /></el-icon>
          <router-link class="text" to="/patients">预约挂号</router-link>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <router-link class="text" to="/patients/center/medicine">处方管理</router-link>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon><icon-menu /></el-icon>
          <router-link class="text" to="/" @click="deleteStorage">退出登录</router-link>
        </el-menu-item>
      </el-menu>
        </el-aside>
        <el-main>
          <RouterView></RouterView>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>
.el-header{
    background-color: #3491FA;
}
.el-aside{
    background-color: #063078;
}
.el-header{
    background-color: #3491FA;
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.text
{
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 24px;
    color: azure;
    font-family:Arial, Helvetica, sans-serif

}
.el-menu-item{
      background-color: #063078 !important;
      color: #fff;
      span {
        color: #fff !important;
      }
}
.el-menu-item.is-active {
      background-color: #3370ff !important;
      color: #fff;
      span {
        color: #fff !important;
      }
}

a {
  text-decoration: none;
}
 
.router-link-active {
  text-decoration: none;
}

</style>

<script>
import axios from 'axios'
import Avatar from "../Avatar.vue"

export default {
    name: 'Base',
    components:{
        Avatar,
    },
    data() {
    return {
      isCollapse:'false'
    }
    },
    methods: {
      deleteStorage(){
        window.localStorage.removeItem("pInfo");
      }
    }
}
</script>