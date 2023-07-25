<template>
    <el-input v-model="search" placeholder="请输入患者Id" />
    <el-button type="primary" @click="Search()">查询</el-button>
    <el-table :data="searchTableData" style="width: 100%">
      <el-table-column align="center" prop="patientNumber" label="门诊号"  width="90"/>
      <el-table-column align="center" prop="time" label="病历填写时间" width="230" />
      <el-table-column align="center" prop="patientWord" label="主诉" width="230" />
      <el-table-column align="center" prop="dealAdvice" label="医生意见" width="230" />
      <el-table-column align="center" prop="medicalResult" label="诊断结果" width="230" />
      <el-table-column align="center" prop="doctorId" label="医生Id" width="230" />
      <el-table-column align="center" prop="patientId" label="患者Id" width="230" />
    </el-table>
</template>

<style scoped>
.el-input {
    width: 300px;
    margin-right:20px;
  }
</style>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      search: '',
      tableData: [] ,
      searchTableData:[],
    }
  },
  created(){
      // 发送GET请求获取挂号信息
      axios.get('http://8.130.117.156:8080/his/doctor/case/allinfo')
        .then(response => {
          this.tableData = response.data;
          this.searchTableData = this.tableData;
        })
        .catch(error => {
          // 请求失败处理
          console.error('Error fetching data:', error);
        });
  },
  methods:{
    Search() {
      if(this.search === '') {
        this.searchTableData = this.tableData;
      }
      else {
        this.searchTableData = this.tableData.filter((elem) => {
          return elem.patientId == this.search;
        });
      }
    }
  }
}
</script>