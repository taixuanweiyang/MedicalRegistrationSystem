<template>
    <body bgcolor="#C3E7FE">
        <el-header>
            <div class="text"><b>欢迎使用HIS挂号预约系统</b></div>
            <avatar></avatar>
        </el-header>    
        <el-carousel :interval="4000" type="card" height="505px">
            <el-carousel-item v-for="item in images" :key="item">
                <el-image style="height: 100%" :src="item" :fit="fill" ></el-image>
            </el-carousel-item>
        </el-carousel>
        <el-container style="width:80%">
        <el-main></el-main>
            <el-header class="table-title">
                <div class="text"><b>选取科室</b></div>
            </el-header>
            <el-main class="table-context">
          <div v-if="dName.length > 0">
            <div v-for="(dept, index) in dName" :key="index">
                <el-card class="box-card" shadow="always">
                  <div>
                    <el-button link @click="this.$store.state.dept = dept;$router.push('patients/center/appointment')"><strong>{{ dept }}</strong></el-button>
                  </div>
                  <el-divider/>
                  <div>{{ dInt[index] }}</div>
                </el-card>
              </div>
          </div>
          <div v-else class="table-context">数据加载中...</div>
        </el-main>
        </el-container>
    </body>  
</template>

<style scoped>
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.el-header{
    background-color: #7BC0FC;
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
.table-title{
    margin-left: 25%;
    color: #7BC0FC;
}
.table-context{
    margin-left: 25%;
    
}
.box-card{
    margin-top:10px;
    margin-bottom:10px;
    width:100%;
    height:100%;
    background-color: #E8F7FF;
}
.el-row {
  margin-bottom: 10px;
}
</style>

<script>
import img1 from "@/assets/img1.jpg"
import img2 from "@/assets/img2.jpg"
import img3 from "@/assets/img3.jpg"
import Avatar from "./Avatar.vue"
import axios from 'axios'

export default {
    name: 'Index',
    components:{
        Avatar,
    },
    data(){
        return {
        images: [
            img1,
            img2,
            img3,
        ],
        dName:[],
        dInt:[]
        }
    },
    created() {
    axios({
      method: "get",
      url: "http://8.130.117.156:8080/his/dept",
    })
      .then((res) => {
        this.dName = res.data.map((dept) => dept.name);
        this.dInt = res.data.map((dept) => dept.introduction);
      })
      .catch((error) => {
        console.error("Failed to fetch department data: ", error);
      });
  },
}
</script>