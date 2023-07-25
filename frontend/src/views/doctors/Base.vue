<template>
  <div class="common-layout">
    <el-container style="height:100vh">
      <el-header>
        <div class="text"><b>欢迎使用HIS信息管理系统</b></div>
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
          <router-link class="text" to="/doctors/appointment">预约管理</router-link>
        </el-menu-item>
        <el-menu-item index="2">
          <el-icon><icon-menu /></el-icon>
          <router-link class="text" to="/doctors/medicine">处方管理</router-link>
        </el-menu-item>
        <el-menu-item index="3">
          <el-icon><icon-menu /></el-icon>
          <router-link class="text" to="/doctors/record">病历管理</router-link>
        </el-menu-item>
        <el-menu-item index="4">
          <el-icon><SwitchButton /></el-icon>
          <router-link class="text" to="/" @click="deleteStorage">退出登录</router-link>
        </el-menu-item>
        </el-menu>
        </el-aside>
        <el-main>
            <RouterView />
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

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 200px;
}
</style>

<script>
import Avatar from "./Avatar.vue"
export default {
  components:{
        Avatar,
    },
  data () {
    return {
      // 是否折叠
      isCollapse: false
    }
  },
  methods: {
    toggleCollapse () {
      this.isCollapse = !this.isCollapse
    },
    deleteStorage(){
        window.localStorage.removeItem("dInfo");
      }
  }
}
</script>